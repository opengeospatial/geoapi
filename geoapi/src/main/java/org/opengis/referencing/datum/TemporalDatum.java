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
package org.opengis.referencing.datum;

import java.util.Map;
import java.util.Date;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A temporal datum defines the origin of a temporal coordinate reference system.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createTemporalDatum(String)
 * @see DatumFactory#createTemporalDatum(Map, Date)
 */
@UML(identifier="CD_TemporalDatum", specification=ISO_19111, version=2007)
public interface TemporalDatum extends Datum {
    /**
     * The date and time origin of this temporal datum.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return the date and time origin of this temporal datum.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19111)
    Date getOrigin();

    /**
     * This attribute is defined in the {@link Datum} parent interface,
     * but is not used by a temporal datum.
     *
     * @return always {@code null}.
     */
    @Override
    @UML(identifier="anchorPoint", obligation=FORBIDDEN, specification=ISO_19111)
    default InternationalString getAnchorPoint() {
        return null;
    }

    /**
     * This attribute is defined in the {@link Datum} parent interface,
     * but is not used by a temporal datum.
     *
     * @return always {@code null}.
     */
    @Override
    @UML(identifier="realizationEpoch", obligation=FORBIDDEN, specification=ISO_19111)
    default Date getRealizationEpoch() {
        return null;
    }
}
