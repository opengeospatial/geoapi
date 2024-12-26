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
package org.opengis.metadata.acquisition;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geometric description of the collection.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@Vocabulary(capacity=4)
@UML(identifier="MI_GeometryTypeCode", specification=ISO_19115_2)
public final class GeometryType extends CodeList<GeometryType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8326145457020825352L;

    /**
     * Single geographic point of interest.
     */
    @UML(identifier="point", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType POINT = new GeometryType("POINT");

    /**
     * Extended collection in a single vector.
     */
    @UML(identifier="linear", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType LINEAR = new GeometryType("LINEAR");

    /**
     * Collection of a geographic area defined by a polygon (coverage).
     */
    @UML(identifier="areal", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType AREAL = new GeometryType("AREAL");

    /**
     * Series of linear collections grouped by way points.
     */
    @UML(identifier="strip", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType STRIP = new GeometryType("STRIP");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private GeometryType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code GeometryType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static GeometryType[] values() {
        return values(GeometryType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public GeometryType[] family() {
        return values();
    }

    /**
     * Returns the geometry type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static GeometryType valueOf(String code) {
        return valueOf(GeometryType.class, code, GeometryType::new).get();
    }
}
