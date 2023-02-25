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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Extent with respect to date/time and spatial boundaries.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc - - - GeographicExtent
 */
@UML(identifier="EX_SpatialTemporalExtent", specification=ISO_19115)
public interface SpatialTemporalExtent extends TemporalExtent {
    /**
     * Returns the spatial extent component of composite
     * spatial and temporal extent.
     *
     * @return The list of geographic extents (never {@code null}).
     */
    @UML(identifier="spatialExtent", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends GeographicExtent> getSpatialExtent();
}
