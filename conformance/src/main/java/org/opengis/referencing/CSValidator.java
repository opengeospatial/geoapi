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
package org.opengis.referencing;

import org.opengis.Validator;
import org.opengis.referencing.cs.*;


/**
 * Validates {@linkplain CoordinateSystem}s. This class should not be used directly;
 * use the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class CSValidator extends Validator {
    /**
     * The system wide instance used by {@link org.opengis.Validators}. Vendor can replace
     * this instance by some {@code Validator} subclass if some tests need to be overrided.
     */
    public static CSValidator instance = new CSValidator();

    /**
     * Creates a new validator.
     */
    protected CSValidator() {
        super("org.opengis.referencing.cs");
    }

    /**
     * Dispatchs the given object to one of {@code validate} methods.
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
        } else {
            ReferencingValidator.instance.validate(object);
        }
    }

    /**
     * Validates the given axis.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CoordinateSystemAxis object) {
        ReferencingValidator.instance.validate(object);
        if (object == null) {
            return;
        }
        // Following tests !(max < min) instead than (max >= min) in order to accept NaN values.
        assertFalse("CoordinateSystemAxis: maximum value should be greater than the minimum value.",
                object.getMaximumValue() < object.getMinimumValue());
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CartesianCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final EllipsoidalCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final SphericalCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CylindricalCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final PolarCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final LinearCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final VerticalCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final TimeCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final UserDefinedCS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            validateAxes(object);
        }
    }

    /**
     * Performs the validation that are common to all coordinate systems. This method is
     * invoked by {@code validate} methods after they have determined the type of their
     * argument.
     *
     * @param object The object to validate (can not be null).
     */
    private void validateAxes(final CoordinateSystem object) {
        final int dimension = object.getDimension();
        assertTrue("CoordinateSystem: dimension must be greater than zero.", dimension >= 1);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = object.getAxis(i);
            assertNotNull("CoordinateSystem: axis can't be null.", axis);
            validate(axis);
        }
    }
}
