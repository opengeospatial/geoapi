/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2016-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

import org.opengis.parameter.ParameterValueGroup;


/**
 * Thrown when {@link Operation#apply Operation.apply(…)} is invoked but the operation cannot complete.
 * The operation can fail for a variety of reasons including but not limited to:
 *
 * <ul>
 *   <li>I/O or SQL error while fetching data,</li>
 *   <li>failure to apply a map projection on a geometric property.</li>
 * </ul>
 *
 * The failure may be caused by illegal arguments, but not necessarily.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see Operation#apply(Feature, ParameterValueGroup)
 */
public class FeatureOperationException extends RuntimeException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -2721288981021742180L;

    /**
     * Creates an exception with no message.
     */
    public FeatureOperationException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public FeatureOperationException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public FeatureOperationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
