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

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A data type that shall be used to identify temporal position within a calendar.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @since   2.3
 * @version 4.0 
 */
@UML(identifier="TM_CalDate", specification=ISO_19108)
public interface CalendarDate extends TemporalPosition {
    
    /**
     * Returns the name of the {@linkplain CalendarEra calendar era}
     * to which the date is referenced.
     *
     * @return the name of the calendar era.
     */
    @UML(identifier="calendarEraName", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getCalendarEraName();

    /**
     * Returns a sequence of integers in which the first integer identifies a specific instance
     * of the unit used at the highest level of the calendar hierarchy, the second integer
     * identifies a specific instance of the unit used at the next lower level in the hierarchy,
     * and so on. The format defined in ISO 8601 for dates in the Gregorian calendar may be
     * used for any date that is composed of values for year, month and day.
     *
     * @return instance of the units, from highest level to lowest level of the calendar hierarchy.
     */
    @UML(identifier="calDate", obligation=MANDATORY, specification=ISO_19108)
    int[] getCalendarDate();
}
