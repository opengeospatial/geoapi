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
 * Description of the quality of geographic data.
 * A data quality evaluation can be applied to data set or a subset of data
 * sharing common characteristics so that its quality can be evaluated.
 *
 * <p>Data quality objects are described in the
 * {@linkplain org.opengis.annotation.Specification#ISO_19157 ISO 19157} specification.
 * The following table shows the class hierarchy,
 * together with a partial view of aggregation hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Class hierarchy</th>
 *   <th class="sep">Aggregation hierarchy</th>
 * </tr><tr><td class="hierarchy">
 * <pre> ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.quality.DataQuality}
 *  ├─ {@linkplain org.opengis.metadata.quality.Element} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.quality.Completeness} «abstract»
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.CompletenessCommission}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.CompletenessOmission}
 *  │   ├─ {@linkplain org.opengis.metadata.quality.LogicalConsistency} «abstract»
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.ConceptualConsistency}
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.DomainConsistency}
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.FormatConsistency}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.TopologicalConsistency}
 *  │   ├─ {@linkplain org.opengis.metadata.quality.PositionalAccuracy} «abstract»
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.AbsoluteExternalPositionalAccuracy}
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.RelativeInternalPositionalAccuracy}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.GriddedDataPositionalAccuracy}
 *  │   ├─ {@linkplain org.opengis.metadata.quality.TemporalQuality} «abstract»
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.AccuracyOfATimeMeasurement}
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.TemporalConsistency}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.TemporalValidity}
 *  │   ├─ {@linkplain org.opengis.metadata.quality.ThematicAccuracy} «abstract»
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.ThematicClassificationCorrectness}
 *  │   │   ├─ {@linkplain org.opengis.metadata.quality.NonQuantitativeAttributeCorrectness}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.QuantitativeAttributeAccuracy}
 *  │   └─ {@linkplain org.opengis.metadata.quality.Metaquality} «abstract»
 *  │       ├─ {@linkplain org.opengis.metadata.quality.Confidence}
 *  │       ├─ {@linkplain org.opengis.metadata.quality.Representativity}
 *  │       └─ {@linkplain org.opengis.metadata.quality.Homogeneity}
 *  ├─ {@linkplain org.opengis.metadata.quality.EvaluationMethod}
 *  │   ├─ {@linkplain org.opengis.metadata.quality.DataEvaluation}
 *  │   │   │─ {@linkplain org.opengis.metadata.quality.IndirectEvaluation}
 *  │   │   │─ {@linkplain org.opengis.metadata.quality.SampleBasedInspection}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.FullInspection}
 *  │   └─ {@linkplain org.opengis.metadata.quality.AggregationDerivation}
 *  ├─ {@linkplain org.opengis.metadata.quality.Measure}
 *  ├─ {@linkplain org.opengis.metadata.quality.BasicMeasure}
 *  ├─ {@linkplain org.opengis.metadata.quality.MeasureReference}
 *  ├─ {@linkplain org.opengis.metadata.quality.Description}
 *  └─ {@linkplain org.opengis.metadata.quality.Result} «abstract»
 *      ├─ {@linkplain org.opengis.metadata.quality.QuantitativeResult}
 *      ├─ {@linkplain org.opengis.metadata.quality.ConformanceResult}
 *      ├─ {@linkplain org.opengis.metadata.quality.DescriptiveResult}
 *      └─ {@linkplain org.opengis.metadata.quality.CoverageResult}
 * {@linkplain org.opengis.util.CodeList}
 *  └─ {@linkplain org.opengis.metadata.quality.EvaluationMethodType}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.quality.DataQuality}
 *  ├─ {@linkplain org.opengis.metadata.maintenance.Scope}
 *  ├─ {@linkplain org.opengis.metadata.quality.Element} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.quality.MeasureReference}
 *  │   ├─ {@linkplain org.opengis.metadata.quality.EvaluationMethod}
 *  │   │   └─ {@linkplain org.opengis.metadata.quality.EvaluationMethodType} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.quality.Result} «abstract»
 *  │       └─ {@linkplain org.opengis.metadata.maintenance.Scope}
 *  └─ {@linkplain org.opengis.metadata.quality.StandaloneQualityReportInformation}
 *
 * {@linkplain org.opengis.metadata.quality.Measure}
 *  ├─ {@linkplain org.opengis.metadata.quality.BasicMeasure}
 *  │   └─ {@linkplain org.opengis.metadata.quality.Description}
 *  ├─ {@linkplain org.opengis.metadata.quality.Description}
 *  ├─ {@linkplain org.opengis.metadata.quality.SourceReference}
 *  └─ {@linkplain org.opengis.metadata.quality.ValueStructure}</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 4.0
 * @since   2.0
 */
package org.opengis.metadata.quality;
