/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.extent;

import org.opengis.annotation.UML;
import org.opengis.temporal.TemporalPrimitive;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Time period covered by the content of the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - TemporalPrimitive
 */
@UML(identifier="EX_TemporalExtent", specification=ISO_19115)
public interface TemporalExtent {
    /**
     * Returns the date and time for the content of the dataset.
     *
     * @return The date and time for the content.
     */
    @UML(identifier="extent", obligation=MANDATORY, specification=ISO_19108)
    TemporalPrimitive getExtent();
}
