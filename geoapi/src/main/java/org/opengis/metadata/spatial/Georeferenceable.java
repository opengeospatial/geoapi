/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;

import java.util.List;
import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid with cells irregularly spaced in any given geographic/projected coordinate reference system.
 * Individual cells can be geolocated using geolocation information supplied with the data but cannot
 * be geolocated from the grid properties alone.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Georeferenceable", specification=ISO_19115)
public interface Georeferenceable extends GridSpatialRepresentation {
    /**
     * Indication of whether or not control point(s) exists.
     *
     * @return whether or not control point(s) exists.
     */
    @UML(identifier="controlPointAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isControlPointAvailable();

    /**
     * Indication of whether or not orientation parameters are available.
     *
     * @return whether or not orientation parameters are available.
     */
    @UML(identifier="orientationParameterAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isOrientationParameterAvailable();

    /**
     * Description of parameters used to describe sensor orientation.
     *
     * @return description of parameters used to describe sensor orientation, or {@code null}.
     */
    @UML(identifier="orientationParameterDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getOrientationParameterDescription() {
        return null;
    }

    /**
     * Terms which support grid data georeferencing.
     *
     * @return terms which support grid data georeferencing.
     */
    @UML(identifier="georeferencedParameters", obligation=MANDATORY, specification=ISO_19115)
    Record getGeoreferencedParameters();

    /**
     * Reference providing description of the parameters.
     *
     * @return reference providing description of the parameters.
     */
    @UML(identifier="parameterCitation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getParameterCitations() {
        return List.of();
    }

    /**
     * Information that can be used to geolocate the data.
     *
     * @return a geolocalisation of the data.
     */
    @UML(identifier="geolocationInformation", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends GeolocationInformation> getGeolocationInformation();
}
