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

import java.util.Collection;
import org.opengis.Validator;
import org.opengis.Validators;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.util.GenericName;


/**
 * Validates {@linkplain CoordinateReferenceSystem Coordinate Reference System} and related
 * objects from the {@code org.opengis.referencing} package. This class should not be used
 * directly; use the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class ReferencingValidator extends Validator {
    /**
     * The system wide instance used by {@link org.opengis.Validators}. Vendor can replace
     * this instance by some {@code Validator} subclass if some tests need to be overrided.
     */
    public static ReferencingValidator instance = new ReferencingValidator();

    /**
     * Creates a new validator.
     */
    protected ReferencingValidator() {
        super("org.opengis.referencing");
    }

    /**
     * Delegates to a {@code validate} method expecting a more specific argument. Do not override
     * this method. Override {@code #doValidate(CoordinateReferenceSystem)} instead unless a new
     * type needs to be checked.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CoordinateReferenceSystem object) {
        if (object == null) {
            return;
        }
        // TODO: check for types here.
        doValidate(object);
    }

    /**
     * Delegates to a {@code validate} method expecting a more specific argument. Do not override
     * this method. Override {@code #doValidate(CoordinateSystem)} instead unless a new type needs
     * to be checked.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CoordinateSystem object) {
        if (object == null) {
            return;
        }
        // TODO: check for types here.
        doValidate(object);
    }

    /**
     * Validates the given axis.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final CoordinateSystemAxis object) {
        if (object == null) {
            return;
        }
        doValidate(object);
        // Following tests !(max < min) instead than (max >= min) in order to accept NaN values.
        assertFalse("CoordinateSystemAxis: maximum value should be greater than the minimum value.",
                object.getMaximumValue() < object.getMinimumValue());
    }

    /**
     * Delegates to a {@code validate} method expecting a more specific argument. Do not override
     * this method. Override {@code #doValidate(IdentifiedObject)} instead unless a new type needs
     * to be checked.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final IdentifiedObject object) {
        if (object instanceof CoordinateReferenceSystem) {
            validate((CoordinateReferenceSystem) object);
        } else if (object instanceof CoordinateSystem) {
            validate((CoordinateSystem) object);
        } else if (object instanceof CoordinateSystemAxis) {
            validate((CoordinateSystemAxis) object);
        } else {
            doValidate(object);
        }
    }

    /**
     * Performs the validation that are common to all coordinate reference systems. This
     * method is invoked by {@code validate} methods after they have determined the type
     * of their argument.
     *
     * @param object The object to validate (can not be null).
     */
    public void doValidate(final CoordinateReferenceSystem object) {
        doValidate((IdentifiedObject) object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        assertNotNull("CoordinateReferenceSystem: must have a CoordinateSystem.", cs);
        validate(cs);
    }

    /**
     * Performs the validation that are common to all coordinate systems. This method is
     * invoked by {@code validate} methods after they have determined the type of their
     * argument.
     *
     * @param object The object to validate (can not be null).
     */
    public void doValidate(final CoordinateSystem object) {
        doValidate((IdentifiedObject) object);
        final int dimension = object.getDimension();
        assertTrue("CoordinateSystem: dimension must be greater than zero.", dimension >= 1);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = object.getAxis(i);
            assertNotNull("CoordinateSystem: axis can't be null.", axis);
            validate(axis);
        }
    }

    /**
     * Performs the validation that are common to all identified objects. This method is
     * invoked by {@code validate} methods after they have determined the type of their
     * argument.
     *
     * @param object The object to validate (can not be null).
     *
     * @todo Validate identifiers and name.
     */
    protected void doValidate(final IdentifiedObject object) {
        final Collection<GenericName> alias = object.getAlias();
        if (alias != null) {
            for (final GenericName name : alias) {
                Validators.validate(name);
            }
        }
        Validators.validate(object.getRemarks());
    }
}
