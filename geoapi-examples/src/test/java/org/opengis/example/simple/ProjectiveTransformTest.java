/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.opengis.util.FactoryException;
import org.opengis.test.referencing.AffineTransformTest;
import org.opengis.referencing.operation.TransformException;


/**
 * Tests {@link ProjectiveTransform} using the {@code geoapi-conformance} module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(JUnit4.class)
public class ProjectiveTransformTest extends AffineTransformTest {
    /**
     * Creates a new test case.
     */
    public ProjectiveTransformTest() {
        super(new SimpleTransformFactory());
    }

    /**
     * Declares that our implementation can not invert such transform before
     * to delegate to the parent class.
     */
    @Test
    @Override
    public void testNonSquare() throws FactoryException, TransformException {
        isInverseTransformSupported = false;
        super.testNonSquare();
    }
}
