/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
import org.junit.jupiter.api.TestInstance;

import org.opengis.util.Factory;


/**
 * Base class of all GeoAPI tests.
 * All concrete subclasses need at least one {@link Factory} for instantiating the objects to test.
 * The factories must be specified at subclasses construction time either directly by the implementer,
 * or indirectly through dependency injection.
 *
 * <h2>Test case life cycle</h2>
 * GeoAPI test cases contain states as non-final protected fields.
 * Therefor, a new {@code TestCase} instance should be created for each test method to run
 * ({@link org.junit.jupiter.api.TestInstance.Lifecycle#PER_METHOD Lifecycle.PER_METHOD}).
 * If {@link org.junit.jupiter.api.TestInstance.Lifecycle#PER_CLASS PER_CLASS} is desired,
 * then subclasses are responsible for resetting these fields after each test.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp abstract class TestCase {
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
     * <h4>Example</h4>
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
     * Creates a new test case.
     */
    protected TestCase() {
        this.validators = Objects.requireNonNull(Validators.DEFAULT, "Validators.DEFAULT shall not be null.");
        this.units      = Units.getDefault();
    }

    /**
     * Returns booleans indicating whether the given operations are enabled.
     * By default, every operations are enabled.
     *
     * @param  properties  the key for which the flags are wanted.
     * @return an array of the same length as {@code properties} in which each element at index
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
