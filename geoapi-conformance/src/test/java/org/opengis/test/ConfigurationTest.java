/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import org.opengis.referencing.operation.MathTransformFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link Configuration}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ConfigurationTest {
    /**
     * Creates a new test case.
     */
    public ConfigurationTest() {
    }

    /**
     * The {@link org.opengis.test.runner} package requires that all key names related to
     * enabling / disabling tests begin with {@code "is"} and end with {@code "Supported"}.
     */
    @Test
    public void testKeyNames() {
        for (final Configuration.Key<?> e : Configuration.Key.values()) {
            if (e.type == Boolean.class
                    && e != Configuration.Key.isFactoryPreservingUserValues
                    && e != Configuration.Key.isValidationEnabled
                    && e != Configuration.Key.isToleranceRelaxed)
            {
                final String key = e.name();
                assertTrue(key.startsWith("is"), "Expected \"is\" prefix.");
                assertTrue(key.endsWith("Supported"), "Expected \"Supported\" prefix.");
            }
        }
    }

    /**
     * Asks for an existing key.
     */
    @Test
    public void testValueOf() {
        assertSame(Configuration.Key.isDoubleToDoubleSupported,
                   Configuration.Key.valueOf("isDoubleToDoubleSupported", Boolean.class));
        assertSame(Configuration.Key.mtFactory,
                   Configuration.Key.valueOf("mtFactory", MathTransformFactory.class));
    }

    /**
     * Tests the type checking.
     */
    @Test
    public void testWrongValueOf() {
        assertThrows(ClassCastException.class,
                () -> assertSame(Configuration.Key.isDoubleToDoubleSupported,
                                 Configuration.Key.valueOf("isDoubleToDoubleSupported", String.class)));
    }
}
