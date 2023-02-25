/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.test;

import java.util.Locale;
import java.util.Collection;
import org.opengis.util.Factory;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.AuthorityFactory;


/**
 * Specifies whether a factory can be used for creating the objects to be tested.
 * Implementers can provide an instance of this interface in their test packages and
 * declare their instance in the {@code META-INF/services/org.opengis.test.FactoryFilter}
 * file. GeoAPI will iterate over every {@code FactoryFilter}s found on the classpath before
 * the first execution of any particular {@link TestCase} subclass, in order to check
 * whether a particular factory can be used.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public interface FactoryFilter {
    /**
     * Returns {@code true} if the given factory can be tested. Implementers shall return
     * {@code false} only when they really want to exclude a particular factory. For every
     * unknown factory, this method shall return {@code true}.
     *
     * <p>If more than one {@code FactoryFilter} is found on the classpath, then the given factory
     * will be tested only if all {@code FactoryFilter.filter(…)} calls returned {@code true}.</p>
     *
     * @param  <T>       the compile-time type of the {@code category} argument.
     * @param  category  the factory interface ({@link org.opengis.util.NameFactory},
     *                   {@link org.opengis.referencing.crs.CRSFactory}, <i>etc.</i>).
     * @param  factory   the factory instance.
     * @return {@code false} if the given factory should be excluded from the tests,
     *         or {@code true} otherwise.
     */
    <T extends Factory> boolean filter(Class<T> category, T factory);




    /**
     * Filters {@link AuthorityFactory} by their {@linkplain AuthorityFactory#getAuthority()
     * authority name}. This filter accepts any factory meeting at least one of the following
     * conditions:
     *
     * <ul>
     *   <li>The factory is not an instance of {@link AuthorityFactory}.</li>
     *   <li>The {@linkplain AuthorityFactory#getAuthority() authority} citation has a
     *       {@linkplain Citation#getTitle title} or {@linkplain Citation#getAlternateTitles
     *       alternate title} matching the name given to this filter at construction time.</li>
     * </ul>
     *
     * The string comparisons are case-insensitive and ignore leading and trailing spaces.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static class ByAuthority implements FactoryFilter {
        /**
         * Convenience constant for a frequently used factory filter.
         */
        public static final FactoryFilter EPSG = new ByAuthority("EPSG");

        /**
         * The authority names (typically {@code "EPSG"}).
         */
        private final String[] names;

        /**
         * Creates a new filter for the given authority names.
         *
         * @param names  the authority names (typically {@code "EPSG"}).
         */
        public ByAuthority(String... names) {
            this.names = names = names.clone();
            for (int i=0; i<names.length; i++) {
                names[i] = names[i].trim();
            }
        }

        /**
         * Returns {@code true} if the given {@link InternationalString} is equal to the
         * {@link #name}. Only the US locale and the default locale string are compared.
         */
        private boolean isValid(final InternationalString i18n) {
            if (i18n == null) {
                return false;
            }
            for (final String name : names) {
                final String asString = i18n.toString(Locale.US);
                if (asString.trim().equalsIgnoreCase(name)) {
                    return true;
                }
                final String asLocalized = i18n.toString();
                if (asLocalized != asString && asLocalized.trim().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns {@code true}, unless the given factory is an instance of {@link AuthorityFactory}
         * and the authority name is not the expected one. See class-javadoc for more details.
         */
        @Override
        public <T extends Factory> boolean filter(final Class<T> category, final T factory) {
            if (!AuthorityFactory.class.isAssignableFrom(category)) {
                return true;
            }
            final Citation authority = ((AuthorityFactory) factory).getAuthority();
            if (authority != null) {
                if (isValid(authority.getTitle())) {
                    return true;
                }
                final Collection<? extends InternationalString> titles = authority.getAlternateTitles();
                if (titles != null) {
                    for (final InternationalString candidate : titles) {
                        if (isValid(candidate)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
