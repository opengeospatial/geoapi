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
 * Sources and production processes used in producing a dataset.
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
 * <pre>ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.lineage.Lineage}
 *  ├─ {@linkplain org.opengis.metadata.lineage.ProcessStep}
 *  ├─ {@linkplain org.opengis.metadata.lineage.Source}
 *  ├─ {@linkplain org.opengis.metadata.lineage.NominalResolution}
 *  ├─ {@linkplain org.opengis.metadata.lineage.Processing}
 *  ├─ {@linkplain org.opengis.metadata.lineage.Algorithm}
 *  └─ {@linkplain org.opengis.metadata.lineage.ProcessStepReport}</pre>
 * </td><td class="sep hierarchy">
 * <pre>{@linkplain org.opengis.metadata.lineage.Lineage}
 *  ├─ {@linkplain org.opengis.metadata.lineage.Source}
 *  │   └─ {@linkplain org.opengis.metadata.lineage.NominalResolution}
 *  └─ {@linkplain org.opengis.metadata.lineage.ProcessStep}
 *      ├─ {@linkplain org.opengis.metadata.lineage.Source}
 *      ├─ {@linkplain org.opengis.metadata.lineage.Processing}
 *      │   └─ {@linkplain org.opengis.metadata.lineage.Algorithm}
 *      └─ {@linkplain org.opengis.metadata.lineage.ProcessStepReport}</pre>
 * </td></tr></table>
 *
 * <p>The {@link org.opengis.metadata.lineage.Lineage#getStatement() Lineage.statement} element is mandatory
 * if <code>{@linkplain org.opengis.metadata.quality.DataQuality#getScope() DataQuality.scope}.{@linkplain
 * org.opengis.metadata.maintenance.Scope#getLevel() level}</code> has a value of
 * {@link org.opengis.metadata.maintenance.ScopeCode#DATASET} or
 * {@link org.opengis.metadata.maintenance.ScopeCode#SERIES} and the
 * {@linkplain org.opengis.metadata.lineage.Lineage#getSources() source} and
 * {@linkplain org.opengis.metadata.lineage.Lineage#getProcessSteps() process step} are not set.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 4.0
 * @since   2.0
 */
package org.opengis.metadata.lineage;
