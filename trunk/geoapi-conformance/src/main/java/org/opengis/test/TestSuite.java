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
import org.opengis.referencing.operation.MathTransform;


/**
 * The suite of every tests defined in the GeoAPI conformance module.
 * The test cases included in this test suite are:
 * <p>
 * <ul>
 *   <li>{@link org.opengis.test.util.NameTest}</li>
 *   <li>{@link org.opengis.test.referencing.ObjectFactoryTest}</li>
 *   <li>{@link org.opengis.test.referencing.AuthorityFactoryTest}</li>
 *   <li>{@link org.opengis.test.referencing.AffineTransformTest}</li>
 *   <li>{@link org.opengis.test.referencing.ParameterizedTransformTest}</li>
 * </ul>
 * <p>
 * All tests use {@link Factory} instances that are specific to the implementation being tested.
 * By default {@code TestSuite} fetches the factory implementations with {@link ServiceLoader},
 * which will scan every <code>META-INF/services/org.opengis.<var>TheFactory</var></code> files
 * on the classpath. However implementors can override this default mechanism with explicit calls
 * to the {@link #setFactories(Class, Factory[])} method.
 * <p>
 * Implementors can have some control on the tests (factories to use, features to test, tolerance
 * thresholds) by registering their {@link FactoryFilter} or {@link ImplementationDetails} in the
 * {@code META-INF/services/} directory. As an alternative, implementors can also extend directly
 * the various {@link TestCase} subclasses.
 * <p>
 * <b>Example:</b> The test suite below declares that the tolerance threshold for {@code MyProjection}
 * needs to be relaxed by a factor 10 during inverse projections.
 *
 * <blockquote><pre>package org.myproject;
 *
 *import org.opengis.test.TestSuite;
 *import org.opengis.test.CalculationType;
 *import org.opengis.test.ToleranceModifier;
 *import org.opengis.test.ToleranceModifiers;
 *import org.opengis.test.ImplementationDetails;
 *import org.opengis.referencing.operation.MathTransform;
 *import org.opengis.util.Factory;
 *import java.util.Properties;
 *
 *public class GeoapiTest extends TestSuite implements {@linkplain ImplementationDetails} {
 *    &#64;Override
 *    public Properties {@linkplain ImplementationDetails#configuration configuration}({@linkplain Factory}... factories) {
 *        return null;
 *    }
 *
 *    &#64;Override
 *    public {@linkplain ToleranceModifier} {@linkplain ImplementationDetails#needsRelaxedTolerance needsRelaxedTolerance}({@linkplain MathTransform} transform) {
 *        if (transform instanceof <var>MyProjection</var>) {
 *            return {@linkplain ToleranceModifiers#scale ToleranceModifiers.scale}(EnumSet.of({@linkplain CalculationType#INVERSE_TRANSFORM}), 1, 10);
 *        }
 *        return null;
 *    }
 *}</pre></blockquote>
 *
 * The above {@code AllTests} class needs to be registered in the {@code META-INF/services/}
 * directory if the implementation details shall be honored (otherwise the tests will be run,
 * but the implementation details will be ignored).
 *
 * @see ImplementationDetails
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
  org.opengis.test.referencing.AuthorityFactoryTest.class,
  org.opengis.test.referencing.AffineTransformTest.class,
  org.opengis.test.referencing.ParameterizedTransformTest.class
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
        if (type == null) {
            throw new NullPointerException("type must not be null"); // JDK7: Objects.requireNonNull
        }
        final Iterable<? extends Factory> list = Arrays.asList(factory.clone());
        synchronized (TestCase.FACTORIES) {
            TestCase.FACTORIES.put(type, list);
        }
    }

    /**
     * Returns the factory implementations explicitely given by the last call to
     * {@link #setFactories(Class, Factory[])} for the given interface. This method does
     * not scan the {@code META-INF/services/<T>} entries.
     *
     * @param <T>  The compile-time type of the {@code type} class argument.
     * @param type The factory interface for which an implementations is desired.
     * @return     The implementations for the given interface, or {@code null} if none.
     */
    public static <T extends Factory> T[] getFactories(final Class<T> type) {
        if (type == null) {
            throw new NullPointerException("type must not be null"); // JDK7: Objects.requireNonNull
        }
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
     * Clears all factories specified to the {@link #setFactories(Class, Factory[])} method, and clears
     * all {@linkplain ServiceLoader service loader} caches. After this method call, all factories
     * will be reloaded when first needed. This method is intended for use in situations in which
     * new factories can be installed or removed into a running Java virtual machine.
     *
     * @see ServiceLoader#reload()
     */
    public static void clear() {
        synchronized (TestCase.FACTORIES) {
            synchronized (TestCase.FACTORY_FILTER) {
                TestCase.FACTORY_FILTER.reload();
            }
            synchronized (TestCase.IMPLEMENTATION_DETAILS) {
                TestCase.IMPLEMENTATION_DETAILS.reload();
            }
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
