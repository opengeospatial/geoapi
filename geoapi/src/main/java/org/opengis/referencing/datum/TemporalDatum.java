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
import java.util.Date;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Definition of the relationship of a temporal coordinate system to an object.
 * The object is usually time on Earth.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createTemporalDatum(String)
 * @see DatumFactory#createTemporalDatum(Map, Date)
 *
 * @departure integration
 *   The {@code calendar} attribute is omitted because the handling of calendar systems
 *   is delegated to the {@link java.time} framework.
 */
@UML(identifier="TemporalDatum", specification=ISO_19111)
public interface TemporalDatum extends Datum {
    /**
     * Date and time to which temporal coordinates are referenced.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release.
     * It may be replaced by {@link java.time.temporal.Temporal}.
     * </div>
     *
     * @return date and time to which temporal coordinates are referenced.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19111)
    Date getOrigin();
}
