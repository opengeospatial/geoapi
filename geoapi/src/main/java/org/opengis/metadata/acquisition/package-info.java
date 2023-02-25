/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
 * {@linkplain org.opengis.metadata.acquisition.AcquisitionInformation Acquisition information}.
 *
 * <p>Metadata objects are described in the {@linkplain org.opengis.annotation.Specification#ISO_19115_2
 * Metadata extension for imagery and gridded data} specification. The following table shows the class
 * hierarchy, together with a partial view of aggregation hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Class hierarchy</th>
 *   <th class="sep">Aggregation hierarchy</th>
 * </tr><tr><td class="hierarchy">
 * <pre> ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.acquisition.AcquisitionInformation Acquisition}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Objective}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.PlatformPass}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Event}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Requirement}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.RequestedDate}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Plan}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Operation}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Platform}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Instrument}
 *  └─ {@linkplain org.opengis.metadata.acquisition.EnvironmentalRecord}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.ObjectiveType}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Trigger}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Context}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Sequence}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Priority}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.GeometryType}
 *  └─ {@linkplain org.opengis.metadata.acquisition.OperationType}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.acquisition.AcquisitionInformation}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Requirement}
 *  │   ├─ {@linkplain org.opengis.metadata.acquisition.RequestedDate}
 *  │   └─ {@linkplain org.opengis.metadata.acquisition.Priority} «code list»
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Objective}
 *  │   ├─ {@linkplain org.opengis.metadata.acquisition.ObjectiveType} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.acquisition.PlatformPass}
 *  │       └─ {@linkplain org.opengis.metadata.acquisition.Event}
 *  │           ├─ {@linkplain org.opengis.metadata.acquisition.Trigger} «code list»
 *  │           ├─ {@linkplain org.opengis.metadata.acquisition.Context} «code list»
 *  │           └─ {@linkplain org.opengis.metadata.acquisition.Sequence} «code list»
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Plan}
 *  │   ├─ {@linkplain org.opengis.metadata.acquisition.GeometryType} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.acquisition.Operation}
 *  │       ├─ {@linkplain org.opengis.metadata.acquisition.OperationType} «code list»
 *  │       └─ {@linkplain org.opengis.metadata.acquisition.Platform}
 *  │           └─ {@linkplain org.opengis.metadata.acquisition.Instrument}
 *  └─ {@linkplain org.opengis.metadata.acquisition.EnvironmentalRecord}</pre>
 * </td></tr></table>
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
package org.opengis.metadata.acquisition;
