/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about spatial, vertical, and temporal extent of the resource.
 * This interface has four optional attributes:
 * {@linkplain #getGeographicElements() geographic elements},
 * {@linkplain #getVerticalElements() vertical elements},
 * {@linkplain #getTemporalElements() temporal elements} and an element called
 * {@linkplain #getDescription() description}.
 * At least one of the four shall be used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="EX_Extent", specification=ISO_19115)
public interface Extent {
    /**
     * The spatial and temporal extent for the referring object.
     *
     * @return the spatial and temporal extent, or {@code null} in none.
     *
     * @condition Mandatory if {@linkplain #getGeographicElements() geographic element},
     *            {@linkplain #getVerticalElements() vertical element} and
     *            {@linkplain #getTemporalElements() temporal element} are not provided.
     */
    @UML(identifier="description", obligation=CONDITIONAL, specification=ISO_19115)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Provides geographic component of the extent of the referring object.
     *
     * @return the geographic extent, or an empty list if none.
     *
     * @condition Mandatory if {@linkplain #getDescription() description},
     *            {@linkplain #getVerticalElements() vertical element} and
     *            {@linkplain #getTemporalElements() temporal element} are not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="geographicElement", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends GeographicExtent> getGeographicElements() {
        return List.of();
    }

    /**
     * Provides vertical component of the extent of the referring object.
     *
     * @return the vertical extent, or an empty list if none.
     *
     * @condition Mandatory if {@linkplain #getDescription() description},
     *            {@linkplain #getGeographicElements() geographic element} and
     *            {@linkplain #getTemporalElements() temporal element} are not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="verticalElement", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends VerticalExtent> getVerticalElements() {
        return List.of();
    }

    /**
     * Provides temporal component of the extent of the referring object.
     *
     * @return the temporal extent, or an empty list if none.
     *
     * @condition Mandatory if {@linkplain #getDescription() description},
     *            {@linkplain #getGeographicElements() geographic element} and
     *            {@linkplain #getVerticalElements() vertical element} are not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="temporalElement", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends TemporalExtent> getTemporalElements() {
        return List.of();
    }
}
