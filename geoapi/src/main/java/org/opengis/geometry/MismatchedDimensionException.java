/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry;


/**
 * Indicates that an operation cannot be completed properly because
 * of a mismatch in the dimensions of object attributes.
 *
 * @departure extension
 *   This exception is not part of the OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
public class MismatchedDimensionException extends IllegalArgumentException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 3138484331425225155L;

    /**
     * Creates an exception with no message.
     */
    public MismatchedDimensionException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     */
    public MismatchedDimensionException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified message and cause.
     *
     * @param  message The detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     * @param  cause The cause.
     */
    public MismatchedDimensionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
