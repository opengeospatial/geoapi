/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Arrays;

import org.opengis.metadata.citation.Citation;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.GeodeticDatum;


/**
 * Base class of all CRS defined in this simple package. This class does not make distinction
 * between <cite>Coordinate System</cite> and <cite>Coordinate Reference System</cite>, so we
 * implement the two interfaces by the same class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleCRS extends SimpleIdentifiedObject implements SingleCRS, CoordinateSystem {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 4203423453709225243L;

    /**
     * The datum.
     *
     * @see #getDatum()
     */
    protected final Datum datum;

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
     * @param authority Organization responsible for definition of the CRS, or {@code null}.
     * @param name      The name of the new CRS.
     * @param datum     The value to be returned by {@link #getDatum()}.
     * @param axes      The axes to be returned by {@link #getAxis(int)}. The length of this array
     *                  is the coordinate system dimension.
     */
    public SimpleCRS(final Citation authority, final String name,
            final Datum datum, final CoordinateSystemAxis... axes)
    {
        super(authority, name);
        Objects.requireNonNull(datum);
        this.datum = datum;
        this.axes = axes.clone();
    }

    /**
     * Returns the datum.
     *
     * @return The datum.
     */
    @Override
    public Datum getDatum() {
        return datum;
    }

    /**
     * Returns the coordinate system, which is represented directly by {@code this} implementation
     * class since it does not distinguish CS and CRS.
     *
     * @return The coordinate system.
     */
    @Override
    public CoordinateSystem getCoordinateSystem() {
        return this;
    }

    /**
     * Returns the dimension of the coordinate system.
     *
     * @return The dimension of the coordinate system.
     */
    @Override
    public final int getDimension() {
        return axes.length;
    }

    /**
     * Returns the coordinate axis at the given dimension.
     *
     * @param  dimension The zero based index of axis.
     * @return The axis at the specified dimension.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     */
    @Override
    public final CoordinateSystemAxis getAxis(final int dimension) throws IndexOutOfBoundsException {
        return axes[dimension];
    }

    /**
     * Compares this CRS with the given object for equality.
     *
     * @param  object The object to compare with this {@code SimpleCRS}.
     * @return {@code true} if the given object is equals to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final SimpleCRS other = (SimpleCRS) object;
            return datum.equals(other.datum) && Arrays.equals(axes, other.axes);
        }
        return false;
    }

    /**
     * The {@link GeographicCRS} specialization of {@link SimpleCRS}.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class Geographic extends SimpleCRS implements GeographicCRS, EllipsoidalCS {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -3574562964980541886L;

        /**
         * The WGS84 CRS, as defined by EPSG:4326. The axis order is (&phi;,&lambda;).
         */
        public static final GeographicCRS WGS84 = new Geographic(SimpleCitation.EPSG, "WGS 84",
                SimpleDatum.WGS84, SimpleAxis.LATITUDE, SimpleAxis.LONGITUDE);

        /**
         * Creates a new CRS for the given name, datum and axes.
         *
         * @param authority Organization responsible for definition of the name, or {@code null}.
         * @param name      The name of the new CRS.
         * @param datum     The value to be returned by {@link #getDatum()}.
         * @param axes      The axes to be returned by {@link #getAxis(int)}. The length of this array
         *                  is the coordinate system dimension, which should be restricted to 2 or 3.
         */
        public Geographic(final Citation authority, final String name,
                final GeodeticDatum datum, final CoordinateSystemAxis... axes)
        {
            super(authority, name, datum, axes);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public GeodeticDatum getDatum() {
            return (GeodeticDatum) datum;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EllipsoidalCS getCoordinateSystem() {
            return this;
        }
    }
}
