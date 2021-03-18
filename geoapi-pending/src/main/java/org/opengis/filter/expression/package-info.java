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
 * Combinations of one or more elements that evaluate to single {@link java.lang.Object} value.
 * The following is adapted from Filter encoding specifications:
 * <ul>
 *   <li><a href="http://docs.opengeospatial.org/is/09-026r2/09-026r2.html">OGC® Filter Encoding 2.0 Encoding Standard</a></li>
 *   <li><a href="http://portal.opengeospatial.org/files/?artifact_id=5929">OpenGIS® Catalogue Service Implementation Specification 2.0.1</a></li>
 * </ul>
 *
 * <p>An <cite>expression</cite> can be formed using the elements:
 * {@link org.opengis.filter.expression.Add}, {@link org.opengis.filter.expression.Subtract},
 * {@link org.opengis.filter.expression.Multiply}, {@link org.opengis.filter.expression.Divide},
 * {@link org.opengis.filter.expression.PropertyName}, {@link org.opengis.filter.expression.Literal}
 * and {@link org.opengis.filter.expression.Function}. They all belong to the substitution group
 * expression which means that any of them can be used wherever an expression is called for. In addition,
 * the combination of these elements are themselves expressions and can be used wherever an expression is
 * called for.</p>
 *
 * <h2>Arithmetic operators</h2>
 * <p>The elements defined in this package are used to encode the fundamental arithmetic operations of addition,
 * subtraction, multiplication and division. Arithmetic operators are binary operators meaning that they accept
 * two arguments and evaluate to a single result.</p>
 *
 * <h2>Literals</h2>
 * <p>A literal value is any part of a statement or expression that is to be used exactly as it is specified,
 * rather than as a variable or other element. The {@link org.opengis.filter.expression.Literal} element is used
 * to encode literal scalar and geometric values.</p>
 *
 * <h2>Functions</h2>
 * <p>A function is a named procedure that performs a distinct computation.
 * A function may accept zero or more arguments as input and generates a single result.
 * A function is composed of the name of the function, encoded using the attribute name, and zero or more arguments
 * contained within the {@link org.opengis.filter.expression.Function} element.
 * The arguments themselves are {@linkplain org.opengis.filter.expression.Expression expressions}.</p>
 *
 * <h2>Data Access</h2>
 * <p>Data access is accomplished by means of {@link org.opengis.filter.expression.PropertyName} expressions.
 * These Expressions are independent of the data being queried and form the foundation of a, very simple, general
 * query language offering independence specific bindings to Feature, Metadata and Record data structures.</p>
 *
 * <h2>Filter Encodings</h2>
 * <p>At the time of this writing the Filter API has standard encodings for XML (Filter 1.0 and Filter 1.1)
 * and Text (a BNF form is provided in the Catalog 2.0.1 specification above). You should be warned that not
 * all content can be expressed in all encodings, please refer to the javadocs for specific limitations.</p>
 */
package org.opengis.filter.expression;
