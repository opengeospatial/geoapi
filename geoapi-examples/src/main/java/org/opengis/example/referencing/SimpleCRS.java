/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Date;
import java.util.Arrays;
import java.util.Objects;

import org.opengis.metadata.citation.Citation;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.VerticalDatumType;


/**
 * A {@link CoordinateReferenceSystem} abstract base class. This class does not make distinction
 * between <cite>Coordinate System</cite> and <cite>Coordinate Reference System</cite>, so we
 * implement the two interfaces by the same class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
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
     * Creates a new CRS for the given name, datum and axes.
     *
     * @param authority  organization responsible for definition of the CRS, or {@code null}.
     * @param name       the name of the new CRS.
     * @param axes       the axes to be returned by {@link #getAxis(int)}.
     *                   The length of this array is the coordinate system dimension.
     */
    public SimpleCRS(final Citation authority, final String name, final CoordinateSystemAxis... axes) {
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
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Geographic extends SimpleCRS implements GeographicCRS, EllipsoidalCS {
        /**
         * The WGS84 CRS, as defined by EPSG:4326. The axis order is (φ,λ).
         */
        public static final GeographicCRS WGS84 = new Geographic(SimpleCitation.EPSG, "WGS 84",
                SimpleDatum.WGS84, SimpleAxis.LATITUDE, SimpleAxis.LONGITUDE);

        /**
         * A spherical CRS used when the datum is unknown, as defined by EPSG:4047.
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
         * @param authority  organization responsible for definition of the name, or {@code null}.
         * @param name       the name of the new CRS.
         * @param datum      the value to be returned by {@link #getDatum()}.
         * @param axes       the axes to be returned by {@link #getAxis(int)}. The length of this array
         *                   is the coordinate system dimension, which should be restricted to 2 or 3.
         */
        public Geographic(final Citation authority, final String name,
                final GeodeticDatum datum, final CoordinateSystemAxis... axes)
        {
            super(authority, name, axes);
            Objects.requireNonNull(datum);
            this.datum = datum;
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
     * <p>In order to keep the model simpler, this vertical CRS is also its own datum. Merging the CRS
     * and datum interfaces is usually not a recommended practice since many vertical CRS can have
     * the same datum. However, this particular class takes this approach because the {@code geoapi-examples}
     * module is only a demonstration of how GeoAPI can be implemented in a few simple cases.
     * More complex applications are encouraged to store the datum in a separated object.</p>
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Vertical extends SimpleCRS implements VerticalCRS, VerticalCS, VerticalDatum {
        /**
         * The type of this vertical datum.
         */
        private final VerticalDatumType type;

        /**
         * Creates a new CRS for the given name, datum and axes.
         *
         * @param authority  organization responsible for definition of the name, or {@code null}.
         * @param name       the name of the new CRS.
         * @param type       the value to be returned by {@link #getVerticalDatumType()}.
         * @param axis       the axis to be returned by {@link #getAxis(int)}.
         */
        public Vertical(final Citation authority, final String name,
                final VerticalDatumType type, final CoordinateSystemAxis axis)
        {
            super(authority, name, axis);
            Objects.requireNonNull(type);
            this.type = type;
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
         * Returns the type of this vertical datum.
         *
         * @return the type of this vertical datum.
         */
        @Override
        public VerticalDatumType getVerticalDatumType() {
            return type;
        }
    }

    /**
     * A {@link TemporalCRS} specialization of {@link SimpleCRS} with its own datum.
     *
     * <p>In order to keep the model simpler, this temporal CRS is also its own datum. Merging the CRS
     * and datum interfaces is usually not a recommended practice since many temporal CRS can have
     * the same datum. However, this particular class takes this approach because the {@code geoapi-examples}
     * module is only a demonstration of how GeoAPI can be implemented in a few simple cases.
     * More complex applications are encouraged to store the datum in a separated object.</p>
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Temporal extends SimpleCRS implements TemporalCRS, TimeCS, TemporalDatum {
        /**
         * The date and time origin of this temporal datum.
         */
        private final long origin;

        /**
         * Creates a new CRS for the given name, datum and axes.
         *
         * @param authority  organization responsible for definition of the name, or {@code null}.
         * @param name       the name of the new CRS.
         * @param origin     the value to be returned by {@link #getOrigin()}.
         * @param axis       the axis to be returned by {@link #getAxis(int)}.
         */
        public Temporal(final Citation authority, final String name, final Date origin,
                final CoordinateSystemAxis axis)
        {
            super(authority, name, axis);
            this.origin = origin.getTime();
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
            return new Date(origin);
        }
    }
}
