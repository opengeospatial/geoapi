/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2013 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.NonSI;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Dimensionless;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.test.Configuration;
import org.opengis.test.referencing.PseudoEpsgFactory;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.Double.*;
import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Test for new CRS definition. The test procedures in this series are designed
 * to evaluate the software’s capabilities for adding user-defined CRS and
 * transformation definitions
 *
 * @see org.opengis.test.referencing.ObjectFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Alexis Manin (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class Series3000Test extends GIGSTestCase {
    /**
     * Factory to use for build user's coordinate reference systems,
     * or {@code null} if none.
     */
    protected final CRSFactory crsFactory;

    /**
     * Factory to use for build user's coordinate systems and axes,
     * or {@code null} if none.
     */
    protected final CSFactory csFactory;

    /**
     * Factory to use for build user's datum, ellipsoid and prime meridians,
     * or {@code null} if none.
     */
    protected final DatumFactory datumFactory;

    /**
     * Factory to use for build user's coordinate operations,
     * or {@code null} if none.
     */
    protected final CoordinateOperationFactory copFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code Series3000Test} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(CRSFactory.class, CSFactory.class, DatumFactory.class,
                CoordinateOperationFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory   Factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory    Factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory Factory for creating {@link Datum} instances.
     * @param copFactory   Factory for creating {@link CoordinateOperation} instances.
     */
    public Series3000Test(final CRSFactory crsFactory, final CSFactory csFactory,
            final DatumFactory datumFactory, final CoordinateOperationFactory copFactory)
    {
        super(crsFactory, csFactory, datumFactory, copFactory);
        this.crsFactory   = crsFactory;
        this.csFactory    = csFactory;
        this.datumFactory = datumFactory;
        this.copFactory   = copFactory;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     * <p>
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@linkplain #crsFactory}</li>
     *       <li>{@linkplain #csFactory}</li>
     *       <li>{@linkplain #datumFactory}</li>
     *       <li>{@linkplain #copFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.crsFactory,   crsFactory));
        assertNull(op.put(Configuration.Key.csFactory,    csFactory));
        assertNull(op.put(Configuration.Key.datumFactory, datumFactory));
        assertNull(op.put(Configuration.Key.copFactory,   copFactory));
        return op;
    }

    /**
     * Returns the linear unit (compatible with metres) of the given name.
     *
     * @param  name The unit name.
     * @return The linear unit for the given name.
     * @throws IllegalArgumentException If the given name is unsupported by this method.
     */
    private static Unit<Length> parseLinearUnit(final String name) throws IllegalArgumentException {
        if (name.equalsIgnoreCase("metre"))          return SI.METRE;
        if (name.equalsIgnoreCase("kilometre"))      return SI.KILOMETRE;
        if (name.equalsIgnoreCase("US survey foot")) return NonSI.FOOT_SURVEY_US;
        if (name.equalsIgnoreCase("ft(US)"))         return NonSI.FOOT_SURVEY_US;
        if (name.equalsIgnoreCase("foot"))           return NonSI.FOOT;
        throw new IllegalArgumentException("Unsupported unit name: " + name);
    }

    /**
     * Retrieve the angular unit (compatible with degrees) corresponding to the given name.
     *
     * @param  name The unit name.
     * @return The angular unit for the given name.
     * @throws IllegalArgumentException If the given name is unsupported by this method.
     */
    private static Unit<Angle> parseAngularUnit(final String name) throws IllegalArgumentException {
        if (name.equalsIgnoreCase("degree"))      return NonSI.DEGREE_ANGLE;
        if (name.equalsIgnoreCase("grad"))        return NonSI.GRADE;
        if (name.equalsIgnoreCase("arc-second"))  return NonSI.SECOND_ANGLE;
        if (name.equalsIgnoreCase("microradian")) return NonSI.CENTIRADIAN;
        throw new IllegalArgumentException("Unsupported unit name: " + name);
    }

    /**
     * Retrieve the scale unit (dimensionless) corresponding to the given name.
     *
     * @param  name The unit name.
     * @return The scale unit for the given name.
     * @throws IllegalArgumentException If the given name is unsupported by this method.
     */
    private static Unit<Dimensionless> parseScaleUnit(final String name) throws IllegalArgumentException {
        if (name.equalsIgnoreCase("unity"))             return Unit.ONE;
        if (name.equalsIgnoreCase("parts per million")) return Unit.ONE.divide(1000000);
        throw new IllegalArgumentException("Unsupported unit name: " + name);
    }

    /**
     * Ellipsoid definition test.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>Verify that the software allows correct definition of a user-defined ellipsoid.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Create user-defined ellipsoid for each of several different ellipsoids.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_3002_userEllipsoid.csv">{@code GIGS_3002_userEllipsoid.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Tested API:</th>
     *   <td>{@link DatumFactory#createEllipsoid(Map, double, double, Unit)},
     *       {@link DatumFactory#createFlattenedSphere(Map, double, double, Unit)}</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>The software should accept the test data. The properties of the created objects will
     *       be compared with the properties given to the factory method.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error occurred while creating an ellipsoid.
     */
    @Test
    public void test3002() throws FactoryException {
        test3002(null);
    }

    /**
     * Creates the ellipsoids and optionally tests them.
     * The behavior of this method depends on whether {@code objects} is null or not:
     * <p>
     * <ul>
     *   <li>If {@code null}, then all ellipsoids will be created and tested.</li>
     *   <li>If non-null, then only the ellipsoids enumerated in the keys will be created,
     *       but none of them will be tested. The created ellipsoids will be stored in the
     *       values of that map.</li>
     * </ul>
     *
     * @param  objects On input, the GIGS names of objects to create. On output if non-null,
     *         the created but untested objects. If {@code null}, then all objects will be
     *         created and tested.
     * @throws FactoryException If an error occurred while creating an object.
     */
    private void test3002(final Map<String,Ellipsoid> objects) throws FactoryException {
        assumeNotNull(datumFactory);
        final ExpectedData data = new ExpectedData("GIGS_3002_userEllipsoid.csv",
                Integer.class,  // [0]: GIGS ellipsoid code
                String .class,  // [1]: GIGS ellipsoid name
                Double .class,  // [2]: Semi-major axis
                String .class,  // [3]: Unit
                Double .class,  // [4]: Inverse flattening
                Double .class,  // [5]: Semi-minor axis
                Boolean.class); // [6]: Is sphere ?

        final StringBuilder prefix = new StringBuilder("Ellipsoid[\"");
        final int prefixLength = prefix.length();
        while (data.next()) {
            final String name = data.getString(1);
            if (objects != null && !objects.containsKey(name)) {
                // If the current row is not for an object on the list of
                // items requested by the caller, skip the object creation.
                continue;
            }
            final int    code        = data.getInt   (0);
            final double semiMajor   = data.getDouble(2);
            final String unitName    = data.getString(3);
            double inverseFlattening = data.getDouble(4);
            double semiMinor         = data.getDouble(5);
            final boolean isSphere, isIvfDefinitive;
            if (isNaN(semiMinor)) {
                if (isNaN(inverseFlattening)) {
                    isSphere          = true;
                    isIvfDefinitive   = false;
                    semiMinor         = semiMajor;
                    inverseFlattening = POSITIVE_INFINITY;
                } else {
                    isSphere        = false;
                    isIvfDefinitive = true;
                    semiMinor       = semiMajor - semiMajor/inverseFlattening;
                }
            } else {
                isSphere        = false;
                isIvfDefinitive = false;
                if (isNaN(inverseFlattening)) {
                    inverseFlattening = semiMajor / (semiMajor-semiMinor);
                }
            }
            /*
             * Create the ellipsoid and save it in the map given by the caller, if non-null.
             */
            final Map<String,Object> properties = new HashMap<String,Object>(4);
            properties.put(Ellipsoid.IDENTIFIERS_KEY, new SimpleReferenceIdentifier(code));
            properties.put(Ellipsoid.NAME_KEY, name);
            final Unit<Length> unit = parseLinearUnit(unitName);
            final Ellipsoid ellipsoid;
            if (isIvfDefinitive) {
                ellipsoid = datumFactory.createFlattenedSphere(properties, semiMajor, inverseFlattening, unit);
            } else {
                ellipsoid = datumFactory.createEllipsoid(properties, semiMajor, semiMinor, unit);
            }
            prefix.setLength(prefixLength);
            prefix.append(name).append("\"]");
            assertNotNull(prefix.toString(), ellipsoid);
            if (objects != null) {
                assertNull("An object already exists for the same name.", objects.put(name, ellipsoid));
                continue;
            }
            /*
             * Test the ellipsoid.
             */
            validators.validate(ellipsoid);
            prefix.append('.');
            assertEquals    (message(prefix, "getName()"),              name,              getName(ellipsoid));
            verifyIdentifier(message(prefix, "getIdentifiers()"),       code,              ellipsoid.getIdentifiers());
            assertEquals    (message(prefix, "getAxisUnit()"),          unit,              ellipsoid.getAxisUnit());
            assertEquals    (message(prefix, "getSemiMajorAxis()"),     semiMajor,         ellipsoid.getSemiMajorAxis(),     TOLERANCE*semiMajor);
            assertEquals    (message(prefix, "getSemiMinorAxis()"),     semiMinor,         ellipsoid.getSemiMinorAxis(),     TOLERANCE*semiMinor);
            assertEquals    (message(prefix, "getInverseFlattening()"), inverseFlattening, ellipsoid.getInverseFlattening(), TOLERANCE*inverseFlattening);
            assertEquals    (message(prefix, "isIvfDefinitive()"),      isIvfDefinitive,   ellipsoid.isIvfDefinitive());
            assertEquals    (message(prefix, "isSphere()"),             isSphere,          ellipsoid.isSphere());
        }
    }

    /**
     * Prime meridian definition test.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>Verify that the software allows correct definition of a user-defined prime meridian.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Create user-defined prime meridian for each of several different prime meridians.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_3003_userPrimeMeridian.csv">{@code GIGS_3003_userPrimeMeridian.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Tested API:</th>
     *   <td>{@link DatumFactory#createPrimeMeridian(Map, double, Unit)}</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>The software should accept the test data. The properties of the created objects will
     *       be compared with the properties given to the factory method.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error occurred while creating a prime meridian.
     */
    @Test
    public void test3003() throws FactoryException {
        test3003(null);
    }

    /**
     * Creates the prime meridians and optionally tests them.
     * The behavior of this method depends on whether {@code objects} is null or not:
     * <p>
     * <ul>
     *   <li>If {@code null}, then all meridians will be created and tested.</li>
     *   <li>If non-null, then only the ones enumerated in the keys will be created,
     *       but none of them will be tested. The created objects will be stored in the
     *       values of that map.</li>
     * </ul>
     *
     * @param  objects On input, the GIGS names of objects to create. On output if non-null,
     *         the created but untested objects. If {@code null}, then all objects will be
     *         created and tested.
     * @throws FactoryException If an error occurred while creating an object.
     */
    private void test3003(final Map<String,PrimeMeridian> objects) throws FactoryException {
        assumeNotNull(datumFactory);
        final ExpectedData data = new ExpectedData("GIGS_3003_userPrimeMeridian.csv",
                Integer.class,  // [0]: GIGS prime meridian code
                String.class,   // [1]: GIGS prime meridian name
                String.class,   // [2]: Longitude from greenwich
                String.class,   // [3]: EPSG unit name
                Double.class);  // [4]: Longitude from Greenwich in decimal degrees

        final StringBuilder prefix = new StringBuilder("PrimeMeridian[\"");
        final int prefixLength = prefix.length();
        while (data.next()) {
            final String name = data.getString(1);
            if (objects != null && !objects.containsKey(name)) {
                // If the current row is not for an object on the list of
                // items requested by the caller, skip the object creation.
                continue;
            }
            final int    code     = data.getInt(0);
            final String unitName = data.getString(3);
            final double longitude;
            final Unit<Angle> unit;
            if (unitName.equalsIgnoreCase("sexagesimal degree")) {
                /*
                 * Sexagesimal degrees are written in a String not directly convertible to 'double'
                 * values. Even if we performed the conversion, we do not expect implementations to
                 * support sexagesimal units since the conversion to degrees is non-linear.
                 * Consequently, fallback on the decimal degrees instead.
                 */
                unit = NonSI.DEGREE_ANGLE;
                longitude = data.getDouble(4);
            } else {
                unit = parseAngularUnit(unitName);
                longitude = Double.parseDouble(data.getString(2));
            }
            /*
             * Create the prime meridian and save it in the map given by the caller, if non-null.
             */
            final Map<String,Object> properties = new HashMap<String,Object>(4);
            properties.put(Ellipsoid.IDENTIFIERS_KEY, new SimpleReferenceIdentifier(code));
            properties.put(Ellipsoid.NAME_KEY, name);
            final PrimeMeridian meridian = datumFactory.createPrimeMeridian(properties, longitude, unit);
            prefix.setLength(prefixLength);
            prefix.append(name).append("\"]");
            assertNotNull(prefix.toString(), meridian);
            if (objects != null) {
                assertNull("An object already exists for the same name.", objects.put(name, meridian));
                continue;
            }
            validators.validate(meridian);
            prefix.append('.');
            assertEquals    (message(prefix, "getName()"),               name,      getName(meridian));
            verifyIdentifier(message(prefix, "getIdentifiers()"),        code,      meridian.getIdentifiers());
            assertEquals    (message(prefix, "getAngularUnit()"),        unit,      meridian.getAngularUnit());
            assertEquals    (message(prefix, "getGreenwichLongitude()"), longitude, meridian.getGreenwichLongitude(), ANGULAR_TOLERANCE);
        }
    }

    /**
     * Datum & CRS definition test.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>Verify that the software allows correct definition of a user-defined geodetic datum and geodetic CRS.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Create user-defined geodetic datum for each of several different datums.
     *       Create user-defined geodetic CRS for each of several different CRSs.
     *   </td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_3004_userGeodeticDatumCRS.csv">{@code GIGS_3004_userGeodeticDatumCRS.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Tested API:</th>
     *   <td>{@link CRSFactory#createGeocentricCRS(Map, GeodeticDatum, CartesianCS)},
     *       {@link CRSFactory#createGeographicCRS(Map, GeodeticDatum, EllipsoidalCS)}</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>The software should accept the test data. The properties of the created objects will
     *       be compared with the properties given to the factory method.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchAuthorityCodeException
     *         unsupported code}) occurred while creating a unit from an EPSG code.
     */
    @Test
    public void test3004() throws FactoryException {
        test3004(null);
    }

    /**
     * Creates the CRS and optionally tests them.
     * The behavior of this method depends on whether {@code objects} is null or not:
     * <p>
     * <ul>
     *   <li>If {@code null}, then all CRS will be created and tested.</li>
     *   <li>If non-null, then only the ones enumerated in the keys will be created,
     *       but none of them will be tested. The created objects will be stored in the
     *       values of that map.</li>
     * </ul>
     *
     * @param  objects On input, the GIGS codes of objects to create. On output if non-null,
     *         the created but untested objects. If {@code null}, then all objects will be
     *         created and tested.
     * @throws FactoryException If an error occurred while creating an object.
     */
    private void test3004(final Map<String,GeodeticCRS> objects) throws FactoryException {
        assumeNotNull(datumFactory);
        assumeNotNull(crsFactory);
        final PseudoEpsgFactory epsgFactory = new PseudoEpsgFactory(datumFactory, csFactory, null, null, null, validators);
        final ExpectedData data = new ExpectedData("GIGS_3004_userGeodeticDatumCRS.csv",
                Integer.class,    // [ 0]: User Datum code
                String.class,     // [ 1]: User Datum code name
                String.class,     // [ 2]: GIGS User-defined Ellipsoid Name (see test 3002)
                String.class,     // [ 3]: GIGS User-defined Prime Meridian Name (see test 3003)
                String.class,     // [ 4]: Datum origin
                Integer.class,    // [ 5]: GIGS User-defined  CRS Code
                String.class,     // [ 6]: GIGS CRS Name
                String.class,     // [ 7]: EPSG CRS Type
                Integer.class,    // [ 8]: GIGS datum code (see column 0)
                Integer.class);   // [ 9]: EPSG coordinate system code
        /*
         * Get needed data from previous tests. First, we take names of the objects
         * we need, then send them to previous tests for getting the matching values.
         */
        final Map<String,Ellipsoid>     ellipsoids = data.getDependencies(2);
        final Map<String,PrimeMeridian> meridians  = data.getDependencies(3);
        test3002(ellipsoids);
        test3003(meridians);
        /*
         * Each line define both a datum and a CRS. However some lines omit the datum
         * definition, in which case the datum from the previous line shall be reused.
         * Note: strictly speaking, the previous datum to use is specified in column 8.
         * But this is always the previous column (this is verified in this method).
         */
        GeodeticDatum datum  = null;
        int    datumCode     = 0;
        String datumName     = null;
        String ellipsoidName = null;
        String meridianName  = null;
        String anchorPoint   = null;
        final StringBuilder prefix = new StringBuilder();
        while (data.next()) {
            /*
             * Remember the information aboue the datum, if any, but do not create it yet.
             * We will wait to know if we really need to create the datum.
             */
            final Integer newDatumCode = data.getIntOptional(0);
            if (newDatumCode != null) {
                datumCode     = newDatumCode;
                datumName     = data.getString(1);
                ellipsoidName = data.getString(2);
                meridianName  = data.getString(3);
                anchorPoint   = data.getString(4);
            }
            assertEquals(data.getInt(8), datumCode); // See the comment before the loop.
            final String crsName = data.getString(6);
            if (objects != null && !objects.containsKey(crsName)) {
                // If the current row is not for an object on the list of
                // items requested by the caller, skip the object creation.
                continue;
            }
            /*
             * If the current line needs a new datum, create it now. Otherwise keep the last used
             * datum. Note that a failure to find the ellipsoid or meridian would be a bug in the
             * tests, not a bug in the library that we are testing.
             */
            if (newDatumCode != null) {
                Ellipsoid ellipsoid    = ellipsoids.get(ellipsoidName);
                PrimeMeridian meridian = meridians .get(meridianName);
                if (ellipsoid == null) {
                    ellipsoid = epsgFactory.createEllipsoid(String.valueOf(CodeForName.get(Ellipsoid.class, ellipsoidName)));
                }
                if (meridian == null) {
                    meridian = epsgFactory.createPrimeMeridian(String.valueOf(CodeForName.get(PrimeMeridian.class, meridianName)));
                }
                final Map<String,Object> properties = new HashMap<String,Object>(4);
                properties.put(Datum.IDENTIFIERS_KEY,  new SimpleReferenceIdentifier(newDatumCode));
                properties.put(Datum.NAME_KEY,         datumName);
                properties.put(Datum.ANCHOR_POINT_KEY, anchorPoint);
                datum = datumFactory.createGeodeticDatum(properties, ellipsoid, meridian);
                prefix.setLength(0);
                prefix.append("Datum[\"").append(datumName).append("\"]");
                assertNotNull(prefix.toString(), meridian);
            }
            assertNotNull(datum); // Failure here would be a bug in the test, not in the library.
            /*
             * Create the geocentric or geographic CRS.
             */
            final int crsCode = data.getInt   (5);
            final String type = data.getString(7);
            final int  csCode = data.getInt   (9);
            final GeodeticCRS crs;
            final Map<String,Object> properties = new HashMap<String,Object>(4);
            properties.put(SingleCRS.IDENTIFIERS_KEY, new SimpleReferenceIdentifier(crsCode));
            properties.put(SingleCRS.NAME_KEY,        crsName);
            if (type.startsWith("Geocentric")) {
                crs = crsFactory.createGeocentricCRS(properties, datum,
                        epsgFactory.createCartesianCS(String.valueOf(csCode)));
            } else if (type.startsWith("Geographic")) {
                crs = crsFactory.createGeographicCRS(properties, datum,
                        epsgFactory.createEllipsoidalCS(String.valueOf(csCode)));
            } else {
                // Should never happen, unless we have a bug in our test suite
                // (not in the library that we are testing).
                throw new AssertionError(type);
            }
            prefix.setLength(0);
            prefix.append(type).append("[\"").append(crsName).append("\"]");
            assertNotNull(prefix.toString(), crs);
            if (objects != null) {
                assertNull("An object already exists for the same name.", objects.put(crsName, crs));
                continue;
            }
            /*
             * Now verify the properties of the CRS we just created.
             * We do not need to validate the datum, since this will
             * be done indirectly by the CRS validator.
             */
            validators.validate(crs);
            prefix.append('.');
            assertEquals    (message(prefix, "getName()"),        crsName, getName(crs));
            verifyIdentifier(message(prefix, "getIdentifiers()"), crsCode, crs.getIdentifiers());
            assertNotNull   (message(prefix, "getCoordinateSystem()"), crs.getCoordinateSystem());
            /*
             * Compare the datum properties. Note that the datum instance do
             * not need to be the instance that we gave to the factory method.
             */
            final GeodeticDatum userDatum = crs.getDatum();
            verifyDependencyIdentification(prefix, "getDatum()", datumName, datumCode, userDatum);
            if (anchorPoint != null) {
                final InternationalString userAnchor = userDatum.getAnchorPoint();
                final String message = message(prefix, "getAnchorPoint()");
                assertNotNull(message, userDatum.getAnchorPoint());
                assertEquals (message, anchorPoint, userAnchor.toString());
            }
            final int prefixLength = prefix.length();
            verifyDependencyIdentification(prefix, "getEllipsoid()", ellipsoidName, 0, userDatum.getEllipsoid());
            prefix.setLength(prefixLength);
            verifyDependencyIdentification(prefix, "getPrimeMeridian()", meridianName,  0, userDatum.getPrimeMeridian());
        }
    }
}
