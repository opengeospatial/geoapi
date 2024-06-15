/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the {@link TemporalOperatorName} code list.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class TemporalOperatorNameTest {
    /**
     * Creates a new test case.
     */
    public TemporalOperatorNameTest() {
    }

    /**
     * Verifies that the reverse values exist and that the revere of the reverse is the original value.
     */
    @Test
    public void testReversed() {
        final int[] counts = new int[2];
        for (TemporalOperatorName op : TemporalOperatorName.values()) {
            op.reversed().ifPresent((reverse) -> {
                assertSame(op, reverse.reversed().orElseThrow());
                counts[reverse == op ? 0 : 1]++;
            });
        }
        assertEquals( 2, counts[0], "Number of operations where the reverse is itself.");
        assertEquals(12, counts[1], "Number of operations having a different reverse.");
    }
}
