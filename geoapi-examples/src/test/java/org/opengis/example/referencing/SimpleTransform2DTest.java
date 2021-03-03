/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Random;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;
import org.opengis.test.referencing.TransformTestCase;
import org.junit.Test;

import static org.opengis.test.Assert.*;


/**
 * Tests {@link SimpleTransform2D}. The main purpose of this test is to ensure
 * that the various {@code transform} methods correctly delegate to the
 * {@link SimpleTransform2D#transform(Point2D, Point2D)} method.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class SimpleTransform2DTest extends TransformTestCase {
    /**
     * The affine transform coefficients used in this test.
     */
    private static final double SCALE_X = 2, TRANSLATE_X = 1,
                                SCALE_Y = 3, TRANSLATE_Y = 2;

    /**
     * Creates a new test case initialized with a trivial implementation of
     * {@link SimpleTransform2D}. The {@linkplain #transform transform} is a
     * hard-coded affine transform.
     */
    @SuppressWarnings("serial")
    public SimpleTransform2DTest() {
        transform = new SimpleTransform2D(null, "Test", null, null) {
            @Override
            public Point2D transform(final Point2D ptSrc, Point2D ptDst) {
                if (ptDst == null) {
                    ptDst = new Point2D.Double();
                }
                ptDst.setLocation(ptSrc.getX()*SCALE_X + TRANSLATE_X,
                                  ptSrc.getY()*SCALE_Y + TRANSLATE_Y);
                return ptDst;
            }
        };
        tolerance = 1E-10;
        isDerivativeSupported = false;
        isInverseTransformSupported = false;
    }

    /**
     * Tests the consistency of various {@code transform} methods. This method runs the
     * {@link #verifyInDomain(double[], double[], int[], Random)} test method using a
     * trivial {@link SimpleTransform2D} implementation.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testConsistency() throws TransformException {
        validators.validate(transform);
        verifyInDomain(new double[] {-100, -100}, // Minimal coordinate values to test.
                       new double[] {+100, +100}, // Maximal coordinate values to test.
                       new int[]    { 100,  100}, // Number of points to test.
                       new Random(216919106));
    }

    /**
     * Tests the {@link MathTransform2D#createTransformedShape(Shape)} method.
     *
     * @throws TransformException should never happen.
     */
    @Test
    public void testCreateShape() throws TransformException {
        final Shape sourceShape = new RoundRectangle2D.Double(-20, -10, 100, 80, 4, 5);
        final Shape targetShape = ((MathTransform2D) transform).createTransformedShape(sourceShape);
        final Shape expectedShape = new AffineTransform(SCALE_X, 0, 0, SCALE_Y, TRANSLATE_X, TRANSLATE_Y).createTransformedShape(sourceShape);
        assertShapeEquals(null, expectedShape, targetShape, 1E-10, 1E-10);
    }
}
