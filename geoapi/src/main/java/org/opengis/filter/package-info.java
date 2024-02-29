/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2024 Open Geospatial Consortium, Inc.
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

/**
 * Filters features according their properties.
 * A <dfn>filter expression</dfn> is a construct used to identify a subset of resources
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
