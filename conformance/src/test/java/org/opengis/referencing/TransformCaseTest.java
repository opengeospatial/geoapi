/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

import java.util.Random;
import org.opengis.referencing.operation.TransformException;
import org.junit.*;


/**
 * Tests {@link TransformTestCase} using {@link AffineTransform} as a reference transform.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
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
     * Tests {@link #testConsistency} using a valid transform.
     *
     * @throws TransformException Should never happen.
     */
    @Test
    public void testConsistencyUsingValidTransform() throws TransformException {
        testConsistency(coordinates, 0f);
    }

    /**
     * Tests {@link #testConsistency} using a bogus transform.
     * A {@link TransformFailure} exception should be thrown.
     *
     * @throws TransformException Should never happen.
     */
    @Test(expected=TransformFailure.class)
    public void testConsistencyUsingBogusTransform() throws TransformException {
        ((BogusAffineTransform2D) transform).wrongFloatToFloat = true;
        testConsistency(coordinates, 0f);
    }
}
