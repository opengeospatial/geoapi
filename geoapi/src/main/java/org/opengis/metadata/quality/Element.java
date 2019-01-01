/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import java.util.Date;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
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
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_Element", specification=ISO_19115, version=2003)
public interface Element {
    /**
     * Name of the test applied to the data.
     *
     * @return name of the test applied to the data.
     */
    @UML(identifier="nameOfMeasure", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Collection<? extends InternationalString> getNamesOfMeasure();

    /**
     * Code identifying a registered standard procedure, or {@code null} if none.
     *
     * @return code identifying a registered standard procedure, or {@code null}.
     */
    @UML(identifier="measureIdentification", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Identifier getMeasureIdentification();

    /**
     * Description of the measure being determined.
     *
     * @return description of the measure being determined, or {@code null}.
     */
    @UML(identifier="measureDescription", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    InternationalString getMeasureDescription();

    /**
     * Type of method used to evaluate quality of the dataset.
     *
     * @return type of method used to evaluate quality, or {@code null}.
     */
    @UML(identifier="evaluationMethodType", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    EvaluationMethodType getEvaluationMethodType();

    /**
     * Description of the evaluation method.
     *
     * @return description of the evaluation method, or {@code null}.
     */
    @UML(identifier="evaluationMethodDescription", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    InternationalString getEvaluationMethodDescription();

    /**
     * Reference to the procedure information, or {@code null} if none.
     *
     * @return reference to the procedure information, or {@code null}.
     */
    @UML(identifier="evaluationProcedure", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Citation getEvaluationProcedure();

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The collection size is 1 for a single date, or 2 for a range.
     * Returns an empty collection if this information is not available.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The element type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return date or range of dates on which a data quality measure was applied.
     *
     * @since 2.1
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Collection<? extends Date> getDates();

    /**
     * Value (or set of values) obtained from applying a data quality measure or the out
     * come of evaluating the obtained value (or set of values) against a specified
     * acceptable conformance quality level.
     *
     * @return set of values obtained from applying a data quality measure.
     *
     * @since 2.1
     */
    @UML(identifier="result", obligation=MANDATORY, specification=ISO_19115, version=2003)
    Collection<? extends Result> getResults();
}
