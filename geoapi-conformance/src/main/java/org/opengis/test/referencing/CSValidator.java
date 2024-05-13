/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.Set;
import java.util.Iterator;
import org.opengis.referencing.cs.*;
import org.opengis.test.ValidatorContainer;

import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Assertions.assertBetween;
import static org.opengis.test.Assertions.assertValidRange;
import static org.opengis.test.Assertions.assertStrictlyPositive;
import static org.opengis.test.referencing.Utilities.getAxisDirections;


/**
 * Validates {@link CoordinateSystem} and related objects from the {@code org.opengis.referencing.cs}
 * package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   2.2
 *
 * @todo Add checks for Unit of Measurement depending on the coordinate system type.
 *       For example, {@link EllipsoidalCS} expects two angular values and one linear value (if 3D).
 */
public class CSValidator extends ReferencingValidator {
    /**
     * The space in which an axis orientation is defined.
     * Orientations in the same space can be compared.
     */
    static enum Space {
        GEOGRAPHIC, TEMPORAL, ENGINEERING, MATRIX, DISPLAY;
    }

    /**
     * Orientation of an {@link AxisDirection}, used to detect if axes are perpendicular.
     */
    static final class Orientation {
        /** Amount of degrees in one angle unit. */ static final double ANGLE_UNIT = 22.5;
        /** Geographic, matrix or display.       */ final Space category;
        /** Orientation as a multiple of 22.5°.  */ final int orientation;

        /**
         * Creates a new {@code Orientation}.
         *
         * @param  category     geographic, matrix or display.
         * @param  orientation  orientation as a multiple of 22.5°.
         */
        Orientation(final Space category, final int orientation) {
            this.category    = category;
            this.orientation = orientation;
        }

        /** {@return a string representation for debugging purpose only}. */
        @Override public String toString() {
            return category.name() + ':' + (orientation * ANGLE_UNIT) + '°';
        }
    }

