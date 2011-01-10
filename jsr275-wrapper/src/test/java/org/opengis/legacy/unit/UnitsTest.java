/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.legacy.unit;

import javax.measure.unit.SI;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests the {@link Units} static methods.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public final class UnitsTest {
    /**
     * Tests the {@link Units#toUOM} method.
     */
    @Test
    public void testToUOM() {
        assertNull(Units.toUOM(null));
        assertEquals(org.unitsofmeasurement.quantity.Length.class,
                Units.toUOM(javax.measure.quantity.Length.class));
        assertEquals(org.unitsofmeasurement.quantity.Angle.class,
                Units.toUOM(javax.measure.quantity.Angle.class));
        assertEquals(org.unitsofmeasurement.quantity.Time.class,
                Units.toUOM(javax.measure.quantity.Duration.class));
    }

    /**
     * Tests the {@link Units#toJSR} method.
     */
    @Test
    public void testToJSR() {
        assertNull(Units.toJSR(null));
        assertEquals(javax.measure.quantity.Length.class,
                Units.toJSR(org.unitsofmeasurement.quantity.Length.class));
        assertEquals(javax.measure.quantity.Angle.class,
                Units.toJSR(org.unitsofmeasurement.quantity.Angle.class));
        assertEquals(javax.measure.quantity.Duration.class,
                Units.toJSR(org.unitsofmeasurement.quantity.Time.class));
    }

    /**
     * Tests the {@link Units#wrap} method.
     */
    @Test
    public void testWrap() {
        assertNull(Units.wrap((javax.measure.unit.Unit<?>) null));

        final org.unitsofmeasurement.unit.Unit<?> length = Units.wrap(SI.METRE);
        assertSame(SI.METRE, Units.unwrap(length));
        assertSame("Wrapped units shall be cached.", length, Units.wrap(SI.METRE));

        final org.unitsofmeasurement.unit.Unit<?> time = Units.wrap(SI.SECOND);
        assertSame(SI.SECOND, Units.unwrap(time));
        assertSame("Wrapped units shall be cached.", time, Units.wrap(SI.SECOND));
        assertNotSame(length, time);

        final org.unitsofmeasurement.unit.Unit<?> speed = length.divide(time);
        assertSame(speed, Units.wrap(SI.METRE.divide(SI.SECOND)));
    }
}
