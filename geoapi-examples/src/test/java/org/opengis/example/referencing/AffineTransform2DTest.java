/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import org.opengis.util.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.test.referencing.AffineTransformTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link AffineTransform2D} using the
 * <code><a href="http://www.geoapi.org/conformance/index.html">geoapi-conformance</a></code>
 * module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class AffineTransform2DTest extends AffineTransformTest {
    /**
     * Creates a new test case.
     */
    public AffineTransform2DTest() {
        super(ProjectiveTransformTest.factory());
    }

    /**
     * Declares that our implementation cannot invert such transform, then delegate to the parent class.
     */
    @Test
    @Override
    public void testDimensionReduction() throws FactoryException, TransformException {
        isInverseTransformSupported = false;
        super.testDimensionReduction();
    }

    /**
     * Invoked after every tests in order to ensure that the transform created by the factory
     * is of the expected type.
     */
    @AfterEach
    public void ensureExpectedTransformClass() {
        assertEquals(ProjectiveTransform.class, transform.getClass(), "Unexpected transform instance.");
    }
}
