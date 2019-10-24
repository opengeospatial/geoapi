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
 * Indicates the ordering requested during a data query.
 * The following is adapted from the following specifications:
 * <ul>
 *   <li><a href="http://docs.opengeospatial.org/is/09-026r2/09-026r2.html">OGC® Filter Encoding 2.0 Encoding Standard</a></li>
 *   <li><a href="http://portal.opengeospatial.org/files/?artifact_id=5929">OpenGIS® Catalogue Service Implementation Specification 2.0.1</a></li>
 * </ul>
 *
 * <p>An <cite>SortBy</cite> can be formed using the elements:
 * {@link org.opengis.filter.expression.Add}, {@link org.opengis.filter.expression.Subtract},
 * {@link org.opengis.filter.expression.Multiply}, {@link org.opengis.filter.expression.Divide},
 * {@link org.opengis.filter.expression.PropertyName}, {@link org.opengis.filter.expression.Literal}
 * and {@link org.opengis.filter.expression.Function}. They all belong to the substitution group
 * expression which means that any of them can be used wherever an expression is called for. In addition,
 * the combination of these elements are themselves expressions and can be used wherever an expression is
 * called for.</p>
 *
 * <h2>FeatureCollections</h2>
 * <p>The contents of a FeatureCollection are not defined with respect to order.</p>
 *
 * <h2>FeatureLists</h2>
 * <p>A FeatureList represents an ordered collection of features, possibly using a
 * siers of SortBy elements to define the intended order.</p>
 *
 * <h2>Catalog and Web Feature Server</h2>
 * <p>Both the Catalog and Web Feature Server specifications all the use of SortBy during requests.</p>
 */
package org.opengis.filter.sort;
