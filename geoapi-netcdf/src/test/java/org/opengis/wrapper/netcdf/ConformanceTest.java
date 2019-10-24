/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.EnumSet;
import org.opengis.util.Factory;
import org.opengis.test.TestSuite;
import org.opengis.test.Configuration;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.ToleranceModifiers;
import org.opengis.test.ValidatorContainer;
import org.opengis.test.ImplementationDetails;
import org.opengis.referencing.operation.MathTransform;

import static org.opengis.test.CalculationType.*;


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
     * The configuration of our netCDF tests.
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
            if (factory instanceof NetcdfTransformFactory) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the map of tests to disable for this implementation, or {@code null}
     * if the given factories are not netCDF implementations.
     */
    @Override
    public synchronized Configuration configuration(final Factory... factories) {
        if (!isOurImplementation(factories)) {
            return null;
        }
        if (configuration == null) {
            configuration = new Configuration();
            configuration.unsupported(
                    Configuration.Key.isStandardNameSupported,
                    Configuration.Key.isStandardAliasSupported,
                    Configuration.Key.isDerivativeSupported);
            /*
             * Our objects are not yet strictly ISO compliant, so be lenient...
             */
            final ValidatorContainer validators = new ValidatorContainer();
            validators.crs.enforceStandardNames = false;
            validators.naming.requireMandatoryAttributes = false;
            validators.metadata.requireMandatoryAttributes = false;
            validators.coordinateOperation.requireMandatoryAttributes = false;
            configuration.put(Configuration.Key.validators, validators);
        }
        return configuration;
    }

    /**
     * Relaxes the tolerance threshold for some transforms to be tested.
     */
    @Override
    public ToleranceModifier tolerance(final MathTransform transform) {
        return ToleranceModifiers.scale(EnumSet.of(DIRECT_TRANSFORM, INVERSE_TRANSFORM), 100, 100);
    }
}
