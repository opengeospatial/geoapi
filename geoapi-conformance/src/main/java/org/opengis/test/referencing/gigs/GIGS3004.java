/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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
import javax.measure.Unit;
import javax.measure.quantity.Angle;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.referencing.cs.CSFactory;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.crs.GeocentricCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.test.Configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies that the software allows correct definition of a user-defined geodetic datum and geodetic CRS.
 * Each test method in this class instantiate exactly one {@link GeodeticDatum}, but
 * may instantiate an arbitrary amount of {@link GeodeticCRS} using that datum.
 *
 * <table class="gigs" summary="Test description"><tr>
 *   <th>Test method:</th>
 *   <td>Create user-defined geodetic datum for each of several different datums.
 *       Create user-defined geodetic CRS for each of several different CRSs.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_3004_userGeodeticDatumCRS.csv">{@code GIGS_3004_userGeodeticDatumCRS.csv}</a>.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link CRSFactory#createGeocentricCRS(Map, GeodeticDatum, CartesianCS)} and<br>
 *       {@link CRSFactory#createGeographicCRS(Map, GeodeticDatum, EllipsoidalCS)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>The software should accept the test data. The properties of the created objects will
 *       be compared with the properties given to the factory method.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS3004;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS3004 {
 *    public MyTest() {
 *        super(new MyDatumFactory(), new MyCSFactory(), new MyCRSFactory());
 *    }
 *}</pre></blockquote>
 * </div>
 *
 * @author  GIGS (IOGP)
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Alexis Manin (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class GIGS3004 extends UserObjectFactoryTestCase<GeodeticDatum> {
    /**
     * The datum created by the factory,
     * or {@code null} if not yet created or if datum creation failed.
     */
    private GeodeticDatum datum;

    /**
     * Data about the geodetic datum ellipsoid.
     */
    private final GIGS3002 ellipsoidData;

    /**
     * Data about the geodetic datum prime meridian.
     */
    private final GIGS3003 primeMeridianData;

    /**
     * Factory to use for building {@link GeodeticCRS} instances, or {@code null} if none.
     */
    protected final CRSFactory crsFactory;

    /**
     * The factory to use for creating coordinate system instances.
     */
    private final EPSGMock epsgFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS3004} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(DatumFactory.class, CSFactory.class, CRSFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param datumFactory  factory for creating {@link Ellipsoid} and {@link PrimeMeridian} instances.
     * @param csFactory     factory for creating {@link CoordinateSystem} instances.
     * @param crsFactory    factory for creating {@link GeodeticCRS} instances.
     */
    public GIGS3004(final DatumFactory datumFactory, final CSFactory csFactory, final CRSFactory crsFactory) {
        super(csFactory, crsFactory);
        this.crsFactory   = crsFactory;
        this.epsgFactory  = new EPSGMock(units, datumFactory, csFactory, validators);
        ellipsoidData     = new GIGS3002(datumFactory);
        primeMeridianData = new GIGS3003(datumFactory);
        ellipsoidData.skipTests = true;
        primeMeridianData.skipTests = true;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isFactoryPreservingUserValues}</li>
     *       <li>{@linkplain GIGS3002#datumFactory}</li>
     *       <li>{@code csFactory}</li>
     *       <li>{@linkplain #crsFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.datumFactory, ellipsoidData.datumFactory));
        assertNull(op.put(Configuration.Key.csFactory, epsgFactory.getCSFactory()));
        assertNull(op.put(Configuration.Key.crsFactory, crsFactory));
        return op;
    }

    /**
     * Returns the geodetic datum instance to be tested. When this method is invoked for the first time,
     * it creates the geodetic datum to test by invoking the corresponding method from {@link DatumFactory}
     * with the current {@link #properties properties} map in argument.
     * The created object is then cached and returned in all subsequent invocations of this method.
     *
     * @return the geodetic datum instance to test.
     * @throws FactoryException if an error occurred while creating the geodetic datum instance.
     */
    @Override
    public GeodeticDatum getIdentifiedObject() throws FactoryException {
        if (datum == null) {
            assumeNotNull(ellipsoidData.datumFactory);
            datum = ellipsoidData.datumFactory.createGeodeticDatum(properties,
                    ellipsoidData.getIdentifiedObject(), primeMeridianData.getIdentifiedObject());
        }
        return datum;
    }

    /**
     * Creates a geographic CRS for the given properties and verify its properties after construction.
     *
     * @param  crsCode  the GIGS code of the CRS to create.
     * @param  crsName  the GIGS name of the CRS to create.
     * @param  csCode   the EPSG code of the coordinate system.
     * @throws FactoryException if an error occurred while creating the CRS instance.
     */
    @SuppressWarnings("null")
    private void createAndVerifyGeographicCRS(final int crsCode, final String crsName, final int csCode)
            throws FactoryException
    {
        if (crsFactory != null) {
            final GeographicCRS crs = crsFactory.createGeographicCRS(properties(crsCode, crsName),
                    getIdentifiedObject(), epsgFactory.createEllipsoidalCS(String.valueOf(csCode)));
            assertNotNull("CRSFactory.createGeographicCRS(…) shall not return null.", crs);
            validators.validate(crs);
            verifyIdentification(crs, crsName, String.valueOf(crsCode));
            /*
             * Verify axes: may be two- or three-dimensional, (φ,λ) or (λ,φ) order, in angular degrees or grads.
             * Those properties are inferred from the coordinate system code.
             */
            Unit<Angle> angularUnit = units.degree();
            AxisDirection[] directions = GIGS2004.GEOGRAPHIC_2D;
            switch (csCode) {
                case 6403: angularUnit = units.grad(); break;
                case 6423: directions = GIGS2004.GEOGRAPHIC_3D; break;
                case 6424: directions = GIGS2004.GEOGRAPHIC_XY; break;
            }
            verifyCoordinateSystem(crs.getCoordinateSystem(), EllipsoidalCS.class,
                    directions, angularUnit, angularUnit, units.metre());
        }
    }

    /**
     * Creates a geocentric CRS for the given properties and verify its properties after construction.
     *
     * @param  crsCode  the GIGS code of the CRS to create.
     * @param  crsName  the GIGS name of the CRS to create.
     * @param  csCode   the EPSG code of the coordinate system.
     * @throws FactoryException if an error occurred while creating the CRS instance.
     */
    @SuppressWarnings("null")
    private void createAndVerifyGeocentricCRS(final int crsCode, final String crsName, final int csCode)
            throws FactoryException
    {
        if (crsFactory != null) {
            final GeocentricCRS crs = crsFactory.createGeocentricCRS(properties(crsCode, crsName),
                    getIdentifiedObject(), epsgFactory.createCartesianCS(String.valueOf(csCode)));
            assertNotNull("CRSFactory.createGeocentricCRS(…) shall not return null.", crs);
            validators.validate(crs);
            verifyIdentification(crs, crsName, String.valueOf(crsCode));
            verifyCoordinateSystem(crs.getCoordinateSystem(), CartesianCS.class, GIGS2004.GEOCENTRIC, units.metre());
        }
    }

    /**
     * Verifies the properties of the geodetic datum given by {@link #getIdentifiedObject()}.
     */
    final void verifyDatum() throws FactoryException {
        if (skipTests) {
            return;
        }
        final String name = getName();
        final String code = getCode();
        final String anchorPoint = (String) properties.get(GeodeticDatum.ANCHOR_POINT_KEY);
        final GeodeticDatum datum = getIdentifiedObject();
        assertNotNull("GeodeticDatum", datum);
        validators.validate(datum);

        verifyIdentification(datum, name, code);
        copyConfigurationTo(ellipsoidData, primeMeridianData);

        ellipsoidData.skipTests = false;
        ellipsoidData.setIdentifiedObject(datum.getEllipsoid());
        ellipsoidData.verifyEllipsoid();

        primeMeridianData.skipTests = false;
        primeMeridianData.setIdentifiedObject(datum.getPrimeMeridian());
        primeMeridianData.verifyPrimeMeridian();

        if (anchorPoint != null) {
            final InternationalString actual = datum.getAnchorPoint();
            assertNotNull("GeodeticDatum.getAnchorPoint()", actual);
            assertEquals ("GeodeticDatum.getAnchorPoint()", anchorPoint, actual.toString());
        }
    }

    /**
     * Tests “GIGS geodetic datum A” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66001</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum A</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid A</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64001 – GIGS geocenCRS A</b></li>
     *   <li>EPSG equivalence: <b>4978 – WGS 84</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testWGS84()
     */
    @Test
    public void testWGS84() throws FactoryException {
        setCodeAndName(66001, "GIGS geodetic datum A");
        ellipsoidData.testWGS84();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeocentricCRS(64001, "GIGS geocenCRS A", 6500);
        createAndVerifyGeographicCRS(64002, "GIGS geog3DCRS A", 6423);
        createAndVerifyGeographicCRS(64003, "GIGS geogCRS A", 6422);
        createAndVerifyGeographicCRS(64004, "GIGS geogCRS Alonlat", 6424);
        createAndVerifyGeographicCRS(64033, "GIGS geogCRS Agr", 6403);
    }

    /**
     * Tests “GIGS geodetic datum B” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66002</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum B</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid B</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64019 – GIGS geog3DCRS B</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testOSGB1936()
     */
    @Test
    public void testOSGB1936() throws FactoryException {
        setCodeAndName(66002, "GIGS geodetic datum B");
        ellipsoidData.testAiry();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64019, "GIGS geog3DCRS B", 6423);
        createAndVerifyGeographicCRS(64005, "GIGS geogCRS B", 6422);
    }

    /**
     * Tests “GIGS geodetic datum C” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66003</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum C</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid C</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64021 – GIGS geog3DCRS C</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testAmersfoort()
     */
    @Test
    public void testAmersfoort() throws FactoryException {
        setCodeAndName(66003, "GIGS geodetic datum C");
        ellipsoidData.testBessel();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64021, "GIGS geog3DCRS C", 6423);
        createAndVerifyGeographicCRS(64006, "GIGS geogCRS C", 6422);
    }

    /**
     * Tests “GIGS geodetic datum D” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66004</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum D</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid C</b></li>
     *   <li>Prime meridian name: <b>GIGS PM D</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64007 – GIGS geogCRS D</b></li>
     *   <li>EPSG equivalence: <b>4813 – Batavia (Jakarta)</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testBatavia_Jakarta()
     */
    @Test
    public void testBatavia_Jakarta() throws FactoryException {
        setCodeAndName(66004, "GIGS geodetic datum D");
        ellipsoidData.testBessel();
        primeMeridianData.testJakarta();
        verifyDatum();
        createAndVerifyGeographicCRS(64007, "GIGS geogCRS D", 6422);
    }

    /**
     * Tests “GIGS geodetic datum E” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66005</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum E</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid E</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64022 – GIGS geog3DCRS E</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testBelge1972()
     */
    @Test
    public void testBelge1972() throws FactoryException {
        setCodeAndName(66005, "GIGS geodetic datum E");
        ellipsoidData.testInternational1924();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64022, "GIGS geog3DCRS E", 6423);
        createAndVerifyGeographicCRS(64008, "GIGS geogCRS E", 6422);
    }

    /**
     * Tests “GIGS geodetic datum F” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66006</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum F</b></li>
     *   <li>GIGS datum anchor point: <b>Origin F</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid F</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64009 – GIGS geogCRS F</b></li>
     *   <li>EPSG equivalence: <b>4283 – GDA94</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testGDA94()
     */
    @Test
    public void testGDA94() throws FactoryException {
        setCodeAndName(66006, "GIGS geodetic datum F");
        assertNull(GeodeticDatum.ANCHOR_POINT_KEY, properties.put(GeodeticDatum.ANCHOR_POINT_KEY, "Origin F"));
        ellipsoidData.testGRS1980();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64009, "GIGS geogCRS F", 6422);
    }

    /**
     * Tests “GIGS geodetic datum G” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66007</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum G</b></li>
     *   <li>GIGS datum anchor point: <b>Origin G</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid F</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64010 – GIGS geogCRS G</b></li>
     *   <li>EPSG equivalence: <b>4258 – ETRS89</b>, <b>4742 – GDM2000</b>, <b>4152 – NAD83(HARN)</b>, <b>4190 – POSGAR98</b>, <b>4674 – SIRGAS 2000</b></li>
     *   <li>Specific usage / Remarks: <b>This GIGS CRS is functionally equivalent to any ITRS realisation using the GRS 1980 ellipsoid.</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testETRS89()
     */
    @Test
    public void testETRS89() throws FactoryException {
        setCodeAndName(66007, "GIGS geodetic datum G");
        assertNull(GeodeticDatum.ANCHOR_POINT_KEY, properties.put(GeodeticDatum.ANCHOR_POINT_KEY, "Origin G"));
        ellipsoidData.testGRS1980();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64010, "GIGS geogCRS G", 6422);
    }

    /**
     * Tests “GIGS geodetic datum H” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66008</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum H</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid H</b></li>
     *   <li>Prime meridian name: <b>GIGS PM H</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64011 – GIGS geogCRS H</b></li>
     *   <li>EPSG equivalence: <b>4807 – NTF (Paris)</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testNTF_Paris()
     */
    @Test
    public void testNTF_Paris() throws FactoryException {
        setCodeAndName(66008, "GIGS geodetic datum H");
        ellipsoidData.testClarkeIGN();
        primeMeridianData.testParis();
        verifyDatum();
        createAndVerifyGeographicCRS(64011, "GIGS geogCRS H", 6403);
    }

    /**
     * Tests “GIGS geodetic datum J” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66009</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum J</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid J</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64012 – GIGS geogCRS J</b></li>
     *   <li>EPSG equivalence: <b>4267 – NAD27</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testNAD27()
     */
    @Test
    public void testNAD27() throws FactoryException {
        setCodeAndName(66009, "GIGS geodetic datum J");
        ellipsoidData.testClarke1866();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64012, "GIGS geogCRS J", 6422);
    }

    /**
     * Tests “GIGS geodetic datum K” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66012</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum K</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid K</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64015 – GIGS geogCRS K</b></li>
     *   <li>EPSG equivalence: <b>4237 – HD72</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testHD72()
     */
    @Test
    public void testHD72() throws FactoryException {
        setCodeAndName(66012, "GIGS geodetic datum K");
        ellipsoidData.testGRS1967();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64015, "GIGS geogCRS K", 6422);
    }

    /**
     * Tests “GIGS geodetic datum L” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66011</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum L</b></li>
     *   <li>GIGS datum anchor point: <b>Origin L</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid C</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64014 – GIGS geogCRS L</b></li>
     *   <li>EPSG equivalence: <b>4211 – Batavia</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testBatavia()
     */
    @Test
    public void testBatavia() throws FactoryException {
        setCodeAndName(66011, "GIGS geodetic datum L");
        assertNull(GeodeticDatum.ANCHOR_POINT_KEY, properties.put(GeodeticDatum.ANCHOR_POINT_KEY, "Origin L"));
        ellipsoidData.testBessel();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64014, "GIGS geogCRS L", 6422);
    }

    /**
     * Tests “GIGS geodetic datum M” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66016</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum M</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid E</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64020 – GIGS geogCRS M</b></li>
     *   <li>EPSG equivalence: <b>4230 – ED50</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testED50()
     */
    @Test
    public void testED50() throws FactoryException {
        setCodeAndName(66016, "GIGS geodetic datum M");
        ellipsoidData.testInternational1924();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64020, "GIGS geogCRS M", 6422);
    }

    /**
     * Tests “GIGS geodetic datum T” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66010</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum T</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid H</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64013 – GIGS geogCRS T</b></li>
     *   <li>EPSG equivalence: <b>4275 – NTF</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testNTF()
     */
    @Test
    public void testNTF() throws FactoryException {
        setCodeAndName(66010, "GIGS geodetic datum T");
        ellipsoidData.testClarkeIGN();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64013, "GIGS geogCRS T", 6403);
    }

    /**
     * Tests “GIGS geodetic datum X” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66013</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum X</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid X</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64016 – GIGS geogCRS X</b></li>
     *   <li>EPSG equivalence: <b>4202 – AGD66</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testAGD66()
     */
    @Test
    public void testAGD66() throws FactoryException {
        setCodeAndName(66013, "GIGS geodetic datum X");
        ellipsoidData.testAustralianNationalSpheroid();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64016, "GIGS geogCRS X", 6422);
    }

    /**
     * Tests “GIGS geodetic datum Y” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66014</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum Y</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid Y</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64017 – GIGS geogCRS Y</b></li>
     *   <li>EPSG equivalence: <b>4284 – Pulkovo 1942</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testPulkovo1942()
     */
    @Test
    public void testPulkovo1942() throws FactoryException {
        setCodeAndName(66014, "GIGS geodetic datum Y");
        ellipsoidData.testKrassowsky();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64017, "GIGS geogCRS Y", 6422);
    }

    /**
     * Tests “GIGS geodetic datum Z” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66015</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum Z</b></li>
     *   <li>GIGS datum anchor point: <b>Origin Z</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid F</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64018 – GIGS geogCRS Z</b></li>
     *   <li>EPSG equivalence: <b>4269 – NAD83</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see GIGS2004#testNAD83()
     */
    @Test
    public void testNAD83() throws FactoryException {
        setCodeAndName(66015, "GIGS geodetic datum Z");
        assertNull(GeodeticDatum.ANCHOR_POINT_KEY, properties.put(GeodeticDatum.ANCHOR_POINT_KEY, "Origin Z"));
        ellipsoidData.testGRS1980();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64018, "GIGS geogCRS Z", 6422);
    }

    /**
     * Tests “GIGS geodetic datum AA” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66326</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum AA</b></li>
     *   <li>Ellipsoid name: <b>WGS 84</b></li>
     *   <li>Prime meridian name: <b>Greenwich</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64326 – GIGS geogCRS AA</b></li>
     *   <li>EPSG equivalence: <b>4326 – WGS 84</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testWGS84()
     */
    @Test
    public void testWGS84_bis() throws FactoryException {
        setCodeAndName(66326, "GIGS geodetic datum AA");
        ellipsoidData.testWGS84();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64326, "GIGS geogCRS AA", 6422);
    }

    /**
     * Tests “GIGS geodetic datum BB” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66277</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum BB</b></li>
     *   <li>Ellipsoid name: <b>Airy 1830</b></li>
     *   <li>Prime meridian name: <b>Greenwich</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64277 – GIGS geogCRS BB</b></li>
     *   <li>EPSG equivalence: <b>4277 – OSGB 1936</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testOSGB1936()
     */
    @Test
    public void testOSGB1936_bis() throws FactoryException {
        setCodeAndName(66277, "GIGS geodetic datum BB");
        ellipsoidData.testAiry();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64277, "GIGS geogCRS BB", 6422);
    }

    /**
     * Tests “GIGS geodetic datum CC” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66289</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum CC</b></li>
     *   <li>Ellipsoid name: <b>Bessel 1841</b></li>
     *   <li>Prime meridian name: <b>Greenwich</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64289 – GIGS geogCRS CC</b></li>
     *   <li>EPSG equivalence: <b>4289 – Amersfoort</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testAmersfoort()
     */
    @Test
    public void testAmersfoort_bis() throws FactoryException {
        setCodeAndName(66289, "GIGS geodetic datum CC");
        ellipsoidData.testBessel();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64289, "GIGS geogCRS CC", 6422);
    }

    /**
     * Tests “GIGS geodetic datum DD” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66813</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum DD</b></li>
     *   <li>Ellipsoid name: <b>Bessel 1841</b></li>
     *   <li>Prime meridian name: <b>Jakarta</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64813 – GIGS geogCRS DD</b></li>
     *   <li>EPSG equivalence: <b>4813 – Batavia (Jakarta)</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testBatavia_Jakarta()
     */
    @Test
    public void testBatavia_Jakarta_bis() throws FactoryException {
        setCodeAndName(66813, "GIGS geodetic datum DD");
        ellipsoidData.testBessel();
        primeMeridianData.testJakarta();
        verifyDatum();
        createAndVerifyGeographicCRS(64813, "GIGS geogCRS DD", 6422);
    }

    /**
     * Tests “GIGS geodetic datum EE” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66313</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum EE</b></li>
     *   <li>Ellipsoid name: <b>International 1924</b></li>
     *   <li>Prime meridian name: <b>Greenwich</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64313 – GIGS geogCRS EE</b></li>
     *   <li>EPSG equivalence: <b>4313 – Belge 1972</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testBelge1972()
     */
    @Test
    public void testBelge1972_bis() throws FactoryException {
        setCodeAndName(66313, "GIGS geodetic datum EE");
        ellipsoidData.testInternational1924();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64313, "GIGS geogCRS EE", 6422);
    }

    /**
     * Tests “GIGS geodetic datum FF” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66283</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum FF</b></li>
     *   <li>Ellipsoid name: <b>GRS 1980</b></li>
     *   <li>Prime meridian name: <b>Greenwich</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64283 – GIGS geogCRS FF</b></li>
     *   <li>EPSG equivalence: <b>4283 – GDA94</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testGDA94()
     */
    @Test
    public void testGDA94_bis() throws FactoryException {
        setCodeAndName(66283, "GIGS geodetic datum FF");
        ellipsoidData.testGRS1980();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64283, "GIGS geogCRS FF", 6422);
    }

    /**
     * Tests “GIGS geodetic datum HH” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66807</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum HH</b></li>
     *   <li>Ellipsoid name: <b>Clarke 1880 (IGN)</b></li>
     *   <li>Prime meridian name: <b>Paris</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64807 – GIGS geogCRS HH</b></li>
     *   <li>EPSG equivalence: <b>4807 – NTF(Paris)</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testNTF_Paris()
     */
    @Test
    public void testNTF_Paris_bis() throws FactoryException {
        setCodeAndName(66807, "GIGS geodetic datum HH");
        ellipsoidData.testClarkeIGN();
        primeMeridianData.testParis();
        verifyDatum();
        createAndVerifyGeographicCRS(64807, "GIGS geogCRS HH", 6422);
    }

    /**
     * Tests “GIGS geodetic datum ZZ” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66269</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum ZZ</b></li>
     *   <li>Ellipsoid name: <b>GRS 1980</b></li>
     *   <li>Prime meridian name: <b>Greenwich</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64269 – GIGS geogCRS ZZ</b></li>
     *   <li>EPSG equivalence: <b>4269 – NAD83</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testNAD83()
     */
    @Test
    public void testNAD83_bis() throws FactoryException {
        setCodeAndName(66269, "GIGS geodetic datum ZZ");
        ellipsoidData.testGRS1980();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64269, "GIGS geogCRS ZZ", 6422);
    }

    /**
     * Tests “GIGS geodetic datum B′” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66017</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum B′</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid B</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64023 – GIGS geogCRS B′</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testOSGB1936()
     */
    @Test
    public void testOSGB1936_p1() throws FactoryException {
        setCodeAndName(66017, "GIGS geodetic datum B′");
        ellipsoidData.testAiry();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64023, "GIGS geogCRS B′", 6422);
        createAndVerifyGeographicCRS(64024, "GIGS geog3DCRS B′", 6423);
    }

    /**
     * Tests “GIGS geodetic datum C′” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66018</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum C′</b></li>
     *   <li>GIGS datum anchor point: <b>Origin C</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid C</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64025 – GIGS geogCRS C′</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testAmersfoort()
     */
    @Test
    public void testAmersfoort_p1() throws FactoryException {
        setCodeAndName(66018, "GIGS geodetic datum C′");
        assertNull(GeodeticDatum.ANCHOR_POINT_KEY, properties.put(GeodeticDatum.ANCHOR_POINT_KEY, "Origin C"));
        ellipsoidData.testBessel();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64025, "GIGS geogCRS C′", 6422);
        createAndVerifyGeographicCRS(64026, "GIGS geog3DCRS C′", 6423);
    }

    /**
     * Tests “GIGS geodetic datum E′” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66023</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum E′</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid E</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64027 – GIGS geogCRS E′</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testBelge1972()
     */
    @Test
    public void testBelge1972_p1() throws FactoryException {
        setCodeAndName(66023, "GIGS geodetic datum E′");
        ellipsoidData.testInternational1924();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64027, "GIGS geogCRS E′", 6422);
        createAndVerifyGeographicCRS(64028, "GIGS geog3DCRS E′", 6423);
    }

    /**
     * Tests “GIGS geodetic datum J′” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66021</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum J′</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid J</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64029 – GIGS geogCRS J′</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testNAD27()
     */
    @Test
    public void testNAD27_p1() throws FactoryException {
        setCodeAndName(66021, "GIGS geodetic datum J′");
        ellipsoidData.testClarke1866();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64029, "GIGS geogCRS J′", 6422);
    }

    /**
     * Tests “GIGS geodetic datum J″” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66019</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum J″</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid J</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64030 – GIGS geogCRS J″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testNAD27()
     */
    @Test
    public void testNAD27_p2() throws FactoryException {
        setCodeAndName(66019, "GIGS geodetic datum J″");
        ellipsoidData.testClarke1866();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64030, "GIGS geogCRS J″", 6422);
    }

    /**
     * Tests “GIGS geodetic datum J‴” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66020</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum J‴</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid J</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64031 – GIGS geogCRS J‴</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     *
     * @see #testNAD27()
     */
    @Test
    public void testNAD27_p3() throws FactoryException {
        setCodeAndName(66020, "GIGS geodetic datum J‴");
        ellipsoidData.testClarke1866();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64031, "GIGS geogCRS J‴", 6422);
    }

    /**
     * Tests “GIGS geodetic datum X′” geodetic datum creation from the factory.
     *
     * <ul>
     *   <li>GIGS datum code: <b>66022</b></li>
     *   <li>GIGS datum name: <b>GIGS geodetic datum X′</b></li>
     *   <li>Ellipsoid name: <b>GIGS ellipsoid X</b></li>
     *   <li>Prime meridian name: <b>GIGS PM A</b></li>
     *   <li>GIGS coordinate reference system using the datum: <b>64032 – GIGS geogCRS X′</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the datum or a CRS from the properties.
     */
    @Test
    public void testAGD66_p1() throws FactoryException {
        setCodeAndName(66022, "GIGS geodetic datum X′");
        ellipsoidData.testAustralianNationalSpheroid();
        primeMeridianData.testGreenwich();
        verifyDatum();
        createAndVerifyGeographicCRS(64032, "GIGS geogCRS X′", 6422);
    }
}
