/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example;

import org.opengis.util.Factory;
import org.opengis.test.TestSuite;
import org.opengis.test.Configuration;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.ImplementationDetails;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.example.referencing.SimpleTransformFactory;
import org.opengis.example.util.SimpleNameFactory;


/**
 * Runs all supported tests from the
 * <code><a href="http://www.geoapi.org/conformance/index.html">geoapi-conformance</a></code>
 * module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ConformanceTest extends TestSuite implements ImplementationDetails {
    /**
     * The configuration of our tests.
     * Will be created when first needed.
     */
    private Configuration configuration;

    /**
     * Returns {@code true} if at least one factory in the given array is our implementation.
     * We will returns a configuration map only for our own implementation, and don't propose
     * anything for implementations we don't known about.
     */
    private static boolean isOurImplementation(final Factory[] factories) {
        for (final Factory factory : factories) {
            if (factory instanceof SimpleNameFactory ||
                factory instanceof SimpleTransformFactory)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the map of tests to disable for this implementation, or {@code null}
     * if the given factories are not the expected implementations.
     */
    @Override
    public synchronized Configuration configuration(final Factory... factories) {
        if (!isOurImplementation(factories)) {
            return null;
        }
        if (configuration == null) {
            configuration = new Configuration();
            configuration.unsupported(
                    Configuration.Key.isMultiLocaleSupported,
                    Configuration.Key.isMixedNameSyntaxSupported);
        }
        return configuration;
    }

    /**
     * Returns {@code null} since there is not transform to test.
     */
    @Override
    public ToleranceModifier tolerance(final MathTransform transform) {
        return null;
    }
}
