/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.EnumSet;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.SingleCRS;

import org.junit.*;
import static org.junit.Assert.*;
import static org.opengis.test.ToleranceModifier.*;


/**
 * Tests {@link ToleranceModifiers}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ToleranceModifiersTest implements DirectPosition {
    /**
     * A sample tolerance array used by all tests.
     */
    private final double[] tolerance = new double[] {1, 3, 2};

    /**
     * A {@link DirectPosition} method returning the hard-coded
     * number of dimensions that we use for this test.
     */
    @Override
    public int getDimension() {
        return 3;
    }

    /**
     * A {@link DirectPosition} method returning hard-coded coordinate values,
     * to be tested with the above {@linkplain #tolerance} values.
     */
    @Override
    public double getOrdinate(final int dimension) {
        switch (dimension) {
            case 0:  return  10;  // λ
            case 1:  return  90;  // φ
            case 2:  return -10;  // z
            default: throw new AssertionError(dimension);
        }
    }

    /**
     * {@link ToleranceModifier} shall not attempt to modify the coordinate values.
     * Attempting to do so will cause a JUnit failure.
     */
    @Override
    public void setOrdinate(int dimension, double value) {
        fail("The coordinate should not be modified.");
    }

    /**
     * {@link DirectPosition} methods which are not of interest to this test case.
     */
    @Override public DirectPosition getDirectPosition()            {return this;}
    @Override public SingleCRS      getCoordinateReferenceSystem() {return null;}
    @Override public double[]       getCoordinate()                {return null;}

    /**
     * Tests the {@link ToleranceModifiers.Relative} implementation.
     */
    @Test
    public void testRelative() {
        final ToleranceModifier modifier = RELATIVE;
        assertEquals("Object shall be equal to itself.", modifier, modifier);
        assertEquals("toString()", "ToleranceModifier.Relative[…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals("Expected scaled values.", new double[] {10, 270, 20}, tolerance, 0);
    }

    /**
     * Tests the {@link ToleranceModifiers.Geographic} implementation.
     */
    @Test
    public void testGeographic() {
        assertSame(GEOGRAPHIC,    ToleranceModifiers.geographic(0, 1));
        assertSame(GEOGRAPHIC_φλ, ToleranceModifiers.geographic(1, 0));

        final ToleranceModifier modifier = GEOGRAPHIC;
        assertEquals("Object shall be equal to itself.", modifier, modifier);
        assertEquals("toString()", "ToleranceModifier.Geographic[λ,φ,…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals("Expected conversions from metres to decimal degrees.",
                new double[] {360, 2.699784E-5, 2}, tolerance, 1E-11);
    }

    /**
     * Tests the {@link ToleranceModifiers.Projection} implementation.
     */
    @Test
    public void testProjection() {
        assertSame(PROJECTION,         ToleranceModifiers.projection(0, 1));
        assertSame(PROJECTION_FROM_φλ, ToleranceModifiers.projection(1, 0));

        final ToleranceModifier modifier = PROJECTION;
        assertEquals("Object shall be equal to itself.", modifier, modifier);
        assertEquals("toString()", "ToleranceModifier.Projection[λ,φ,…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals("Expected unmodified values.", new double[] {1, 3, 2}, tolerance, 0);

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals("Expected conversions from metres to decimal degrees.",
                new double[] {360, 2.699784E-5, 2}, tolerance, 1E-11);
    }

    /**
     * Tests the {@link ToleranceModifiers.Scale} implementation.
     */
    @Test
    public void testScale() {
        final EnumSet<CalculationType> types = EnumSet.of(CalculationType.INVERSE_TRANSFORM);
        assertNull("Testing with identity conversion.", ToleranceModifiers.scale(types, 1, 1));

        final ToleranceModifier modifier = ToleranceModifiers.scale(types, 1, 2, 1);
        assertEquals("Object shall be equal to itself.", modifier, modifier);
        assertEquals("toString()", "ToleranceModifier.Scale[INVERSE_TRANSFORM:·,×2,…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals("Expected unmodified values.", new double[] {1, 3, 2}, tolerance, 0);

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals("Expected scaled values.", new double[] {1, 6, 2}, tolerance, 0);
    }

    /**
     * Tests the {@code ToleranceModifiers.Maximum} implementation.
     */
    @Test
    public void testMaximum() {
        assertNull("Testing with empty array.", ToleranceModifiers.maximum());
        assertSame("Testing with singleton.", PROJECTION, ToleranceModifiers.maximum(PROJECTION));

        final ToleranceModifier modifier = ToleranceModifiers.maximum(PROJECTION, RELATIVE);
        assertEquals("Object shall be equal to itself.", modifier, modifier);
        assertEquals("toString()", "ToleranceModifier.Maximum[Projection[λ,φ,…], Relative[…]]", modifier.toString());

        final double[] copy = tolerance.clone();
        modifier.adjust(copy, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals("Expected only scaled values.", new double[] {10, 270, 20}, copy, 0);

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals("Expected converted and scaled values.", new double[] {360, 270, 20}, tolerance, 0);
    }

    /**
     * Tests the {@code ToleranceModifiers.Concatenate} implementation.
     */
    @Test
    public void testConcatenate() {
        assertSame(GEOGRAPHIC, ToleranceModifiers.concatenate(GEOGRAPHIC, null));
        assertSame(RELATIVE,   ToleranceModifiers.concatenate(null, RELATIVE));

        final ToleranceModifier modifier = ToleranceModifiers.concatenate(GEOGRAPHIC, RELATIVE);
        assertEquals("Object shall be equal to itself.", modifier, modifier);
        assertEquals("toString()", "ToleranceModifier.Concatenate[Geographic[λ,φ,…] → Relative[…]]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals("Expected converted and scaled values.",
                new double[] {3600, 2.42981E-3, 20}, tolerance, 1E-8);
    }
}
