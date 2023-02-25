/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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

import java.util.Collection;


/**
 * Thrown when an {@link Attribute} value or a {@link FeatureAssociation} does not met the constraints
 * (other than Java class) specified by its type. This exception can be thrown by implementations that
 * perform validity checks. Such verifications happen typically in the following methods:
 * <ul>
 *   <li>In {@code Attribute}:
 *     <ul>
 *       <li>{@link Attribute#setValue(Object) setValue(Object)}</li>
 *       <li>{@link Attribute#setValues(Collection) setValues(Collection)}</li>
 *     </ul>
 *   </li>
 *   <li>In {@code FeatureAssociation}:
 *     <ul>
 *       <li>{@link FeatureAssociation#setValue(Feature) setValue(Feature)}</li>
 *       <li>{@link FeatureAssociation#setValues(Collection) setValues(Collection)}</li>
 *     </ul>
 *   </li>
 *   <li>In {@code Feature}:
 *     <ul>
 *       <li>{@link Feature#setProperty(Property) setProperty(Property)}</li>
 *       <li>{@link Feature#setPropertyValue(String, Object) setPropertyValue(String, Object)}</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Exception for invalid class</h2>
 * Libraries may throw {@link ClassCastException} instead of this {@code InvalidPropertyValueException} when
 * the given value is not an instance of the expected class (typically {@link AttributeType#getValueClass()}).
 * The reason is that libraries may rely on Java parameterized types, which throws {@code ClassCastException}
 * at runtime when the objects are used in an unsafe way. Libraries may also rely on {@link Class#cast(Object)}
 * or {@link Class#asSubclass(Class)} standard methods, which are designed to throw {@code ClassCastException},
 * or may way to be consistent with all the above.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class InvalidPropertyValueException extends IllegalArgumentException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 3769646492399262226L;

    /**
     * Creates an exception with no message.
     */
    public InvalidPropertyValueException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public InvalidPropertyValueException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public InvalidPropertyValueException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
