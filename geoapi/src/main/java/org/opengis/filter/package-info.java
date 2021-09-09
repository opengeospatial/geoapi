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

/**
 * Filters features according their properties.
 * A <cite>filter expression</cite> is a construct used to identify a subset of resources
 * from a collection of resources whose property values satisfy a set of logically connected predicates.
 * If the property values of a resource satisfy all the predicates in a filter then that resource is considered
 * to be part of the resulting subset.
 * Filtering can use the following operations:
 *
 * <ul class="verbose">
 *   <li>{@link ComparisonOperator}: mathematical comparison between two arguments.
 *   In addition to the standard set ({@code =}, {@code <}, {@code >}, {@code >=}, {@code <=}, {@code <>})
 *   of comparison operators, this package defines the {@linkplain BetweenComparisonOperator between},
 *   {@linkplain LikeOperator like}, {@linkplain NullOperator null} and {@linkplain NilOperator nil}
 *   comparison operators.</li>
 *
 *   <li>{@link SpatialOperator}: determines whether geometric arguments satisfy the stated spatial relationship.
 *   The {@link DistanceOperator} sub-type tests whether the value of a geometric property is within or beyond a
 *   specified distance of another geometry.</li>
 *
 *   <li>{@link TemporalOperator}: determines whether time arguments satisfy the stated spatial relationship.
 *   Standard set of operators is after, before, begins, begun by, contains, during, equals, overlaps, meets,
 *   ends, overlapped by, met by, ended by.</li>
 *
 *   <li>{@link LogicalOperator}: combination of one or more conditional expressions.</li>
 *
 *   <li>{@link ResourceId}: select objects matching an identifier, optionally with version control.</li>
 * </ul>
 *
 * @version 3.1
 * @since   3.1
 */
package org.opengis.filter;
