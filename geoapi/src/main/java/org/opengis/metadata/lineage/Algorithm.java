/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.lineage;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Details of the methodology by which geographic information was derived from the instrument
 * readings.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - Citation
 */
@UML(identifier="LE_Algorithm", specification=ISO_19115_2)
public interface Algorithm {
    /**
     * Information identifying the algorithm and version or date.
     *
     * @return Algorithm and version or date.
     */
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115_2)
    Citation getCitation();

    /**
     * Information describing the algorithm used to generate the data.
     *
     * @return Algorithm used to generate the data.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getDescription();
}
