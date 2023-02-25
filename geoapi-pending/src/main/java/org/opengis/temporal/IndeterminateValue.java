/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;

/**
 * Provides 4 values for indeterminate positions.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (IRD)
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_IndeterminateValue", specification=ISO_19108)
public final class IndeterminateValue extends CodeList<IndeterminateValue> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 1399031922917754577L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<IndeterminateValue> VALUES = new ArrayList<IndeterminateValue>(4);

    /**
     * Shall be used with the parent class {@link TemporalPosition} to indicate that
     * not specific value for temporal position is provided.
     */
    public static final IndeterminateValue UNKNOWN = new IndeterminateValue("UNKNOWN");

    /**
     * Shall be used with any subtype of {@link TemporalPosition} to indicate that
     * the specified value shall be replaced with the current temporal position whenever the value is accessed.
     */
    public static final IndeterminateValue NOW = new IndeterminateValue("NOW");

    /**
     * Shall be used with any subtype of {@link TemporalPosition} to indicate that the actual
     * temporal position is unknown, but it is known to be before the specified value.
     */
    public static final IndeterminateValue BEFORE = new IndeterminateValue("BEFORE");

    /**
     * Shall be used with any subtype of {@link TemporalPosition} to indicate that the actual
     * temporal position is unknown, but it is known to be after the specified value.
     */
    public static final IndeterminateValue AFTER = new IndeterminateValue("AFTER");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private IndeterminateValue(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code IndeterminateValue}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static IndeterminateValue[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new IndeterminateValue[VALUES.size()]);
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
    public IndeterminateValue[] family() {
        return values();
    }

    /**
     * Returns the indeterminate value that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static IndeterminateValue valueOf(String code) {
        return valueOf(IndeterminateValue.class, code);
    }
}
