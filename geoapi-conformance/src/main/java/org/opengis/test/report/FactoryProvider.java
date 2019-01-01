/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.report;

import java.util.List;
import java.util.Arrays;
import java.lang.reflect.Array;
import org.opengis.test.TestCase;
import org.opengis.util.Factory;


/**
 * Provides factory implementations for the given type. This utility class is implemented as a
 * subclass of {@link TestCase} only in order to get access to the {@link #factories(Class[])}
 * protected method.
 *
 * @author Martin Desruisseaux
 */
final class FactoryProvider extends TestCase {
    /**
     * Do not allow instantiation of this class.
     */
    private FactoryProvider() {
    }

    /**
     * Returns all factory of the given types, or an empty array if none.
     */
    @SuppressWarnings("unchecked")
    static <T extends Factory> T[] forType(final Class<T> type) {
        final List<Factory[]> factories = factories(type);
        T[] selected = (T[]) Array.newInstance(type, factories.size());
        int count = 0;
        for (final Factory[] candidates : factories) {
            final T candidate = (T) candidates[0];
            if (candidate != null) {
                selected[count++] = candidate;
            }
        }
        if (count != selected.length) {
            selected = Arrays.copyOf(selected, count);
        }
        return selected;
    }
}
