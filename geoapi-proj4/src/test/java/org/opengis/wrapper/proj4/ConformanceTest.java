/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.util.EnumSet;
import org.opengis.util.Factory;
import org.opengis.test.TestSuite;
import org.opengis.test.Configuration;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.ToleranceModifiers;
import org.opengis.test.ValidatorContainer;
import org.opengis.test.ImplementationDetails;
import org.opengis.test.referencing.ParameterValidator;
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
     * The configuration of our Proj.4 tests.
     * Will be created when first needed.
     */
    private Configuration configuration;

    /**
     * Returns {@code true} if at least one factory in the given array is our implementation.
     * We will return a configuration map only for our own implementation, and don't propose
     * anything for implementations we don't known about.
     */
    private static boolean isOurImplementation(final Factory[] factories) {
        for (final Factory factory : factories) {
            if (factory instanceof PJFactory) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the map of tests to disable for this implementation, or {@code null}
     * if the given factories are not Proj.4 implementations.
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
                    Configuration.Key.isDependencyIdentificationSupported,
                    Configuration.Key.isDerivativeSupported,
                    Configuration.Key.isNonSquareMatrixSupported,
                    Configuration.Key.isNonBidimensionalSpaceSupported);
            /*
             * Objects created from Proj.4 definition strings are not strictly ISO 19111 compliant, so be lenient.
             */
            final ValidatorContainer validators = new ValidatorContainer();
            validators.parameter = new ParameterValidator(validators);
            validators.naming.requireMandatoryAttributes = false;
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
        if (transform instanceof PJOperation.Projection) {
            final PJDatum pj = (((PJOperation) transform).target).pj;
            final String projection = pj.getParameter("+proj=");
            if (projection != null) {
                if (projection.equals("cass")) {
                    // Relax tolerance of Cassini-Soldner from 0.01 metre to 1 metre.
                    return ToleranceModifiers.scale(EnumSet.of(DIRECT_TRANSFORM, INVERSE_TRANSFORM), 100, 100);
                }
            }
        }
        return null;
    }
}
