/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
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
 * A math transform implemented by the JDK affine transform. Math transform should be immutable,
 * but this test class is not. It may be modified after construction, but should not be modified
 * anymore once the tests begin.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
@SuppressWarnings("serial")
class AffineTransform2D extends AffineTransform implements MathTransform2D {
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
     * @return Always 2.
     */
    public final int getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the target dimension, which is 2.
     *
     * @return Always 2.
     */
    public final int getTargetDimensions() {
        return 2;
    }

    /**
     * Returns the given direct position as a {@link Point2D}. This is used for forwarding
     * GeoAPI method calls to {@link AffineTransform} method calls.
     *
     * @param  point The direct position to returns as a two-dimensional point.
     * @return The direct position as a two-dimensional point.
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
     * @param  ptSrc The source position.
     * @param  ptDst Pre-allocated target position, or {@code null} if none.
     * @return The transformed position.
     * @throws MismatchedDimensionException if a given position is not two-dimensional.
     */
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
     * @param  point The point where to evaluate the derivative.
     * @return The derivative at the given point.
     * @throws MismatchedDimensionException if the given position is not two-dimensional.
     */
    public Matrix derivative(final DirectPosition point) throws MismatchedDimensionException {
        return derivative(toPoint2D(point));
    }

    /**
     * Returns the derivative at the given position.
     *
     * @param  point The point where to evaluate the derivative.
     * @return The derivative at the given point.
     */
    public Matrix derivative(final Point2D point) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the inverse of this math transform.
     *
     * @return The inverse of this math transform.
     * @throws NoninvertibleTransformException if the transform is not invertible.
     *
     * @todo Use {@link AffineTransform#invert} when we will be allowed to compile for Java 6.
     */
    public MathTransform2D inverse() throws NoninvertibleTransformException {
        if (inverse == null) try {
            inverse = new AffineTransform2D(createInverse());
            inverse.inverse = this;
        } catch (java.awt.geom.NoninvertibleTransformException e) {
            throw new NoninvertibleTransformException();
        }
        return inverse;
    }

    /**
     * Returns the Well Known Text of this math transform. Current implementation
     * thrown a {@link UnsupportedOperationException} in all cases.
     *
     * @return The WKT of this transform.
     * @throws UnsupportedOperationException if this operation is not supported.
     */
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
