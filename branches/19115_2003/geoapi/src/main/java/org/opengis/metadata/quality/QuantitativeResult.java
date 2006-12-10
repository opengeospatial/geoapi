/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/quality/QuantitativeResult.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// J2SE direct dependencies and extension
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the value (or set of values) obtained from applying a data quality measure.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="DQ_QuantitativeResult", specification=ISO_19115)
public interface QuantitativeResult extends Result {
    /**
     * Quantitative value or values, content determined by the evaluation procedure used.
     *
     * @unitof Measure
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19115)
    double[] getValues();

    /**
     * Value type for reporting a data quality result, or {@code null} if none.
     */
    @UML(identifier="valueType", obligation=OPTIONAL, specification=ISO_19115)
    Class getValueType();

    /**
     * Value unit for reporting a data quality result, or {@code null} if none.
     */
    @UML(identifier="valueUnit", obligation=OPTIONAL, specification=ISO_19115)
    Unit getValueUnit();

    /**
     * Statistical method used to determine the value, or {@code null} if none.
     */
    @UML(identifier="errorStatistic", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getErrorStatistic();
}
