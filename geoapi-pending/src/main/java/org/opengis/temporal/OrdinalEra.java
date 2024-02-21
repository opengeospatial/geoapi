/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import org.opengis.referencing.IdentifiedObject;

/**
 * Provides a reference to the ordinal era in which the instant occurs.
 *
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_OrdinalEra", specification=ISO_19108)
public interface OrdinalEra  extends IdentifiedObject {

    /**
     * Returns the beginning {@link Date} at which this {@link OrdinalEra} begun, or {@code null} if none.
     *
     * @return the beginning {@link Date} at which this {@link OrdinalEra} begun, or {@code null} if none.
     */
    @UML(identifier="begin", obligation=OPTIONAL, specification=ISO_19108)
    Date getBegin();

    /**
     * Returns the ending {@link Date} at which this {@link OrdinalEra} stop, or {@code null} if none.
     *
     * @return the ending {@link Date} at which this {@link OrdinalEra} stop, or {@code null} if none.
     */
    @UML(identifier="end", obligation=OPTIONAL, specification=ISO_19108)
    Date getEnd();

    /**
     * Returns {@link OrdinalEra} sequence that subdivide or compose this {@link OrdinalEra}.
     *
     * @return {@link OrdinalEra} sequence that subdivide or compose this {@link OrdinalEra}.
     */
    @UML(identifier="member", obligation=MANDATORY, specification=ISO_19108)
    Collection<OrdinalEra> getMember();
}
