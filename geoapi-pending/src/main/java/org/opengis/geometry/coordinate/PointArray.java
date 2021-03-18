/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
