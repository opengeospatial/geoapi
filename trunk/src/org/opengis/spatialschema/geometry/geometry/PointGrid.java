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


/**
 * A grid of points. The grid may be see as a sequences of equal length sequences.
 *  
 * @UML datatype GM_PointGrid
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PointGrid {
    /**
     * Returns the width of this grid. All {@linkplain PointArray point array}
     * in this grid must have this length.
     *
     * @return The grid width.
     * @see PointArray#size
     */
    public int width();

    /**
     * Returns the length of this array. This is equivalent to
     * <code>getRows().size()</code>.
     *
     * @return The grid height.
     */
    public int height();

    /**
     * Returns the point at the given index. This is equivalent to
     * <code>getRow(row).get(column)</code>.
     *
     * @param  row The row index from 0 inclusive to {@link #height} exclusive.
     * @param  column The column index from 0 inclusive to {@link #width} exclusive.
     * @return The point at the given index.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     */
    public Position get(int row, int column) throws IndexOutOfBoundsException;

    /**
     * Returns the row at the given index.
     *
     * @param  row The index from 0 inclusive to {@link #height} exclusive.
     * @return The row at the given index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public PointArray getRow(int row) throws IndexOutOfBoundsException;

    /**
     * Returns all rows in this array.
     *
     * @return The rows in this array.
     * @UML mandatory row
     *
     * @revisit Should changes in this list be forwarded to the underlying <code>PointGrid</code>?
     *          In the mean time, it is probably safe for implementor to make this list immutable.
     */
    public List/*<PointArray>*/ getRows();
}
