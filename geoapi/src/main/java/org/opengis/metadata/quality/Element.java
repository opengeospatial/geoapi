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
package org.opengis.metadata.quality;

import java.util.Date;
import java.util.Collection;
import java.util.Collections;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Aspect of quantitative quality information.
 * A data quality element is a component describing a certain aspect of the quality of geographic data.
 * An evaluation of a data quality element is described by the following:
 * <ul>
 *   <li>{@linkplain #getMeasure() Measure}: the type of evaluation;</li>
 *   <li>{@linkplain #getEvaluationMethod() Evaluation method}: the procedure used to evaluate the measure;</li>
 *   <li>{@linkplain #getResults() Result:} the output of the evaluation.</li>
 * </ul>
 *
 * Elements are organized into different categories, which are identified by the following subtypes:
 * {@link Completeness}, {@link LogicalConsistency}, {@link PositionalAccuracy}, {@link TemporalQuality},
 * {@link ThematicAccuracy}, {@link UsabilityElement} or {@link Metaquality}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_Element", specification=ISO_19157)
public interface Element {
    /**
     * Clause in the standalone quality report where this data quality element is described.
     * May apply to any related data quality element (original results in case of derivation or aggregation).
     *
     * @return clause where this data quality element is described, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="standaloneQualityReportDetails", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getStandaloneQualityReportDetails() {
        return null;
    }

    /**
     * Identifier of a measure fully described elsewhere.
     * The whole description can be found within a measure register or catalogue.
     *
     * @return reference to the measure used, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="measure", obligation=OPTIONAL, specification=ISO_19157)
    default MeasureReference getMeasure() {
        return null;
    }

    /**
     * Name of the test applied to the data.
     *
     * @return name of the test applied to the data.
     *
     * @deprecated Replaced by {@link MeasureReference#getNamesOfMeasure()}.
     */
    @Deprecated
    @UML(identifier="nameOfMeasure", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends InternationalString> getNamesOfMeasure() {
        final MeasureReference ref = getMeasure();
        return (ref != null) ? ref.getNamesOfMeasure() : Collections.emptyList();
    }

    /**
     * Evaluation information.
     *
     * @return information about the evaluation method, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="evaluationMethod", obligation=OPTIONAL, specification=ISO_19157)
    default EvaluationMethod getEvaluationMethod() {
        return null;
    }

    /**
     * Code identifying a registered standard procedure, or {@code null} if none.
     *
     * @return code identifying a registered standard procedure, or {@code null}.
     *
     * @deprecated Replaced by {@link MeasureReference#getMeasureIdentification()}.
     */
    @Deprecated
    @UML(identifier="measureIdentification", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Identifier getMeasureIdentification() {
        final MeasureReference ref = getMeasure();
        return (ref != null) ? ref.getMeasureIdentification() : null;
    }

    /**
     * Description of the measure being determined.
     *
     * @return description of the measure being determined, or {@code null}.
     *
     * @deprecated Replaced by {@link MeasureReference#getMeasureDescription()}.
     */
    @Deprecated
    @UML(identifier="measureDescription", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getMeasureDescription() {
        final MeasureReference ref = getMeasure();
        return (ref != null) ? ref.getMeasureDescription() : null;
    }

    /**
     * Type of method used to evaluate quality of the dataset.
     *
     * @return type of method used to evaluate quality, or {@code null}.
     *
     * @deprecated Replaced by {@link EvaluationMethod#getEvaluationMethodType()}.
     */
    @Deprecated
    @UML(identifier="evaluationMethodType", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default EvaluationMethodType getEvaluationMethodType() {
        final EvaluationMethod ref = getEvaluationMethod();
        return (ref != null) ? ref.getEvaluationMethodType() : null;
    }

    /**
     * Description of the evaluation method.
     *
     * @return description of the evaluation method, or {@code null}.
     *
     * @deprecated Replaced by {@link EvaluationMethod#getEvaluationMethodDescrition()}.
     */
    @Deprecated
    @UML(identifier="evaluationMethodDescription", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getEvaluationMethodDescription() {
        final EvaluationMethod ref = getEvaluationMethod();
        return (ref != null) ? ref.getEvaluationMethodDescription() : null;
    }

    /**
     * Reference to the procedure information, or {@code null} if none.
     *
     * @return reference to the procedure information, or {@code null}.
     *
     * @deprecated Replaced by {@link EvaluationMethod#getEvaluationProcedure()}.
     */
    @Deprecated
    @UML(identifier="evaluationProcedure", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Citation getEvaluationProcedure() {
        final EvaluationMethod ref = getEvaluationMethod();
        return (ref != null) ? ref.getEvaluationProcedure() : null;
    }

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The collection size is 1 for a single date, or 2 for a range.
     * Returns an empty collection if this information is not available.
     *
     * @return date or range of dates on which a data quality measure was applied.
     *
     * @deprecated Replaced by {@link EvaluationMethod#getDates()}.
     */
    @Deprecated
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends Date> getDates() {
        final EvaluationMethod ref = getEvaluationMethod();
        return (ref != Collections.emptyList()) ? ref.getDates() : Collections.emptyList();
    }

    /**
     * Value (or set of values) obtained from applying a data quality measure.
     * May be an outcome of evaluating the obtained value (or set of values)
     * against a specified acceptable conformance quality level.
     *
     * @return set of values obtained from applying a data quality measure.
     */
    @UML(identifier="result", obligation=MANDATORY, specification=ISO_19157)
    Collection<? extends Result> getResults();

    /**
     * In case of aggregation or derivation, indicates the original element.
     *
     * @return original element when there is an aggregation or derivation.
     *
     * @since 3.1
     */
    @UML(identifier="derivedElement", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Element> getDerivedElements() {
        return Collections.emptyList();
    }
}
