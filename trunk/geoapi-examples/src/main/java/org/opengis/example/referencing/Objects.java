/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;


/**
 * Place holder for {@link java.util.Objects}. This class will be deleted when we will be allowed
 * to compile for JDK7.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Objects {
    /**
     * Do not allow instantiation of this class.
     */
    private Objects() {
    }

    /**
     * See JDK7 javadoc.
     */
    static void requireNonNull(final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    /**
     * See JDK7 javadoc.
     */
    static boolean equals(final Object o1, final Object o2) {
        return (o1 == o2) || (o1 != null && o1.equals(o2));
    }
}
