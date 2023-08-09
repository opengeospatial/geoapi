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
package org.opengis.util;

import org.opengis.geoapi.internal.Errors;


/**
 * Thrown when a factory does not implement a method.
 * The unimplemented service may be the creation of instances of some type,
 * or the parsing of texts in <i>Well-Known Text</i> (WKT)
 * or <i>Geographic Markup Language</i> (GML) formats.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   3.1
 */
public class UnimplementedServiceException extends FactoryException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 7527916609849976921L;

    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public UnimplementedServiceException(final String message) {
        super(message);
    }

    /**
     * Constructs an exception with a message inferred from the specified factory and object type.
     *
     * @param  factory  the factory on which a {@code createFoo(…)} method has been invoked.
     * @param  type     the type of object that the user requested.
     */
    public UnimplementedServiceException(final Factory factory, final Class<?> type) {
        super(Errors.cannotCreate(factory, type, null));
    }

    /**
     * Constructs an exception with a message inferred from the specified factory, object type and variant.
     * The exact message formatted by this constructor is unspecified.
     *
     * @param  factory  the factory on which a {@code createFoo(…)} method has been invoked.
     * @param  type     the type of object that the user requested.
     * @param  variant  variant of the type (e.g. "2D", "3D", "spherical", "Cartesian"), or {@code null} if none.
     */
    public UnimplementedServiceException(final Factory factory, final Class<?> type, final String variant) {
        super(Errors.cannotCreate(factory, type, variant));
    }
}
