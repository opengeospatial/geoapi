/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.acquisition;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the environmental conditions during the acquisition.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_EnvironmentalRecord", specification=ISO_19115_2)
public interface EnvironmentalRecord {
    /**
     * Average air temperature along the flight pass during the photo flight.
     *
     * @return Average air temperature along the flight pass during the photo flight.
     */
    @UML(identifier="averageAirTemperature", obligation=MANDATORY, specification=ISO_19115_2)
    Double getAverageAirTemperature();

    /**
     * Maximum relative humidity along the flight pass during the photo flight.
     *
     * @return Maximum relative humidity along the flight pass during the photo flight.
     */
    @UML(identifier="maxRelativeHumidity", obligation=MANDATORY, specification=ISO_19115_2)
    Double getMaxRelativeHumidity();

    /**
     * Maximum altitude during the photo flight.
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
