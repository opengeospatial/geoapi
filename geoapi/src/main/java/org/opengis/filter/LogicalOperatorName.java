/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2021-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Nature of the logic operation between two or more filters.
 * The standard set of comparison operators is {@code AND}, {@code OR} and {@code NOT}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=3)
@UML(identifier="BinaryLogicType, UnaryLogicType", specification=ISO_19143)
public final class LogicalOperatorName extends CodeList<LogicalOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5675380369617820169L;

    /**
     * Operator evaluates to {@code true} if all the combined filters evaluate to {@code true}.
     */
    @UML(identifier="And", obligation=CONDITIONAL, specification=ISO_19143)
    public static final LogicalOperatorName AND = new LogicalOperatorName("AND");

    /**
     * Operator evaluates to {@code true} if any of the combined filters evaluate to {@code true}.
     */
    @UML(identifier="Or", obligation=CONDITIONAL, specification=ISO_19143)
    public static final LogicalOperatorName OR = new LogicalOperatorName("OR");

    /**
     * Operator reverses the logical value of a filter.
     */
    @UML(identifier="Not", obligation=CONDITIONAL, specification=ISO_19143)
    public static final LogicalOperatorName NOT = new LogicalOperatorName("NOT");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private LogicalOperatorName(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code LogicalOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static LogicalOperatorName[] values() {
        return values(LogicalOperatorName.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public LogicalOperatorName[] family() {
        return values();
    }

    /**
     * Returns the binary logic type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static LogicalOperatorName valueOf(String code) {
        return valueOf(LogicalOperatorName.class, code, LogicalOperatorName::new).get();
    }
}
