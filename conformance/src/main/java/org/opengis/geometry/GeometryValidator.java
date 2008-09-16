/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry;

import java.util.Arrays;
import org.opengis.Validator;
import org.opengis.Validators;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Validates {@linkplain Geometry geometries} and related objects from the
 * {@code org.opengis.geometry} package. This class should not be used directly;
 * use the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class GeometryValidator extends Validator {
    /**
     * The system wide instance used by {@link org.opengis.Validators}. Vendor can replace
     * this instance by some {@code Validator} subclass if some tests need to be overrided.
     */
    public static GeometryValidator instance = new GeometryValidator();

    /**
     * Small tolerance values for comparaisons of floating point numbers.
     * The default value is {@value}. Implementors can change this value
     * before to run the tests.
     */
    public double tolerance = DEFAULT_TOLERANCE;

    /**
     * Creates a new validator.
     */
    protected GeometryValidator() {
        super("org.opengis.geometry");
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
        assertTrue("Envelope: dimension can't be negative.", dimension >= 0);
        final CoordinateReferenceSystem crs = object.getCoordinateReferenceSystem();
        Validators.validate(crs); // May be null.
        /*
         * Validates corners.
         */
        final DirectPosition lower = object.getLowerCorner();
        validate(lower);
        assertNotNull("Envelope: must have a lower corner.", lower);
        assertEquals("Envelope: lower corner dimension must be equals to the envelope dimension.",
                dimension, lower.getDimension());
        if (crs != null) {
            CoordinateReferenceSystem check = lower.getCoordinateReferenceSystem();
            if (check != null) {
                assertSame("Envelope: lower CRS must be the same than the envelope CRS.", crs, check);
            }
        }
        final DirectPosition upper = object.getUpperCorner();
        validate(upper);
        assertNotNull("Envelope: must have a upper corner.", upper);
        assertEquals("Envelope: upper corner dimension must be equals to the envelope dimension.",
                dimension, upper.getDimension());
        if (crs != null) {
            CoordinateReferenceSystem check = upper.getCoordinateReferenceSystem();
            if (check != null) {
                assertSame("Envelope: upper CRS must be the same than the envelope CRS.", crs, check);
            }
        }
        /*
         * Validates minimal and maximal values against the corners.
         */
        for (int i=0; i<dimension; i++) {
            final double minimum = object.getMinimum(i);
            final double maximum = object.getMaximum(i);
            assertEquals("Envelope: minimum value must be equals to the lower corner ordinate.",
                    lower.getOrdinate(i), minimum, 0.0); // No tolerance - we want exact match.
            assertEquals("Envelope: maximum value must be equals to the upper corner ordinate.",
                    upper.getOrdinate(i), maximum, 0.0); // No tolerance - we want exact match.
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
        assertTrue("DirectPosition: dimension can't be negative.", dimension >= 0);
        final double[] coordinate = object.getCoordinate();
        assertNotNull("DirectPosition: coordinate array can't be null.", coordinate);
        assertEquals("DirectPosition: coordinate array length must be equals to the dimension.",
                dimension, coordinate.length);
        for (int i=0; i<dimension; i++) {
            assertEquals("DirectPosition: getOrdinate(i) must be the same than coordinate[i].",
                    coordinate[i], object.getOrdinate(i), 0.0); // No tolerance - we want exact match.
        }
        /*
         * Checks coordinate validity in the CRS.
         */
        final CoordinateReferenceSystem crs = object.getCoordinateReferenceSystem();
        Validators.validate(crs); // May be null.
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
                        // Following test use !(o < min) instead than (o >= min) - and same
                        // for testing against the upper bound - in order to accept NaN values.
                        assertFalse("DirectPosition: ordinate is less than minimal allowed value.",
                                ordinate < minimum - eps);
                        assertFalse("DirectPosition: ordinate is greater than maximal allowed value.",
                                ordinate > maximum + eps);
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
