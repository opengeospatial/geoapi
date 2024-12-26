/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2021-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Nature of the spatial operation computed with geometries and a distance parameter.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=2)
@UML(identifier="DistanceOperatorName", specification=ISO_19143)
public final class DistanceOperatorName extends CodeList<DistanceOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6843540817045459049L;

    /**
     * Operator evaluates to {@code true} when all of a feature's geometry lies beyond
     * (is more distant) than a given distance from the filter geometry.
     * More specifically:
     *
     * <blockquote>{@literal Beyond(A,B,d) = Distance(A,B) > d}</blockquote>
     */
    @UML(identifier="Beyond", obligation=CONDITIONAL, specification=ISO_19143)
    public static final DistanceOperatorName BEYOND = new DistanceOperatorName("BEYOND");

    /**
     * Operator evaluates to {@code true} when any part of the first geometry
     * lies within the given distance of the second geometry.
     * More specifically:
     *
     * <blockquote>{@literal Within(A,B,d) = Distance(A,B) < d}</blockquote>
     */
    @UML(identifier="DWithin", obligation=CONDITIONAL, specification=ISO_19143)
    public static final DistanceOperatorName WITHIN = new DistanceOperatorName("WITHIN");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private DistanceOperatorName(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code DistanceOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static DistanceOperatorName[] values() {
        return values(DistanceOperatorName.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public DistanceOperatorName[] family() {
        return values();
    }

    /**
     * Returns the distance operator that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static DistanceOperatorName valueOf(String code) {
        return valueOf(DistanceOperatorName.class, code, DistanceOperatorName::new).get();
    }
}
