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
     *
     * @revisit In the UML, the return type for <code>value</code> is an arbitrary amount of
     *          <code>Measure</code> (could be mapped to a <code>double[]</code> array in Java)
     *          and the return type for <code>valueType</code> is <code>RecordType</code>, which
     *          is defined in ISO 19103. We currently map <code>RecordType</code> to a Java
     *          {@link Class}, but it may be revisited in a future version.
     */
/// @UML (identifier="value", obligation=MANDATORY)
    Object getValue();

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
