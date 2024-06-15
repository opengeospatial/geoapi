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
package org.opengis.referencing.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the {@link AxisDirection} code list.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class AxisDirectionTest {
    /**
     * Creates a new test case.
     */
    public AxisDirectionTest() {
    }

    /**
     * Verifies that the opposite values exist and that the opposite of the opposite is the original value.
     */
    @Test
    public void testOpposites() {
        final int[] counts = new int[2];
        for (AxisDirection op : AxisDirection.values()) {
            op.opposite().ifPresent((opposite) -> {
                assertSame(op, opposite.opposite().orElseThrow());
                counts[opposite == op ? 0 : 1]++;
            });
        }
        assertEquals( 1, counts[0], "Number of directions where the opposite is itself.");
        assertEquals(36, counts[1], "Number of directions having a different opposite.");
    }

    /**
     * Verification of opposite directions on the rose. Because the code list values are declared in clockwise order,
     * all directions should be separated from their opposite direction by exactly 8 values.
     */
    @Test
    public void testOppositesOnRose() {
        for (AxisDirection op : AxisDirection.values()) {
            if (op.ordinal() >= AxisDirection.NORTH.ordinal() && op.ordinal() < AxisDirection.SOUTH.ordinal()) {
                AxisDirection opposite = op.opposite().orElseThrow();
                assertEquals(op.ordinal() + 8, opposite.ordinal());
            }
        }
    }
}
