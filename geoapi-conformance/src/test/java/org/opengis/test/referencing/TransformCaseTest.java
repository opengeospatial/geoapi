/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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

import java.util.Random;
import java.awt.geom.AffineTransform;
import org.opengis.referencing.operation.TransformException;
import org.junit.*;

import static java.lang.StrictMath.*;
import static org.opengis.test.Assert.*;


/**
 * Tests {@link TransformTestCase} using {@link java.awt.geom.AffineTransform}
 * as a reference transform.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class TransformCaseTest extends TransformTestCase {
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
     * Default constructor without factories.
     */
    public TransformCaseTest() {
        super();
    }

    /**
     * Initializes {@link #transform} to a new affine transform.
     * A slightly different affine transform is created during
     * each initialization.
     */
    @Before
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
    @Before
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
        try {
            verifyTransform(new double[] { 1,  4,   2,  3},
                            new double[] { 10, 400, 20, 300.125});
            fail("Expected TransformFailure exception.");
        } catch (TransformFailure e) {
            // This is the expected exception.
            final String message = e.getMessage();
            assertTrue("Wrong or missing dimension and index in the error message.",
                    message.contains("DirectPosition2D[1]"));
            assertTrue("Wrong or missing coordinate values in the error message.",
                    message.contains("Expected POINT(20.0 300.125) but got POINT(20.0 300.0)"));
            assertTrue("Wrong or missing delta value in the error message.",
                    message.contains("The delta at coordinate 1 is 0.125"));
        }
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
    @Test(expected=TransformFailure.class)
    public void testConsistencyUsingBogusTransform() throws TransformException {
        tolerance = 0;
        validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongFloatToFloat = true;
        verifyConsistency(coordinates);
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
    @Test(expected=TransformFailure.class)
    public void testInversionUsingBogusTransform() throws TransformException {
        tolerance = 1E-10;
        validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongInverse = true;
        verifyInverse(coordinates);
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
    @Test(expected=DerivativeFailure.class)
    public void testDerivativeUsingBogusTransform() throws TransformException {
        tolerance = 1E-10;
        derivativeDeltas = new double[] {0.1};
        validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongDerivative = true;
        verifyDerivative(0, 0);
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
