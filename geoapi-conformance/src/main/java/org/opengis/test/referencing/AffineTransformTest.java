/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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

import java.util.Arrays;
import java.util.Random;
import java.awt.geom.AffineTransform;

import org.opengis.util.FactoryException;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.test.Configuration;

import org.junit.Test;

import static java.lang.StrictMath.*;
import static org.junit.Assume.*;
import static org.junit.Assert.*;
import static org.opengis.test.referencing.PseudoEpsgFactory.FEET;


/**
 * Tests {@linkplain MathTransformFactory#createAffineTransform(Matrix) affine transforms}
 * from the {@code org.opengis.referencing.operation} package. Math transform instances are
 * created using the factory given at construction time.
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * {@snippet lang="java" :
 * import org.opengis.test.referencing.AffineTransformTest;
 *
 * public class MyTest extends AffineTransformTest {
 *     public MyTest() {
 *         super(new MyMathTransformFactory());
 *     }
 * }}
 *
 * @see ParameterizedTransformTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp class AffineTransformTest extends TransformTestCase {
    /**
     * The default tolerance threshold for comparing the results of direct transforms.
     * Because affine transform are linear, only rounding errors should exist.
     */
    private static final double TRANSFORM_TOLERANCE = 1E-8;

    /**
     * The delta value to use for computing an approximation of the derivative by finite
     * difference, in metres. Because affine transform are linear, this value should
     * actually have no impact on the result.
     */
    private static final double DERIVATIVE_DELTA = 1;

    /**
     * Approximate number of points to transform in each test.
     */
    private static final int NUM_POINTS = 2500;

    /**
     * The factory for creating {@link MathTransform} objects, or {@code null} if none.
     */
    protected final MathTransformFactory mtFactory;

    /**
     * The matrix of the math transform being tested. This field is set, together with the
     * {@link #transform transform} field, after the execution of every {@code testFoo()}
     * method in this class.
     *
     * <p>If this field is non-null before a test is run, then those parameters will be used
     * directly. This allow implementers to alter the parameters before to run the test one
     * more time.</p>
     */
    protected Matrix matrix;

    /**
     * {@code true} if {@link MathTransformFactory#createAffineTransform(Matrix)} accepts
     * non-square matrixes. Transforms defined by non-square matrixes have a number of
     * input dimensions different than the number of output dimensions.
     */
    protected boolean isNonSquareMatrixSupported;

    /**
     * {@code true} if {@link MathTransformFactory#createAffineTransform(Matrix)} accepts
     * matrixes of size different than 3×3. If {@code false}, then only matrixes of
     * size 3×3 (i.e. affine transforms between two-dimensional spaces) will be tested.
     */
    protected boolean isNonBidimensionalSpaceSupported;

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory  factory for creating {@link MathTransform} instances.
     */
    public AffineTransformTest(final MathTransformFactory factory) {
        super(factory);
        mtFactory = factory;
        @SuppressWarnings("unchecked")
        final boolean[] isEnabled = getEnabledFlags(
                Configuration.Key.isNonSquareMatrixSupported,
                Configuration.Key.isNonBidimensionalSpaceSupported);
        isNonSquareMatrixSupported       = isEnabled[0];
        isNonBidimensionalSpaceSupported = isEnabled[1];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the entries defined in the {@linkplain TransformTestCase#configuration() parent class}.</li>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isNonSquareMatrixSupported}</li>
     *       <li>{@link #isNonBidimensionalSpaceSupported}</li>
     *       <li>{@link #mtFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return {@inheritDoc}
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isNonSquareMatrixSupported,       isNonSquareMatrixSupported));
        assertNull(op.put(Configuration.Key.isNonBidimensionalSpaceSupported, isNonBidimensionalSpaceSupported));
        assertNull(op.put(Configuration.Key.mtFactory,                        mtFactory));
        return op;
    }

    /**
     * Runs a test using the given Java2D affine transform as a reference.
     *
     * @param  reference  the affine transform to use as a reference for checking the results.
     * @throws FactoryException if the transform cannot be created.
     * @throws TransformException if an error occurred while testing the transform.
     */
    private void runTest(final AffineTransform reference) throws FactoryException, TransformException {
        assumeNotNull(mtFactory);
        if (matrix == null) {
            matrix = new SimpleMatrix(3, 3,
                    reference.getScaleX(), reference.getShearX(), reference.getTranslateX(),
                    reference.getShearY(), reference.getScaleY(), reference.getTranslateY(),
                                 0,              0,                  1);
        }
        if (transform == null) {
            transform = mtFactory.createAffineTransform(matrix);
            assertNotNull(transform);
        }
        final float[] coordinates = verifyInternalConsistency(reference.hashCode());
        /*
         * At this point, we have performed internal consistency check of the
         * implementer transform. Now compute the expected values using the
         * Java2D transform and compare with the implementer transform.
         */
        final double[] source = new double[coordinates.length];
        final double[] target = new double[coordinates.length];
        for (int i=0; i<coordinates.length; i++) {
            source[i] = coordinates[i];
        }
        reference.transform(source, 0, target, 0, coordinates.length/2);
        verifyTransform(source, target);
        for (int i=0; i<coordinates.length; i++) {
            assertEquals("Source array should be unmodified.", coordinates[i], source[i], 0.0);
        }
    }

    /**
     * Runs a test using the given matrix. This method checks only for internal consistency,
     * i.e. we don't have an external implementation that we can use for comparing the results.
     *
     * @param  numRow    number of rows in the matrix.
     * @param  numCol    number of columns in the matrix.
     * @param  elements  matrix element values.
     * @throws FactoryException if the transform cannot be created.
     * @throws TransformException if an error occurred while testing the transform.
     */
    private void runTest(final int numRow, final int numCol, final double... elements)
            throws FactoryException, TransformException
    {
        assumeNotNull(mtFactory);
        if (matrix == null) {
            matrix = new SimpleMatrix(numRow, numCol, elements);
        }
        if (transform == null) {
            transform = mtFactory.createAffineTransform(matrix);
            assertNotNull(transform);
        }
        verifyInternalConsistency(Arrays.hashCode(elements));
    }

    /**
     * Tests the transform consistency using many random points inside an arbitrary domain.
     *
     * @param  seed  the random seed. We recommend a constant value for each transform or CRS to be tested.
     * @return the generated random coordinates inside the arbitrary domain.
     * @throws TransformException if a point cannot be transformed.
     */
    private float[] verifyInternalConsistency(final long seed) throws TransformException {
        validators.validate(transform);
        if (!(tolerance >= TRANSFORM_TOLERANCE)) {      // !(a >= b) instead of (a < b) in order to catch NaN.
            tolerance = TRANSFORM_TOLERANCE;
        }
        final int dimension = transform.getSourceDimensions();
        final int[]    num = new int   [dimension];
        final double[] min = new double[dimension];
        final double[] max = new double[dimension];
        derivativeDeltas   = new double[dimension];
        Arrays.fill(num, (int) ceil(pow(NUM_POINTS, 1.0/num.length)));
        Arrays.fill(min, -1000);
        Arrays.fill(max, +1000);
        Arrays.fill(derivativeDeltas, DERIVATIVE_DELTA);
        return verifyInDomain(min, max, num, new Random(seed));
    }

    /**
     * Tests using an identity transform in an one-dimensional space.
     * This test is executed only if the {@link #isNonBidimensionalSpaceSupported}
     * flag is set to {@code true}.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testIdentity1D() throws FactoryException, TransformException {
        assumeTrue(isNonBidimensionalSpaceSupported);
        configurationTip = Configuration.Key.isNonBidimensionalSpaceSupported;
        runTest(2, 2,
            1, 0,
            0, 1);
        assertTrue("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using an identity transform in a two-dimensional space.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testIdentity2D() throws FactoryException, TransformException {
        runTest(new AffineTransform());
        assertTrue("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using an identity transform in a three-dimensional space.
     * This test is executed only if the {@link #isNonBidimensionalSpaceSupported}
     * flag is set to {@code true}.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testIdentity3D() throws FactoryException, TransformException {
        assumeTrue(isNonBidimensionalSpaceSupported);
        configurationTip = Configuration.Key.isNonBidimensionalSpaceSupported;
        runTest(4, 4,
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1);
        assertTrue("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests a transform swapping the axes in a two-dimensional space.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testAxisSwapping2D() throws FactoryException, TransformException {
        runTest(new AffineTransform(0, 1, 1, 0, 0, 0));
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using a 180° rotation in a two-dimensional space.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testSouthOrientated2D() throws FactoryException, TransformException {
        runTest(AffineTransform.getQuadrantRotateInstance(2));
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using a translation of (400000,-100000) metres in a two-dimensional space.
     * This translation is the (<cite>False easting</cite>, <cite>False northing</cite>)
     * parameter values of the <cite>OSGB 1936 / British National Grid</cite> projection.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testTranslatation2D() throws FactoryException, TransformException {
        runTest(AffineTransform.getTranslateInstance(400000, -100000));
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using a uniform scale factor of 0.3048 in a two-dimensional space.
     * This is the conversion factor from <cite>feet</cite> to <cite>metres</cite>.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testUniformScale2D() throws FactoryException, TransformException {
        runTest(AffineTransform.getScaleInstance(FEET, FEET));
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using a non-uniform scale factor of (3,4) in a two-dimensional space.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testGenericScale2D() throws FactoryException, TransformException {
        runTest(AffineTransform.getScaleInstance(3, 4));
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using an anticlockwise 30° rotation in a two-dimensional space.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testRotation2D() throws FactoryException, TransformException {
        runTest(AffineTransform.getRotateInstance(toRadians(30)));
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests using a combination of scale, rotation and translation in a two-dimensional space.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testGeneral() throws FactoryException, TransformException {
        final AffineTransform reference = AffineTransform.getTranslateInstance(10,-20);
        reference.rotate(0.5);
        reference.scale(0.2, 0.3);
        reference.translate(300, 500);
        runTest(reference);
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }

    /**
     * Tests a transform which reduce the number of dimensions from 4 to 2.
     * This test is executed only if the {@link #isNonSquareMatrixSupported}
     * flag is set to {@code true}.
     *
     * @throws FactoryException should never happen.
     * @throws TransformException should never happen.
     */
    @Test
    public void testDimensionReduction() throws FactoryException, TransformException {
        assumeTrue(isNonSquareMatrixSupported);
        configurationTip = Configuration.Key.isNonSquareMatrixSupported;
        final int sourceDim = 4;
        final int targetDim = 2;
        final boolean inverseSupported = isInverseTransformSupported;
        isInverseTransformSupported = false;
        try {
            runTest(targetDim+1, sourceDim+1,
                2,  0,  0,  0,  8,
                0,  0,  4,  0,  5,
                0,  0,  0,  0,  1);
            /*
             * Tests hard-coded known points.
             */
            final double[] source = new double[] {0,0,0,0 , 1,1,1,1 , 8,3,-7,5};
            final double[] target = new double[] {8,5 ,     10,9 ,    24,-23};
            verifyTransform(source, target);
            /*
             * Inverse the transform (this is the interesting part of this test) and try again.
             * The coordinates at index 1 and 3 (indices of columns were all elements are 0 in
             * the above matrix) are expected to be NaN.
             */
            if (inverseSupported) {
                configurationTip = Configuration.Key.isInverseTransformSupported;
                for (int i=0; i<source.length; i += sourceDim) {
                    source[i + 1] = Double.NaN;
                    source[i + 3] = Double.NaN;
                }
                final MathTransform direct = transform;
                transform = direct.inverse();
                try {
                    verifyTransform(target, source);
                } finally {
                    transform = direct;
                }
            }
        } finally {
            isInverseTransformSupported = inverseSupported;
        }
        assertFalse("MathTransform.isIdentity().", transform.isIdentity());
    }
}
