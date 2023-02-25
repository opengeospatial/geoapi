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
package org.opengis.util;

import org.opengis.metadata.Identifier;
import org.opengis.parameter.ParameterValueGroup;  // For javadoc


/**
 * Thrown when an identifier provided to a factory method cannot be found.
 * The identifier may be provided by {@link org.opengis.referencing.IdentifiedObject#getName()}.
 * In the common case where the identifier is an "authority:code" pair,
 * the {@link org.opengis.referencing.NoSuchAuthorityCodeException} specialization should be used.
 *
 * <p><b>Example:</b> This exception is thrown when a
 * {@linkplain org.opengis.referencing.operation.MathTransform math transform} has been requested
 * with an unknown {@linkplain org.opengis.referencing.operation.OperationMethod operation method}
 * name.</p>
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
     * @param message     the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param identifier  the {@linkplain Identifier#getCode() identifier code}.
     */
    public NoSuchIdentifierException(final String message, final String identifier) {
        super(message);
        this.identifier = identifier;
    }

    /**
     * Returns the {@linkplain Identifier#getCode identifier code}.
     *
     * @return the identifier code.
     */
    public String getIdentifierCode() {
        return identifier;
    }
}
