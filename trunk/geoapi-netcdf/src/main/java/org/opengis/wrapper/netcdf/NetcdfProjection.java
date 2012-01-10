/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import ucar.unidata.util.Parameter;
import ucar.unidata.geoloc.LatLonRect;
import ucar.unidata.geoloc.LatLonPoint;
import ucar.unidata.geoloc.LatLonPointImpl;
import ucar.unidata.geoloc.Projection;
import ucar.unidata.geoloc.ProjectionPoint;
import ucar.unidata.geoloc.ProjectionPointImpl;
import ucar.unidata.geoloc.projection.ProjectionAdapter;

import org.opengis.util.InternationalString;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.Formula;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterValueGroup;


/**
 * Wraps a NetCDF {@link Projection} object in a GeoAPI
 * {@link org.opengis.referencing.operation.Projection}.
 * The NetCDF class does not distinguish <cite>Coordinate Operation</cite> from
 * <cite>Math Transform</cite>, so we implement the two interfaces by the same class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfProjection extends NetcdfIdentifiedObject
        implements org.opengis.referencing.operation.Projection, MathTransform2D
{
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 6497844299422453709L;

    /**
     * The source CRS, which determine the number of source dimensions.
     *
     * @see #getSourceCRS()
     * @see #getSourceDimensions()
     */
    private final CoordinateReferenceSystem sourceCRS;

    /**
     * The target CRS, which determine the number of target dimensions.
     *
     * @see #getTargetCRS()
     * @see #getTargetDimensions()
     */
    private final CoordinateReferenceSystem targetCRS;

    /**
     * The NetCDF projection specified at construction time.
     */
    private final Projection projection;

    /**
     * {@code true} if this math transform is for the inverse projection.
     */
    private final boolean isInverse;

    /**
     * The inverse of this math transform, or {@code null} if not yet computed.
     * Will be created by {@link #inverse()} when first needed.
     */
    private transient MathTransform2D inverse;

    /**
     * Creates a new wrapper for the given NetCDF projection object.
     *
     * @param projection The NetCDF projection.
     * @param sourceCRS  The source CRS to be returned by {@link #getSourceCRS()}, or {@code null}.
     * @param targetCRS  The target CRS to be returned by {@link #getTargetCRS()}, or {@code null}.
     */
    public NetcdfProjection(final Projection projection,
            final CoordinateReferenceSystem sourceCRS,
            final CoordinateReferenceSystem targetCRS)
    {
        this.sourceCRS  = sourceCRS;
        this.targetCRS  = targetCRS;
        this.projection = projection;
        this.isInverse  = false;
    }

    /**
     * Creates a new wrapper as the inverse of the given projection.
     */
    private NetcdfProjection(final NetcdfProjection other) {
        sourceCRS  =  other.targetCRS;
        targetCRS  =  other.sourceCRS;
        projection =  other.projection;
        isInverse  = !other.isInverse;
        inverse    =  other;
    }

    /**
     * Returns the NetCDF projection wrapped by this adapter.
     *
     * @return The NetCDF projection object.
     */
    @Override
    public Projection delegate() {
        return projection;
    }

    /**
     * Returns the projection name.
     *
     * @see Projection#getName()
     */
    @Override
    public String getCode() {
        return projection.getName();
    }

    /**
     * Tests whether this transform does not move any points. The default implementation
     * tests if the source and target CRS are equals.
     *
     * @return {@code true} if this {@code MathTransform} is
     *         an identity transform; {@code false} otherwise.
     */
    @Override
    public boolean isIdentity() {
        return Objects.equals(sourceCRS, targetCRS);
    }

    /**
     * Returns the source CRS.
     *
     * @return The source CRS, or {@code null} if not available.
     */
    @Override
    public CoordinateReferenceSystem getSourceCRS() {
        return sourceCRS;
    }

    /**
     * Returns the target CRS.
     *
     * @return The target CRS, or {@code null} if not available.
     */
    @Override
    public CoordinateReferenceSystem getTargetCRS() {
        return targetCRS;
    }

    /**
     * Gets the dimension of input points. The default implementation returns
     * the dimension of the {@linkplain #getSourceCRS() source CRS}.
     *
     * @return The dimension of input points.
     */
    @Override
    public int getSourceDimensions() {
        return (sourceCRS != null) ? sourceCRS.getCoordinateSystem().getDimension() : 2;
    }

    /**
     * Gets the dimension of target points. The default implementation returns
     * the dimension of the {@linkplain #getTargetCRS() target CRS}.
     *
     * @return The dimension of input points.
     */
    @Override
    public int getTargetDimensions() {
        return (targetCRS != null) ? targetCRS.getCoordinateSystem().getDimension() : 2;
    }

    /**
     * Gets the math transform, which is represented directly by {@code this} implementation
     * class since it does not distinguish operation and transform.
     *
     * @return The transform from source to target CRS.
     */
    @Override
    public MathTransform2D getMathTransform() {
        return this;
    }

    /**
     * Ensures that the given position is two-dimensional.
     *
     * @param  point The position to verify, or {@code null}.
     * @throws MismatchedDimensionException If the given position is not two-dimensional.
     */
    private static void ensureTwoDimensional(final DirectPosition point) throws MismatchedDimensionException {
        if (point != null && point.getDimension() != 2) {
            throw new MismatchedDimensionException("All given positions shall be two-dimensional.");
        }
    }

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}. If
     * {@code ptDst} is {@code null}, a new {@link DirectPosition} object is allocated and
     * the result of the transformation is stored in this object. In either case, {@code ptDst},
     * which contains the transformed point, is returned for convenience. If {@code ptSrc} and
     * {@code ptDst} are the same object, the input point is correctly overwritten with the
     * transformed point.
     * <p>
     * This method delegates to one of the following methods:
     * <p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       for the forward projection.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       for the inverse projection.</li>
     * </ul>
     *
     * @param  ptSrc the specified coordinate point to be transformed.
     * @param  ptDst the specified coordinate point that stores the result of transforming
     *         {@code ptSrc}, or {@code null}.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst}, or a newly created point if {@code ptDst} was null.
     * @throws MismatchedDimensionException if {@code ptSrc} or
     *         {@code ptDst} doesn't have the expected dimension.
     * @throws TransformException if the point can't be transformed.
     */
    @Override
    public DirectPosition transform(final DirectPosition ptSrc, DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException
    {
        ensureTwoDimensional(ptSrc);
        ensureTwoDimensional(ptDst);
        double x = ptSrc.getOrdinate(0);
        double y = ptSrc.getOrdinate(1);
        if (isInverse) {
            final LatLonPoint pt = projection.projToLatLon(new ProjectionPointImpl(x, y), new LatLonPointImpl());
            x = pt.getLongitude();
            y = pt.getLatitude();
        } else {
            final ProjectionPoint pt = projection.latLonToProj(new LatLonPointImpl(y, x), new ProjectionPointImpl());
            x = pt.getX();
            y = pt.getY();
        }
        if (ptDst == null) {
            ptDst = new SimpleDirectPosition(2);
        }
        ptDst.setOrdinate(0, x);
        ptDst.setOrdinate(1, y);
        return ptDst;
    }

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}.
     * If {@code ptDst} is {@code null}, a new {@link Point2D} object is allocated
     * and then the result of the transformation is stored in this object. In either case,
     * {@code ptDst}, which contains the transformed point, is returned for convenience.
     * If {@code ptSrc} and {@code ptDst} are the same object, the input point is
     * correctly overwritten with the transformed point.
     * <p>
     * This method delegates to one of the following methods:
     * <p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       for the forward projection.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       for the inverse projection.</li>
     * </ul>
     *
     * @param  ptSrc the coordinate point to be transformed.
     * @param  ptDst the coordinate point that stores the result of transforming {@code ptSrc},
     *         or {@code null} if a new point shall be created.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst} or in a new point if {@code ptDst} was null.
     * @throws TransformException if the point can't be transformed.
     */
    @Override
    public Point2D transform(final Point2D ptSrc, final Point2D ptDst) throws TransformException {
        double x = ptSrc.getX();
        double y = ptSrc.getY();
        if (isInverse) {
            final LatLonPoint pt = projection.projToLatLon(new ProjectionPointImpl(x, y), new LatLonPointImpl());
            x = pt.getLongitude();
            y = pt.getLatitude();
        } else {
            final ProjectionPoint pt = projection.latLonToProj(new LatLonPointImpl(y, x), new ProjectionPointImpl());
            x = pt.getX();
            y = pt.getY();
        }
        if (ptDst == null) {
            return new Point2D.Double(x, y);
        }
        ptDst.setLocation(x, y);
        return ptDst;
    }

    /**
     * Returns {@code true} if the source array need to be copied before to write in the target array.
     * This method can be invoked if:
     * <p>
     * <ul>
     *   <li>The source array and the target array are the same array (note that it can never be
     *       the case if the arrays are not of the same type)</li>
     *   <li>Each source coordinate is read atomically, and each target coordinate is written
     *       atomically (i.e. no target ordinate is written before the source ordinates are
     *       fully read)</li>
     *   <li>The coordinates are read and written in increasing array index order.</li>
     * </ul>
     *
     * @param  srcOff The offset in the source coordinate array.
     * @param  srcDim The dimension of input points.
     * @param  dstOff The offset in the destination coordinate array.
     * @param  dstDim The dimension of output points.
     * @param  numPts The number of points to transform.
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
     * Transforms an arbitrary amount of points from the given source array to the given
     * destination array. This method delegates to one of the following methods for each
     * point:
     * <p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       for the forward projection.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       for the inverse projection.</li>
     * </ul>
     */
    @Override
    public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts)
            throws TransformException
    {
        final int srcDim = getSourceDimensions();
        final int dstDim = getTargetDimensions();
        if (srcPts == dstPts && needsCopy(srcOff, srcDim, dstOff, dstDim, numPts)) {
            srcPts = Arrays.copyOfRange(srcPts, srcOff, srcOff + numPts*srcDim);
            srcOff = 0;
        }
        final LatLonPointImpl     src = new LatLonPointImpl();
        final ProjectionPointImpl dst = new ProjectionPointImpl();
        while (--numPts >= 0) {
            if (isInverse) {
                dst.setLocation(srcPts[srcOff], srcPts[srcOff+1]);
                final LatLonPoint pt = projection.projToLatLon(dst, src);
                dstPts[dstOff]   = pt.getLongitude();
                dstPts[dstOff+1] = pt.getLatitude();
            } else {
                src.set(srcPts[srcOff+1], srcPts[srcOff]); // (lat,lon)
                final ProjectionPoint pt = projection.latLonToProj(src, dst);
                dstPts[dstOff  ] = pt.getX();
                dstPts[dstOff+1] = pt.getY();
            }
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     *                May be the same than {@code srcPts}.
     * @param  dstOff the offset to the location of the first transformed point that is
     *                stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point can't be transformed.
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
            arraycopy(srcPts, srcOff, ptSrc.ordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            arraycopy(ptDst.ordinates, 0, dstPts, dstOff, dstDim);
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     *                May be the same than {@code srcPts}.
     * @param  dstOff the offset to the location of the first transformed point that is
     *                stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point can't be transformed.
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
            arraycopy(srcPts, srcOff, ptSrc.ordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            System.arraycopy(ptDst.ordinates, 0, dstPts, dstOff, dstDim);
            srcOff += srcDim;
            dstOff += dstDim;
        }
    }

    /**
     * Transforms a list of coordinate point ordinal values.
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     *                May be the same than {@code srcPts}.
     * @param  dstOff the offset to the location of the first transformed point that is
     *                stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point can't be transformed.
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
            System.arraycopy(srcPts, srcOff, ptSrc.ordinates, 0, srcDim);
            transform(ptSrc, ptDst);
            arraycopy(ptDst.ordinates, 0, dstPts, dstOff, dstDim);
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
     * Transforms the specified shape. The default implementation returns a new shape with
     * the transform of all control points.
     *
     * @param  shape The Shape to transform.
     * @return The transformed shape.
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
     * @param  point The coordinate point where to evaluate the derivative.
     * @return The derivative at the specified point (never {@code null}).
     * @throws TransformException if the derivative can't be evaluated at the specified point.
     */
    @Override
    public Matrix derivative(final DirectPosition point) throws TransformException {
        ensureTwoDimensional(point);
        return derivative(new Point2D.Double(point.getOrdinate(0), point.getOrdinate(1)));
    }

    /**
     * Gets the derivative of this transform at a point. The default implementation throws
     * an exception in all cases.
     *
     * @param  point The coordinate point where to evaluate the derivative.
     * @return The derivative at the specified point (never {@code null}).
     * @throws TransformException if the derivative can't be evaluated at the specified point.
     */
    @Override
    public Matrix derivative(final Point2D point) throws TransformException {
        throw new TransformException();
    }

    /**
     * Returns the inverse of this math transform.
     */
    @Override
    public synchronized MathTransform2D inverse() {
        if (inverse == null) {
            inverse = new NetcdfProjection(this);
        }
        return inverse;
    }

    /**
     * Returns the domain of validity declared by the NetCDF projection, or {@code null} if none.
     *
     * @see ucar.unidata.geoloc.ProjectionImpl#getDefaultMapAreaLL()
     */
    @Override
    public Extent getDomainOfValidity() {
        final LatLonRect domain = ProjectionAdapter.factory(projection).getDefaultMapAreaLL();
        if (domain != null) {
            return new SimpleGeographicBoundingBox(
                    domain.getLonMin(), domain.getLonMax(),
                    domain.getLatMin(), domain.getLatMax());
        }
        return null;
    }

    /**
     * Returns {@code null}, since this adapter does not have information about the projection
     * scope.
     */
    @Override
    public InternationalString getScope() {
        return null;
    }

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). The default implementation returns {@code null}.
     *
     * @return The coordinate operation version, or {@code null} in none.
     */
    @Override
    public String getOperationVersion() {
        return null;
    }

    /**
     * Estimate(s) of the impact of this operation on point accuracy.
     * The default implementation returns an empty set.
     *
     * @return The position error estimates, or an empty set if not available.
     */
    @Override
    public Set<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return Collections.emptySet();
    }

    /**
     * The operation method returned by {@link NetcdfProjection#getMethod()}. The main purpose of
     * this implementation is to provide access to the {@link Projection#getClassName()} method.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    private final class Method extends NetcdfIdentifiedObject implements OperationMethod {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -5114329943808680717L;

        /**
         * Returns the NetCDF projection wrapped by this adapter.
         *
         * @return The NetCDF projection object.
         */
        @Override
        public Projection delegate() {
            return NetcdfProjection.this.delegate();
        }

        /**
         * Returns the map projection class name, for example "<cite>Transverse Mercator</cite>".
         *
         * @see Projection#getClassName()
         */
        @Override
        public String getCode() {
            return delegate().getClassName();
        }

        /**
         * Not yet implemented.
         */
        @Override
        public Formula getFormula() {
            throw new UnsupportedOperationException();
        }

        /**
         * Returns the number of {@linkplain MathTransform#getSourceDimensions() source dimensions}
         * of the math transform.
         */
        @Override
        public Integer getSourceDimensions() {
            return NetcdfProjection.this.getSourceDimensions();
        }

        /**
         * Returns the number of {@linkplain MathTransform#getTargetDimensions() target dimensions}
         * of the math transform.
         */
        @Override
        public Integer getTargetDimensions() {
            return NetcdfProjection.this.getTargetDimensions();
        }

        /**
         * Returns the descriptor of the math transform parameters.
         * This method returns a wrapper around the NetCDF {@link Parameter} objects.
         *
         * @see Projection#getProjectionParameters()
         */
        @Override
        public ParameterDescriptorGroup getParameters() {
            return NetcdfProjection.this.getParameters();
        }
    }

    /**
     * Returns the operation method. The name of the returned method is the NetCDF
     * {@linkplain Projection#getClassName() projection class name}.
     *
     * @return The operation method.
     *
     * @see Projection#getClassName()
     */
    @Override
    public OperationMethod getMethod() {
        return new Method();
    }

    /**
     * Returns the descriptor of the math transform parameters.
     * This method returns a wrapper around the NetCDF {@link Parameter} objects.
     *
     * @see Projection#getProjectionParameters()
     */
    @Override
    public ParameterValueGroup getParameterValues() {
        return getParameters();
    }

    /**
     * Implementations of {@link #getParameterValues()} and {@link Method#getParameters()}.
     */
    final SimpleParameterGroup getParameters() {
        final List<Parameter> param = projection.getProjectionParameters();
        final NetcdfParameter<?>[] values = new NetcdfParameter<?>[param.size()];
        for (int i=0; i<values.length; i++) {
            values[i] = NetcdfParameter.create(param.get(i));
        }
        return new SimpleParameterGroup(projection.getClassName(), values);
    }

    /**
     * Compares this projection with the given object for equality.
     *
     * @param  object The object to compare with this {@code NetcdfProjection}.
     * @return {@code true} if the given object is equals to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final NetcdfProjection other = (NetcdfProjection) object;
            return Objects.equals(sourceCRS,  other.sourceCRS) &&
                   Objects.equals(targetCRS,  other.targetCRS) &&
                   Objects.equals(projection, other.projection);
        }
        return false;
    }
}
