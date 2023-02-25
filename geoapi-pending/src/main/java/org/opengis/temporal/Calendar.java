/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
