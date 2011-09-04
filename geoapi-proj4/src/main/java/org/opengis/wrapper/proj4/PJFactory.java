/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.wrapper.proj4;

import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.proj4.PJ;

import static org.proj4.PJ.DIMENSION_MAX;


/**
 * The base class for factories of Proj4 wrappers. This base class provides static methods working
 * directly with the Proj.4 definition strings. Subclasses provides implementation of GeoAPI
 * factory interfaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJFactory implements Factory {
    /**
     * The Proj4 parameter used for declaration of axis order. This parameter is handled in a special
     * way by this factory: it be a comma-separated list of axis order definitions, in which case the
     * second value is used as the axis order of the {@link ProjectedCRS#getBaseCRS()}.
     * <p>
     * An other departure from Proj.4 is that Proj.4 expect the axis parameter to be exactly
     * 3 characters long, which our code accepts 2 characters as well. We relax the Proj.4
     * rule because we use the number of characters for determining the number of dimensions.
     * This is okay since 1 character = 1 axis.
     */
    static final String AXIS_ORDER_PARAM = "+axis=";

    /**
     * The character used for separating the Proj4 axis order declarations.
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
     * Creates a simple identifier from the given code and codespace. Identifiers are code
     * under the control of some authority (the code space). For example a widely used
     * identifier is {@code EPSG:4326}.
     * <p>
     * In principle, every ISO 19111 compliant {@linkplain org.opengis.referencing.IdentifiedObject
     * identified object} must have an identifier. However this {@code proj4} package is not strict
     * about that. Users are nevertheless encouraged to use this method for creating an identifier
     * before to invoke the other static methods in this {@code PJFactory} class.
     *
     * @param  codespace  The code space (for example {@code "EPSG"}), or {@code null} if none.
     * @param  code The code, for example {@code "4326"}.
     * @return A reference identifier for the given code space and code.
     * @throws NullPointerException If the code argument is {@code null}.
     * @throws IllegalArgumentException If any of the given argument is an empty string.
     */
    public static ReferenceIdentifier createIdentifier(String codespace, String code) {
        if ((code = code.trim()).isEmpty() || (codespace != null && (codespace = codespace.trim()).isEmpty())) {
            throw new IllegalArgumentException("Codespace and code must be non-empty.");
        }
        return new PJIdentifier(codespace, code);
    }

    /**
     * Creates a new CRS from the given Proj4 definition string. The CRS can have an arbitrary
     * number of dimensions in the [2-{@value org.proj4.PJ#DIMENSION_MAX}] range. However Proj.4
     * will handle at most the 3 first dimensions. All supplemental dimensions will be simply
     * copied unchanged by {@link MathTransform} implementations.
     *
     * @param  identifier The name of the CRS is create, or {@code null} if none.
     * @param  definition The Proj.4 definition string.
     * @param  dimension  The number of dimension of the CRS to create.
     * @return A CRS created from the given definition string and number of dimension.
     * @throws NullPointerException If the definition string is {@code null}.
     * @throws IllegalArgumentException If one of the given argument has an invalid value.
     */
    public static CoordinateReferenceSystem createCRS(final ReferenceIdentifier identifier,
            String definition, final int dimension) throws IllegalArgumentException
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
            while (beginParam<length && Character.isWhitespace(definition.charAt(beginParam))) {
                beginParam++; // Skip whitespaces.
            }
            final StringBuilder modified = new StringBuilder(definition.length());
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
        final PJDatum datum = new PJDatum(identifier, definition);
        final PJ.Type type = datum.getType();
        final CoordinateReferenceSystem crs;
        switch (type) {
            case GEOCENTRIC: crs = new PJCRS.Geocentric(identifier, datum, dimension); break;
            case GEOGRAPHIC: crs = new PJCRS.Geographic(identifier, datum, dimension); break;
            case PROJECTED:  crs = new PJCRS.Projected (identifier, datum, dimension, orientation); break;
            default: throw new UnsupportedOperationException("Unknown CRS type: " + type);
        }
        return crs;
    }

    /**
     * Creates an operation for conversion or transformation between two coordinate reference systems.
     * This given source and target CRS must be instances created by this factory.
     *
     * @param  identifier The name of the operation to create, or {@code null} if none.
     * @param  sourceCRS  The source coordinate reference system.
     * @param  targetCRS  The target coordinate reference system.
     * @return A coordinate operation for transforming coordinates from the given source CRS
     *         to the given target CRS.
     * @throws ClassCastException If the given CRS are not instances created by this class.
     */
    public static CoordinateOperation createOperation(final ReferenceIdentifier identifier,
            final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem targetCRS)
            throws ClassCastException
    {
        return new PJOperation(identifier, (PJCRS) sourceCRS, (PJCRS) targetCRS);
    }

    /**
     * A factory for {@linkplain CoordinateReferenceSystem Coordinate Reference System} objects
     * created from an EPSG code. While this factory is primarily designed for EPSG codes, it
     * accepts also any other codespaces supported by the Proj.4 library.
     * <p>
     * The main methods of this class are:
     * <p>
     * <ul>
     *   <li>{@link #getAuthority()}</li>
     *   <li>{@link #createCoordinateReferenceSystem(String)}</li>
     * </ul>
     * <p>
     * The following methods delegate to {@link #createCoordinateReferenceSystem(String)} and cast
     * the result if possible, or throw a {@link FactoryException} otherwise.
     * <ul>
     *   <li>{@link #createGeographicCRS(String)}</li>
     *   <li>{@link #createGeocentricCRS(String)}</li>
     *   <li>{@link #createProjectedCRS(String)}</li>
     *   <li>{@link #createObject(String)}</li>
     * </ul>
     * <p>
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
         * The file which contains the axis orientations for each CRS code.
         */
        static final String AXIS_FILE = "axis-orientations.txt";

        /**
         * {@code true} if the CRS created by this factory should use the axis order
         * declared by the EPSG database. This is the default value.
         */
        private final boolean useEpsgAxisOrder;

        /**
         * The map of axis orientations for each CRS codes.
         * This map will be loaded from the {@value #AXIS_FILE} file when first needed.
         */
        private Map<String,String> axisOrientations;

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
         * Creates a new coordinate operation factory. Whatever the factory will follow
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
         * authority when no codespace is explicitely given to a {@code createFoo(String)}
         * method. If a codespace is explicitely given, any authority recognized by the Proj.4
         * library will be accepted.
         */
        @Override
        public Citation getAuthority() {
            return SimpleCitation.EPSG;
        }

        /**
         * Returns the axis orientation map. Callers shall not modify the returned map.
         * The file format is the one created by {@link SupportedCodes#write()} in the
         * test directory.
         *
         * @throws FactoryException If the resource file can not be loaded.
         */
        private synchronized Map<String,String> getAxisOrientations() throws FactoryException {
            if (axisOrientations != null) {
                return axisOrientations;
            }
            IOException cause = null;
            final InputStream in = PJFactory.class.getResourceAsStream(AXIS_FILE);
            if (in != null) try {
                final Map<String,String> map = new LinkedHashMap<String,String>(5000);
                final BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if ((line = line.trim()).isEmpty()) {
                        continue; // Skip empty lines.
                    }
                    switch (line.charAt(0)) {
                        case '#': {
                            // A line of comment. Ignore.
                            break;
                        }
                        case '[': {
                            // The authority. Actually we don't parse yet
                            // this element. Maybe a future version will do.
                            break;
                        }
                        default: {
                            int s = line.indexOf(':');
                            final String orientation = line.substring(0, s).trim();
                            do {
                                final int p = s+1;
                                s = line.indexOf(' ', p);
                                final String code = (s >= 0) ? line.substring(p,s) : line.substring(p);
                                map.put(code.trim(), orientation);
                            } while (s >= 0);
                            break;
                        }
                    }
                }
                reader.close();
                return axisOrientations = map;
            } catch (IOException e) {
                cause = e;
            }
            throw new FactoryException("Can not read the \"" + AXIS_FILE + "\" resource", cause);
        }

        /**
         * Unconditionally returns {@code null}, since this functionality is not supported yet.
         * Note that {@code null} is not the same than an empty set, since the later would mean
         * that there is no supported code.
         */
        @Override
        public synchronized Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
            if (codes == null) {
                codes = Collections.unmodifiableSet(getAxisOrientations().keySet());
            }
            return codes;
        }

        /**
         * Returns the name of the CRS identified by the given code. The default implementation
         * returns a non-null value only for a few common codes.
         */
        @Override
        public InternationalString getDescriptionText(final String code) throws FactoryException {
            final String name = getName(code);
            return (name != null) ? new SimpleCitation(code) : null;
        }

        /**
         * Returns a hard-coded name for the given code, or {@code null} if none.
         * Only the most frequent CRS are recognized by this method.
         */
        private static String getName(String code) {
            final int s = code.indexOf(':');
            if (s<0 || code.substring(0,s).trim().equalsIgnoreCase("epsg")) try {
                switch (Integer.parseInt(code.substring(s+1).trim())) {
                    case 4326: return "WGS84";
                }
            } catch (NumberFormatException e) {
                // Ignore - this is okay for this method contract.
            }
            return null;
        }

        /**
         * Creates a new CRS from the given code. If the given string is of the form
         * {@code "AUTHORITY:CODE"}, then any authority recognized by the Proj.4 library will be
         * accepted (it doesn't need to be EPSG). If no authority is given, then {@code "EPSG:"}
         * is assumed.
         *
         * @param  code The code of the CRS object to create.
         * @return A CRS created from the given code.
         * @throws FactoryException If the CRS object can not be created for the given code.
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
                //
                // If the user asked to honor the EPSG axis definitions, get the axis orientation
                // from the "axis-orientations.txt" file.   This may be a comma-separated list if
                // there is also a definition for the base CRS. It may be 2 or 3 characters long.
                // The number of characters determine the number of dimensions. However this will
                // have to be adjusted before to be given to Proj.4 since the later expects
                // exactly 3 characters.
                //
                String orientation = getAxisOrientations().get(code);
                if (orientation != null) {
                    definition.append(' ').append(AXIS_ORDER_PARAM).append(orientation);
                    final int end = orientation.indexOf(AXIS_ORDER_SEPARATOR);
                    dimension = (end >= 0) ? end : orientation.length();
                }
            }
            final String name = getName(code);
            if (name != null) {
                code = name;
            }
            try {
                return createCRS(createIdentifier(codespace, code), definition.toString(), dimension);
            } catch (IllegalArgumentException e) {
                throw new NoSuchAuthorityCodeException(e.getMessage(), codespace, code);
            }
        }

        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public GeographicCRS    createGeographicCRS (String code) throws FactoryException {return cast(GeographicCRS .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public GeocentricCRS    createGeocentricCRS (String code) throws FactoryException {return cast(GeocentricCRS .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public ProjectedCRS     createProjectedCRS  (String code) throws FactoryException {return cast(ProjectedCRS  .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public CompoundCRS      createCompoundCRS   (String code) throws FactoryException {return cast(CompoundCRS   .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public DerivedCRS       createDerivedCRS    (String code) throws FactoryException {return cast(DerivedCRS    .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public EngineeringCRS   createEngineeringCRS(String code) throws FactoryException {return cast(EngineeringCRS.class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public ImageCRS         createImageCRS      (String code) throws FactoryException {return cast(ImageCRS      .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public TemporalCRS      createTemporalCRS   (String code) throws FactoryException {return cast(TemporalCRS   .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)} and casts the result. */ @Override public VerticalCRS      createVerticalCRS   (String code) throws FactoryException {return cast(VerticalCRS   .class, code);}
        /** Delegates to {@link #createCoordinateReferenceSystem(String)}. */                      @Override public IdentifiedObject createObject        (String code) throws FactoryException {return createCoordinateReferenceSystem(code);}

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
     * A factory for {@linkplain CoordinateOperation coordinate operation} objects created from
     * a source and a target CRS. Current implementation accepts only CRS objects created by
     * {@link PJFactory} or {@link PJFactory.EPSG}.
     * <p>
     * The only supported methods are:
     * <p>
     * <ul>
     *   <li>{@link #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)}</li>
     *   <li>{@link #createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem, OperationMethod)}</li>
     * </ul>
     * <p>
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
         * @param  sourceCRS The source coordinate reference system.
         * @param  targetCRS The target coordinate reference system.
         * @return A coordinate operation for transforming coordinates from the given source CRS
         *         to the given target CRS.
         * @throws FactoryException If the given CRS are not instances recognized by this class.
         */
        @Override
        public CoordinateOperation createOperation(final CoordinateReferenceSystem sourceCRS,
                                                   final CoordinateReferenceSystem targetCRS)
                throws FactoryException
        {
            ReferenceIdentifier id;
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
         * @param  sourceCRS The source coordinate reference system.
         * @param  targetCRS The target coordinate reference system.
         * @return A coordinate operation for transforming coordinates from the given source CRS
         *         to the given target CRS.
         * @throws FactoryException If the given CRS are not instances recognized by this class.
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
         */
        @Override
        public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties,
                CoordinateOperation... operations) throws FactoryException
        {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public Conversion createDefiningConversion(Map<String, ?> properties,
                OperationMethod method, ParameterValueGroup parameters) throws FactoryException
        {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public OperationMethod createOperationMethod(Map<String, ?> properties,
                Integer sourceDimension, Integer targetDimension, ParameterDescriptorGroup parameters) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }

        /**
         * Unconditionally throw an exception, since this functionality is not supported yet.
         */
        @Override
        public OperationMethod getOperationMethod(String name) throws FactoryException {
            throw new FactoryException("Not supported yet.");
        }
    }
}
