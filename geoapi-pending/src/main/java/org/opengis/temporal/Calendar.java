/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * A discrete temporal reference system that provides a
 * basis for defining temporal position to a resolution of one day.
 *
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_Calendar", specification=ISO_19108)
public interface Calendar extends TemporalReferenceSystem {

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * object factory {@code createFoo(…)} methods.
     * This is used for setting the value to be returned by {@code getReferenceEvent()}.
     *
     * @since 4.0
     *
     * @todo Revisit javadoc - seems inaccurate.
     */
    public static String REFERENCE_EVENT_KEY = "referenceEvent";

    /**
     * Returns conversion of a {@linkplain CalendarDate date} in this calendar to a
     * {@linkplain JulianDate julian date}.
     *
     * @param date reference date which will be convert.
     * @return conversion of a {@linkplain CalendarDate date} in this calendar to a
     * {@linkplain JulianDate julian date}.
     */
    @UML(identifier="dateTrans", obligation=MANDATORY, specification=ISO_19108)
    JulianDate dateTrans(CalendarDate date, ClockTime time);

    /**
     * Returns convertion of a {@linkplain JulianDate julian date} to a {@linkplain CalendarDate date}
     * in this calendar.
     *
     * @param julian {@link JulianDate} which will be convert.
     * @return convertion of a {@linkplain JulianDate julian date} to a {@linkplain CalendarDate date}
     * in this calendar.
     */
    @UML(identifier="julTrans", obligation=MANDATORY, specification=ISO_19108)
    CalendarDate julTrans(JulianDate julian);

    /**
     * Returns all {@linkplain CalendarEra calendar eras} associated with the {@link Calendar} being described.
     *
     * @return all {@linkplain CalendarEra calendar eras} associated with the {@link Calendar} being described.
     * @since 4.0
     */
    @UML(identifier="referenceFrame", obligation=MANDATORY, specification=ISO_19108)
    Collection<CalendarEra> getReferenceFrame();

    /**
     * Returns the clock that is use with this {@link Calendar} to define temporal
     * position within a calendar day, or {@code null} if none.
     *
     * @return the clock that is use with this {@link Calendar} to define temporal
     * position within a calendar day, or {@code null} if none.
     * @since 4.0
     */
    @UML(identifier="timeBasis", specification=ISO_19108)
    Clock getTimeBasis();
}
