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
package org.opengis.metadata.quality;

import java.util.Collection;
import java.util.Date;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Type of test applied to the data specified by a data quality scope.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - Identifier
 * @navassoc 1 - - EvaluationMethodType
 * @navassoc 1 - - Citation
 * @navassoc - - - Result
 */
@UML(identifier="DQ_Element", specification=ISO_19115)
public interface Element {
    /**
     * Name of the test applied to the data.
     *
     * @return Name of the test applied to the data.
     */
    @UML(identifier="nameOfMeasure", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getNamesOfMeasure();

    /**
     * Code identifying a registered standard procedure, or {@code null} if none.
     *
     * @return Code identifying a registered standard procedure, or {@code null}.
     */
    @UML(identifier="measureIdentification", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getMeasureIdentification();

    /**
     * Description of the measure being determined.
     *
     * @return Description of the measure being determined, or {@code null}.
     */
    @UML(identifier="measureDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getMeasureDescription();

    /**
     * Type of method used to evaluate quality of the dataset, or {@code null} if unspecified.
     *
     * @return Type of method used to evaluate quality, or {@code null}.
     */
    @UML(identifier="evaluationMethodType", obligation=OPTIONAL, specification=ISO_19115)
    EvaluationMethodType getEvaluationMethodType();

    /**
     * Description of the evaluation method.
     *
     * @return Description of the evaluation method, or {@code null}.
     */
    @UML(identifier="evaluationMethodDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getEvaluationMethodDescription();

    /**
     * Reference to the procedure information, or {@code null} if none.
     *
     * @return Reference to the procedure information, or {@code null}.
     */
    @UML(identifier="evaluationProcedure", obligation=OPTIONAL, specification=ISO_19115)
    Citation getEvaluationProcedure();

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The collection size is 1 for a single date, or 2 for a range. Returns
     * an empty collection if this information is not available.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The element type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Date or range of dates on which a data quality measure was applied.
     *
     * @since 2.1
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Date> getDates();

    /**
     * Value (or set of values) obtained from applying a data quality measure or the out
     * come of evaluating the obtained value (or set of values) against a specified
     * acceptable conformance quality level.
     *
     * @return Set of values obtained from applying a data quality measure.
     *
     * @since 2.1
     */
    @UML(identifier="result", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Result> getResults();
}
