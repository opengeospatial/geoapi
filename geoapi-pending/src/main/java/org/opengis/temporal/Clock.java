/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides a basis for defining temporal position within a day.
 *
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0 
 */
@UML(identifier="TM_Clock", specification=ISO_19108)
public interface Clock extends TemporalReferenceSystem {
    
    /**
     * Returns event used as the datum for this clock.
     * 
     * @return event used as the datum for this clock.
     */
    @UML(identifier="referenceEvent", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getReferenceEvent();
    
    /**
     * Returns time of the reference event for this clock, usually the origin of the clock scale.
     * 
     * @return time of the reference event for this clock, usually the origin of the clock scale.
     */
    @UML(identifier="referenceTime", obligation=MANDATORY, specification=ISO_19108)
    ClockTime getReferenceTime();

    /**
     * Returns the 24-hour local or UTC time that corresponds to the reference time.
     * 
     * @return the 24-hour local or UTC time that corresponds to the reference time.
     */
    @UML(identifier="utcReference", obligation=MANDATORY, specification=ISO_19108)
    ClockTime getUTCReference();
    
    /**
     * Returns conversion of an UTC time to a time on this clock.
     * 
     * @param uTime the UTC time which will be convert.
     * @return convertion of an UTC time to a time on this clock.
     */
    @UML(identifier="clkTrans", obligation=MANDATORY, specification=ISO_19108)
    ClockTime clkTrans(ClockTime uTime);
    
    /**
     * Returns convertion from time on this clock to UTC time.
     * 
     * @param clockTime the clock time which will be convert.
     * @return convertion from time on this clock to UTC time.
     */
    @UML(identifier="utcTrans", obligation=MANDATORY, specification=ISO_19108)
    ClockTime utcTrans(ClockTime clockTime);
}
