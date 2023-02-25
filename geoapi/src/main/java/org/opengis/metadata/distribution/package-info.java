/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
 * Information concerning the distributor of, and options for obtaining, a resource.
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
 *  ├─ {@linkplain org.opengis.metadata.distribution.Distribution}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Distributor}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Medium}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Format}
 *  ├─ {@linkplain org.opengis.metadata.distribution.StandardOrderProcess}
 *  ├─ {@linkplain org.opengis.metadata.distribution.DigitalTransferOptions}
 *  └─ {@linkplain org.opengis.metadata.distribution.DataFile}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.distribution.MediumName}
 *  └─ {@linkplain org.opengis.metadata.distribution.MediumFormat}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.distribution.Distribution}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Format}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Distributor}
 *  │   └─ {@linkplain org.opengis.metadata.distribution.StandardOrderProcess}
 *  └─ {@linkplain org.opengis.metadata.distribution.DigitalTransferOptions}
 *      └─ {@linkplain org.opengis.metadata.distribution.Medium}
 *          ├─ {@linkplain org.opengis.metadata.distribution.MediumName} «code list»
 *          └─ {@linkplain org.opengis.metadata.distribution.MediumFormat} «code list»
 * {@linkplain org.opengis.metadata.distribution.DataFile}</pre>
 * </td></tr></table>
 *
 * <p>The {@link org.opengis.metadata.distribution.Distribution#getDistributionFormats() Distribution.distributionFormat} element is mandatory
 * if the {@link org.opengis.metadata.distribution.Distributor#getDistributorFormats() Distributor.distributorFormat} is empty, and conversely.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.0
 */
package org.opengis.metadata.distribution;
