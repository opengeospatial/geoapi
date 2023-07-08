/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.opengis.util.Factory;


/**
 * Base class of all GeoAPI tests. All concrete subclasses need at least one {@linkplain Factory
 * factory} for instantiating the objects to test. The factories must be specified at subclasses
 * construction time either directly by the implementer, or indirectly by calls to the
 * {@link #factories(Class[])} method.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 *
 * @see TestSuite
 */
public strictfp abstract class TestCase {
    /**
     * An empty array of factories, as a convenience for
     * {@linkplain #TestCase() no-argument constructors}.
     */
    private static final Factory[] NO_FACTORY = new Factory[0];

    /**
     * The factories specified explicitly by the implementers, or the {@link ServiceLoader}
     * to use for loading those factories.
     *
     * <p>Accesses to this field must be synchronized on itself.</p>
     *
     * @see TestSuite#setFactories(Class, Factory[])
     */
    private static final Map<Class<? extends Factory>, Iterable<? extends Factory>> FACTORIES = new HashMap<>();

    /**
     * The class loader to use for searching implementations, or {@code null} for the default.
     */
    private static ClassLoader classLoader;

    /**
     * Creates a service loader for the given type. This method must be invoked from a block
     * synchronized on {@link FACTORIES}.
     */
    private static <T> ServiceLoader<T> load(final Class<T> service) {
        return (classLoader == null) ? ServiceLoader.load(service)
                : ServiceLoader.load(service, classLoader);
    }

    /**
     * The factories used by the test case to execute, or an empty array if none.
     * This array is given at construction time and is not cloned.
     */
    private final Factory[] factories;

    /**
     * Provider of units of measurement (degree, metre, second, <i>etc</i>), never {@code null}.
     * The {@link Units#degree()}, {@link Units#metre() metre()} and other methods shall return
     * {@link javax.measure.Unit} instances compatible with the units created by the {@link Factory}
     * instances to be tested. Those {@code Unit<?>} instances depend on the Unit of Measurement (JSR-373)
     * implementation used by the factories.
     * If no units were {@linkplain org.opengis.test.Configuration.Key#units explicitly specified},
     * then the {@linkplain Units#getDefault() default units} are used.
     *
     * @since 3.1
     */
    protected final Units units;

    /**
     * The set of {@link Validator} instances to use for verifying objects conformance (never {@code null}).
     * If no validators were {@linkplain org.opengis.test.Configuration.Key#validators explicitly specified},
     * then the {@linkplain Validators#DEFAULT default validators} are used.
     *
     * @since 3.1
     */
    protected final ValidatorContainer validators;

    /**
     * A tip set by subclasses during the execution of some optional tests.
     * In case of optional test failure, if this field is non-null, then a message will be logged at the
     * {@link java.util.logging.Level#INFO} for giving some tips to the developer about how he can disable the test.
     *
     * <p><b>Example</b></p>
     * {@snippet lang="java" :
     * @Test
     * public void myTest() {
     *     if (isDerivativeSupported) {
     *         configurationTip = Configuration.Key.isDerivativeSupported;
     *         // Do some tests the require support of math transform derivatives.
     *     }
     *     configurationTip = null;
     * }}
     *
     * @since 3.1
     */
    protected transient Configuration.Key<Boolean> configurationTip;

    /**
     * Creates a new test without factory. This constructor is provided for subclasses
     * that instantiate their test object directly, without using any factory.
     */
    protected TestCase() {
       this(NO_FACTORY);
    }

    /**
     * Creates a new test which will use the given factories to execute.
     *
     * @param factories  the factories to be used by the test.
     *
     * @since 3.1
     */
    protected TestCase(final Factory... factories) {
        this.factories  = Objects.requireNonNull(factories, "Given `factories` array cannot be null.");
        this.validators = Objects.requireNonNull(Validators.DEFAULT, "Validators.DEFAULT shall not be null.");
        this.units      = Units.getDefault();
    }

    /**
     * Returns factory instances for given factory interfaces. Each element in the returned list
     * is the arguments to give to the subclass constructor. There is typically only one element
     * in the list, but more elements could be included if many factory implementations are found
     * for the same interface.
     *
     * <p>This method is used by static methods having the {@link org.junit.runners.Parameterized.Parameters}
     * annotation in subclasses. For example if a subclass constructor expects 3 factories of kind
     * {@link org.opengis.referencing.crs.CRSFactory}, {@link org.opengis.referencing.cs.CSFactory}
     * and {@link org.opengis.referencing.datum.DatumFactory} in that order, then that subclass
     * contains the following method:</p>
     *
     * {@snippet lang="java" :
     * @Parameterized.Parameters
     * public static List&lt;Factory[]&gt; factories() {
     *     return factories(CRSFactory.class, CSFactory.class, DatumFactory.class);
     * }}
     *
     * Note that the arrays may contain null elements if no factory implementation were found
     * for a given interface. All GeoAPI test cases use {@link org.junit.Assume} checks in order
     * to disable any tests that require a missing factory.
     *
     * <p>If many factory implementations were found for a given interface, then this method
     * returns all possible combinations of those factories. For example if two instances
     * of interface {@code A} are found (named {@code A1} and {@code A2}), and two instances
     * of interface {@code B} are also found (named {@code B1} and {@code B2}), then this
     * method returns a list containing:</p>
     *
     * <blockquote><pre>{A1, B1}
     *{A2, B1}
     *{A1, B2}
     *{A2, B2}</pre></blockquote>
     *
     * The current implementation first checks the factories explicitly specified by calls to the
     * {@link TestSuite#setFactories(Class, Factory[])} method. In no factories were explicitly
     * specified, then this method searches the classpath using {@link ServiceLoader}.
     *
     * @param  types  the kind of factories to fetch.
     * @return all combinations of factories of the given kind. Each list element is an array
     *         having the same length than {@code types}.
     *
     * @see org.opengis.test.util.NameTest#factories()
     * @see org.opengis.test.referencing.ObjectFactoryTest#factories()
     * @see org.opengis.test.referencing.AuthorityFactoryTest#factories()
     * @see org.opengis.test.referencing.AffineTransformTest#factories()
     * @see org.opengis.test.referencing.ParameterizedTransformTest#factories()
     *
     * @since 3.1
     */
    @SafeVarargs
    protected static List<Factory[]> factories(final Class<? extends Factory>... types) {
        final List<Factory[]> factories = new ArrayList<>(4);
        try {
            synchronized (FACTORIES) {
                if (!factories(types, factories)) {
                    /*
                     * The user has invoked TestSuite.setFactories(…) during the iteration.
                     * Let be lenient and try again.
                     * If the second try fails for the same reason, we will give up.
                     */
                    factories.clear();
                    if (!factories(types, factories)) {
                        throw new ServiceConfigurationError("TestSuite.setFactories(…) has been invoked "
                                + "in the middle of a search for factories.");
                    }
                }
            }
        } catch (ServiceConfigurationError e) {
            // JUnit 4.10 eats the exception silently, so we need to log
            // it in order to allow users to figure out what is going.
            Logger.getLogger("org.opengis.test").log(Level.WARNING, e.toString(), e);
            throw e;                                          // To be caught by JUnit.
        }
        return factories;
    }

    /**
     * Implementation of the above {@code factories} method. The factories are added to
     * the given list. This method returns {@code true} on success, or {@code false} if
     * we detected that {@link TestSuite#setFactories(Class, T[])} has been invoked by
     * some user method while we were iterating. This check is done in an opportunist;
     * it is not fully reliable.
     */
    private static boolean factories(final Class<? extends Factory>[] types, final List<Factory[]> factories) {
        factories.add(new Factory[types.length]);
        for (int i=0; i<types.length; i++) {
            final Class<? extends Factory> type = types[i];
            Iterable<? extends Factory> choices = FACTORIES.get(type);
            if (choices == null) {
                choices = load(type);
                final Iterable<? extends Factory> old = FACTORIES.put(type, choices);
                if (old != null) {
                    // TestSuite.setFactories(…) has been invoked,  maybe as a result of user
                    // class initialization. Restores the user-provided value and declares that
                    // this operation failed.
                    FACTORIES.put(type, old);
                    return false;
                }
            }
            List<Factory[]> toUpdate = factories;
            for (final Factory factory : choices) {
                type.cast(factory);
                if (toUpdate == factories) {
                    toUpdate = Arrays.asList(factories.toArray(new Factory[factories.size()][]));
                } else {
                    for (int j=toUpdate.size(); --j>=0;) {
                        toUpdate.set(j, toUpdate.get(j).clone());
                    }
                    factories.addAll(toUpdate);
                }
                for (final Factory[] previous : toUpdate) {
                    previous[i] = factory;
                }
            }
            /*
             * Check if TestSuite.setFactories(…) has been invoked while we were iterating.
             * While not an encouraged practice, we try to be a little bit more robust than
             * not checking at all.
             */
            if (FACTORIES.get(type) != choices) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns booleans indicating whether the given operations are enabled.
     * By default, every operations are enabled.
     *
     * @param  properties  the key for which the flags are wanted.
     * @return an array of the same length than {@code properties} in which each element at index
     *         <var>i</var> indicates whether the {@code properties[i]} test should be enabled.
     *
     * @since 3.1
     */
    @SafeVarargs
    protected final boolean[] getEnabledFlags(final Configuration.Key<Boolean>... properties) {
        final boolean[] isEnabled = new boolean[properties.length];
        Arrays.fill(isEnabled, true);
        return isEnabled;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * The content of this map depends on the {@code TestCase} subclass.
     * For a description of the map content, see any of the following subclasses:
     *
     * <ul>
     *   <li>{@link org.opengis.test.referencing.AffineTransformTest#configuration()}</li>
     *   <li>{@link org.opengis.test.referencing.ParameterizedTransformTest#configuration()}</li>
     *   <li>{@link org.opengis.test.referencing.AuthorityFactoryTest#configuration()}</li>
     * </ul>
     *
     * @return the configuration of the test being run, or an empty map if none.
     *         This method returns a modifiable map in order to allow subclasses to modify it.
     *
     * @since 3.1
     */
    public Configuration configuration() {
        final Configuration configuration = new Configuration();
        configuration.put(Configuration.Key.units,      units);
        configuration.put(Configuration.Key.validators, validators);
        return configuration;
    }
}
