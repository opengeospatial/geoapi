/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.time.Instant;

import org.opengis.metadata.citation.Citation;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.CoordinateDataType;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.RealizationMethod;


/**
 * A {@link CoordinateReferenceSystem} abstract base class. This class does not distinguish between
 * <i>Coordinate System</i> and <i>Coordinate Reference System</i>, therefor it implements the two interfaces.
 *
 * <h2>Shortcoming</h2>
 * In order to keep the example simple,
 * this <abbr>CRS</abbr> shares its name with the coordinate system (<abbr>CS</abbr>) and sometime the datum.
 * This is not a recommended practice because many coordinate reference systems can have the same coordinate
 * system and datum. However, this particular package takes this approach because the GeoAPI example module
 * is only a demonstration of how GeoAPI can be implemented in a few simple cases.
 * Real applications should store the datum and coordinate system in separated objects.
 */
public abstract class SimpleCRS extends SimpleIdentifiedObject implements SingleCRS, CoordinateSystem {
    /**
     * The coordinate system axes. The length of this array is the coordinate system dimension.
     *
     * @see #getDimension()
     * @see #getAxis(int)
     */
    protected final CoordinateSystemAxis[] axes;

    /**
     * Creates a new CRS for the given name and axes.
     *
     * @param authority  organization responsible for definition of the CRS, or {@code null}.
     * @param name       the name of the new CRS.
     * @param axes       the axes to be returned by {@link #getAxis(int)}.
     *                   The length of this array is the coordinate system dimension.
     */
    protected SimpleCRS(final Citation authority, final String name, final CoordinateSystemAxis... axes) {
        super(authority, name);
        this.axes = axes.clone();
    }

    /**
     * Returns the coordinate system, which is represented directly by {@code this} implementation
     * class since it does not distinguish CS and CRS.
     *
     * @return the coordinate system.
     */
    @Override
    public CoordinateSystem getCoordinateSystem() {
        return this;
    }

    /**
     * Returns the dimension of the coordinate system.
     *
     * @return the dimension of the coordinate system.
     */
    @Override
    public final int getDimension() {
        return axes.length;
    }

    /**
     * Returns the coordinate axis at the given dimension.
     *
     * @param  dimension  the zero based index of axis.
     * @return the axis at the specified dimension.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     */
    @Override
    public final CoordinateSystemAxis getAxis(final int dimension) throws IndexOutOfBoundsException {
        return axes[dimension];
    }

    /**
     * Compares this CRS with the given object for equality.
     *
     * @param  object  the object to compare with this {@code SimpleCRS}.
     * @return {@code true} if the given object is equal to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            return Arrays.equals(axes, ((SimpleCRS) object).axes);
        }
        return false;
    }

    /**
     * A {@link GeographicCRS} specialization of {@link SimpleCRS}.
     *
     * <h2>Shortcoming</h2>
     * In order to keep the example simple, this <abbr>CRS</abbr> shares its name with the coordinate system.
     * This is not a recommended practice because many <abbr>CRS</abbr> can have the same coordinate system.
     * However, this particular package takes this approach because the GeoAPI example module is only
     * a demonstration of how GeoAPI can be implemented in a few simple cases.
     * Real applications should store the coordinate system in separated objects.
     */
    public static class Geographic extends SimpleCRS implements GeographicCRS, EllipsoidalCS {
        /**
         * The WGS84 CRS, based on EPSG:4326. The axis order is (φ,λ).
         */
        public static final GeographicCRS WGS84 = new Geographic(SimpleCitation.EPSG, "WGS 84",
                SimpleDatum.WGS84, SimpleAxis.LATITUDE, SimpleAxis.LONGITUDE);

        /**
         * A spherical CRS used when the datum is unknown, based on EPSG:4047.
         * The axis order is (φ,λ).
         */
        public static final GeographicCRS SPHERE = new Geographic(SimpleCitation.EPSG, "GRS 1980 Authalic Sphere",
                SimpleDatum.SPHERE, SimpleAxis.LATITUDE, SimpleAxis.LONGITUDE);

        /**
         * The datum.
         *
         * @see #getDatum()
         */
        private final GeodeticDatum datum;

        /**
         * Creates a new CRS for the given name, datum and axes.
         *
         * @param  authority  organization responsible for definition of the name, or {@code null}.
         * @param  name       the name of the geodetic <abbr>CRS</abbr> and the coordinate system.
         * @param  datum      the <abbr>CRS</abbr> geodetic reference frame.
         * @param  axes       the <abbr>CRS</abbr> axes. The length of this array is the coordinate system dimension, 2 or 3.
         */
        public Geographic(final Citation authority, final String name,
                final GeodeticDatum datum, final CoordinateSystemAxis... axes)
        {
            super(authority, name, axes);
            this.datum = Objects.requireNonNull(datum);
        }

