/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
 * <section class="note">
 * <h1>Exception for invalid class</h1>
 * Libraries may throw {@link ClassCastException} instead than this {@code InvalidPropertyValueException} when
 * the given value is not an instance of the expected class (typically {@link AttributeType#getValueClass()}).
 * The reason is that libraries may rely on Java parameterized types, which throws {@code ClassCastException}
 * at runtime when the objects are used in an unsafe way. Libraries may also rely on {@link Class#cast(Object)}
 * or {@link Class#asSubclass(Class)} standard methods, which are designed to throw {@code ClassCastException},
 * or may way to be consistent with all the above.
 * </section>
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
