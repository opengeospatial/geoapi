/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2015-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;


/**
 * Thrown when {@link FeatureType#newInstance()} is invoked but the feature cannot be instantiated.
 * The instantiation can fail for a variety of reasons including but not limited to:
 *
 * <ul>
 *   <li>the feature type {@linkplain FeatureType#isAbstract() is abstract},</li>
 *   <li>the feature type definition is incomplete.</li>
 * </ul>
 *
 * <div class="note"><b>Analogy with Java reflection</b>:
 * if we compare {@code FeatureType} to {@link Class} and {@code Feature} to {@link Object} in the Java language,
 * then this exception is equivalent to {@link java.lang.InstantiationException}.</div>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see FeatureType#newInstance()
 */
public class FeatureInstantiationException extends IllegalStateException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 2453228493560053757L;

    /**
     * Creates an exception with no message.
     */
    public FeatureInstantiationException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public FeatureInstantiationException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public FeatureInstantiationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
