/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
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


/**
 * Runs all supported tests from the
 * <code><a href="http://www.geoapi.org/geoapi-conformance/index.html">geoapi-conformance</a></code>
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
     * Returns the map of tests to disable for this implementation.
     */
    @Override
    public synchronized Configuration configuration(final Factory... factories) {
        if (configuration == null) {
            configuration = new Configuration();
            configuration.unsupported(
                    Configuration.Key.isMultiLocaleSupported,
                    Configuration.Key.isMixedNameSyntaxSupported);
        }
        return configuration;
    }

    /**
     * Returns {@code null} since there is not transform to test.
     */
    @Override
    public ToleranceModifier tolerance(final MathTransform transform) {
        return null;
    }
}
