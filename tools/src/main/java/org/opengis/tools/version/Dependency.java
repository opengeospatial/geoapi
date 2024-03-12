/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;


/**
 * Represents a GeoAPI dependency, together with its path in the Maven repository.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
enum Dependency {
    /**
     * The dependency for units of measurement.
     */
    UNIT_OF_MEASURES {
        @Override
        public String[] pathInMavenRepository(final Version geoapiVersion) {
            return new String[] {"javax/measure/unit-api/1.0/unit-api-1.0.jar"};
        }
    },

    /**
     * The JUnit dependency for tests.
     */
    JUNIT {
        @Override
        public String[] pathInMavenRepository(final Version geoapiVersion) {
            return new String[] {
                "org/junit/jupiter/junit-jupiter-api/5.10.2/junit-jupiter-api-5.10.2.jar"
            };
        }
    };

    /**
     * Returns the path in the Maven repository for this dependency for the given GeoAPI version.
     * Those dependencies are hard-coded for now and doesn't need to be accurate for the needs of
     * this package. We report only major changes, like switching from JSR-275 to the JSR-363 API.
     */
    public abstract String[] pathInMavenRepository(Version geoapiVersion);
}
