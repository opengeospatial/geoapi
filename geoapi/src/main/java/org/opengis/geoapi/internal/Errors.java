/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi.internal;

import java.util.Objects;
import org.opengis.util.Factory;
import org.opengis.util.InternationalString;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;


/**
 * Provides error messages for saying that an implementation does not support a type or a service.
 * This is used for building messages in {@link UnimplementedServiceException} or other exceptions.
 * This class is loaded only if an implementation does not support a feature requested by the user.
 * It is intended to help users to identify which implementation they are using and which service
 * is not implemented.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Errors {
    /**
     * Name of the logger to use for non-fatal errors.
     */
    public static final String LOGGER = "org.opengis.geoapi";

    /**
     * Do not allow instantiation of this class.
     */
    private Errors() {
    }

    /**
     * Returns a message saying that a factory does not support creation of objects of the specified type.
     * This error message can be given to the {@link UnimplementedServiceException} constructor.
     *
     * @param  factory  the factory on which a {@code createFoo(…)} method has been invoked.
     * @param  type     the type of object that the user requested.
     * @param  variant  variant of the type (e.g. "2D", "3D", "spherical", "Cartesian"), or {@code null} if none.
     * @return error message saying that the factory does not support the specified type and variant.
     */
    public static String cannotCreate(final Factory factory, final Class<?> type, final String variant) {
        final var message = new StringBuilder(80).append("The “").append(getVendor(factory))
                .append("” implementation does not support the creation of ").append(type.getSimpleName());
        if (variant != null) {
            message.append(" (").append(variant).append(')');
        }
        return message.append(" instances.").toString();
    }

    /**
     * Returns a message saying that a factory cannot parse the specified format.
     * This error message can be given to the {@link UnimplementedServiceException} constructor.
     *
     * @param  factory  the factory on which a {@code createFromFoo(…)} method has been invoked.
     * @param  format   the format to parse.
     * @return error message saying that the factory does not support parsing the specified format.
     */
    public static String cannotParse(final Factory factory, final String format) {
        return "The “" + getVendor(factory) + "” implementation does not support the parsing of " + format + " format.";
    }

    /**
     * Returns the exception to throw when an object is not of the expected type.
     *
     * @param  factory  the factory on which a {@code createFoo(…)} method has been invoked.
     * @param  code     the user-specified code.
     * @param  actual   the object which was found for the specified code.
     * @param  cause    the exception thrown when trying to cast the object.
     * @return the exception to throw.
     * @throws FactoryException if the factory authority cannot be obtained.
     */
    public static NoSuchAuthorityCodeException unexpectedType(final AuthorityFactory factory,
            final String code, final IdentifiedObject actual, final ClassCastException cause)
            throws FactoryException
    {
        String as = null, ac = null;
        final Citation authority = factory.getAuthority();
        for (final Identifier id : actual.getIdentifiers()) {
            if (Objects.equals(authority, id.getAuthority())) {
                as = id.getCodeSpace();
                ac = id.getCode();
                break;
            }
        }
        /*
         * We do not try to provide more information about the expected versus actual type
         * because they are already provided in the message of the `ClassCastException`.
         */
        return new NoSuchAuthorityCodeException("The “" + code + "” object from the “" + getVendor(factory)
                    + "” implementation is not an instance of the requested type.", as, ac, code, cause);
    }

    /**
     * Returns the vendor of the given factory, or the implementation class name if vendor is unspecified.
     *
     * @param  factory  the factory for which to get the vendor name.
     * @return vendor name to use in error messages.
     */
    private static String getVendor(final Factory factory) {
        String vendor = getTitle(factory.getVendor());
        if (vendor != null && !vendor.isBlank()) {
            return vendor;
        }
        return factory.getClass().getCanonicalName();
    }

    /**
     * Returns the title of the given citation, or {@code null} if none.
     *
     * @param  citation  the citation from which to get the title, or {@code null} if none.
     * @return citation title, or {@code null} if none.
     */
    private static String getTitle(final Citation citation) {
        if (citation != null) {
            final InternationalString title = citation.getTitle();
            if (title != null) {
                return title.toString();
            }
        }
        return null;
    }
}
