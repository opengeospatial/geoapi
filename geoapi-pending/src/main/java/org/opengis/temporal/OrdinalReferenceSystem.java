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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * An {@linkplain OrdinalReferenceSystem ordinal temporal reference system} consists
 * of a set or {@linkplain OrdinalEra ordinal era (period)}.
 * {@linkplain OrdinalReferenceSystem ordinal reference system} are often hierarchically
 * structured such that an {@link OrdinalEra} at a given level of the hierarchy include
 * a sequence of coterminous shorter {@link OrdinalEra}.
 *
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
public interface OrdinalReferenceSystem extends TemporalReferenceSystem {

    /**
     * Returns {@linkplain OrdinalEra ordinal era} sequence that make up the highest level of this ordinal reference system.
     *
     * @return {@linkplain OrdinalEra ordinal era} sequence that make up the highest level of this ordinal reference system.
     */
    @UML(identifier="component", obligation=MANDATORY,specification=ISO_19108)
    Collection<OrdinalEra> getComponents();
}
