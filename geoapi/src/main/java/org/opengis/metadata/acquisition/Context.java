/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
    private static final List<Context> VALUES = new ArrayList<Context>(3);

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
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private Context(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Context}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static Context[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Context[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public Context[] family() {
        return values();
    }

    /**
     * Returns the context that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static Context valueOf(String code) {
        return valueOf(Context.class, code);
    }
}
