/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.simple;

import java.util.Arrays;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.GeodeticCRS;
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
public class SimpleCRS extends SimpleIdentifiedObject implements GeodeticCRS, CoordinateSystem {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 4203423453709225243L;

    /**
     * The geodetic datum.
     *
     * @see #getDatum()
     */
    protected final GeodeticDatum datum;

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
     * @param authority Organization responsible for definition of the name, or {@code null}.
     * @param name  The name of the new CRS.
     * @param datum The value to be returned by {@link #getDatum()}.
     * @param axes  The axes to be returned by {@link #getAxis(int)}. The length of this array
     *              is the coordinate system dimension.
     */
    public SimpleCRS(final Citation authority, final String name,
            final GeodeticDatum datum, final CoordinateSystemAxis... axes)
    {
        super(authority, name);
//      Objects.requireNonNull(datum); // JDK7
        this.datum = datum;
        this.axes = axes.clone();
    }

    /**
     * Returns the datum.
     *
     * @return The datum.
     */
    @Override
    public GeodeticDatum getDatum() {
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
}
