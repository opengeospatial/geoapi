/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2024 Open Geospatial Consortium, Inc.
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
 * </ul>
 *
 * @version 3.1
 * @since 2.2
 */
module org.opengis.geoapi.conformance {
    requires transitive java.logging;
    requires transitive java.desktop;
    requires transitive java.measure;
    requires transitive org.opengis.geoapi.pending;
    requires transitive org.junit.jupiter.api;

    exports org.opengis.geoapi;
    exports org.opengis.test;
    exports org.opengis.test.util;
    exports org.opengis.test.metadata;
    exports org.opengis.test.referencing;
    exports org.opengis.test.geometry;
    exports org.opengis.test.coverage.image;
    exports org.opengis.test.dataset;
    exports org.opengis.test.report;
}
