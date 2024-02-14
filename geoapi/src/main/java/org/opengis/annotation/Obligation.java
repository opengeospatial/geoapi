/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import org.opengis.util.ControlledVocabulary;

import static org.opengis.annotation.Specification.*;


/**
 * Whether an element is mandatory, optional or have other obligation.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public enum Obligation implements ControlledVocabulary {
    /*
     * Implementation note: Enum or CodeList elements are usually declared with
     * Obligation.CONDITIONAL. However, such declaration in the Obligation enum
     * causes a recursive dependency. Some compilers (like Oracle javac) accept
     * this recursive dependency while some other (Eclipse, Scala...) reject it.
     * For better portability, we have to omit the Obligation declarations here.
     */

    /**
     * Element is always required.
     */
    @UML(identifier="mandatory", specification=ISO_19115)
    MANDATORY("mandatory"),

    /**
     * Element is not required.
     */
    @UML(identifier="optional", specification=ISO_19115)
    OPTIONAL("optional"),

    /**
     * Element is required when a specific condition is met.
     */
    @UML(identifier="conditional", specification=ISO_19115)
    CONDITIONAL("conditional"),

    /**
     * The element should always be {@code null}. This obligation code is used only when
     * a sub-interface overrides an association and force it to a {@code null} value.
     * An example is {@link org.opengis.referencing.datum.TemporalDatum#getAnchorPoint()}.
     *
     * @departure constraint
     *   ISO specifications sometimes override a parent method with a comment saying that the method
     *   is not allowed for a particular class. Since there is no construct in Java for expressing
     *   this constraint in the method signature, GeoAPI defines a {@code FORBIDDEN} obligation
     *   (not in original ISO specifications) to be used with the {@code @UML} annotation and
     *   which adds a flag in the Java documentation.
     */
    FORBIDDEN(null);

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     *
     * @param identifier  the UML identifier.
     */
    private Obligation(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant, or {@code null} if none.
     *
     * @since 3.1
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}, if any.
     *
     * @since 3.1
     */
    @Override
    public String[] names() {
        if (identifier != null) {
            return new String[] {name(), identifier};
        } else {
            return new String[] {name()};
        }
    }

    /**
     * Returns all constants defined by this enumeration type.
     * Invoking this method is equivalent to invoking {@link #values()}, except that this
     * method can be invoked on an instance of the {@code ControlledVocabulary} interface
     * (i.e. the enumeration type does not need to be known at compile-time).
     *
     * @return all {@linkplain #values() values} for this enumeration.
     *
     * @since 3.1
     */
    @Override
    public Obligation[] family() {
        return values();
    }
}
