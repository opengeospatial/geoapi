/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.openoffice;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.NoSuchElementException;

import org.opengis.util.Factory;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;


/**
 * Defines static methods used to access the application default factory implementations. To declare
 * a factory implementation, a services subdirectory is placed within the {@code META-INF} directory
 * that is present in every JAR file. This directory contains a file for each factory interface that
 * has one or more implementation classes present in the JAR file. For example, if the JAR file contained
 * a class named {@code com.mycompany.DatumFactoryImpl} which implements the {@link DatumFactory} interface,
 * the JAR file would contain a file named:</P>
 *
 * <blockquote><pre>META-INF/services/org.opengis.referencing.datum.DatumFactory</pre></blockquote>
 *
 * containing the line:
 *
 * <blockquote><pre>com.mycompany.DatumFactoryImpl</pre></blockquote>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class FactoryFinder {
    /** Service registry initialized only when first needed. */
    private static ServiceLoader<CRSAuthorityFactory> crsFactory;

    /** Service registry initialized only when first needed. */
    private static ServiceLoader<CoordinateOperationFactory> copFactory;

    /**
     * Do not allows any instantiation of this class.
     */
    private FactoryFinder() {
    }

    /**
     * Returns the classloader to use for searching the {@code META-INF/services} directories.
     */
    private static ClassLoader getClassLoader() {
        return FactoryFinder.class.getClassLoader();
    }

    /**
     * Returns the first service provider from the given loader.
     *
     * @param  loader  the loader.
     * @return the first factory found for the specified loader.
     * @throws NoSuchElementException if no factory were found.
     */
    private static <T extends Factory> T getFactory(final ServiceLoader<T> loader) {
        final Iterator<T> it = loader.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("No factory implementation found.");
    }

    /**
     * Returns the first service provider of the specified authority.
     *
     * @param  loader     the loader.
     * @param  authority  the authority (e.g. {@code "EPSG"}.
     * @return the first factory found for the specified authority.
     * @throws NoSuchElementException if no factory were found.
     */
    private static <T extends AuthorityFactory> T getAuthorityFactory(final ServiceLoader<T> loader, final String authority) {
        final Iterator<T> it = loader.iterator();
        while (it.hasNext()) {
            final T factory = it.next();
            if (Utilities.titleMatches(factory.getAuthority(), authority)) {
                return factory;
            }
        }
        throw new NoSuchElementException("No factory implementation found for \"" + authority + "\" authority.");
    }

    /**
     * Returns the first implementation of {@link CRSAuthorityFactory}.
     *
     * @param  authority  the authority (e.g. {@code "EPSG"}).
     * @return the first coordinate reference system authority factory found.
     * @throws NoSuchElementException if no factory were found.
     */
    static synchronized CRSAuthorityFactory getCRSAuthorityFactory(final String authority) {
        ServiceLoader<CRSAuthorityFactory> loader = crsFactory;
        if (loader == null) {
            crsFactory = loader = ServiceLoader.load(CRSAuthorityFactory.class, getClassLoader());
        }
        return getAuthorityFactory(loader, authority);
    }

    /**
     * Returns the first implementation of {@link CoordinateOperationFactory}.
     *
     * @return the first coordinate operation factory found.
     * @throws NoSuchElementException if no factory were found.
     */
    static synchronized CoordinateOperationFactory getCoordinateOperationFactory() {
        ServiceLoader<CoordinateOperationFactory> loader = copFactory;
        if (loader == null) {
            copFactory = loader = ServiceLoader.load(CoordinateOperationFactory.class, getClassLoader());
        }
        return getFactory(loader);
    }
}
