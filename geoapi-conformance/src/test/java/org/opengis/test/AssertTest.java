/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
