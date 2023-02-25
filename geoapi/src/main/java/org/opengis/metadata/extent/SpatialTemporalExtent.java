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
package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Extent with respect to date/time and spatial boundaries.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="EX_SpatialTemporalExtent", specification=ISO_19115)
public interface SpatialTemporalExtent extends TemporalExtent {
    /**
     * The spatial extent components of composite spatial and temporal extent.
     *
     * @return the list of geographic extents (never {@code null}).
     */
    @UML(identifier="spatialExtent", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends GeographicExtent> getSpatialExtent();
    /*
     * Missing trailing "s" in the method name (because the return type is a collection),
     * but this is probably not worth a compatibility break. Note also that the vertical
     * and temporal extents are both singleton, so while not strictly correct at least
     * the current name provides a uniform naming pattern in this interface.
     */

    /**
     * The vertical extent component.
     *
     * @return vertical extent component, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="verticalExtent", obligation=OPTIONAL, specification=ISO_19115)
    default VerticalExtent getVerticalExtent() {
        return null;
    }
}
