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

import java.util.List;
import java.util.Arrays;
import java.awt.geom.Point2D;

import ucar.unidata.util.Parameter;
import ucar.unidata.geoloc.LatLonPoint;
import ucar.unidata.geoloc.LatLonPointImpl;
import ucar.unidata.geoloc.Projection;
import ucar.unidata.geoloc.ProjectionPoint;
import ucar.unidata.geoloc.ProjectionPointImpl;

import org.opengis.referencing.operation.Formula;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.parameter.ParameterDescriptorGroup;

import org.opengis.example.referencing.SimpleTransform2D;
import org.opengis.example.parameter.SimpleParameterGroup;


/**
 * Wraps a NetCDF {@link Projection} object in a GeoAPI {@link MathTransform}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class NetcdfTransform extends SimpleTransform2D {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 8409596089701574326L;

    /**
     * The NetCDF projection specified at construction time.
     */
    protected final Projection projection;

    /**
     * {@code true} if this math transform is for the inverse projection.
     */
    protected final boolean isInverse;

    /**
     * The inverse of this math transform, or {@code null} if not yet computed.
     * Will be created by {@link #inverse()} when first needed.
     */
    private transient MathTransform2D inverse;

    /**
     * Creates a new wrapper for the given NetCDF projection object.
     *
     * @param projection The NetCDF projection.
     * @param sourceCRS  The source CRS to be returned by {@link #getSourceCRS()}.
     * @param targetCRS  The target CRS to be returned by {@link #getTargetCRS()}.
     */
    NetcdfTransform(final Projection projection,
            final CoordinateReferenceSystem sourceCRS,
            final CoordinateReferenceSystem targetCRS)
    {
        super(NetcdfIdentifiedObject.NETCDF, projection.getName(), sourceCRS, targetCRS);
        this.projection = projection;
        this.isInverse  = false;
    }

    /**
     * Creates a new wrapper as the inverse of the given projection.
     */
    private NetcdfTransform(final NetcdfTransform other) {
        super(other.getAuthority(), other.getCode() + " inverse", other.targetCRS, other.sourceCRS);
        projection =  other.projection;
        isInverse  = !other.isInverse;
        inverse    =  other;
    }

    /**
     * Transforms a single point. This method delegates to one of the following methods:
     * <p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       if {@link #isInverse} is {@code false}.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       if {@link #isInverse} is {@code true}.</li>
     * </ul>
     */
    @Override
    public Point2D transform(final Point2D ptSrc, Point2D ptDst) throws TransformException {
        if (ptDst == null) {
            ptDst = new Point2D.Double();
        }
        if (isInverse) {
            final LatLonPoint pt = projection.projToLatLon(new ProjectionPointImpl(ptSrc), null);
            ptDst.setLocation(pt.getLongitude(), pt.getLatitude());
        } else {
            final ProjectionPoint pt = projection.latLonToProj(new LatLonPointImpl(ptSrc.getX(), ptSrc.getY()), null);
            ptDst.setLocation(pt.getX(), pt.getY());
        }
        return ptDst;
    }

    /**
     * Transforms an arbitrary amount of points from the given source array to the given
     * destination array. This method delegates to one of the following methods for each
     * point:
     * <p>
     * <ul>
     *   <li>{@link Projection#latLonToProj(LatLonPoint, ProjectionPointImpl)}
     *       if {@link #isInverse} is {@code false}.</li>
     *   <li>{@link Projection#projToLatLon(ProjectionPoint, LatLonPointImpl)}
     *       if {@link #isInverse} is {@code true}.</li>
     * </ul>
     */
    @Override
    public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts)
            throws TransformException
    {
        final int srcInc = getSourceDimensions();
        final int dstInc = getTargetDimensions();
        if (srcPts == dstPts && srcOff < dstOff) {
            srcPts = Arrays.copyOfRange(srcPts, srcOff, srcOff + numPts*srcInc);
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
                src.set(srcPts[srcOff], srcPts[srcOff+1]);
                final ProjectionPoint pt = projection.latLonToProj(src, dst);
                dstPts[dstOff  ] = pt.getX();
                dstPts[dstOff+1] = pt.getY();
            }
            srcOff += srcInc;
            dstOff += dstInc;
        }
    }

    /**
     * Returns the inverse of this math transform.
     */
    @Override
    public synchronized MathTransform2D inverse() {
        if (inverse == null) {
            inverse = new NetcdfTransform(this);
        }
        return inverse;
    }

    /**
     * The operation method returned by {@link NetcdfProjection#getMethod()}. The main purpose of
     * this implementation is to provide access to the {@link Projection#getClassName()} method.
     *
     * @author Martin Desruisseaux (Geomatys)
     * @version 3.1
     *
     * @since 3.1
     */
    final class Method extends NetcdfIdentifiedObject implements OperationMethod {
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
            return projection;
        }

        /**
         * Returns the map projection class name, for example "<cite>Transverse Mercator</cite>".
         *
         * @see Projection#getClassName()
         */
        @Override
        public String getCode() {
            return projection.getClassName();
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
            return NetcdfTransform.this.getSourceDimensions();
        }

        /**
         * Returns the number of {@linkplain MathTransform#getTargetDimensions() target dimensions}
         * of the math transform.
         */
        @Override
        public Integer getTargetDimensions() {
            return NetcdfTransform.this.getTargetDimensions();
        }

        /**
         * Returns the descriptor of the math transform parameters.
         * This method returns a wrapper around the Netcdf {@link Parameter} objects.
         *
         * @see Projection#getProjectionParameters()
         */
        @Override
        public ParameterDescriptorGroup getParameters() {
            final List<Parameter> param = projection.getProjectionParameters();
            final NetcdfParameter<?>[] values = new NetcdfParameter<?>[param.size()];
            for (int i=0; i<values.length; i++) {
                values[i] = NetcdfParameter.create(param.get(i));
            }
            return new SimpleParameterGroup(NETCDF, projection.getClassName(), values);
        }
    }
}
