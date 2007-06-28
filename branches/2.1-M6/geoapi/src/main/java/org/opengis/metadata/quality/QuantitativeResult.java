/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

import java.util.Collection;
import javax.units.Unit;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the value (or set of values) obtained from applying a data quality measure.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @author Cory Horner (Refractions Research)
 * @since GeoAPI 2.0
 */
@UML(identifier="DQ_QuantitativeResult", specification=ISO_19115)
public interface QuantitativeResult extends Result {
    /**
     * Quantitative value or values, content determined by the evaluation procedure used.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Record> getValues();

    /**
     * Value type for reporting a data quality result, or {@code null} if none.
     */
    @UML(identifier="valueType", obligation=OPTIONAL, specification=ISO_19115)
    RecordType getValueType();

    /**
     * Value unit for reporting a data quality result, or {@code null} if none.
     */
    @UML(identifier="valueUnit", obligation=MANDATORY, specification=ISO_19115)
    Unit getValueUnit();

    /**
     * Statistical method used to determine the value, or {@code null} if none.
     */
    @UML(identifier="errorStatistic", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getErrorStatistic();
}
