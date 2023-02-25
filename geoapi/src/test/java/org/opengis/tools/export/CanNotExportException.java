/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.export;


/**
 * Thrown when a Java element cannot be exported to the target language.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
public class CanNotExportException extends RuntimeException {
    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public CanNotExportException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with the specified detail message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause of this exception, saved for later retrieval by the {@link #getCause()} method.
     */
    public CanNotExportException(String message, Exception cause) {
        super(message, cause);
    }
}
