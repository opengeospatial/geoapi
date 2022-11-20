/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@link SimpleDirectPosition}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleDirectPositionTest {
    /**
     * Tests the creation of a {@code SimpleDirectPosition} and verifies the
     * values returned by {@link SimpleDirectPosition#getOrdinate(int)}.
     */
    @Test
    public void testCreation() {
        final SimpleDirectPosition pos = new SimpleDirectPosition(null, 4, 8, -2);
        assertNull(pos.getCoordinateReferenceSystem());
        assertEquals( 3,   pos.getDimension());
        assertEquals( 4.0, pos.getOrdinate(0), 0.0);
        assertEquals( 8.0, pos.getOrdinate(1), 0.0);
        assertEquals(-2.0, pos.getOrdinate(2), 0.0);
        assertArrayEquals(new double[] {4.0, 8.0, -2.0}, pos.getCoordinate(), 0.0);
        validate(pos);
    }

    /**
     * Tests the creation of an initially empty {@code SimpleDirectPosition} and tests the
     * modifications applied by {@link SimpleDirectPosition#setOrdinate(int, double)}.
     */
    @Test
    public void testSetOrdinate() {
        final SimpleDirectPosition pos = new SimpleDirectPosition(2);
        assertNull(pos.getCoordinateReferenceSystem());
        assertEquals(2,   pos.getDimension());
        assertEquals(0.0, pos.getOrdinate(0), 0.0);
        assertEquals(0.0, pos.getOrdinate(1), 0.0);
        pos.setOrdinate(0, 4.0);
        pos.setOrdinate(1, 3.0);
        assertEquals(4.0, pos.getOrdinate(0), 0.0);
        assertEquals(3.0, pos.getOrdinate(1), 0.0);
        assertArrayEquals(new double[] {4.0, 3.0}, pos.getCoordinate(), 0.0);
        validate(pos);
    }

    /**
     * Tests <cite>Well-Known Text</cite> (WKT) formatting.
     */
    @Test
    public void testWKT() {
        final SimpleDirectPosition pos = new SimpleDirectPosition(null, 4, 8, -2);
        assertEquals("POINT(4.0 8.0 -2.0)", pos.toString());
        validate(pos);
    }
}
