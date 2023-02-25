/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Thrown if a filter or an expression cannot be evaluated for a given parameter.
 * It may be because an expression contains a reference to a value which is not defined
 * in an instance being evaluated.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see Filter#test(Object)
 * @see Expression#apply(Object)
 *
 * @since 3.1
 */
@UML(identifier="InvalidParameterValue", specification=ISO_19143)
public class InvalidFilterValueException extends IllegalArgumentException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 741289651819871347L;

    /**
     * Creates an exception with no message.
     */
    public InvalidFilterValueException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public InvalidFilterValueException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified cause.
     *
     * @param cause  the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public InvalidFilterValueException(final Throwable cause) {
        super(cause);
    }

    /**
     * Creates an exception with the specified message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public InvalidFilterValueException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
