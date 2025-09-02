/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.geometry.Geometry;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of collection coverage.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_PlatformPass", specification=ISO_19115_2)
public interface PlatformPass {
    /**
     * Unique name of the pass.
     *
     * @return unique name of the pass.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Area covered by the pass.
     *
     * @return area covered by the pass, or {@code null}.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115_2)
    default Geometry getExtent() {
        return null;
    }

    /**
     * Occurrence of one or more events for a pass.
     *
     * @return occurrence of one or more events for a pass.
     */
    @UML(identifier="relatedEvent", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Event> getRelatedEvents() {
        return List.of();
    }
}
