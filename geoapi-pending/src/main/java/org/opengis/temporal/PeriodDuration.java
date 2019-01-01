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

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * Uses the format specified by ISO 8601 for exchanging information
 * about the duration of a period.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_PeriodDuration", specification=ISO_19108)
public interface PeriodDuration extends Duration {
    
   /**
    * Returns a mandatory element which designates that the returned string
    * represents the duration of a period.
    * 
    * @return a mandatory element which designates that the returned string
    * represents the duration of a period.
    */
    @UML(identifier="designator", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getDesignator();

   /**
    * Returns a positive integer, followed by the character "Y",
    * which indicated the number of years in the period.
    * 
    * @return a positive integer, followed by the character "Y",
    * which indicated the number of years in the period.
    */
    @UML(identifier="years", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getYears();

   /**
    * Returns a positive integer, followed by the character "M",
    * which indicated the number of months in the period.
    * 
    * @return a positive integer, followed by the character "M",
    * which indicated the number of months in the period.
    */
    @UML(identifier="months", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getMonths();

   /**
    * Returns a positive integer, followed by the character "D",
    * which indicated the number of days in the period.
    * 
    * @return a positive integer, followed by the character "D",
    * which indicated the number of days in the period.
    */
    @UML(identifier="days", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getDays();

   /**
    * Returns included element whenever the sequence includes values for
    * units less than a day.
    * 
    * @return included element whenever the sequence includes values for
    * units less than a day.
    */
    @UML(identifier="timeIndicator", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getTimeIndicator();

   /**
    * Returns a positive integer, followed by the character "H",
    * which indicated the number of hours in the period.
    * 
    * @return a positive integer, followed by the character "H",
    * which indicated the number of hours in the period.
    */
    @UML(identifier="hours", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getHours();

   /**
    * Returns a positive integer, followed by the character "M",
    * which indicated the number of minutes in the period.
    * 
    * @return a positive integer, followed by the character "M",
    * which indicated the number of minutes in the period.
    */
    @UML(identifier="minutes", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getMinutes();

   /**
    * Returns a positive integer, followed by the character "S",
    * which indicated the number of seconds in the period.
    * 
    * @return a positive integer, followed by the character "S",
    * which indicated the number of seconds in the period.
    */
    @UML(identifier="seconds", obligation=OPTIONAL, specification=ISO_19108)
    InternationalString getSeconds();
}
