/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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

import java.util.Optional;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Identification and description information inherited by property types and feature types.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="IdentifiedType", specification=ISO_19109)
public interface IdentifiedType {
    /**
     * Returns the name of this type. The namespace can be either explicit
     * ({@link org.opengis.util.ScopedName}) or implicit
     * ({@link org.opengis.util.LocalName}).
     *
     * The name is optional for {@link Operation}, but mandatory for other types.
     * For {@link AttributeType}, the name shall be unique in the {@code FeatureType}.
     * For {@link FeatureType}, the name shall be unique in the unit processing the data.
     *
     * @return the type name, or {@code null} if none.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19109)
    GenericName getName();

    /**
     * Returns a concise definition of the element.
     *
     * @return concise definition of the element.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19109)
    InternationalString getDefinition();

    /**
     * Returns a natural language designator for the element.
     * This can be used as an alternative to the {@linkplain #getName() name} in user interfaces.
     *
     * @return natural language designator for the element.
     */
    @UML(identifier="designation", obligation=OPTIONAL, specification=ISO_19109)
    default Optional<InternationalString> getDesignation() {
        return Optional.empty();
    }

    /**
     * Returns optional information beyond that required for concise definition of the element.
     * The description may assist in understanding the element scope and application.
     *
     * @return information beyond that required for concise definition of the element.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19109)
    default Optional<InternationalString> getDescription() {
        return Optional.empty();
    }

    /*
     * ISO 19109 properties omitted for now:
     *
     *   - constrainedBy : CharacterString
     *
     * Rational: a CharacterString is hardly programmatically usable.
     * We may want for a {@code org.opengis.filter} package to be defined, and use it.
     */
}
