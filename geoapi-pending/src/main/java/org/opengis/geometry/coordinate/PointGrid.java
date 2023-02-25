/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
     * Returns a view of all rows in this array.
     * The list is backed by this {@code PointGrid}, so changes to any
     * {@linkplain PointArray point array} are reflected in the grid, and vice-versa.
     *
     * @return the rows in this grid.
     */
    @UML(identifier="row", obligation=MANDATORY, specification=ISO_19107)
    List<PointArray> rows();
}
