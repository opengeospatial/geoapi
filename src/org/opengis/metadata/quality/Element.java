/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// J2SE direct dependencies
import java.util.Collection;
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Type of test applied to the data specified by a data quality scope.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="DQ_Element")
public interface Element {
    /**
     * Name of the test applied to the data.
     */
/// @UML (identifier="nameOfMeasure", obligation=OPTIONAL)
    Collection/*<InternationalString>*/ getNamesOfMeasure();

    /**
     * Code identifying a registered standard procedure, or <code>null</code> if none.
     */
/// @UML (identifier="measureIdentification", obligation=OPTIONAL)
    Identifier getMeasureIdentification();

    /**
     * Description of the measure being determined.
     */
/// @UML (identifier="measureDescription", obligation=OPTIONAL)
    InternationalString getMeasureDescription();

    /**
     * Type of method used to evaluate quality of the dataset, or <code>null</code> if unspecified.
     */
/// @UML (identifier="evaluationMethodType", obligation=OPTIONAL)
    EvaluationMethodType getEvaluationMethodType();

    /**
     * Description of the evaluation method.
     */
/// @UML (identifier="evaluationMethodDescription", obligation=OPTIONAL)
    InternationalString getEvaluationMethodDescription();

    /**
     * Reference to the procedure information, or <code>null</code> if none.
     */
/// @UML (identifier="evaluationProcedure", obligation=OPTIONAL)
    Citation getEvaluationProcedure();

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The array length is 1 for a single date, or 2 for a range. Returns
     * <code>null</code> if this information is not available.
     */
/// @UML (identifier="dateTime", obligation=OPTIONAL)
    Date[] getDate();

    /**
     * Value (or set of values) obtained from applying a data quality measure or the out
     * come of evaluating the obtained value (or set of values) against a specified
     * acceptable conformance quality level.
     */
/// @UML (identifier="result", obligation=MANDATORY)
    Result getResult();
}
