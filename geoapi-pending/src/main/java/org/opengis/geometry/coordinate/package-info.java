/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
