/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
 * Geographic, vertical and temporal {@linkplain org.opengis.metadata.extent.Extent extents}.
 *
 * <p>Metadata object are described in the {@linkplain org.opengis.annotation.Specification#ISO_19115
 * OpenGIS® Metadata (Topic 11)} specification. The following table shows the class hierarchy,
 * together with a partial view of aggregation hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Class hierarchy</th>
 *   <th class="sep">Aggregation hierarchy</th>
 * </tr><tr><td class="hierarchy">
 * <pre> ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.extent.Extent}
 *  ├─ {@linkplain org.opengis.metadata.extent.GeographicExtent} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.extent.GeographicBoundingBox}
 *  │   ├─ {@linkplain org.opengis.metadata.extent.GeographicDescription}
 *  │   └─ {@linkplain org.opengis.metadata.extent.BoundingPolygon}
 *  ├─ {@linkplain org.opengis.metadata.extent.VerticalExtent}
 *  └─ {@linkplain org.opengis.metadata.extent.TemporalExtent}
 *      └─ {@linkplain org.opengis.metadata.extent.SpatialTemporalExtent}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.extent.Extent}
 *  ├─ {@linkplain org.opengis.metadata.extent.GeographicExtent} «abstract»
 *  ├─ {@linkplain org.opengis.metadata.extent.VerticalExtent}
 *  └─ {@linkplain org.opengis.metadata.extent.TemporalExtent}
 * {@linkplain org.opengis.metadata.extent.SpatialTemporalExtent}
 *  └─ {@linkplain org.opengis.metadata.extent.GeographicExtent} «abstract»
 * {@linkplain org.opengis.metadata.extent.GeographicBoundingBox}
 * {@linkplain org.opengis.metadata.extent.GeographicDescription}
 * {@linkplain org.opengis.metadata.extent.BoundingPolygon}</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 4.0
 * @since   1.0
 */
package org.opengis.metadata.extent;
