/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * The {@linkplain org.opengis.metadata.Metadata metadata} root entity.
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
 *  ├─ {@linkplain org.opengis.metadata.Metadata}
 *  ├─ {@linkplain org.opengis.metadata.MetadataScope}
 *  ├─ {@linkplain org.opengis.metadata.PortrayalCatalogueReference}
 *  ├─ {@linkplain org.opengis.metadata.ApplicationSchemaInformation}
 *  ├─ {@linkplain org.opengis.metadata.MetadataExtensionInformation}
 *  ├─ {@linkplain org.opengis.metadata.ExtendedElementInformation}
 *  └─ {@linkplain org.opengis.metadata.Identifier}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.Datatype}
 *  └─ {@linkplain org.opengis.annotation.Obligation}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.Metadata}
 *  ├─ {@linkplain org.opengis.metadata.MetadataScope}
 *  ├─ {@linkplain org.opengis.metadata.PortrayalCatalogueReference}
 *  ├─ {@linkplain org.opengis.metadata.ApplicationSchemaInformation}
 *  ├─ {@linkplain org.opengis.metadata.MetadataExtensionInformation}
 *  │   └─ {@linkplain org.opengis.metadata.ExtendedElementInformation}
 *  │       ├─ {@linkplain org.opengis.metadata.Datatype} «code list»
 *  │       └─ {@linkplain org.opengis.annotation.Obligation} «code list»
 *  └─ {@linkplain org.opengis.metadata.Identifier}</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 4.0
 * @since   2.0
 */
package org.opengis.metadata;
