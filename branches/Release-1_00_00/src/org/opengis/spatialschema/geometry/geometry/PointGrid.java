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
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * A grid of points. The grid may be see as a sequences of equal length {@linkplain PointArray
 * point arrays}. While a point grid conceptually contains {@linkplain Position positions}, it
 * provides convenience methods for fetching directly the {@linkplain DirectPosition direct positions}
 * instead.
 *  
 * @UML datatype GM_PointGrid
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see Position
 * @see PointArray
 */
public interface PointGrid {
    /**
     * Returns the width of this grid. All {@linkplain PointArray point array}
     * in this grid must have this {@linkplain PointArray#length length}.
     *
     * @return The grid width.
     * @see PointArray#length
     */
    public int width();

    /**
     * Returns the length of this array. This is equivalent to
     * <code>{@linkplain #rows rows}().{@linkplain PointArray#length length}()</code>.
     *
     * @return The grid height.
     */
    public int height();

    /**
     * Returns the point at the given row and column index. This is equivalent to
     * <code>{@linkplain #getRow getRow}(row).{@linkplain PointArray#get(int) get}(column)</code>.
     *
     * @param  row The row index from 0 inclusive to {@link #height} exclusive.
     * @param  column The column index from 0 inclusive to {@link #width} exclusive.
     * @return The point at the given index.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     */
    public DirectPosition get(int row, int column) throws IndexOutOfBoundsException;

    /**
     * Gets a copy of the <code>DirectPosition</code> at the particular location in this 
     * <code>PointGrid</code>. If the <code>dest</code> argument is non-null, that object
     * will be populated with the value from the array. In all cases, the position in insulated
     * from changes in the <code>PointArray</code>, and vice-versa. Consequently, the same
     * <code>DirectPosition</code> object can be reused for fetching many points from this grid.
     * Example:
     * <blockquote><pre>
     * &nbsp;DirectPosition position = null;
     * &nbsp;for (int j=0; j&lt;grid.height(); j++) {
     * &nbsp;    for (int i=0; i&lt;grid.width(); i++) {
     * &nbsp;        position = array.get(j, i, position);
     * &nbsp;        // Do some processing...
     * &nbsp;    }
     * &nbsp;}
     * </pre></blockquote>
     *
     * @param  row The row index from 0 inclusive to {@link #height} exclusive.
     * @param  column The column index from 0 inclusive to {@link #width} exclusive.
     * @param  dest An optionnaly pre-allocated direct position.
     * @return The <code>dest</code> argument, or a new object if <code>dest</code> was null.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     */
    public DirectPosition get(int row, int column, DirectPosition dest) throws IndexOutOfBoundsException;

    /**
     * Set the point at the given index. The point coordinates will be copied, i.e. changes
     * to the given <code>position</code> after this method call will not be reflected into
     * this point array. Consequently, the same <code>DirectPosition</code> object can be
     * reused for setting many points in this array.
     *
     * @param  row The row index from 0 inclusive to {@link #height} exclusive.
     * @param  column The column index from 0 inclusive to {@link #width} exclusive.
     * @param  position The point to set at the given location in this array.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     * @throws UnsupportedOperationException if this grid is immutable.
     */
    public void set(int row, int column, DirectPosition position) throws IndexOutOfBoundsException,
                                                                         UnsupportedOperationException;

    /**
     * Returns the row at the given index.
     * The row is backed by this <code>PointGrid</code>, so changes to the row
     * are reflected in the grid, and vice-versa.
     *
     * @param  row The index from 0 inclusive to {@link #height} exclusive.
     * @return The row at the given index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public PointArray getRow(int row) throws IndexOutOfBoundsException;

    /**
     * Returns a view of all rows in this array.
     * The list is backed by this <code>PointGrid</code>, so changes to any
     * {@linkplain PointArray point array} are reflected in the grid, and vice-versa.
     *
     * @return The rows in this grid.
     * @UML mandatory row
     */
    public List/*<PointArray>*/ rows();
}
