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

import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;
import java.awt.geom.Rectangle2D;

import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.extent.GeographicBoundingBox;


/**
 * An {@link Extent} containing only a single {@link GeographicBoundingBox}.
 * In order to keep the model simpler, this simple geographic bounding box
 * is also an extent with no vertical or temporal elements.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleGeographicBoundingBox implements GeographicBoundingBox, Extent, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -1914566301431044983L;

    /**
     * An extent and a bounding box ranging from 180°W to 180°E and 90°S to 90°N.
     */
    public static final SimpleGeographicBoundingBox WORLD = new SimpleGeographicBoundingBox(-180, 180, -90, 90);

    /**
     * The western-most coordinate of the limit of the dataset extent.
     * The value is expressed in longitude in decimal degrees (positive east).
     */
    protected final double westBoundLongitude;

    /**
     * The eastern-most coordinate of the limit of the dataset extent.
     * The value is expressed in longitude in decimal degrees (positive east).
     */
    protected final double eastBoundLongitude;

    /**
     * The southern-most coordinate of the limit of the dataset extent.
     * The value is expressed in latitude in decimal degrees (positive north).
     */
    protected final double southBoundLatitude;

    /**
     * The northern-most, coordinate of the limit of the dataset extent.
     * The value is expressed in latitude in decimal degrees (positive north).
     */
    protected final double northBoundLatitude;

    /**
     * Constructs a geographic bounding box initialized to the same values than the specified one.
     *
     * @param box The existing box to use for initializing this geographic bounding box.
     */
    public SimpleGeographicBoundingBox(final GeographicBoundingBox box) {
        this(box.getWestBoundLongitude(), box.getEastBoundLongitude(),
             box.getSouthBoundLatitude(), box.getNorthBoundLatitude());
    }

    /**
     * Constructs a geographic bounding box from the specified rectangle. The rectangle is assumed
     * in the default netCDF geographic CRS (implementation dependent).
     *
     * @param bounds  the rectangle to use for initializing this geographic bounding box.
     */
    public SimpleGeographicBoundingBox(final Rectangle2D bounds) {
        this(bounds.getMinX(), bounds.getMaxX(),
             bounds.getMinY(), bounds.getMaxY());
    }

    /**
     * Creates a geographic bounding box initialized to the specified values.
     *
     * <p><b>Caution:</b> Arguments are expected in the same order than they appear in the
     * ISO 19115 specification. This is different than the order commonly found in Java world,
     * which is rather (<var>x</var><sub>min</sub>, <var>y</var><sub>min</sub>,
     * <var>x</var><sub>max</sub>, <var>y</var><sub>max</sub>).</p>
     *
     * @param westBoundLongitude The minimal <var>x</var> value.
     * @param eastBoundLongitude The maximal <var>x</var> value.
     * @param southBoundLatitude The minimal <var>y</var> value.
     * @param northBoundLatitude The maximal <var>y</var> value.
     *
     * @throws IllegalArgumentException if (<var>west bound</var> &gt; <var>east bound</var>)
     *         or (<var>south bound</var> &gt; <var>north bound</var>). Note that
     *         {@linkplain Double#NaN NaN} values are allowed.
     */
    public SimpleGeographicBoundingBox(final double westBoundLongitude,
                                       final double eastBoundLongitude,
                                       final double southBoundLatitude,
                                       final double northBoundLatitude)
            throws IllegalArgumentException
    {
        final String dim;
        final double min, max;
        if (westBoundLongitude > eastBoundLongitude) {
            min = westBoundLongitude;
            max = eastBoundLongitude;
            dim = "longitude";
            // Exception will be thrown below.
        } else if (southBoundLatitude > northBoundLatitude) {
            min = southBoundLatitude;
            max = northBoundLatitude;
            dim = "latitude";
            // Exception will be thrown below.
        } else {
            this.westBoundLongitude = westBoundLongitude;
            this.eastBoundLongitude = eastBoundLongitude;
            this.southBoundLatitude = southBoundLatitude;
            this.northBoundLatitude = northBoundLatitude;
            return;
        }
        throw new IllegalArgumentException("Illegal " + dim + " range: [" + min + " … " + max + "].");
    }

    /**
     * Returns the western-most coordinate of the limit of the
     * dataset extent. The value is expressed in longitude in
     * decimal degrees (positive east).
     *
     * @return the western-most longitude between -180 and +180°.
     */
    @Override
    public double getWestBoundLongitude() {
        return westBoundLongitude;
    }

    /**
     * Returns the eastern-most coordinate of the limit of the
     * dataset extent. The value is expressed in longitude in
     * decimal degrees (positive east).
     *
     * @return the eastern-most longitude between -180 and +180°.
     */
    @Override
    public double getEastBoundLongitude() {
        return eastBoundLongitude;
    }

    /**
     * Returns the southern-most coordinate of the limit of the
     * dataset extent. The value is expressed in latitude in
     * decimal degrees (positive north).
     *
     * @return the southern-most latitude between -90 and +90°.
     */
    @Override
    public double getSouthBoundLatitude() {
        return southBoundLatitude;
    }

    /**
     * Returns the northern-most, coordinate of the limit of the
     * dataset extent. The value is expressed in latitude in
     * decimal degrees (positive north).
     *
     * @return the northern-most latitude between -90 and +90°.
     */
    @Override
    public double getNorthBoundLatitude() {
        return northBoundLatitude;
    }

    /**
     * Indication of whether the bounding box encompasses an area covered by the data
     * (<cite>inclusion</cite>) or an area where data is not present (<cite>exclusion</cite>).
     * The default implementation unconditionally returns {@link Boolean#TRUE}.
     *
     * @return {@code true} for inclusion, or {@code false} for exclusion.
     */
    @Override
    public Boolean getInclusion() {
        return Boolean.TRUE;
    }

    /**
     * Provides geographic component of the extent of the referring object.
     * The default implementation returns a singleton containing only this
     * geographic bounding box.
     *
     * @return the geographic extent, or an empty set if none.
     */
    @Override
    public Collection<? extends GeographicExtent> getGeographicElements() {
        return Collections.singleton(this);
    }

    /**
     * Returns {@code true} if the given floating point values are equal.
     */
    private static boolean equals(final double a, final double b) {
        return Double.doubleToLongBits(a) == Double.doubleToLongBits(b);
    }

    /**
     * Compares this geographic bounding box with the specified object for equality.
     *
     * @param  object  the object to compare for equality.
     * @return {@code true} if the given object is equal to this box.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof SimpleGeographicBoundingBox) {
            final SimpleGeographicBoundingBox that = (SimpleGeographicBoundingBox) object;
            return equals(this.southBoundLatitude, that.southBoundLatitude) &&
                   equals(this.northBoundLatitude, that.northBoundLatitude) &&
                   equals(this.eastBoundLongitude, that.eastBoundLongitude) &&
                   equals(this.westBoundLongitude, that.westBoundLongitude);
        }
        return false;
    }

    /**
     * Returns a hash code value for this bounding box.
     */
    @Override
    public int hashCode() {
        long code = serialVersionUID ^
               (Double.doubleToLongBits(westBoundLongitude) + 31*
               (Double.doubleToLongBits(eastBoundLongitude) + 31*
               (Double.doubleToLongBits(southBoundLatitude) + 31*
                Double.doubleToLongBits(northBoundLatitude))));
        return ((int) code) ^ ((int) (code >>> 32));
    }

    /**
     * Returns a string representation of this extent.
     *
     * @return a string representation of the given box in the given locale.
     */
    @Override
    public String toString() {
        return "GeographicBoundingBox[" +
                "west="  + westBoundLongitude + ", " +
                "east="  + eastBoundLongitude + ", " +
                "south=" + southBoundLatitude + ", " +
                "north=" + northBoundLatitude + ']';
    }
}
