/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Objects;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.opengis.parameter.*;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.test.util.PseudoFactory;
import org.opengis.test.ValidatorContainer;
import org.opengis.test.Units;

import static org.junit.Assert.*;
import static org.junit.Assume.*;


/**
 * Creates referencing objects for a limited set of hard-coded EPSG codes
 * using {@link ObjectFactory} and {@link MathTransformFactory}. This pseudo-factory
 * can be used with implementation that do not support (or don't want to test) a "real"
 * {@link CRSAuthorityFactory} for the EPSG database.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class PseudoEpsgFactory extends PseudoFactory implements DatumAuthorityFactory,
        CSAuthorityFactory, CRSAuthorityFactory
{
    /**
     * The reciprocal of the conversion from US feets to metres.
     */
    static final double R_US_FEET = 3.2808333333333333333;

    /**
     * Conversion from Clarke's 1865 feet to metres.
     */
    static final double CLARKE_FEET = 0.3047972654;

    /**
     * Conversion from feet to metres.
     */
    static final double FEET = 0.3048;

    /**
     * Conversion from links to metres
     */
    static final double LINKS = 0.66 * FEET;

    /**
     * Provider of predefined {@link Unit} instances (degree, metre, second, <i>etc</i>).
     */
    protected final Units units;

    /**
     * Factory to use for building {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumFactory datumFactory;

    /**
     * Factory to use for building {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSFactory csFactory;

    /**
     * Factory to use for building {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSFactory crsFactory;

    /**
     * Factory to use for building {@link Conversion} instances, or {@code null} if none.
     */
    protected final CoordinateOperationFactory copFactory;

    /**
     * Factory to use for building {@link MathTransform} instances, or {@code null} if none.
     */
    protected final MathTransformFactory mtFactory;

    /**
     * The set of validators to use for verifying objects conformance (never {@code null}).
     */
    protected final ValidatorContainer validators;

    /**
     * Creates a new pseudo-factory which will use the given factories.
     *
     * @param  units         provider of predefined {@link Unit} instances.
     * @param  datumFactory  factory for creating {@link Datum} instances.
     * @param  csFactory     factory for creating {@link CoordinateSystem} instances.
     * @param  crsFactory    factory for creating {@link CoordinateReferenceSystem} instances.
     * @param  copFactory    factory for creating {@link Conversion} instances.
     * @param  mtFactory     factory for creating {@link MathTransform} instances.
     * @param  validators    the set of validators to use for verifying objects conformance,
     *                       Cannot be {@code null}; if there is no particular validators,
     *                       use {@link org.opengis.test.Validators#DEFAULT}.
     */
    public PseudoEpsgFactory(
            final Units                           units,
            final DatumFactory             datumFactory,
            final CSFactory                   csFactory,
            final CRSFactory                 crsFactory,
            final CoordinateOperationFactory copFactory,
            final MathTransformFactory        mtFactory,
            final ValidatorContainer         validators)
    {
        this.units        = Objects.requireNonNull(units, "The units cannot be null. Do you mean Units.getDefault()?");
        this.datumFactory = datumFactory;
        this.csFactory    = csFactory;
        this.crsFactory   = crsFactory;
        this.copFactory   = copFactory;
        this.mtFactory    = mtFactory;
        this.validators   = Objects.requireNonNull(validators, "The validators cannot be null. Do you mean Validators.DEFAULT?");
    }

    /**
     * Returns the given EPSG code as an integer.
     *
     * @param  code  the EPSG code to parse.
     * @return the EPSG code as an integer.
     * @throws NoSuchAuthorityCodeException if the given code cannot be parsed as an integer.
     */
    private static int parseCode(String code) throws NoSuchAuthorityCodeException {
        final int s = code.lastIndexOf(':');
        if (s >= 0) {
            final String authority = code.substring(0, s).trim();
            if (!authority.equalsIgnoreCase("EPSG")) {
                throw new NoSuchAuthorityCodeException("Unsupported \"" + authority + "\" authority.", "EPSG", code);
            }
            code = code.substring(s+1).trim();
        }
        try {
            return Integer.parseInt(code);
        } catch (NumberFormatException cause) {
            NoSuchAuthorityCodeException e = new NoSuchAuthorityCodeException(
                    "Unparseable EPSG code: " + code, "EPSG", code);
            e.initCause(cause);
            throw e;
        }
    }

    /**
     * Creates the exception to be thrown when the given code has not been recognized.
     *
     * @param  code   the code as a numerical value.
     * @param  given  the code as given by the user.
     * @return the exception to throw.
     */
    private static NoSuchAuthorityCodeException noSuchAuthorityCode(final int code, final String given) {
        final String codeAsText = String.valueOf(code);
        return new NoSuchAuthorityCodeException("No case implemented for EPSG:" + codeAsText,
                "EPSG", codeAsText, given);
    }

    /**
     * The default implementation returns {@code null}.
     */
    @Override
    public Citation getAuthority() {
        return null;
    }

    /**
     * The default implementation returns an empty set.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     *
     * @todo Needs to be implemented.
     */
    @Override
    public Set<String> getAuthorityCodes(final Class<? extends IdentifiedObject> type) throws FactoryException {
        return Collections.emptySet();
    }

    /**
     * The default implementation returns {@code null}.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public InternationalString getDescriptionText(final String code) throws FactoryException {
        return null;
    }

    /**
     * Builds a map of properties for a referencing object to be built. The map shall contain
     * at least the {@link IdentifiedObject#NAME_KEY} identifier associated to the given value.
     * Subclasses can override this method in order to provide more information if they wish.
     *
     * @param  code  The EPSG code of the object being built.
     * @param  name  The name of the object being built.
     * @return a map containing the properties for the object to create.
     */
    protected Map<String,Object> createPropertiesMap(final int code, final String name) {
        final Map<String,Object> properties = new HashMap<>(4);
        assertNull(properties.put(IdentifiedObject.NAME_KEY, name));
        assertNull(properties.put(IdentifiedObject.IDENTIFIERS_KEY, new EPSGIdentifier(code)));
        return properties;
    }

    /**
     * Returns an arbitrary object from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>4326</td> <td>WGS 84</td></tr>
     *   <tr><td>6326</td> <td>World Geodetic System 1984</td></tr>
     *   <tr><td>6422</td> <td>Ellipsoidal 2D CS. Axes: latitude, longitude. Orientations: north, east. UoM: degree</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public IdentifiedObject createObject(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            case 6326: return createDatum(code);
            case 6422: return createCoordinateSystem(code);
            case 4326: return createCoordinateReferenceSystem(code);
            default:   throw noSuchAuthorityCode(id, code);
        }
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////                                 ///////////////////////////////
    ///////////////////////////////    D A T U M   F A C T O R Y    ///////////////////////////////
    ///////////////////////////////                                 ///////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns an arbitrary {@linkplain Datum datum} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>6326</td> <td>World Geodetic System 1984</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public Datum createDatum(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            case 6326: return createGeodeticDatum(code);
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public ImageDatum createImageDatum(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public VerticalDatum createVerticalDatum(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public TemporalDatum createTemporalDatum(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public ParametricDatum createParametricDatum(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * Returns a {@linkplain GeodeticDatum geodetic datum} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>6326</td> <td>World Geodetic System 1984</td></tr>
     *   <tr><td>6284</td> <td>Pulkovo 1942</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public GeodeticDatum createGeodeticDatum(final String code) throws FactoryException {
        final String name;
        final int ellipsoid;
        final int primeMeridian;
        final int id = parseCode(code);
        switch (id) {
            case 6326: name="World Geodetic System 1984"; ellipsoid=7030; primeMeridian=8901; break;
            case 6284: name="Pulkovo 1942";               ellipsoid=7024; primeMeridian=8901; break;
            default:   throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(datumFactory);
        final GeodeticDatum object = datumFactory.createGeodeticDatum(createPropertiesMap(id, name),
                createEllipsoid    (String.valueOf(ellipsoid)),
                createPrimeMeridian(String.valueOf(primeMeridian)));
        validators.validate(object);
        return object;
    }

    /**
     * Returns an {@linkplain Ellipsoid ellipsoid} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>7001</td> <td>Airy 1830</td></tr>
     *   <tr><td>7004</td> <td>Bessel 1841</td></tr>
     *   <tr><td>7011</td> <td>Clarke 1880 (IGN)</td></tr>
     *   <tr><td>7019</td> <td>GRS 1980</td></tr>
     *   <tr><td>7022</td> <td>International 1924</td></tr>
     *   <tr><td>7024</td> <td>Krassowsky 1940</td></tr>
     *   <tr><td>7030</td> <td>WGS 84</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the ellipsoid for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public Ellipsoid createEllipsoid(final String code) throws FactoryException {
        final String name;
        final double semiMajorAxis;
        double semiMinorAxis = Double.NaN;
        double inverseFlattening = Double.NaN;
        int    unitCode = 9001;                     // Default unit is metre.
        final int id = parseCode(code);
        switch (id) {
            case 7030: name="WGS 84";             semiMajorAxis=6378137;     inverseFlattening=298.257223563; break;
            case 7019: name="GRS 1980";           semiMajorAxis=6378137;     inverseFlattening=298.2572221;   break;
            case 7001: name="Airy 1830";          semiMajorAxis=6377563.396; inverseFlattening=299.3249646;   break;
            case 7004: name="Bessel 1841";        semiMajorAxis=6377397.155; inverseFlattening=299.1528128;   break;
            case 7024: name="Krassowsky 1940";    semiMajorAxis=6378245;     inverseFlattening=298.3;         break;
            case 7022: name="International 1924"; semiMajorAxis=6378388;     inverseFlattening=297;           break;
            case 7011: name="Clarke 1880 (IGN)";  semiMajorAxis=6378249.2;   semiMinorAxis=6356515;           break;
            default:   throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(datumFactory);
        final Map<String,?> properties = createPropertiesMap(id, name);
        final Unit<Length> unit = createUnit(String.valueOf(unitCode)).asType(Length.class);
        final Ellipsoid object;
        if (Double.isNaN(inverseFlattening)) {
            object = datumFactory.createEllipsoid(properties, semiMajorAxis, semiMinorAxis, unit);
        } else {
            object = datumFactory.createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, unit);
        }
        validators.validate(object);
        return object;
    }

    /**
     * Returns a {@linkplain PrimeMeridian prime meridian} from a EPSG code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>8901</td> <td>Greenwich</td></tr>
     *   <tr><td>8903</td> <td>Paris</td></tr>
     *   <tr><td>8908</td> <td>Jakarta</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the prime meridian for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public PrimeMeridian createPrimeMeridian(final String code) throws FactoryException {
        final String name;
        final double longitude;
        final int    unit;
        final int id = parseCode(code);
        switch (id) {
            case 8901: name="Greenwich"; longitude=  0.0;              unit=9102; break;
            case 8903: name="Paris";     longitude=  2.5969213;        unit=9105; break;
            case 8908: name="Jakarta";   longitude=106.80771944444444; unit=9102; break;
            default:   throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(datumFactory);
        final PrimeMeridian object = datumFactory.createPrimeMeridian(createPropertiesMap(id, name),
                longitude, createUnit(String.valueOf(unit)).asType(Angle.class));
        validators.validate(object);
        return object;
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////                                                         ///////////////////
    ///////////////////    C O O R D I N A T E   S Y S T E M   F A C T O R Y    ///////////////////
    ///////////////////                                                         ///////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns an arbitrary {@linkplain CoordinateSystem coordinate system} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>6422</td> <td>Ellipsoidal 2D CS. Axes: latitude, longitude. Orientations: north, east. UoM: degree</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public CoordinateSystem createCoordinateSystem(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            case 6422: return createEllipsoidalCS(code);
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * Creates a Cartesian coordinate system from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>6500</td> <td>Earth centred, earth fixed, righthanded 3D coordinate system,
     *     consisting of 3 orthogonal axes with X and Y axes in the equatorial plane,
     *     positive Z-axis parallel to mean earth rotation axis and pointing towards North Pole.
     *     UoM: m</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public CartesianCS createCartesianCS(final String code) throws FactoryException {
        final String name;
        final int axisCode0;
        final int axisCode1;
        final int axisCode2;
        final int id = parseCode(code);
        switch (id) {
            case 6500: {
                name = "Earth centred, earth fixed, righthanded 3D coordinate system, "
                     + "consisting of 3 orthogonal axes with X and Y axes in the equatorial plane, "
                     + "positive Z-axis parallel to mean earth rotation axis and pointing towards North Pole. "
                     + "UoM: m";
                axisCode0 = 115;        // Geocentric X
                axisCode1 = 116;        // Geocentric Y
                axisCode2 = 117;        // Geocentric Z
                break;
            }
            default: throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(csFactory);
        final Map<String,?>   properties = createPropertiesMap(id, name);
        final CoordinateSystemAxis axis0 = createCoordinateSystemAxis(String.valueOf(axisCode0));
        final CoordinateSystemAxis axis1 = createCoordinateSystemAxis(String.valueOf(axisCode1));
        final CartesianCS object;
        if (axisCode2 == 0) {
            object = csFactory.createCartesianCS(properties, axis0, axis1);
        } else {
            object = csFactory.createCartesianCS(properties, axis0, axis1,
                    createCoordinateSystemAxis(String.valueOf(axisCode2)));
        }
        validators.validate(object);
        return object;
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public PolarCS createPolarCS(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public CylindricalCS createCylindricalCS(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public SphericalCS createSphericalCS(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * Creates an ellipsoidal coordinate system from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>6403</td> <td>Ellipsoidal 2D CS. Axes: latitude, longitude. Orientations: north, east. UoM: grads.</td></tr>
     *   <tr><td>6422</td> <td>Ellipsoidal 2D CS. Axes: latitude, longitude. Orientations: north, east. UoM: degree</td></tr>
     *   <tr><td>6423</td> <td>Ellipsoidal 3D CS. Axes: latitude, longitude, ellipsoidal height. Orientations: north, east, up. UoM: degree, degree, metre.</td></tr>
     *   <tr><td>6424</td> <td>Ellipsoidal 2D CS. Axes: longitude, latitude. Orientations: east, north. UoM: degree</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate system for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public EllipsoidalCS createEllipsoidalCS(final String code) throws FactoryException {
        final String name;
        final int axisCode0;
        final int axisCode1;
              int axisCode2 = 0;
        final int id = parseCode(code);
        switch (id) {
            case 6403: {
                name = "Ellipsoidal 2D CS. Axes: latitude, longitude. "
                     + "Orientations: north, east. "
                     + "UoM: grads. ";
                axisCode0 = 58;         // Geodetic latitude
                axisCode1 = 59;         // Geodetic longitude
                break;
            }
            case 6422: {
                name = "Ellipsoidal 2D CS. Axes: latitude, longitude. "
                     + "Orientations: north, east. "
                     + "UoM: degree";
                axisCode0 = 106;        // Geodetic latitude
                axisCode1 = 107;        // Geodetic longitude
                break;
            }
            case 6423: {
                name = "Ellipsoidal 3D CS. Axes: latitude, longitude, ellipsoidal height. "
                     + "Orientations: north, east, up. "
                     + "UoM: degree, degree, metre.";
                axisCode0 = 108;        // Geodetic latitude
                axisCode1 = 109;        // Geodetic longitude
                axisCode2 = 110;        // Ellipsoidal height
                break;
            }
            case 6424: {
                name = "Ellipsoidal 2D CS. Axes: longitude, latitude. "
                     + "Orientations: east, north. "
                     + "UoM: degree";
                axisCode0 = 220;        // Geodetic longitude
                axisCode1 = 221;        // Geodetic latitude
                break;
            }
            default: throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(csFactory);
        final Map<String,?>   properties = createPropertiesMap(id, name);
        final CoordinateSystemAxis axis0 = createCoordinateSystemAxis(String.valueOf(axisCode0));
        final CoordinateSystemAxis axis1 = createCoordinateSystemAxis(String.valueOf(axisCode1));
        final EllipsoidalCS object;
        if (axisCode2 == 0) {
            object = csFactory.createEllipsoidalCS(properties, axis0, axis1);
        } else {
            object = csFactory.createEllipsoidalCS(properties, axis0, axis1,
                    createCoordinateSystemAxis(String.valueOf(axisCode2)));
        }
        validators.validate(object);
        return object;
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public VerticalCS createVerticalCS(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public TimeCS createTimeCS(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public ParametricCS createParametricCS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * Returns a {@linkplain CoordinateSystemAxis coordinate system axis} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th>          <th>Name</th>               <th>Unit</th></tr>
     *   <tr><td>58</td>            <td>Geodetic latitude</td>  <td>grad</td></tr>
     *   <tr><td>59</td>            <td>Geodetic longitude</td> <td>grad</td></tr>
     *   <tr><td>106, 108, 221</td> <td>Geodetic latitude</td>  <td>degree</td></tr>
     *   <tr><td>107, 109, 220</td> <td>Geodetic longitude</td> <td>degree</td></tr>
     *   <tr><td>110</td>           <td>Ellipsoidal height</td> <td>metre</td></tr>
     *   <tr><td>115</td>           <td>Geocentric X</td>       <td>metre</td></tr>
     *   <tr><td>116</td>           <td>Geocentric Y</td>       <td>metre</td></tr>
     *   <tr><td>117</td>           <td>Geocentric Z</td>       <td>metre</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the axis for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public CoordinateSystemAxis createCoordinateSystemAxis(final String code) throws FactoryException {
        final String name;
        final String abbreviation;
        final AxisDirection direction;
        final int unit;
        final int id = parseCode(code);
        switch (id) {
            case 108: case 221:
            case 106: name="Geodetic latitude";  abbreviation="Lat";  direction=AxisDirection.NORTH;        unit=9122; break;
            case  58: name="Geodetic latitude";  abbreviation="Lat";  direction=AxisDirection.NORTH;        unit=9105; break;
            case 109: case 220:
            case 107: name="Geodetic longitude"; abbreviation="Long"; direction=AxisDirection.EAST;         unit=9122; break;
            case  59: name="Geodetic longitude"; abbreviation="Long"; direction=AxisDirection.EAST;         unit=9105; break;
            case 110: name="Ellipsoidal height"; abbreviation="h";    direction=AxisDirection.UP;           unit=9001; break;
            case 115: name="Geocentric X";       abbreviation="X";    direction=AxisDirection.GEOCENTRIC_X; unit=9001; break;
            case 116: name="Geocentric Y";       abbreviation="Y";    direction=AxisDirection.GEOCENTRIC_Y; unit=9001; break;
            case 117: name="Geocentric Z";       abbreviation="Z";    direction=AxisDirection.GEOCENTRIC_Z; unit=9001; break;
            default:  throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(csFactory);
        final CoordinateSystemAxis object = csFactory.createCoordinateSystemAxis(createPropertiesMap(id, name),
                abbreviation, direction, createUnit(String.valueOf(unit)));
        validators.validate(object);
        return object;
    }

    /**
     * Returns an {@linkplain Unit unit} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>9001</td> <td>metre</td></tr>
     *   <tr><td>9102</td> <td>degree</td></tr>
     *   <tr><td>9105</td> <td>grad</td></tr>
     *   <tr><td>9122</td> <td>degree (supplier to define representation)</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the unit for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public Unit<?> createUnit(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            case 9001: return units.metre();
            case 9122: // Fall through
            case 9102: return units.degree();
            case 9105: return units.grad();
            default:   throw noSuchAuthorityCode(id, code);
        }
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////                                                                             /////////
    /////////    C O O R D I N A T E   R E F E R E N C E   S Y S T E M   F A C T O R Y    /////////
    /////////                                                                             /////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns an arbitrary {@linkplain CoordinateReferenceSystem coordinate reference system} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>4326</td> <td>WGS 84</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public CoordinateReferenceSystem createCoordinateReferenceSystem(final String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            case 4326: return createGeographicCRS(code);
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public CompoundCRS createCompoundCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public DerivedCRS createDerivedCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * Returns a {@linkplain GeographicCRS geographic coordinate reference system} from a code.
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th> <th>Name</th></tr>
     *   <tr><td>4326</td> <td>WGS 84</td></tr>
     *   <tr><td>4284</td> <td>Pulkovo 1942</td></tr>
     * </table>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws FactoryException if the object creation failed.
     */
    @Override
    public GeographicCRS createGeographicCRS(final String code) throws FactoryException {
        final String name;
        final int datum;
        final int coordinateSystem;
        final int id = parseCode(code);
        switch (id) {
            case 4326: name="WGS 84";       datum=6326; coordinateSystem=6422; break;
            case 4284: name="Pulkovo 1942"; datum=6284; coordinateSystem=6422; break;
            default:   throw noSuchAuthorityCode(id, code);
        }
        assumeNotNull(crsFactory);
        final GeographicCRS object = crsFactory.createGeographicCRS(createPropertiesMap(id, name),
                createGeodeticDatum(String.valueOf(datum)),
                createEllipsoidalCS(String.valueOf(coordinateSystem)));
        validators.validate(object);
        return object;
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public ImageCRS createImageCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public TemporalCRS createTemporalCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public VerticalCRS createVerticalCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }

    /**
     * The default implementation throws {@link NoSuchAuthorityCodeException} unconditionally.
     *
     * @throws FactoryException if this method cannot provide the requested information.
     */
    @Override
    public ParametricCRS createParametricCRS(String code) throws FactoryException {
        final int id = parseCode(code);
        switch (id) {
            default:   throw noSuchAuthorityCode(id, code);
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////                                                               ////////////////
    ////////////////    C O O R D I N A T E   O P E R A T I O N   F A C T O R Y    ////////////////
    ////////////////                                                               ////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the parameters to use for creating the {@linkplain CoordinateOperation coordinate
     * operation} identified by the given EPSG code. The coordinate operation is typically a map
     * projection used by exactly one {@linkplain ProjectedCRS projected CRS}, which is listed in
     * the second column for information purpose.
     *
     * <p>The supported codes are determined from the set of examples published in the EPSG guidance
     * document, augmented with other sources (IGNF).
     * The following table lists the supported codes.
     * <i>Codes in italics are not official EPSG codes.</i></p>
     *
     * <table class="ogc">
     *   <caption>Supported codes</caption>
     *   <tr><th>Code</th>  <th>Used by CRS</th><th>CRS or transformation name</th>                 <th>Operation method</th></tr>
     *   <tr><td>19905</td> <td>3002</td>  <td>Makassar / NEIEZ</td>                                <td>Mercator (variant A)</td></tr>
     *   <tr><td>19884</td> <td>3388</td>  <td>Pulkovo 1942 / Caspian Sea Mercator</td>             <td>Mercator (variant B)</td></tr>
     *   <tr><td>3856</td>  <td>3857</td>  <td>WGS 84 / Pseudo-Mercator</td>                        <td>Popular Visualisation Pseudo Mercator</td></tr>
     *   <tr><td><i>310642901</i></td> <td><i>310642901</i></td> <td>IGNF:MILLER</td>               <td><i>Miller_Cylindrical</i></td></tr>
     *   <tr><td>19958</td> <td>29873</td> <td>Timbalai 1948 / RSO Borneo (m)</td>                  <td>Hotine Oblique Mercator (variant B)</td></tr>
     *   <tr><td>19916</td> <td>27700</td> <td>OSGB 1936 / British National Grid</td>               <td>Transverse Mercator</td></tr>
     *   <tr><td>17529</td> <td>2053</td>  <td>South African Survey Grid zone 29</td>               <td>Transverse Mercator</td></tr>
     *   <tr><td>19975</td> <td>2314</td>  <td>Trinidad 1903 / Trinidad Grid</td>                   <td>Cassini-Soldner</td></tr>
     *   <tr><td>19878</td> <td>3139</td>  <td>Vanua Levu 1915 / Vanua Levu Grid</td>               <td>Hyperbolic Cassini-Soldner</td></tr>
     *   <tr><td>19910</td> <td>24200</td> <td>JAD69 / Jamaica National Grid</td>                   <td>Lambert Conic Conformal (1SP)</td></tr>
     *   <tr><td>14204</td> <td>32040</td> <td>NAD27 / Texas South Central</td>                     <td>Lambert Conic Conformal (2SP)</td></tr>
     *   <tr> <td>6198</td> <td>6201</td>  <td>Michigan CS27 Central zone</td>                      <td>Lambert Conic Conformal (2SP Michigan)</td></tr>
     *   <tr><td>19902</td> <td>31300</td> <td>Belge 1972 / Belge Lambert 72</td>                   <td>Lambert Conic Conformal (2SP Belgium)</td></tr>
     *   <tr><td>19986</td> <td>3035</td>  <td>ETRS89 / LAEA Europe</td>                            <td>Lambert Azimuthal Equal Area</td></tr>
     *   <tr><td>16061</td> <td>5041</td>  <td>WGS 84 / UPS North (E,N)</td>                        <td>Polar Stereographic (variant A)</td></tr>
     *   <tr><td>19993</td> <td>3032</td>  <td>WGS 84 / Australian Antarctic Polar</td>             <td>Polar Stereographic (variant B)</td></tr>
     *   <tr><td>19983</td> <td>2985</td>  <td>Petrels 1972 / Terre Adelie Polar Stereographic</td> <td>Polar Stereographic (variant C)</td></tr>
     *   <tr><td>19914</td> <td>28992</td> <td>Amersfoort / RD New</td>                             <td>Oblique Stereographic</td></tr>
     *   <tr><td><i>9818</i></td> <td><i>9818</i></td> <td><i>Polyconic</i></td>                    <td><i>Polyconic</i></td></tr>
     *   <tr><td><i>9840</i></td> <td><i>9840</i></td> <td><i>Orthographic</i></td>                 <td><i>Orthographic</i></td></tr>
     *   <tr><td>15399</td> <td>3295</td>  <td>Guam 1963 / Yap Islands</td>                         <td>Modified Azimuthal Equidistant</td></tr>
     *   <tr><td>19952</td> <td>2065</td>  <td>CRS S-JTSK (Ferro) / Krovak</td>                     <td>Krovak</td></tr>
     *   <tr><td><i>9605</i></td> <td>4230</td> <td>ED50 to WGS 84</td>                             <td>Abridged Molodensky</td></tr>
     *   <tr><td>15595</td> <td>5820</td>  <td>EPSG topocentric example B</td>                      <td>Geocentric/topocentric conversions</td></tr>
     * </table>
     *
     * @param  code  the EPSG code of the {@linkplain CoordinateOperation coordinate operation} to create.
     * @return the coordinate operation (typically a map projection) parameters.
     * @throws FactoryException if the given EPSG code is unknown to this factory.
     *
     * @see ParameterizedTransformTest
     * @see AuthorityFactoryTest
     */
    protected ParameterValueGroup createParameters(final int code) throws FactoryException {
        final ParameterValueGroup parameters = createParameters(mtFactory, code);
        validators.validate(parameters);
        return parameters;
    }

    /**
     * Implementation of the above {@link #createParameters(int)} method,
     * as a static method for direct access by {@link ParameterizedTransformTest}.
     *
     * @param  factory  the factory to use for creating the parameters.
     * @param  code     authority code of the parameter to create.
     * @return parameter for the given authority code.
     * @throws FactoryException if the given EPSG code is unknown to this factory.
     */
    static ParameterValueGroup createParameters(final MathTransformFactory factory, final int code)
            throws FactoryException
    {
        final ParameterValueGroup parameters;
        switch (code) {
            case 19905: {       // "Makassar / NEIEZ" using operation method 9804
                parameters = factory.getDefaultParameters("Mercator (variant A)");                      // Alias "Mercator (1SP)"
                parameters.parameter("semi_major").setValue(6377397.155);                               // Bessel 1841
                parameters.parameter("semi_minor").setValue(6377397.155 * (1 - 1/299.1528128));
                parameters.parameter("Latitude of natural origin")    .setValue(  0.0);
                parameters.parameter("Longitude of natural origin")   .setValue(110.0);
                parameters.parameter("Scale factor at natural origin").setValue(0.997);
                parameters.parameter("False easting").setValue(3900000.0);
                parameters.parameter("False northing").setValue(900000.0);
                break;
            }
            case 19884: {       // "Pulkovo 1942 / Caspian Sea Mercator" using operation method 9805
                parameters = factory.getDefaultParameters("Mercator (variant B) ");                     // Alias "Mercator (2SP)"
                parameters.parameter("semi_major").setValue(6378245.0);                                 // Krassowski 1940
                parameters.parameter("semi_minor").setValue(6378245.0 * (1 - 1/298.3));
                parameters.parameter("Latitude of 1st standard parallel").setValue(42.0);
                parameters.parameter("Longitude of natural origin")      .setValue(51.0);
                break;
            }
            case 3856: {        // "WGS 84 / Pseudo-Mercator" using operation method 1024
                parameters = factory.getDefaultParameters("Popular Visualisation Pseudo Mercator");
                parameters.parameter("semi_major").setValue(6378137.0);                                 // WGS 84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                break;
            }
            case 310642901: {   // "IGNF:MILLER" (not an official EPSG code)
                parameters = factory.getDefaultParameters("Miller_Cylindrical");
                parameters.parameter("semi_major").setValue(6378137.0);
                parameters.parameter("semi_minor").setValue(6378137.0);
                break;
            }
            case 19958: {       // "Rectified Skew Orthomorphic Borneo Grid (metres)" using operation method 9815
                parameters = factory.getDefaultParameters("Hotine Oblique Mercator (variant B)");
                parameters.parameter("semi_major").setValue(6377298.556);                               // Everest 1830
                parameters.parameter("semi_minor").setValue(6377298.556 * (1 - 1/300.8017));
                parameters.parameter("Latitude of projection centre") .setValue(  4.0);                         //   4°00'00"N
                parameters.parameter("Longitude of projection centre").setValue(115.0);                         // 115°00'00"E
                parameters.parameter("Azimuth of initial line").setValue(53 + (18 + 56.9537/60)/60);            //  53°18'56.9537"
                parameters.parameter("Angle from Rectified to Skew Grid").setValue(53 + (7 + 48.3685/60)/60);   //  53°07'48.3685"
                parameters.parameter("Scale factor on initial line") .setValue(0.99984);
                parameters.parameter("Easting at projection centre") .setValue(590476.87);
                parameters.parameter("Northing at projection centre").setValue(442857.65);
                break;
            }
            case 19916: {       // "British National Grid" using operation method 9807
                parameters = factory.getDefaultParameters("Transverse Mercator");
                parameters.parameter("semi_major").setValue(6377563.396);                               // Airy
                parameters.parameter("semi_minor").setValue(6377563.396 * (1 - 1/299.32496));
                parameters.parameter("Latitude of natural origin") .setValue(49.0);
                parameters.parameter("Longitude of natural origin").setValue(-2.0);
                parameters.parameter("Scale factor at natural origin").setValue(0.9996012717);
                parameters.parameter("False easting") .setValue( 400000.00);
                parameters.parameter("False northing").setValue(-100000.00);
                break;
            }
            case 17529: {       // "South African Survey Grid zone 29" using operation method 9808
                parameters = factory.getDefaultParameters("Transverse Mercator (South Orientated)");
                parameters.parameter("semi_major").setValue(6378137.0);                                 // WGS 84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("Latitude of natural origin").setValue(0.0);
                parameters.parameter("Longitude of natural origin").setValue(29.0);
                parameters.parameter("Scale factor at natural origin").setValue(1.0);
                parameters.parameter("False easting") .setValue(0.0);
                parameters.parameter("False northing").setValue(0.0);
                break;
            }
            case 19975: {       // "Trinidad 1903 / Trinidad Grid" using operation method 9806
                /*
                 * Values used below are those published in IOGP Publication 373-7-2 §3.2.2 — September 2019.
                 * They differ from values in EPSG geodetic dataset 9.8.11 in following aspects: geodetic dataset
                 * uses Clarke's foot units instead of foot and link units, with same values for ellipsoid axis
                 * lengths (before conversion) but different values for false easting and northing parameters.
                 * The differences in easting/northing parameters is up to 0.8 metre. We keep the values published
                 * in IOGP 373-7-2 because the sample point tested in map projection is computed with those values.
                 */
                parameters = factory.getDefaultParameters("Cassini-Soldner");
                parameters.parameter("semi_major").setValue(20926348.0 * FEET);                         // Clarke 1858
                parameters.parameter("semi_minor").setValue(20855233.0 * FEET);
                parameters.parameter("Latitude of natural origin") .setValue(10 + (26 + 30.0/60)/60);   // 10°26'30"N
                parameters.parameter("Longitude of natural origin").setValue(-(61 + 20.0/60));          // 61°20'00"W
                parameters.parameter("False easting") .setValue(430000.00 * LINKS);
                parameters.parameter("False northing").setValue(325000.00 * LINKS);
                break;
            }
            case 19878: {       // "Vanua Levu Grid" using operation method 9833
                parameters = factory.getDefaultParameters("Hyperbolic Cassini-Soldner");
                parameters.parameter("semi_major").setValue(20926202.0 * FEET);                         // Clarke 1880
                parameters.parameter("semi_minor").setValue(20854895.0 * FEET);
                parameters.parameter("Latitude of natural origin") .setValue(-(16 + 15./60));           // 16°15'00"S
                parameters.parameter("Longitude of natural origin").setValue( 179 + 20./60);            // 179°20'00"E
                parameters.parameter("False easting") .setValue(1251331.8 * LINKS);
                parameters.parameter("False northing").setValue(1662888.5 * LINKS);
                break;
            }
            case 19910: {       // "JAD69 / Jamaica National Grid" using operation method 9801
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (1SP)");
                parameters.parameter("semi_major").setValue(6378206.4);                                 // Clarke 1866
                parameters.parameter("semi_minor").setValue(6356583.8);
                parameters.parameter("Latitude of natural origin")    .setValue( 18.0);
                parameters.parameter("Longitude of natural origin")   .setValue(-77.0);
                parameters.parameter("Scale factor at natural origin").setValue(  1.0);
                parameters.parameter("False easting") .setValue(250000.00);
                parameters.parameter("False northing").setValue(150000.00);
                break;
            }
            case 14204: {       // "NAD27 / Texas South Central" using operation method 9802
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (2SP)");
                parameters.parameter("semi_major").setValue(6378206.4);                                 // Clarke 1866
                parameters.parameter("semi_minor").setValue(6356583.8);
                parameters.parameter("Latitude of 1st standard parallel").setValue(28 + 23.0/60);       // 28°23'00"N
                parameters.parameter("Latitude of 2nd standard parallel").setValue(30 + 17.0/60);       // 30°17'00"N
                parameters.parameter("Latitude of false origin")         .setValue(27 + 50.0/60);       // 27°50'00"N
                parameters.parameter("Longitude of false origin")        .setValue(-99.0);              // 99°00'00"W
                parameters.parameter("Easting at false origin") .setValue(2000000 / R_US_FEET);
                parameters.parameter("Northing at false origin").setValue(      0 / R_US_FEET);
                break;
            }
            case 6198: {        // "Michigan CS27 Central zone" using operation method 1051
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (2SP Michigan)");
                parameters.parameter("semi_major").setValue(6378206.4);                                 // Clarke 1866
                parameters.parameter("semi_minor").setValue(6356583.8);
                parameters.parameter("Latitude of 1st standard parallel").setValue( 44 + 11.0/60);      // 44°11' N
                parameters.parameter("Latitude of 2nd standard parallel").setValue( 45 + 42.0/60);      // 45°42' N
                parameters.parameter("Latitude of false origin")         .setValue( 43 + 19.0/60);      // 43°19' N
                parameters.parameter("Longitude of false origin")        .setValue(-84 - 20.0/60);      // 84°20' W
                parameters.parameter("Easting at false origin") .setValue(2000000 / R_US_FEET);
                parameters.parameter("Northing at false origin").setValue(      0 / R_US_FEET);
                parameters.parameter("Ellipsoid scaling factor").setValue(1.0000382);
                break;
            }
            case 19902: {       // "Belge 1972 / Belge Lambert 72" using operation method 9803
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (2SP Belgium)");
                parameters.parameter("semi_major").setValue(6378388.0);                                 // International 1924
                parameters.parameter("semi_minor").setValue(6378388.0 * (1 - 1/297.0));
                parameters.parameter("Latitude of 1st standard parallel").setValue(49 + 50.0/60);       // 49°50'00.000"N
                parameters.parameter("Latitude of 2nd standard parallel").setValue(51 + 10.0/60);       // 51°10'00.000"N
                parameters.parameter("Latitude of false origin")         .setValue(90.0);               // 90°00'00.000"N
                parameters.parameter("Longitude of false origin").setValue(4 + (21 + 24.983/60)/60);    //  4°21'24.983"E
                parameters.parameter("Easting at false origin") .setValue( 150000.01);
                parameters.parameter("Northing at false origin").setValue(5400088.44);
                break;
            }
            case 19986: {       // "Europe Equal Area 2001" using operation method 9820
                parameters = factory.getDefaultParameters("Lambert Azimuthal Equal Area");
                parameters.parameter("semi_major").setValue(6378137.0);
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572221));
                parameters.parameter("Latitude of natural origin") .setValue(52.0);
                parameters.parameter("Longitude of natural origin").setValue(10.0);
                parameters.parameter("False easting") .setValue(4321000.00);
                parameters.parameter("False northing").setValue(3210000.00);
                break;
            }
            case 16061: {       // "Universal Polar Stereographic North" using operation method 9810
                parameters = factory.getDefaultParameters("Polar Stereographic (variant A)");
                parameters.parameter("semi_major").setValue(6378137.0);                                 // WGS84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("Latitude of natural origin").setValue(90.0);
                parameters.parameter("Longitude of natural origin").setValue(0.0);
                parameters.parameter("Scale factor at natural origin").setValue(0.994);
                parameters.parameter("False easting") .setValue(2000000.00);
                parameters.parameter("False northing").setValue(2000000.00);
                break;
            }
            case 19993: {       // "Australian Antarctic Polar Stereographic" using operation method 9829
                parameters = factory.getDefaultParameters("Polar Stereographic (variant B)");
                parameters.parameter("semi_major").setValue(6378137.0);                                 // WGS84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("Latitude of standard parallel").setValue(-71.0);
                parameters.parameter("Longitude of origin").setValue(70.0);
                parameters.parameter("False easting") .setValue(6000000.00);
                parameters.parameter("False northing").setValue(6000000.00);
                break;
            }
            case 19983: {       // "Petrels 1972 / Terre Adelie Polar Stereographic" using operation method 9830
                parameters = factory.getDefaultParameters("Polar Stereographic (variant C)");
                parameters.parameter("semi_major").setValue(6378388.0);                                 // International 1924
                parameters.parameter("semi_minor").setValue(6378388.0 * (1 - 1/297.0));
                parameters.parameter("Latitude of standard parallel").setValue(-67.0);
                parameters.parameter("Longitude of origin").setValue(140.0);
                parameters.parameter("Easting at false origin") .setValue(300000.00);
                parameters.parameter("Northing at false origin").setValue(200000.00);
                break;
            }
            case 19914: {       // "RD New" using operation method 9809
                parameters = factory.getDefaultParameters("Oblique Stereographic");
                parameters.parameter("semi_major").setValue(6377397.155);                               // Bessel 1841
                parameters.parameter("semi_minor").setValue(6377397.155 * (1 - 1/299.15281));
                parameters.parameter("Latitude of natural origin").setValue(52 + ( 9 + 22.178/60)/60);  // 52°09'22.178"N
                parameters.parameter("Longitude of natural origin").setValue(5 + (23 + 15.500/60)/60);  //  5°23'15.500"E
                parameters.parameter("Scale factor at natural origin").setValue(0.9999079);
                parameters.parameter("False easting") .setValue(155000.00);
                parameters.parameter("False northing").setValue(463000.00);
                break;
            }
            case 9818: {        // (not an official EPSG code) using operation method 9818
                parameters = factory.getDefaultParameters("Polyconic");
                parameters.parameter("semi_major").setValue(6378206.4);
                parameters.parameter("semi_minor").setValue(6356583.8);
                parameters.parameter("Latitude of natural origin") .setValue(0.0);
                parameters.parameter("Longitude of natural origin").setValue(0.0);
                parameters.parameter("False easting") .setValue(0.0);
                parameters.parameter("False northing").setValue(0.0);
                break;
            }
            case 9840: {        // (not an official EPSG code) using operation method 9840
                parameters = factory.getDefaultParameters("Orthographic");
                parameters.parameter("semi_major").setValue(6378137.0);                                 // WGS84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("Latitude of natural origin").setValue(55.0);
                parameters.parameter("Longitude of natural origin").setValue(5.0);
                parameters.parameter("False easting") .setValue(0.0);
                parameters.parameter("False northing").setValue(0.0);
                break;
            }
            case 15399: {       // "Guam 1963 / Yap Islands" using operation method 9832
                parameters = factory.getDefaultParameters("Modified Azimuthal Equidistant");
                parameters.parameter("semi_major").setValue(6378206.4);                                 // Clarke 1866
                parameters.parameter("semi_minor").setValue(6356583.8);
                parameters.parameter("Latitude of natural origin").setValue(9 + (32 + 48.15/60)/60);
                parameters.parameter("Longitude of natural origin").setValue(138 + (10 + 7.48/60)/60);
                parameters.parameter("False easting") .setValue(40000.0);
                parameters.parameter("False northing").setValue(60000.0);
                break;
            }
            case 19952: {       // "CRS S-JTSK (Ferro) / Krovak" using operation method 9819
                parameters = factory.getDefaultParameters("Krovak");
                parameters.parameter("semi_major").setValue(6377397.155);                               // Bessel
                parameters.parameter("semi_minor").setValue(6377397.155 * (1 - 1/299.15281));
                parameters.parameter("Latitude of projection centre").setValue(49.5);                   // 49°30'00"N
                parameters.parameter("Longitude of origin").setValue(24 + 50.0/60);                     // 24°30'00"E
                parameters.parameter("Co-latitude of cone axis").setValue(30 + (17 + 17.3031/60)/60);
                parameters.parameter("Latitude of pseudo standard parallel").setValue(78.5);
                parameters.parameter("Scale factor on pseudo standard parallel").setValue(0.99990);
                break;
            }
            case 9605: {        // (not an official EPSG code) using operation method 9605
                parameters = factory.getDefaultParameters("Abridged Molodensky");
                parameters.parameter("dim").setValue(3);                                                // Parameter defined by OGC 01-009
                parameters.parameter("src_semi_major").setValue(6378137.0);                             // WGS84
                parameters.parameter("src_semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("X-axis translation").setValue( 84.87);
                parameters.parameter("Y-axis translation").setValue( 96.49);
                parameters.parameter("Z-axis translation").setValue(116.95);
                parameters.parameter("Semi-major axis length difference").setValue(251);
                parameters.parameter("Flattening difference").setValue(1.41927E-05);
                break;
            }
            case 15594: {       // EPSG topocentric example A
                parameters = factory.getDefaultParameters("Geographic/topocentric conversions");
                parameters.parameter("semi_major").setValue(6378137.0);                               // WGS84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("Latitude of topocentric origin").setValue(55);
                parameters.parameter("Longitude of topocentric origin").setValue(5);
                parameters.parameter("Ellipsoidal height of topocentric origin").setValue(200);
                break;
            }
            case 15595: {       // EPSG topocentric example B
                parameters = factory.getDefaultParameters("Geocentric/topocentric conversions");
                parameters.parameter("semi_major").setValue(6378137.0);                               // WGS84
                parameters.parameter("semi_minor").setValue(6378137.0 * (1 - 1/298.2572236));
                parameters.parameter("Geocentric X of topocentric origin").setValue(3652755.3058);
                parameters.parameter("Geocentric Y of topocentric origin").setValue( 319574.6799);
                parameters.parameter("Geocentric Z of topocentric origin").setValue(5201547.3536);
                break;
            }
            default: {
                throw noSuchAuthorityCode(code, String.valueOf(code));
            }
        }
        return parameters;
    }
}
