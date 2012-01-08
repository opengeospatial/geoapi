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
package org.opengis.wrapper.netcdf;

import java.util.EnumSet;
import org.opengis.util.Factory;
import org.opengis.test.TestSuite;
import org.opengis.test.Validators;
import org.opengis.test.Configuration;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.ToleranceModifiers;
import org.opengis.test.ImplementationDetails;
import org.opengis.referencing.operation.MathTransform;

import static org.opengis.test.CalculationType.*;


/**
 * Runs all supported tests from the {@code geoapi-conformance} module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ConformanceTest extends TestSuite implements ImplementationDetails {
    /**
     * The configuration of our NetCDF tests.
     */
    private static final Configuration CONFIGURATION = new Configuration();
    static {
        CONFIGURATION.unsupported(
                Configuration.Key.isNameSupported,
                Configuration.Key.isAliasSupported,
                Configuration.Key.isDerivativeSupported);
        /*
         * Our objects are not yet strictly ISO 19111 compliant, so be lenient...
         */
        Validators.DEFAULT.naming.requireMandatoryAttributes = false;
        Validators.DEFAULT.coordinateOperation.requireMandatoryAttributes = false;
    }

    /**
     * Returns the map of tests to disable for this implementation.
     */
    @Override
    public Configuration configuration(final Factory... factories) {
        return CONFIGURATION;
    }

    /**
     * Relaxes the tolerance threshold for some transforms to be tested.
     */
    @Override
    public ToleranceModifier tolerance(final MathTransform transform) {
        if (false) { // Not yet enabled.
            return ToleranceModifiers.scale(EnumSet.of(DIRECT_TRANSFORM, INVERSE_TRANSFORM), 100, 100);
        }
        return null;
    }
}
