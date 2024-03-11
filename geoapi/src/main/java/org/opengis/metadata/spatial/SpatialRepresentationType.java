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
 * Method used to represent geographic information in the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Vocabulary(capacity=6)
@UML(identifier="MD_SpatialRepresentationTypeCode", specification=ISO_19115)
public final class SpatialRepresentationType extends CodeList<SpatialRepresentationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 4790487150664264363L;

    /**
     * Vector data is used to represent geographic data.
     */
    @UML(identifier="vector", obligation=CONDITIONAL, specification=ISO_19115)
    public static final SpatialRepresentationType VECTOR = new SpatialRepresentationType("VECTOR");

    /**
     * Grid data is used to represent geographic data.
     */
    @UML(identifier="grid", obligation=CONDITIONAL, specification=ISO_19115)
    public static final SpatialRepresentationType GRID = new SpatialRepresentationType("GRID");

    /**
     * Textual or tabular data is used to represent geographic data.
     */
    @UML(identifier="textTable", obligation=CONDITIONAL, specification=ISO_19115)
    public static final SpatialRepresentationType TEXT_TABLE = new SpatialRepresentationType("TEXT_TABLE");

    /**
     * Triangulated irregular network.
     */
    @UML(identifier="tin", obligation=CONDITIONAL, specification=ISO_19115)
    public static final SpatialRepresentationType TIN = new SpatialRepresentationType("TIN");

    /**
     * Three-dimensional view formed by the intersecting homologous rays of an overlapping pair of images.
     */
    @UML(identifier="stereoModel", obligation=CONDITIONAL, specification=ISO_19115)
    public static final SpatialRepresentationType STEREO_MODEL = new SpatialRepresentationType("STEREO_MODEL");

    /**
     * Scene from a video recording.
     */
    @UML(identifier="video", obligation=CONDITIONAL, specification=ISO_19115)
    public static final SpatialRepresentationType VIDEO = new SpatialRepresentationType("VIDEO");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private SpatialRepresentationType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code SpatialRepresentationType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SpatialRepresentationType[] values() {
        return values(SpatialRepresentationType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public SpatialRepresentationType[] family() {
        return values();
    }

    /**
     * Returns the spatial representation type that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SpatialRepresentationType valueOf(String code) {
        return valueOf(SpatialRepresentationType.class, code, SpatialRepresentationType::new).get();
    }
}
