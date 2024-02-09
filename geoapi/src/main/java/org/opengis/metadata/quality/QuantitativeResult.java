/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * The values or information about the value(s) (or set of values) obtained from applying a data quality measure.
 * Quantitative result may be a single value or multiple values, depending on the {@linkplain #getValueType() value type}
 * and {@linkplain Measure#getValueStructure() value structure} defined in the description of the measure applied.
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
     *
     * @see #getValueType()
     * @see #getValueUnit()
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
     * It describes how the {@linkplain Measure#getValueType() value type} and
     * {@linkplain Measure#getValueStructure() value structure} defined in the
     * measure are implemented to provide the value of the quantitative result.
     *
     * <div class="note"><b>Example:</b>
     * Within the description of the <i>misclassification matrix</i> measure,
     * the {@linkplain Measure#getValueType() value type} may be an integer and
     * the {@linkplain Measure#getValueStructure() value structure} is matrix (<var>n</var> × <var>n</var>).
     * The {@link #getValues() value} attribute of the {@code QuantitativeResult} provides the result matrix itself.
     * This attribute {@code valueRecordType} provides the description of the matrix type.
     * If another encoding is used, the attribute {@code valueRecordType} will change to provide the description
     * of the type matrix in the other encoding, and the implementation of the attribute value will change accordingly,
     * but the value itself will not change.</div>
     *
     * @return value type for reporting a data quality result, or {@code null} if none.
     *
     * @see #getValues()
     * @see Measure#getValueType()
     *
     * @departure historic
     *   Renamed from {@code "valueRecordType"} to {@code "valueType"} for compatibility with ISO 19115:2003
     *   and because the return object type does not need to be repeated in Java method name.
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
