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
package org.opengis.feature;

import org.opengis.annotation.UML;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.Classifier;

import static org.opengis.annotation.Specification.OGC_MOVING_FEATURE;


/**
 * Definition of a dynamic attribute in a moving feature type.
 * Dynamic attributes describe attributes of a feature in which the values vary with time and/or location.
 * A {@link Feature} having dynamic attributes is a moving feature.
 * A moving feature can have an arbitrary number of time-varying attributes,
 * such as the velocity of vehicles or the wind speed of hurricanes.
 *
 * <p>Dynamic attributes are used for non-spatial attribute.
 * If the value type of a dynamic attribute is geometric point, it is a trajectory.</p>
 *
 * @param <V> the type of attribute values.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see DynamicAttribute
 * @see AttributeType
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="DynamicAttributeType", specification=OGC_MOVING_FEATURE)
public interface DynamicAttributeType<V> extends AttributeType<V> {
    /**
     * Creates a new attribute instance of this type.
     *
     * @return a new dynamic attribute instance.
     * @throws UnsupportedOperationException if this type does not support new instance creation.
     */
    @Override
    DynamicAttribute<V> newInstance() throws UnsupportedOperationException;
}
