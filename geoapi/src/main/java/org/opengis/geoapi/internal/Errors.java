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
package org.opengis.geoapi.internal;

import org.opengis.util.Factory;
import org.opengis.util.InternationalString;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;


/**
 * Provides error messages for saying that an implementation does not support a feature.
 * This is used for building the message in {@link UnimplementedServiceException} among others.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Errors {
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
                        .append("” factory does not support the creation of ").append(type);
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
        return "The “" + getVendor(factory) + "” factory does not support the parsing of " + format + " format.";
    }

    /**
     * Returns the exception to throw when an object is not of the expected type.
     *
     * @param  factory  the factory on which a {@code createFoo(…)} method has been invoked.
     * @param  code     the user-specified code.
     * @param  actual   the object which was found for the specified code.
     * @param  cause    the exception thrown when trying to cast the object.
     * @return the exception to throw.
     */
    public static NoSuchAuthorityCodeException unexpectedType(final AuthorityFactory factory,
            final String code, final IdentifiedObject actual, final ClassCastException cause)
    {
        String as = null, ac = null;
        final Citation authority = factory.getAuthority();
        if (authority != null) {
            for (final Identifier id : actual.getIdentifiers()) {
                if (authority.equals(id.getAuthority())) {
                    as = id.getCodeSpace();
                    ac = id.getCode();
                    break;
                }
            }
        }
        return new NoSuchAuthorityCodeException("Not an object of the expected type.", as, ac, code, cause);
    }

    /**
     * Returns the vendor of the given factory, or the implementation class name if vendor is unspecified.
     *
     * @param  factory  the factory for which to get the vendor name.
     * @return vendor name to use in error messages.
     */
    private static String getVendor(final Factory factory) {
        String vendor = getTitle(factory.getVendor());
        if (vendor == null || vendor.isBlank()) {
            vendor = factory.getClass().getCanonicalName();
        }
        return vendor;
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
