/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.geometry;

import java.util.Arrays;

import org.opengis.geometry.*;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.RangeMeaning;

import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Assertions.assertBetween;
import static org.opengis.test.Assertions.assertPositive;
import static org.opengis.test.Assertions.assertValidRange;


/**
 * Validates {@link Geometry} and related objects from the {@code org.opengis.geometry}
 * package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class GeometryValidator extends Validator {
    /**
     * Small relative tolerance values for comparisons of floating point numbers.
     * The default value is {@value org.opengis.test.Validator#DEFAULT_TOLERANCE}.
     * Implementers can change this value before to run the tests.
     */
    public double tolerance = DEFAULT_TOLERANCE;

    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public GeometryValidator(final ValidatorContainer container) {
        super(container, "org.opengis.geometry");
    }

    /**
     * Returns {@code true} if the given range is [+0 … -0]. Such range is used by some implementations
     * for representing a 360° turn around the Earth. Such convention is of course not mandatory, but
     * some tests in this class must be aware of it.
     *
     * @param  lower  the lower value of the range to test.
     * @param  upper  the upper value of the range to test.
     * @return whether the given range is [+0 … -0].
     */
    private static boolean isPositiveToNegativeZero(final double lower, final double upper) {
        return Double.doubleToRawLongBits(lower) == 0L &&                       // Positive zero
               Double.doubleToRawLongBits(upper) == Long.MIN_VALUE;             // Negative zero
    }

    /**
     * Validates the given envelope.
     * This method performs the following verifications:
     *
     * <ul>
     *   <li>Envelope and corners dimension shall be the same.</li>
     *   <li>Envelope and corners CRS shall be the same, ignoring {@code null} values.</li>
     *   <li>Lower, upper and median coordinate values shall be inside the [minimum … maximum] range.</li>
     *   <li>Lower &gt; upper coordinate values are allowed only on axis having wraparound range meaning.</li>
     *   <li>For the usual lower &lt; upper case, compares the minimum, maximum, median and span values
     *       against values computed from the lower and upper coordinates.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Envelope object) {
        if (object == null) {
            return;
        }
        final int dimension = object.getDimension();
        assertPositive(dimension, "Envelope: dimension cannot be negative.");
        final CoordinateReferenceSystem crs = object.getCoordinateReferenceSystem();
        container.validate(crs);                                                            // May be null.
        CoordinateSystem cs = null;
        if (crs != null) {
            cs = crs.getCoordinateSystem();
            if (cs != null) {
                assertEquals(dimension, cs.getDimension(),
                        "Envelope: CRS dimension shall be equal to the envelope dimension");
            }
        }
        /*
         * Validates the corners as DirectPosition objects,
         * then checks Coordinate Reference Systems and dimensions.
         */
        final DirectPosition lowerCorner = object.getLowerCorner();
        final DirectPosition upperCorner = object.getUpperCorner();
        mandatory("Envelope: shall have a lower corner.",  lowerCorner);
        mandatory("Envelope: shall have an upper corner.", upperCorner);
        validate(lowerCorner);
        validate(upperCorner);
        CoordinateReferenceSystem lowerCRS = null;
        CoordinateReferenceSystem upperCRS = null;
        if (lowerCorner != null) {
            lowerCRS = lowerCorner.getCoordinateReferenceSystem();
            assertEquals(dimension, lowerCorner.getDimension(),
                    "Envelope: lower corner dimension shall be equal to the envelope dimension.");
        }
        if (upperCorner != null) {
            upperCRS = upperCorner.getCoordinateReferenceSystem();
            assertEquals(dimension, upperCorner.getDimension(),
                    "Envelope: upper corner dimension shall be equal to the envelope dimension.");
        }
        if (crs != null) {
            if (lowerCRS != null) assertSame(crs, lowerCRS, "Envelope: lower CRS shall be the same as the envelope CRS.");
            if (upperCRS != null) assertSame(crs, upperCRS, "Envelope: upper CRS shall be the same as the envelope CRS.");
        } else if (lowerCRS != null && upperCRS != null) {
            assertSame(lowerCRS, upperCRS, "Envelope: the two corners shall have the same CRS.");
        }
        /*
         * Verifies the consistency of lower, upper, minimum, maximum, median and span values.
         * The tests are relaxed in the case of ranges spanning the wraparound limit (e.g. the
         * anti-meridian).
         */
        for (int i=0; i<dimension; i++) {
            RangeMeaning meaning = null;
            if (cs != null) {
                final CoordinateSystemAxis axis = cs.getAxis(i);
                if (axis != null) {         // Should never be null, but it is not this test's job to ensure that.
                    meaning = axis.getRangeMeaning();
                }
            }
            final double lower   = (lowerCorner != null) ? lowerCorner.getOrdinate(i) : NaN;
            final double upper   = (upperCorner != null) ? upperCorner.getOrdinate(i) : NaN;
            final double minimum = object.getMinimum(i);
            final double maximum = object.getMaximum(i);
            final double median  = object.getMedian (i);
            final double span    = object.getSpan   (i);
            if (!isNaN(minimum) && !isNaN(maximum)) {
                if (lower <= upper && !isPositiveToNegativeZero(lower, upper)) { // Do not accept NaN in this block.
                    final double eps = (upper - lower) * tolerance;
                    assertEquals(lower, minimum, eps, "Envelope: minimum value shall be equal to the lower corner coordinate.");
                    assertEquals(upper, maximum, eps, "Envelope: maximum value shall be equal to the upper corner coordinate.");
                    assertEquals((maximum - minimum),   span,   eps, "Envelope: unexpected span value.");
                    assertEquals((maximum + minimum)/2, median, eps, "Envelope: unexpected median value.");
                } else if (RangeMeaning.EXACT.equals(meaning)) {
                    // assertBetween(…) tolerates NaN values, which is what we want.
                    assertValidRange(minimum, maximum,         "Envelope: invalid minimum or maximum.");
                    assertBetween   (minimum, maximum, lower,  "Envelope: invalid lower coordinate.");
                    assertBetween   (minimum, maximum, upper,  "Envelope: invalid upper coordinate.");
                    assertBetween   (minimum, maximum, median, "Envelope: invalid median coordinate.");
                }
            }
            if (meaning != null && (lower > upper || isPositiveToNegativeZero(lower, upper))) {
                assertEquals(RangeMeaning.WRAPAROUND, meaning, "Envelope: lower coordinate value may be "
                        + "greater than upper coordinate value only on axis having wrappround range.");
            }
        }
    }

    /**
     * Validates the given position.
     * This method ensures that the following hold:
     *
     * <ul>
     *   <li>The number of dimension cannot be negative.</li>
     *   <li>If the position is associated to a CRS, then their number of dimensions must be equal.</li>
     *   <li>Length of {@link DirectPosition#getCoordinate()} must be equals to the number of dimensions.</li>
     *   <li>Values of above array must be equals to values returned by {@link DirectPosition#getOrdinate(int)}.</li>
     *   <li>If the position is associated to a CRS and the axis range meaning is {@link RangeMeaning#EXACT},
     *       then the coordinate values must be between the minimum and maximum axis value.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final DirectPosition object) {
        if (object == null) {
            return;
        }
        /*
         * Checks coordinate consistency.
         */
        final int dimension = object.getDimension();
        assertPositive(dimension, "DirectPosition: dimension cannot be negative.");
        final double[] coordinates = object.getCoordinate();
        mandatory("DirectPosition: coordinate array cannot be null.", coordinates);
        if (coordinates != null) {
            assertEquals(dimension, coordinates.length,
                    "DirectPosition: coordinate array length shall be equal to the dimension.");
            for (int i=0; i<dimension; i++) {
                assertEquals(coordinates[i], object.getOrdinate(i),     // No tolerance - we want exact match.
                        "DirectPosition: getOrdinate(i) shall be the same as coordinate[i].");
            }
        }
        /*
         * Checks coordinate validity in the CRS.
         */
        final CoordinateReferenceSystem crs = object.getCoordinateReferenceSystem();
        container.validate(crs);                                                        // May be null.
        int hashCode = 0;
        if (crs != null) {
            final CoordinateSystem cs = crs.getCoordinateSystem();                      // Assume already validated.
            if (cs != null) {
                assertEquals(dimension, cs.getDimension(),
                        "DirectPosition: CRS dimension must matches the position dimension.");
                for (int i=0; i<dimension; i++) {
                    final CoordinateSystemAxis axis = cs.getAxis(i);                    // Assume already validated.
                    if (axis != null && RangeMeaning.EXACT.equals(axis.getRangeMeaning())) {
                        final double coordinate = coordinates[i];
                        final double minimum  = axis.getMinimumValue();
                        final double maximum  = axis.getMaximumValue();
                        assertBetween(minimum, maximum, coordinate, "DirectPosition: coordinate out of axis bounds.");
                    }
                }
            }
            hashCode = crs.hashCode();
        }
        /*
         * Tests hash code values. It must be compliant to DirectPosition.hashCode()
         * contract stated in the javadoc.
         */
        hashCode += Arrays.hashCode(coordinates);
        assertEquals(hashCode, object.hashCode(),
                "DirectPosition: hashCode shall be compliant to the contract given in javadoc.");
        assertTrue(object.equals(object), "DirectPosition: shall be equal to itself.");
        /*
         * Ensures that the array returned by DirectPosition.getCoordinate() is a clone.
         */
        for (int i=0; i<dimension; i++) {
            final double oldValue = coordinates[i];
            coordinates[i] *= 2;
            assertEquals(oldValue, object.getOrdinate(i),                      // No tolerance - we want exact match.
                    "DirectPosition: coordinate array shall be cloned.");
        }
    }
}
