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
package org.opengis.temporal;

import java.time.DateTimeException;


/**
 * Thrown when a temporal operation cannot be performed because a position has an indeterminate value.
 *
 * <h2>Example</h2>
 * This exception should be thrown by {@link Instant#distance(TemporalPrimitive)} if
 * {@link Instant#getIndeterminatePosition()} returns a non-empty value different than
 * {@link IndeterminateValue#NOW}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public class IndeterminatePositionException extends DateTimeException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -7483311297081181904L;

    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public IndeterminatePositionException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with the specified detail message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public IndeterminatePositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
