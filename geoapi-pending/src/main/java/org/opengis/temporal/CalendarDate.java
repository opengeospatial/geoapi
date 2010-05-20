/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A data type that shall be used to identify temporal position within a calendar.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_CalDate", specification=ISO_19108)
public interface CalendarDate extends TemporalPosition {
    /**
     * Provides the name of the {@linkplain CalendarEra calendar era}
     * to which the date is referenced.
     *
     * @return The name of the calendar era.
     */
    @UML(identifier="calendarEraName", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getCalendarEraName();

    /**
     * Provides a sequence of integers in which the first integer identifies a specific instance
     * of the unit used at the highest level of the calendar hierarchy, the second integer
     * identifies a specific instance of the unit used at the next lower level in the hierarchy,
     * and so on. The format defined in ISO 8601 for dates in the Gregorian calendar may be
     * used for any date that is composed of values for year, month and day.
     *
     * @return Instance of the units, from highest level to lowest level of the calendar hierarchy.
     */
    @UML(identifier="calDate", obligation=MANDATORY, specification=ISO_19108)
    int[] getCalendarDate();
}