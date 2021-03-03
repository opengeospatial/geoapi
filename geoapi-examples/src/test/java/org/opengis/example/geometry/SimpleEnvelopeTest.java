/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import org.opengis.geometry.MismatchedDimensionException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@link SimpleEnvelope}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleEnvelopeTest {
    /**
     * Tests the creation of a {@code SimpleEnvelope}, then test the property values.
     */
    @Test
    public void testOrdinateValues() {
        final SimpleEnvelope envelope = new SimpleEnvelope(
                new SimpleDirectPosition(null, 4, 8, -2),
                new SimpleDirectPosition(null, 8, 9,  3));
        assertNull(envelope.getCoordinateReferenceSystem());
        assertEquals( 3,   envelope.getDimension());
        assertEquals( 4.0, envelope.getMinimum(0), 0.0);
        assertEquals( 8.0, envelope.getMinimum(1), 0.0);
        assertEquals(-2.0, envelope.getMinimum(2), 0.0);
        assertEquals( 8.0, envelope.getMaximum(0), 0.0);
        assertEquals( 9.0, envelope.getMaximum(1), 0.0);
        assertEquals( 3.0, envelope.getMaximum(2), 0.0);
        assertEquals( 6.0, envelope.getMedian (0), 0.0);
        assertEquals( 8.5, envelope.getMedian (1), 0.0);
        assertEquals( 0.5, envelope.getMedian (2), 0.0);
        assertEquals( 4.0, envelope.getSpan   (0), 0.0);
        assertEquals( 1.0, envelope.getSpan   (1), 0.0);
        assertEquals( 5.0, envelope.getSpan   (2), 0.0);
        assertArrayEquals(new double[] {4.0, 8.0, -2.0}, envelope.getLowerCorner().getCoordinate(), 0.0);
        assertArrayEquals(new double[] {8.0, 9.0,  3.0}, envelope.getUpperCorner().getCoordinate(), 0.0);
        validate(envelope);
    }

    /**
     * Tests the creation of a {@code SimpleEnvelope} with invalid coordinate values.
     * In this test, the invalid coordinate values is in the last dimension.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOrdinateValues() {
        new SimpleEnvelope(new SimpleDirectPosition(null, 4, 8, -2),
                           new SimpleDirectPosition(null, 8, 9, -3));
    }

    /**
     * Tests the creation of a {@code SimpleEnvelope} with mismatched dimensions.
     */
    @Test(expected = MismatchedDimensionException.class)
    public void testMismatchedDimension() {
        new SimpleEnvelope(new SimpleDirectPosition(null, 4, 8, -2),
                           new SimpleDirectPosition(null, 8, 9));
    }

    /**
     * Tests <cite>Well-Known Text</cite> (WKT) formatting.
     */
    @Test
    public void testWKT() {
        final SimpleEnvelope envelope = new SimpleEnvelope(
                new SimpleDirectPosition(null, 4, 8, -2),
                new SimpleDirectPosition(null, 8, 9,  3));
        assertEquals("BOX3D(4.0 8.0 -2.0, 8.0 9.0 3.0)", envelope.toString());
        validate(envelope);
    }
}
