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
package org.opengis.parameter;

import java.util.Optional;
import org.opengis.annotation.UML;
import org.opengis.util.ControlledVocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Class of information to which the referencing entity applies.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_ParameterDirection", specification=ISO_19115)
public enum ParameterDirection implements ControlledVocabulary {
    /**
     * The parameter is an input parameter to the service instance.
     */
    @UML(identifier="in", obligation=CONDITIONAL, specification=ISO_19115)
    IN("in"),

    /**
     * The parameter is an output parameter to the service instance.
     */
    @UML(identifier="out", obligation=CONDITIONAL, specification=ISO_19115)
    OUT("out"),

    /**
     * The parameter is both an input and output parameter to the service instance.
     */
    @UML(identifier="in/out", obligation=CONDITIONAL, specification=ISO_19115)
    IN_OUT("in/out");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     *
     * @param identifier  the UML identifier.
     */
    private ParameterDirection(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     *
     * @since 3.1
     */
    @Override
    public Optional<String> identifier() {
        return Optional.ofNullable(identifier);
    }

    /**
     * Returns all constants defined by this enumeration type.
     * Invoking this method is equivalent to invoking {@link #values()}, except that this
     * method can be invoked on an instance of the {@code ControlledVocabulary} interface
     * (i.e. the enumeration type does not need to be known at compile-time).
     *
     * @return all {@linkplain #values() values} for this enumeration.
     */
    @Override
    public ParameterDirection[] family() {
        return values();
    }
}
