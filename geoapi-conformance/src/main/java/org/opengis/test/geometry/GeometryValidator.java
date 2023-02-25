/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link Geometry} and related objects from the {@code org.opengis.geometry}
 * package. This class should not be used directly; use the {@link org.opengis.test.Validators}
 * convenience static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class GeometryValidator extends Validator {
    /**
     * Small tolerance values for comparisons of floating point numbers.
     * The default value is {@value org.opengis.test.Validator#DEFAULT_TOLERANCE}.
     * Implementors can change this value before to run the tests.
     */
    public double tolerance = DEFAULT_TOLERANCE;

    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public GeometryValidator(final ValidatorContainer container) {
        super(container, "org.opengis.geometry");
    }

    /**
     * Validates the given envelope.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Envelope object) {
        if (object == null) {
            return;
        }
        final int dimension = object.getDimension();
        assertPositive("Envelope: dimension can't be negative.", dimension);
        final CoordinateReferenceSystem crs = object.getCoordinateReferenceSystem();
        container.crs.dispatch(crs); // May be null.
        /*
         * Validates corners.
         */
        final DirectPosition lower = object.getLowerCorner();
        validate(lower);
        mandatory("Envelope: must have a lower corner.", lower);
        if (lower != null) {
            assertEquals("Envelope: lower corner dimension must be equals to the envelope dimension.",
                    dimension, lower.getDimension());
            if (crs != null) {
                CoordinateReferenceSystem check = lower.getCoordinateReferenceSystem();
                if (check != null) {
                    assertSame("Envelope: lower CRS must be the same than the envelope CRS.", crs, check);
                }
            }
        }
        final DirectPosition upper = object.getUpperCorner();
        validate(upper);
        mandatory("Envelope: must have a upper corner.", upper);
        if (upper != null) {
            assertEquals("Envelope: upper corner dimension must be equals to the envelope dimension.",
                    dimension, upper.getDimension());
            if (crs != null) {
                CoordinateReferenceSystem check = upper.getCoordinateReferenceSystem();
                if (check != null) {
                    assertSame("Envelope: upper CRS must be the same than the envelope CRS.", crs, check);
                }
            }
        }
        /*
         * Validates minimal and maximal values against the corners.
         */
        for (int i=0; i<dimension; i++) {
            final double minimum = object.getMinimum(i);
            final double maximum = object.getMaximum(i);
            if (lower != null) {
                assertEquals("Envelope: minimum value must be equals to the lower corner ordinate.",
                        lower.getOrdinate(i), minimum, 0.0); // No tolerance - we want exact match.
            }
            if (upper != null) {
                assertEquals("Envelope: maximum value must be equals to the upper corner ordinate.",
                        upper.getOrdinate(i), maximum, 0.0); // No tolerance - we want exact match.
            }
            if (!Double.isNaN(minimum) && !Double.isNaN(maximum)) {
                assertValidRange("Envelope: invalid minimum and maximum ordinate values.", minimum, maximum);
            }
            final double span = maximum - minimum;
            final double eps = span * tolerance;
            assertEquals("Envelope: unexpected span value.", span, object.getSpan(i), eps);
            assertEquals("Envelope: unexpected median value.",
                    (maximum + minimum) / 2, object.getMedian(i), eps);
        }
    }

    /**
     * Validates the given position.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final DirectPosition object) {
        if (object == null) {
            return;
        }
        /*
         * Checks coordinate consistency.
         */
        final int dimension = object.getDimension();
        assertPositive("DirectPosition: dimension can't be negative.", dimension);
        final double[] coordinate = object.getCoordinate();
        mandatory("DirectPosition: coordinate array can't be null.", coordinate);
        if (coordinate != null) {
            assertEquals("DirectPosition: coordinate array length must be equals to the dimension.",
                    dimension, coordinate.length);
            for (int i=0; i<dimension; i++) {
                assertEquals("DirectPosition: getOrdinate(i) must be the same than coordinate[i].",
                        coordinate[i], object.getOrdinate(i), 0.0); // No tolerance - we want exact match.
            }
        }
        /*
         * Checks coordinate validity in the CRS.
         */
        final CoordinateReferenceSystem crs = object.getCoordinateReferenceSystem();
        container.crs.dispatch(crs); // May be null.
        int hashCode = 0;
        if (crs != null) {
            final CoordinateSystem cs = crs.getCoordinateSystem(); // Assume already validated.
            if (cs != null) {
                assertEquals("DirectPosition: CRS dimension must matches the position dimension.",
                        dimension, cs.getDimension());
                for (int i=0; i<dimension; i++) {
                    final CoordinateSystemAxis axis = cs.getAxis(i); // Assume already validated.
                    if (axis != null) {
                        final double ordinate = coordinate[i];
                        final double minimum  = axis.getMinimumValue();
                        final double maximum  = axis.getMaximumValue();
                        final double eps = (maximum - minimum) * tolerance;
                        assertBetween("DirectPosition: ordinate out of axis bounds.",
                                minimum - eps, maximum + eps, ordinate);
                    }
                }
            }
            hashCode = crs.hashCode();
        }
        /*
         * Tests hash code values. It must be compliant to DirectPosition.hashCode()
         * contract stated in the javadoc.
         */
        hashCode += Arrays.hashCode(coordinate);
        assertEquals("DirectPosition: hashCode must be compliant to the contract given in javadoc.",
                hashCode, object.hashCode());
        assertTrue("DirectPosition: must be equals to itself.", object.equals(object));
        /*
         * Ensures that the array returned by DirectPosition.getCoordinate() is a clone.
         */
        for (int i=0; i<dimension; i++) {
            final double oldValue = coordinate[i];
            coordinate[i] *= 2;
            assertEquals("DirectPosition: coordinate array must be cloned.",
                    oldValue, object.getOrdinate(i), 0.0); // No tolerance - we want exact match.
        }
    }
}
