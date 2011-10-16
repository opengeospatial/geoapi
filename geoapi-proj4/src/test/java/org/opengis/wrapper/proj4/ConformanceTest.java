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
package org.opengis.wrapper.proj4;

import java.util.EnumSet;
import java.util.Properties;
import org.opengis.util.Factory;
import org.opengis.test.TestSuite;
import org.opengis.test.Validators;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.ToleranceModifiers;
import org.opengis.test.SupportedOperation;
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
     * The configuration of our Proj4 tests.
     */
    private static final Properties CONFIGURATION = new Properties();
    static {
        SupportedOperation.unsupported(CONFIGURATION,
                SupportedOperation.DERIVATIVE_TRANSFORM);
        /*
         * Our objects are not yet strictly ISO 19111 compliant, so be lenient...
         */
        Validators.DEFAULT.coordinateOperation.requireMandatoryAttributes = false;
    }

    /**
     * Returns the map of tests to disable for this implementation.
     */
    @Override
    public Properties configuration(final Factory... factories) {
        return CONFIGURATION;
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
