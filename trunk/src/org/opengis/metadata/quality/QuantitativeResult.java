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
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the value (or set of values) obtained from applying a data quality measure.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="DQ_QuantitativeResult")
public interface QuantitativeResult extends Result {
    /**
     * Quantitative value or values, content determined by the evaluation procedure used.
     *
     * @unitof Measure
     */
/// @UML (identifier="value", obligation=MANDATORY)
    double[] getValues();

    /**
     * Value type for reporting a data quality result, or <code>null</code> if none.
     */
/// @UML (identifier="valueType", obligation=OPTIONAL)
    Class getValueType();

    /**
     * Value unit for reporting a data quality result, or <code>null</code> if none.
     */
/// @UML (identifier="valueUnit", obligation=OPTIONAL)
    Unit getValueUnit();

    /**
     * Statistical method used to determine the value, or <code>null</code> if none.
     */
/// @UML (identifier="errorStatistic", obligation=OPTIONAL)
    InternationalString getErrorStatistic();
}
