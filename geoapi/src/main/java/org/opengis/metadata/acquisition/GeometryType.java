/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geometric description of the collection.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_GeometryTypeCode", specification=ISO_19115_2)
public final class GeometryType extends CodeList<GeometryType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8326145457020825352L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<GeometryType> VALUES = new ArrayList<GeometryType>(4);

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
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private GeometryType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code GeometryType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static GeometryType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new GeometryType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public GeometryType[] family() {
        return values();
    }

    /**
     * Returns the geometry type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static GeometryType valueOf(String code) {
        return valueOf(GeometryType.class, code);
    }
}
