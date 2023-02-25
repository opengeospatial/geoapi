/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.tools.apt;


/**
 * A few classes of special interest.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
enum Classes {
    /**
     * {@code "org.opengis.annotation.UML"}.
     */
    UML(UmlProcessor.UML_CLASSNAME, false),

    /**
     * {@code "org.opengis.util.CodeList"}.
     */
    CODE_LIST("org.opengis.util.CodeList", false),

    /**
     * {@code "org.opengis.parameter.ParameterValueGroup"}.
     */
    PARAMETER_VALUE_GROUP("org.opengis.parameter.ParameterValueGroup", false),

    /**
     * {@code "org.opengis.parameter.ParameterDescriptorGroup"}.
     */
    PARAMETER_DESCRIPTOR_GROUP("org.opengis.parameter.ParameterDescriptorGroup", false),

    /**
     * {@code "org.opengis.geometry.coordinate.PointGrid"}.
     */
    POINT_GRID("org.opengis.geometry.coordinate.PointGrid", true),

    /**
     * {@code "org.opengis.geometry.coordinate.PointArray"}.
     */
    POINT_ARRAY("org.opengis.geometry.coordinate.PointArray", true),

    /**
     * {@code "java.util.Collection"}.
     */
    COLLECTION("java.util.Collection", false),

    /**
     * {@code "java.util.Map"}.
     */
    MAP("java.util.Map", false),

    /**
     * {@code "java.lang.Boolean"}.
     */
    BOOLEAN("java.lang.Boolean", false),

    /**
     * {@code "java.lang.Exception"}.
     */
    EXCEPTION("java.lang.Exception", false);

    /**
     * Returns types for which the name of the getter method should be in plural form.
     */
    static Classes[] MULTI_OCCURRENCE = {
        COLLECTION, MAP, POINT_GRID, POINT_ARRAY, PARAMETER_VALUE_GROUP, PARAMETER_DESCRIPTOR_GROUP
    };

    /**
     * The classname.
     */
    final String classname;

    /**
     * {@code true} for interfaces from the {@code geoapi-pending} module.
     * Those interfaces are optional rather than mandatory.
     */
    final boolean isPending;

    /**
     * Creates a new enumeration for the given class name.
     */
    private Classes(final String classname, final boolean isPending) {
        this.classname = classname;
        this.isPending = isPending;
    }
}
