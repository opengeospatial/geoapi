/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
 * Determines whether a spatial operation geometric arguments satisfy the stated spatial relationship.
 * The operator {@linkplain org.opengis.filter.Filter#evaluate evaluates} to {@code true} if the spatial
 * relationship is satisfied. Otherwise the operator evaluates to {@code false}.
 *
 * The following is adapted from the <a href="http://docs.opengeospatial.org/is/09-026r2/09-026r2.html">OGC®
 * Filter Encoding 2.0 Encoding Standard</a>:
 *
 * <p>{@linkplain org.opengis.filter.spatial.SpatialOperator Spatial operators} are used to test whether
 * the value of a geometric property, potentially referenced using the name of the property, and a
 * (potentially literal) geometric value satisfy the spatial relationship implied by the operator.
 * For example, the {@link org.opengis.filter.spatial.Overlaps} operator evaluates whether the value
 * of the specified geometric property and the specified literal geometric value spatially overlap.</p>
 *
 * <p>The {@link org.opengis.filter.spatial.BBOX} element is defined as a convenient and more compact
 * way of encoding the very common bounding box constraint based on the {@code gml:Box} geometry.
 * The {@code BBOX} operator should identify all geometries that spatially interact with the box in some manner.</p>
 *
 * <p>The spatial operators {@link org.opengis.filter.spatial.DWithin} and {@link org.opengis.filter.spatial.Beyond}
 * test whether the value of a geometric property is within or beyond a specified distance of the specified literal
 * geometric value. Distance values are expressed using the
 * {@linkplain org.opengis.filter.spatial.DistanceBufferOperator#getDistance distance} attribute.
 * The content of the distance attribute represents the magnitude of the distance and the
 * {@linkplain org.opengis.filter.spatial.DistanceBufferOperator#getDistanceUnits units}
 * attribute is used to specify the units of measure.</p>
 */
package org.opengis.filter.spatial;
