/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Methods used to group similar keywords.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_KeywordTypeCode", specification=ISO_19115)
public final class KeywordType extends CodeList<KeywordType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268565235927L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<KeywordType> VALUES = new ArrayList<KeywordType>(5);

    /**
     * Keyword identifies a branch of instruction or specialized learning.
     */
    @UML(identifier="discipline", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType DISCIPLINE = new KeywordType("DISCIPLINE");

    /**
     * Keyword identifies a location.
     */
    @UML(identifier="place", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PLACE = new KeywordType("PLACE");

    /**
     * Keyword identifies the layer(s) of any deposited substance.
     */
    @UML(identifier="stratum", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType STRATUM = new KeywordType("STRATUM");

    /**
     * Keyword identifies a time period related to the dataset.
     */
    @UML(identifier="temporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType TEMPORAL = new KeywordType("TEMPORAL");

    /**
     * Keyword identifies a particular subject or topic.
     */
    @UML(identifier="theme", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType THEME = new KeywordType("THEME");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private KeywordType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code KeywordType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static KeywordType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new KeywordType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public KeywordType[] family() {
        return values();
    }

    /**
     * Returns the KeywordType that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static KeywordType valueOf(String code) {
        return valueOf(KeywordType.class, code);
    }
}
