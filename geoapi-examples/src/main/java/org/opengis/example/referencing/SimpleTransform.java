/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Arrays;
import java.util.Objects;

import org.opengis.metadata.citation.Citation;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.NoninvertibleTransformException;

import org.opengis.example.geometry.SimpleDirectPosition;


/**
 * A {@link CoordinateOperation} abstract base class. This class does not make
 * distinction between <cite>Coordinate Operation</cite> and <cite>Math Transform</cite>, so we
 * implement the two interfaces by the same class.
 *
 * <p>Subclasses must implement the {@link #transform(DirectPosition, DirectPosition)} method.
 * All other transform methods are defined in terms of the above-cited method. However the
 * other {@code transform} methods may be overridden for performances reasons.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract class SimpleTransform extends SimpleIdentifiedObject implements CoordinateOperation, MathTransform {
    /**
     * The source CRS, which determine the number of source dimensions.
     *
     * @see #getSourceCRS()
     * @see #getSourceDimensions()
     */
    protected final CoordinateReferenceSystem sourceCRS;

    /**
     * The target CRS, which determine the number of target dimensions.
     *
     * @see #getTargetCRS()
     * @see #getTargetDimensions()
     */
    protected final CoordinateReferenceSystem targetCRS;

    /**
     * Creates a new operation for the given name and CRS.
     *
     * @param authority  organization responsible for definition of the name, or {@code null}.
     * @param name       the name of the new operation.
     * @param sourceCRS  the source CRS to be returned by {@link #getSourceCRS()}.
     * @param targetCRS  the target CRS to be returned by {@link #getTargetCRS()}.
     */
    protected SimpleTransform(final Citation authority, final String name,
            final CoordinateReferenceSystem sourceCRS,
            final CoordinateReferenceSystem targetCRS)
    {
        super(authority, name);
        this.sourceCRS = sourceCRS;
        this.targetCRS = targetCRS;
    }

    /**
     * Returns the source CRS.
     *
     * @return the source CRS, or {@code null} if not available.
     */
    @Override
    public CoordinateReferenceSystem getSourceCRS() {
        return sourceCRS;
    }

    /**
     * Returns the target CRS.
     *
     * @return the target CRS, or {@code null} if not available.
     */
    @Override
    public CoordinateReferenceSystem getTargetCRS() {
        return targetCRS;
    }

    /**
     * Gets the dimension of input points. The default implementation returns
     * the dimension of the {@linkplain #getSourceCRS() source CRS}.
     *
     * @return the dimension of input points.
     */
    @Override
    public int getSourceDimensions() {
        return sourceCRS.getCoordinateSystem().getDimension();
    }

    /**
     * Gets the dimension of target points. The default implementation returns
     * the dimension of the {@linkplain #getTargetCRS() target CRS}.
     *
     * @return the dimension of input points.
     */
    @Override
    public int getTargetDimensions() {
        return targetCRS.getCoordinateSystem().getDimension();
    }

    /**
     * Gets the math transform, which is represented directly by {@code this} implementation
     * class since it does not distinguish operation and transform.
     *
     * @return the transform from source to target CRS.
     */
    @Override
    public MathTransform getMathTransform() {
        return this;
    }

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}. If
     * {@code ptDst} is {@code null}, a new {@link DirectPosition} object is allocated and
     * the result of the transformation is stored in this object. In either case, {@code ptDst},
     * which contains the transformed point, is returned for convenience. If {@code ptSrc} and
     * {@code ptDst} are the same object, the input point is correctly overwritten with the
     * transformed point.
     *
     * @param  ptSrc  the specified coordinate point to be transformed.
     * @param  ptDst  the specified coordinate point that stores the result of transforming {@code ptSrc}, or {@code null}.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result in {@code ptDst},
     *         or a newly created point if {@code ptDst} was null.
     * @throws MismatchedDimensionException if {@code ptSrc} or {@code ptDst} does not have the expected dimension.
     * @throws TransformException if the point cannot be transformed.
     */
    @Override
    public abstract DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException;

    /**
     * Returns {@code true} if the source array need to be copied before to write in the target array.
     * This method can be invoked if:
     *
     * <ul>
     *   <li>The source array and the target array are the same array (note that it can never be
     *       the case if the arrays are not of the same type)</li>
     *   <li>Each source coordinate is read atomically, and each target coordinate is written atomically
     *       (i.e. no target coordinate is written before the source coordinates are fully read)</li>
     *   <li>The coordinates are read and written in increasing array index order.</li>
     * </ul>
     *
     * @param  srcOff  the offset in the source coordinate array.
     * @param  srcDim  the dimension of input points.
     * @param  dstOff  the offset in the destination coordinate array.
     * @param  dstDim  the dimension of output points.
     * @param  numPts  the number of points to transform.
     * @return {@code true} if the source array needs to be copied.
     */
    private static boolean needsCopy(final int srcOff, final int srcDim, final int dstOff, final int dstDim, final int numPts) {
        if (numPts <= 1) {
            return false;
        }
        int ΔOff = srcOff - dstOff;
        if (ΔOff >= 0) {
            final int ΔDim = srcDim - dstDim;
            if (ΔDim >= 0 || ΔOff >= (1-numPts)*ΔDim) {
                return false;
            }
        } else {
            ΔOff = -ΔOff;
            if (ΔOff >= numPts*srcDim) {
                return false;
            }
        }
        return true;
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts  the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed.
     */
    @Override
    public void transform(double[] srcPts, int srcOff, final double[] dstPts, int dstOff, int numPts)
            throws TransformException
    {
        final int srcDim = getSourceDimensions();
        final int dstDim = getTargetDimensions();
        final SimpleDirectPosition ptSrc = new SimpleDirectPosition(srcDim);
        final SimpleDirectPosition ptDst = new SimpleDirectPosition(dstDim);
        if (srcPts == dstPts && needsCopy(srcOff, srcDim, dstOff, dstDim, numPts)) {
            srcPts = Arrays.copyOfRange(srcPts, srcOff, srcOff + srcDim*numPts);
            srcOff = 0;
        }
        while (--numPts >= 0) {
            System.arraycopy(srcPts, srcOff, ptSrc.coordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            System.arraycopy(ptDst.coordinates, 0, dstPts, dstOff, dstDim);
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts  the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed.
     */
    @Override
    public void transform(float[] srcPts, int srcOff, final float[] dstPts, int dstOff, int numPts)
            throws TransformException
    {
        final int srcDim = getSourceDimensions();
        final int dstDim = getTargetDimensions();
        final SimpleDirectPosition ptSrc = new SimpleDirectPosition(srcDim);
        final SimpleDirectPosition ptDst = new SimpleDirectPosition(dstDim);
        if (srcPts == dstPts && needsCopy(srcOff, srcDim, dstOff, dstDim, numPts)) {
            srcPts = Arrays.copyOfRange(srcPts, srcOff, srcOff + srcDim*numPts);
            srcOff = 0;
        }
        while (--numPts >= 0) {
            arraycopy(srcPts, srcOff, ptSrc.coordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            arraycopy(ptDst.coordinates, 0, dstPts, dstOff, dstDim);
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts  the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed.
     */
    @Override
    public void transform(final float[] srcPts, int srcOff, final double[] dstPts, int dstOff, int numPts)
            throws TransformException
    {
        final int srcDim = getSourceDimensions();
        final int dstDim = getTargetDimensions();
        final SimpleDirectPosition ptSrc = new SimpleDirectPosition(srcDim);
        final SimpleDirectPosition ptDst = new SimpleDirectPosition(dstDim);
        while (--numPts >= 0) {
            arraycopy(srcPts, srcOff, ptSrc.coordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            System.arraycopy(ptDst.coordinates, 0, dstPts, dstOff, dstDim);
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts  the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed.
     */
    @Override
    public void transform(final double[] srcPts, int srcOff, final float[] dstPts, int dstOff, int numPts)
            throws TransformException
    {
        final int srcDim = getSourceDimensions();
        final int dstDim = getTargetDimensions();
        final SimpleDirectPosition ptSrc = new SimpleDirectPosition(srcDim);
        final SimpleDirectPosition ptDst = new SimpleDirectPosition(dstDim);
        while (--numPts >= 0) {
            System.arraycopy(srcPts, srcOff, ptSrc.coordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            arraycopy(ptDst.coordinates, 0, dstPts, dstOff, dstDim);
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Like {@link System#arraycopy(Object, int, Object, int, int)}, but cast {@code float}
     * to {@code double} during the copy operation.
     */
    private static void arraycopy(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int length) {
        while (--length >= 0) {
            dstPts[dstOff++] = srcPts[srcOff++];
        }
    }

    /**
     * Like {@link System#arraycopy(Object, int, Object, int, int)}, but cast {@code double}
     * to {@code float} during the copy operation.
     */
    private static void arraycopy(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int length) {
        while (--length >= 0) {
            dstPts[dstOff++] = (float) srcPts[srcOff++];
        }
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
    public Matrix derivative(final DirectPosition point) throws TransformException {
        throw new TransformException();
    }

    /**
     * Creates the inverse transform of this object. The default implementation throws
     * an exception in all cases.
     *
     * @return the inverse transform.
     * @throws NoninvertibleTransformException if the transform cannot be inverted.
     */
    @Override
    public MathTransform inverse() throws NoninvertibleTransformException {
        throw new NoninvertibleTransformException();
    }

    /**
     * Tests whether this transform does not move any points. The default implementation
     * tests if the source and target CRS are equals.
     *
     * @return {@code true} if this {@code MathTransform} is an identity transform; {@code false} otherwise.
     */
    @Override
    public boolean isIdentity() {
        return Objects.equals(sourceCRS, targetCRS);
    }

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). The default implementation returns {@code null}.
     *
     * @return the coordinate operation version, or {@code null} in none.
     */
    @Override
    public String getOperationVersion() {
        return null;
    }

    /**
     * Compares this transform with the given object for equality.
     *
     * @param  object  the object to compare with this {@code SimpleTransform}.
     * @return {@code true} if the given object is equal to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final SimpleTransform other = (SimpleTransform) object;
            return Objects.equals(sourceCRS, other.sourceCRS) &&
                   Objects.equals(targetCRS, other.targetCRS);
        }
        return false;
    }
}
