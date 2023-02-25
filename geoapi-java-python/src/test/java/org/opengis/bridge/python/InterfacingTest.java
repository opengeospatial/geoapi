/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.bridge.python;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import org.opengis.annotation.UML;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests {@link Interfacing}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
public final class InterfacingTest {
    /**
     * The {@link Interfacing#GEOAPI} constant cast to its implementation type.
     */
    private final Interfacing.GeoAPI geoapi;

    /**
     * Creates a new test.
     */
    public InterfacingTest() {
        geoapi = (Interfacing.GeoAPI) Interfacing.GEOAPI;
    }

    /**
     * Verifies the {@code *_CAPACITY} constant values in {@link Interfacing#GEOAPI}.
     */
    @Test
    public void verifyCapacities() {
        assertEquals(hashMapCapacity(geoapi.typesForNames().size()), Interfacing.GeoAPI.CLASS_CAPACITY);
        assertEquals(hashMapCapacity(geoapi.subclassed().size()), Interfacing.GeoAPI.SUBCLASSED_CAPACITY);
    }

    /**
     * Verifies the content of {@link Interfacing#GEOAPI} mapping from names to classes.
     *
     * @throws IOException if an error occurred while reading the {@code "class-index.properties"} file.
     * @throws ClassNotFoundException if a value in {@code "class-index.properties"} is invalid.
     */
    @Test
    public void verifyTypesForNames() throws IOException, ClassNotFoundException {
        final Properties umlToClass = new Properties();     // TODO: use (CLASS_CAPACITY + 14) on JDK10.
        try (InputStream in = UML.class.getResourceAsStream(Interfacing.GeoAPI.CLASS_LIST)) {
            umlToClass.load(in);
        }
        /*
         * Create a map of Python type names to Java classes. In this process, we are going to have name collisions
         * as a result of prefix removal. For example, "MD_Identifier" and "RS_Identifier" both become "Identifier".
         * However in such case, one of the types is deprecated. The 'deprecated' set will contain those deprecated
         * types that we need to omit for avoiding name collisions.
         */
        final Set<Class<?>> deprecated = new HashSet<>();
        final ClassLoader loader = UML.class.getClassLoader();
        final Map<String,Class<?>> typesForNames = new HashMap<>(Interfacing.GeoAPI.CLASS_CAPACITY);
        for (final Map.Entry<Object,Object> e : umlToClass.entrySet()) {
            String name = (String) e.getKey();
            name = name.substring(name.indexOf('_') + 1);
            final Class<?> type = Class.forName(((String) e.getValue()), false, loader);
            final Class<?> previous = typesForNames.putIfAbsent(name, type);
            if (previous != null && previous != type) {
                if (previous.isAnnotationPresent(Deprecated.class)) {
                    assertSame(previous, typesForNames.put(name, type));
                    assertTrue(deprecated.add(previous));
                } else if (type.isAnnotationPresent(Deprecated.class)) {
                    assertTrue(deprecated.add(type));
                } else {
                    fail("Name collision: " + name);
                }
            }
        }
        /*
         * Assert that Interfacing.GEOAPI know the correct set of interfaces to exclude.
         * Tested first because other tests in this class are likely to fail if this list
         * of excluded interfaces is wrong.
         */
        final Set<String> excludes = new HashSet<>();
        for (final Class<?> c : deprecated) {
            assertTrue(excludes.add(c.getAnnotation(UML.class).identifier()));
        }
        assertEquals("excludes", geoapi.excludes(), excludes);
        /*
         * Assert that Interfacing.GEOAPI has the correct list of Java interfaces.
         * In particular, in case of name collision the interface shall be the non-deprecated one.
         */
        for (final Map.Entry<String,String> e : geoapi.typesForNames().entrySet()) {
            final String key = e.getKey();
            final Class<?> type = typesForNames.remove(key);
            assertNotNull(key, type);
            assertEquals(key, type.getName(), e.getValue());
        }
    }

    /**
     * Verifies the content of {@link Interfacing#GEOAPI} list of sub-classed classed.
     *
     * @throws ClassNotFoundException if a value in {@code "class-index.properties"} is invalid.
     */
    @Test
    public void verifySubclassed() throws ClassNotFoundException {
        final Map<String,String> typesForNames = geoapi.typesForNames();
        /*
         * For each interface, set a flag telling us whether that interface has subtypes or not.
         * Environment uses this information for reducing the amount of relatively costly checks
         * for subtypes.
         */
        final ClassLoader loader = UML.class.getClassLoader();
        final Map<Class<?>,Boolean> hasSubTypes = new HashMap<>(typesForNames.size());
        for (final String name : typesForNames.values()) {
            final Class<?> type = Class.forName(name, false, loader);
            hasSubTypes.put(type, Boolean.FALSE);
        }
        for (final String name : typesForNames.values()) {
            final Class<?> type = Class.forName(name, false, loader);
            for (final Class<?> parent : type.getInterfaces()) {
                hasSubTypes.replace(parent, Boolean.TRUE);
            }
        }
        /*
         * List the interfaces having sub-types, in alphabetical order.
         * Then compare with content of the "subclassed.txt" file.
         */
        hasSubTypes.values().removeIf((v) -> !v);
        if (!hasSubTypes.keySet().equals(geoapi.subclassed())) {
            final String[] parentTypes = new String[hasSubTypes.size()];
            int i = 0;
            for (final Class<?> c : hasSubTypes.keySet()) {
                parentTypes[i++] = c.getName();
            }
            assertEquals(parentTypes.length, i);
            Arrays.sort(parentTypes);
            final String lineSeparator = System.lineSeparator();
            final StringBuilder buffer = new StringBuilder(500).append("Content of ")
                    .append(Interfacing.GeoAPI.SUBCLASSED_LIST).append(" should be:")
                    .append(lineSeparator);
            for (final String type : parentTypes) {
                buffer.append(type).append(lineSeparator);
            }
            fail(buffer.toString());
        }
    }


    /**
     * Returns the capacity to be given to the {@link java.util.HashMap#HashMap(int) HashMap}
     * constructor for holding the given number of elements. This method computes the capacity
     * for the default <cite>load factor</cite>, which is 0.75.
     *
     * <p>The same calculation can be used for {@link java.util.LinkedHashMap} and
     * {@link java.util.HashSet} as well, which are built on top of {@code HashMap}.
     * However, it is not needed for {@link java.util.IdentityHashMap}.</p>
     *
     * @param  count  the number of elements to be put into the hash map or hash set.
     * @return the minimal initial capacity to be given to the hash map constructor.
     */
    private static int hashMapCapacity(final int count) {
        int r = count / 3;          // Dividing 'count' by 0.75 is equivalent to multiplying by 1.333333…
        if ((count % 3) != 0) {
            r++;
        }
        return count + r;
    }
}
