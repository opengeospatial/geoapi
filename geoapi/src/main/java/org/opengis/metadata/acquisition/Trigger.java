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
 * Mechanism of activation.
 *
 * @author  Cédric Briançon (Geomatys)
 * @since   2.3
 * @version 3.0
 */
@UML(identifier="MI_TriggerCode", specification=ISO_19115_2)
public final class Trigger extends CodeList<Trigger> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 649620597963351153L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Trigger> VALUES = new ArrayList<Trigger>(3);

    /**
     * Event due to external stimuli.
     */
    @UML(identifier="automatic", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Trigger AUTOMATIC = new Trigger("AUTOMATIC");

    /**
     * Event manually instigated.
     */
    @UML(identifier="manual", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Trigger MANUAL = new Trigger("MANUAL");

    /**
     * Event instigated by planned internal stimuli.
     */
    @UML(identifier="preProgrammed", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Trigger PRE_PROGRAMMED = new Trigger("PRE_PROGRAMMED");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by another enum of this type.
     */
    private Trigger(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Trigger}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static Trigger[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Trigger[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind as this enum.
     */
    public Trigger[] family() {
        return values();
    }

    /**
     * Returns the trigger that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static Trigger valueOf(String code) {
        return valueOf(Trigger.class, code);
    }
}
