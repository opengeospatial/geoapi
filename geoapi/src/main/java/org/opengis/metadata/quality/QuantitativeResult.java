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
import javax.measure.Unit;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the value (or set of values) obtained from applying a data quality measure.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0.1
 * @since   2.0
 *
 * @navassoc - - - Record
 * @navassoc 1 - - RecordType
 * @navassoc 1 - - Unit
 */
@UML(identifier="DQ_QuantitativeResult", specification=ISO_19115)
public interface QuantitativeResult extends Result {
    /**
     * Quantitative value or values, content determined by the evaluation procedure used.
     *
     * @return Quantitative value or values.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Record> getValues();

    /**
     * Value type for reporting a data quality result, or {@code null} if none.
     *
     * @return Value type for reporting a data quality result, or {@code null}.
     */
    @UML(identifier="valueType", obligation=OPTIONAL, specification=ISO_19115)
    RecordType getValueType();

    /**
     * Value unit for reporting a data quality result, or {@code null} if none.
     *
     * @return Value unit for reporting a data quality result, or {@code null}.
     */
    @UML(identifier="valueUnit", obligation=MANDATORY, specification=ISO_19115)
    Unit<?> getValueUnit();

    /**
     * Statistical method used to determine the value, or {@code null} if none.
     *
     * @return Statistical method used to determine the value, or {@code null}.
     */
    @UML(identifier="errorStatistic", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getErrorStatistic();
}
