/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
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
 * a simple array of {@link Position}s. More efficient implementations will generally stores
 * coordinates in a more compact form (e.g. in a single {@code float[]} array) and creates
 * {@link Position} objects on the fly when needed.
 * <p>
 * If a particular {@code PointArray} implementation supports efficiently random access
 * through any {@code get} or {@code set} method, it shall announce this capability
 * by implementing the {@link java.util.RandomAccess} interface. Otherwise, users should read
 * the positions through the <code>{@linkplain #positions()}.iterator()</code> instead.
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
    public int length();

    /**
     * Returns the dimensionality of the coordinates in this array.
     * This may be less than or equal to the dimensionality of the 
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}
     * for these coordinates.
     *
     * @return the dimensionality of this array.
     *
     * @see DirectPosition#getDimension
     *
     * @deprecated This method definition conflict with section 6.2.2.10 in ISO 19107:
     *             "<cite>The operation {@code coordinateDimension} shall return
     *             the dimension of the coordinates that define this {@code GM_Object},
     *             which must be the same as the coordinate dimension of the coordinate
     *             reference system for this GM_Object.</cite>".
     */
    @Extension
    @Deprecated
    public int getDimension();

    /**
     * Returns the Coordinate Reference System of this array.
     *
     * @return the Coordinate Reference System for this array.
     *
     * @see DirectPosition#getCoordinateReferenceSystem
     *
     * @revisit ISO 19107 do not defines such an association. We may keep this method as a
     *          convenience, but it put a constraint on the points that a {@code PointArray}
     *          can contains. Whatever or not this constraints is wanted is an open question.
     */
    @Extension
    public CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Gets a copy of the {@code DirectPosition} at the particular location in this 
     * {@code PointArray}. If the {@code dest} argument is non-null, that object
     * will be populated with the value from the array. In all cases, the position in insulated
     * from changes in the {@code PointArray}, and vice-versa. Consequently, the same
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
     * @param  column The location in the array, from 0 inclusive
     *                to the array's {@linkplain #length} exclusive.
     * @param  dest An optionnaly pre-allocated direct position.
     * @return The {@code dest} argument, or a new object if {@code dest} was null.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     *
     * @see #get(int)
     */
    @Extension
    public DirectPosition get(int column, DirectPosition dest) throws IndexOutOfBoundsException;

    /**
     * Set the point at the given index. The point coordinates will be copied, i.e. changes
     * to the given {@code position} after this method call will not be reflected into
     * this point array. Consequently, the same {@code DirectPosition} object can be
     * reused for setting many points in this array.
     *
     * @param  column The location in the array, from 0 inclusive
     *         to the array's {@linkplain #length} exclusive.
     * @param  position The point to set at the given location in this array.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     * @throws UnsupportedOperationException if this array is immutable.
     *
     * @see List#set
     */
    @Extension
    public void set(int column, DirectPosition position) throws IndexOutOfBoundsException,
                                                                UnsupportedOperationException;

    /**
     * Returns a view of the points in this array as a list of {@linkplain Position positions}.
     * The list is backed by this {@code PointArray}, so changes to the point array are
     * reflected in the list, and vice-versa.
     *
     * @return The list of positions in this array.
     */
    @UML(identifier="column", obligation=MANDATORY, specification=ISO_19107)
    public List<Position> positions();
}
