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
 * {@linkplain org.opengis.metadata.extent.Extent} information. The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">The datatype in this package is an aggregate of the metadata
 * elements that describe the spatial and temporal extent of the referring entity.
 * The {@linkplain org.opengis.metadata.extent.Extent extent} entity contains information about the
 * {@linkplain org.opengis.metadata.extent.GeographicExtent geographic},
 * {@linkplain org.opengis.metadata.extent.TemporalExtent temporal} and the
 * {@linkplain org.opengis.metadata.extent.VerticalExtent vertical} extent of the referring
 * entity.
 *
 * The {@linkplain org.opengis.metadata.extent.GeographicExtent geographic extent} can be subclassed as
 * {@linkplain org.opengis.metadata.extent.BoundingPolygon bounding polygon},
 * {@linkplain org.opengis.metadata.extent.GeographicBoundingBox geographic bounding box} and
 * {@linkplain org.opengis.metadata.extent.GeographicDescription geographic description}.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.metadata.extent;
