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
 * Nature of the spatial operation between two geometries.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=9)
@UML(identifier="SpatialOperatorName", specification=ISO_19143)
public final class SpatialOperatorName extends CodeList<SpatialOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5467041303033565459L;

    /**
     * Operator evaluates to {@code true} when the bounding box of the feature's geometry
     * interacts with the bounding box provided in the filter properties.
     * The geometry is given by the first expression and the bounding box
     * is the envelope given by the second expression.
     *
     * <p>The {@code BBOX} operation is defined as a convenient and more compact way of
     * expressing the very common bounding box constraint based on the envelope geometry.
     * It is equivalent to the spatial operation <code>NOT {@linkplain #DISJOINT}</code>
     * meaning that the {@code BBOX} operator identifies all geometries that spatially
     * interact with the box.</p>
     *
     * <p>An implementation may choose to throw an exception if one attempts to test
     * features that are in a different SRS than the SRS contained in the filter.</p>
     */
    @UML(identifier="BBOX", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName BBOX = new SpatialOperatorName("BBOX");

    /**
     * Operator evaluates to {@code true} if the geometry of the two operands are equal.
     */
    @UML(identifier="Equals", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName EQUALS = new SpatialOperatorName("EQUALS");

    /**
     * Operator evaluates to {@code true} if the first operand is disjoint from the second.
     * "Disjoint" shall be interpreted in the sense defined in the OGC Simple Features specification.
     */
    @UML(identifier="Disjoint", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName DISJOINT = new SpatialOperatorName("DISJOINT");

    /**
     * Operator evaluates to {@code true} if the two geometric operands intersect.
     */
    @UML(identifier="Intersects", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName INTERSECTS = new SpatialOperatorName("INTERSECTS");

    /**
     * Operator evaluates to {@code true} if the two geometric operands touch, but do not overlap.
     */
    @UML(identifier="Touches", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName TOUCHES = new SpatialOperatorName("TOUCHES");

    /**
     * Operator evaluates to {@code true} if the first geometric operand crosses the second.
     * "Cross" shall be interpreted in the sense defined by the OGC Simple Features specification.
     */
    @UML(identifier="Crosses", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName CROSSES = new SpatialOperatorName("CROSSES");

    /**
     * Operator evaluates to {@code true} if the first geometric operand is completely
     * contained by the constant geometric operand.
     */
    @UML(identifier="Within", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName WITHIN = new SpatialOperatorName("WITHIN");

    /**
     * Operator evaluates to {@code true} if the first geometric operand contains the second.
     */
    @UML(identifier="Contains", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName CONTAINS = new SpatialOperatorName("CONTAINS");

    /**
     * Operator evaluates to {@code true} if the interior of the first geometric operand
     * somewhere overlaps the interior of the second geometric operand.
     */
    @UML(identifier="Overlaps", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName OVERLAPS = new SpatialOperatorName("OVERLAPS");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private SpatialOperatorName(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code SpatialOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SpatialOperatorName[] values() {
        return values(SpatialOperatorName.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public SpatialOperatorName[] family() {
        return values();
    }

    /**
     * Returns the spatial operator that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SpatialOperatorName valueOf(String code) {
        return valueOf(SpatialOperatorName.class, code, SpatialOperatorName::new).get();
    }
}
