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
package org.opengis.metadata.content;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of specific range elements.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @see CoverageDescription#getRangeElementDescriptions()
 */
@UML(identifier="MI_RangeElementDescription", specification=ISO_19115_2)
public interface RangeElementDescription {
    /**
     * Designation associated with a set of range elements.
     *
     * @return designation associated with a set of range elements.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getName();

    /**
     * Description of a set of specific range elements.
     *
     * @return description of a set of specific range elements.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getDefinition();

    /**
     * Specific range elements, i.e. range elements associated with a name and their definition.
     *
     * @return specific range elements.
     */
    @UML(identifier="rangeElement", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Record> getRangeElements();
}
