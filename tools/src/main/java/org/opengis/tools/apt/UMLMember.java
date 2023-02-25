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
 * Members of the {@link org.opengis.annotation.UML} annotation.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
enum UMLMember {
    /**
     * {@code identifier()}.
     */
    IDENTIFIER("identifier"),

    /**
     * {@code specification()}.
     */
    SPECIFICATION("specification"),

    /**
     * {@code version()}.
     */
    VERSION("version");

    /**
     * The member name.
     */
    final String methodName;

    /**
     * Creates a new enumeration for the given member name.
     */
    private UMLMember(final String methodName) {
        this.methodName = methodName;
    }
}
