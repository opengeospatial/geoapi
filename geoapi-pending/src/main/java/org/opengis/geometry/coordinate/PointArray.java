/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * A sequence of points.
 * <p>
 * The {@code PointArray} interface outlines a means of efficiently storing large numbers of usually
 * homogeneous {@linkplain Position positions}; i.e. all having the same
 * {@linkplain CoordinateReferenceSystem coordinate reference system}. While a point array
 * conceptually contains {@linkplain Position positions}, it provides convenience methods for
 * fetching directly the {@linkplain DirectPosition direct positions} instead.
 * <p>
 * A simple implementation of {@code PointArray} will generally be no more efficient than a simple
 * array of {@link Position}s. More efficient implementations may store coordinates in a more
 * compact form (e.g. in a single {@code float[]} array) and creates {@link Position} objects on the
 * fly when needed.
 * <p>
 * If a particular {@code PointArray} implementation supports efficiently random access through any
 * {@code get} or {@code set} method, it shall announce this capability by implementing the
 * {@link java.util.RandomAccess} interface. Otherwise, users should read the positions through the
 * {@link #iterator() iterator()} instead.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see Position
 * @see PointGrid
 */
@UML(identifier="GM_PointArray", specification=ISO_19107)
public interface PointArray extends List<Position> {
    /**
     * Returns the dimensionality of the coordinates in this array. It shall be equal to the
     * dimensionality of the {@linkplain #getCoordinateReferenceSystem() coordinate reference system}
     * for these coordinates.
     * <p>
     * This method is the same as:
     *
     * <blockquote><pre>
     * return getCoordinateReferenceSystem().getCoordinateSystem().getDimension();
     * </pre></blockquote>
     *
     * @return the dimensionality of this array.
     * @see DirectPosition#getDimension
     */
    int getDimension();

    /**
     * Returns the Coordinate Reference System in which the coordinates are given. May be
     * {@code null} if this particular {@code PointArray} is included in a larger object with such a
     * reference to a coordinate reference system}. In this case, the coordinate reference system is
     * implicitly assumed to take on the value of the containing object's coordinate reference
     * system.
     *
     * @return the coordinate reference system, or {@code null}.
     * @see DirectPosition#getCoordinateReferenceSystem
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Gets a copy of the {@linkplain DirectPosition direct position} at the particular location in
     * this {@code PointArray}. If the {@code dest} argument is non-null, that object will be
     * populated with the value from the array. In all cases, the position in insulated from changes
     * in the {@code PointArray}, and vice-versa. Consequently, the same {@code DirectPosition}
     * object can be reused for fetching many points from this array. Example:
     *
     * <blockquote><pre>
     * DirectPosition position = null;
     * final int length = array.length();
     * for (int i=0; i&lt;length; i++) {
     *     position = array.getDirectPosition(i, position);
     *     // Do some processing...
     * }
     * </pre></blockquote>
     *
     * @param index The location in the array, from 0 inclusive to the array
     *        {@linkplain #length length} exclusive.
     * @param dest An optionally pre-allocated direct position.
     * @return the {@code dest} argument, or a new object if {@code dest} was null.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    DirectPosition getDirectPosition(int index, DirectPosition dest) throws IndexOutOfBoundsException;
}
