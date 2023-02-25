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
 * Thrown if adding or removing a {@linkplain ParameterValue parameter value} in a
 * {@linkplain ParameterValueGroup group} would result in more or less parameters than the expected range.
 * The minimum and maximum occurrences are defined by the {@link ParameterDescriptorGroup}
 * instance associated with the {@code ParameterValueGroup}.
 *
 * <p>This exception may be thrown directly by the {@link ParameterValueGroup#addGroup(String)}
 * method, or indirectly during the add or remove operations applied on the list returned by
 * {@link ParameterValueGroup#values()}.</p>
 *
 * <div class="note"><b>Note 1:</b>
 * the <cite>cardinality</cite> is the number of elements in a set. Contrast with <cite>multiplicity</cite>,
 * which is the range of possible cardinalities a set can hold.</div>
 *
 * <div class="note"><b>Note 2:</b>
 * this exception is of kind {@code IllegalStateException} instead of {@code IllegalArgumentException}
 * because it is not caused by a bad argument. It is rather a consequence of an {@link ParameterValueGroup}
 * being "full".</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
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
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the name of the parameter with invalid cardinality.
     */
    public InvalidParameterCardinalityException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Creates an exception with the specified message, cause and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause          the cause, saved for later retrieval by the {@link #getCause()} method.
     * @param parameterName  the name of the parameter with invalid cardinality.
     *
     * @since 3.1
     */
    public InvalidParameterCardinalityException(String message, Throwable cause, String parameterName) {
        super(message, cause);
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the parameter with invalid cardinality.
     *
     * @return the name of the parameter with invalid cardinality.
     */
    public String getParameterName() {
        return parameterName;
    }
}
