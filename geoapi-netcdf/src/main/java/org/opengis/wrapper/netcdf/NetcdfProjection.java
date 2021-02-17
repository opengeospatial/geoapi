/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
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

import org.opengis.metadata.extent.Extent;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.Formula;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterValueGroup;


/**
 * A {@link org.opengis.referencing.operation.Projection} implementation backed by a netCDF {@link Projection} object.
 * The netCDF class does not distinguish <cite>Coordinate Operation</cite> from
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
    private final GeographicCRS sourceCRS;

    /**
     * The target CRS, which determine the number of target dimensions.
     *
     * @see #getTargetCRS()
     * @see #getTargetDimensions()
     */
    private final ProjectedCRS targetCRS;

    /**
     * The operation method that provider this projection instance, or {@code null} if
     * unknown. This information is set by the {@link NetcdfTransformFactory} only.
     */
    final ProjectionProvider<?> provider;

    /**
     * The netCDF projection specified at construction time.
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
     * Creates a new wrapper for the given netCDF projection object.
     *
     * @param projection  the netCDF projection.
     * @param sourceCRS   the source CRS to be returned by {@link #getSourceCRS()}, or {@code null}.
     * @param targetCRS   the target CRS to be returned by {@link #getTargetCRS()}, or {@code null}.
     */
    public NetcdfProjection(final Projection projection,
            final GeographicCRS sourceCRS,
            final ProjectedCRS targetCRS)
    {
        this(projection, null, sourceCRS, targetCRS);
    }

    /**
     * Constructor for {@link ProjectionProvider#createProjection()} usage only.
     */
    NetcdfProjection(final Projection projection,
                     final ProjectionProvider<?> provider,
                     final GeographicCRS sourceCRS,
                     final ProjectedCRS targetCRS)
    {
        Objects.requireNonNull(projection);
        this.sourceCRS  = sourceCRS;
        this.targetCRS  = targetCRS;
        this.provider   = provider;
        this.projection = projection;
        this.isInverse  = false;
    }

    /**
     * Creates a new wrapper as the inverse of the given projection.
     */
    private NetcdfProjection(final NetcdfProjection other) {
        sourceCRS  =  null;
        targetCRS  =  null;
        provider   =  other.provider;
        projection =  other.projection;
        isInverse  = !other.isInverse;
        inverse    =  other;
    }

    /**
     * Returns the netCDF projection wrapped by this adapter.
     *
     * @return the netCDF projection object.
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
     * @return the source CRS, or {@code null} if not available.
     */
    @Override
    public GeographicCRS getSourceCRS() {
        return sourceCRS;
    }

    /**
     * Returns the target CRS.
     *
     * @return the target CRS, or {@code null} if not available.
     */
    @Override
    public ProjectedCRS getTargetCRS() {
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
        return (sourceCRS != null) ? sourceCRS.getCoordinateSystem().getDimension() : 2;
    }

    /**
     * Gets the dimension of target points. The default implementation returns
     * the dimension of the {@linkplain #getTargetCRS() target CRS}.
     *
     * @return the dimension of input points.
     */
    @Override
    public int getTargetDimensions() {
        return (targetCRS != null) ? targetCRS.getCoordinateSystem().getDimension() : 2;
    }

    /**
     * Gets the math transform, which is represented directly by {@code this} implementation
     * class since it does not distinguish operation and transform.
     *
     * @return the transform from source to target CRS.
     */
    @Override
    public MathTransform2D getMathTransform() {
        return this;
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
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}. If
     * {@code ptDst} is {@code null}, a new {@link DirectPosition} object is allocated and
     * the result of the transformation is stored in this object. In either case, {@code ptDst},
     * which contains the transformed point, is returned for convenience. If {@code ptSrc} and
     * {@code ptDst} are the same object, the input point is correctly overwritten with the
     * transformed point.
     *
     * <p>This method delegates to one of the following methods:</p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       for the forward projection.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       for the inverse projection.</li>
     * </ul>
     *
     * @param  ptSrc  the specified coordinate point to be transformed.
     * @param  ptDst  the specified coordinate point that stores the result of transforming {@code ptSrc}, or {@code null}.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result in {@code ptDst},
     *         or a newly created point if {@code ptDst} was null.
     * @throws MismatchedDimensionException if {@code ptSrc} or {@code ptDst} does not have the expected dimension.
     * @throws TransformException if the point can not be transformed.
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
     *
     * <p>This method delegates to one of the following methods:</p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       for the forward projection.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       for the inverse projection.</li>
     * </ul>
     *
     * @param  ptSrc  the coordinate point to be transformed.
     * @param  ptDst  the coordinate point that stores the result of transforming {@code ptSrc},
     *         or {@code null} if a new point shall be created.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result in {@code ptDst}
     *         or in a new point if {@code ptDst} was null.
     * @throws TransformException if the point can not be transformed.
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
     *
     * <ul>
     *   <li>The source array and the target array are the same array (note that it can never be
     *       the case if the arrays are not of the same type)</li>
     *   <li>Each source coordinate is read atomically, and each target coordinate is written
     *       atomically (i.e. no target ordinate is written before the source ordinates are
     *       fully read)</li>
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
     * Transforms an arbitrary amount of points from the given source array to the given
     * destination array. This method delegates to one of the following methods for each
     * point:
     *
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)} for the forward projection.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)} for the inverse projection.</li>
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
                src.set(srcPts[srcOff+1], srcPts[srcOff]);                          // (lat,lon)
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
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts  the number of point objects to be transformed.
     * @throws TransformException if a point can not be transformed.
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
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts  the number of point objects to be transformed.
     * @throws TransformException if a point can not be transformed.
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
     * @param  srcPts  the array containing the source point coordinates.
     * @param  srcOff  the offset to the first point to be transformed in the source array.
     * @param  dstPts  the array into which the transformed point coordinates are returned. May be the same than {@code srcPts}.
     * @param  dstOff  the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point can not be transformed.
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
     * @throws TransformException if the derivative can not be evaluated at the specified point.
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
     * @param  point  the coordinate point where to evaluate the derivative.
     * @return the derivative at the specified point (never {@code null}).
     * @throws TransformException if the derivative can not be evaluated at the specified point.
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
     * Returns the domain of validity declared by the netCDF projection, or {@code null} if none.
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
         * Returns the netCDF projection wrapped by this adapter.
         *
         * @return the netCDF projection object.
         */
        @Override
        public Projection delegate() {
            return NetcdfProjection.this.delegate();
        }

        /**
         * Returns the map projection class name, for example <cite>"Transverse Mercator"</cite>.
         *
         * @see Projection#getClassName()
         */
        @Override
        public String getCode() {
            return delegate().getClassName();
        }

        /**
         * Returns the formula or procedure declared by the provider, if any.
         */
        @Override
        public Formula getFormula() {
            final ProjectionProvider<?> provider = NetcdfProjection.this.provider;
            return (provider != null) ? provider.getFormula() : null;
        }

        /**
         * Returns the number of {@linkplain MathTransform#getSourceDimensions() source dimensions}
         * of the math transform.
         *
         * @deprecated This attribute has been removed from ISO 19111:2019.
         */
        @Override
        @Deprecated
        public Integer getSourceDimensions() {
            return NetcdfProjection.this.getSourceDimensions();
        }

        /**
         * Returns the number of {@linkplain MathTransform#getTargetDimensions() target dimensions}
         * of the math transform.
         *
         * @deprecated This attribute has been removed from ISO 19111:2019.
         */
        @Override
        @Deprecated
        public Integer getTargetDimensions() {
            return NetcdfProjection.this.getTargetDimensions();
        }

        /**
         * Returns the descriptor of the math transform parameters.
         * This method returns a wrapper around the netCDF {@link Parameter} objects.
         *
         * @see Projection#getProjectionParameters()
         */
        @Override
        public ParameterDescriptorGroup getParameters() {
            return NetcdfProjection.this.getParameters();
        }
    }

    /**
     * Returns the operation method. The name of the returned method is the netCDF
     * {@linkplain Projection#getClassName() projection class name}.
     *
     * @return the operation method.
     *
     * @see Projection#getClassName()
     */
    @Override
    public OperationMethod getMethod() {
        return new Method();
    }

    /**
     * Returns the descriptor of the math transform parameters.
     * This method returns a wrapper around the netCDF {@link Parameter} objects.
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
        final List<Parameter> parameters = projection.getProjectionParameters();
        final NetcdfParameter<?>[] values = new NetcdfParameter<?>[parameters.size()];
        final Map<String,AliasList> aliases = (provider != null) ?
                provider.byNames : Collections.<String,AliasList>emptyMap();
        for (int i=0; i<values.length; i++) {
            final Parameter param = parameters.get(i);
            values[i] = NetcdfParameter.create(param, aliases.get(param.getName()));
        }
        return new SimpleParameterGroup(new AliasList(projection.getClassName()), values);
    }

    /**
     * Compares this projection with the given object for equality.
     *
     * @param  object  the object to compare with this {@code NetcdfProjection}.
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
