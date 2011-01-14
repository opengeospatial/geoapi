/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides a basis for defining temporal position within a day.
 *
 * @author Alexander Petkov
 *
 * @todo Retrofit in the referencing framework.
 */
@UML(identifier="TM_Clock", specification=ISO_19108)
public interface Clock extends TemporalReferenceSystem {
    /**
     * Event used as the datum for this clock.
     */
    @UML(identifier="referenceEvent", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getReferenceEvent();

    /**
     * Time of the reference Event for this clock, usually the origin of the clock scale.
     */
    @UML(identifier="ReferenceTime", obligation=MANDATORY, specification=ISO_19108)
    ClockTime getReferenceTime();

    /**
     * Provides the 24-hour local or UTC time that corresponds to the reference time.
     */
    @UML(identifier="utcReference", obligation=MANDATORY, specification=ISO_19108)
    ClockTime getUTCReference();

    /**
     * Converts UTC time to a time on this clock.
     */
    @UML(identifier="clkTrans", obligation=MANDATORY, specification=ISO_19108)
    ClockTime clkTrans(ClockTime clkTime);

    /**
     * Converts UTC time to a time on this clock.
     */
    @UML(identifier="utcTrans", obligation=MANDATORY, specification=ISO_19108)
    ClockTime utcTrans(ClockTime uTime);
}
