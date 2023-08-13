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

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests {@link Assert}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class AssertTest {
    /**
     * Creates a new test case.
     */
    public AssertTest() {
    }

    /**
     * Tests {@link Assert#assertUnicodeIdentifierEquals(String, CharSequence, CharSequence, boolean)}.
     */
    @Test
    public void testIdentifierEquals() {
        Assert.assertUnicodeIdentifierEquals(null, "WGS84" ,   "WGS84",  true);
        Assert.assertUnicodeIdentifierEquals(null, "WGS84" ,   "WGS 84", true);
        Assert.assertUnicodeIdentifierEquals(null, "WGS 84",   "WGS84",  true);
        Assert.assertUnicodeIdentifierEquals(null, "WGS 84",   "WGS 84", true);
        Assert.assertUnicodeIdentifierEquals(null, "_WgS 84!", "wgs84",  true);
        try {
            Assert.assertUnicodeIdentifierEquals(null, "WGS84 and more" , "WGS84", true);
        } catch (AssertionError e) {
            assertEquals("Expected \"WGS84 and more\" but got \"WGS84\". Missing part: \"and more\".", e.getMessage());
        }
        try {
            Assert.assertUnicodeIdentifierEquals(null, "WGS84" , "WGS84 and more", true);
        } catch (AssertionError e) {
            assertEquals("Expected \"WGS84\", but found it with a unexpected trailing string: \"and more\".", e.getMessage());
        }
        try {
            Assert.assertUnicodeIdentifierEquals(null, "WGS84" , "WBS84", true);
        } catch (AssertionError e) {
            assertEquals("Expected \"WGS84\" but got \"WBS84\".", e.getMessage());
        }
    }

    /**
     * Tests {@link Assert#assertShapeEquals(String, Shape, Shape, double, double)}.
     */
    @Test
    public void testShapeEquals() {
        final Shape shape = new RoundRectangle2D.Double(-20, -10, 100, 80, 4, 5);
        Assert.assertShapeEquals(null, shape, shape, 0, 0);
        final Shape mismatched = new Rectangle2D.Double(-20, -10, 100, 80);
        try {
            Assert.assertShapeEquals(null, shape, mismatched, 0, 0);
            fail("Expected an AssertionError.");
        } catch (AssertionError e) {
            // This is the expected exception.
            assertTrue(e.getMessage().contains("Mismatched"));
        }
    }
}
