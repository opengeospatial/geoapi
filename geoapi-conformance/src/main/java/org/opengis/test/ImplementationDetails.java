/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
