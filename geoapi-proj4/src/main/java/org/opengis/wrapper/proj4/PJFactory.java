/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.util.Set;
import java.util.Map;
import java.util.Collections;
import java.util.MissingResourceException;
import java.awt.geom.AffineTransform;
import javax.measure.Unit;
import javax.measure.quantity.Angle;

import org.opengis.util.*;
import org.opengis.parameter.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.Identifier;
import org.proj4.PJ;

import static org.proj4.PJ.DIMENSION_MAX;


/**
 * The base class for factories of {@literal Proj.4} wrappers. This base class provides static methods working
 * directly with the Proj.4 definition strings. Subclasses provides implementation of GeoAPI factory interfaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJFactory implements Factory {
    /**
     * The {@literal Proj.4} parameter used for declaration of axis order. This parameter is handled in a special
     * way by this factory: it can be a comma-separated list of axis order definitions, in which case the second
     * value is used as the axis order of the {@link ProjectedCRS#getBaseCRS()}.
     *
     * <p>Another departure from Proj.4 is that Proj.4 expects the axis parameter to be exactly 3 characters long,
     * while our code accepts 2 characters as well. We relax the Proj.4 rule because we use the number of characters
     * for determining the number of dimensions. This is okay since 1 character = 1 axis.</p>
     */
    static final String AXIS_ORDER_PARAM = "+axis=";

    /**
     * The character used for separating the {@literal Proj.4} axis order declarations.
     */
    static final char AXIS_ORDER_SEPARATOR = ',';

    /**
     * For sub-class constructors only.
     */
    protected PJFactory() {
    }

    /**
     * Returns the implementor responsible for creating this factory implementation.
     */
    @Override
    public Citation getVendor() {
        return SimpleCitation.VENDOR;
    }

    /**
     * Creates a simple identifier from the given properties. The full set of properties is
     * documented in the {@link org.opengis.referencing.ObjectFactory} class. However this
     * implementation uses only the following properties:
     *
     * <table class="ogc">
     *   <caption>Recognized properties</caption>
     *   <tr>
     *     <th>Property name</th>
     *     <th>Value type</th>
     *     <th>Value given to</th>
     *   </tr>
     *   <tr>
     *     <td>{@value org.opengis.referencing.IdentifiedObject#NAME_KEY}</td>
     *     <td>{@link Identifier} or {@link String}</td>
     *     <td>{@link IdentifiedObject#getName()}</td>
     *   </tr>
     *   <tr>
     *     <td>{@value org.opengis.metadata.Identifier#CODESPACE_KEY}</td>
     *     <td>{@link String}</td>
     *     <td>{@link Identifier#getCodeSpace()} on the {@linkplain IdentifiedObject#getName name}</td>
     *   </tr>
     * </table>
     *
     * @param  properties  the properties, or {@code null} if none.
     * @return a reference identifier for the given code space and code, or {@code null}.
     * @throws IllegalArgumentException if any of the requested value is an empty string.
     */
    public static Identifier createIdentifier(final Map<String,?> properties) {
        if (properties != null) {
            final Object name = properties.get(IdentifiedObject.NAME_KEY);
            if (name != null) {
                if (name instanceof Identifier) {
                    return (Identifier) name;
                }
                final Object cs = properties.get(Identifier.CODESPACE_KEY);
                return createIdentifier(cs != null ? cs.toString() : null, name.toString());
            }
        }
        return null;
    }

    /**
     * Creates a simple identifier from the given code and codespace. Identifiers are code
     * under the control of some authority (the code space). For example a widely used
     * identifier is {@code EPSG:4326}.
     *
     * <p>In principle, every ISO 19111 compliant {@linkplain org.opengis.referencing.IdentifiedObject
     * identified object} must have an identifier. However this {@code proj4} package is not strict
     * about that. Users are nevertheless encouraged to use this method for creating an identifier
     * before to invoke the other static methods in this {@code PJFactory} class.</p>
     *
     * @param  codespace  the code space (for example {@code "EPSG"}), or {@code null} if none.
     * @param  code       the code, for example {@code "4326"}.
     * @return a reference identifier for the given code space and code.
     * @throws NullPointerException if the code argument is {@code null}.
     * @throws IllegalArgumentException if any of the given argument is an empty string.
     */
    public static Identifier createIdentifier(String codespace, String code) {
        if ((code = code.trim()).isEmpty() || (codespace != null && (codespace = codespace.trim()).isEmpty())) {
            throw new IllegalArgumentException("Codespace and code must be non-empty.");
        }
        return new PJIdentifier(codespace, code);
    }

    /**
     * Creates a new CRS from the given {@literal Proj.4} definition string. The CRS can have an arbitrary number
     * of dimensions in the [2-{@value org.proj4.PJ#DIMENSION_MAX}] range. However Proj.4 will handle at most the
     * 3 first dimensions. All supplemental dimensions will be simply copied unchanged by {@link MathTransform}
     * implementations.
     *
     * @param  crsId       the name of the CRS to create, or {@code null} if none.
     * @param  datumId     the name of the datum to create, or {@code null} if none.
     * @param  definition  the Proj.4 definition string.
     * @param  dimension   the number of dimension of the CRS to create.
     * @return a CRS created from the given definition string and number of dimensions.
     * @throws NullPointerException if the definition string is {@code null}.
     * @throws IllegalArgumentException if one of the given argument has an invalid value.
     */
    public static CoordinateReferenceSystem createCRS(final Identifier crsId,
            final Identifier datumId, String definition, final int dimension)
            throws IllegalArgumentException
    {
        if ((definition = definition.trim()).isEmpty()) {
            throw new IllegalArgumentException("The definition must be non-empty.");
        }
        if (dimension < 2 || dimension > DIMENSION_MAX) {
            throw new IllegalArgumentException("Illegal number of dimensions: " + dimension);
        }
        //
        // Custom parsing of the "+axis=" parameter.
        // This code may modify the definition string.
        //
        String orientation = null;
        int beginParam = definition.indexOf(AXIS_ORDER_PARAM);
        if (beginParam >= 0) {
            beginParam += AXIS_ORDER_PARAM.length();
            final int length = definition.length();
            while (beginParam < length) { // Skip whitespaces.
                final int c = definition.codePointAt(beginParam);
                if (!Character.isWhitespace(c)) break;
                beginParam += Character.charCount(c);
            }
            final StringBuilder modified = new StringBuilder(length);
            modified.append(definition, 0, beginParam);
            int endParam = PJCRS.Projected.findWordEnd(definition, beginParam);
            orientation = definition.substring(beginParam, endParam);
            modified.append(PJCRS.Projected.ensure3D(orientation));
            if (endParam < length && definition.charAt(endParam) == AXIS_ORDER_SEPARATOR) {
                endParam = PJCRS.Projected.findWordEnd(definition, endParam+1);
                orientation = definition.substring(beginParam, endParam);
            }
            modified.append(definition, endParam, length);
            definition = modified.toString();
        }
        //
        // Create the Proj.4 wrapper.
        //
        final PJDatum datum = new PJDatum(datumId, definition);
        final PJ.Type type = datum.getType();
        final CoordinateReferenceSystem crs;
        switch (type) {
            case GEOCENTRIC: crs = new PJCRS.Geocentric(crsId, datum, dimension); break;
            case GEOGRAPHIC: crs = new PJCRS.Geographic(crsId, datum, dimension); break;
            case PROJECTED:  crs = new PJCRS.Projected (crsId, datum, dimension, orientation); break;
            default: throw new UnsupportedOperationException("Unknown CRS type: " + type);
        }
        return crs;
    }

    /**
     * Creates an operation for conversion or transformation between two coordinate reference systems.
     * This given source and target CRS must be instances created by this factory.
     *
     * @param  identifier  the name of the operation to create, or {@code null} if none.
     * @param  sourceCRS   the source coordinate reference system.
     * @param  targetCRS   the target coordinate reference system.
     * @return a coordinate operation for transforming coordinates from the given source CRS to the given target CRS.
     * @throws ClassCastException if the given CRS are not instances created by this class.
     */
    public static CoordinateOperation createOperation(final Identifier identifier,
            final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem targetCRS)
            throws ClassCastException
    {
        return new PJOperation(identifier, (PJCRS) sourceCRS, (PJCRS) targetCRS);
    }

    /**
     * Returns the exception to throw when a feature is not yet supported.
     */
    static FactoryException unsupportedOperation() {
        return new FactoryException("Not supported yet.");
    }

    /**
     * A factory for {@linkplain CoordinateReferenceSystem Coordinate Reference System} objects
     * created from property maps.
     *
     * <p>The supported methods in this class are:</p>
     *
     * <ul>
     *   <li>{@link #createGeocentricCRS(Map, GeodeticDatum, CartesianCS)}</li>
     *   <li>{@link #createGeographicCRS(Map, GeodeticDatum, EllipsoidalCS)}</li>
     *   <li>{@link #createProjectedCRS(Map, GeographicCRS, Conversion, CartesianCS)}</li>
     * </ul>
     *
     * All other methods throw a {@link FactoryException}.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Objects extends PJFactory implements CRSFactory {
        /**
         * Creates a new factory.
         */
        public Objects() {
        }

        /**
         * Appends the prime meridian to the given definition string buffer.
         *
         * @param def  the definition string buffer.
         * @param pm   the prime meridian, or {@code null} if none.
         */
        private static void appendPrimeMeridian(final StringBuilder def, final PrimeMeridian pm) {
            if (pm != null) {
                double lon = pm.getGreenwichLongitude();
                final Unit<Angle> unit = pm.getAngularUnit();
                if (unit != null) {
                    lon = unit.getConverterTo(Units.DEGREE).convert(lon);
                }
                def.append(" +pm=").append(lon);
            }
        }

        /**
         * Appends the axis directions in the given definition string buffer.
         *
         * @param  def        the definition string buffer.
         * @param  cs         the coordinate system.
         * @param  dimension  the number of dimension to format (may be lower than the CS dimension).
         * @throws FactoryException if an axis direction is not supported.
         */
        private static void appendAxisDirections(final StringBuilder def, final CoordinateSystem cs,
                final int dimension) throws FactoryException
        {
            for (int i=0; i<dimension; i++) {
                final AxisDirection dir = cs.getAxis(i).getDirection();
                final char c;
                     if (dir == AxisDirection.EAST ) c = 'e';
                else if (dir == AxisDirection.WEST ) c = 'w';
                else if (dir == AxisDirection.NORTH) c = 'n';
                else if (dir == AxisDirection.SOUTH) c = 's';
                else if (dir == AxisDirection.UP   ) c = 'u';
                else if (dir == AxisDirection.DOWN ) c = 'd';
                else throw new FactoryException("Unsupported axis direction: " + dir);
                def.append(c);
            }
        }

        /**
         * Creates a geographic or geocentric coordinate reference system.
         *
         * @param  type        {@code "latlon"} or {@code "geocent"}.
         * @param  properties  name to give to the new object. Available properties are
         *                     {@linkplain PJFactory#createIdentifier(Map) listed there}.
         * @param  datum       geodetic datum to use in created CRS.
         * @param  cs          the ellipsoidal coordinate system for the created CRS.
         * @return the coordinate reference system for the given properties.
         * @throws FactoryException if the object creation failed.
         */
        private static CoordinateReferenceSystem createGeodeticCRS(final String type, final Map<String,?> properties,
                final GeodeticDatum datum, final CoordinateSystem cs) throws FactoryException
        {
            final int           dimension  = cs.getDimension();
            final Identifier    name       = createIdentifier(properties);
            final Ellipsoid     ellipsoid  = datum.getEllipsoid();
            final StringBuilder definition = new StringBuilder(100);
            definition.append("+proj=").append(type)
                    .append(" +a=").append(ellipsoid.getSemiMajorAxis())
                    .append(" +b=").append(ellipsoid.getSemiMinorAxis());
            appendPrimeMeridian(definition, datum.getPrimeMeridian());
            appendAxisDirections(definition.append(' ').append(AXIS_ORDER_PARAM), cs, Math.min(dimension, 3));
            try {
                return createCRS(name, datum.getName(), definition.toString(), dimension);
            } catch (IllegalArgumentException e) {
                throw new FactoryException(e.getMessage(), e);
            }
        }

        /**
         * Creates a geocentric coordinate reference system.
         *
         * @param  properties  name to give to the new object. Available properties are
         *                     {@linkplain PJFactory#createIdentifier(Map) listed there}.
         * @param  datum       geodetic datum to use in created CRS.
         * @param  cs          the coordinate system for the created CRS.
         * @return the coordinate reference system for the given properties.
         * @throws FactoryException if the object creation failed.
         */
        @Override
        public GeocentricCRS createGeocentricCRS(final Map<String,?> properties,
                final GeodeticDatum datum, final CartesianCS cs) throws FactoryException
        {
            return (GeocentricCRS) createGeodeticCRS("geocent", properties, datum, cs);
        }

        /**
         * Creates a geographic coordinate reference system.
         * It can be <var>Latitude</var>/<var>Longitude</var> or
         * <var>Longitude</var>/<var>Latitude</var>.
         *
         * @param  properties  name to give to the new object. Available properties are
         *                     {@linkplain PJFactory#createIdentifier(Map) listed there}.
         * @param  datum       geodetic datum to use in created CRS.
         * @param  cs          the ellipsoidal coordinate system for the created CRS.
         * @return the coordinate reference system for the given properties.
         * @throws FactoryException if the object creation failed.
         */
        @Override
        public GeographicCRS createGeographicCRS(final Map<String,?> properties,
                final GeodeticDatum datum, final EllipsoidalCS cs) throws FactoryException
        {
            return (GeographicCRS) createGeodeticCRS("latlon", properties, datum, cs);
        }

        /**
         * Creates a projected coordinate reference system from a defining conversion.
         * The projection and parameter names in the {@code conversionFromBase} can be
         * Proj.4 names, OGC names, EPSG names or GeoTIFF names.
         *
         * @param  properties  name to give to the new object. Available properties are
         *                     {@linkplain PJFactory#createIdentifier(Map) listed there}.
         * @param  baseCRS     geographic coordinate reference system to base the projection on.
         * @param  conversionFromBase  the defining conversion.
         * @param  derivedCS   the coordinate system for the projected CRS.
         * @return the coordinate reference system for the given properties.
         * @throws FactoryException if the object creation failed.
         */
        @Override
        public ProjectedCRS createProjectedCRS(final Map<String,?> properties, final GeographicCRS baseCRS,
                final Conversion conversionFromBase, final CartesianCS derivedCS) throws FactoryException
        {
            final int                 dimension  = derivedCS.getDimension();
            final Identifier          name       = createIdentifier(properties);
            final EllipsoidalCS       baseCS     = baseCRS.getCoordinateSystem();
            final GeodeticDatum       datum      = baseCRS.getDatum();
            final Ellipsoid           ellipsoid  = datum.getEllipsoid();
            final ParameterValueGroup parameters = conversionFromBase.getParameterValues();
            final StringBuilder       definition = new StringBuilder(200);
            definition.append("+proj=").append(ResourcesLoader.getProjName(parameters, false).substring(1));
            boolean hasSemiMajor = false;
            boolean hasSemiMinor = false;
            for (final GeneralParameterValue parameter : parameters.values()) {
                if (parameter instanceof ParameterValue) {
                    final Object value = ((ParameterValue) parameter).getValue();
                    if (value != null) {
                        final String pn = ResourcesLoader.getProjName(parameter, true);
                        if (pn.equals("+a")) hasSemiMajor = true;
                        if (pn.equals("+b")) hasSemiMinor = true;
                        definition.append(' ').append(pn).append('=').append(value);
                    }
                }
            }
            if (!hasSemiMajor) definition.append(" +a=").append(ellipsoid.getSemiMajorAxis());
            if (!hasSemiMinor) definition.append(" +b=").append(ellipsoid.getSemiMinorAxis());
            appendPrimeMeridian (definition, datum.getPrimeMeridian());
            appendAxisDirections(definition.append(' ').append(AXIS_ORDER_PARAM), derivedCS, Math.min(dimension, 3));
            appendAxisDirections(definition.append(AXIS_ORDER_SEPARATOR), baseCS, Math.min(baseCS.getDimension(), 3));
            final PJCRS.Projected crs;
            try {
                crs = (PJCRS.Projected) createCRS(name, datum.getName(), definition.toString(), dimension);
            } catch (IllegalArgumentException e) {
                throw new FactoryException(e.getMessage(), e);
            }
            if (baseCRS instanceof PJCRS.Geographic) {
                crs.baseCRS = (PJCRS.Geographic) baseCRS;
            }
            return crs;
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public GeocentricCRS createGeocentricCRS (Map<String,?> properties, GeodeticDatum datum, SphericalCS cs) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public VerticalCRS createVerticalCRS(Map<String,?> properties, VerticalDatum datum, VerticalCS cs) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public TemporalCRS createTemporalCRS(Map<String, ?> properties, TemporalDatum datum, TimeCS cs) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public ParametricCRS createParametricCRS(Map<String, ?> properties, ParametricDatum datum, ParametricCS cs) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public ImageCRS createImageCRS(Map<String, ?> properties, ImageDatum datum, AffineCS cs) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public EngineeringCRS createEngineeringCRS(Map<String, ?> properties, EngineeringDatum datum, CoordinateSystem cs) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public DerivedCRS createDerivedCRS(Map<String, ?> properties, CoordinateReferenceSystem baseCRS, Conversion conversionFromBase, CoordinateSystem derivedCS) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public CompoundCRS createCompoundCRS(Map<String, ?> properties, CoordinateReferenceSystem... elements) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public CoordinateReferenceSystem createFromXML(String xml) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException always thrown.
         */
        @Override
        public CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException {
            throw unsupportedOperation();
        }
    }

    /**
     * A factory for {@linkplain CoordinateReferenceSystem Coordinate Reference System} objects
     * created from EPSG codes. While this factory is primarily designed for EPSG codes, it
     * accepts also any other codespaces supported by the Proj.4 library.
     *
     * <p>The main methods in this class are:</p>
     * <ul>
     *   <li>{@link #getAuthority()}</li>
     *   <li>{@link #createCoordinateReferenceSystem(String)}</li>
     * </ul>
     *
     * The following methods delegate to {@link #createCoordinateReferenceSystem(String)} and cast
     * the result if possible, or throw a {@link FactoryException} otherwise.
     * <ul>
     *   <li>{@link #createGeographicCRS(String)}</li>
     *   <li>{@link #createGeocentricCRS(String)}</li>
     *   <li>{@link #createProjectedCRS(String)}</li>
     *   <li>{@link #createObject(String)}</li>
     * </ul>
     *
     * All other methods are not supported by the default implementation of this factory.
     * However those methods will work if the {@link #createCoordinateReferenceSystem(String)}
     * method is overridden in order to return CRS objects of the appropriate type.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class EPSG extends PJFactory implements CRSAuthorityFactory {
        /**
         * {@code true} if the CRS created by this factory should use the axis order
         * declared by the EPSG database. This is the default value.
         */
        private final boolean useEpsgAxisOrder;

        /**
         * The set of all EPSG codes known to Proj.4, created when first needed.
         */
        private Set<String> codes;

        /**
         * Creates a new coordinate operation factory which will create CRS with axis order
         * as declared in the EPSG database.
         */
        public EPSG() {
            useEpsgAxisOrder = true;
        }

        /**
         * Creates a new coordinate operation factory. Whether the factory will follow
         * EPSG axis order or not is specified by the given {@code useEpsgAxisOrder} argument.
         *
         * @param useEpsgAxisOrder {@code true} if the CRS created by this factory should
         *        use the axis order declared by the EPSG database, or {@code false} for
         *        the Proj.4 axis order. The default value is {@code true}.
         */
        public EPSG(final boolean useEpsgAxisOrder) {
            this.useEpsgAxisOrder = useEpsgAxisOrder;
        }

        /**
         * Returns the authority for this factory, which is EPSG. This is actually the default
         * authority when no codespace is explicitly given to a {@code createFoo(String)}
         * method. If a codespace is explicitely given, any authority recognized by the Proj.4
         * library will be accepted.
         */
        @Override
        public Citation getAuthority() {
            return SimpleCitation.EPSG;
        }

        /**
         * Returns the authority codes.
         *
         * @throws FactoryException if an error occurred while fetching the authority codes.
         */
        @Override
        @SuppressWarnings("ReturnOfCollectionOrArrayField")
        public synchronized Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
            if (codes == null) {
                codes = Collections.unmodifiableSet(ResourcesLoader.getAxisOrientations().keySet());
            }
            return codes;
        }

        /**
         * Returns the name of the CRS identified by the given code. The default implementation
         * returns a non-null value only for a few common codes.
         *
         * @throws FactoryException if an error occurred while fetching the description.
         */
        @Override
        public InternationalString getDescriptionText(final String code) throws FactoryException {
            final String name = getName(code, null, false);
            return (name != null) ? new SimpleCitation(code) : null;
        }

        /**
         * Returns a hard-coded name for the given code, or {@code null} if none.
         * Only the most frequent CRS are recognized by this method.
         *
         * @param isDatum  {@code false} for creating a CRS name (the usual case), or
         *                 {@code true} for creating a datum name.
         */
        private static String getName(String code, final String defaultValue, final boolean isDatum) {
            final int s = code.indexOf(':');
            if (s<0 || code.substring(0,s).trim().equalsIgnoreCase("epsg")) try {
                switch (Integer.parseInt(code.substring(s+1).trim())) {
                    case 4326: return isDatum ? "World Geodetic System 1984" : "WGS 84";
                }
            } catch (NumberFormatException e) {
                // Ignore - this is okay for this method contract.
            }
            return defaultValue;
        }

        /**
         * Creates a new CRS from the given code. If the given string is of the form
         * {@code "AUTHORITY:CODE"}, then any authority recognized by the Proj.4 library will be
         * accepted (it doesn't need to be EPSG). If no authority is given, then {@code "EPSG:"}
         * is assumed.
         *
         * @param  code  the code of the CRS object to create.
         * @return a CRS created from the given code.
         * @throws FactoryException if the CRS object can not be created for the given code.
         */
        @Override
        public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
            String codespace = "EPSG";
            code = code.trim();
            final int s = code.indexOf(':');
            if (s >= 0) {
                codespace = code.substring(0, s).trim();
                code = code.substring(s+1).trim();
            }
            int dimension = 2;
            final StringBuilder definition = new StringBuilder(40);
            definition.append("+init=").append(codespace).append(':').append(code);
            if (useEpsgAxisOrder) {
                /*
                 * If the user asked to honor the EPSG axis definitions, get the axis orientation
                 * from the "axis-orientations.txt" file.   This may be a comma-separated list if
                 * there is also a definition for the base CRS. It may be 2 or 3 characters long.
                 * The number of characters determine the number of dimensions. However this will
                 * have to be adjusted before to be given to Proj.4 since the later expects
                 * exactly 3 characters.
                 */
                String orientation = ResourcesLoader.getAxisOrientations().get(code);
                if (orientation != null) {
                    definition.append(' ').append(AXIS_ORDER_PARAM).append(orientation);
                    final int end = orientation.indexOf(AXIS_ORDER_SEPARATOR);
                    dimension = (end >= 0) ? end : orientation.length();
                }
            }
            final String crsName   = getName(code, code,   false);
            final String datumName = getName(code, crsName, true);
            final Identifier crsId = createIdentifier(codespace, crsName);
            final Identifier datumId = datumName.equals(crsName) ? crsId : createIdentifier(codespace, datumName);
            try {
                return createCRS(crsId, datumId, definition.toString(), dimension);
            } catch (IllegalArgumentException e) {
                throw new NoSuchAuthorityCodeException(e.getMessage(), codespace, code);
            }
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public GeographicCRS createGeographicCRS(String code) throws FactoryException {
            return cast(GeographicCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
            return cast(GeocentricCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
            return cast(ProjectedCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public CompoundCRS createCompoundCRS(String code) throws FactoryException {
            return cast(CompoundCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public DerivedCRS createDerivedCRS(String code) throws FactoryException {
            return cast(DerivedCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
            return cast(EngineeringCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public ImageCRS createImageCRS(String code) throws FactoryException {
            return cast(ImageCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public TemporalCRS createTemporalCRS(String code) throws FactoryException {
            return cast(TemporalCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public VerticalCRS createVerticalCRS(String code) throws FactoryException {
            return cast(VerticalCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public ParametricCRS createParametricCRS(String code) throws FactoryException {
            return cast(ParametricCRS.class, code);
        }

        /**
         * Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result.
         *
         * @throws FactoryException if {@code createCoordinateReferenceSystem(code)} failed.
         */
        @Override
        public IdentifiedObject createObject(String code) throws FactoryException {
            return createCoordinateReferenceSystem(code);
        }

        /**
         * Invokes {@link #createCoordinateReferenceSystem(String)} and casts the result
         * to the given type. If the result can not be casted, a factory exception is thrown.
         */
        private <T extends CoordinateReferenceSystem> T cast(final Class<T> type, final String code) throws FactoryException {
            final CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
            try {
                return type.cast(crs);
            } catch (ClassCastException e) {
                throw new FactoryException("The \"" + code + "\" object is not a " + type.getSimpleName(), e);
            }
        }
    }

    /**
     * A factory for {@linkplain CoordinateOperation Coordinate Operation} objects created from
     * source and target CRS. Current implementation accepts only CRS objects created by a
     * {@link PJFactory}.
     *
     * <p>The only supported methods are:</p>
     * <ul>
     *   <li>{@link #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)}</li>
     *   <li>{@link #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem, OperationMethod)}</li>
     * </ul>
     *
     * All other methods unconditionally throw a {@link FactoryException}.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Operation extends PJFactory implements CoordinateOperationFactory {
        /**
         * Creates a new coordinate operation factory.
         */
        public Operation() {
        }

        /**
         * Creates an operation for conversion or transformation between two coordinate reference
         * systems. This given source and target CRS must be instances created by {@link PJFactory}
         * or {@link PJFactory.EPSG}.
         *
         * @param  sourceCRS  the source coordinate reference system.
         * @param  targetCRS  the target coordinate reference system.
         * @return a coordinate operation for transforming coordinates from the given source CRS to the given target CRS.
         * @throws FactoryException if the given CRS are not instances recognized by this class.
         */
        @Override
        public CoordinateOperation createOperation(final CoordinateReferenceSystem sourceCRS,
                                                   final CoordinateReferenceSystem targetCRS)
                throws FactoryException
        {
            Identifier id;
            String src=null, tgt=null, space=null;
            if ((id = sourceCRS.getName()) != null) {
                src = id.getCode();
                space = id.getCodeSpace();
            }
            if ((id = targetCRS.getName()) != null) {
                tgt = id.getCode();
                if (space == null) {
                    space = id.getCodeSpace();
                }
            }
            id = null;
            if (src != null || tgt != null) {
                final StringBuilder buffer = new StringBuilder();
                if (src != null) buffer.append("From ").append(src);
                if (tgt != null) buffer.append(buffer.length() == 0 ? "To " : " to ").append(tgt);
                id = createIdentifier(space, buffer.toString());
            }
            try {
                return createOperation(id, sourceCRS, targetCRS);
            } catch (ClassCastException e) {
                throw new FactoryException("The CRS must be instances created by PJFactory.", e);
            }
        }

        /**
         * Ignores the given {@code method} argument and delegates to
         * <code>{@linkplain #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)
         * createOperation}(sourceCRS, targetCRS)</code>.
         *
         * @param  sourceCRS  the source coordinate reference system.
         * @param  targetCRS  the target coordinate reference system.
         * @return a coordinate operation for transforming coordinates from the given source CRS to the given target CRS.
         * @throws FactoryException if the given CRS are not instances recognized by this class.
         */
        @Override
        public CoordinateOperation createOperation(final CoordinateReferenceSystem sourceCRS,
                                                   final CoordinateReferenceSystem targetCRS,
                                                   final OperationMethod method)
                throws FactoryException
        {
            return createOperation(sourceCRS, targetCRS);
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public CoordinateOperation createConcatenatedOperation(Map<String,?> properties,
                CoordinateOperation... operations) throws FactoryException
        {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public Conversion createDefiningConversion(Map<String,?> properties,
                OperationMethod method, ParameterValueGroup parameters) throws FactoryException
        {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public OperationMethod createOperationMethod(Map<String,?> properties, Integer sourceDimension,
                Integer targetDimension, ParameterDescriptorGroup parameters) throws FactoryException
        {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public OperationMethod getOperationMethod(String name) throws FactoryException {
            throw unsupportedOperation();
        }
    }

    /**
     * A factory for {@linkplain MathTransform Math Transform} objects created from a list
     * of parameters.
     *
     * <p>The only supported methods are:</p>
     * <ul>
     *   <li>{@link #getAvailableMethods(Class)}</li>
     *   <li>{@link #getDefaultParameters(String)} - only partially implemented</li>
     *   <li>{@link #createParameterizedTransform(ParameterValueGroup)}</li>
     *   <li>{@link #createAffineTransform(Matrix)}</li>
     *   <li>{@link #createConcatenatedTransform(MathTransform, MathTransform)}</li>
     * </ul>
     *
     * All other methods unconditionally throw a {@link FactoryException}, or return
     * {@code null} when doing so is allowed.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Transform extends PJFactory implements MathTransformFactory {
        /**
         * Creates a new coordinate operation factory.
         */
        public Transform() {
        }

        /**
         * Returns the Proj.4 names of all projections supported by this class.
         */
        @Override
        public Set<OperationMethod> getAvailableMethods(Class<? extends SingleOperation> type) {
            if (type.isAssignableFrom(Projection.class)) try {
                return ResourcesLoader.getMethods();
            } catch (FactoryException e) { // Should never happen, unless there is an I/O error.
                throw new MissingResourceException(e.getLocalizedMessage(), ResourcesLoader.PROJECTIONS_FILE, "<all>");
            } else {
                return Collections.emptySet();
            }
        }

        /**
         * Unconditionally returns {@code null}, since this functionality is not supported yet.
         */
        @Override
        public OperationMethod getLastMethodUsed() {
            return null;
        }

        /**
         * Returns the parameter group for the given projection. The {@code method} argument can
         * be the Proj.4 projection name, or one of its aliases. This method does not check the
         * validity of the given argument, and the returned group does not enumerate the actual
         * list of valid parameters, because Proj.4 does not supply this information.
         */
        @Override
        public ParameterValueGroup getDefaultParameters(final String method) {
            try {
                return new PJParameterGroup(new PJIdentifier(method), ResourcesLoader.getAliases(method, false));
            } catch (FactoryException e) { // Should never happen, unless there is an I/O error.
                throw new MissingResourceException(e.getLocalizedMessage(), ResourcesLoader.PROJECTIONS_FILE, method);
            }
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS,
                ParameterValueGroup parameters, CoordinateSystem derivedCS) throws FactoryException
        {
            throw unsupportedOperation();
        }

        /**
         * Creates a math transform from the given Proj.4 parameters.
         *
         * @throws FactoryException if an error occurred while creating the transform.
         */
        @Override
        public MathTransform createParameterizedTransform(final ParameterValueGroup parameters) throws FactoryException {
            final StringBuilder definition = new StringBuilder(200);
            definition.append("+proj=").append(ResourcesLoader.getProjName(parameters, false).substring(1));
            for (final GeneralParameterValue parameter : parameters.values()) {
                if (parameter instanceof ParameterValue) {
                    final Object value = ((ParameterValue) parameter).getValue();
                    if (value != null) {
                        final String pn = ResourcesLoader.getProjName(parameter, true);
                        definition.append(' ').append(pn).append('=').append(value);
                    }
                }
            }
            final Identifier id = parameters.getDescriptor().getName();
            final CoordinateReferenceSystem targetCRS = createCRS(id, id, definition.toString(), 2);
            final CoordinateReferenceSystem sourceCRS = (targetCRS instanceof ProjectedCRS)
                    ? ((ProjectedCRS) targetCRS).getBaseCRS()
                    : createCRS(PJIdentifier.WGS84, PJIdentifier.WGS84, "+init=epsg:4326", 2);
            return createOperation(id, sourceCRS, targetCRS).getMathTransform();
        }

        /**
         * Creates an affine transform from a matrix. If the transform input dimension is {@code M},
         * and output dimension is {@code N}, then the matrix will have size {@code [N+1][M+1]}.
         * The {@code [i][N]} element of the matrix must be 0 for <var>i</var> less than {@code M},
         * and 1 for <var>i</var> equals {@code M}.
         *
         * @param  matrix  the matrix used to define the affine transform.
         * @return the affine transform.
         * @throws FactoryException if the object creation failed.
         */
        @Override
        public MathTransform createAffineTransform(final Matrix matrix) throws FactoryException {
            return Affine.create(matrix);
        }

        /**
         * Creates a transform by concatenating two existing transforms.
         * A concatenated transform acts in the same way as applying two
         * transforms, one after the other.
         *
         * <p>This implementation can only concatenate two affine transforms,
         * or to Proj.4 transforms. All other cases are unsupported.</p>
         *
         * @param  transform1  the first transform to apply to points.
         * @param  transform2  the second transform to apply to points.
         * @return the concatenated transform.
         * @throws FactoryException if the object creation failed.
         */
        @Override
        public MathTransform createConcatenatedTransform(final MathTransform transform1,
                final MathTransform transform2) throws FactoryException
        {
            if (transform1 instanceof AffineTransform && transform2 instanceof AffineTransform) {
                final Affine c = new Affine((AffineTransform) transform1);
                c.preConcatenate((AffineTransform) transform2);
                return c;
            }
            final PJCRS sourceCRS, targetCRS;
            try {
                sourceCRS = ((PJOperation) transform1).source;
                targetCRS = ((PJOperation) transform2).target;
            } catch (ClassCastException e) {
                throw new FactoryException(e);
            }
            return new PJOperation(null, sourceCRS, targetCRS);
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public MathTransform createPassThroughTransform(int firstAffectedOrdinate, MathTransform subTransform, int numTrailingOrdinates) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        @Deprecated
        public MathTransform createFromXML(String xml) throws FactoryException {
            throw unsupportedOperation();
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         *
         * @throws FactoryException alway thrown.
         */
        @Override
        public MathTransform createFromWKT(String wkt) throws FactoryException {
            throw unsupportedOperation();
        }
    }
}
