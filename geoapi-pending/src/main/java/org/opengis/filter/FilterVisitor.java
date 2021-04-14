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
import org.opengis.filter.temporal.After;
import org.opengis.filter.temporal.AnyInteracts;
import org.opengis.filter.temporal.Before;
import org.opengis.filter.temporal.Begins;
import org.opengis.filter.temporal.BegunBy;
import org.opengis.filter.temporal.During;
import org.opengis.filter.temporal.EndedBy;
import org.opengis.filter.temporal.Ends;
import org.opengis.filter.temporal.Meets;
import org.opengis.filter.temporal.MetBy;
import org.opengis.filter.temporal.OverlappedBy;
import org.opengis.filter.temporal.TContains;
import org.opengis.filter.temporal.TEquals;
import org.opengis.filter.temporal.TOverlaps;


/**
 * Visitor with {@code visit} methods to be called by {@link Filter#accept Filter.accept(…)}.
 * <p>
 * Consider: It is unclear if this visitor should be applied directly to Filter, or should be walked accross
 * the data structure by hand.  The standard complient structure is well defined, and this should negate
 * the need for a formal visitor (we don't have internal structure we are hiding).
 * </p>
 * <p>
 * There is still a very valid use for FilterVisitor, a instance may implement both FilterVisitor and ExpressionVisitor
 * and ExpressionVisitory in one direction, and a FilterVisitor and a StyleVisitor in the other. The ability
 * to directly focus on transforming data within a larger structure is something a normal data walk
 * can not accomplish in a scalable manner.
 * </p>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface FilterVisitor {
    /**
     * Used to account for a <code>null</code> filter value.
     * This is particularly used during data structure transofrmations, however
     * the use of <code>null</code> is not recommended. Please make use of Filter.NONE
     * and Filter.ALL as placeholder objects that communicate intent.
     *
     * @param  extraData  value object provided to visitor
     * @return subclass defined
     */
    Object visitNullFilter(Object extraData);

    /**
     * Visit {@link Filter#EXCLUDE} (often used during data structure transformations).
     *
     * @param  filter     {@link Filter#EXCLUDE}.
     * @param  extraData  value object provided to visitor
     * @return subclass supplied
     */
    Object visit(ExcludeFilter filter, Object extraData);

    /**
     * Visit {@link Filter#INCLUDE} (often used during data structure transformations).
     *
     * @param  filter     {@link Filter#INCLUDE}.
     * @param  extraData  value object provided to visitor
     * @return subclass supplied
     */
    Object visit(IncludeFilter filter, Object extraData);

    Object visit(And filter,                            Object extraData);
    Object visit(Id filter,                             Object extraData);
    Object visit(Not filter,                            Object extraData);
    Object visit(Or filter,                             Object extraData);
    Object visit(PropertyIsBetween filter,              Object extraData);
    Object visit(PropertyIsEqualTo filter,              Object extraData);
    Object visit(PropertyIsNotEqualTo filter,           Object extraData);
    Object visit(PropertyIsGreaterThan filter,          Object extraData);
    Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData);
    Object visit(PropertyIsLessThan filter,             Object extraData);
    Object visit(PropertyIsLessThanOrEqualTo filter,    Object extraData);
    Object visit(PropertyIsLike filter,                 Object extraData);
    Object visit(PropertyIsNull filter,                 Object extraData);
    Object visit(PropertyIsNil filter,                  Object extraData);

    Object visit(BBOX filter,       Object extraData);
    Object visit(Beyond filter,     Object extraData);
    Object visit(Contains filter,   Object extraData);
    Object visit(Crosses filter,    Object extraData);
    Object visit(Disjoint filter,   Object extraData);
    Object visit(DWithin filter,    Object extraData);
    Object visit(Equals filter,     Object extraData);
    Object visit(Intersects filter, Object extraData);
    Object visit(Overlaps filter,   Object extraData);
    Object visit(Touches filter,    Object extraData);
    Object visit(Within filter,     Object extraData);

    Object visit(After filter,     Object extraData);
    Object visit(AnyInteracts filter,     Object extraData);
    Object visit(Before filter,     Object extraData);
    Object visit(Begins filter,     Object extraData);
    Object visit(BegunBy filter,     Object extraData);
    Object visit(During filter,     Object extraData);
    Object visit(EndedBy filter,     Object extraData);
    Object visit(Ends filter,     Object extraData);
    Object visit(Meets filter,     Object extraData);
    Object visit(MetBy filter,     Object extraData);
    Object visit(OverlappedBy filter,     Object extraData);
    Object visit(TContains filter,     Object extraData);
    Object visit(TEquals filter,     Object extraData);
    Object visit(TOverlaps filter,     Object extraData);
}
