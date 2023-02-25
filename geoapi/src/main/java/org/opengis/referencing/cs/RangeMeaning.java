/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Meaning of the axis value range specified through
 * {@linkplain CoordinateSystemAxis#getMinimumValue minimum value} and
 * {@linkplain CoordinateSystemAxis#getMaximumValue maximum value}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @see CoordinateSystemAxis#getRangeMeaning()
 */
@UML(identifier="CS_RangeMeaning", specification=ISO_19111)
public final class RangeMeaning extends CodeList<RangeMeaning> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3525560558294789416L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<RangeMeaning> VALUES = new ArrayList<RangeMeaning>(2);

    /**
     * Any value between and including {@linkplain CoordinateSystemAxis#getMinimumValue minimum value}
     * and {@linkplain CoordinateSystemAxis#getMaximumValue maximum value} is valid.
     */
    @UML(identifier="exact", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RangeMeaning EXACT = new RangeMeaning("EXACT");

    /**
     * The axis is continuous with values wrapping around at the
     * {@linkplain CoordinateSystemAxis#getMinimumValue minimum value} and
     * {@linkplain CoordinateSystemAxis#getMaximumValue maximum value}.
     * Values with the same meaning repeat modulo the difference between maximum value and
     * minimum value.
     */
    @UML(identifier="wraparound", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RangeMeaning WRAPAROUND = new RangeMeaning("WRAPAROUND");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private RangeMeaning(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code RangeMeaning}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static RangeMeaning[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new RangeMeaning[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public RangeMeaning[] family() {
        return values();
    }

    /**
     * Returns the range meaning that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static RangeMeaning valueOf(String code) {
        return valueOf(RangeMeaning.class, code);
    }
}
