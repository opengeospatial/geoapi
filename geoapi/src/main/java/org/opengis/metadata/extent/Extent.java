/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about spatial, vertical, and temporal extent.
 * This interface has four optional attributes
 * ({@linkplain #getGeographicElements geographic elements},
 *  {@linkplain #getTemporalElements temporal elements}, and
 *  {@linkplain #getVerticalElements vertical elements}) and an element called
 *  {@linkplain #getDescription description}.
 *  At least one of the four shall be used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc - - - GeographicExtent
 * @navassoc - - - TemporalExtent
 * @navassoc - - - VerticalExtent
 */
@UML(identifier="EX_Extent", specification=ISO_19115)
public interface Extent {
    /**
     * Returns the spatial and temporal extent for the referring object.
     *
     * @return The spatial and temporal extent, or {@code null} in none.
     *
     * @condition {@linkplain #getGeographicElements Geographic element},
     *            {@linkplain #getTemporalElements temporal element} and
     *            {@linkplain #getVerticalElements vertical element} not documented.
     */
    @UML(identifier="description", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Provides geographic component of the extent of the referring object
     *
     * @return The geographic extent, or an empty set if none.
     *
     * @condition {@linkplain #getDescription Description},
     *            {@linkplain #getTemporalElements temporal element} and
     *            {@linkplain #getVerticalElements vertical element} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="geographicElement", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends GeographicExtent> getGeographicElements();

    /**
     * Provides temporal component of the extent of the referring object
     *
     * @return The temporal extent, or an empty set if none.
     *
     * @condition {@linkplain #getDescription Description},
     *            {@linkplain #getGeographicElements geographic element} and
     *            {@linkplain #getVerticalElements vertical element} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="temporalElement", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends TemporalExtent> getTemporalElements();

    /**
     * Provides vertical component of the extent of the referring object
     *
     * @return The vertical extent, or an empty set if none.
     *
     * @condition {@linkplain #getDescription Description},
     *            {@linkplain #getGeographicElements geographic element} and
     *            {@linkplain #getTemporalElements temporal element} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="verticalElement", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends VerticalExtent> getVerticalElements();
}
