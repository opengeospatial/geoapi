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

import java.util.List;


/**
 * Thrown by {@link ParameterValueGroup} if adding or removing a {@linkplain ParameterValue
 * parameter value} would result in more or less parameters than the expected range. The
 * [{@linkplain ParameterDescriptor#getMinimumOccurs minimum} &hellip;
 *  {@linkplain ParameterDescriptor#getMaximumOccurs maximum}] range is defined by
 * the {@link ParameterDescriptorGroup} instance associated with the {@code ParameterValueGroup}.
 * <p>
 * This exception may be thrown directly by the {@link ParameterValueGroup#addGroup(String)}
 * method, or indirectly during the {@linkplain List#add add} or {@linkplain List#remove remove}
 * operations on the list returned by {@link ParameterValueGroup#values()}.
 *
 * {@note This exception is of kind <code>IllegalStateException</code> instead of
 *        <code>IllegalArgumentException</code> because it is not caused by a bad argument.
 *        It is rather a consequence of an <code>ParameterValueGroup</code> being "full".}
 *
 * @departure extension
 *   This exception is not part of the OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see ParameterValueGroup#values()
 * @see ParameterValueGroup#addGroup(String)
 */
public class InvalidParameterCardinalityException extends IllegalStateException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 4030549323541812311L;

    /**
     * The name of the parameter with invalid cardinality.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message The detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param parameterName The name of the parameter with invalid cardinality.
     */
    public InvalidParameterCardinalityException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the parameter with invalid cardinality.
     *
     * @return The name of the parameter with invalid cardinality.
     */
    public String getParameterName() {
        return parameterName;
    }
}
