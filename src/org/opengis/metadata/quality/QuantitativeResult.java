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

// J2SE direct dependencies and extension
import java.util.Locale;
import javax.units.Unit;


/**
 * Information about the value (or set of values) obtained from applying a data quality measure.
 *
 * @UML datatype DQ_QuantitativeResult
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface QuantitativeResult extends Result {
    /**
     * Auantitative value or values, content determined by the evaluation procedure used.
     *
     * @UML mandatory value
     * @unitof Measure
     */
    double[] getValues();

    /**
     * Value type for reporting a data quality result, or <code>null</code> if none.
     *
     * @UML optional valueType
     *
     * @revisit Need to defines a policy for <code>RecordType</code>. Should we
     *          returns a {@link Class}?
     */
//    RecordType getValueType();

    /**
     * Value unit for reporting a data quality result, or <code>null</code> if none.
     *
     * @UML optional valueUnit
     */
    Unit getValueUnit();

    /**
     * Statistical method used to determine the value, or <code>null</code> if none.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale, or <code>null</code> if none.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional errorStatistic
     */
    String getRrrorStatistic(Locale locale);
}
