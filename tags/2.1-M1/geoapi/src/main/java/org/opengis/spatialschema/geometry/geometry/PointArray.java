/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A sequence of points. The {@code PointArray} interface outlines a means of efficiently
 * storing large numbers of usually homogeneous {@linkplain Position positions}; i.e. all having
 * the same {@linkplain CoordinateReferenceSystem coordinate reference system}. While a point array
 * conceptually contains {@linkplain Position positions}, it provides convenience methods for
 * fetching directly the {@linkplain DirectPosition direct positions} instead.
 * <p>
 * A simple implementation of {@code PointArray} will generally be no more efficient than
 * a simple array of {@link Position}s. More efficient implementations may store coordinates
 * in a more compact form (e.g. in a single {@code float[]} array) and creates {@link Position}
 * objects on the fly when needed.
 * <p>
 * If a particular {@code PointArray} implementation supports efficiently random access through
 * any {@code get} or {@code set} method, it shall announce this capability by implementing the
 * {@link java.util.RandomAccess} interface. Otherwise, users should read the positions through
 * the <code>{@linkplain #positions()}.iterator()</code> instead.
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see Position
 * @see PointGrid
 */
@UML(identifier="GM_PointArray", specification=ISO_19107)
public interface PointArray {
    /**
     * Returns the length (the number of elements) of this array. This is equivalent to
     * <code>{@linkplain #positions positions}().{@linkplain List#size size}()</code>.
     *
     * @return The array length.
     *
     * @see List#size
     * @see PointGrid#width
     */
    @Extension
    int length();

    /**
     * Returns the dimensionality of the coordinates in this array. It should be equals to the
     * dimensionality of the {@linkplain #getCoordinateReferenceSystem() coordinate reference system}
     * for these coordinates.
     *
     * @return the dimensionality of this array.
     *
     * @see DirectPosition#getDimension
     */
    @Extension
    int getDimension();

    /**
     * Returns the Coordinate Reference System in which the coordinates are given.
     * May be {@code null} if this particular {@code PointArray} is included in a
     * larger object with such a reference to a coordinate reference system}. In
     * this case, the cordinate reference system is implicitly assumed to take on
     * the value of the containing object's coordinate reference system.
     *
     * @return The coordinate reference system, or {@code null}.
     *
     * @see DirectPosition#getCoordinateReferenceSystem
     */
    @Extension
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Gets a copy of the {@linkplain DirectPosition direct position} at the particular location
     * in this {@code PointArray}. If the {@code dest} argument is non-null, that object will be
     * populated with the value from the array. In all cases, the position in insulated from
     * changes in the {@code PointArray}, and vice-versa. Consequently, the same
     * {@code DirectPosition} object can be reused for fetching many points from this array.
     * Example:
     * <blockquote><pre>
     * &nbsp;DirectPosition position = null;
     * &nbsp;for (int i=0; i&lt;array.length(); i++) {
     * &nbsp;    position = array.get(i, position);
     * &nbsp;    // Do some processing...
     * &nbsp;}
     * </pre></blockquote>
     *
     * @param  index The location in the array, from 0 inclusive to the array
     *               {@linkplain #length length} exclusive.
     * @param  dest An optionnaly pre-allocated direct position.
     * @return The {@code dest} argument, or a new object if {@code dest} was null.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    @Extension
    DirectPosition get(int index, DirectPosition dest) throws IndexOutOfBoundsException;

    /**
     * Sets the point at the given index. The point coordinates will be copied, i.e. changes
     * to the given {@code position} after this method call will not be reflected into
     * this point array. Consequently, the same {@code DirectPosition} object can be
     * reused for setting many points in this array.
     *
     * @param  index The location in the array, from 0 inclusive to the array
     *         {@linkplain #length length} exclusive.
     * @param  position The point to set at the given location in this array.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     * @throws UnsupportedOperationException if this array is immutable.
     *
     * @see List#set
     */
    @Extension
    void set(int index, DirectPosition position) throws IndexOutOfBoundsException,
                                                        UnsupportedOperationException;

    /**
     * Returns a view of the points in this array as a list of {@linkplain Position positions}.
     * The list is backed by this {@code PointArray}, so changes to the point array are
     * reflected in the list, and vice-versa.
     * <p>
     * Note that random access may be costly in some implementations. If the returned list
     * doesn't implement the {@link java.util.RandomAccess} interface, then consider avoiding
     * the {@link List#get(int)} method. Favor the {@linkplain List#iterator list iterator}
     * instead.
     *
     * @return The list of positions in this array.
     */
    @UML(identifier="column", obligation=MANDATORY, specification=ISO_19107)
    List<Position> positions();
}
