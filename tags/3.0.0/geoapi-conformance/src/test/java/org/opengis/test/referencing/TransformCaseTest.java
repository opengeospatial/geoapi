/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.operation.TransformException;
import org.opengis.test.Validators;
import org.junit.*;

import static org.opengis.test.Assert.*;


/**
 * Tests {@link TransformTestCase} using {@link AffineTransform} as a reference transform.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public strictfp class TransformCaseTest extends TransformTestCase {
    /**
     * The rotation angle to apply on the affine transform. Incremented
     * in order to get different transforms for each test.
     */
    private static double rotation;

    /**
     * Random number generator. Initialized to a constant seed in order
     * to make the tests more reproductible.
     */
    private static final Random random = new Random(534546549);

    /**
     * An array of coordinates point to be tested.
     */
    protected final float[] coordinates = new float[256];

    /**
     * Initializes {@link #transform} to a new affine transform.
     * A slightly different affine transform is created during
     * each initialization.
     */
    @Before
    public void initTransform() {
        final BogusAffineTransform2D work = new BogusAffineTransform2D();
        work.rotate(rotation += Math.toRadians(5));
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
     * Tests {@link #verifyTransform} using a valid transform.
     *
     * @throws TransformException Should never happen.
     */
    @Test
    public void testTransform() throws TransformException {
        tolerance = 0;
        assertAllTestsEnabled();
        ((AffineTransform2D) transform).setToScale(10, 100);
        Validators.validate(transform);
        verifyTransform(new double[] { 2,  3},
                        new double[] { 20, 300});
        try {
            verifyTransform(new double[] { 2,  3},
                            new double[] { 20, 300.01});
            fail("Expected TransformFailure exception.");
        } catch (TransformFailure e) {
            // This is the expected exception.
        }
    }

    /**
     * Tests {@link #verifyConsistency} using a valid transform.
     *
     * @throws TransformException Should never happen.
     */
    @Test
    public void testConsistencyUsingValidTransform() throws TransformException {
        tolerance = 0;
        assertAllTestsEnabled();
        Validators.validate(transform);
        verifyConsistency(coordinates);
    }

    /**
     * Tests {@link #verifyConsistency} using a bogus transform.
     * A {@link TransformFailure} exception should be thrown.
     *
     * @throws TransformException Should never happen.
     */
    @Test(expected=TransformFailure.class)
    public void testConsistencyUsingBogusTransform() throws TransformException {
        tolerance = 0;
        assertAllTestsEnabled();
        Validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongFloatToFloat = true;
        verifyConsistency(coordinates);
    }

    /**
     * Tests {@link #verifyInverse} using a valid transform.
     *
     * @throws TransformException Should never happen.
     */
    @Test
    public void testInversionUsingValidTransform() throws TransformException {
        tolerance = 1E-10;
        assertAllTestsEnabled();
        Validators.validate(transform);
        verifyInverse(coordinates);
    }

    /**
     * Tests {@link #verifyInverse} using a bogus transform.
     * A {@link TransformFailure} exception should be thrown.
     *
     * @throws TransformException Should never happen.
     */
    @Test(expected=TransformFailure.class)
    public void testInversionUsingBogusTransform() throws TransformException {
        tolerance = 1E-10;
        assertAllTestsEnabled();
        Validators.validate(transform);
        ((BogusAffineTransform2D) transform).wrongInverse = true;
        verifyInverse(coordinates);
    }
}
