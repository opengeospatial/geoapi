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
package org.opengis.util;

import org.opengis.metadata.Identifier;
import org.opengis.parameter.ParameterValueGroup;  // For javadoc


/**
 * Thrown when an identifier provided to a factory method cannot be found.
 * The identifier is often provided by {@link Identifier#getCode()}.
 * <p>
 * <b>Example:</b> This exception is thrown when a
 * {@linkplain org.opengis.referencing.operation.MathTransform math transform}
 * as been requested with an unknown {@linkplain org.opengis.referencing.operation.OperationMethod
 * operation method} identifier.
 *
 * @departure extension
 *   This exception is not part of the OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.operation.MathTransformFactory#createParameterizedTransform(ParameterValueGroup)
 */
public class NoSuchIdentifierException extends FactoryException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -6846799994429345902L;

    /**
     * The identifier code.
     */
    private final String identifier;

    /**
     * Constructs an exception with the specified detail message and classification name.
     *
     * @param  message The detail message. The detail message is saved
     *         for later retrieval by the {@link #getMessage()} method.
     * @param identifier The {@linkplain Identifier#getCode() identifier code}.
     */
    public NoSuchIdentifierException(final String message, final String identifier) {
        super(message);
        this.identifier = identifier;
    }

    /**
     * Returns the {@linkplain Identifier#getCode identifier code}.
     *
     * @return The identifier code.
     */
    public String getIdentifierCode() {
        return identifier;
    }
}
