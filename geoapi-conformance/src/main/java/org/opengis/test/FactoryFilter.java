/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Locale;
import java.util.Collection;
import org.opengis.util.Factory;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.AuthorityFactory;


/**
 * Specifies whether a factory can be used for creating the objects to be tested.
 * Implementors can provide an instance of this interface in their test packages and
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
     * Returns {@code true} if the given factory can be tested. Implementors shall return
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
         * Returns {@code true} if the given {@link InternationalString} is equals to the
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