    /**
     * The orientation of {@link AxisDirection} enumeration elements for which the direction will be verified.
     * This array does not need to contain all axis directions, because it is used only by
     * {@link #validate(CartesianCS)} for asserting that axes are perpendicular.
     */
    static final Orientation[] ORIENTATIONS = new Orientation[34];
    static {
        ORIENTATIONS[AxisDirection.NORTH            .ordinal()] = new Orientation(Space.GEOGRAPHIC,   0);
        ORIENTATIONS[AxisDirection.NORTH_NORTH_EAST .ordinal()] = new Orientation(Space.GEOGRAPHIC,   1);
        ORIENTATIONS[AxisDirection.NORTH_EAST       .ordinal()] = new Orientation(Space.GEOGRAPHIC,   2);
        ORIENTATIONS[AxisDirection.EAST_NORTH_EAST  .ordinal()] = new Orientation(Space.GEOGRAPHIC,   3);
        ORIENTATIONS[AxisDirection.EAST             .ordinal()] = new Orientation(Space.GEOGRAPHIC,   4);
        ORIENTATIONS[AxisDirection.EAST_SOUTH_EAST  .ordinal()] = new Orientation(Space.GEOGRAPHIC,   5);
        ORIENTATIONS[AxisDirection.SOUTH_EAST       .ordinal()] = new Orientation(Space.GEOGRAPHIC,   6);
        ORIENTATIONS[AxisDirection.SOUTH_SOUTH_EAST .ordinal()] = new Orientation(Space.GEOGRAPHIC,   7);
        ORIENTATIONS[AxisDirection.SOUTH            .ordinal()] = new Orientation(Space.GEOGRAPHIC,   8);
        ORIENTATIONS[AxisDirection.SOUTH_SOUTH_WEST .ordinal()] = new Orientation(Space.GEOGRAPHIC,   9);
        ORIENTATIONS[AxisDirection.SOUTH_WEST       .ordinal()] = new Orientation(Space.GEOGRAPHIC,  10);
        ORIENTATIONS[AxisDirection.WEST_SOUTH_WEST  .ordinal()] = new Orientation(Space.GEOGRAPHIC,  11);
        ORIENTATIONS[AxisDirection.WEST             .ordinal()] = new Orientation(Space.GEOGRAPHIC,  12);
        ORIENTATIONS[AxisDirection.WEST_NORTH_WEST  .ordinal()] = new Orientation(Space.GEOGRAPHIC,  13);
        ORIENTATIONS[AxisDirection.NORTH_WEST       .ordinal()] = new Orientation(Space.GEOGRAPHIC,  14);
        ORIENTATIONS[AxisDirection.NORTH_NORTH_WEST .ordinal()] = new Orientation(Space.GEOGRAPHIC,  15);
        ORIENTATIONS[AxisDirection.ROW_NEGATIVE     .ordinal()] = new Orientation(Space.MATRIX,       0);
        ORIENTATIONS[AxisDirection.COLUMN_POSITIVE  .ordinal()] = new Orientation(Space.MATRIX,       4);
        ORIENTATIONS[AxisDirection.ROW_POSITIVE     .ordinal()] = new Orientation(Space.MATRIX,       8);
        ORIENTATIONS[AxisDirection.COLUMN_NEGATIVE  .ordinal()] = new Orientation(Space.MATRIX,      12);
        ORIENTATIONS[AxisDirection.DISPLAY_UP       .ordinal()] = new Orientation(Space.DISPLAY,      0);
        ORIENTATIONS[AxisDirection.DISPLAY_RIGHT    .ordinal()] = new Orientation(Space.DISPLAY,      4);
        ORIENTATIONS[AxisDirection.DISPLAY_DOWN     .ordinal()] = new Orientation(Space.DISPLAY,      8);
        ORIENTATIONS[AxisDirection.DISPLAY_LEFT     .ordinal()] = new Orientation(Space.DISPLAY,     12);
        ORIENTATIONS[AxisDirection.FORWARD          .ordinal()] = new Orientation(Space.ENGINEERING,  0);
        ORIENTATIONS[AxisDirection.STARBOARD        .ordinal()] = new Orientation(Space.ENGINEERING,  4);
        ORIENTATIONS[AxisDirection.AFT              .ordinal()] = new Orientation(Space.ENGINEERING,  8);
        ORIENTATIONS[AxisDirection.PORT             .ordinal()] = new Orientation(Space.ENGINEERING, 12);
    }

    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public CSValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.cs");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final CoordinateSystem object) {
        int n = 0;
        if (object != null) {
            if (object instanceof CartesianCS)   {validate((CartesianCS)   object); n++;}
            if (object instanceof EllipsoidalCS) {validate((EllipsoidalCS) object); n++;}
            if (object instanceof SphericalCS)   {validate((SphericalCS)   object); n++;}
            if (object instanceof CylindricalCS) {validate((CylindricalCS) object); n++;}
            if (object instanceof PolarCS)       {validate((PolarCS)       object); n++;}
            if (object instanceof LinearCS)      {validate((LinearCS)      object); n++;}
            if (object instanceof VerticalCS)    {validate((VerticalCS)    object); n++;}
            if (object instanceof TimeCS)        {validate((TimeCS)        object); n++;}
            if (n == 0) {
                validateIdentifiedObject(object);
                validateAxes(object);
            }
        }
        return n;
    }

    /**
     * Validates the given axis.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final CoordinateSystemAxis object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        mandatory(object.getAbbreviation(), "CoordinateSystemAxis: abbreviation is mandatory.");
        mandatory(object.getUnit(),         "CoordinateSystemAxis: unit is mandatory.");
        assertValidRange(object.getMinimumValue(), object.getMaximumValue(),
                "CoordinateSystemAxis: expected maximum >= minimum.");
    }

    /**
     * Validates the given coordinate system. This method ensures that
     * {@linkplain CoordinateSystemAxis#getDirection() axis directions}
     * are perpendicular to each other. Only known or compatibles directions are compared
     * (e.g. {@code NORTH} with {@code EAST}). Unknown or incompatible directions
     * (e.g. {@code NORTH} with {@code FUTURE}) are ignored.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final CartesianCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final Set<AxisDirection> axes = getAxisDirections(object);
        validate(axes);
        assertPerpendicularAxes(axes);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final EllipsoidalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertBetween(2, 3, dimension, "EllipsoidalCS: wrong number of dimensions.");
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final SphericalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals(3, dimension, "SphericalCS: wrong number of dimensions.");
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final CylindricalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals(3, dimension, "CylindricalCS: wrong number of dimensions.");
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final PolarCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals(2, dimension, "PolarCS: wrong number of dimensions.");
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final LinearCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals(1, dimension, "LinearCS: wrong number of dimensions.");
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final VerticalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals(1, dimension, "VerticalCS: wrong number of dimensions.");
    }

    /**
     * Validates the given coordinate system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final TimeCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals(1, dimension, "TimeCS: wrong number of dimensions.");
    }

    /**
     * Performs the validation that are common to all coordinate systems. This method is
     * invoked by {@code validate} methods after they have determined the type of their
     * argument.
     *
     * @param  object  the object to validate (cannot be null).
     */
    private void validateAxes(final CoordinateSystem object) {
        final int dimension = object.getDimension();
        assertStrictlyPositive(dimension, "CoordinateSystem: dimension must be greater than zero.");
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = object.getAxis(i);
            mandatory(axis, "CoordinateSystem: axis cannot be null.");
            validate(axis);
        }
    }

    /**
     * Asserts that the given set of axis directions are perpendicular.
     * Only known or compatibles directions are compared (e.g. {@code NORTH} with {@code EAST}).
     * Unknown or incompatible directions (e.g. {@code NORTH} with {@code FUTURE}) are ignored.
     *
     * <p>The given collection will be modified; do not pass a valuable collection!</p>
     *
     * @param  directions  the axis directions to verify.
     */
    static void assertPerpendicularAxes(final Iterable<AxisDirection> directions) {
        Iterator<AxisDirection> it;
        while ((it = directions.iterator()).hasNext()) {
            AxisDirection refDirection = null;
            Orientation ref = null;
            do {
                final AxisDirection direction = it.next();
                if (direction.ordinal() < ORIENTATIONS.length) {
                    final Orientation other = ORIENTATIONS[direction.ordinal()];
                    if (other != null) {
                        if (ref == null) {
                            ref = other;
                            refDirection = direction;
                        } else {
                            /*
                             * At this point, we got a pair of orientations to compare.
                             * We will perform the comparison only if they are compatible.
                             */
                            if (ref.category.equals(other.category)) {
                                // Get the angle as a multiple of 22.5°.
                                // An angle of 4 units is 90°.
                                final int angle = other.orientation - ref.orientation;
                                if ((angle % 4) != 0) {
                                    fail("Found an angle of " + (angle * Orientation.ANGLE_UNIT) +
                                            "° between axis directions " + refDirection.name() +
                                            " and " + direction.name() + '.');
                                }
                            }
                            /*
                             * Do not remove the `other` axis direction, because
                             * we want to compare it again in other pairs of axes.
                             */
                            continue;
                        }
                    }
                }
                it.remove();
            } while (it.hasNext());
        }
    }
}
