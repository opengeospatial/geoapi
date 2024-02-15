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
 * Temporal persistence of collection objective.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_ObjectiveTypeCode", specification=ISO_19115_2)
public final class ObjectiveType extends CodeList<ObjectiveType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4952647967684867284L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ObjectiveType> VALUES = new ArrayList<ObjectiveType>(3);

    /**
     * Single instance of collection.
     */
    @UML(identifier="instantaneousCollection", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final ObjectiveType INSTANTANEOUS_COLLECTION = new ObjectiveType("INSTANTANEOUS_COLLECTION");

    /**
     * Multiple instances of collection.
     */
    @UML(identifier="persistentView", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final ObjectiveType PERSISTENT_VIEW = new ObjectiveType("PERSISTENT_VIEW");

    /**
     * Collection over specified domain.
     */
    @UML(identifier="survey", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final ObjectiveType SURVEY = new ObjectiveType("SURVEY");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by another enum of this type.
     */
    private ObjectiveType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ObjectiveType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static ObjectiveType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ObjectiveType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind as this enum.
     */
    public ObjectiveType[] family() {
        return values();
    }

    /**
     * Returns the objective type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static ObjectiveType valueOf(String code) {
        return valueOf(ObjectiveType.class, code);
    }
}
