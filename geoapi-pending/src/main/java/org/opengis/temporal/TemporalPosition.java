/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.crs.TemporalCRS;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Used for describing temporal positions referenced to a temporal CRS.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 *
 * @todo This interface may be replaced by {@link Temporal} from the standard Java library.
 */
@UML(identifier="TM_TemporalPosition", specification=ISO_19108)
public interface TemporalPosition extends Temporal {
    /**
     * Returns the only value for temporal position when no temporal field is defined.
     * When at least one temporal field is defined, this code provides a qualifier
     * to the specific value for temporal position provided by the subtype.
     *
     * @departure integration
     *   The specification has been changed for removing reference to subtypes.
     *   Subtypes are replaced by {@link Temporal} fields for integration with
     *   the Java standard library.
     *
     * @return the reason why the position is indeterminate.
     */
    @UML(identifier="indeterminatePosition", obligation=OPTIONAL, specification=ISO_19108)
    default Optional<IndeterminateValue> getIndeterminatePosition() {
        return Optional.empty();
    }

    /**
     * Returns temporal CRS in which this temporal position is represented.
     * Every {@code TemporalPosition} shall be associated with a {@link TemporalCRS}.
     * This association need not be explicit at the instance level.
     * If not specified, it is assumed to be an association to Gregorian Calendar and UTC.
     *
     * @departure harmonization
     *   ISO 19108 {@code TM_ReferenceSystem} has been replaced by ISO 19111 {@code TemporalCRS}.
     *
     * @return the temporal CRS in which this temporal position is represented.
     */
    @UML(identifier="frame", obligation=MANDATORY, specification=ISO_19108)
    TemporalCRS getFrame();
}
