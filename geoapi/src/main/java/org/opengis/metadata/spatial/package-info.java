/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
 * Information concerning the mechanisms used to represent spatial information in a dataset.
 * The {@linkplain org.opengis.metadata.spatial.SpatialRepresentation spatial representation}
 * entity is optional and can be specified as
 * {@linkplain org.opengis.metadata.spatial.GridSpatialRepresentation grid spatial representation} or
 * {@linkplain org.opengis.metadata.spatial.VectorSpatialRepresentation vector spatial representation}.
 * When further description is necessary, grid spatial representation may be specified as
 * {@linkplain org.opengis.metadata.spatial.Georectified georectified} or
 * {@linkplain org.opengis.metadata.spatial.Georeferenceable georeferenceable} entity.
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
 *  ├─ {@linkplain org.opengis.metadata.spatial.SpatialRepresentation} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.spatial.VectorSpatialRepresentation}
 *  │   └─ {@linkplain org.opengis.metadata.spatial.GridSpatialRepresentation}
 *  │       ├─ {@linkplain org.opengis.metadata.spatial.Georeferenceable}
 *  │       └─ {@linkplain org.opengis.metadata.spatial.Georectified}
 *  ├─ {@linkplain org.opengis.metadata.spatial.GeolocationInformation} «abstract»
 *  │   └─ {@linkplain org.opengis.metadata.spatial.GCPCollection}
 *  ├─ {@linkplain org.opengis.metadata.spatial.GCP}
 *  ├─ {@linkplain org.opengis.metadata.spatial.Dimension}
 *  └─ {@linkplain org.opengis.metadata.spatial.GeometricObjects}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.spatial.TopologyLevel}
 *  ├─ {@linkplain org.opengis.metadata.spatial.GeometricObjectType}
 *  ├─ {@linkplain org.opengis.metadata.spatial.CellGeometry}
 *  ├─ {@linkplain org.opengis.metadata.spatial.PixelOrientation}
 *  ├─ {@linkplain org.opengis.metadata.spatial.DimensionNameType}
 *  └─ {@linkplain org.opengis.metadata.spatial.SpatialRepresentationType}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.spatial.SpatialRepresentation} «abstract»
 * {@linkplain org.opengis.metadata.spatial.VectorSpatialRepresentation}
 *  ├─ {@linkplain org.opengis.metadata.spatial.TopologyLevel} «code list»
 *  └─ {@linkplain org.opengis.metadata.spatial.GeometricObjects}
 *      └─ {@linkplain org.opengis.metadata.spatial.GeometricObjectType} «code list»
 * {@linkplain org.opengis.metadata.spatial.GridSpatialRepresentation}
 *  ├─ {@linkplain org.opengis.metadata.spatial.Dimension}
 *  │   └─ {@linkplain org.opengis.metadata.spatial.DimensionNameType} «code list»
 *  └─ {@linkplain org.opengis.metadata.spatial.CellGeometry} «code list»
 * {@linkplain org.opengis.metadata.spatial.Georeferenceable}
 *  └─ {@linkplain org.opengis.metadata.spatial.GeolocationInformation} «abstract»
 * {@linkplain org.opengis.metadata.spatial.Georectified}
 *  ├─ {@linkplain org.opengis.metadata.spatial.PixelOrientation} «code list»
 *  └─ {@linkplain org.opengis.metadata.spatial.GCP}
 * {@linkplain org.opengis.metadata.spatial.GCPCollection}
 * {@linkplain org.opengis.metadata.spatial.SpatialRepresentationType} «code list»</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.0
 */
package org.opengis.metadata.spatial;
