/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.NoninvertibleTransformException;

import org.opengis.example.geometry.SimpleDirectPosition;


/**
 * A {@link MathTransform2D} backed by the Java2D {@link AffineTransform}.
 *
 * <p>Affine transforms are very commons in GIS - they can be used for scaling, flipping
 * the <var>y</var> axis, applying unit conversions, <em>etc.</em>. The standard Java2D
 * library provides an efficient general-purpose implementation of affine transform.
 * This class extends the Java2D class for efficiency and for inter-operability with
 * Java2D.</p>
 *
 * <p>This affine transform is a special case of {@link ProjectiveTransform} restricted to
 * the following cases:</p>
 *
 * <ul>
 *   <li>The number of {@linkplain #getSourceDimensions() source} and
 *       {@linkplain #getTargetDimensions() target dimensions} are 2.</li>
 *   <li>The last matrix row contains the [0 0 1] values.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class AffineTransform2D extends AffineTransform implements MathTransform2D {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -2450159714021625517L;

    /**
     * The inverse of this affine transform, or {@code null} if not yet computed.
     * Note that this field is part of the serialization; we do not declare it as
     * {@code transient} even if we could easily recompute it, because if the user
     * serialize the inverse transform rather than the original transform, then
     * inverting the inverse transform in order to get back the original transform
     * would lead to floating point errors.
     */
    private AffineTransform2D inverse;

    /**
     * Creates a new transform initialized to the identity transform.
     */
    public AffineTransform2D() {
    }

    /**
     * Creates a new transform initialized to the values of the given transform.
     *
     * @param tr  the transform to copy.
     */
    public AffineTransform2D(final AffineTransform tr) {
        super(tr);
    }

    /**
     * Creates a new transform initialized to the values of the given matrix.
     * The matrix size must be 3×3 and the last row shall contain [0 0 1].
     * The conditions are not verified by this constructor since they were already
     * verified by the {@link SimpleTransformFactory#createAffineTransform(Matrix)}
     * method.
     *
     * @param matrix  the matrix to copy.
     */
    AffineTransform2D(final Matrix matrix) {
        super(matrix.getElement(0,0),
              matrix.getElement(1,0),
              matrix.getElement(0,1),
              matrix.getElement(1,1),
              matrix.getElement(0,2),
              matrix.getElement(1,2));
    }

    /**
     * Sets this affine transform to the values of the given matrix.
     * The matrix size must be 3×3 and the last row shall contain the [0 0 1] values.
     *
     * @param  matrix  the matrix to copy in this affine transform.
     * @throws IllegalArgumentException if the matrix size is not 3×3, or the transform is not affine.
     */
    public void setTransform(final Matrix matrix) throws IllegalArgumentException {
        if (matrix.getNumCol() != 3 || matrix.getNumRow() != 3) {
            throw new MismatchedDimensionException("Matrix size must be 3×3.");
        }
        if (matrix.getElement(2,0) != 0 ||
            matrix.getElement(2,1) != 0 ||
            matrix.getElement(2,2) != 1)
        {
            throw new IllegalArgumentException("The transform must be affine.");
        }
        setTransform(matrix.getElement(0,0),
                     matrix.getElement(1,0),
                     matrix.getElement(0,1),
                     matrix.getElement(1,1),
                     matrix.getElement(0,2),
                     matrix.getElement(1,2));
    }

    /**
     * Returns the source dimension, which is 2.
     * This number of dimensions can not be changed.
     */
    @Override
    public final int getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the target dimension, which is 2.
     * This number of dimensions can not be changed.
     */
    @Override
    public final int getTargetDimensions() {
        return 2;
    }

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}.
     * This method delegates its work to {@link #transform(Point2D, Point2D)}.
     *
     * @param  ptSrc  the coordinate point to be transformed.
     * @param  ptDst  the coordinate point that stores the transform result, or {@code null}.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst}, or a newly created point if {@code ptDst} was null.
     * @throws MismatchedDimensionException if {@code ptSrc} or {@code ptDst} does not have
     *         exactly 2 dimensions.
     */
    @Override
    public DirectPosition transform(final DirectPosition ptSrc, DirectPosition ptDst) throws MismatchedDimensionException {
        if (ptSrc.getDimension() != 2) {
            throw new MismatchedDimensionException("ptSrc must have 2 dimensions.");
        }
        Point2D pt = new Point2D.Double(ptSrc.getOrdinate(0), ptSrc.getOrdinate(1));
        pt = transform(pt, pt);
        if (ptDst == null) {
            ptDst = new SimpleDirectPosition(2);
        } else if (ptDst.getDimension() != 2) {
            throw new MismatchedDimensionException("ptDst must have 2 dimensions.");
        }
        ptDst.setOrdinate(0, pt.getX());
        ptDst.setOrdinate(1, pt.getY());
        return ptDst;
    }

    /**
     * Gets the derivative of this transform at a point.
     * This method delegates its work to {@link #derivative(Point2D)}.
     *
     * @param  point  ignored (except for the dimension, which is checked as a matter of principle).
     * @return the derivative (never {@code null}).
     * @throws MismatchedDimensionException if {@code point} does not have the expected dimension.
     */
    @Override
    public Matrix derivative(final DirectPosition point) throws MismatchedDimensionException {
        if (point != null && point.getDimension() != 2) {
            throw new MismatchedDimensionException("point must have 2 dimensions.");
        }
        return derivative((Point2D) null);
    }

    /**
     * Gets the derivative of this transform at a point.
     * The derivative of an affine transform is constant everywhere.
     *
     * @param  point  ignored - may be {@code null}.
     * @return the derivative (never {@code null}).
     */
    @Override
    public Matrix derivative(final Point2D point) {
        final Matrix m = new SimpleMatrix(2, 2);
        m.setElement(0, 0, getScaleX());
        m.setElement(1, 1, getScaleY());
        m.setElement(0, 1, getShearX());
        m.setElement(1, 0, getShearY());
        return m;
    }

    /**
     * Returns the inverse of this affine transform.
     */
    @Override
    public synchronized MathTransform2D inverse() throws NoninvertibleTransformException {
        if (inverse == null) {
            final AffineTransform2D tmp = new AffineTransform2D(this);
            try {
                tmp.invert();
            } catch (java.awt.geom.NoninvertibleTransformException e) {
                throw new NoninvertibleTransformException(e.getLocalizedMessage(), e);
            }
            tmp.inverse = this;
            inverse = tmp;                      // Keep the reference only on success.
        }
        return inverse;
    }

    /**
     * Unsupported operation.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
