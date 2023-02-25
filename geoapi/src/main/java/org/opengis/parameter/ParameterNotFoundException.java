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
package org.opengis.parameter;


/**
 * Thrown by {@link ParameterValueGroup} and {@link ParameterDescriptorGroup}
 * when a parameter is requested but not found in that group.
 *
 * @departure extension
 *   This exception is not part of OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see ParameterValueGroup#parameter(String)
 * @see ParameterDescriptorGroup#descriptor(String)
 */
public class ParameterNotFoundException extends IllegalArgumentException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = -8074834945993975175L;

    /**
     * The invalid parameter name.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message The detail message. The detail message is saved for
     *        later retrieval by the {@link #getMessage()} method.
     * @param parameterName The name of the parameter which was required but not found.
     */
    public ParameterNotFoundException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the parameter which was required but not found.
     *
     * @return The required parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }
}
