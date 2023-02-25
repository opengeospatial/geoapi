/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designation of criterion for defining the context of the scanning process event.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_ContextCode", specification=ISO_19115_2)
public final class Context extends CodeList<Context> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 1723020399396010030L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Context> VALUES = new ArrayList<>(3);

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
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Context(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Context}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Context[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Context[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
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
     * Returns the context that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Context valueOf(String code) {
        return valueOf(Context.class, code);
    }
}
