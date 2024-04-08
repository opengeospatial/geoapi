/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.operation.CoordinateOperation;
import org.junit.jupiter.api.Test;

import static org.opengis.referencing.ReferenceSystem.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the value of key constants.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 */
public final class KeyConstantsTest {
    /**
     * Creates a new test case.
     */
    public KeyConstantsTest() {
    }

    /**
     * Ensures that the key that are expected to be the same are really the same.
     * We use {@code assertSame} instead of {@code assertEquals} because we
     * expect the JVM to have {@linkplain String#intern internalized} the strings.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testSame() {
        assertSame(SCOPE_KEY,              Datum              .SCOPE_KEY);
        assertSame(SCOPE_KEY,              CoordinateOperation.SCOPE_KEY);
        assertSame(DOMAIN_OF_VALIDITY_KEY, Datum              .DOMAIN_OF_VALIDITY_KEY);
        assertSame(DOMAIN_OF_VALIDITY_KEY, CoordinateOperation.DOMAIN_OF_VALIDITY_KEY);
    }
}
