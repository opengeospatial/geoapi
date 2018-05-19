/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import ucar.nc2.units.DateUnit;
import ucar.nc2.constants.AxisType;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.CoordinateAxis;
import ucar.nc2.dataset.CoordinateAxis1D;
import ucar.nc2.dataset.CoordinateSystem;
import ucar.nc2.dataset.CoordinateAxis1DTime;

import org.opengis.metadata.extent.Extent;
import org.opengis.util.FactoryException;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.coverage.grid.Grid;
import org.opengis.coverage.grid.GridGeometry;
import org.opengis.coverage.grid.GridEnvelope;
import org.opengis.coverage.grid.GridCoordinates;


/**
 * A {@link CoordinateReferenceSystem} implementation backed by a netCDF {@link CoordinateSystem} object.
 * This class implements both the GeoAPI {@link org.opengis.referencing.cs.CoordinateSystem} and
 * {@link CoordinateReferenceSystem} interfaces because the netCDF {@code CoordinateSystem}
 * object combines the concepts of both of them. It also implements the {@link GridGeometry}
 * interface since netCDF Coordinate Systems contain all information related to the image grid.
 *
 * <p><b>Axis order</b><br>
 * The order of axes returned by {@link #getAxis(int)} is reversed compared to the order of axes
 * in the wrapped netCDF coordinate system. This is because the netCDF convention stores axes in
 * the (<var>time</var>, <var>height</var>, <var>latitude</var>, <var>longitude</var>) order, while
 * referencing framework often uses the (<var>longitude</var>, <var>latitude</var>, <var>height</var>,
 * <var>time</var>) order.</p>
 *
 * <p><b>Restrictions</b><br>
 * Current implementation has the following restrictions:</p>
 * <ul>
 *   <li><p>This class supports only axes of kind {@link CoordinateAxis1D}. Callers can verify this
 *       condition with a call to the {@link CoordinateSystem#isProductSet()} method on the wrapped
 *       netCDF coordinate system, which shall returns {@code true}.</p></li>
 *
 *   <li><p>At the time of writing, the netCDF API doesn't specify the CRS datum. Consequently the
 *       current implementation assumes that all {@code NetcdfCRS} instances use a spherical datum.
 *       We presume a sphere rather than WGS84 because the netCDF projection framework uses spherical
 *       formulas.</p></li>
 *
 *   <li><p>This class assumes that the list of netCDF axes returned by
 *       {@link CoordinateSystem#getCoordinateAxes()} is stable during the
 *       lifetime of this {@code NetcdfCRS} instance.</p></li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfCRS extends NetcdfIdentifiedObject implements CoordinateReferenceSystem,
        org.opengis.referencing.cs.CoordinateSystem, GridGeometry, GridEnvelope
{
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 104309666271704901L;

    /**
     * Small tolerance factor for rounding error.
     */
    private static final double EPS = 1E-10;

    /**
     * The netCDF coordinate system wrapped by this {@code NetcdfCRS} instance.
     */
    private final CoordinateSystem cs;

    /**
     * The netCDF axes.
     */
    private final NetcdfAxis[] axes;

    /**
     * The grid to CRS transform, computed when first needed.
     */
    private transient MathTransform gridToCRS;

    /**
     * Creates a new {@code NetcdfCRS} object wrapping the same netCDF coordinate system
     * than the given object. This copy constructor is provided for subclasses wanting to
     * wraps the same netCDF coordinate system and change a few properties or methods.
     *
     * @param crs  the CRS to copy.
     */
    protected NetcdfCRS(final NetcdfCRS crs) {
        this.cs        = crs.cs;
        this.axes      = crs.axes;
        this.gridToCRS = crs.gridToCRS;
    }

    /**
     * Creates a new {@code NetcdfCRS} object wrapping the given netCDF coordinate system.
     * The {@link CoordinateSystem#getCoordinateAxes()} is invoked at construction time and
     * every elements are assumed instances of {@link CoordinateAxis1D}.
     *
     * @param  netcdfCS  the netCDF coordinate system to wrap.
     * @throws ClassCastException if at least one axis is not an instance of the
     *         {@link CoordinateAxis1D} subclass.
     */
    protected NetcdfCRS(final CoordinateSystem netcdfCS) throws ClassCastException {
        this(netcdfCS, netcdfCS.getCoordinateAxes());
    }

    /**
     * Creates a new {@code NetcdfCRS} object wrapping the given axes of the given netCDF
     * coordinate system. The axes will be retained in reverse order, as documented in
     * class javadoc.
     *
     * @param  netcdfCS    the netCDF coordinate system to wrap.
     * @param  netcdfAxis  the axes to add, in reverse order.
     */
    NetcdfCRS(final CoordinateSystem netcdfCS, final List<CoordinateAxis> netcdfAxis) {
        Objects.requireNonNull(netcdfCS);
        cs = netcdfCS;
        final int dimension = netcdfAxis.size();
        axes = new NetcdfAxis[dimension];
        for (int i=0; i<dimension; i++) {
            // Adds the axis in reverse order. See class javadoc for explanation.
            axes[(dimension-1) - i] = new NetcdfAxis((CoordinateAxis1D) netcdfAxis.get(i));
        }
    }

    /**
     * Creates a new {@code NetcdfCRS} with {@link NetcdfAxis} instances fetched
     * from the given components. This is used by the {@link Compound} constructor.
     */
    NetcdfCRS(final CoordinateSystem netcdfCS, final NetcdfCRS... components) {
        cs = netcdfCS;
        final List<NetcdfAxis> axes = new ArrayList<>(netcdfCS.getRankRange());
        for (final NetcdfCRS c : components) {
            axes.addAll(Arrays.asList(c.axes));
        }
        this.axes = axes.toArray(new NetcdfAxis[axes.size()]);
    }

    /**
     * Creates a new {@code NetcdfCRS} object wrapping the given netCDF coordinate system.
     * The returned object may implement any of the {@link ProjectedCRS}, {@link GeographicCRS}
     * {@link VerticalCRS} or {@link TemporalCRS}, depending on the {@linkplain AxisType axis
     * types}.
     *
     * <p>If the netCDF object contains different kind of CRS, then the returned CRS will be an
     * instance of {@link CompoundCRS} in which each component implements one of the above-cited
     * interfaces.</p>
     *
     * <p>If the netCDF object contains axes of unknown type, then the returned CRS will not
     * implement any of the above-cited interfaces.</p>
     *
     * @param  netcdfCS  the netCDF coordinate system to wrap, or {@code null} if none.
     * @return a wrapper for the given object, or {@code null} if the argument was null.
     * @throws ClassCastException if at least one axis is not an instance of the {@link CoordinateAxis1D} subclass.
     */
    public static NetcdfCRS wrap(final CoordinateSystem netcdfCS) throws ClassCastException {
        try {
            return wrap(netcdfCS, null, null);
        } catch (IOException e) {
            throw new AssertionError(e);    // Should never happen, since we didn't performed any I/O.
        }
    }

    /**
     * Creates a new {@code NetcdfCRS} object, optionally using the given netCDF file for additional
     * information. This method performs the same work than {@link #wrap(CoordinateSystem)}, except
     * that more accurate coordinate axes may be created if a reference to the original dataset file
     * is provided. This apply especially to {@link CoordinateAxis1DTime}.
     *
     * @param  netcdfCS  the netCDF coordinate system to wrap, or {@code null} if none.
     * @param  file      the originating dataset file, or {@code null} if none.
     * @param  logger    an optional object where to log warnings, or {@code null} if none.
     * @return a wrapper for the given object, or {@code null} if the {@code netcdfCS} argument was null.
     * @throws ClassCastException if at least one axis is not an instance of the {@link CoordinateAxis1D} subclass.
     * @throws IOException if an I/O operation was needed and failed.
     *
     * @since 3.14
     */
    public static NetcdfCRS wrap(final CoordinateSystem netcdfCS, final NetcdfDataset file,
                final Logger logger) throws IOException, ClassCastException
    {
        if (netcdfCS == null) {
            return null;
        }
        /*
         * Separate the horizontal, vertical and temporal components. We need to iterate
         * over the netCDF axes in reverse order (see class javadoc). We don't use the
         * CoordinateAxis.getTaxis() and similar methods because we want to ensure that
         * the components are build in the same order than axes are found.
         */
        final List<NetcdfCRS> components = new ArrayList<>(4);
        final List<CoordinateAxis>  axes = netcdfCS.getCoordinateAxes();
        for (int i=axes.size(); --i>=0;) {
            CoordinateAxis1D axis = (CoordinateAxis1D) axes.get(i);
            if (axis != null) {
                final AxisType type = axis.getAxisType();
                if (type != null) { // This is really null in some netCDF file.
                    switch (type) {
                        case Pressure:
                        case Height:
                        case GeoZ: {
                            components.add(new Vertical(netcdfCS, axis));
                            continue;
                        }
                        case RunTime:
                        case Time: {
                            components.add(new Temporal(netcdfCS, Temporal.complete(axis, file, logger)));
                            continue;
                        }
                        case Lat:
                        case Lon: {
                            final int upper = i+1;
                            i = lower(axes, i, AxisType.Lat, AxisType.Lon);
                            components.add(new Geographic(netcdfCS, axes.subList(i, upper)));
                            continue;
                        }
                        case GeoX:
                        case GeoY: {
                            final int upper = i+1;
                            i = lower(axes, i, AxisType.GeoX, AxisType.GeoY);
                            components.add(new Projected(netcdfCS, axes.subList(i, upper)));
                            continue;
                        }
                    }
                }
            }
            // Unknown axes: do not try to split.
            components.clear();
            break;
        }
        final int size = components.size();
        switch (size) {
            /*
             * If we have been unable to split the CRS ourself in various components,
             * use the information provided by the netCDF library as a fallback. Note
             * that the CRS created that way may not be valid in the ISO 19111 sense.
             */
            case 0: {
                if (netcdfCS.isLatLon()) {
                    return new Geographic(netcdfCS, axes);
                }
                if (netcdfCS.isGeoXY()) {
                    return new Projected(netcdfCS, axes);
                }
                return new NetcdfCRS(netcdfCS, axes);
            }
            /*
             * If we have been able to create exactly one CRS, returns that CRS.
             */
            case 1: {
                return components.get(0);
            }
            /*
             * Otherwise create a CompoundCRS will all the components we have separated.
             */
            default: {
                return new Compound(netcdfCS, components.toArray(new NetcdfCRS[size]));
            }
        }
    }

    /**
     * Returns the lower index of the sublist containing axes of the given types.
     *
     * @param  axes   the list from which to get the sublist indices.
     * @param  upper  the upper index of the sublist, inclusive.
     * @param  t1     the first axis type to accept.
     * @param  t2     the second axis type to accept.
     * @return the lower index of the sublist range.
     */
    private static int lower(final List<CoordinateAxis> axes, int upper, final AxisType t1, final AxisType t2) {
        while (upper != 0) {
            final AxisType type = axes.get(upper-1).getAxisType();
            if (type != t1 && type != t2) {
                break;
            }
            upper--;
        }
        return upper;
    }

    /**
     * Returns the wrapped netCDF coordinate system.
     *
     * <p><b>Note:</b> The dimension of the returned netCDF Coordinate System may be greater than the
     * dimension of the GeoAPI CRS implemented by this object, because the netCDF CS puts all axes
     * in a single object while the GeoAPI CRS may splits the axes in various kind of CRS
     * ({@link GeographicCRS}, {@link VerticalCRS}, {@link TemporalCRS}).</p>
     */
    @Override
    public CoordinateSystem delegate() {
        return cs;
    }

    /**
     * Returns the coordinate system name. The default implementation delegates to
     * {@link CoordinateSystem#getName()}.
     *
     * @see CoordinateSystem#getName()
     */
    @Override
    public String getCode() {
        return cs.getName();
    }

    /**
     * Returns the number of dimensions.
     *
     * @see CoordinateSystem#getRankRange()
     */
    @Override
    public int getDimension() {
        return axes.length;
    }

    /**
     * Returns the coordinate system, which is {@code this}.
     */
    @Override
    public org.opengis.referencing.cs.CoordinateSystem getCoordinateSystem() {
        return this;
    }

    /**
     * Returns the axis at the given dimension. Note that the order of axes returned by this
     * method is reversed compared to the order of axes in the netCDF coordinate system. See
     * the <a href="#skip-navbar_top">class javadoc</a> for more information.
     *
     * @param  dimension  the zero based index of axis.
     * @return the axis at the specified dimension.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     *
     * @see CoordinateSystem#getCoordinateAxes()
     */
    @Override
    public NetcdfAxis getAxis(final int dimension) throws IndexOutOfBoundsException {
        return axes[dimension];
    }

    /**
     * Returns the number of numeric values in the axis at the given dimension.
     *
     * @param  dimension  the zero based index of axis.
     * @return the number of values for the given axis.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     *
     * @see NetcdfAxis#length()
     */
    @Override
    public int getSpan(final int dimension) throws IndexOutOfBoundsException {
        return axes[dimension].length();
    }

    /**
     * Returns the valid minimum inclusive grid coordinate along the specified dimension,
     * which is assumed zero.
     *
     * @param  dimension  the zero based index of axis.
     * @return the minimum inclusive grid coordinates for the given axis.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     */
    @Override
    public int getLow(int dimension) throws IndexOutOfBoundsException {
        return 0;
    }

    /**
     * Returns the valid maximum inclusive grid coordinate along the specified dimension.
     *
     * @param  dimension  the zero based index of axis.
     * @return the maximum inclusive grid coordinates for the given axis.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     */
    @Override
    public int getHigh(int dimension) throws IndexOutOfBoundsException {
        return getLow(dimension) + getSpan(dimension) - 1;
    }

    /**
     * Returns the minimal coordinate values for all grid points within the {@linkplain Grid grid}.
     * The default implementation builds the grid coordinates from the values returned by
     * {@link #getLow(int)}.
     *
     * @return the minimal coordinate values for all grid points, inclusive.
     */
    @Override
    public GridCoordinates getLow() {
        final int[] c = new int[getDimension()];
        for (int i=0; i<c.length; i++) {
            c[i] = getLow(i);
        }
        return new SimpleGridCoordinates(c);
    }

    /**
     * Returns the maximal coordinate values for all grid points within the {@linkplain Grid grid}.
     * The default implementation builds the grid coordinates from the values returned by
     * {@link #getHigh(int)}.
     *
     * @return the maximal coordinate values for all grid points, <strong>inclusive</strong>.
     */
    @Override
    public GridCoordinates getHigh() {
        final int[] c = new int[getDimension()];
        for (int i=0; i<c.length; i++) {
            c[i] = getHigh(i);
        }
        return new SimpleGridCoordinates(c);
    }

    /**
     * Returns the valid coordinate range of the netCDF grid coordinates.
     * The lowest valid grid coordinate is usually zero. The highest valid
     * grid coordinates are usually the length of each axis minus 1.
     *
     * @return the valid coordinate range of a grid coverage.
     */
    @Override
    public GridEnvelope getExtent() {
        return this;
    }

    /**
     * @deprecated Renamed {@link #getExtent()}.
     */
    @Override
    @Deprecated
    public final GridEnvelope getGridRange() {
        return getExtent();
    }

    /**
     * Returns the transform from grid coordinates to this CRS coordinates, or {@code null} if
     * none. If this CRS is regular and two-dimensional, then the returned transform is also an
     * instance of Java2D {@link java.awt.geom.AffineTransform}.
    *
    * <p><b>Limitation</b><br>
     * Current implementation can build a transform only for regular coordinate systems.
     * A future implementation may be more general.</p>
     *
     * @return the transform from grid to this CRS, or {@code null} if none.
     */
    @Override
    public synchronized MathTransform getGridToCRS() {
        if (gridToCRS == null) {
            gridToCRS = getGridToCRS(0, axes.length);
        }
        return gridToCRS;
    }

    /**
     * Returns the transform from grid coordinates to this CRS coordinates in the given
     * range of dimensions.
     *
     * <p><b>Limitation</b><br>
     * Current implementation can build a transform only for regular axes.
     * A future implementation may be more general.</p>
     *
     * @param  lowerDimension  index of the first dimension for which to get the transform.
     * @param  upperDimension  index after the last dimension for which to get the transform.
     * @return the transform from grid to this CRS in the given range of dimensions, or {@code null} if none.
     * @throws IllegalArgumentException if the given dimensions are not in the
     *         [0 … {@linkplain #getDimension() dimension}] range.
     */
    public MathTransform getGridToCRS(final int lowerDimension, final int upperDimension) {
        if (lowerDimension < 0 || upperDimension > axes.length || upperDimension < lowerDimension) {
            throw new IllegalArgumentException("Illegal range");
        }
        final int numDimensions = upperDimension - lowerDimension;
        final SimpleMatrix matrix = new SimpleMatrix(numDimensions + 1);
        for (int i=0; i<numDimensions; i++) {
            final CoordinateAxis1D axis = axes[lowerDimension + i].delegate();
            if (!axis.isRegular()) {
                return null;
            }
            final double scale = axis.getIncrement();
            if (Double.isNaN(scale) || scale == 0) {
                return null;
            }
            matrix.setElement(i, i, nice(scale));
            matrix.setElement(i, numDimensions, nice(axis.getStart()));
        }
        try {
            return Factories.getFactory(MathTransformFactory.class).createAffineTransform(matrix);
        } catch (FactoryException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Workaround rounding errors found in netCDF files.
     *
     * @since 3.16
     */
    private static double nice(double value) {
        final double tf = value * 360;
        final double ti = Math.rint(tf);
        if (Math.abs(tf - ti) <= EPS) {
            value = ti / 360;
        }
        return value;
    }

    /**
     * Returns the area or region or timeframe in which this CRS is valid, or {@code null} if none.
     * The default implementation returns {@code null} except for {@link ProjectedCRS}, in which
     * case the {@link NetcdfProjection} domain of validity is returned.
     */
    @Override
    public Extent getDomainOfValidity() {
        return null;
    }



    /**
     * The CRS for compound CRS.
     *
     * @author Martin Desruisseaux (Geomatys)
     * @version 3.1
     *
     * @since 3.1
     */
    private static final class Compound extends NetcdfCRS implements CompoundCRS,
            org.opengis.referencing.cs.CoordinateSystem
    {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -539244102356523189L;

        /**
         * The components of this compound CRS.
         */
        private final List<CoordinateReferenceSystem> components;

        /**
         * Wraps the given coordinate system.
         */
        Compound(final CoordinateSystem cs, final NetcdfCRS[] components) {
            super(cs, components);
            this.components = Collections.unmodifiableList(Arrays.<CoordinateReferenceSystem>asList(components));
        }

        /**
         * Wraps the same coordinate system than the given CRS, with different components.
         */
        private Compound(final Compound crs, final CoordinateReferenceSystem[] components) {
            super(crs);
            this.components = Collections.unmodifiableList(Arrays.asList(components));
        }

        /**
         * Returns the coordinate system, which is {@code this}.
         */
        @Override
        public org.opengis.referencing.cs.CoordinateSystem getCoordinateSystem() {
            return this;
        }

        /**
         * Returns the components of this compound CRS.
         */
        @Override
        public List<CoordinateReferenceSystem> getComponents() {
            return components;
        }
    }




    /**
     * The CRS, CS and datum for temporal coordinates.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    private static final class Temporal extends NetcdfCRS implements TemporalCRS, TimeCS, TemporalDatum {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -6187564212157767070L;

        /**
         * The date and time origin of this temporal datum.
         */
        private final long origin;

        /**
         * Wraps the given coordinate system.
         */
        Temporal(final CoordinateSystem cs, final CoordinateAxis netcdfAxis) {
            super(cs, Collections.singletonList(netcdfAxis));
            final String unitSymbol = netcdfAxis.getUnitsString();
            final DateUnit unit;
            try {
                unit = new DateUnit(unitSymbol);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unknown unit symbol: " + unitSymbol, e);
            }
            origin = unit.getDateOrigin().getTime();
            getAxis(0).unit = Units.SECOND.multiply(unit.getTimeUnit().getValueInSeconds());
        }

        /**
         * If the given axis is not an instance of {@link CoordinateAxis1DTime}, tries to build
         * a {@code CoordinateAxis1DTime} now. Otherwise returns the axis unchanged. This method
         * can be invoked before to pass the axis to the constructor, if desired.
         *
         * @param  axis    the axis to check.
         * @param  file    the originating dataset, or {@code null} if none.
         * @param  logger  an optional object where to log warnings, or {@code null} if none.
         * @return the axis as an (@link CoordinateAxis1DTime} if possible.
         * @throws IOException if an I/O operation was needed and failed.
         */
        static CoordinateAxis complete(CoordinateAxis axis, final NetcdfDataset file,
                final Logger logger) throws IOException
        {
            if (!(axis instanceof CoordinateAxis1DTime) && file != null) {
                final Formatter formatter = (logger != null) ? new Formatter() : null;
                axis = CoordinateAxis1DTime.factory(file, axis, formatter);
                if (formatter != null) {
                    final StringBuilder buffer = (StringBuilder) formatter.out();
                    if (buffer.length() != 0) {
                        logger.logp(Level.WARNING, NetcdfCRS.class.getName(), "wrap", buffer.toString());
                    }
                }
            }
            return axis;
        }

        /**
         * Returns the coordinate system, which is {@code this}.
         */
        @Override
        public TimeCS getCoordinateSystem() {
            return this;
        }

        /**
         * Returns the datum.
         */
        @Override
        public TemporalDatum getDatum() {
            return this;
        }

        /**
         * Returns the date and time origin of this temporal datum.
         * The units can be obtained by {@code getAxis(0).getUnit()}.
         */
        @Override
        public Date getOrigin() {
            return new Date(origin);
        }
    }




    /**
     * The CRS, CS and datum for vertical coordinates.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    private static final class Vertical extends NetcdfCRS implements VerticalCRS, VerticalCS, VerticalDatum {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = 6758042090424299563L;

        /**
         * The type of this vertical datum.
         */
        private final VerticalDatumType type;

        /**
         * Wraps the given coordinate system.
         */
        Vertical(final CoordinateSystem cs, final CoordinateAxis netcdfAxis) {
            super(cs, Collections.singletonList(netcdfAxis));
            switch (netcdfAxis.getAxisType()) {
                case Pressure: type = VerticalDatumType.BAROMETRIC;    break;
                case Height:   type = VerticalDatumType.GEOIDAL;       break;
                case GeoZ:     type = VerticalDatumType.valueOf("ELLIPSOIDAL"); break;
                default:       type = VerticalDatumType.OTHER_SURFACE; break;
            }
        }

        /**
         * Returns the coordinate system, which is {@code this}.
         */
        @Override
        public VerticalCS getCoordinateSystem() {
            return this;
        }

        /**
         * Returns the datum.
         */
        @Override
        public VerticalDatum getDatum() {
            return this;
        }

        /**
         * Returns the type of this vertical datum.
         */
        @Override
        public VerticalDatumType getVerticalDatumType() {
            return type;
        }
    }




    /**
     * The CRS for geographic coordinates. This is normally a two-dimensional CRS (current
     * {@link NetcdfCRS} implementation has no support for 3D geographic CRS). However a
     * different dimension (either 1 or more than 2) may happen for unusual netCDF files.
     * <p>
     * This class assumes that the geodetic datum is {@linkplain DefaultGeodeticDatum#WGS84 WGS84}.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    private static final class Geographic extends NetcdfCRS implements GeographicCRS, EllipsoidalCS {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -907156823074314297L;

        /**
         * Wraps the given coordinate system. The given list of axes should in theory contains
         * exactly 2 elements (current {@link NetcdfCRS} implementation has no support for 3D
         * geographic CRS). However a different number of axes may be provided if the
         * {@link NetcdfCRS#wrap(CoordinateSystem)} method has been unable to split the
         * netCDF coordinate system into geodetic, vertical and temporal components.
         */
        Geographic(final CoordinateSystem cs, final List<CoordinateAxis> netcdfAxis) {
            super(cs, netcdfAxis);
        }

        /**
         * Returns the coordinate system, which is {@code this}.
         */
        @Override
        public EllipsoidalCS getCoordinateSystem() {
            return this;
        }

        /**
         * Returns the datum, which is assumed a sphere.
         */
        @Override
        public GeodeticDatum getDatum() {
            return NetcdfEllipsoid.SPHERE;
        }
    }




    /**
     * The CRS for projected coordinates. This is normally a two-dimensional CRS. However
     * a different dimension (either 1 or more than 2) may happen for unusual netCDF files.
     *
     * <p>This class assumes that the geodetic datum is {@linkplain DefaultGeodeticDatum#WGS84 WGS84}.</p>
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    private static final class Projected extends NetcdfCRS implements ProjectedCRS, CartesianCS {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = 8353773858669958163L;

        /**
         * The netCDF projection, or {@code null} if none.
         * Will be created when first needed.
         */
        private transient Projection projection;

        /**
         * Wraps the given coordinate system. The given list of axes should in theory contains
         * exactly 2 elements. However a different number of axes may be provided if the
         * {@link NetcdfCRS#wrap(CoordinateSystem)} method has been unable to split the netCDF
         * coordinate system into geodetic, vertical and temporal components.
         */
        Projected(final CoordinateSystem cs, final List<CoordinateAxis> netcdfAxis) {
            super(cs, netcdfAxis);
        }

        /**
         * Returns the coordinate system, which is {@code this}.
         */
        @Override
        public CartesianCS getCoordinateSystem() {
            return this;
        }

        /**
         * Returns the datum, which is assumed spherical. This datum must be
         * the same than the datum of the CRS returned by {@link #getBaseCRS()}.
         */
        @Override
        public GeodeticDatum getDatum() {
            return NetcdfEllipsoid.SPHERE;
        }

        /**
         * Returns the base CRS, which is assumed spherical. We presume a sphere rather
         * than WGS84 because the netCDF projection framework uses spherical formulas.
         */
        @Override
        public GeographicCRS getBaseCRS() {
            return NetcdfEllipsoid.SPHERE;
        }

        /**
         * Returns a wrapper around the netCDF projection.
         *
         * @throws IllegalStateException if the netCDF coordinate system does not define a projection.
         */
        @Override
        public synchronized Projection getConversionFromBase() {
            if (projection == null) {
                final ucar.unidata.geoloc.Projection p = delegate().getProjection();
                if (p == null) {
                    throw new IllegalStateException("Projection is unspecified.");
                }
                projection = new NetcdfProjection(p, getBaseCRS(), this);
            }
            return projection;
        }

        /**
        * Returns the projection domain of validity.
        */
        @Override
        public Extent getDomainOfValidity() {
            return getConversionFromBase().getDomainOfValidity();
        }
    }
}
