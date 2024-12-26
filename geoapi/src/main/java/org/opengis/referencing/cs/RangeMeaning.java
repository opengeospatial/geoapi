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
package org.opengis.referencing.cs;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Meaning of the axis value range specified through minimum value and maximum value.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.1
 *
 * @see CoordinateSystemAxis#getMinimumValue()
 * @see CoordinateSystemAxis#getMaximumValue()
 * @see CoordinateSystemAxis#getRangeMeaning()
 */
@Vocabulary(capacity=2)
@UML(identifier="RangeMeaning", specification=ISO_19111)
public final class RangeMeaning extends CodeList<RangeMeaning> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3525560558294789416L;

    /**
     * Any value between and including minimum and maximum value is valid.
     */
    @UML(identifier="exact", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RangeMeaning EXACT = new RangeMeaning("EXACT");

    /**
     * The axis is continuous with values wrapping around at the minimum and maximum value.
     * Values with the same meaning repeat modulo the difference between maximum value and
     * minimum value.
     *
     * <h4>Example</h4>
     * In a geographic <abbr>CRS</abbr>, longitude values are often defined with a finite extent
     * (e.g., from -180 degrees to +180 degrees). The minimum and maximum longitude limits define
     * a single line (on the ellipsoid, sphere, or cylinder), known as the anti-meridian,
     * across which longitude values are discontinuous:
     * as this line is crossed, longitude changes abruptly
     * (e.g., going West from a little more than -180° to a little less than +180°).
     */
    @UML(identifier="wraparound", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RangeMeaning WRAPAROUND = new RangeMeaning("WRAPAROUND");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private RangeMeaning(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code RangeMeaning}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static RangeMeaning[] values() {
        return values(RangeMeaning.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
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
     * Returns the range meaning that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static RangeMeaning valueOf(String code) {
        return valueOf(RangeMeaning.class, code, RangeMeaning::new).get();
    }
}
