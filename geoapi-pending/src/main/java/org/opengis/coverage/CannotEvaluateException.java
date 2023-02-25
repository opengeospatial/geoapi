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
package org.opengis.coverage;

import org.opengis.geometry.DirectPosition;  // For Javadoc


/**
 * Thrown when a quantity cannot be evaluated. This exception is usually thrown by a
 * <code>Coverage.{@linkplain Coverage#evaluate(DirectPosition, double[]) evaluate}(…)</code>
 * method, for example when a point is outside the coverage.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexander Petkov
 * @since   GeoAPI 1.0
 *
 * @see Coverage#evaluate(DirectPosition, byte[])
 * @see Coverage#evaluate(DirectPosition, double[])
 */
public class CannotEvaluateException extends RuntimeException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 506793649975583062L;

    /**
     * Represents the coverage for which this exception is thrown. Useful when {@link Coverage}
     * is used on a multilevel, so {@code PointOutsideCoverageException} can provide informative
     * details.
     */
    private Coverage coverage;

    /**
     * Creates an exception with no message.
     */
    public CannotEvaluateException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message  the detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     */
    public CannotEvaluateException(String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message  the detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     * @param  cause  the cause for this exception. The cause is saved
     *         for later retrieval by the {@link #getCause()} method.
     */
    public CannotEvaluateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns the coverage for which this exception is thrown. Useful when {@link Coverage}
     * is used on a multilevel, so {@code CannotEvaluateException} can provide informative
     * details.
     *
     * @return the coverage, or {@code null} if unknown.
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * Sets the coverage.
     *
     * @param  coverage  the coverage, or {@code null} if unknown.
     */
    public void setCoverage(final Coverage coverage) {
        this.coverage = coverage;
    }
}
