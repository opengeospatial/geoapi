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
package org.opengis.metadata.quality;

import java.util.Date;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.AbstractCollection;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
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
 *   <li>{@linkplain #getMeasureReference() Measure}: the type of evaluation;</li>
 *   <li>{@linkplain #getEvaluationMethod() Evaluation method}: the procedure used to evaluate the measure;</li>
 *   <li>{@linkplain #getResults() Result:} the output of the evaluation.</li>
 * </ul>
 *
 * Elements are organized into different categories, which are identified by the following subtypes:
 * {@link Completeness}, {@link LogicalConsistency}, {@link PositionalAccuracy}, {@link TemporalQuality},
 * {@link ThematicAccuracy}, or {@link Metaquality}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @todo Renamed in 19157:2022: {@code QualityElement}.
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
     *
     * @todo Renamed in 19157:2022: {@code QualityEvaluationReportDetails}.
     */
    @UML(identifier="standaloneQualityReportDetails", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getStandaloneQualityReportDetails() {
        return null;
    }

    /**
     * Full description of a data quality measure.
     *
     * @departure easeOfUse
     *   This method is not part of ISO 19157 specification.
     *   Instead, the standard provides only a {@link MeasureReference}
     *   that clients can use for finding the full measure description in a measure register or catalogue.
     *   Because Java interfaces can execute code (as opposed to static data encoded in XML or JSON documents),
     *   implementers are free to do themselves the work of fetching this information from an external source
     *   when {@code getMeasure()} is invoked. This method is added in the {@link Element} interface for making
     *   that feature possible. This is an optional feature; implementers can ignore this method and implement
     *   only the {@link #getMeasureReference()} method.
     *
     * @return a measure of data quality, or {@code null} if none.
     *
     * @since 3.1
     */
    default Measure getMeasure() {
        return null;
    }

    /**
     * Identifier of a measure fully described elsewhere.
     * The full description is given by {@link #getMeasure()},
     * but that description may not be available to this {@code Element}.
     * Instead, the whole description may be found within a measure register or catalogue,
     * in which case this reference can be used for finding the whole description.
     *
     * <p>If a full measure is {@linkplain #getMeasure() contained in this element},
     * then by default this method returns the {@linkplain Measure#getName() name},
     * {@linkplain Measure#getMeasureIdentifier() identifier} and
     * {@linkplain Measure#getDefinition() definition} of that measure.</p>
     *
     * @departure rename
     *   The ISO 19157 property name is {@code measure}.
     *   This is renamed {@code measureReference} in GeoAPI for reflecting the return type
     *   and for making room for a {@code measure} property for the full {@link Measure} description.
     *
     * @return reference to the measure used, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="measure", obligation=OPTIONAL, specification=ISO_19157)
    default MeasureReference getMeasureReference() {
        final Measure measure = getMeasure();
        return (measure == null) ? null : new MeasureInstanceReference(measure);
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
        final MeasureReference ref = getMeasureReference();
        return (ref != null) ? ref.getNamesOfMeasure() : Collections.emptyList();
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
        final MeasureReference ref = getMeasureReference();
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
        final MeasureReference ref = getMeasureReference();
        return (ref != null) ? ref.getMeasureDescription() : null;
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
     * @deprecated Replaced by {@link EvaluationMethod#getEvaluationMethodDescription()}.
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
     * <p>The default implementation returns a wrapper around @link EvaluationMethod#getDates()} collection.
     * Calls to {@link Iterator#next()} may throw {@link java.time.DateTimeException} if the temporal object
     * cannot be converted to a date.</p>
     *
     * @return date or range of dates on which a data quality measure was applied.
     *
     * @deprecated Replaced by {@link EvaluationMethod#getDates()}.
     */
    @Deprecated
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends Date> getDates() {
        return new AbstractCollection<Date>() {
            @Override public int size() {
                final EvaluationMethod ref = getEvaluationMethod();
                return (ref != null) ? ref.getDates().size() : 0;
            }

            @Override public Iterator<Date> iterator() {
                final EvaluationMethod ref = getEvaluationMethod();
                if (ref == null) {
                    return Collections.emptyIterator();
                }
                final Iterator<? extends Temporal> it = ref.getDates().iterator();
                return new Iterator<Date>() {
                    @Override public void    remove()  {it.remove();}
                    @Override public boolean hasNext() {return it.hasNext();}
                    @Override public Date next() {
                        final Temporal t = it.next();
                        if (t == null) return null;
                        if (t instanceof Instant) {
                            return Date.from((Instant) t);
                        }
                        // Following may throw `DateTimeException` if the temporal does not support the field.
                        return new Date(Math.multiplyExact(t.getLong(ChronoField.INSTANT_SECONDS), 1000));
                    }
                };
            }
        };
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
     * In case of aggregation or derivation, indicates the original elements.
     *
     * @return original element(s) when there is an aggregation or derivation.
     *
     * @since 3.1
     */
    @UML(identifier="derivedElement", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Element> getDerivedElements() {
        return Collections.emptyList();
    }
}
