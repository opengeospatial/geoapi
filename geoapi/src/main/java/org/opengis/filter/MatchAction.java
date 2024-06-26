/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.Optional;
import org.opengis.util.ControlledVocabulary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Specifies how the comparison predicate shall be evaluated for a collection of values.
 * This enumeration does not specify which specific value from the collection is tested.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MatchAction", specification=ISO_19143)
public enum MatchAction implements ControlledVocabulary {
    /**
     * Any of the value in the collection can satisfy the predicate.
     * This is the default value.
     */
    @UML(identifier="any", obligation=CONDITIONAL, specification=ISO_19143)
    ANY("any"),

    /**
     * All values in the collection shall satisfy the predicate.
     */
    @UML(identifier="all", obligation=CONDITIONAL, specification=ISO_19143)
    ALL("all"),

    /**
     * One of the values in the collection shall satisfy the predicate.
     * Additional context may specify which value in the collection should satisfy the predicate.
     * For example, the index may be a parameter in an expression.
     */
    @UML(identifier="one", obligation=CONDITIONAL, specification=ISO_19143)
    ONE("one");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     *
     * @param  identifier  the UML identifier.
     */
    private MatchAction(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     */
    @Override
    public Optional<String> identifier() {
        return Optional.of(identifier);
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
    public MatchAction[] family() {
        return values();
    }
}
