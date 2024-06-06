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
package org.opengis.referencing.datum;

import java.util.Map;
import java.time.temporal.Temporal;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Definition of the relationship of a temporal coordinate system to an object.
 * The object is usually time on Earth.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createTemporalDatum(String)
 * @see DatumFactory#createTemporalDatum(Map, Temporal)
 *
 * @departure integration
 *   The {@code calendar} attribute is omitted because the handling of calendar systems
 *   is delegated to the {@link java.time} framework.
 */
@UML(identifier="TemporalDatum", specification=ISO_19111)
public interface TemporalDatum extends Datum {
    /**
     * Date and time to which temporal coordinates are referenced.
     * The {@link java.time.Instant} type is recommended, but other types are allowed.
     *
     * @return date and time to which temporal coordinates are referenced.
     * @version 4.0
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19111)
    Temporal getOrigin();
}
