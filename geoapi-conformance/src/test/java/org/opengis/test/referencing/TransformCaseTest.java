/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test.referencing;

import java.util.Random;
import java.awt.geom.AffineTransform;
import org.opengis.referencing.operation.TransformException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link TransformTestCase} using {@link java.awt.geom.AffineTransform}
 * as a reference transform.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public final class TransformCaseTest extends TransformTestCase {
    /**
     * The rotation angle to apply on the affine transform. Incremented
     * in order to get different transforms for each test.
     */
    private static double rotation;

    /**
     * Random number generator. Initialized to a constant seed in order
     * to make the tests more reproducible.
     */
    private static final Random random = new Random(534546549);

    /**
     * An array of coordinates point to be tested.
     */
    protected final float[] coordinates = new float[256];

    /**
     * Creates a new test case.
     */
    public TransformCaseTest() {
    }

    /**
     * Initializes {@link #transform} to a new affine transform.
     * A slightly different affine transform is created during
     * each initialization.
     */
    @BeforeEach
    public void initTransform() {
        final BogusAffineTransform2D work = new BogusAffineTransform2D();
        work.rotate(rotation += toRadians(5));
        work.scale(10, 20);
        work.translate(4, 6);
        transform = work;
    }

    /**
     * Initializes the {@linkplain #coordinates} array to random values.
     */
    @BeforeEach
    public void initCoordinates() {
        for (int i=0; i<coordinates.length; i++) {
            coordinates[i] = random.nextFloat()*2000f - 1000f;
        }
    }

    /**
     * Tests {@link #verifyTransform(double[], double[])} using a valid transform.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testTransform() throws TransformException {
        tolerance = 0;
        ((AffineTransform) transform).setToScale(10, 100);
        validators.validate(transform);
        verifyTransform(new double[] { 1,  4,   2,  3},
                        new double[] { 10, 400, 20, 300});

        String message = assertThrows(TransformFailure.class,
                () -> verifyTransform(new double[] { 1,  4,   2,  3},
                                      new double[] { 10, 400, 20, 300.125}))
                .getMessage();

        assertTrue(message.contains("DirectPosition2D[1]"),
                "Wrong or missing dimension and index in the error message.");
        assertTrue(message.contains("Expected POINT(20.0 300.125) but got POINT(20.0 300.0)"),
                "Wrong or missing coordinate values in the error message.");
        assertTrue(message.contains("The delta at coordinate 1 is 0.125"),
                "Wrong or missing delta value in the error message.");
    }

    /**
     * Tests {@link #verifyConsistency(float[])} using a valid transform.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testConsistencyUsingValidTransform() throws TransformException {
        tolerance = 0;
        validators.validate(transform);
        verifyConsistency(coordinates);
    }

    /**
     * Tests {@link #verifyConsistency(float[])} using a bogus transform.
     * A {@link TransformFailure} exception should be thrown.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testConsistencyUsingBogusTransform() throws TransformException {
        tolerance = 0;
        validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongFloatToFloat = true;
        assertThrows(TransformFailure.class, () -> verifyConsistency(coordinates));
    }

    /**
     * Tests {@link #verifyInverse(float[])} using a valid transform.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testInversionUsingValidTransform() throws TransformException {
        tolerance = 1E-10;
        validators.validate(transform);
        verifyInverse(coordinates);
    }

    /**
     * Tests {@link #verifyInverse(float[])} using a bogus transform.
     * A {@link TransformFailure} exception should be thrown.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testInversionUsingBogusTransform() throws TransformException {
        tolerance = 1E-10;
        validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongInverse = true;
        assertThrows(TransformFailure.class, () -> verifyInverse(coordinates));
    }

    /**
     * Tests {@link #verifyDerivative(double[])}.
     *
     * @throws TransformException should never happen.
     *
     * @since 3.1
     */
    public void testDerivative() throws TransformException {
        tolerance = 1E-10;
        derivativeDeltas = new double[] {0.1};
        validators.validate(transform);
        verifyDerivative(10, 20);
    }

    /**
     * Tests {@link #verifyDerivative(double[])} using a bogus transform.
     * A {@link TransformFailure} exception should be thrown.
     *
     * @throws TransformException should never happen.
     *
     * @since 3.1
     */
    @Test
    public void testDerivativeUsingBogusTransform() throws TransformException {
        tolerance = 1E-10;
        derivativeDeltas = new double[] {0.1};
        validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongDerivative = true;
        assertThrows(DerivativeFailure.class, () -> verifyDerivative(0, 0));
    }

    /**
     * Tests {@link TransformTestCase#verifyInDomain(double[], double[], int[], Random)}.
     *
     * @throws TransformException should never happen.
     *
     * @since 3.1
     */
    @Test
    public void testVerifyInDomain() throws TransformException {
        tolerance = 1E-10;
        derivativeDeltas = new double[] {0.1};
        validators.validate(transform);
        testVerifyInDomain(new double[] {10, 100}, new double[] {20, 400}, 10,  30);
    }

    /**
     * Implementation of {@code testVerifyInDomain} for an arbitrary number of dimensions.
     *
     * @param  min  minimum legal coordinate values.
     * @param  max  maximum legal coordinate values.
     * @param  num  the number of points along each dimension.
     * @return the generated random coordinates inside the given domain of validity.
     * @throws TransformException if a transform or a derivative cannot be computed.
     */
    private void testVerifyInDomain(final double[] min, final double[] max, final int... num)
            throws TransformException
    {
        assertEquals(num.length, min.length);
        assertEquals(num.length, max.length);
        int expectedLength = num.length;
        for (int s : num) {
            expectedLength *= s;
        }
        final float[] coordinates = verifyInDomain(min, max, num, random);
        assertEquals(expectedLength, coordinates.length);
        for (int i=0; i<coordinates.length; i++) {
            final float c = coordinates[i];
            final int   j = i % num.length;
            assertTrue(c >= min[j] && c <= max[j]);
        }
    }
}
