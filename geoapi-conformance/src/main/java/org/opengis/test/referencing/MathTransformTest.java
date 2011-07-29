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
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.MathTransformFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
public class MathTransformTest extends TransformTestCase {
    /**
     * The factory for creating {@link MathTransform} objects, or {@code null} if none.
     */
    protected final MathTransformFactory factory;

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
            -(76 + (56 + 37.26/60)/60),   // 76°56'37.26"W
              17 + (55 + 55.80/60)/60};   // 17°55'55.80"N
        final double[] expected = new double[] {
            255966.58,
            142493.51};
        tolerance = 0.005;
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
            -96.0,           // 96°00'00.00"W
             28 + 30.0/60};  // 28°30'00.00"N
        final double[] expected = new double[] {
            2963503.91/FEET,
             254759.80/FEET};
        tolerance = 0.005;
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
             5 + (48 + 26.533/60)/60,   //  5°48'26.533"E
            50 + (40 + 46.461/60)/60};  // 50°40'46.461"N
        final double[] expected = new double[] {
            251763.20,
            153034.13};
        tolerance = 0.005;
        verifyTransform(point, expected);
    }
}
