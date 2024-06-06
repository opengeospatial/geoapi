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
package org.opengis.metadata.distribution;

import java.util.Date;
import java.util.Currency;
import java.time.temporal.Temporal;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.util.InternationalString;
import org.opengis.geoapi.internal.Legacy;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Common ways in which the resource may be obtained or received, and related instructions
 * and fee information.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_StandardOrderProcess", specification=ISO_19115)
public interface StandardOrderProcess {
    /**
     * Fees and terms for retrieving the resource.
     * Includes monetary units (as specified in ISO 4217).
     * The monetary units may also be available with {@link #getCurrency()}.
     *
     * @return fees and terms for retrieving the resource, or {@code null}.
     */
    @UML(identifier="fees", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getFees() {
        return null;
    }

    /**
     * The monetary units of the {@link #getFees() fees} (as specified in ISO 4217).
     *
     * <p><b>Constraints:</b><br>
     * For ISO 19115 compatibility reasons, this method is <strong>not</strong> required to return
     * a non-null value even if the text returned by {@link #getFees()} contains a currency units.
     * However, if this method returns a non-null value, then that value is required to be consistent
     * with the fees text.</p>
     *
     * @departure integration
     *   This method is not part of ISO specification. It has been added in GeoAPI for
     *   integration with the standard JDK library.
     *
     * @return the fees monetary units, or {@code null} if none or unknown.
     *
     * @since 3.1
     */
    default Currency getCurrency() {
        return null;
    }

    /**
     * Date and time when the dataset will be available.
     *
     * @return date and time when the dataset will be available, or {@code null}.
     *
     * @deprecated Replaced by {@link #getPlannedAvailableDate()}.
     */
    @Deprecated(since="3.1")
    default Date getPlannedAvailableDateTime() {
        return Legacy.toDate(getPlannedAvailableDate());
    }

    /**
     * Date and time when the dataset will be available.
     * The returned value should be an instance of {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
     * {@link java.time.OffsetDateTime} or {@link java.time.ZonedDateTime}, depending whether hours are defined
     * and how the timezone (if any) is defined. But other types are also allowed.
     *
     * @return date and time when the dataset will be available, or {@code null}.
     *
     * @since 3.1
     */
    @UML(identifier="plannedAvailableDateTime", obligation=OPTIONAL, specification=ISO_19115)
    default Temporal getPlannedAvailableDate() {
        return null;
    }

    /**
     * General instructions, terms and services provided by the distributor.
     *
     * @return general instructions, terms and services provided by the distributor, or {@code null}.
     */
    @UML(identifier="orderingInstructions", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getOrderingInstructions() {
        return null;
    }

    /**
     * Typical turnaround time for the filling of an order.
     *
     * @return typical turnaround time for the filling of an order, or {@code null}.
     */
    @UML(identifier="turnaround", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getTurnaround() {
        return null;
    }

    /**
     * Description of the {@linkplain #getOrderOptions() order options} record.
     *
     * @return description of the order options record, or {@code null} if none.
     *
     * @since 3.1
     *
     * @see Record#getRecordType()
     */
    @UML(identifier="orderOptionsType", obligation=OPTIONAL, specification=ISO_19115)
    default RecordType getOrderOptionsType() {
        return null;
    }

    /**
     * Request/purchase choices.
     *
     * @return request/purchase choices.
     *
     * @since 3.1
     *
     * @todo We presume that this record is filled by the vendor for describing the options chosen by the client
     *       when he ordered the resource. We presume that this is not a record to be filled by the user for new
     *       orders, otherwise this method would need to be a factory rather than a getter.
     */
    @UML(identifier="orderOptions", obligation=OPTIONAL, specification=ISO_19115)
    default Record getOrderOptions() {
        return null;
    }
}
