/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.util.Optional;
import java.time.temporal.Temporal;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * An abstract class that represents a non-decomposed element of geometry or topology of time.
 *
 * <div class="warning"><b>Upcoming API change — future development</b><br>
 * This is a placeholder for an interface defined by ISO 19108 but not yet expressed in GeoAPI.
 * </div>
 *
 * @author  ISO 19108 (for abstract model and documentation)
 * @author  Stephane Fellah (Image Matters)
 * @author  Alexander Petkov
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Remi Marechal (Geomatys).
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="TM_Primitive", specification=ISO_19108)
public interface TemporalPrimitive {
    /**
     * If this primitive is a temporal position (date, time or instant), returns that position.
     * Otherwise (for example, if this primitive is a period), returns an empty value.
     *
     * @return the date, time or instant represented by this primitive,
     *         or an empty value if this primitive is not a position.
     *
     * @departure integration
     *   This method is not part of the ISO 19108 standard.
     *   It has been added for interoperability with the Java standard API.
     *
     * @since 3.1
     */
    default Optional<Temporal> position() {
        return Optional.empty();
    }
}
