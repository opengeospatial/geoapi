/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@link SimpleDirectPosition}.
 */
public class SimpleDirectPositionTest {
    /**
     * Creates a new test case.
     */
    public SimpleDirectPositionTest() {
    }

    /**
     * Tests the creation of a {@code SimpleDirectPosition} and verifies the
     * values returned by {@link SimpleDirectPosition#getCoordinate(int)}.
     */
    @Test
    public void testCreation() {
        final SimpleDirectPosition pos = new SimpleDirectPosition(null, 4, 8, -2);
        assertNull(pos.getCoordinateReferenceSystem());
        assertEquals( 3,   pos.getDimension());
        assertEquals( 4.0, pos.getCoordinate(0));
        assertEquals( 8.0, pos.getCoordinate(1));
        assertEquals(-2.0, pos.getCoordinate(2));
        assertArrayEquals(new double[] {4.0, 8.0, -2.0}, pos.getCoordinates());
        validate(pos);
    }

    /**
     * Tests the creation of an initially empty {@code SimpleDirectPosition} and tests the
     * modifications applied by {@link SimpleDirectPosition#setCoordinate(int, double)}.
     */
    @Test
    public void testSetCoordinate() {
        final SimpleDirectPosition pos = new SimpleDirectPosition(2);
        assertNull(pos.getCoordinateReferenceSystem());
        assertEquals(2,   pos.getDimension());
        assertEquals(0.0, pos.getCoordinate(0));
        assertEquals(0.0, pos.getCoordinate(1));
        pos.setCoordinate(0, 4.0);
        pos.setCoordinate(1, 3.0);
        assertEquals(4.0, pos.getCoordinate(0));
        assertEquals(3.0, pos.getCoordinate(1));
        assertArrayEquals(new double[] {4.0, 3.0}, pos.getCoordinates());
        validate(pos);
    }

    /**
     * Tests <i>Well-Known Text</i> (WKT) formatting.
     */
    @Test
    public void testWKT() {
        final SimpleDirectPosition pos = new SimpleDirectPosition(null, 4, 8, -2);
        assertEquals("POINT(4.0 8.0 -2.0)", pos.toString());
        validate(pos);
    }
}
