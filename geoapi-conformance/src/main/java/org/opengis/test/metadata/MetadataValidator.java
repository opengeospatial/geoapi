/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.metadata;

import java.util.Collection;
import java.lang.reflect.Array;
import org.opengis.util.InternationalString;

import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Base class for validators of {@code org.opengis.metadata} package and sub-packages.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public abstract class MetadataValidator extends Validator {
    /**
     * Creates a new validator instance.
     *
     * @param container    the set of validators to use for validating other kinds of objects
     *                     (see {@linkplain #container field javadoc}).
     * @param packageName  the name of the package containing the classes to be validated.
     */
    protected MetadataValidator(final ValidatorContainer container, final String packageName) {
        super(container, packageName);
    }

    /**
     * Returns all collection elements in an array of the given type. This method ensures that
     * all elements are non-null and of the expected type. Callers should iterate over the
     * returned array and validate each elements, if needed.
     *
     * @param <T>          the type of elements in the collection, or {@code null} if none.
     * @param elementType  the type of elements in the collection.
     * @param objects      the collection to validate (never {@code null}).
     */
    final <T> T[] toArray(final Class<T> elementType, final Collection<? extends T> objects) {
        assertNotNull("Null collection. Should be an empty one if there is no elements.", objects);
        validate(objects);
        @SuppressWarnings("unchecked")
        final T[] array = (T[]) Array.newInstance(elementType, objects.size());
        int count = 0;
        for (final T element : objects) {
            assertNotNull("Collection should not contain null element.", element);
            assertInstanceOf("Wrong element type in the collection.", elementType, element);
            array[count++] = element;
        }
        assertEquals("Unexpected end of iteration", array.length, count);
        return array;
    }

    /**
     * Validates the given mandatory string.
     *
     * @param  object  the object to validate.
     */
    final void validateMandatory(final InternationalString object) {
        mandatory("Missing mandatory metadata attribute.", object);
        container.validate(object);
    }

    /**
     * Validates the given optional string.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    final void validateOptional(final InternationalString object) {
        container.validate(object);
    }

    /**
     * Returns {@code true} if the given text is {@code null}, white or
     * {@linkplain String#isEmpty() empty}.
     *
     * @param  text  the text to test, or {@code null}.
     * @return {@code true} if the given text is null, white or empty.
     *
     * @since 3.1
     */
    static boolean isNullOrEmpty(final CharSequence text) {
        return (text == null) || text.toString().trim().isEmpty();
    }

    /**
     * Returns {@code true} if the given collection is {@code null} or
     * {@linkplain Collection#isEmpty() empty}.
     *
     * @param  collection  the text to test, or {@code null}.
     * @return {@code true} if the given collection is null or empty.
     *
     * @since 3.1
     */
    static boolean isNullOrEmpty(final Collection<?> collection) {
        return (collection == null) || collection.isEmpty();
    }
}
