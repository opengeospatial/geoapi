/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the environmental conditions during the acquisition.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_EnvironmentalRecord", specification=ISO_19115_2)
public interface EnvironmentalRecord {
    /**
     * Average air temperature along the flight pass during the photo flight.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Temperature} type in order
     *     to provide unit of measurement together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Average air temperature along the flight pass during the photo flight.
     */
    @UML(identifier="averageAirTemperature", obligation=MANDATORY, specification=ISO_19115_2)
    Double getAverageAirTemperature();

    /**
     * Maximum relative humidity along the flight pass during the photo flight.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Dimensionless} type in
     *     order to provide unit of measurement (typically a percentage) together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Maximum relative humidity along the flight pass during the photo flight.
     */
    @UML(identifier="maxRelativeHumidity", obligation=MANDATORY, specification=ISO_19115_2)
    Double getMaxRelativeHumidity();

    /**
     * Maximum altitude during the photo flight.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Length} type in order to
     *     provide unit of measurement together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Maximum altitude during the photo flight.
     */
    @UML(identifier="maxAltitude", obligation=MANDATORY, specification=ISO_19115_2)
    Double getMaxAltitude();

    /**
     * Meteorological conditions in the photo flight area, in particular clouds, snow and wind.
     *
     * @return Meteorological conditions in the photo flight area.
     */
    @UML(identifier="meteorologicalConditions", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getMeteorologicalConditions();
}
