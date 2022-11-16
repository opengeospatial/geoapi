/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */

/**
 * Description of the quality of geographic data.
 * A data quality evaluation can be applied to data set or a subset of data
 * sharing common characteristics so that its quality can be evaluated.
 *
 * <p>Metadata objects are described in the {@linkplain org.opengis.annotation.Specification#ISO_19115
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
 *  │   ├─ {@linkplain org.opengis.metadata.quality.UsabilityElement}
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
