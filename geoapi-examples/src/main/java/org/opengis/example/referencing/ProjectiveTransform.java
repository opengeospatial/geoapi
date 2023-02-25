/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Objects;
import javax.vecmath.GMatrix;

import org.opengis.metadata.citation.Citation;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.NoninvertibleTransformException;

import org.opengis.example.geometry.SimpleDirectPosition;


/**
 * A {@link MathTransform} which use a {@link Matrix} for transforming the coordinates.
 * This transform is usually, but not necessarily, affine.
 *
 * <p><b>Constraints:</b></p>
 * <ul>
 *   <li>The {@linkplain Matrix#getNumCol() number of columns} in the matrix shall be equal
 *       to the number of source dimensions + 1.</li>
 *   <li>The {@linkplain Matrix#getNumRow() number of rows} in the matrix shall be equal
 *       to the number of target dimensions + 1.</li>
 * </ul>
 *
 * <b>Performance note:</b>
 * This implementation is known to be slow. However, the intent is to be pedagogic, not to be efficient.
 * Performance enhancements are left to implementers
 * (<i>Tip:</i> override all {@code transform} methods expecting array arguments).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see SimpleTransformFactory#createAffineTransform(Matrix)
 */
public class ProjectiveTransform extends SimpleTransform {
    /**
     * The matrix used for performing the coordinate conversions.
     */
    protected final SimpleMatrix matrix;

    /**
     * The inverse of this transform, computed when first needed.
     */
    private transient ProjectiveTransform inverse;

    /**
     * Creates a new operation for the given name, CRS and matrix.
     *
     * @param authority  organization responsible for definition of the name, or {@code null}.
     * @param name       the name of the new CRS.
     * @param sourceCRS  the source CRS to be returned by {@link #getSourceCRS()}, or {@code null}.
     * @param targetCRS  the target CRS to be returned by {@link #getTargetCRS()}, or {@code null}.
     * @param matrix     the matrix. See class javadoc for constraints on the matrix size.
     */
    public ProjectiveTransform(final Citation authority, final String name,
            final CoordinateReferenceSystem sourceCRS,
            final CoordinateReferenceSystem targetCRS,
            final SimpleMatrix matrix)
    {
        super(authority, name, sourceCRS, targetCRS);
        Objects.requireNonNull(matrix);
        this.matrix = matrix;
        if (sourceCRS != null && sourceCRS.getCoordinateSystem().getDimension() != matrix.getNumCol() - 1) {
            throw new MismatchedDimensionException("Wrong number of source dimensions.");
        }
        if (targetCRS != null && targetCRS.getCoordinateSystem().getDimension() != matrix.getNumRow() - 1) {
            throw new MismatchedDimensionException("Wrong number of target dimensions.");
        }
    }

    /**
     * Gets the dimension of input points. This is equal to the
     * {@linkplain Matrix#getNumCol() number of columns} in the matrix minus one.
     */
    @Override
    public int getSourceDimensions() {
        return matrix.getNumCol() - 1;
    }

    /**
     * Gets the dimension of target points. This is equal to the
     * {@linkplain Matrix#getNumRow() number of rows} in the matrix minus one.
     */
    @Override
    public int getTargetDimensions() {
        return matrix.getNumRow() - 1;
    }

    /**
     * Transforms the specified {@code ptSrc}. First, this implementation computes the
     * following matrix product:
     *
     * <blockquote><pre>
     * ┌     ┐     ┌      ┐ ┌     ┐
     * │ptDst│  =  │{@linkplain #matrix}│ │ptSrc│
     * │  w  │     │      │ │  1  │
     * └     ┘     └      ┘ └     ┘</pre></blockquote>
     * <p>
     * Then, the destination coordinate values are divided by <var>w</var>. Note that in the
     * common case where the transform is affine, <var>w</var> = 1.
     */
    @Override
    public DirectPosition transform(final DirectPosition ptSrc, DirectPosition ptDst) throws MismatchedDimensionException {
        //
        // Check arguments validity.
        //
        final int srcDim = matrix.getNumCol() - 1;
        final int dstDim = matrix.getNumRow() - 1;
        if (ptSrc.getDimension() != srcDim) {
            throw new MismatchedDimensionException("Wrong number of source dimensions.");
        }
        if (ptDst != null) {
            if (ptDst.getDimension() != dstDim) {
                throw new MismatchedDimensionException("Wrong number of target dimensions.");
            }
        } else {
            ptDst = new SimpleDirectPosition(dstDim);
        }
        //
        // Create two matrixes of 1 column, which will
        // represent the source and target coordinates.
        //
        final GMatrix source = new GMatrix(srcDim+1, 1);
        final GMatrix target = new GMatrix(dstDim+1, 1);
        source.setElement(srcDim, 0, 1);
        for (int j=0; j<srcDim; j++) {
            source.setElement(j, 0, ptSrc.getOrdinate(j));
        }
        //
        // Compute [target] = [matrix]*[source]
        // as documented in the method javadoc.
        //
        target.mul(matrix, source);
        final double w = target.getElement(dstDim, 0);          // =1 if the transform is affine.
        for (int j=0; j<dstDim; j++) {
            ptDst.setOrdinate(j, target.getElement(j, 0) / w);
        }
        return ptDst;
    }

    /**
     * Gets the derivative of this transform. In the particular case of linear transforms,
     * the derivative is the same at every points. Consequently, the {@code point} argument
     * is ignored.
     *
     * @param  point  ignored for a linear transform.
     * @return the derivative (never {@code null}).
     */
    @Override
    public Matrix derivative(final DirectPosition point) {
        final int srcDim = matrix.getNumCol() - 1;
        final int dstDim = matrix.getNumRow() - 1;
        final SimpleMatrix derivative = new SimpleMatrix(dstDim, srcDim);
        matrix.copySubMatrix(0, 0, dstDim, srcDim, 0, 0, derivative);
        return derivative;
    }

    /**
     * Returns the inverse transform of this object. The default implementation
     * {@linkplain SimpleMatrix#invert() invert} the {@linkplain #matrix} and
     * build a new {@code ProjectiveTransform} from it.
     */
    @Override
    public synchronized ProjectiveTransform inverse() throws NoninvertibleTransformException {
        if (inverse == null) {
            final SimpleMatrix invert = matrix.clone();
            try {
                invert.invert();
            } catch (RuntimeException e) {      // SingularMatrixException & MismatchedSizeException
                throw new NoninvertibleTransformException("Cannot invert \"" + code + '"', e);
            }
            inverse = new ProjectiveTransform(authority, "Inverse of " + code, targetCRS, sourceCRS, invert);
            inverse.inverse = this;
        }
        return inverse;
    }

    /**
     * Tests whether this transform does not move any points.
     * The default implementation delegates to {@link SimpleMatrix#isIdentity()}.
     */
    @Override
    public boolean isIdentity() {
        return matrix.isIdentity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            return matrix.equals(((ProjectiveTransform) object).matrix);
        }
        return false;
    }
}
