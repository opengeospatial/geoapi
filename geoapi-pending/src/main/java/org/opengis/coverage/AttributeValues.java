/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.coverage;

import org.opengis.util.Record;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents an element from the range of the {@linkplain Coverage coverage}.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @see Coverage#getRangeElements
 */
@UML(identifier="CV_AttributeValues", specification=ISO_19123)
public interface AttributeValues {
    /**
     * Returns a record containing one value for each attribute, as specified in the
     * {@linkplain Coverage#getRangeType coverage's range type}.
     * <p>
     * <B>Examples:</B>
     * <ul>
     *   <li>A coverage with a single (scalar) value (such as elevation).</li>
     *   <li>A coverage with a series (array / tensor) of values all defined in the same way
     *       (such as brightness values in different parts of the electromagnetic spectrum).</li>
     * </ul>
     *
     * @return the value for each attribute.
     */
    @UML(identifier="values", obligation=MANDATORY, specification=ISO_19123)
    Record getValues();
}
