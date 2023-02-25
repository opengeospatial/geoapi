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
