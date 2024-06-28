/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.util.HashMap;
import java.util.Collection;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.RegisterOperations;
import org.opengis.geoapi.internal.Produces;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Verifies the {@link Produces} annotation on factories.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ProducesTest {
    /**
     * Creates a new test case.
     */
    public ProducesTest() {
    }

    /**
     * Verifies all {@link Produces} annotations found on interfaces.
     * The current version expects the annotation to be present only on {@link AuthorityFactory},
     * But if we decide to move {@link Produces} in public API, then a future version would expect
     * the annotation on all factories.
     */
    @Test
    public void testAll() {
        int count = 0;
        for (final Class<?> type : Content.INTERFACES.types()) {
            final Produces annotation = type.getAnnotation(Produces.class);
            assertEquals(AuthorityFactory.class.isAssignableFrom(type)
                    && type != AuthorityFactory.class
                    && type != RegisterOperations.class,
                    annotation != null,
                    () -> "Unexpected or missing annotation on " + type.getCanonicalName());

            if (annotation != null) {
                count++;
                final var foundProducts = new HashMap<Class<?>, Boolean>();
                for (final Class<?> produced : annotation.value()) {
                    assertNull(foundProducts.put(produced, Boolean.FALSE),
                            () -> "Duplicated type " + produced.getSimpleName() + " in " + type.getCanonicalName());
                }
                assertFalse(foundProducts.isEmpty(), () -> "Annotation on " + type.getCanonicalName() + " is empty.");
                for (final Method method : type.getMethods()) {
                    if (method.getName().startsWith("create")) {
                        final Class<?> returnType = getReturnType(method);
                        final int[] declarationCount = new int[1];
                        foundProducts.entrySet().forEach((entry) -> {
                            if (entry.getKey().isAssignableFrom(returnType)) {
                                entry.setValue(Boolean.TRUE);
                                declarationCount[0]++;
                            }
                        });
                        assertEquals(1, declarationCount[0], () -> type.getSimpleName() + '.' + method.getName() +
                                ": " + returnType.getSimpleName() + " is not declared or is declared twice.");
                    }
                }
                assertTrue(foundProducts.values().removeIf(Boolean::booleanValue));
                assertTrue(foundProducts.isEmpty(), () -> "Undeclared types in: " + type.getSimpleName() + ": " + foundProducts.values());
            }
        }
        assertEquals(4, count, "Unexpected number of factories found.");
    }

    /**
     * Returns the return type of the given method.
     * If that type is a collection, extracts the collection element.
     *
     * @throws ClassCastException if the collection is not parameterized with classes or interfaces.
     */
    private static Class<?> getReturnType(final Method method) {
        Class<?> returnType = method.getReturnType();
        if (Collection.class.isAssignableFrom(returnType)) {
            returnType = (Class<?>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
        }
        return returnType;
    }
}
