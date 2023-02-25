/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.bridge.python;

import java.lang.reflect.Type;


/**
 * Thrown when a Java object cannot be converted to a Python object.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
public class UnconvertibleTypeException extends ClassCastException {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 2952252051742016250L;

    /**
     * Creates a new exception with the given message.
     *
     * @param message  a description of the problem. Should contain at least the name of the unconvertible class.
     */
    public UnconvertibleTypeException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with a default message for the given type.
     *
     * @param type  the unconvertible class.
     */
    UnconvertibleTypeException(final Type type) {
        super("Cannot get converter to " + type);
    }
}
