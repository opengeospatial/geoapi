/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;

// J2SE direct dependencies
import java.util.List;


/**
 * A sequence of points.
 *  
 * @UML datatype GM_PointArray
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PointArray {
    /**
     * Returns the length of this array. This is equivalent to
     * <code>getColumns().size()</code>.
     *
     * @return The array length.
     * @see PointGrid#width
     */
    public int length();

    /**
     * Returns the point at the given index. This is equivalent to
     * <code>getColumns().get(column)</code>.
     *
     * @param  column The index from 0 inclusive to {@link #length} exclusive.
     * @return The point at the given index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Position get(int column) throws IndexOutOfBoundsException;

    /**
     * Returns all points in this array.
     *
     * @return The points in this array.
     * @UML mandatory column
     *
     * @revisit Should changes in this list be forwarded to the underlying <code>PointArray</code>?
     *          In the mean time, it is probably safe for implementor to make this list immutable.
     */
    public List<Position> getColumns();
}
