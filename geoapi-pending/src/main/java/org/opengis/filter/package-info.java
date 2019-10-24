/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
 * Filters {@linkplain org.opengis.feature.Feature features} according their properties.
 * A <cite>filter expression</cite> is a construct used to constraints the property
 * values of an object type for the purpose of identifying a subset of object instances
 * to be operated upon in some manner.
 *
 * The following is adapted from the <a href="http://docs.opengeospatial.org/is/09-026r2/09-026r2.html">OGC®
 * Filter Encoding 2.0 Encoding Standard</a>:
 *
 * <h2>Comparison operators</h2>
 * <p>A comparison operator is used to form expressions that evaluate the mathematical comparison between two arguments.
 * If the arguments satisfy the comparison then the expression {@linkplain org.opengis.filter.Filter#evaluate evaluates}
 * to {@code true}. Otherwise the expression evaluates to {@code false}.</p>
 *
 * <p>In addition to the standard set ({@code =}, {@code <}, {@code >}, {@code >=}, {@code <=}, {@code <>})
 * of comparison operators, this package defines the elements {@link org.opengis.filter.PropertyIsLike},
 * {@link org.opengis.filter.PropertyIsBetween} and {@link org.opengis.filter.PropertyIsNull}.</p>
 *
 * <h2>Logical operators</h2>
 * <p>A logical operator can be used to combine one or more conditional expressions.
 * The logical operator {@link org.opengis.filter.And} evaluates to {@code true} if all the combined
 * expressions evaluate to {@code true}. The operator {@link org.opengis.filter.Or} operator evaluates
 * to {@code true} is any of the combined expressions evaluate to {@code true}.
 * The {@link org.opengis.filter.Not} operator reverses the logical value of an expression.
 * The elements {@code And}, {@code Or} and {@code Not} can be used to combine scalar,
 * spatial and other logical expressions to form more complex compound expressions.</p>
 *
 * <h2>Identity</h2>
 * <p>Identity can be checked using {@link org.opengis.filter.Id}, selected objects
 * will are matched against a set of {@link org.opengis.filter.identify.Identifier}.</p>
 */
package org.opengis.filter;
