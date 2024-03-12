/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Specification.*;


/**
 * Obligation of the element or entity.
 *
 * <div class="warning"><b>Upcoming API change — enumeration</b><br>
 * According ISO 19115, {@code Obligation} shall be an enumeration, not a code list.
 * Such enumeration already exists in the {@link org.opengis.annotation} package.
 * Consequently this {@code Obligation} class may be deleted in favor or {@code org.opengis.annotation.Obligation}
 * in GeoAPI 4.0. See <a href="http://jira.codehaus.org/browse/GEO-199">GEO-199</a> for more information.</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Vocabulary(capacity=3)
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public final class Obligation extends CodeList<Obligation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2135167450448770693L;

    /**
     * Element is always required.
     */
    @UML(identifier="mandatory", obligation=org.opengis.annotation.Obligation.CONDITIONAL, specification=ISO_19115)
    public static final Obligation MANDATORY = new Obligation("MANDATORY");

    /**
     * Element is not required.
     */
    @UML(identifier="optional", obligation=org.opengis.annotation.Obligation.CONDITIONAL, specification=ISO_19115)
    public static final Obligation OPTIONAL = new Obligation("OPTIONAL");

    /**
     * Element is required when a specific condition is met.
     */
    @UML(identifier="conditional", obligation=org.opengis.annotation.Obligation.CONDITIONAL, specification=ISO_19115)
    public static final Obligation CONDITIONAL = new Obligation("CONDITIONAL");

    /**
     * Constructs an element of the given name.
     *
     * @param name The name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private Obligation(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code Obligation}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static Obligation[] values() {
        return values(Obligation.class);
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return All code {@linkplain #values() values} for this code list.
     */
    @Override
    public Obligation[] family() {
        return values();
    }

    /**
     * Returns the obligation that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static Obligation valueOf(String code) {
        return valueOf(Obligation.class, code, Obligation::new).get();
    }
}
