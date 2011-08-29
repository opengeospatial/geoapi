/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.simple;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.opengis.test.referencing.AffineTransformTest;


/**
 * Tests {@link ProjectiveTransform} using the {@code geoapi-conformance} module.
 * This test case sets {@link #isNonSquareMatrixSupported} to {@code false} because
 * {@link javax.vecmath.GMatrix} can not invert non-square matrix.
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
        isNonSquareMatrixSupported = false;
    }
}
