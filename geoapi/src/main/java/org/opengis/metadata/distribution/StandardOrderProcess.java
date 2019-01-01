/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.distribution;

import java.util.Date;
import java.util.Currency;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.util.InternationalString;
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
    InternationalString getFees();

    /**
     * The monetary units of the {@link #getFees() fees} (as specified in ISO 4217).
     *
     * <p><b>Constraints:</b><br>
     * For ISO 19115 compatibility reasons, this method is <strong>not</strong> required to return
     * a non-null value even if the text returned by {@link #getFees()} contains a currency units.
     * However if this method returns a non-null value, then that value is required to be consistent
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
    Currency getCurrency();

    /**
     * Date and time when the dataset will be available.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return date and time when the dataset will be available, or {@code null}.
     */
    @UML(identifier="plannedAvailableDateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getPlannedAvailableDateTime();

    /**
     * General instructions, terms and services provided by the distributor.
     *
     * @return general instructions, terms and services provided by the distributor, or {@code null}.
     */
    @UML(identifier="orderingInstructions", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getOrderingInstructions();

    /**
     * Typical turnaround time for the filling of an order.
     *
     * @return typical turnaround time for the filling of an order, or {@code null}.
     */
    @UML(identifier="turnaround", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getTurnaround();

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
    RecordType getOrderOptionType();

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
    Record getOrderOptions();
}
