/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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

import org.opengis.util.Factory;
import org.opengis.referencing.operation.MathTransform;


/**
 * Provides optional information about the implementation being tested. Implementers can
 * provide an instance of this interface in their test packages and declare their instance
 * in the {@code META-INF/services/org.opengis.test.ImplementationDetails} file. GeoAPI
 * will iterate over every {@code ImplementationDetails} found on the classpath when needed:
 *
 * <ul>
 *   <li>Before each execution of a configurable {@link TestCase}, in order to check which tests
 *   (if any) should be disabled.</li>
 *
 *   <li>Before each execution of a {@link TestCase} performing numerical calculation, in
 *   order to determine if a specific implementation needs to relax the tolerance threshold.</li>
 * </ul>
 *
 * If no instance of {@code ImplementationDetails} is registered, then GeoAPI assumes that
 * all tests are enabled with their default tolerance threshold. This is equivalent to using
 * an {@code ImplementationDetails} instance where every methods return {@code null}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public interface ImplementationDetails {
    /**
     * Returns the set of tests that should be disabled, or {@code null} if none.
     * If non-null, then the returned map can contain some {@link org.opengis.test.Configuration.Key}
     * associated to the {@link Boolean#FALSE} value. Example:
     *
     * {@snippet lang="java" :
     * @Override
     * public Configuration configuration(Factory... factories) {
     *     Configuration config = new Configuration();
     *     config.unsupported(Configuration.Key.isDerivativeSupported, Configuration.Key.isNonSquareMatrixSupported);
     *     return config;
     * }}
     *
     * If more than one {@code ImplementationDetails} is found on the classpath, then a logical {@code AND}
     * is performed on the boolean values returned by all {@code ImplementationDetails.configuration(…)} calls.
     *
     * <p>This method is invoked often (typically one or two time before every single test method),
     * so implementers may want to cache their configuration map.</p>
     *
     * @param  factories  the factories to be tested.
     * @return the collection of tests to disable for the given factories, or {@code null} if none.
     *
     * @see TestCase#configuration()
     */
    Configuration configuration(Factory... factories);

    /**
     * Returns an object for modifying the tolerance thresholds when testing the given math transform,
     * or {@code null} if no change is needed. This method should return a non-null value only if the
     * implementation being tested does not have the accuracy expected by the {@link TestCase}. In
     * such case, the object returned by this method can be used for relaxing the tolerance threshold.
     *
     * <p>If more than one {@code ImplementationDetails} return a non-null value, then the threshold
     * used by GeoAPI will be the maximal value returned by all {@code ToleranceModifier} objects.</p>
     *
     * @param  transform  the transform being tested.
     * @return an object for modifying the tolerance thresholds, or {@code null} if no change is needed.
     */
    ToleranceModifier tolerance(MathTransform transform);
}
