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
package org.opengis.test.referencing;

import java.util.List;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.MathTransformFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.StrictMath.*;
import static org.junit.Assume.*;
import static org.opengis.test.Validators.*;
import static org.opengis.test.referencing.PseudoEpsgFactory.FEET;


/**
 * Tests {@linkplain MathTransform} from the {@code org.opengis.referencing.operation} package.
 * Math transform instances are created using the factory given at construction time.
 *
 * In order to specify their factory and run the tests in a JUnit framework, implementors can
 * define a subclass as below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.MathTransformTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends MathTransformTest {
 *    public MyTest() {
 *        super(new MyMathTransformFactory());
 *    }
 *}</pre></blockquote>
 *
 * Alternatively this test class can also be used directly in the {@link org.opengis.test.TestSuite},
 * which combine every tests defined in the GeoAPI conformance module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class MathTransformTest extends TransformTestCase {
    /**
     * The factory for creating {@link MathTransform} objects, or {@code null} if none.
     */
    protected final MathTransformFactory factory;

    /**
     * {@code true} if the {@linkplain #transform} being tested is a map projection
     * from a geographic CRS to a projected CRS. This flag shall be set together
     * with the {@link #tolerance} threshold before the {@code verify(...)} methods
     * are invoked.
     */
    private transient boolean isProjection;

    /**
     * {@code true} if the longitude (which is assumed to be the first ordinate)
     * should be ignored. This can be the case only if the latitude is at a pole,
     * in which case the longitude has no meaning.
     */
    private transient boolean isAtPole;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code MathTransformTest} constructor.
     */
    @Parameterized.Parameters
    public static List<Factory[]> factories() {
        return factories(MathTransformFactory.class);
    }

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory Factory for creating {@link MathTransform} instances.
     */
    public MathTransformTest(final MathTransformFactory factory) {
        this.factory = factory;
    }

    /**
     * Creates a {@linkplain MathTransform math transform} for the given EPSG Coordinate
     * Reference System code, and stores the result in the {@link #transform} field. The
     * set of allowed codes is documented in the {@link PseudoEpsgFactory#createParameters(int)}
     * method.
     *
     * @param  code The EPSG code of a Coordinate Reference System.
     * @throws FactoryException If the math transform for the given projected CRS can not be created.
     */
    private void createMathTransform(final int code) throws FactoryException {
        assumeNotNull(factory);
        transform = factory.createParameterizedTransform(PseudoEpsgFactory.createParameters(factory, code));
        validate(transform);
    }

    /**
     * Creates the "<cite>Mercator (variant A)</cite>" (EPSG:9804) projection documented
     * in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testMercator1SP() throws FactoryException, TransformException {
        createMathTransform(3002);  // "Makassar / NEIEZ"
        final double[] point = new double[] {
            110,  // Longitude of natural origin
              0,  // Latitude of natural origin
            120,
             -3
        };
        final double[] expected = new double[] {
            3900000.00,  // False easting
             900000.00,  // False northing
            5009726.58,
             569150.82
        };
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Creates the "<cite>Mercator (variant B)</cite>" (EPSG:9805) projection documented
     * in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testMercator2SP() throws FactoryException, TransformException {
        createMathTransform(3388);  // "Pulkovo 1942 / Caspian Sea Mercator"
        final double[] point = new double[] {
            51, // Longitude of natural origin
             0, // Latitude of natural origin
            53,
            53
        };
        final double[] expected = new double[] {
                  0.00, // False easting
                  0.00, // False northing
             165704.29,
            5171848.07};
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Creates the "<cite>Mercator Popular Visualisation Pseudo Mercator" (EPSG:1024) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testPseudoMercator() throws FactoryException, TransformException {
        createMathTransform(3857);  // "WGS 84 / Pseudo-Mercator"
        final double[] point = new double[] {
                0,
                0,
            -(100 +  20.0/60),             // 100°20'00.000"W
               24 + (22 + 54.433/60)/60};  //  24°22'54.433"N
        final double[] expected = new double[] {
                    0.00,
                    0.00,
            -11169055.58,
              2800000.00};
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Creates the "<cite>Lambert Conic Conformal (1SP)</cite>" (EPSG:9801) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testLambertConicConformal1SP() throws FactoryException, TransformException {
        createMathTransform(24200);  // "JAD69 / Jamaica National Grid"
        final double[] point = new double[] {
             -77.0,                       // Longitude of natural origin
              18.0,                       // Latitude of natural origin
            -(76 + (56 + 37.26/60)/60),   // 76°56'37.26"W
              17 + (55 + 55.80/60)/60};   // 17°55'55.80"N
        final double[] expected = new double[] {
            250000.00,  // False easting
            150000.00,  // False northing
            255966.58,
            142493.51};
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Creates the "<cite>Lambert Conic Conformal (2SP)</cite>" (EPSG:9802) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testLambertConicConformal2SP() throws FactoryException, TransformException {
        createMathTransform(32040);  // "NAD27 / Texas South Central"
        final double[] point = new double[] {
            -99.0,           // Longitude of false origin
             27 + 50.0/60,   // Latitude of false origin
            -96.0,           // 96°00'00.00"W
             28 + 30.0/60};  // 28°30'00.00"N
        final double[] expected = new double[] {
            2000000.00/FEET, // Easting at false origin
                  0.00/FEET, // Northing at false origin
            2963503.91/FEET,
             254759.80/FEET};
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Creates the "<cite>Lambert Conic Conformal (2SP Belgium)</cite>" (EPSG:9803) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testLambertConicConformalBelgium() throws FactoryException, TransformException {
        createMathTransform(31300);  // "Belge 1972 / Belge Lambert 72"
        final double[] point = new double[] {
             4 + (21 + 24.983/60)/60,   // Longitude of false origin
            90.0,                       // Latitude of false origin
             5 + (48 + 26.533/60)/60,   //  5°48'26.533"E
            50 + (40 + 46.461/60)/60};  // 50°40'46.461"N
        final double[] expected = new double[] {
            150000.01,  // Easting at false origin
           5400088.44,  // Northing at false origin
            251763.20,
            153034.13};
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Creates the "<cite>IGNF:MILLER</cite>" (EPSG:310642901) projection documented in the
     * <a href="http://api.ign.fr/geoportail/api/doc/fr/developpeur/wmsc.html">IGN documentation</a>
     * and transform the example point given by IGNF. The {@link MathTransform} result is then
     * compared with the expected result documented by IGN.
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testMiller() throws FactoryException, TransformException {
        createMathTransform(310642901);  // "IGNF:MILLER"
        final double[] point = new double[] {
             0.0,
             0.0,
             2.478917,
            48.805639};
        final double[] expected = new double[] {
                  0.00,
                  0.00,
             275951.78,
            5910061.78};
        tolerance = 0.005;
        isProjection = true;
        verifyTransform(point, expected);
    }

    /**
     * Returns the index within the given position of an ordinate value which is not approximatively
     * equals to the expected value. The default implementation performs the work documented in the
     * {@linkplain TransformTestCase#indexOfMismatch parent class} except when the coordinate to
     * compare is a geographic coordinate located at the North or South pole, in which case the
     * longitude value is essentially ignored.
     */
    @Override
    protected int indexOfMismatch(final DirectPosition expected, final DirectPosition actual, final ComparisonType mode) {
        isAtPole = isProjection && mode == ComparisonType.INVERSE_TRANSFORM && abs(expected.getOrdinate(1)) == 90;
        return super.indexOfMismatch(expected, actual, mode);
    }

    /**
     * Returns the tolerance threshold for comparing the given ordinate value. The default
     * implementation returns the {@link #tolerance} value, which shall be in metres, except
     * during the inverse transform of a map projection, in which case the tolerance value
     * is converted from metres to decimal degrees.
     */
    @Override
    protected double tolerance(final double ordinate, final int dimension, final ComparisonType mode) {
        double tolerance = super.tolerance(ordinate, dimension, mode);
        if (isProjection && mode == ComparisonType.INVERSE_TRANSFORM) {
            tolerance /= (1852 * 60); // 1 nautical miles = 1852 metres in 1 minute of angle.
            if (dimension == 0 && isAtPole) {
                tolerance = 360;
            }
        }
        return tolerance;
    }
}
