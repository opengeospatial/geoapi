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
import javax.measure.quantity.Length;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.test.Configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies that the software allows correct definition of a user-defined ellipsoid.
 *
 * <table class="gigs" summary="Test description"><tr>
 *   <th>Test method:</th>
 *   <td>Create user-defined ellipsoid for each of several different ellipsoids.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_3002_userEllipsoid.csv">{@code GIGS_3002_userEllipsoid.csv}</a>.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link DatumFactory#createEllipsoid(Map, double, double, Unit)} and<br>
 *       {@link DatumFactory#createFlattenedSphere(Map, double, double, Unit)}.</td>
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
 *import org.opengis.test.referencing.gigs.GIGS3002;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS3002 {
 *    public MyTest() {
 *        super(new MyDatumFactory());
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
public strictfp class GIGS3002 extends UserObjectFactoryTestCase<Ellipsoid> {
    /**
     * The ellipsoid semi-major axis length, in unit of {@link #axisUnit}.
     */
    public double semiMajorAxis;

    /**
     * The ellipsoid semi-minor axis length, in unit of {@link #axisUnit}.
     */
    public double semiMinorAxis;

    /**
     * The {@link #semiMajorAxis} and {@link #semiMinorAxis} unit of measurement.
     */
    public Unit<Length> axisUnit;

    /**
     * The inverse flattening factor (dimensionless),
     * or {@link Double#POSITIVE_INFINITY} if the ellipsoid is a sphere.
     */
    public double inverseFlattening;

    /**
     * {@code false} if the second defining parameter is the {@link #semiMinorAxis} length, or
     * {@code true} if it is the {@link #inverseFlattening}.
     */
    public boolean isIvfDefinitive;

    /**
     * {@code true} if the ellipsoid is a sphere. In such case, {@link #semiMinorAxis} = {@link #semiMajorAxis}
     * and {@link #inverseFlattening} is infinite.
     */
    public boolean isSphere;

    /**
     * The ellipsoid created by the factory,
     * or {@code null} if not yet created or if the ellipsoid creation failed.
     *
     * @see #datumFactory
     */
    private Ellipsoid ellipsoid;

    /**
     * Factory to use for building {@link Ellipsoid} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final DatumFactory datumFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS3002} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(DatumFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param datumFactory  factory for creating {@link Ellipsoid} instances.
     */
    public GIGS3002(final DatumFactory datumFactory) {
        super(datumFactory);
        this.datumFactory = datumFactory;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isFactoryPreservingUserValues}</li>
     *       <li>{@linkplain #datumFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.datumFactory, datumFactory));
        return op;
    }

    /**
     * Sets the ellipsoid instance to verify. This method is invoked only by other test classes which need to
     * verify the ellipsoid contained in a geodetic datum instead than the ellipsoid immediately after creation.
     */
    final void setIdentifiedObject(final Ellipsoid instance) {
        ellipsoid = instance;
    }

    /**
     * Returns the ellipsoid instance to be tested. When this method is invoked for the first time,
     * it creates the ellipsoid to test by invoking the corresponding method from {@link DatumFactory}
     * with the current {@link #properties properties} map in argument.
     * The created object is then cached and returned in all subsequent invocations of this method.
     *
     * @return the ellipsoid instance to test.
     * @throws FactoryException if an error occurred while creating the ellipsoid instance.
     */
    @Override
    public Ellipsoid getIdentifiedObject() throws FactoryException {
        if (ellipsoid == null) {
            assumeNotNull(datumFactory);
            if (isIvfDefinitive) {
                ellipsoid = datumFactory.createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, axisUnit);
            } else {
                ellipsoid = datumFactory.createEllipsoid(properties, semiMajorAxis, semiMinorAxis, axisUnit);
            }
        }
        return ellipsoid;
    }

    /**
     * Verifies the properties of the ellipsoid given by {@link #getIdentifiedObject()}.
     */
    final void verifyEllipsoid() throws FactoryException {
        if (skipTests) {
            return;
        }
        final String name = getName();
        final String code = getCode();
        final Ellipsoid ellipsoid = getIdentifiedObject();
        assertNotNull("Ellipsoid", ellipsoid);
        validators.validate(ellipsoid);

        verifyFlattenedSphere(ellipsoid, name, semiMajorAxis, inverseFlattening, axisUnit);
        verifyIdentification(ellipsoid, name, code);
        if (isFactoryPreservingUserValues) {
            configurationTip = Configuration.Key.isFactoryPreservingUserValues;
            assertEquals("Ellipsoid.getAxisUnit()",          axisUnit,          ellipsoid.getAxisUnit());
            assertEquals("Ellipsoid.getSemiMajorAxis()",     semiMajorAxis,     ellipsoid.getSemiMajorAxis(),     TOLERANCE*semiMajorAxis);
            assertEquals("Ellipsoid.getSemiMinorAxis()",     semiMinorAxis,     ellipsoid.getSemiMinorAxis(),     TOLERANCE*semiMinorAxis);
            assertEquals("Ellipsoid.getInverseFlattening()", inverseFlattening, ellipsoid.getInverseFlattening(), TOLERANCE*inverseFlattening);
            assertEquals("Ellipsoid.isIvfDefinitive()",      isIvfDefinitive,   ellipsoid.isIvfDefinitive());
            assertEquals("Ellipsoid.isSphere()",             isSphere,          ellipsoid.isSphere());
            configurationTip = null;
        }
    }

    /**
     * Tests “GIGS ellipsoid A” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67030</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid A</b></li>
     *   <li>EPSG equivalence: <b>7030 – WGS 84</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378137.0 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356752.314247833 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.2572236</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testWGS84()
     */
    @Test
    public void testWGS84() throws FactoryException {
        setCodeAndName(67030, "GIGS ellipsoid A");
        semiMajorAxis     = 6378137.0;
        semiMinorAxis     = 6356752.314247833;
        axisUnit          = units.metre();
        inverseFlattening = 298.2572236;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid B” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67001</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid B</b></li>
     *   <li>EPSG equivalence: <b>7001 – Airy 1830</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377563.396 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356256.909237285 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.3249646</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testAiry()
     */
    @Test
    public void testAiry() throws FactoryException {
        setCodeAndName(67001, "GIGS ellipsoid B");
        semiMajorAxis     = 6377563.396;
        semiMinorAxis     = 6356256.909237285;
        axisUnit          = units.metre();
        inverseFlattening = 299.3249646;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid C” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67004</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid C</b></li>
     *   <li>EPSG equivalence: <b>7004 – Bessel 1841</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377397.155 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356078.962818189 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.1528128</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testBessel()
     */
    @Test
    public void testBessel() throws FactoryException {
        setCodeAndName(67004, "GIGS ellipsoid C");
        semiMajorAxis     = 6377397.155;
        semiMinorAxis     = 6356078.962818189;
        axisUnit          = units.metre();
        inverseFlattening = 299.1528128;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid E” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67022</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid E</b></li>
     *   <li>EPSG equivalence: <b>7022 – International 1924</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378388.0 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356911.9461279465 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>297</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testInternational1924()
     */
    @Test
    public void testInternational1924() throws FactoryException {
        setCodeAndName(67022, "GIGS ellipsoid E");
        semiMajorAxis     = 6378388.0;
        semiMinorAxis     = 6356911.9461279465;
        axisUnit          = units.metre();
        inverseFlattening = 297.0;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid F” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67019</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid F</b></li>
     *   <li>EPSG equivalence: <b>7019 – GRS 1980</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378.137 kilometres (6378137.0 metres)</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356.7523141402835 kilometres (6356752.3141402835 metres)</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.2572221</b></li>
     *   <li>Specific usage / Remarks: <b>not metres</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testGRS1980()
     */
    @Test
    public void testGRS1980() throws FactoryException {
        setCodeAndName(67019, "GIGS ellipsoid F");
        semiMajorAxis     = 6378.137;
        semiMinorAxis     = 6356.7523141402835;
        axisUnit          = units.kilometre();
        inverseFlattening = 298.2572221;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid H” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67011</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid H</b></li>
     *   <li>EPSG equivalence: <b>7011 – Clarke 1880 (IGN)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378249.2 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356515.0 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>293.4660212936269</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and b. Calculated 1/f = 293.4660213</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testClarkeIGN()
     */
    @Test
    public void testClarkeIGN() throws FactoryException {
        setCodeAndName(67011, "GIGS ellipsoid H");
        semiMajorAxis     = 6378249.2;
        semiMinorAxis     = 6356515.0;
        axisUnit          = units.metre();
        inverseFlattening = 293.4660212936269;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid I” sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67052</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid I</b></li>
     *   <li>EPSG equivalence: <b>7052 – Clarke 1866 Authalic Sphere</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6370997.0 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6370997.0 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>Infinity</b></li>
     *   <li>Specific usage / Remarks: <b>Sphere</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testClarkeAuthalicSphere()
     */
    @Test
    public void testClarkeAuthalicSphere() throws FactoryException {
        setCodeAndName(67052, "GIGS ellipsoid I");
        semiMajorAxis     = 6370997.0;
        semiMinorAxis     = 6370997.0;
        axisUnit          = units.metre();
        inverseFlattening = Double.POSITIVE_INFINITY;
        isSphere          = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid J” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67008</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid J</b></li>
     *   <li>EPSG equivalence: <b>7008 – Clarke 1866</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>20925832.16 US survey foot (6378206.4 metres)</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>20854892.013176885 US survey foot (6356583.807100443 metres)</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>294.9786982</b></li>
     *   <li>Specific usage / Remarks: <b>not metres</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testClarke1866()
     */
    @Test
    public void testClarke1866() throws FactoryException {
        setCodeAndName(67008, "GIGS ellipsoid J");
        semiMajorAxis     = 20925832.16;
        semiMinorAxis     = 20854892.013176885;
        axisUnit          = units.footSurveyUS();
        inverseFlattening = 294.9786982;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid K” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67036</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid K</b></li>
     *   <li>EPSG equivalence: <b>7036 – GRS 1967</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378160.0 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356774.516088779 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.2471674</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testGRS1967()
     */
    @Test
    public void testGRS1967() throws FactoryException {
        setCodeAndName(67036, "GIGS ellipsoid K");
        semiMajorAxis     = 6378160.0;
        semiMinorAxis     = 6356774.516088779;
        axisUnit          = units.metre();
        inverseFlattening = 298.2471674;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid X” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67003</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid X</b></li>
     *   <li>EPSG equivalence: <b>7003 – Australian National Spheroid</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378160.0 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356774.719195306 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.25</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testAustralianNationalSpheroid()
     */
    @Test
    public void testAustralianNationalSpheroid() throws FactoryException {
        setCodeAndName(67003, "GIGS ellipsoid X");
        semiMajorAxis     = 6378160.0;
        semiMinorAxis     = 6356774.719195306;
        axisUnit          = units.metre();
        inverseFlattening = 298.25;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }

    /**
     * Tests “GIGS ellipsoid Y” flattened sphere creation from the factory.
     *
     * <ul>
     *   <li>GIGS ellipsoid code: <b>67024</b></li>
     *   <li>GIGS ellipsoid name: <b>GIGS ellipsoid Y</b></li>
     *   <li>EPSG equivalence: <b>7024 – Krassowsky 1940</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378245.0 metres</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356863.018773047 metres</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.3</b></li>
     *   <li>Specific usage / Remarks: <b>Defined using a and 1/f</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the properties.
     *
     * @see GIGS2002#testKrassowsky()
     */
    @Test
    public void testKrassowsky() throws FactoryException {
        setCodeAndName(67024, "GIGS ellipsoid Y");
        semiMajorAxis     = 6378245.0;
        semiMinorAxis     = 6356863.018773047;
        axisUnit          = units.metre();
        inverseFlattening = 298.3;
        isIvfDefinitive   = true;
        verifyEllipsoid();
    }
}
