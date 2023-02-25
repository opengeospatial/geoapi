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
package org.opengis.parameter;


/**
 * Thrown by {@link ParameterValue} getter methods when a value cannot be casted to the
 * requested type. For example, this exception is thrown when {@link ParameterValue#doubleValue()}
 * is invoked but the value is not convertible to a {@code double}.
 *
 * <div class="note"><b>Note:</b>
 * this exception is of kind {@code IllegalStateException} instead of {@code IllegalArgumentException}
 * because it is not caused by a bad argument. It is rather a consequence of a zero-argument method invoked
 * in a context where it is not allowed.</div>
 *
 * This exception is typically thrown by the following methods:
 * <ul>
 *   <li>{@link ParameterValue#intValue()}</li>
 *   <li>{@link ParameterValue#doubleValue()}</li>
 *   <li>{@link ParameterValue#booleanValue()}</li>
 *   <li>{@link ParameterValue#stringValue()}</li>
 *   <li>{@link ParameterValue#valueFile()}</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see InvalidParameterValueException
 */
public class InvalidParameterTypeException extends IllegalStateException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 2740762597003093176L;

    /**
     * The invalid parameter name.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the parameter name.
     */
    public InvalidParameterTypeException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Creates an exception with the specified message, cause and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause          the cause, saved for later retrieval by the {@link #getCause()} method.
     * @param parameterName  the parameter name.
     *
     * @since 3.1
     */
    public InvalidParameterTypeException(String message, Throwable cause, String parameterName) {
        super(message, cause);
        this.parameterName = parameterName;
    }

    /**
     * Returns the parameter name.
     *
     * @return the parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }
}
