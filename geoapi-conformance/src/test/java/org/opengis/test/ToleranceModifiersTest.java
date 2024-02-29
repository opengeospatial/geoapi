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

import java.util.EnumSet;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.SingleCRS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.ToleranceModifier.*;


/**
 * Tests {@link ToleranceModifiers}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ToleranceModifiersTest implements DirectPosition {
    /**
     * A sample tolerance array used by all tests.
     */
    private final double[] tolerance = new double[] {1, 3, 2};

    /**
     * Creates a new test case.
     */
    public ToleranceModifiersTest() {
    }

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
        assertEquals(modifier, modifier, "Object shall be equal to itself.");
        assertEquals("ToleranceModifier.Relative[…]", modifier.toString(), "toString()");

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals(new double[] {10, 270, 20}, tolerance, "Expected scaled values.");
    }

    /**
     * Tests the {@link ToleranceModifiers.Geographic} implementation.
     */
    @Test
    public void testGeographic() {
        assertSame(GEOGRAPHIC,    ToleranceModifiers.geographic(0, 1));
        assertSame(GEOGRAPHIC_φλ, ToleranceModifiers.geographic(1, 0));

        final ToleranceModifier modifier = GEOGRAPHIC;
        assertEquals(modifier, modifier, "Object shall be equal to itself.");
        assertEquals("ToleranceModifier.Geographic[λ,φ,…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals(new double[] {360, 2.699784E-5, 2}, tolerance, 1E-11,
                "Expected conversions from metres to decimal degrees.");
    }

    /**
     * Tests the {@link ToleranceModifiers.Projection} implementation.
     */
    @Test
    public void testProjection() {
        assertSame(PROJECTION,         ToleranceModifiers.projection(0, 1));
        assertSame(PROJECTION_FROM_φλ, ToleranceModifiers.projection(1, 0));

        final ToleranceModifier modifier = PROJECTION;
        assertEquals(modifier, modifier, "Object shall be equal to itself.");
        assertEquals("ToleranceModifier.Projection[λ,φ,…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals(new double[] {1, 3, 2}, tolerance, "Expected unmodified values.");

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals(new double[] {360, 2.699784E-5, 2}, tolerance, 1E-11,
                "Expected conversions from metres to decimal degrees.");
    }

    /**
     * Tests the {@link ToleranceModifiers.Scale} implementation.
     */
    @Test
    public void testScale() {
        final EnumSet<CalculationType> types = EnumSet.of(CalculationType.INVERSE_TRANSFORM);
        assertNull(ToleranceModifiers.scale(types, 1, 1), "Testing with identity conversion.");

        final ToleranceModifier modifier = ToleranceModifiers.scale(types, 1, 2, 1);
        assertEquals(modifier, modifier, "Object shall be equal to itself.");
        assertEquals("ToleranceModifier.Scale[INVERSE_TRANSFORM:·,×2,…]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals(new double[] {1, 3, 2}, tolerance, "Expected unmodified values.");

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals(new double[] {1, 6, 2}, tolerance, "Expected scaled values.");
    }

    /**
     * Tests the {@code ToleranceModifiers.Maximum} implementation.
     */
    @Test
    public void testMaximum() {
        assertNull(ToleranceModifiers.maximum(), "Testing with empty array.");
        assertSame(PROJECTION, ToleranceModifiers.maximum(PROJECTION), "Testing with singleton.");

        final ToleranceModifier modifier = ToleranceModifiers.maximum(PROJECTION, RELATIVE);
        assertEquals(modifier, modifier, "Object shall be equal to itself.");
        assertEquals("ToleranceModifier.Maximum[Projection[λ,φ,…], Relative[…]]", modifier.toString());

        final double[] copy = tolerance.clone();
        modifier.adjust(copy, this, CalculationType.DIRECT_TRANSFORM);
        assertArrayEquals(new double[] {10, 270, 20}, copy, "Expected only scaled values.");

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals(new double[] {360, 270, 20}, tolerance, "Expected converted and scaled values.");
    }

    /**
     * Tests the {@code ToleranceModifiers.Concatenate} implementation.
     */
    @Test
    public void testConcatenate() {
        assertSame(GEOGRAPHIC, ToleranceModifiers.concatenate(GEOGRAPHIC, null));
        assertSame(RELATIVE,   ToleranceModifiers.concatenate(null, RELATIVE));

        final ToleranceModifier modifier = ToleranceModifiers.concatenate(GEOGRAPHIC, RELATIVE);
        assertEquals(modifier, modifier, "Object shall be equal to itself.");
        assertEquals("ToleranceModifier.Concatenate[Geographic[λ,φ,…] → Relative[…]]", modifier.toString());

        modifier.adjust(tolerance, this, CalculationType.INVERSE_TRANSFORM);
        assertArrayEquals(new double[] {3600, 2.42981E-3, 20}, tolerance, 1E-8,
                "Expected converted and scaled values.");
    }
}
