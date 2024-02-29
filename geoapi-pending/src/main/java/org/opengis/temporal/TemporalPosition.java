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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Used for describing {@link TemporalPosition temporal positions} referenced
 * to other {@linkplain TemporalReferenceSystem temporal reference systems}.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_TemporalPosition", specification=ISO_19108)
public interface TemporalPosition {
    /**
     * Returns the only value for temporal position unless a subtype of
     * {@link TemporalPosition} is used as the data type, or {@code null} if none.
     * When this attribute is used with a subtype of {@link TemporalPosition},
     * it provides a qualifier to the specific value for temporal position provided by the subtype.
     *
     * @return the only value for temporal position unless a subtype of
     * {@link TemporalPosition} is used as the data type, or {@code null} if none.
     */
    @UML(identifier="indeterminatePosition", obligation=OPTIONAL, specification=ISO_19108)
    IndeterminateValue getIndeterminatePosition();

    /**
     * Returns the association which connect the {@link TemporalPosition} to a {@link TemporalReferenceSystem}.
     * Every {@link TemporalPosition} shall be associated with a {@link TemporalReferenceSystem}.
     * This association need not be explicit at the instance level.
     * If not specified, it is assumed to be an association to Gregorian Calendar and UTC.
     *
     * @return the association which connect the {@link TemporalPosition} to a {@link TemporalReferenceSystem}.
     */
    @UML(identifier="frame", obligation=MANDATORY, specification=ISO_19108)
    TemporalReferenceSystem getFrame();
}
