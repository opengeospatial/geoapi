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

import java.util.Date;
import javax.measure.unit.SI;
import javax.measure.unit.NonSI;
import javax.measure.unit.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.opengis.Validator;
import org.opengis.referencing.datum.*;


/**
 * Validates {@link Datum}s. This class should not be used directly;
 * use the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class DatumValidator extends Validator {
    /**
     * The system wide instance used by {@link org.opengis.Validators}. Vendor can replace
     * this instance by some {@code Validator} subclass if some tests need to be overrided.
     */
    public static DatumValidator instance = new DatumValidator();

    /**
     * Creates a new validator.
     */
    protected DatumValidator() {
        super("org.opengis.referencing.datum");
    }

    /**
     * Dispatchs the given object to one of {@code validate} methods.
     *
     * @param object The object to dispatch.
     */
    public void dispatch(final Datum object) {
        if (object instanceof GeodeticDatum) {
            validate((GeodeticDatum) object);
        } else if (object instanceof VerticalDatum) {
            validate((VerticalDatum) object);
        } else if (object instanceof TemporalDatum) {
            validate((TemporalDatum) object);
        } else if (object instanceof ImageDatum) {
            validate((ImageDatum) object);
        } else if (object instanceof EngineeringDatum) {
            validate((EngineeringDatum) object);
        } else {
            ReferencingValidator.instance.validate(object);
        }
    }

    /**
     * Validates the given prime meridian.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final PrimeMeridian object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            Unit<Angle> unit = object.getAngularUnit();
            assertNotNull("PrimeMeridian: must have a unit of measurement.", unit);
            assertTrue("PrimeMeridian: unit must be compatible with degrees.",
                    unit.isCompatible(NonSI.DEGREE_ANGLE));
            double longitude = object.getGreenwichLongitude();
            longitude = unit.getConverterTo(NonSI.DEGREE_ANGLE).convert(longitude);
            assertTrue("PrimeMeridian: expected longitude in [-180 ... +180°] range.",
                    longitude >= -180 && longitude <= 180);
        }
    }

    /**
     * Validates the given ellipsoid.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Ellipsoid object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            Unit<Length> unit = object.getAxisUnit();
            assertNotNull("Ellipsoid: must have a unit of measurement.", unit);
            assertTrue("Ellipsoid: unit must be compatible with metres.",
                    unit.isCompatible(SI.METRE));
            double semiMajor = object.getSemiMajorAxis();
            double semiMinor = object.getSemiMinorAxis();
            assertTrue("Ellipsoid: semi-minor axis length must be less than or equal to the semi-major axis length.",
                    semiMinor <= semiMajor);
        }
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GeodeticDatum object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            PrimeMeridian meridian = object.getPrimeMeridian();
            assertNotNull("GeodeticDatum: must have a prime meridian.", meridian);
            validate(meridian);
            Ellipsoid ellipsoid = object.getEllipsoid();
            assertNotNull("GeodeticDatum: must have an ellipsoid.", ellipsoid);
            validate(ellipsoid);
        }
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final VerticalDatum object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            VerticalDatumType type = object.getVerticalDatumType();
            assertNotNull("VerticalDatum: must have a datum type.", type);
        }
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final TemporalDatum object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            Date origin = object.getOrigin();
            assertNotNull("TemporalDatum: expected an origin.", origin);
            assertNull("TemporalDatum: should not have anchor point.",
                    object.getAnchorPoint());
            assertNull("TemporalDatum: should not have realization epoch.",
                    object.getRealizationEpoch());
        }
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ImageDatum object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            PixelInCell pc = object.getPixelInCell();
            assertNotNull("ImageDatum: must specify PixelInCell.", pc);
        }
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final EngineeringDatum object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            // Put tests here if any.
        }
    }
}
