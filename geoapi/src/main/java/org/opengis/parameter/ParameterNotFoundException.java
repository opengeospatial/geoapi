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
package org.opengis.parameter;


/**
 * Thrown by parameter groups
 * ({@linkplain ParameterValueGroup value} and {@linkplain ParameterDescriptorGroup descriptor})
 * when a parameter is requested but not found in that group.
 * This exception is typically thrown by the following methods:
 *
 * <ul>
 *   <li>{@link ParameterDescriptorGroup#descriptor(String)}</li>
 *   <li>{@link ParameterValueGroup#parameter(String)}</li>
 *   <li>{@link ParameterValueGroup#groups(String)}</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see InvalidParameterNameException
 */
public class ParameterNotFoundException extends IllegalArgumentException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -8074834945993975175L;

    /**
     * The invalid parameter name.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the name of the parameter which was required but not found.
     */
    public ParameterNotFoundException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the parameter which was required but not found.
     *
     * @return the required parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }
}
