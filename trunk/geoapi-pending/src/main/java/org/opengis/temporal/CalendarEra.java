/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2010 Open Geospatial Consortium, Inc.
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
 * Characteristics of each calendar era.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_CalendarEra", specification=ISO_19108)
public interface CalendarEra {
    /**
     * Uniquely identifies the calendar era within this calendar.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getName();

    /**
     * Provides the name or description of a mythical or historic event which fixes the position
     * of the base scale of the calendar era.
     */
    @UML(identifier="referenceEvent", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getReferenceEvent();

    /**
     * Provides the date of the reference event expressed as a date in the given calendar.
     */
    @UML(identifier="referenceDate", obligation=OPTIONAL, specification=ISO_19108)
    CalendarDate getReferenceDate();

    /**
     * Provides the {@linkplain JulianDate julian date} that corresponds to the reference date.
     */
    @UML(identifier="julianReference", specification=ISO_19108)
    JulianDate getJulianReference();

    /**
     * Identifies the {@linkplain Period period} for which the calendar era
     * was used as a reference fro dating.
     *
     *
     * @return The period, where the data type for {@linkplain Period#getBegin begin}
     *         and {@link Period#getEnd end} is {@link JulianDate}.
     */
    @UML(identifier="epochOfUse", specification=ISO_19108)
    Period getEpochOfUse();
}
