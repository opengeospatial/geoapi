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
 * {@linkplain org.opengis.metadata.spatial.SpatialRepresentation Spatial representation} information
 * (includes grid and vector representation). The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">This package contains information concerning the mechanisms used to represent
 * spatial information in a dataset. The {@linkplain org.opengis.metadata.spatial.SpatialRepresentation
 * spatial representation} entity is optional and can be specified as
 * {@linkplain org.opengis.metadata.spatial.GridSpatialRepresentation grid spatial representation} and
 * {@linkplain org.opengis.metadata.spatial.VectorSpatialRepresentation vector spatial representation}.
 * Each of the specified entities contains mandatory and optional metadata elements. When further
 * description is necessary,
 * {@linkplain org.opengis.metadata.spatial.VectorSpatialRepresentation vector spatial representation}
 * may be specified as {@linkplain org.opengis.metadata.spatial.Georectified georectified}. and/or
 * {@linkplain org.opengis.metadata.spatial.Georeferenceable georeferenceable} entity.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
package org.opengis.metadata.spatial;
