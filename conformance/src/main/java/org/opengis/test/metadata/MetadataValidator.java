/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.metadata;

import java.util.Collection;
import org.opengis.util.InternationalString;

import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;


/**
 * Base class for validators of {@code org.opengis.metadata} package.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class MetadataValidator extends Validator {
    /**
     * Creates a new validator instance.
     *
     * @param container   The container of this validator.
     * @param packageName The name of the package containing the classes to be validated.
     */
    protected MetadataValidator(final ValidatorContainer container, final String packageName) {
        super(container, packageName);
    }

    /**
     * Validates all elements in the given collection.
     *
     * @param <T>         The type of elements in the collection.
     * @param elementType The type of elements in the collection.
     * @param objects     The collection to validate.
     */
    final <T> void validateCollection(final Class<T> elementType, final Collection<? extends T> objects) {
        mandatory("Null collection. Should be an empty one if there is no elements.", objects);
        if (objects != null) {
            for (final T element : objects) {
                assertNotNull("Collection should not contain null element.", element);
                assertInstanceOf("Wrong element type in the collection.", elementType, element);
                container.dispatch(element);
            }
        }
    }

    /**
     * Validates the given mandatory string.
     *
     * @param object The object to validate.
     */
    final void validateMandatory(final InternationalString object) {
        mandatory("Missing mandatory metadata attribute.", object);
        container.naming.validate(object);
    }

    /**
     * Validates the given mandatory string.
     *
     * @param object The object to validate, or {@code null}.
     */
    final void validateOptional(final InternationalString object) {
        container.naming.validate(object);
    }
}
