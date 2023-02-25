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
 * {@linkplain org.opengis.metadata.citation.Citation} and
 * {@linkplain org.opengis.metadata.citation.ResponsibleParty responsible party} information.
 * The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">This package of datatypes provides a standardized method
 * ({@linkplain org.opengis.metadata.citation.Citation citation}) for citing a resource
 * (dataset, feature, source, publication, etc.), as well as information about the
 * {@linkplain org.opengis.metadata.citation.ResponsibleParty party responsible} for a resource.
 * The {@linkplain org.opengis.metadata.citation.ResponsibleParty responsible party} contains the
 * identity of person(s), and/or position, and/or organization(s) associated with the resource. The
 * location ({@linkplain org.opengis.metadata.citation.Address address}) of the responsible person
 * or organization is also defined here.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.metadata.citation;
