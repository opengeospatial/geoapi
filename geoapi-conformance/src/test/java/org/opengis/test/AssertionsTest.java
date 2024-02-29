/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2024 Open Geospatial Consortium, Inc.
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

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link Assertions}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class AssertionsTest {
    /**
     * Creates a new test case.
     */
    public AssertionsTest() {
    }

    /**
     * Tests {@link Assertions#assertUnicodeIdentifierEquals(CharSequence, CharSequence, boolean, String)}.
     */
    @Test
    public void testIdentifierEquals() {
        Assertions.assertUnicodeIdentifierEquals("WGS84" ,   "WGS84",  true, null);
        Assertions.assertUnicodeIdentifierEquals("WGS84" ,   "WGS 84", true, null);
        Assertions.assertUnicodeIdentifierEquals("WGS 84",   "WGS84",  true, null);
        Assertions.assertUnicodeIdentifierEquals("WGS 84",   "WGS 84", true, null);
        Assertions.assertUnicodeIdentifierEquals("_WgS 84!", "wgs84",  true, null);

        AssertionError e;
        e = assertThrows(AssertionError.class,
                () -> Assertions.assertUnicodeIdentifierEquals("WGS84 and more" , "WGS84", true, null));
        assertEquals("Expected \"WGS84 and more\" but got \"WGS84\". Missing part: \"and more\".", e.getMessage());

        e = assertThrows(AssertionError.class,
                () -> Assertions.assertUnicodeIdentifierEquals("WGS84" , "WGS84 and more", true, null));
        assertEquals("Expected \"WGS84\", but found it with a unexpected trailing string: \"and more\".", e.getMessage());

        e = assertThrows(AssertionError.class,
                () -> Assertions.assertUnicodeIdentifierEquals("WGS84" , "WBS84", true, null));
        assertEquals("Expected \"WGS84\" but got \"WBS84\".", e.getMessage());
    }

    /**
     * Tests {@link Assertions#assertShapeEquals(Shape, Shape, double, double, String)}.
     */
    @Test
    public void testShapeEquals() {
        final Shape shape = new RoundRectangle2D.Double(-20, -10, 100, 80, 4, 5);
        Assertions.assertShapeEquals(shape, shape, 0, 0, null);
        final Shape mismatched = new Rectangle2D.Double(-20, -10, 100, 80);

        AssertionError e = assertThrows(AssertionError.class,
                () -> Assertions.assertShapeEquals(shape, mismatched, 0, 0, null));
        assertTrue(e.getMessage().contains("Mismatched"));
    }
}
