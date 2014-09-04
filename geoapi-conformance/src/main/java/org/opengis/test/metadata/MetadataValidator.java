/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.util.InternationalString;

import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Base class for validators of {@code org.opengis.metadata} package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
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
