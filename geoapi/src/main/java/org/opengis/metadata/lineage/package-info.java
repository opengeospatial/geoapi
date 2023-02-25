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
 * {@linkplain org.opengis.metadata.lineage.Lineage} information. The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">This package also contains information about the sources and production processes
 * used in producing a dataset. The {@linkplain org.opengis.metadata.lineage.Lineage lineage} entity is
 * optional and contains a statement about the lineage. It is an aggregate of
 * {@linkplain org.opengis.metadata.lineage.ProcessStep process step} and
 * {@linkplain org.opengis.metadata.lineage.Source source}. The
 * {@linkplain org.opengis.metadata.lineage.Lineage#getStatement statement} element is mandatory if
 * <CODE>{@linkplain org.opengis.metadata.quality.DataQuality#getScope DataQuality.scope}.{@linkplain
 * org.opengis.metadata.quality.Scope#getLevel level}</CODE> has a value of
 * "{@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}" or
 * "{@linkplain org.opengis.metadata.maintenance.ScopeCode#SERIES series}" and the
 * {@linkplain org.opengis.metadata.lineage.Source source} and
 * {@linkplain org.opengis.metadata.lineage.ProcessStep process step} are not set.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
package org.opengis.metadata.lineage;
