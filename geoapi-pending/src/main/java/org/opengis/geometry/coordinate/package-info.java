/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
 * Core package needed to investigate coordinate-defined geometry. The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19107 OpenGIS® Feature Geometry
 * (Topic 1)} specification.
 *
 * <P>A large number of the geometric types in the ISO 19107 standard are defined
 * parametrically, that is they are represented by functions from a set of parameters (in a parametric
 * space, usually a subset of some Euclidean <var>n</var>-dimensional coordinate space) into a coordinate
 * space of some larger dimension. The first few dimensions (up to 3) representing geographic space,
 * the next possibly time, and any remainder representing whatever the application needs, such as
 * distributed attributes or some other measures. The type of geometry is usually determined by the
 * dimension of the parameter space, which will normally be equal to the topological dimension of
 * the resulting geometry. So a 0- parameter geometric object is a point, 1-parameter geometric
 * object is a curve, a 2-parameter geometric object is a surface, a 3-parameter geometric object
 * is a solid.</P>
 *
 * <P>An <var>n</var>-dimensional coordinate space consists of all <var>n</var>-long arrays
 * of numbers; each array represents a point in the space. In particular situations, this may be restricted
 * to a subset of such points, called the extent of validity, usually based on a set of constraints
 * on values of the various offsets within the array. Each point is associated to a spatial or
 * spatial-temporal location, but a single location may be the target of multiple coordinate space
 * points. Locations given by such structures are called {@linkplain org.opengis.geometry.DirectPosition
 * direct positions}.</P>
 *
 * <P>All locations in a list or array shall use the same coordinate system and
 * shall reference reality in a manner representable by continuous functions from the coordinate
 * tuples ({@link org.opengis.geometry.DirectPosition}s) to reality in such a manner that “nearby”
 * coordinates in the {@code DirectPosition}s map to “nearby” positions in reality. The ISO 19107
 * standard does not assume that these functions maintain topological dimension. See for example
 * {@linkplain org.opengis.geometry.coordinate.HomogeneousDirectPosition homogeneous direct position}.</P>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Axel Francois (LSIS/Geomatys)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.geometry.coordinate;
