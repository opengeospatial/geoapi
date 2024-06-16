/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Specification.ISO_19108;
import static org.opengis.annotation.Obligation.CONDITIONAL;


/**
 * Indications that a temporal position is not precisely known.
 *
 * @author  ISO 19108 (for abstract model and documentation)
 * @author  Stephane Fellah (Image Matters)
 * @author  Alexander Petkov
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @since   3.1
 * @version 3.1
 */
@Vocabulary(capacity=4)
@UML(identifier="TM_IndeterminateValue", specification=ISO_19108)
public final class IndeterminateValue extends CodeList<IndeterminateValue> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 1399031922917754577L;

    /**
     * Indicates that no specific value for temporal position is provided.
     */
    @UML(identifier="unknown", obligation=CONDITIONAL, specification=ISO_19108)
    public static final IndeterminateValue UNKNOWN = new IndeterminateValue("UNKNOWN");

    /**
     * Indicates that the specified value shall be replaced with the
     * current temporal position whenever the value is accessed.
     */
    @UML(identifier="now", obligation=CONDITIONAL, specification=ISO_19108)
    public static final IndeterminateValue NOW = new IndeterminateValue("NOW");

    /**
     * Indicate that the actual temporal position is unknown, but it is known to be before the specified value.
     */
    @UML(identifier="before", obligation=CONDITIONAL, specification=ISO_19108)
    public static final IndeterminateValue BEFORE = new IndeterminateValue("BEFORE");

    /**
     * Indicates that the actual temporal position is unknown, but it is known to be after the specified value.
     */
    @UML(identifier="after", obligation=CONDITIONAL, specification=ISO_19108)
    public static final IndeterminateValue AFTER = new IndeterminateValue("AFTER");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private IndeterminateValue(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code IndeterminateValue}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static IndeterminateValue[] values() {
        return values(IndeterminateValue.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public IndeterminateValue[] family() {
        return values();
    }

    /**
     * Returns the indeterminate value that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static IndeterminateValue valueOf(String code) {
        return valueOf(IndeterminateValue.class, code, IndeterminateValue::new).get();
    }
}
