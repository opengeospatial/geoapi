/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
 * {@linkplain org.opengis.metadata.quality.DataQuality Data quality} and
 * {@linkplain org.opengis.metadata.quality.PositionalAccuracy positional accuracy}.
 * The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS&reg; Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <P ALIGN="justify">The parameters that define a coordinate reference system
 * are chosen rather than measured to satisfy the degrees-of-freedom problem in
 * the changeover from observation to coordinate quantities. Coordinate reference
 * systems are therefore by definition error-free (i.e., non-stochastic).
 * A coordinate reference system is realised through a network of control points.
 * The coordinates of those control points, derived from surface and/or from
 * satellite observations, are stochastic. Their accuracy can be expressed in a
 * covariance matrix, which, due to the degrees-of-freedom problem, will have a
 * rank deficiency, described in geodetic literature.</P>
 *
 * <P ALIGN="justify">Coordinate transformations between coordinate reference
 * systems usually have parameter values derived from two sets of point coordinates,
 * one set in system 1, the other set in system 2. As these coordinates are stochastic
 * (i.e., have random-error characteristics) the derived transformation parameter
 * values will also be stochastic. Their covariance matrix can be calculated.</P>
 *
 * <P ALIGN="justify">Coordinates that have not been "naturally" determined in
 * coordinate reference system 2, but have been determined in coordinate system 1
 * and then transformed to system 2, have the random error effects of the
 * transformation superimposed on their original error characteristics. It may be
 * possible in well-controlled cases to calculate the covariance matrices of the
 * point coordinates before and after the transformation, and thus isolate the
 * effect of the transformation, but in practice a user will only be interested
 * in the accuracy of the final transformed coordinates.</P>
 *
 * <P ALIGN="justify">Nevertheless the option is offered to specify the
 * covariance matrix of point coordinates resulting exclusively from the
 * transformation. It is outside the scope of this specification to describe
 * how that covariance matrix should be used. Because a covariance matrix is
 * symmetrical, only the upper or lower diagonal part (including the main diagonal)
 * needs to be specified.</P>
 *
 * <P ALIGN="justify">For some transformations, this accuracy information is
 * compacted in some assessment of an average impact on horizontal position and
 * vertical position, allowing specification of average absolute accuracy and,
 * when relevant and available, average relative accuracy. Hence separate quality
 * measures may be specified for horizontal and for vertical position in those
 * objects.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
package org.opengis.metadata.quality;
