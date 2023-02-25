/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

import org.opengis.util.GenericName;


/**
 * An instance of a {@link PropertyType}.
 * A property is usually part of another entity such as a {@link Feature}.
 * This interface is the parent type of {@linkplain Attribute attribute} and
 * {@linkplain FeatureAssociation feature association} but not feature.
 *
 * <p>A property is a wrapper around an arbitrary object or value.
 * It provides the following information:</p>
 *
 * <ul>
 *   <li>A value, available via the {@link #getValue()} method.
 *       The value can be set via a setter method provided by the sub-interface.</li>
 *   <li>A type, available via the {@code getType()} or {@code getRole()} method provided by the sub-interface.
 *       The {@link PropertyType} defines information about the property.
 *       This includes which Java class the value of the property is an instance of, any restrictions on
 *       the value, <i>etc</i>.</li>
 * </ul>
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public interface Property {
    /**
     * Returns the name of this property.
     * This is a convenience method for {@code getType().getName()} or
     * {@code getRole().getName()}, depending on the sub-interface.
     *
     * @return name of this property.
     */
    GenericName getName();

    /**
     * Returns the value or content of the property, or {@code null} if none.
     * <ul>
     *   <li>If this property is an {@link Attribute}, then the returned object may be an instance of any Java class
     *       assignable to {@link AttributeType#getValueClass()}.</li>
     *   <li>If this property is an {@link FeatureAssociation}, then the returned object is a {@link Feature}.</li>
     * </ul>
     *
     * @return the value of the property, or {@code null} if none.
     */
    Object getValue();
}
