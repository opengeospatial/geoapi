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
package org.opengis.metadata.spatial;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Name of point and vector spatial objects used to locate zero-, one-, and two-dimensional
 * spatial locations in the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_GeometricObjectTypeCode", specification=ISO_19115)
public final class GeometricObjectType extends CodeList<GeometricObjectType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8910485325021913980L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<GeometricObjectType> VALUES = new ArrayList<>(6);

    /**
     * Set of geometric primitives such that their boundaries can be represented as a
     * union of other primitives.
     */
    @UML(identifier="complex", obligation=CONDITIONAL, specification=ISO_19115)
    public static final GeometricObjectType COMPLEX = new GeometricObjectType("COMPLEX");

    /**
     * Connected set of curves, solids or surfaces.
     */
    @UML(identifier="composite", obligation=CONDITIONAL, specification=ISO_19115)
    public static final GeometricObjectType COMPOSITE = new GeometricObjectType("COMPOSITE");

    /**
     * Bounded, 1-dimensional geometric primitive, representing the continuous image of a line.
     */
    @UML(identifier="curve", obligation=CONDITIONAL, specification=ISO_19115)
    public static final GeometricObjectType CURVE = new GeometricObjectType("CURVE");

    /**
     * Zero-dimensional geometric primitive, representing a position but not having an extent.
     */
    @UML(identifier="point", obligation=CONDITIONAL, specification=ISO_19115)
    public static final GeometricObjectType POINT = new GeometricObjectType("POINT");

    /**
     * Bounded, connected 3-dimensional geometric primitive, representing the
     * continuous image of a region of space.
     */
    @UML(identifier="solid", obligation=CONDITIONAL, specification=ISO_19115)
    public static final GeometricObjectType SOLID = new GeometricObjectType("SOLID");

    /**
     * Bounded, connected 2-dimensional geometric, representing the continuous image
     * of a region of a plane.
     */
    @UML(identifier="surface", obligation=CONDITIONAL, specification=ISO_19115)
    public static final GeometricObjectType SURFACE = new GeometricObjectType("SURFACE");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private GeometricObjectType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code GeometricObjectType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static GeometricObjectType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(GeometricObjectType[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public GeometricObjectType[] family() {
        return values();
    }

    /**
     * Returns the geometric object type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static GeometricObjectType valueOf(String code) {
        return valueOf(GeometricObjectType.class, code);
    }
}
