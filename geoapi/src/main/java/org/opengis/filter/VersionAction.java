/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.util.ControlledVocabulary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * The action to do for selecting a new version for an identified resource.
 * This is one of the possible values of {@link Version}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="VersionAction", specification=ISO_19143)
public enum VersionAction implements ControlledVocabulary {
    /**
     * Filter selects the first version of a resource.
     */
    @UML(identifier="first", obligation=CONDITIONAL, specification=ISO_19143)
    FIRST("first"),

    /**
     * Filter selects the most recent version of a resource.
     */
    @UML(identifier="last", obligation=CONDITIONAL, specification=ISO_19143)
    LAST("last"),

    /**
     * Filter selects the previous version of a resource relative to the version
     * specified using the {@code identifier} attribute.
     */
    @UML(identifier="previous", obligation=CONDITIONAL, specification=ISO_19143)
    PREVIOUS("previous"),

    /**
     * Filter selects the next version of a resource relative to the version
     * specified using the {@code identifier} attribute.
     */
    @UML(identifier="next", obligation=CONDITIONAL, specification=ISO_19143)
    NEXT("next"),

    /**
     * Filters selects all available versions of a resource.
     */
    @UML(identifier="all", obligation=CONDITIONAL, specification=ISO_19143)
    ALL("all");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     *
     * @param identifier  the UML identifier.
     */
    private VersionAction(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}.
     */
    @Override
    public String[] names() {
        return new String[] {name(), identifier};
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
    public VersionAction[] family() {
        return values();
    }
}
