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
import org.opengis.referencing.datum.*;
import org.opengis.util.GenericName;


/**
 * Validators of {@linkplain CoordinateReferenceSystem Coordinate Reference System} and
 * related objects from the {@code org.opengis.referencing} package. This class should
 * not be used directly; use the {@link org.opengis.Validators} convenience static methods
 * instead.
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
     * this method. Override {@code #validate(IdentifiedObject)} instead unless a new type needs
     * to be checked.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void dispatch(final IdentifiedObject object) {
        if (object instanceof CoordinateReferenceSystem) {
            CRSValidator.instance.dispatch((CoordinateReferenceSystem) object);
        } else if (object instanceof CoordinateSystem) {
            CSValidator.instance.dispatch((CoordinateSystem) object);
        } else if (object instanceof CoordinateSystemAxis) {
            CSValidator.instance.validate((CoordinateSystemAxis) object);
        } else if (object instanceof Datum) {
            DatumValidator.instance.dispatch((Datum) object);
        } else if (object instanceof Ellipsoid) {
            DatumValidator.instance.validate((Ellipsoid) object);
        } else if (object instanceof PrimeMeridian) {
            DatumValidator.instance.validate((PrimeMeridian) object);
        } else {
            validate(object);
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
    protected void validate(final IdentifiedObject object) {
        final Collection<GenericName> alias = object.getAlias();
        if (alias != null) {
            for (final GenericName name : alias) {
                Validators.validate(name);
            }
        }
        Validators.validate(object.getRemarks());
    }
}
