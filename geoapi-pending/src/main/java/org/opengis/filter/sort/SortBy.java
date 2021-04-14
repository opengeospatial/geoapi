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
package org.opengis.filter.sort;

import org.opengis.filter.expression.PropertyName;


/**
 * Defines the sort order, based on a property and ascending/descending.
 * Having SortBy at the Filter level is an interesting undertaking of Filter 1.1
 * support. Why you ask? It is at the Same level as Filter, it is not *used* by
 * Filter itself. The services that make use of Filter, such as WFS are starting
 * to make use of SortBy in the same breath.
 *
 * <p>Where is SortBy used:</p>
 * <ul>
 *   <li>WFS 1.1 Query</li>
 *   <li>CSW 2.0.1 AbstractQuery</li>
 * </ul>
 * There may be more ...
 *
 * <p>What this means is that the GeoTools Query will make use of this
 * construct. As for sorting the result of an expression (where an
 * expression matches more then one element), we will splice it in to
 * AttributeExpression as an optional parameter. Note function is defined
 * to return a single value (so we don't need to worry there).</p>
 *
 * @see <a href="http://schemas.opengis.net/filter/1.1.0/sort.xsd">http://schemas.opengis.net/filter/1.1.0/sort.xsd</a>
 * @see <a href="http://schemas.opengis.net/wfs/1.1.0/wfs.xsd">http://schemas.opengis.net/wfs/1.1.0/wfs.xsd</a>
 *
 * @since GeoAPI 2.1
 * @author Jody Garnett (Refractions Research)
 */
public interface SortBy {
    /**
     * Used to indicate lack of a sorting order.
     * This is the default value for used when setting up a Query.
     */
    SortBy[] UNSORTED = new SortBy[0];

    /**
     * Used to indicate "natural" sorting order, usually according
     * FID (hopefully based on Key attributes).
     * This is the order that is most likely to be available in optimized
     * form, if an Attribute is marked as "key" an optimized ordering should
     * be considered available.
     *
     * <p>Non optimized orderings are will at the very least require as pass
     * through the data to bring it into memory, you can assume some kind
     * of TreeSet would be used. Where the nodes in the tree would indicate
     * a list of FeatureIds associated with the node, in the order encountered.</p>
     *
     * <p>This is a "NullObject".</p>
     */
    SortBy NATURAL_ORDER = new NullSortBy(SortOrder.ASCENDING);

    /**
     * Indicate the reverse order, usually assoicated with "Fid".
     *
     * <p>This is a "NullObject".</p>
     */
    SortBy REVERSE_ORDER = new NullSortBy(SortOrder.DESCENDING);

    /**
     * Indicate property to sort by, specification is limited to PropertyName.
     *
     * <p>Not sure if this is allowed to be a xPath expression?</p>
     * <ul>
     *   <li>It would be consist with our use in GeoTools</li>
     *   <li>It would not seem to agree with the short hand notation
     *       used by WFS1.1 (ie. "year A, month A, day A" )</li>
     * </ul>
     *
     * @todo Return type could be an {@link Expression}.
     * @return name of property to sort by.
     */
    PropertyName getPropertyName();

    /**
     * The the sort order - one of {@link SortOrder#ASCENDING ASCENDING}
     * or {@link SortOrder#DESCENDING DESCENDING}.
     */
    SortOrder getSortOrder();
}
