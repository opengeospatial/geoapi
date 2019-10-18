/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Map;
import java.util.HashMap;
import java.util.ServiceLoader;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;


/**
 * The factories needed for {@code geoapi-netcdf} working.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Factories {
    /**
     * The factories, created when first needed.
     */
    private static final Map<Class<? extends Factory>, Factory> FACTORIES = new HashMap<>();

    /**
     * Do now allow instantiation.
     */
    private Factories() {
    }

    /**
     * Returns an instance of the factory of the given type.
     *
     * @param  type  the factory type.
     * @return an instance of the factory of the given type, or {@code null}.
     * @throws FactoryException if no factory can be found for the given type.
     */
    public static <T extends Factory> T getFactory(final Class<T> type) throws FactoryException {
        synchronized (FACTORIES) {
            final T factory = type.cast(FACTORIES.get(type));
            if (factory != null) {
                return factory;
            }
            for (final T candidate : ServiceLoader.load(type)) {
                // TODO: If we want to apply some filtering, do it here.
                FACTORIES.put(type, candidate);
                return candidate;
            }
        }
        throw new FactoryException("No " + type.getSimpleName() + " found.");
    }
}
