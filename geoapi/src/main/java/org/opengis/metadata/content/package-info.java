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
 * {@linkplain org.opengis.metadata.content.ContentInformation Content information}
 * (includes Feature catalogue and Coverage descriptions). The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">This package contains information identifying the feature catalogue used
 * ({@linkplain org.opengis.metadata.content.FeatureCatalogueDescription feature catalogue description})
 * and/or information describing the content of a coverage dataset
 * ({@linkplain org.opengis.metadata.content.CoverageDescription coverage description}). Both description
 * entities are subclasses of the {@linkplain org.opengis.metadata.content.ContentInformation content
 * information} entity. {@linkplain org.opengis.metadata.content.CoverageDescription Coverage description}
 * may be subclassed as {@linkplain org.opengis.metadata.content.ImageDescription image description}.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
package org.opengis.metadata.content;
