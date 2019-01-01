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
 * Represents the rearrangement of a list, or a projection. The main usage of this interface is
 * to determine the differences in the order in which a {@link CoordinateSystem} axes are defined
 * and the order in which they are used in a {@link DirectPosition}.
 *
 * <div class="note"><b>Note:</b>
 * This is helpful, because forcing the {@linkplain CoordinateReferenceSystem coordinate reference system}
 * to be in the {@link CoordinateSystem} as the first coordinate offsets will often force the system
 * to be left handed. Using a odd-permutation on the spatial offsets will flip this to a right handed
 * system, and allow graphics-based or engineering-based software to deal with coordinate as stored
 * in the {@link DirectPosition} without the worry of mirror images, which is the result of getting
 * the handedness of a coordinate system wrong. The usual permutation with simply flip the first and
 * second offset.</div>
 *
 * @author  Axel Francois (LSIS/Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Draft
@UML(identifier="GM_Permutation", specification=ISO_19107)
public interface Permutation {
    /**
     * describes the new ordering of offsets in terms of offset indexes of the original ordering.
     * The offsets shall be represented as a 0-up index; so a <var>n</var>-dimension array in
     * original order would be (0, 1, 2, … , n-1); the index will always ranges up to one less
     * than the cardinality of the array. If the array length is less than the dimension of the
     * coordinate, then all offsets beyond the length of the array will be unmoved by the permutation.
     *
     * <div class="note"><b>Note:</b>
     * Because of its definition, the {@code newOrder} array will normally be a rearrangement
     * of the integer between 0 and <var>n</var>-1 where <var>n</var> is the dimension of the
     * {@link CoordinateSystem} in which the permutation appears. Since the most common issue
     * is that the use of “lat-long” is left handed, the most common new order will be (1, 0)
     * which simply flips the order of the latitude and longitude offsets of a geographic
     * coordinate system.
     * </div>
     *
     * @return Returns a new order or the same order as in {@link DirectPosition}.
     */
    @Draft
    @UML(identifier="newOrder", obligation=MANDATORY, specification=ISO_19107)
    int[] getNewOrder();

    /**
     * Determines whether the permutation is representable as an even or odd number of
     * transposition (2 element swaps). Even permutation preserve handedness and odd
     * ones do not.
     *
     * <div class="note"><b>Note:</b>
     * Under usual circumstances, the only reason to use a permutation is to change
     * a spatial {@linkplain CoordinateReferenceSystem coordinate reference system}
     * from left handed to right handedness. A {@code null} permutation is even,
     * and the usual (1, 0) swap is odd (1 transposition).
     * </div>
     *
     * @return {@code true} if permutation is even, or {@code false} if odd.
     */
    @Draft
    @UML(identifier="isEven", obligation=MANDATORY, specification=ISO_19107)
    boolean isEven();
}
