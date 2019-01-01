/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A grid of points. The grid may be see as a sequences of equal length {@linkplain PointArray
 * point arrays}. While a point grid conceptually contains {@linkplain Position positions}, it
 * provides convenience methods for fetching directly the {@linkplain DirectPosition direct positions}
 * instead.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see Position
 * @see PointArray
 */
@UML(identifier="GM_PointGrid", specification=ISO_19107)
public interface PointGrid {
    /**
     * Returns the width of this grid. All {@linkplain PointArray point array}
     * in this grid must have this {@linkplain PointArray#length length}.
     *
     * @return the grid width.
     * @see PointArray#length
     */
    int width();

    /**
     * Returns the length of this array. This is equivalent to
     * <code>{@linkplain #rows rows}().{@linkplain PointArray#length length}()</code>.
     *
     * @return the grid height.
     */
    int height();

    /**
     * Returns the point at the given row and column index. This is equivalent to
     * <code>{@linkplain #getRow getRow}(row).{@linkplain PointArray#get get}(column)</code>.
     *
     * @param  row The row index from 0 inclusive to {@link #height} exclusive.
     * @param  column The column index from 0 inclusive to {@link #width} exclusive.
     * @return the point at the given index.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     */
    DirectPosition get(int row, int column) throws IndexOutOfBoundsException;

    /**
     * Gets a copy of the {@code DirectPosition} at the particular location in this
     * {@code PointGrid}. If the {@code dest} argument is non-null, that object
     * will be populated with the value from the array. In all cases, the position in insulated
     * from changes in the {@code PointArray}, and vice-versa. Consequently, the same
     * {@code DirectPosition} object can be reused for fetching many points from this grid.
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
     * @param  dest An optionally pre-allocated direct position.
     * @return the {@code dest} argument, or a new object if {@code dest} was null.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     */
    DirectPosition get(int row, int column, DirectPosition dest) throws IndexOutOfBoundsException;

    /**
     * Set the point at the given index. The point coordinates will be copied, i.e. changes
     * to the given {@code position} after this method call will not be reflected into
     * this point array. Consequently, the same {@code DirectPosition} object can be
     * reused for setting many points in this array.
     *
     * @param  row The row index from 0 inclusive to {@link #height} exclusive.
     * @param  column The column index from 0 inclusive to {@link #width} exclusive.
     * @param  position The point to set at the given location in this array.
     * @throws IndexOutOfBoundsException if an index is out of bounds.
     * @throws UnsupportedOperationException if this grid is immutable.
     */
    void set(int row, int column, DirectPosition position) throws IndexOutOfBoundsException,
                                                                         UnsupportedOperationException;

    /**
     * Returns the row at the given index.
     * The row is backed by this {@code PointGrid}, so changes to the row
     * are reflected in the grid, and vice-versa.
     *
     * @param  row The index from 0 inclusive to {@link #height} exclusive.
     * @return the row at the given index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    PointArray getRow(int row) throws IndexOutOfBoundsException;

    /**
     * Returns a view of all rows in this array.
     * The list is backed by this {@code PointGrid}, so changes to any
     * {@linkplain PointArray point array} are reflected in the grid, and vice-versa.
     *
     * @return the rows in this grid.
     */
    @UML(identifier="row", obligation=MANDATORY, specification=ISO_19107)
    List<PointArray> rows();
}
