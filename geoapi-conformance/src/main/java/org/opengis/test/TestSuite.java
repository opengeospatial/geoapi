/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Iterator;
import java.util.Collection;
import java.util.ServiceLoader;
import java.lang.reflect.Array;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.opengis.util.Factory;


/**
 * The suite of every tests defined in the GeoAPI conformance module.
 * The test cases included in this test suite are:
 *
 * <ul>
 *   <li>{@link org.opengis.test.util.NameTest}</li>
 *   <li>{@link org.opengis.test.referencing.ObjectFactoryTest}</li>
 *   <li>{@link org.opengis.test.referencing.AffineTransformTest}</li>
 *   <li>{@link org.opengis.test.referencing.ParameterizedTransformTest}</li>
 *   <li>{@link org.opengis.test.referencing.AuthorityFactoryTest}</li>
 *   <li>{@link org.opengis.test.wkt.CRSParserTest}</li>
 * </ul>
 *
 * This {@code TestSuite} class provides also some static methods for {@linkplain #setFactories specifying
 * explicitly which factories to use} or {@linkplain #addTestListener being notified of test results}.
 * Those methods take effect even if the {@link TestCase} are run outside of a {@code TestSuite} context.
 *
 * <h2>How implementations are discovered</h2>
 * All tests use {@link Factory} instances that are specific to the implementation being tested.
 * By default {@code TestSuite} fetches the factory implementations with {@link ServiceLoader},
 * which will iterate every <code>provides org.opengis.<var>TheFactory</var></code> statements
 * in {@code module-info} files. However, implementers can override this default mechanism with
 * explicit calls to the {@link #setFactories(Class, Factory[])} method.
 *
 * @see TestCase
 * @see Factory
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  org.opengis.test.util.NameTest.class,
  org.opengis.test.referencing.ObjectFactoryTest.class,
  org.opengis.test.referencing.AffineTransformTest.class,
  org.opengis.test.referencing.ParameterizedTransformTest.class,
  org.opengis.test.referencing.AuthorityFactoryTest.class,
  org.opengis.test.wkt.CRSParserTest.class
})
public strictfp class TestSuite {
    /**
     * Constructor provided for allowing subclassing.
     * Instances of this class usually don't need to be created.
     */
    protected TestSuite() {
    }

    /**
     * Sets the class loader to use for loading implementations. A {@code null} value restores
     * the default {@linkplain Thread#getContextClassLoader() context class loader}.
     *
     * @param loader  the class loader to use, or {@code null} for the default.
     */
    public static void setClassLoader(final ClassLoader loader) {
        TestCase.setClassLoader(loader);
    }

    /**
     * Specifies the factory implementations to use for the given factory interface.
     *
     * @param <T>      the compile-time type of the {@code type} class argument.
     * @param type     the factory interface for which an implementation is specified.
     * @param factory  the implementations to use for the given interface.
     */
    @SafeVarargs
    public static <T extends Factory> void setFactories(final Class<T> type, final T... factory) {
        Objects.requireNonNull(type, "Given 'type' cannot be null");
        final Iterable<? extends Factory> list = Arrays.asList(factory.clone());
        synchronized (TestCase.FACTORIES) {
            TestCase.FACTORIES.put(type, list);
        }
    }

    /**
     * Returns the factory implementations explicitly given by the last call to
     * {@link #setFactories(Class, Factory[])} for the given interface.
     * This method does not iterate on {@link ServiceLoader} entries.
     *
     * @param <T>   the compile-time type of the {@code type} class argument.
     * @param type  the factory interface for which an implementations is desired.
     * @return      the implementations for the given interface, or {@code null} if none.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Factory> T[] getFactories(final Class<T> type) {
        Objects.requireNonNull(type, "Given 'type' cannot be null");
        final Iterable<? extends Factory> factories;
        synchronized (TestCase.FACTORIES) {
            factories = TestCase.FACTORIES.get(type);
        }
        if (factories instanceof Collection<?>) {
            final Collection<? extends Factory> collection = (Collection<? extends Factory>) factories;
            return collection.toArray((T[]) Array.newInstance(type, collection.size()));
        }
        return null;
    }

    /**
     * Adds a listener to be informed every time a test begin or finish, either on success
     * or failure. This method does not check if the given listener was already registered
     * (i.e. the same listener may be added more than once).
     *
     * @param listener The listener to add. {@code null} values are silently ignored.
     *
     * @deprecated To be replaced by JUnit 5 listener mechanism.
     */
    @Deprecated
    public static void addTestListener(final TestListener listener) {
        TestCase.addTestListener(listener);
    }

    /**
     * Removes a previously {@linkplain #addTestListener(TestListener) added} listener. If the
     * given listener has been added more than once, then only the last occurrence is removed.
     * If the given listener is not found, then this method does nothing.
     *
     * @param listener  the listener to remove. {@code null} values are silently ignored.
     *
     * @deprecated To be replaced by JUnit 5 listener mechanism.
     */
    @Deprecated
    public static void removeTestListener(final TestListener listener) {
        TestCase.removeTestListener(listener);
    }

    /**
     * Clears all factories specified to the {@link #setFactories(Class, Factory[])} method, and clears
     * all {@linkplain ServiceLoader service loader} caches. After this method call, all factories
     * will be reloaded when first needed. This method is intended for use in situations in which
     * new factories can be installed or removed into a running Java virtual machine.
     *
     * @see ServiceLoader#reload()
     */
    public static void clear() {
        synchronized (TestCase.FACTORIES) {
            final Iterator<Iterable<? extends Factory>> it = TestCase.FACTORIES.values().iterator();
            while (it.hasNext()) {
                final Iterable<? extends Factory> factories = it.next();
                if (factories instanceof ServiceLoader<?>) {
                    ((ServiceLoader<? extends Factory>) factories).reload();
                } else {
                    it.remove();
                }
            }
        }
    }
}