        /**
         * Returns the datum.
         *
         * @return the datum.
         */
        @Override
        public GeodeticDatum getDatum() {
            return datum;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EllipsoidalCS getCoordinateSystem() {
            return this;
        }

        /**
         * Compares this CRS with the given object for equality.
         *
         * @param  object  the object to compare with this {@code SimpleCRS.Geographic}.
         * @return {@code true} if the given object is equal to this object.
         */
        @Override
        public boolean equals(final Object object) {
            if (super.equals(object)) {
                return datum.equals(((Geographic) object).datum);
            }
            return false;
        }
    }

    /**
     * A {@link VerticalCRS} specialization of {@link SimpleCRS} with its own datum.
     *
     * <h2>Shortcoming</h2>
     * In order to keep the example simple,
     * this vertical <abbr>CRS</abbr> shares its name with the reference frame and coordinate system (<abbr>CS</abbr>).
     * This is not a recommended practice because many coordinate reference systems can have the same reference frame.
     * However, this particular package takes this approach because the GeoAPI example module is only a demonstration
     * of how GeoAPI can be implemented in a few simple cases.
     * Real applications should store the reference frame and coordinate system in separated objects.
     */
    public static class Vertical extends SimpleCRS implements VerticalCRS, VerticalCS, VerticalDatum {
        /**
         * A vertical CRS for Mean Sea Level, based on EPSG:5714.
         */
        public static final VerticalCRS MSL = new Vertical(
                SimpleCitation.EPSG, "Mean Sea Level (MSL) height", RealizationMethod.TIDAL, SimpleAxis.HEIGHT);

        /**
         * The type of this vertical datum.
         */
        private final RealizationMethod method;

        /**
         * Creates a new CRS for the given name and axis.
         *
         * @param authority  organization responsible for definition of the name, or {@code null}.
         * @param name       the name for the <abbr>CRS</abbr>, the vertical reference frame and the coordinate system.
         * @param method     the realization method of the vertical reference frame, or {@code null} if none.
         * @param axis       the single <abbr>CRS</abbr> axis.
         */
        public Vertical(final Citation authority, final String name,
                final RealizationMethod method, final CoordinateSystemAxis axis)
        {
            super(authority, name, axis);
            this.method = method;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public VerticalCS getCoordinateSystem() {
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public VerticalDatum getDatum() {
            return this;
        }

        /**
         * Returns the method through which this vertical reference frame is realized.
         *
         * @return method through which this vertical reference frame is realized.
         */
        @Override
        public Optional<RealizationMethod> getRealizationMethod() {
            return Optional.ofNullable(method);
        }
    }

    /**
     * A {@link TemporalCRS} specialization of {@link SimpleCRS} with its own datum.
     *
     * <h2>Shortcoming</h2>
     * In order to keep the example simple,
     * this temporal <abbr>CRS</abbr> shares its name with the datum and coordinate system (<abbr>CS</abbr>).
     * This is not a recommended practice because many coordinate reference systems can have the same datum.
     * However, this particular package takes this approach because the GeoAPI example module is only a
     * demonstration of how GeoAPI can be implemented in a few simple cases.
     * Real applications should store the datum and coordinate system in separated objects.
     */
    public static class Temporal extends SimpleCRS implements TemporalCRS, TimeCS, TemporalDatum {
        /**
         * A temporal CRS for Julian date, based on OGC:JulianDate.
         */
        public static final TemporalCRS JULIAN = new Temporal(
                SimpleCitation.OGC, "Julian Date", Instant.ofEpochMilli(-210866760000000L), SimpleAxis.HEIGHT);

        /**
         * The date and time origin of this temporal datum.
         */
        private final Instant origin;

        /**
         * Creates a new CRS for the given name, datum and axis.
         *
         * @param authority  organization responsible for definition of the name, or {@code null}.
         * @param name       the name for the <abbr>CRS</abbr>, the temporal datum and the coordinate system.
         * @param origin     the date and time origin of the temporal datum.
         * @param axis       the single <abbr>CRS</abbr> axis.
         */
        public Temporal(final Citation authority, final String name, final Instant origin,
                final CoordinateSystemAxis axis)
        {
            super(authority, name, axis);
            this.origin = origin;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public TimeCS getCoordinateSystem() {
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public TemporalDatum getDatum() {
            return this;
        }

        /**
         * Returns the date and time origin of this temporal datum.
         * The units can be obtained by {@code getAxis(0).getUnit()}.
         *
         * @return the date and time origin of this temporal datum.
         */
        @Override
        public Date getOrigin() {
            return Date.from(origin);
        }

        /**
         * Returns the type (measure, integer or data-time) of coordinate values.
         * This simple implementation supports only {@link CoordinateDataType#MEASURE}.
         */
        @Override
        public CoordinateDataType getCoordinateType() {
            return CoordinateDataType.MEASURE;
        }
    }
}
