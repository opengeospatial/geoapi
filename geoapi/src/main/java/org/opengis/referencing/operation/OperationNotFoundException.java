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
package org.opengis.referencing.operation;

import org.opengis.util.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Thrown when a {@linkplain CoordinateOperation coordinate operation} is not found.
 * It may be because there is no known path between source and target
 * {@linkplain CoordinateReferenceSystem coordinate reference systems},
 * or because the requested operation is not available in the environment.
 *
 * @departure extension
 *   This exception is not part of the OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
public class OperationNotFoundException extends FactoryException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -382625493416204214L;

    /**
     * Construct an exception with no detail message.
     */
    public OperationNotFoundException() {
    }

    /**
     * Construct an exception with the specified detail message.
     *
     * @param message The details message.
     */
    public OperationNotFoundException(final String message) {
        super(message);
    }

    /**
     * Construct an exception with the specified detail message and cause.
     *
     * @param message The details message.
     * @param cause The cause for this exception.
     */
    public OperationNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
