/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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

import java.util.Arrays;
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
 * <p>
 * <ul>
 *   <li>{@link org.opengis.test.util.NameTest}</li>
 *   <li>{@link org.opengis.test.referencing.MathTransformTest}</li>
 *   <li>{@link org.opengis.test.referencing.CRSTest}</li>
 *   <li>{@link org.opengis.test.referencing.ReferencingTest}</li>
 * </ul>
 * <p>
 * All tests use {@link Factory} instances that are specific to the implementation being tested.
 * By default {@code TestSuite} fetches the factory implementations with {@link ServiceLoader},
 * which will scan every <code>META-INF/services/org.opengis.<var>TheFactory</var></code> files
 * on the classpath. However implementors can override this default mechanism with explicit calls
 * to the {@link #setFactories(Class, T[])} method. The following example overrides the CRS and
 * datum factories in its static initializer - all other factories will be fetch by the service
 * loader:
 *
 * <blockquote><pre>org.opengis.test.TestSuite;
 *
 *public class AllTests extends TestSuite {
 *    static {
 *        setFactories(  CRSFactory.class, new MyCRSFactory());
 *        setFactories(DatumFactory.class, new MyDatumFactory());
 *        <i>// etc.</i>
 *    }
 *}</pre></blockquote>
 *
 * Notes:
 * <p>
 * <ul>
 *   <li>There is no need for explicit JUnit annotations, because they are inherited from
 *       this {@code TestSuite} class.</li>
 *   <li>The above example works in NetBeans, but the static initializer is not executed
 *       during a Maven build. In the later case, only the service loader is used.</li>
 * </ul>
 * <p>
 * This {@code TestSuite} class is provided as a convenience for implementations that do not need
 * fine-grain control on the tests being executed. If more control is required (for example in
 * order to specify whatever {@link org.opengis.referencing.operation.MathTransform} instances
 * {@linkplain org.opengis.test.referencing.TransformTestCase#isDerivativeSupported support derivative}
 * functions), then the various {@link TestCase} subclasses shall be used directly.
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
  org.opengis.test.referencing.MathTransformTest.class,
  org.opengis.test.referencing.CRSTest.class,
  org.opengis.test.referencing.ReferencingTest.class
})
public strictfp class TestSuite {
    /**
     * Constructor provided for allowing subclassing.
     * Instances of this class usually don't need to be created.
     */
    protected TestSuite() {
    }

    /**
     * Returns a new array of the given type and length.
     *
     * @param <T>    The compile-time type of the {@code type} class argument.
     * @param type   The factory interface for which an array is desired.
     * @param length The length of the array to create.
     * @return A new array for components of the given type.
     */
    @SuppressWarnings("unchecked")
    private static <T extends Factory> T[] newArray(final Class<T> type, final int length) {
        return (T[]) Array.newInstance(type, length);
    }

    /**
     * Specifies the factory implementations to use for the given factory interface.
     *
     * @param <T>     The compile-time type of the {@code type} class argument.
     * @param type    The factory interface for which an implementation is specified.
     * @param factory The implementations to use for the given interface.
     */
    public static <T extends Factory> void setFactories(final Class<T> type, final T... factory) {
        final Iterable<? extends Factory> list = Arrays.asList(factory.clone());
        synchronized (TestCase.FACTORIES) {
            TestCase.FACTORIES.put(type, list);
        }
    }

    /**
     * Returns the factory implementations explicitely given by the last call to
     * {@link #setFactories(Class, T[])} for the given interface. This method does
     * not scan the {@code META-INF/services/<T>} entries.
     *
     * @param <T>  The compile-time type of the {@code type} class argument.
     * @param type The factory interface for which an implementations is desired.
     * @return     The implementations for the given interface, or {@code null} if none.
     */
    public static <T extends Factory> T[] getFactories(final Class<T> type) {
        final Iterable<? extends Factory> factories;
        synchronized (TestCase.FACTORIES) {
            factories = TestCase.FACTORIES.get(type);
        }
        if (factories instanceof Collection<?>) {
            final Collection<? extends Factory> collection = (Collection<? extends Factory>) factories;
            return collection.toArray(newArray(type, collection.size()));
        }
        return null;
    }

    /**
     * Clears all factories specified to the {@link #setFactories(Class, T[])} method, and clears
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
