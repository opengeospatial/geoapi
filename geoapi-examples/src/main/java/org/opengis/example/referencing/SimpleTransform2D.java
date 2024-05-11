/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import org.opengis.metadata.citation.Citation;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;

import org.opengis.example.geometry.SimpleDirectPosition;


/**
 * A {@link CoordinateOperation} working only on two-dimensional points.
 *
 * <p>Subclasses must implement the {@link #transform(Point2D, Point2D)} method. All other transform
 * methods are defined in terms of the above-cited method. However, the other {@code transform}
 * methods may be overridden for performances reasons.</p>
 */
public abstract class SimpleTransform2D extends SimpleTransform implements MathTransform2D {
    /**
     * Creates a new operation for the given name and CRS.
     *
     * @param authority  organization responsible for definition of the name, or {@code null}.
     * @param name       the name of the new operation.
     * @param sourceCRS  the source CRS to be returned by {@link #getSourceCRS()}.
     * @param targetCRS  the target CRS to be returned by {@link #getTargetCRS()}.
     */
    protected SimpleTransform2D(final Citation authority, final String name,
            final CoordinateReferenceSystem sourceCRS,
            final CoordinateReferenceSystem targetCRS)
    {
        super(authority, name, sourceCRS, targetCRS);
    }

    /**
     * Returns the number of source dimensions, which is fixed to 2.
     */
    @Override
    public final int getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the number of target dimensions, which is fixed to 2.
     */
    @Override
    public final int getTargetDimensions() {
        return 2;
    }

    /**
     * Ensures that the given position is two-dimensional.
     *
     * @param  point  the position to verify, or {@code null}.
     * @throws MismatchedDimensionException if the given position is not two-dimensional.
     */
    private static void ensureTwoDimensional(final DirectPosition point) throws MismatchedDimensionException {
        if (point != null && point.getDimension() != 2) {
            throw new MismatchedDimensionException("All given positions shall be two-dimensional.");
        }
    }

    /**
     * Transforms the specified point by delegating to {@link #transform(Point2D, Point2D)}.
     */
    @Override
    public DirectPosition transform(final DirectPosition ptSrc, DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException
    {
        ensureTwoDimensional(ptSrc);
        ensureTwoDimensional(ptDst);
        Point2D pt = new Point2D.Double(ptSrc.getCoordinate(0), ptSrc.getCoordinate(1));
        pt = transform(pt, pt);
        if (ptDst == null) {
            ptDst = new SimpleDirectPosition(2);
        }
        ptDst.setCoordinate(0, pt.getX());
        ptDst.setCoordinate(1, pt.getY());
        return ptDst;
    }

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}.
     * If {@code ptDst} is {@code null}, a new {@link Point2D} object is allocated
     * and then the result of the transformation is stored in this object. In either case,
     * {@code ptDst}, which contains the transformed point, is returned for convenience.
     * If {@code ptSrc} and {@code ptDst} are the same object, the input point is
     * correctly overwritten with the transformed point.
     *
     * @param  ptSrc  the coordinate point to be transformed.
     * @param  ptDst  the coordinate point that stores the result of transforming {@code ptSrc},
     *                or {@code null} if a new point shall be created.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst} or in a new point if {@code ptDst} was null.
     * @throws TransformException if the point cannot be transformed.
     */
    @Override
    public abstract Point2D transform(Point2D ptSrc, Point2D ptDst) throws TransformException;

    /**
     * Transforms the specified shape. The default implementation returns a new shape with
     * the transform of all control points.
     *
     * @param  shape  the Shape to transform.
     * @return the transformed shape.
     * @throws TransformException if a transform failed.
     */
    @Override
    public Shape createTransformedShape(final Shape shape) throws TransformException {
        final PathIterator it = shape.getPathIterator(null);
        final Path2D path = new Path2D.Double(it.getWindingRule());
        final double[] b = new double[6];
        while (!it.isDone()) {
            final int mode = it.currentSegment(b);
            switch (mode) {
                case PathIterator.SEG_CLOSE:   path.closePath(); break;
                case PathIterator.SEG_MOVETO:  transform(b, 0, b, 0, 1); path.moveTo (b[0], b[1]); break;
                case PathIterator.SEG_LINETO:  transform(b, 0, b, 0, 1); path.lineTo (b[0], b[1]); break;
                case PathIterator.SEG_QUADTO:  transform(b, 0, b, 0, 2); path.quadTo (b[0], b[1], b[2], b[3]); break;
                case PathIterator.SEG_CUBICTO: transform(b, 0, b, 0, 3); path.curveTo(b[0], b[1], b[2], b[3], b[4], b[5]); break;
                default: throw new AssertionError(mode);
            }
            it.next();
        }
        return path;
    }

    /**
     * Gets the derivative of this transform at a point. This method ensures that the given
     * position is two-dimensional, then delegates to {@link #derivative(Point2D)}.
     *
     * @param  point  the coordinate point where to evaluate the derivative.
     * @return the derivative at the specified point (never {@code null}).
     * @throws TransformException if the derivative cannot be evaluated at the specified point.
     */
    @Override
    public Matrix derivative(final DirectPosition point) throws TransformException {
        ensureTwoDimensional(point);
        return derivative(new Point2D.Double(point.getCoordinate(0), point.getCoordinate(1)));
    }

    /**
     * Gets the derivative of this transform at a point. The default implementation throws
     * an exception in all cases.
     *
     * @param  point  the coordinate point where to evaluate the derivative.
     * @return the derivative at the specified point (never {@code null}).
     * @throws TransformException if the derivative cannot be evaluated at the specified point.
     */
    @Override
    public Matrix derivative(final Point2D point) throws TransformException {
        throw new TransformException();
    }
}
