/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import org.opengis.util.FactoryException;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.NoninvertibleTransformException;


/**
 * A math transform backed by the Java2D affine transform.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Affine extends AffineTransform implements MathTransform2D {
    /**
     * For cross-version compatibility.
     */
    static final long serialVersionUID = -2166137125997372615L;

    /**
     * The inverse of this affine transform, or {@code null} if not yet computed.
     * Note that this field is part of the serialization; we do not declare it as
     * {@code transient} even if we could easily recompute, because the user serialize
     * the inverse transform rather than the original transform, inverting the inverse
     * transform in order to get back the original transform would lead to floating
     * point errors.
     */
    private Affine inverse;

    /**
     * Creates a new transform initialized to the values of the given transform.
     */
    Affine(final AffineTransform tr) {
        super(tr);
    }

    /**
     * Creates a new affine transform initialized from the given matrix.
     * The matrix size must be 3×3 and the last row shall contains
     * [0 0 1] (this is not verified by this constructor).
     */
    private Affine(final Matrix matrix) {
        super(matrix.getElement(0,0),
              matrix.getElement(1,0),
              matrix.getElement(0,1),
              matrix.getElement(1,1),
              matrix.getElement(0,2),
              matrix.getElement(1,2));
    }

    /**
     * Creates a new affine transform initialized from the given matrix.
     *
     * @throws FactoryException if the matrix size is not 3×3, or the transform is not affine.
     */
    static Affine create(final Matrix matrix) throws FactoryException {
        if (matrix.getNumCol() != 3 || matrix.getNumRow() != 3) {
            throw new FactoryException("Matrix size must be 3×3.");
        }
        if (matrix.getElement(2,0) != 0 ||
            matrix.getElement(2,1) != 0 ||
            matrix.getElement(2,2) != 1)
        {
            throw new FactoryException("The transform must be affine.");
        }
        return new Affine(matrix);
    }

    /**
     * Returns the source dimension, which is 2.
     */
    @Override
    public int getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the target dimension, which is 2.
     */
    @Override
    public int getTargetDimensions() {
        return 2;
    }

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}.
     * This method delegates its work to {@link #transform(Point2D, Point2D)}.
     *
     * @param  ptSrc the coordinate point to be transformed.
     * @param  ptDst the coordinate point that stores the transform result, or {@code null}.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst}, or a newly created point if {@code ptDst} was null.
     * @throws MismatchedDimensionException if {@code ptSrc} or {@code ptDst} does not have exactly 2 dimensions.
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
     * @return the derivative at the specified point (never {@code null}).
     * @throws MismatchedDimensionException if {@code point} doesn't have the expected dimension.
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
     * This operation is not yet supported.
     *
     * @param  point  ignored (except for the dimension, which is checked as a matter of principle).
     * @return the derivative at the specified point (never {@code null}).
     */
    @Override
    public Matrix derivative(final Point2D point) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the inverse of this affine transform.
     */
    @Override
    public synchronized MathTransform2D inverse() throws NoninvertibleTransformException {
        if (inverse == null) {
            final Affine tmp = new Affine(this);
            try {
                tmp.invert();
            } catch (java.awt.geom.NoninvertibleTransformException e) {
                throw new NoninvertibleTransformException(e.getLocalizedMessage(), e);
            }
            tmp.inverse = this;
            inverse = tmp;              // Keep the reference only on success.
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
