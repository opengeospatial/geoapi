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
package org.opengis.test.referencing;

import org.opengis.referencing.cs.*;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link CoordinateSystem} and related objects from the {@code org.opengis.referencing.cs}
 * package. This class should not be used directly; use the {@link org.opengis.test.Validators}
 * convenience static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class CSValidator extends ReferencingValidator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public CSValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.cs");
    }

    /**
     * Dispatches the given object to one of {@code validate} methods.
     *
     * @param object The object to dispatch.
     */
    public void dispatch(final CoordinateSystem object) {
        if (object instanceof CartesianCS) {
            validate((CartesianCS) object);
        } else if (object instanceof EllipsoidalCS) {
            validate((EllipsoidalCS) object);
        } else if (object instanceof SphericalCS) {
            validate((SphericalCS) object);
        } else if (object instanceof CylindricalCS) {
            validate((CylindricalCS) object);
        } else if (object instanceof PolarCS) {
            validate((PolarCS) object);
        } else if (object instanceof LinearCS) {
            validate((LinearCS) object);
        } else if (object instanceof VerticalCS) {
            validate((VerticalCS) object);
        } else if (object instanceof TimeCS) {
            validate((TimeCS) object);
        } else if (object instanceof UserDefinedCS) {
            validate((UserDefinedCS) object);
        } else if (object != null) {
            validateIdentifiedObject(object);
            validateAxes(object);
        }
    }

    /**
     * Validates the given axis.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @todo Add checks for abbreviations and names standardized by ISO 19111.
     */
    public void validate(final CoordinateSystemAxis object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        assertValidRange("CoordinateSystemAxis: expected maximum >= minimum.",
                object.getMinimumValue(), object.getMaximumValue());
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CartesianCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final EllipsoidalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertBetween("EllipsoidalCS: wrong number of dimensions.", 2, 3, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final SphericalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals("SphericalCS: wrong number of dimensions.", 3, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CylindricalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals("CylindricalCS: wrong number of dimensions.", 3, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final PolarCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals("PolarCS: wrong number of dimensions.", 2, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final LinearCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals("LinearCS: wrong number of dimensions.", 1, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final VerticalCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals("VerticalCS: wrong number of dimensions.", 1, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final TimeCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertEquals("TimeCS: wrong number of dimensions.", 1, dimension);
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final UserDefinedCS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertBetween("UserDefinedCS: wrong number of dimensions.", 2, 3, dimension);
    }

    /**
     * Performs the validation that are common to all coordinate systems. This method is
     * invoked by {@code validate} methods after they have determined the type of their
     * argument.
     *
     * @param object The object to validate (cannot be null).
     */
    private void validateAxes(final CoordinateSystem object) {
        final int dimension = object.getDimension();
        assertStrictlyPositive("CoordinateSystem: dimension must be greater than zero.", dimension);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = object.getAxis(i);
            mandatory("CoordinateSystem: axis cannot be null.", axis);
            validate(axis);
        }
    }
}
