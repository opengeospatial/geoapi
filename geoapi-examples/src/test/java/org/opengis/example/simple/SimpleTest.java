/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.simple;

import org.junit.Test;
import org.opengis.referencing.cs.CoordinateSystemAxis;

import static org.junit.Assert.*;


/**
 * Tests the creation of simple objects.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleTest {
    /**
     * Tests the creation of a simple WGS84 CRS.
     */
    @Test
    public void testCRS() {
        final SimpleCitation space = new SimpleCitation("MyAuthority");
        final SimpleDatum    datum = new SimpleDatum(space, "WGS84", 6378137.0, 298.257223563);
        final CoordinateSystemAxis latitude  = null; // TODO: not yet defined.
        final CoordinateSystemAxis longitude = null; // TODO: not yet defined.
        final SimpleCRS      crs   = new SimpleCRS(space, "WGS84", datum, latitude, longitude);

        assertEquals("Object shall be equals to itself.", space, space);
        assertEquals("Object shall be equals to itself.", datum, datum);
        assertEquals("Object shall be equals to itself.", crs,   crs  );
        assertEquals(0, datum.getPrimeMeridian().getGreenwichLongitude(), 0);
        assertSame(datum, crs.getDatum());
        assertEquals("MyAuthority:WGS84", crs.toString());
    }
}
