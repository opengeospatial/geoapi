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
 * {@linkplain org.opengis.metadata.identification.Identification} information
 * (includes data and service identification). The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">Identification information contains information to uniquely identify the data.
 * Identification information includes information about the citation for the resource, an abstract,
 * the purpose, credit, the status and points of contact.
 * The {@linkplain org.opengis.metadata.identification.Identification identification}
 * entity is mandatory. It may be specified (subclassed) as
 * {@linkplain org.opengis.metadata.identification.DataIdentification data identification}
 * when used to identify data and as
 * {@linkplain org.opengis.metadata.identification.ServiceIdentification service identification}
 * when used to identify a service.</p>
 *
 * <P ALIGN="justify">{@linkplain org.opengis.metadata.identification.Identification}
 * is an aggregate of the following entities:</P>
 * <UL>
 *   <LI>{@link org.opengis.metadata.distribution.Format}, format of the data</LI>
 *   <LI>{@link org.opengis.metadata.identification.BrowseGraphic}, graphic overview of the data</LI>
 *   <LI>{@link org.opengis.metadata.identification.Usage}, specific uses of the data</LI>
 *   <LI>{@link org.opengis.metadata.constraint.Constraints}, constraints placed on the resource</LI>
 *   <LI>{@link org.opengis.metadata.identification.Keywords}, keywords describing the resource</LI>
 *   <LI>{@link org.opengis.metadata.maintenance.MaintenanceInformation}, how often the data is scheduled
 *       to be updated and the scope of the update</LI>
 * </UL>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.0
 */
package org.opengis.metadata.identification;
