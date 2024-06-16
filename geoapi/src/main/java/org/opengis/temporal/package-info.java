/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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

/**
 * Temporal position and duration.
 * The current GeoAPI version contains only the minimal amount of ISO 19108 (Temporal schema)
 * interfaces required for supporting metadata and referencing by coordinates.
 *
 * <h2>Integration with other standards</h2>
 * This package is derived from ISO 19108:2002 (Temporal schema).
 * But that standard overlaps with ISO 19111:2019 and with the {@link java.time} standard API.
 * In order to provide a more harmonized <abbr>API</abbr>, GeoAPI omits the interfaces listed below.
 * The column on the right side shows the closest matches in other standards,
 * but the actual type used by GeoAPI is generally a parent type for allowing flexibility.
 *
 * <table class="ogc">
 *   <caption>Mapping from omitted interfaces to their replacements</caption>
 *   <tr><th>Omitted ISO 19108 type</th>            <th>Closest equivalence</th></tr>
 *   <tr><td>{@code JulianDate}</td>                <td>Combination of {@link org.opengis.geometry.DirectPosition} and {@link org.opengis.referencing.crs.TemporalCRS}</td></tr>
 *   <tr><td>{@code TM_CalDate}</td>                <td>{@link java.time.LocalDate}</td></tr>
 *   <tr><td>{@code TM_CalendarEra}</td>            <td>{@link java.time.chrono.IsoEra}</td></tr>
 *   <tr><td>{@code TM_Calendar}</td>               <td>{@link org.opengis.referencing.crs.TemporalCRS} with {@link org.opengis.referencing.cs.CoordinateDataType#DATE_TIME}</td></tr>
 *   <tr><td>{@code TM_Clock}</td>                  <td>{@link org.opengis.referencing.crs.TemporalCRS} with user-defined {@link org.opengis.referencing.cs.CoordinateDataType}</td></tr>
 *   <tr><td>{@code TM_ClockTime}</td>              <td>{@link java.time.LocalTime}</td></tr>
 *   <tr><td>{@code TM_CoordinateSystem}</td>       <td>{@link org.opengis.referencing.crs.TemporalCRS} with {@link org.opengis.referencing.cs.CoordinateDataType#MEASURE}</td></tr>
 *   <tr><td>{@code TM_Coordinate}</td>             <td>Combination of {@link org.opengis.geometry.DirectPosition} and {@link org.opengis.referencing.crs.TemporalCRS}</td></tr>
 *   <tr><td>{@code TM_DateAndTime}</td>            <td>{@link java.time.LocalDateTime}</td></tr>
 *   <tr><td>{@code TM_Duration}</td>               <td>{@link java.time.temporal.TemporalAmount}</td></tr>
 *   <tr><td>{@code TM_IntervalLength}</td>         <td>{@link java.time.Duration}</td></tr>
 *   <tr><td>{@code TM_OrdinalEra}</td>             <td>{@link java.time.chrono.Era}</td></tr>
 *   <tr><td>{@code TM_OrdinalPosition}</td>        <td>Combination of {@link org.opengis.geometry.DirectPosition} and {@link org.opengis.referencing.crs.TemporalCRS}</td></tr>
 *   <tr><td>{@code TM_OrdinalReferenceSystem}</td> <td>{@link org.opengis.referencing.crs.TemporalCRS} with {@link org.opengis.referencing.cs.CoordinateDataType#INTEGER}</td></tr>
 *   <tr><td>{@code TM_PeriodDuration}</td>         <td>{@link java.time.Period}</td></tr>
 *   <tr><td>{@code TM_ReferenceSystem}</td>        <td>{@link org.opengis.referencing.crs.TemporalCRS}</td></tr>
 *   <tr><td>{@code TM_TemporalPosition}</td>       <td>{@link java.time.temporal.Temporal}</td></tr>
 * </table>
 *
 * @version 3.1
 * @since   1.0
 */
package org.opengis.temporal;
