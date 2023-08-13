/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
     * @param  <T>          the type of elements in the collection, or {@code null} if none.
     * @param  elementType  the type of elements in the collection.
     * @param  objects      the collection to validate (never {@code null}).
     * @return an array of all elements in the given collection.
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
