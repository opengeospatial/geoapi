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
 * Designation of criterion for defining the context of the scanning process event.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@Vocabulary(capacity=3)
@UML(identifier="MI_ContextCode", specification=ISO_19115_2)
public final class Context extends CodeList<Context> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 1723020399396010030L;

    /**
     * Event related to a specific collection.
     */
    @UML(identifier="acquisition", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Context ACQUISITION = new Context("ACQUISITION");

    /**
     * Event related to a sequence of collections.
     */
    @UML(identifier="pass", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Context PASS = new Context("PASS");

    /**
     * Event related to a navigational manoeuvre.
     */
    @UML(identifier="wayPoint", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Context WAY_POINT = new Context("WAY_POINT");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Context(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code Context}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Context[] values() {
        return values(Context.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Context[] family() {
        return values();
    }

    /**
     * Returns the context that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Context valueOf(String code) {
        return valueOf(Context.class, code, Context::new).get();
    }
}
