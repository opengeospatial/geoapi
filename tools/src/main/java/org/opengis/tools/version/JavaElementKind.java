/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;


/**
 * The kind of a Java element (interface, field, method).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
enum JavaElementKind {
    /**
     * The element is a package.
     */
    PACKAGE("Package", false),

    /**
     * The type is an enumeration.
     */
    ENUM("Enumeration", false),

    /**
     * The type is a code list.
     */
    CODE_LIST("Code list", false),

    /**
     * The element is type other than an enumeration, a code list or an interface.
     */
    CLASS("Classe", false),

    /**
     * The element is an interface.
     */
    INTERFACE("Interface", false),

    /**
     * The type is a field. This normally occur only in enumeration or code lists.
     */
    FIELD("Field", true),

    /**
     * The type is a constructor.
     */
    CONSTRUCTOR("Constructor", true),

    /**
     * The type is a method.
     */
    METHOD("Method", true);

    /**
     * The title to display in the HTML page.
     */
    final String label;

    /**
     * {@code true} if the element is a field or a method, or {@code false} otherwise
     * (interface, class, enumeration, package).
     */
    final boolean isMember;

    /**
     * Creates a new enum with the given title to display in the HTML page.
     */
    private JavaElementKind(final String label, final boolean isMember) {
        this.label    = label;
        this.isMember = isMember;
    }
}
