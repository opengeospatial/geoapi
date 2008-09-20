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
        } else if (object != null) {
            ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
        assertValidRange("CoordinateSystemAxis: expected maximum >= minimum.",
                object.getMaximumValue(), object.getMinimumValue());
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
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
        ReferencingValidator.instance.validate(object);
        validateAxes(object);
        final int dimension = object.getDimension();
        assertBetween("UserDefinedCS: wrong number of dimensions.", 2, 3, dimension);
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
        assertStrictlyPositive("CoordinateSystem: dimension must be greater than zero.", dimension);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = object.getAxis(i);
            mandatory("CoordinateSystem: axis can't be null.", axis);
            validate(axis);
        }
    }
}
