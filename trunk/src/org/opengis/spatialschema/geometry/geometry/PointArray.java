/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * A sequence of points. The <code>PointArray</code> interface outlines a means
 * of efficiently storing large numbers of homogeneous {@link DirectPosition}s;
 * i.e. all having the same {@linkplain CoordinateReferenceSystem coordinate reference
 * system}. Classes implementing the <code>PointArray</code> interface are not required
 * to store only one type of <code>DirectPosition</code> (the benefit of a homogenous
 * collection arises in sub-interfaces). A simple implementation of <code>PointArray</code>
 * will generally be no more efficient than a simple array of <code>DirectPosition</code>s.
 * <br><br>
 *
 * <code>PointArray</code> is similar to <code>{@link List}&lt;{@link DirectPosition}&gt;</code>
 * from the <A HREF="http://java.sun.com/j2se/1.5.0/docs/guide/collections/index.html">collection
 * framework</A>. Implementations are free to implement directly the {@link List} interface.
 *  
 * @UML datatype GM_PointArray
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PointArray {
    /**
     * Returns the size (the number of elements) of this array.
     * This is equivalent to <code>getColumns().size()</code>.
     *
     * @return The array size.
     *
     * @see List#size
     * @see PointGrid#width
     */
    public int size();

    /**
     * Returns the dimensionality of the coordinates in this array.
     * This may be less than or equal to the dimensionality of the 
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}
     * for these coordinates.
     *
     * @return the dimensionality of this array.
     *
     * @see DirectPosition#getDimension
     */
    public int getDimension();

    /**
     * Returns the Coordinate Reference System of this array.
     *
     * @return the Coordinate Reference System for this array.
     *
     * @see DirectPosition#getCoordinateReferenceSystem
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Returns the point at the given index. This is equivalent to
     * <code>getColumns().get(column).getDirect()</code>.
     *
     * @param  column The location in the array, from 0 inclusive
     *                to the array's {@linkplain #size} exclusive.
     * @return The point at the given location in this array.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     *
     * @see List#get
     * @see #get(int, DirectPosition)
     *
     * @revisit Should we specify that changes to the returned point will not be reflected
     *          to this array, or should we left the decision to the implementor?
     */
    public DirectPosition get(int column) throws IndexOutOfBoundsException;

    /**
     * Gets the <code>DirectPosition</code> at the particular location in this 
     * <code>PointArray</code>. If the <code>dest</code> argument is non-null,
     * that object will be populated with the value from the list.
     *
     * @param  column The location in the array, from 0 inclusive
     *                to the array's {@linkplain #size} exclusive.
     * @param  dest An optionnaly pre-allocated direct position.
     * @return The <code>dest</code> argument, or a new object if <code>dest</code> was null.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     *
     * @see #get(int)
     */
    public DirectPosition get(int column, DirectPosition dest) throws IndexOutOfBoundsException;

    /**
     * Set the point at the given index.
     *
     * @param  column The location in the array, from 0 inclusive
     *         to the array's {@linkplain #size} exclusive.
     * @param  position The point to set at the given location in this array.
     *         The point coordinates will be copied, i.e. changes to the given
     *         <code>position</code> after the method call will not be reflected
     *         to this array.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     *
     * @see List#set
     */
    public void set(int column, DirectPosition position) throws IndexOutOfBoundsException;
    
    /**
     * Returns the elements of this <code>PointArray</code> as an array of
     * <code>DirectPosition</code>s.
     *
     * @return The elements as an array of direct positions.
     *
     * @see List#toArray
     *
     * @revisit Should we specify that changes to the returned points will not be reflected
     *          into this array, or should we left the decision to the implementor?
     */
    public DirectPosition[] toArray();

    /**
     * Returns a view of the points in this array as a list of {@linkplain Position positions}.
     * The list is backed by this <code>PointArray</code>, so changes to the array are reflected
     * in the list, and vice-versa.
     *
     * @return The points in this array.
     * @UML mandatory column
     */
    public List/*<Position>*/ positions();
}
