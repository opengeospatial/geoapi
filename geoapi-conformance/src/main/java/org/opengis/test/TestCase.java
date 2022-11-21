/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
import java.util.logging.LogRecord;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

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
    static final Map<Class<? extends Factory>, Iterable<? extends Factory>> FACTORIES = new HashMap<>();

    /**
     * The service loader to use for loading {@link FactoryFilter}.
     *
     * <p>Accesses to this field must be synchronized on itself. If both {@code FACTORIES} and
     * {@code FACTORY_FILTER} are synchronized, then {@code FACTORIES} must be synchronized first.</p>
     */
    private static ServiceLoader<FactoryFilter> factoryFilter;

    /**
     * The service loader to use for loading {@link ImplementationDetails}.
     *
     * <p>Accesses to this field must be synchronized on itself. If both {@code FACTORIES}
     * and {@code IMPLEMENTATION_DETAILS} are synchronized, then {@code FACTORIES} must be
     * synchronized first.</p>
     */
    private static ServiceLoader<ImplementationDetails> implementationDetails;

    /**
     * The class loader to use for searching implementations, or {@code null} for the default.
     */
    private static ClassLoader classLoader;

    /**
     * Sets the class loader to use for loading implementations. A {@code null} value restores
     * the default {@linkplain Thread#getContextClassLoader() context class loader}.
     *
     * @param loader  the class loader to use, or {@code null} for the default.
     */
    static void setClassLoader(final ClassLoader loader) {
        synchronized (FACTORIES) {
            if (loader != classLoader) {
                classLoader = loader;
                factoryFilter = null;
                implementationDetails = null;
            }
        }
    }

    /**
     * Creates a service loader for the given type. This method must be invoked from a block
     * synchronized on {@link FACTORIES}.
     */
    private static <T> ServiceLoader<T> load(final Class<T> service) {
        return (classLoader == null) ? ServiceLoader.load(service)
                : ServiceLoader.load(service, classLoader);
    }

    /**
     * Returns the current {@link #factoryFilter} instance, creating a new one if needed.
     */
    static ServiceLoader<FactoryFilter> getFactoryFilter() {
        synchronized (FACTORIES) {
            if (factoryFilter == null) {
                factoryFilter = load(FactoryFilter.class);
            }
            return factoryFilter;
        }
    }

    /**
     * Returns the current {@link #implementationDetails} instance, creating a new one if needed.
     */
    static ServiceLoader<ImplementationDetails> getImplementationDetails() {
        synchronized (FACTORIES) {
            if (implementationDetails == null) {
                implementationDetails = load(ImplementationDetails.class);
            }
            return implementationDetails;
        }
    }

    /**
     * The test listeners. We intentionally copy the full array every time a listener is
     * added or removed. We do not clone the array used by the {@link #listener} field,
     * so it is important that any array instance is never modified after creation.
     *
     * @see #addTestListener(TestListener)
     * @see #removeTestListener(TestListener)
     * @see #getTestListeners()
     */
    private static TestListener[] listeners = new TestListener[0];

    /**
     * Returns all currently registered test listeners, or an empty array if none.
     * This method returns directly the internal array, so it is important to never modify it.
     * This method is for internal usage by the {@link #listener} field only.
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    static synchronized TestListener[] getTestListeners() {
        return listeners;
    }

    /**
     * A JUnit {@linkplain Rule rule} for listening to test execution events. This rule forwards
     * events to all {@linkplain TestSuite#addTestListener(TestListener) registered listeners}.
     *
     * <p>This field is public because JUnit requires us to do so, but should be considered as
     * an implementation details (it should have been a private field).</p>
     *
     * @since 3.1
     */
    @Rule
    public final TestWatcher listener = new TestWatcher() {
        /**
         * A snapshot of the test listeners. We make this snapshot at rule creation time
         * in order to be sure that the same set of listeners is notified for all phases
         * of the test method being run.
         */
        private final TestListener[] listeners = getTestListeners();

        /**
         * Invoked when a test is about to start.
         */
        @Override
        protected void starting(final Description description) {
            final TestEvent event = new TestEvent(TestCase.this, description);
            for (final TestListener listener : listeners) {
                listener.starting(event);
            }
        }

        /**
         * Invoked when a test succeeds.
         */
        @Override
        protected void succeeded(final Description description) {
            final TestEvent event = new TestEvent(TestCase.this, description);
            for (final TestListener listener : listeners) {
                listener.succeeded(event);
            }
        }

        /**
         * Invoked when a test fails. If the failure occurred in an optional part of the test,
         * logs an information message for helping the developer to disable that test if (s)he wishes.
         */
        @Override
        protected void failed(final Throwable exception, final Description description) {
            final TestEvent event = new TestEvent(TestCase.this, description);
            final Configuration.Key<Boolean> tip = configurationTip;
            if (tip != null) {
                event.configurationTip = tip;
                final Logger logger = Logger.getLogger("org.opengis.test");
                final LogRecord record = new LogRecord(Level.INFO, "A test failure occurred while "
                        + "testing an optional feature. To skip that part of the test, set the '"
                        + tip.name() + "' boolean field to false or specify that value in the "
                        + "Configuration map.");
                record.setLoggerName(logger.getName());
                record.setSourceClassName(event.getClassName());
                record.setSourceMethodName(event.getMethodName());
                logger.log(record);
            }
            for (final TestListener listener : listeners) {
                listener.failed(event, exception);
            }
        }

        /**
         * Invoked when a test method finishes (whether passing or failing)
         */
        @Override
        protected void finished(final Description description) {
            final TestEvent event = new TestEvent(TestCase.this, description);
            for (final TestListener listener : listeners) {
                listener.finished(event);
            }
        }
    };

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
     * <blockquote><pre>&#64;Test
     *public void myTest() {
     *    if (isDerivativeSupported) {
     *        configurationTip = Configuration.Key.isDerivativeSupported;
     *        // Do some tests the require support of math transform derivatives.
     *    }
     *    configurationTip = null;
     *}</pre></blockquote>
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
     * @param factories  the factories to be used by the test. Those factories will be given to
     *        {@link ImplementationDetails#configuration(Factory[])} in order to decide which
     *        {@linkplain #validators} to use.
     *
     * @since 3.1
     */
    protected TestCase(final Factory... factories) {
        Objects.requireNonNull(factories, "Given 'factories' array cannot be null.");
        this.factories = factories;
        Units units = null;
        ValidatorContainer validators = null;
        final ServiceLoader<ImplementationDetails> services = getImplementationDetails();
        synchronized (services) {
            for (final ImplementationDetails impl : services) {
                final Configuration config = impl.configuration(factories);
                if (config != null) {
                    if (units == null) {
                        units = config.get(Configuration.Key.units);
                    }
                    if (validators == null) {
                        validators = config.get(Configuration.Key.validators);
                    }
                    if (units != null && validators != null) {
                        break;          // We got all information will we looking for, no need to continue.
                    }
                }
            }
        }
        if (units == null) {
            units = Units.getDefault();
        }
        if (validators == null) {
            Objects.requireNonNull(validators = Validators.DEFAULT, "Validators.DEFAULT shall not be null.");
        }
        this.units = units;
        this.validators = validators;
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
     * <blockquote><pre>&#64;Parameterized.Parameters
     *public static List&lt;Factory[]&gt; factories() {
     *    return factories(CRSFactory.class, CSFactory.class, DatumFactory.class);
     *}</pre></blockquote>
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
        return factories(null, types);
    }

    /**
     * Returns factory instances for given factory interfaces, excluding the factories filtered
     * by the given filter. This method performs the same work than {@link #factories(Class[])}
     * except that the given filter is applied in addition to any filter found on the classpath.
     *
     * <p>The main purpose of this method is to get {@link org.opengis.referencing.AuthorityFactory}
     * instances for a given authority name.</p>
     *
     * @param  filter  an optional factory filter to use in addition to any filter declared in
     *                 the classpath, or {@code null} if none.
     * @param  types   the kind of factories to fetch.
     * @return all combinations of factories of the given kind. Each list element is an array
     *         having the same length than {@code types}.
     *
     * @since 3.1
     */
    @SafeVarargs
    protected static List<Factory[]> factories(final FactoryFilter filter, final Class<? extends Factory>... types) {
        final List<Factory[]> factories = new ArrayList<>(4);
        try {
            synchronized (FACTORIES) {
                if (!factories(filter, types, factories)) {
                    // The user has invoked TestSuite.setFactories(…), for example inside
                    // his FactoryFilter.filter(…) method. Let be lenient and try again.
                    // If the second try fails for the same reason, we will give up.
                    factories.clear();
                    if (!factories(filter, types, factories)) {
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
    private static boolean factories(final FactoryFilter filter,
            final Class<? extends Factory>[] types, final List<Factory[]> factories)
    {
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
                if (filter(type, factory, filter)) {
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
            }
            // Check if TestSuite.setFactories(…) has been invoked while we were iterating.
            // The method may have been invoked by a FactoryFilter.filter(…) method for
            // example. While not an encouraged practice, we try to be a little bit more
            // robust than not checking at all.
            if (FACTORIES.get(type) != choices) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns {@code true} if the given factory can be tested. This method iterates over all
     * registered {@link FactoryFilter} and ensures that all of them accept the given factory.
     */
    private static <T extends Factory> boolean filter(final Class<T> category, final Factory factory, final FactoryFilter filter) {
        final T checked = category.cast(factory);
        if (filter != null && !filter.filter(category, checked)) {
            return false;
        }
        final ServiceLoader<FactoryFilter> services = getFactoryFilter();
        synchronized (services) {
            for (final FactoryFilter impl : services) {
                if (!impl.filter(category, checked)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns booleans indicating whether the given operations are enabled. By default, every
     * operations are enabled. However, if any {@link ImplementationDetails} instance found on the
     * classpath returns a {@linkplain ImplementationDetails#configuration configuration} map
     * having the value {@link Boolean#FALSE} for a given key, then the boolean value corresponding
     * to that key is set to {@code false}.
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
        final ServiceLoader<ImplementationDetails> services = getImplementationDetails();
        synchronized (services) {
            for (final ImplementationDetails impl : services) {
                final Configuration config = impl.configuration(factories);
                if (config != null) {
                    boolean atLeastOneTestIsEnabled = false;
                    for (int i=0; i<properties.length; i++) {
                        if (isEnabled[i]) {
                            final Boolean value = config.get(properties[i]);
                            if (value != null && !(isEnabled[i] = value)) {
                                continue;                       // Leave 'atLeastOneTestIsEnabled' unchanged.
                            }
                            atLeastOneTestIsEnabled = true;
                        }
                    }
                    if (!atLeastOneTestIsEnabled) {
                        break;                                  // No need to continue scanning the classpath.
                    }
                }
            }
        }
        return isEnabled;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * The content of this map depends on the {@code TestCase} subclass and on the
     * values returned by the {@link ImplementationDetails#configuration(Factory[])}
     * method for the factories being tested. For a description of the map content,
     * see any of the following subclasses:
     *
     * <ul>
     *   <li>{@link org.opengis.test.referencing.AffineTransformTest#configuration()}</li>
     *   <li>{@link org.opengis.test.referencing.ParameterizedTransformTest#configuration()}</li>
     *   <li>{@link org.opengis.test.referencing.AuthorityFactoryTest#configuration()}</li>
     *   <li>{@link org.opengis.test.referencing.gigs.AuthorityFactoryTestCase#configuration()}</li>
     * </ul>
     *
     * @return the configuration of the test being run, or an empty map if none.
     *         This method returns a modifiable map in order to allow subclasses to modify it.
     *
     * @see ImplementationDetails#configuration(Factory[])
     *
     * @since 3.1
     */
    public Configuration configuration() {
        final Configuration configuration = new Configuration();
        configuration.put(Configuration.Key.units,      units);
        configuration.put(Configuration.Key.validators, validators);
        return configuration;
    }

    /**
     * Implementation of the {@link TestSuite#addTestListener(TestListener)} public method.
     *
     * @param listener  the listener to add. {@code null} values are silently ignored.
     *
     * @deprecated To be replaced by JUnit 5 listener mechanism.
     */
    @Deprecated
    static synchronized void addTestListener(final TestListener listener) {
        if (listener != null) {
            final int length = listeners.length;
            listeners = Arrays.copyOf(listeners, length + 1);
            listeners[length] = listener;
        }
    }

    /**
     * Implementation of the {@link TestSuite#removeTestListener(TestListener)} public method.
     *
     * @param listener  the listener to remove. {@code null} values are silently ignored.
     *
     * @deprecated To be replaced by JUnit 5 listener mechanism.
     */
    @Deprecated
    static synchronized void removeTestListener(final TestListener listener) {
        for (int i=listeners.length; --i>=0;) {
            if (listeners[i] == listener) {
                final int length = listeners.length - 1;
                System.arraycopy(listeners, i, listeners, i+1, length-i);
                listeners = Arrays.copyOf(listeners, length);
                break;
            }
        }
    }
}
