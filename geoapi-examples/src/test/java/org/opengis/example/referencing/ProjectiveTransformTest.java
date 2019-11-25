/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.opengis.util.FactoryException;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.test.referencing.AffineTransformTest;

import static org.junit.Assert.*;


/**
 * Tests {@link ProjectiveTransform} using the
 * <code><a href="http://www.geoapi.org/conformance/index.html">geoapi-conformance</a></code>
 * module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(JUnit4.class)
public strictfp class ProjectiveTransformTest extends AffineTransformTest {
    /**
     * The expected class of the {@link #transform} instance.
     * The default value is {@code ProjectiveTransform.class}.
     * Subclasses can change this value if they expect an other
     * kind of transform to be created by the factory.
     */
    protected Class<? extends MathTransform> expectedTransformClass;

    /**
     * Creates a new test case.
     */
    public ProjectiveTransformTest() {
        this(new SimpleTransformFactory() {
            @Override // Prevent SimpleTransformFactory from creating AffineTransform2D instances.
            public MathTransform createAffineTransform(final Matrix matrix) {
                return new ProjectiveTransform(getVendor(), "Projective transform", null, null,
                        (matrix instanceof SimpleMatrix) ? (SimpleMatrix) matrix : new SimpleMatrix(matrix));
            }
        });
    }

    /**
     * Creates a new test case using the given factory.
     *
     * @param factory  the factory to use for testing affine transforms.
     */
    protected ProjectiveTransformTest(final SimpleTransformFactory factory) {
        super(factory);
        expectedTransformClass = ProjectiveTransform.class;
    }

    /**
     * Declares that our implementation can not invert such transform before to delegate to the parent class.
     */
    @Test
    @Override
    public void testDimensionReduction() throws FactoryException, TransformException {
        isInverseTransformSupported = false;
        super.testDimensionReduction();
    }

    /**
     * Invoked after every tests in order to ensure that the transform created by the factory
     * is of the expected type. This method requires that the transform class is exactly the
     * {@link #expectedTransformClass} - not a subclass.
     */
    @After
    public void ensureExpectedTransformClass() {
        assertEquals("Unexpected transform instance.", expectedTransformClass, transform.getClass());
    }
}
