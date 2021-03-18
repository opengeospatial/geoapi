/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
import java.util.Date;
import javax.measure.Unit;
import javax.measure.quantity.Time;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;

/**
 * Factory to create Temporal object.
 *
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @author Johann Sorel  (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
public interface TemporalFactory {

    /**
     * Creates a {@link Calendar} without any {@linkplain CalendarEra referenceFrame}.
     *
     * @param name Name that uniquely identifies the temporal reference system.
     * @param domainOfValidity space and time within which the reference system is applicable.
     * @return expected {@link Calendar}.
     */
    Calendar createCalendar(Identifier name, Extent domainOfValidity);

    /**
     * Creates a {@link Calendar}.
     *
     * @param name Name that uniquely identifies the temporal reference system.
     * @param domainOfValidity Space and time within which the reference system is applicable.
     * @param referenceFrame The {@linkplain CalendarEra calendar eras} associated with the calendar being described.
     * @param timeBasis The {@linkplain Clock time basis} that is use with this calendar to define temporal position within a calendar day.
     * @return expected {@link Calendar}.
     */
    Calendar createCalendar(Identifier name, Extent domainOfValidity, Collection<CalendarEra> referenceFrame, Clock timeBasis);

    /**
     * Creates a {@link CalendarDate} object.
     *
     * @param frame                   The {@code ReferenceSystem} associated with this {@linkplain TemporalPosition CalendarDate},
     *                                if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition   Attribute provides the only value for
     *                                {@code TemporalPosition} unless a subtype of {@code TemporalPosition} is used as the data type.
     * @param calendarEraName         Name of the calendar era to which the date is referenced.
     * @param calendarDate            Sequence of positive integers in which the first
     *                                integer identifies a specific instance of the unit used at the highest level
     *                                of the calendar hierarchy, the second integer identifies a specific instance
     *                                of the unit used at the next lower level in the hierarchy, and so on.
     *                                The format defined in ISO 8601 for dates in the Gregorian calendar may be
     *                                used for any date that is composed of values for year, month and day.
     * @return expected {@link CalendarDate}.
     */
    CalendarDate createCalendarDate(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition,
            InternationalString calendarEraName, int[] calendarDate);

    /**
     * Create a {@link CalendarEra} object.
     *
     * @param name            Identify the calendarEra within this calendar.
     * @param referenceEvent  Provide the name or description of a mythical or
     *                        historic event which fixes the position of the base scale of the calendar era.
     * @param referenceDate   Provide the date of the reference referenceEvent
     *                        expressed as a date in the given calendar. In most calendars, this date is
     *                        the origin (i.e the first day) of the scale, but this is not always true.
     * @param julianReference Provide the Julian date that corresponds to the reference date.
     * @param epochOfUse      Identify the Period for which the calendar era was
     *                        used as a basis for dating, the datatype for {@linkplain Period#getBeginning() Period.begin}
     *                        and {@linkplain Period#getEnding() Period.end} shall be JulianDate.
     * @return expected {@link CalendarEra}.
     */
    CalendarEra createCalendarEra(InternationalString name, InternationalString referenceEvent,
            CalendarDate referenceDate, JulianDate julianReference, Period epochOfUse);

    /**
     * Creates a {@link Clock} object.
     *
     * @param name             Name that uniquely identifies the temporal reference system.
     * @param domainOfValidity The space and time within which the reference system is applicable.
     * @param referenceEvent   Provide the name or description of an event,
     *                         such as solar noon or sunrise.
     * @param referenceTime    Provide the time of day associated with the reference
     *                         event expressed as a time of day in the given clock, the reference time
     *                         is usually the origin of the clock scale.
     * @param utcReference     The 24-hour local or UTC time that corresponds to the reference time.
     * @return expected {@link Clock}.
     */
    Clock createClock(Identifier name, Extent domainOfValidity, InternationalString referenceEvent,
            ClockTime referenceTime, ClockTime utcReference);

    /**
     * Creates {@link ClockTime} object.
     *
     * @param frame                 The ReferenceSystem associated with this {@linkplain TemporalPosition ClockTime},
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition The only value for
     *                              {@code TemporalPosition} unless a subtype of TemporalPosition is used as the data type.
     * @param clockTime             A sequence of positive numbers with a structure similar to a {@link CalendarDate}.
     * @return expected {@link ClockTime}.
     */
    ClockTime createClockTime(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition,
            Number[] clockTime);

    /**
     * Creates a {@link DateAndTime}.
     *
     * @param frame                 The ReferenceSystem associated with this TemporalPosition,
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition Attribute provides the only value for
     *                              TemporalPosition unless a subtype of TemporalPosition is used as the data type.
     * @param calendarEraName       Name of the calendar era to which the date is referenced.
     * @param calendarDate          A sequence of positive integers in which the first
     *                              integer identifies a specific instance of the unit used at the highest level of the calendar hierarchy,
     *                              the second integer identifies a specific instance of the unit used at the next
     *                              lower level in the hierarchy, and so on. The format defined in ISO 8601 for
     *                              dates in the Gregorian calendar may be used for any date that is composed
     *                              of values for year, month and day.
     * @param clockTime             A sequence of positive numbers with a structure similar to a CalendarDate.
     * @return expected {@link DateAndTime}
     */
    DateAndTime createDateAndTime(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition,
            InternationalString calendarEraName, int[] calendarDate, Number[] clockTime);

    /**
     * Creates an {@link Instant}.
     *
     * @param date The date of this {@link Instant}, it shall be associated with a single temporal reference system.
     * @return expected {@link Instant}.
     */
    Instant createInstant(Date date);

    /**
     * Creates an {@link IntervalLength}.
     *
     * @param unit   Name of the unit of measure used to express the length of the interval.
     * @param radix  Base of the multiplier of the unit.
     * @param factor Exponent of the base.
     * @param value  Length of the time interval as an integer multiple
     *               of one radix(exp -factor) of the specified unit.
     * @return expected {@link IntervalLength}, sub interface of {@link Duration}.
     */
    IntervalLength createIntervalLenght(Unit unit, int radix, int factor, int value);

    /**
     * Creates an {@link JulianDate}.
     *
     * @param frame                 The ReferenceSystem associated with this TemporalPosition,
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition Attribute provides the only value for
     *                              TemporalPosition unless a subtype of TemporalPosition is used as the data type.
     * @param coordinateValue       The distance from the scale origin expressed
     *                              as a multiple of the standard interval associated with the temporal coordinate system.
     * @return expected {@linkplain TemporalCoordinate JulianDate}.
     */
    JulianDate createJulianDate(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition,
            Number coordinateValue);

    /**
     * Creates an {@link OrdinalEra}.
     *
     * @param name      String that identifies the ordinal era within the {@link OrdinalReferenceSystem}.
     * @param beginning Temporal position at which the ordinal era began, if it is known.
     * @param end       Temporal position at which the ordinal era ended.
     * @param member    {@link OrdinalEra} sequence that subdivide or compose this {@link OrdinalEra}.
     * @return expected {@link OrdinalEra}.
     */
    OrdinalEra createOrdinalEra(InternationalString name, Date beginning, Date end,
            Collection<OrdinalEra> member);

    /**
     * Creates an {@link OrdinalPosition}.
     *
     * @param frame                 The ReferenceSystem associated with this {@linkplain TemporalPosition OrdinalPosition},
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition Provides the only value for
     *                              TemporalPosition unless a subtype of TemporalPosition is used as the data type.
     * @param ordinalPosition       A reference to the ordinal era in which the instant occurs.
     * @return expected {@link OrdinalPosition}.
     */
    OrdinalPosition createOrdinalPosition(TemporalReferenceSystem frame,
            IndeterminateValue indeterminatePosition, OrdinalEra ordinalPosition);

    /**
     * Creates an {@link OrdinalReferenceSystem}.
     *
     * @param name               Name that uniquely identifies the temporal reference system.
     * @param domainOfValidity   Space and time within which the reference system is applicable.
     * @param ordinalEraSequence An ordinal temporal reference system  consists of a set of ordinal eras.
     * @return expected {@link OrdinalReferenceSystem}.
     */
    OrdinalReferenceSystem createOrdinalReferenceSystem(Identifier name,
            Extent domainOfValidity, Collection<OrdinalEra> ordinalEraSequence);

    /**
     * Creates an {@link Period}.
     *
     * @param begin The Instant at which this Period starts.
     * @param end   The Instant at which this Period ends.
     * @return expected {@link OrdinalReferenceSystem}.
     */
    Period createPeriod(Instant begin, Instant end);

    /**
     * Creates an {@link PeriodDuration}.
     *
     * @param years   Number of years in the period.
     * @param months  Number of months in the period.
     * @param week    Number of week in the period.
     * @param days    Number of days in the period.
     * @param hours   number of hours in the period.
     * @param minutes number of minutes in the period.
     * @param seconds number of seconds in the period.
     * @return {@link PeriodDuration} a sub interface of {@link Duration}.
     */
    PeriodDuration createPeriodDuration(InternationalString years, InternationalString months,
            InternationalString week, InternationalString days, InternationalString hours,
            InternationalString minutes, InternationalString seconds);

    /**
     * Creates an {@link TemporalCoordinate}.
     *
     * @param frame                 The {@link TemporalReferenceSystem} associated with this {@link TemporalCoordinate},
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition Provides the only value for
     *                              {@link TemporalCoordinate} unless a subtype of {@link TemporalCoordinate} is used as the data type.
     * @param coordinateValue       Distance from the scale origin expressed
     *                              as a multiple of the standard interval associated with the temporal coordinate system.
     * @return expected {@link TemporalCoordinate}.
     */
    TemporalCoordinate createTemporalCoordinate(TemporalReferenceSystem frame,
            IndeterminateValue indeterminatePosition, Number coordinateValue);

    /**
     * Creates a {@link TemporalCoordinateSystem}.
     *
     * @param name             Name that uniquely identifies the temporal reference system.
     * @param domainOfValidity The space and time within which the reference coordinate system is applicable.
     * @param origin           The origin of the scale, it must be specified in the Gregorian
     *                         calendar with time of day in UTC.
     * @param interval         The name of a single unit of measure used as the base interval for the scale.
     *                         it shall be one of those units of measure for time specified by ISO 31-1,
     *                         or a multiple of one of those units, as specified by ISO 1000.
     * @return expected {@link TemporalCoordinateSystem}.
     */
    TemporalCoordinateSystem createTemporalCoordinateSystem(Identifier name,
            Extent domainOfValidity, Date origin, Unit<Time> interval);

    /**
     * Creates a {@link TemporalPosition}.
     *
     * @param frame                 The ReferenceSystem associated with this TemporalPosition,
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition The only value for TemporalPosition unless a
     *                              subtype of TemporalPosition is used as the data type.
     * @return expected {@link TemporalPosition}.
     */
    TemporalPosition createTemporalPosition(TemporalReferenceSystem frame,
            IndeterminateValue indeterminatePosition);

    /**
     * Creates a {@link TemporalReferenceSystem}.
     *
     * @param name             Name that uniquely identifies the temporal reference system.
     * @param domainOfValidity The space and time within which the reference system is applicable.
     * @return expected {@link TemporalReferenceSystem}.
     */
    TemporalReferenceSystem createTemporalReferenceSystem(Identifier name, Extent domainOfValidity);
}
