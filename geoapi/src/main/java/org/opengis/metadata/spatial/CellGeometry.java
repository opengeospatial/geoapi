/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Code indicating whether grid data is point or area.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Vocabulary(capacity=4)
@UML(identifier="MD_CellGeometryCode", specification=ISO_19115)
public final class CellGeometry extends CodeList<CellGeometry> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -1901029875497457189L;

    /**
     * Each cell represents a point.
     */
    @UML(identifier="point", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CellGeometry POINT = new CellGeometry("POINT");

    /**
     * Each cell represents an area.
     */
    @UML(identifier="area", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CellGeometry AREA = new CellGeometry("AREA");

    /**
     * Each cell represents a volumetric measurement on a regular grid in three dimensional space.
     *
     * @since 3.1
     */
    @UML(identifier="voxel", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CellGeometry VOXEL = new CellGeometry("VOXEL");

    /**
     * Height range for a single point vertical profile.
     *
     * @since 3.1
     */
    @UML(identifier="stratum", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CellGeometry STRATUM = new CellGeometry("STRATUM");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private CellGeometry(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code CellGeometry}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CellGeometry[] values() {
        return values(CellGeometry.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public CellGeometry[] family() {
        return values();
    }

    /**
     * Returns the cell geometry that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CellGeometry valueOf(String code) {
        return valueOf(CellGeometry.class, code, CellGeometry::new).get();
    }
}
