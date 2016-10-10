/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.parameter;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests {@link SimpleParameter}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class SimpleParameterTest {
    /**
     * Tolerance factor for comparison of floating point numbers
     * that are expected to be strictly equal.
     */
    private static final double STRICT = 0.0;

    /**
     * Small tolerance factor for comparison of floating point numbers.
     */
    private static final double EPS = 1E-12;

    /**
     * Tests a latitude parameter.
     */
    @Test
    public void testLatitude() {
        final SimpleParameter param = new SimpleParameter(null, "latitude", SimpleParameter.Type.LATITUDE);
        assertEquals("Minimum",               Double.valueOf(-90),  param.getMinimumValue());
        assertEquals("Maximum",               Double.valueOf(+90),  param.getMaximumValue());
        assertEquals("Initial value",         0.0,                  param.doubleValue(), 0.0);
        assertEquals("Lenient cast",          0,                    param.intValue());
        assertEquals("Lenient behavior",      false,                param.booleanValue());
        assertArrayEquals("Lenient behavior", new double[] {0.0},   param.doubleValueList(), 0.0);
        assertArrayEquals("Lenient behavior", new int[]    {0},     param.intValueList());
        assertEquals     ("Unit conversion",  0.0,                  param.doubleValue(SimpleParameter.GRAD), STRICT);
        assertArrayEquals("Unit conversion",  new double[] {0.0},   param.doubleValueList(SimpleParameter.GRAD), STRICT);

        param.setValue(18);
        assertEquals("value",                 18.0,                 param.doubleValue(), 0.0);
        assertEquals("Lenient cast",          18,                   param.intValue());
        assertArrayEquals("Lenient behavior", new double[] {18.0},  param.doubleValueList(), 0.0);
        assertArrayEquals("Lenient behavior", new int[]    {18},    param.intValueList());
        assertEquals     ("Unit conversion",  20.0,                 param.doubleValue(SimpleParameter.GRAD), EPS);
        assertArrayEquals("Unit conversion",  new double[] {20.0},  param.doubleValueList(SimpleParameter.GRAD), EPS);
    }
}
