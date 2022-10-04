/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
import javax.measure.Unit;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The values or information about the value(s) (or set of values) obtained
 * from applying a data quality measure.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_QuantitativeResult", specification=ISO_19157)
public interface QuantitativeResult extends Result {
    /**
     * Quantitative value or values, content determined by the evaluation procedure used.
     * This is determined accordingly with the value type and value structure defined for the measure.
     *
     * @return quantitative value or values.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19157)
    Collection<? extends Record> getValues();

    /**
     * Value unit for reporting a data quality result.
     *
     * @return value unit for reporting a data quality result, or {@code null}.
     */
    @UML(identifier="valueUnit", obligation=OPTIONAL, specification=ISO_19157)
    default Unit<?> getValueUnit() {
        return null;
    }

    /**
     * Value type for reporting a data quality result.
     * Depends of the implementation.
     *
     * @return value type for reporting a data quality result, or {@code null} if none.
     *
     * @departure rename
     *   Renamed from {@code "valueRecordType"} to {@code "valueType"} for compatibility with ISO 19115:2003
     *   and because the return object type does not need to be repeated in Java method name.
     *
     * @since 3.1
     */
    @UML(identifier="valueRecordType", obligation=OPTIONAL, specification=ISO_19157)
    default RecordType getValueType() {
        return null;
    }

    /**
     * Statistical method used to determine the value,
     *
     * @return statistical method used to determine the value.
     *
     * @deprecated Removed from ISO 19157:2013.
     */
    @Deprecated
    @UML(identifier="errorStatistic", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getErrorStatistic() {
        return null;
    }
}
