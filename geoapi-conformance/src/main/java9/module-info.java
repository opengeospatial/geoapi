/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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


/**
 * Test suites for GeoAPI implementations.
 * The GeoAPI conformance module provides three kinds of Java classes:
 *
 * <ul>
 *   <li>{@linkplain org.opengis.test.Validators} for testing the conformance of
 *     existing instances of GeoAPI interfaces.</li>
 *   <li>{@linkplain org.opengis.test.TestCase} as the base class of all JUnit tests
 *     in this module, which can be extended by developers on a case-by-case basis.</li>
 *   <li>{@linkplain org.opengis.test.TestSuite} as the aggregation of all JUnit tests
 *     defined in this module.</li>
 * </ul>
 *
 * Implementers can alter the tests, for example in order to disable testing of some unsupported features,
 * either by extending directly the appropriate {@code TestCase} subclass (easiest way),
 * or by providing {@linkplain org.opengis.test.ImplementationDetails implementation details} in a central place.
 * See the <a href="http://www.geoapi.org/conformance/index.html">web site</a> for examples.
 *
 * @version 4.0
 * @since 2.2
 */
module org.opengis.geoapi.conformance {
    requires java.prefs;
    requires java.logging;
    requires transitive java.measure;
    requires transitive org.opengis.geoapi.pending;
    requires junit;

    exports org.opengis.test;
    exports org.opengis.test.util;
    exports org.opengis.test.metadata;
    exports org.opengis.test.referencing;
    exports org.opengis.test.referencing.gigs;
    exports org.opengis.test.wkt;
    exports org.opengis.test.geometry;
    exports org.opengis.test.coverage.image;
    exports org.opengis.test.report;

    uses org.opengis.test.FactoryFilter;
    uses org.opengis.test.ImplementationDetails;
}
