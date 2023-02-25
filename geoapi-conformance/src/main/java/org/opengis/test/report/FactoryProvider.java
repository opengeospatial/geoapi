/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
