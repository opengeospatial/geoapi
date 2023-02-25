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
package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.geometry.Geometry;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geometry that is an aggregate of other geometries.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Aggregate", specification=ISO_19107)
public interface Aggregate extends Geometry {
    /**
     * Returns the set containing the elements that compose this aggregate. The
     * set may be modified if this geometry {@linkplain #isMutable is mutable}.
     *
     * @return the set containing the elements that compose this aggregate.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
    Set<? extends Geometry> getElements();
}
