/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
 * {@linkplain CoordinateSystemAxis#getMinimumValue() minimum value} and
 * {@linkplain CoordinateSystemAxis#getMaximumValue() maximum value}.
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
    private static final List<RangeMeaning> VALUES = new ArrayList<>(2);

    /**
     * Any value between and including {@linkplain CoordinateSystemAxis#getMinimumValue() minimum value}
     * and {@linkplain CoordinateSystemAxis#getMaximumValue() maximum value} is valid.
     */
    @UML(identifier="exact", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RangeMeaning EXACT = new RangeMeaning("EXACT");

    /**
     * The axis is continuous with values wrapping around at the
     * {@linkplain CoordinateSystemAxis#getMinimumValue() minimum value} and
     * {@linkplain CoordinateSystemAxis#getMaximumValue() maximum value}.
     * Values with the same meaning repeat modulo the difference between maximum value and
     * minimum value.
     *
     * <div class="note"><b>Example:</b> in a geographic CRS, longitude values are
     * often defined with a finite extent (e.g., from -180 degrees to +180 degrees). The minimum
     * and maximum longitude limits define a single line (on the ellipsoid, sphere, or cylinder),
     * known as the anti-meridian, across which longitude values are discontinuous: as this line
     * is crossed, longitude changes abruptly (e.g., going West from a little more than -180° to
     * a little less than +180°).</div>
     */
    @UML(identifier="wraparound", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RangeMeaning WRAPAROUND = new RangeMeaning("WRAPAROUND");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private RangeMeaning(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code RangeMeaning}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static RangeMeaning[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new RangeMeaning[VALUES.size()]);
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
    public RangeMeaning[] family() {
        return values();
    }

    /**
     * Returns the range meaning that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static RangeMeaning valueOf(String code) {
        return valueOf(RangeMeaning.class, code);
    }
}
