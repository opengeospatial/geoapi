/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Ordered list of priorities.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@Vocabulary(capacity=4)
@UML(identifier="MI_PriorityCode", specification=ISO_19115_2)
public final class Priority extends CodeList<Priority> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3504801926504645861L;

    /**
     * Decisive importance.
     */
    @UML(identifier="critical", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Priority CRITICAL = new Priority("CRITICAL");

    /**
     * Requires resources to be made available.
     */
    @UML(identifier="highImportance", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Priority HIGH_IMPORTANCE = new Priority("HIGH_IMPORTANCE");

    /**
     * Normal operation priority.
     */
    @UML(identifier="mediumImportance", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Priority MEDIUM_IMPORTANCE = new Priority("MEDIUM_IMPORTANCE");

    /**
     * To be completed when resources are available
     */
    @UML(identifier="lowImportance", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Priority LOW_IMPORTANCE = new Priority("LOW_IMPORTANCE");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Priority(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code Priority}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Priority[] values() {
        return values(Priority.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Priority[] family() {
        return values();
    }

    /**
     * Returns the priority that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Priority valueOf(String code) {
        return valueOf(Priority.class, code, Priority::new).get();
    }
}
