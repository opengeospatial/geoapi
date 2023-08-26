/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import org.junit.Test;

import org.opengis.util.FactoryException;
import org.opengis.referencing.operation.TransformException;


/**
 * Tests {@link AffineTransform2D} using the
 * <code><a href="http://www.geoapi.org/conformance/index.html">geoapi-conformance</a></code>
 * module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class AffineTransform2DTest extends ProjectiveTransformTest {
    /**
     * Creates a new test case.
     */
    public AffineTransform2DTest() {
        super(new SimpleTransformFactory());
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testIdentity2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testIdentity2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testAxisSwapping2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testAxisSwapping2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testSouthOrientated2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testSouthOrientated2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testTranslatation2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testTranslatation2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testUniformScale2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testUniformScale2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testGenericScale2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testGenericScale2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testRotation2D() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testRotation2D();
    }

    /**
     * Declares that this particular test expects an {@link AffineTransform2D} instance,
     * then runs the test.
     */
    @Test
    @Override
    public void testGeneral() throws FactoryException, TransformException {
        expectedTransformClass = AffineTransform2D.class;
        super.testGeneral();
    }
}
