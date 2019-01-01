/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.annotation.Draft;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;


/**
 * Organizes the manner in which the {@linkplain DirectPosition direct positions} are described.
 *
 * @author  Axel Francois (LSIS/Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @todo This interface is defined in the ISO 19107 draft. But maybe we should consider retrofitting
 *       it in the GeoAPI <code>org.opengis.referencing.cs.CoordinateSystem</code> interface instead.
 *       See <a href="https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-202">GEO-202</a>.
 */
@Draft
@UML(identifier="GM_CoordinateSystem", specification=ISO_19107)
public interface CoordinateSystem {
    /**
     * The {@linkplain CoordinateReferenceSystem coordinate reference system} as
     * defined in ISO 19111 that describes this {@link CoordinateSystem}.
     * This is non-null if and only if the {@code CoordinateSystem} is acting
     * as a proxy for a pure spatial coordinate system, or if the system is compound,
     * one of its projections is such a proxy. In either case, the proxy is needed
     * to keep track of the {@linkplain CoordinateReferenceSystem coordinate reference system}
     * handedness, and provide a place for other information as needed by applications
     * classes implementing this interface.
     *
     * @return the coordinate reference system, or {@code null}.
     */
    @Draft
    @UML(identifier="coordinateReferenceSystem", obligation=OPTIONAL, specification=ISO_19107)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The size of the ordinate array needed to represent a coordinate in this
     * {@code CoordinateSystem}, when non-homogeneous representations are used.
     * For example the dimension of (<var>X</var>, <var>Y</var>, <var>Z</var>) is 3
     * and the dimension of (<var>Longitude</var>, <var>Latitude</var>) is 2.
     *
     * @return the dimension of this coordinate system.
     */
    @Draft
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension();

    /**
     * A character string identifier for this coordinate system. Since many of these systems
     * will be non-standard, this name may not map to any well know identifier, and the context
     * of the system may be required to understand how the mapping of {@link DirectPosition}s
     * to real locations is accomplished.
     *
     * @return the name of this coordinate system, or {@code null} if unspecified.
     */
    @Draft
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19107)
    String getName();

    /**
     * Describes how offsets in the coordinate arrays are rearranged for storage within the
     * {@link DirectPosition} instances. The representation is as a list of integers representing
     * the new order of the coordinate array. The offsets are expressed in 0-up order. If the
     * permutation is absent, then the order of the {@link DirectPosition#getCoordinate()} array
     * will be the natural order implied by the manner in which the {@link CoordinateSystem} is
     * constructed.
     *
     * <div class="note"><b>Note:</b>
     * The most common use of the permutation is to allow the {@link DirectPosition}s
     * coordinates to be stored in an order consistent with the mathematical requirement to be
     * right-handed. For example, a lat-long 2D system is left-handed so that swapping the first
     * two offsets gives a system more consistent with most mathematical libraries.
     * </div>
     *
     * @return the offsets in the coordinate arrays for storage within the direct positions,
     *         or {@code null} for natural ordering.
     */
    @Draft
    @UML(identifier="permutation", obligation=OPTIONAL, specification=ISO_19107)
    Permutation getPermutation();

    /**
     * The enumerated value for “left” or “right” which will identify the spatial orientation of
     * this coordinate system in its unpermuted state. This information is needed to properly
     * calculate visual representations of the underlying geometry. Most display systems
     * are right-handed (OpenGL) and most {@link org.opengis.referencing.crs.GeographicCRS}
     * systems are left-handed, which means that a geometric object in a {@code GeographicCRS}
     * may require special treatment to obtain proper orientation of views to agree with reality.
     * Orientation is also dependent on coordinate system handedness, and is discussed in this
     * standard in the clauses for surfaces and solids. If not specified, the by default “right”
     * which is the standard for Mathematics and Geometry.
     *
     * <div class="note"><b>Note:</b>
     * A permutation can be represented by sequence of transpositions each swapping exactly two
     * offsets. Although the number of transpositions is not fixed, if one representation uses
     * an even number, then all representations use an even number. Using this, permutations can
     * be classified as either odd or even. An odd permutation changes the handedness of the system.
     * An even permutation does not.
     * </div>
     *
     * @return the coordinate system orientation, or {@code null} if unspecified.
     */
    @Draft
    @UML(identifier="orientation", obligation=OPTIONAL, specification=ISO_19107)
    Handed getOrientation();
}
