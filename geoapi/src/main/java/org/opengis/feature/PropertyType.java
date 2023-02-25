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

import org.opengis.annotation.UML;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.Classifier;

import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Characteristics that may be associated with a {@link FeatureType}.
 * This interface is the parent type of {@linkplain AttributeType attribute type},
 * {@linkplain FeatureAssociationRole feature association role} and {@linkplain Operation operation},
 * but not feature type.
 *
 * <p>Property types typically define the following properties:</p>
 * <ul>
 *   <li>A name and a human-readable description.</li>
 *   <li>The type of values (in {@link AttributeType} and {@link FeatureAssociationRole}).</li>
 *   <li>Number of allowable occurrences of the value (in {@link AttributeType} and {@link FeatureAssociationRole}).</li>
 *   <li>A default value (in {@link AttributeType}).</li>
 * </ul>
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="PropertyType", specification=ISO_19109)
public interface PropertyType extends IdentifiedType {
}
