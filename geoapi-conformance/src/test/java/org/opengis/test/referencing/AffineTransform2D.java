/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.referencing;

import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.NoninvertibleTransformException;

import static org.junit.Assert.*;


/**
 * A math transform implemented by the JDK affine transform. {@code MathTransform} instances should be immutable,
 * but this test class is not. It may be modified after construction, but should not be modified anymore after the
 * tests began.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
@SuppressWarnings("serial")
strictfp class AffineTransform2D extends AffineTransform implements MathTransform2D {
    /**
     * The inverse of this transform. Will be computed when first needed on the assumption that
     * this transform will not be modified anymore at the time {@link #inverse()} is invoked.
     */
    private AffineTransform2D inverse;

    /**
     * Creates a new transform initialized to the identity transform.
     */
    public AffineTransform2D() {
    }

    /**
     * Creates a new transform initialized to the given transform.
     *
     * @param tr The transform to copy.
     */
    public AffineTransform2D(final AffineTransform tr) {
        super(tr);
    }

    /**
     * Returns the source dimension, which is 2.
     *
     * @return always 2.
     */
    @Override
    public final int getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the target dimension, which is 2.
     *
     * @return always 2.
     */
    @Override
    public final int getTargetDimensions() {
        return 2;
    }

    /**
     * Returns the given direct position as a {@link Point2D}. This is used for forwarding
     * GeoAPI method calls to {@link AffineTransform} method calls.
     *
     * @param  point  the direct position to returns as a two-dimensional point.
     * @return the direct position as a two-dimensional point.
     * @throws MismatchedDimensionException if the given position is not two-dimensional.
     */
    private static Point2D.Double toPoint2D(final DirectPosition point)
            throws MismatchedDimensionException
    {
        if (point.getDimension() != 2) {
            throw new MismatchedDimensionException();
        }
        return new Point2D.Double(point.getOrdinate(0), point.getOrdinate(1));
    }

    /**
     * Transforms the given position.
     *
     * @param  ptSrc  the source position.
     * @param  ptDst  pre-allocated target position, or {@code null} if none.
     * @return the transformed position.
     * @throws MismatchedDimensionException if a given position is not two-dimensional.
     */
    @Override
    public final DirectPosition transform(final DirectPosition ptSrc, final DirectPosition ptDst)
            throws MismatchedDimensionException
    {
        final Point2D.Double point = toPoint2D(ptSrc);
        assertSame(point, transform(point, point));
        if (ptDst != ptSrc) {
            if (ptDst == null) {
                return new SimpleDirectPosition(point);
            }
            if (ptDst.getDimension() != 2) {
                throw new MismatchedDimensionException();
            }
        }
        ptDst.setOrdinate(0, point.x);
        ptDst.setOrdinate(1, point.y);
        return ptDst;
    }

    /**
     * Returns the derivative at the given position. The default implementation delegates
     * to {@link #derivative(Point2D)}.
     *
     * @param  point  the point where to evaluate the derivative.
     * @return the derivative at the given point.
     * @throws MismatchedDimensionException if the given position is not two-dimensional.
     */
    @Override
    public Matrix derivative(final DirectPosition point) throws MismatchedDimensionException {
        return derivative(toPoint2D(point));
    }

    /**
     * Returns the derivative at the given position.
     *
     * @param  point  the point where to evaluate the derivative.
     * @return the derivative at the given point.
     */
    @Override
    public Matrix derivative(final Point2D point) {
        return new SimpleMatrix(2, 2, getScaleX(), getShearX(), getShearY(), getScaleY());
    }

    /**
     * Returns the inverse of this math transform.
     *
     * @return the inverse of this math transform.
     * @throws NoninvertibleTransformException if the transform is not invertible.
     */
    @Override
    public MathTransform2D inverse() throws NoninvertibleTransformException {
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
     * Returns the Well-Known Text of this math transform. Current implementation
     * thrown a {@link UnsupportedOperationException} in all cases.
     *
     * @return the WKT of this transform.
     * @throws UnsupportedOperationException if this operation is not supported.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
