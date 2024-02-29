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
 * Type for {@linkplain org.opengis.metadata.citation.Citation citing} a resource and information
 * about the {@linkplain org.opengis.metadata.citation.Responsibility responsible party}.
 * The resource can be a dataset, feature, source, publication, <i>etc</i>.
 * The responsible party contains the identity of person(s), and/or position, and/or organization(s)
 * associated with the resource. The {@linkplain org.opengis.metadata.citation.Address address}
 * contains the location of the responsible person or organization.
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
 *  ├─ {@linkplain org.opengis.metadata.citation.Citation}
 *  ├─ {@linkplain org.opengis.metadata.citation.CitationDate}
 *  ├─ {@linkplain org.opengis.metadata.citation.Responsibility}
 *  ├─ {@linkplain org.opengis.metadata.citation.Party} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.citation.Individual}
 *  │   └─ {@linkplain org.opengis.metadata.citation.Organisation}
 *  ├─ {@linkplain org.opengis.metadata.citation.Contact}
 *  ├─ {@linkplain org.opengis.metadata.citation.Telephone}
 *  ├─ {@linkplain org.opengis.metadata.citation.Address}
 *  ├─ {@linkplain org.opengis.metadata.citation.OnlineResource}
 *  └─ {@linkplain org.opengis.metadata.citation.Series}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.citation.DateType}
 *  ├─ {@linkplain org.opengis.metadata.citation.OnLineFunction}
 *  ├─ {@linkplain org.opengis.metadata.citation.PresentationForm}
 *  └─ {@linkplain org.opengis.metadata.citation.Role}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.citation.Citation}
 *  ├─ {@linkplain org.opengis.metadata.citation.CitationDate}
 *  │   └─ {@linkplain org.opengis.metadata.citation.DateType} «code list»
 *  ├─ {@linkplain org.opengis.metadata.citation.Responsibility}
 *  │   ├─ {@linkplain org.opengis.metadata.citation.Party} «abstract»
 *  │   │   └─ {@linkplain org.opengis.metadata.citation.Contact}
 *  │   │       ├─ {@linkplain org.opengis.metadata.citation.Telephone}
 *  │   │       ├─ {@linkplain org.opengis.metadata.citation.Address}
 *  │   │       └─ {@linkplain org.opengis.metadata.citation.OnlineResource}
 *  │   │           └─ {@linkplain org.opengis.metadata.citation.OnLineFunction} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.citation.Role} «code list»
 *  ├─ {@linkplain org.opengis.metadata.citation.PresentationForm} «code list»
 *  └─ {@linkplain org.opengis.metadata.citation.Series}</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 4.0
 * @since   1.0
 */
package org.opengis.metadata.citation;
