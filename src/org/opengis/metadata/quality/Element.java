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
import java.util.Date;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;


/**
 * Type of test applied to the data specified by a data quality scope.
 *
 * @UML datatype DQ_Element
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Element {
    /**
     * Name of the test applied to the data.
     *
     * @param  locale The desired locale for the names to be returned, or <code>null</code>
     *         for names in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The names in the given locale, or an empty array if none.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional nameOfMeasure
     */
    String[] getNamesOfMeasure(final Locale locale);

    /**
     * Code identifying a registered standard procedure, or <code>null</code> if none.
     *
     * @UML optional measureIdentification
     */
    Identifier getMeasureIdentification();

    /**
     * Description of the measure being determined.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale, or <code>null</code> if none.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional measureDescription
     */
    String getMeasureDescription(Locale locale);

    /**
     * Type of method used to evaluate quality of the dataset, or <code>null</code> if unspecified.
     *
     * @UML optional evaluationMethodType
     */
    EvaluationMethodType getEvaluationMethodType();

    /**
     * Description of the evaluation method.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale, or <code>null</code> if none.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional evaluationMethodDescription
     */
    String getEvaluationMethodDescription(Locale locale);

    /**
     * Reference to the procedure information, or <code>null</code> if none.
     *
     * @UML optional evaluationProcedure
     */
    Citation getEvaluationProcedure();

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The array length is 1 for a single date, or 2 for a range. Returns
     * <code>null</code> if this information is not available.
     *
     * @UML optional dateTime
     */
    Date[] getDate();

    /**
     * Value (or set of values) obtained from applying a data quality measure or the out
     * come of evaluating the obtained value (or set of values) against a specified
     * acceptable conformance quality level.
     *
     * @UML mandatory result
     */
    Result getResult();
}
