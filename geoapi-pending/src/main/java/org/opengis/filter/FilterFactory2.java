/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.opengis.util.GenericName;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.spatial.BBOX;
import org.opengis.filter.spatial.Beyond;
import org.opengis.filter.spatial.Contains;
import org.opengis.filter.spatial.Crosses;
import org.opengis.filter.spatial.DWithin;
import org.opengis.filter.spatial.Disjoint;
import org.opengis.filter.spatial.Equals;
import org.opengis.filter.spatial.Intersects;
import org.opengis.filter.spatial.Overlaps;
import org.opengis.filter.spatial.Touches;
import org.opengis.filter.spatial.Within;
import org.opengis.geometry.Envelope;


/**
 * Allows creation of additional Filter constructs.
 * <p>
 * Why do we need this? Because not all implementations are going
 * to be using geoapi Geometry. This allows the creation of complient
 * filters with SFSQL Geometry constructs. Consider this a bridge to
 * existing projects allowing GeoAPI to be used.
 * </p>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.1</A>
 * @author Jody Garnett, Refractions Research Inc.
 * @since GeoAPI 2.1
 */
public interface FilterFactory2 extends FilterFactory {
////////////////////////////////////////////////////////////////////////////////
//
//  FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /** Retrieves the value of a {@linkplain org.opengis.feature.Feature feature}'s property. */
    PropertyName property(GenericName name);

    /**
     * Character string comparison operator with pattern matching and specified wildcards.
     */
    PropertyIsLike like(Expression expr, String pattern, String wildcard, String singleChar, String escape, boolean matchCase);

////////////////////////////////////////////////////////////////////////////////
//
//  SPATIAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /** Checks if the geometry expression overlaps the specified bounding box. */
    BBOX        bbox( Expression geometry, double minx, double miny, double maxx, double maxy, String srs);

    /**
     * Checks if the bounding box of the feature's geometry overlaps the indicated bounds.
     * <p>
     * This method does not strictly confirm to the the Filter 1.0 specification, you may
     * use it to check expressions other than PropertyName.
     * </p>
     * @param geometry  expression used to access a Geometry, in order to check for interaction with bounds
     * @param bounds    indicates the bounds to check geometry against
     */
    BBOX        bbox( Expression geometry, Envelope bounds);


    /** Check if all of a geometry is more distant than the given distance from this object's geometry. */
    Beyond      beyond( Expression geometry1, Expression geometry2, double distance, String units);

    /** Checks if the the first geometric operand contains the second. */
    Contains    contains(Expression geometry1, Expression geometry2);

    /** Checks if the first geometric operand crosses the second. */
    Crosses     crosses(Expression geometry1, Expression geometry2);

    /** Checks if the first operand is disjoint from the second. */
    Disjoint    disjoint(Expression geometry1, Expression geometry2);

    /** Checks if any part of the first geometry lies within the given distance of the second geometry. */
    DWithin     dwithin(Expression geometry1, Expression geometry2, double distance, String units);

    /** Checks if the geometry of the two operands are equal.
     * @todo should be equal, resolve conflict with PropertyIsEqualTo equals( Expression, Expression )
     */
    Equals      equal(Expression geometry1, Expression geometry2);

    /** Checks if the two geometric operands intersect. */
    Intersects  intersects(Expression geometry1, Expression geometry2);

    /** Checks if the interior of the first geometry somewhere overlaps the interior of the second geometry. */
    Overlaps    overlaps(Expression geometry1, Expression geometry2);

    /** Checks if the feature's geometry touches, but does not overlap with the geometry held by this object. */
    Touches     touches(Expression propertyName1, Expression geometry2);

    /** Checks if the feature's geometry is completely contained by the specified constant geometry. */
    Within      within(Expression geometry1, Expression geometry2);
}
