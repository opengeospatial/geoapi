/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.referencing;

import java.util.Date;
import javax.measure.unit.SI;
import javax.measure.unit.NonSI;
import javax.measure.unit.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.opengis.referencing.datum.*;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Datum} and related objects from the {@code org.opengis.datum} package. This
 * class should not be used directly; use the {@link org.opengis.Validators} convenience static
 * methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class DatumValidator extends ReferencingValidator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public DatumValidator(ValidatorContainer container) {
        super(container, "org.opengis.referencing.datum");
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
            validateIdentifiedObject(object);
        }
    }

    /**
     * Validates the given prime meridian.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final PrimeMeridian object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Unit<Angle> unit = object.getAngularUnit();
        mandatory("PrimeMeridian: must have a unit of measurement.", unit);
        if (unit != null) {
            assertTrue("PrimeMeridian: unit must be compatible with degrees.",
                    unit.isCompatible(NonSI.DEGREE_ANGLE));
        }
        double longitude = object.getGreenwichLongitude();
        if (unit != null) {
            longitude = unit.getConverterTo(NonSI.DEGREE_ANGLE).convert(longitude);
        }
        assertBetween("PrimeMeridian: expected longitude in [-180 ... +180°] range.", -180, +180, longitude);
    }

    /**
     * Validates the given ellipsoid.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Ellipsoid object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Unit<Length> unit = object.getAxisUnit();
        mandatory("Ellipsoid: must have a unit of measurement.", unit);
        if (unit != null) {
            assertTrue("Ellipsoid: unit must be compatible with metres.", unit.isCompatible(SI.METRE));
        }
        final double semiMajor = object.getSemiMajorAxis();
        final double semiMinor = object.getSemiMinorAxis();
        assertTrue("Ellipsoid: expected semi-minor <= semi-major axis length.", semiMinor <= semiMajor);
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GeodeticDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final PrimeMeridian meridian = object.getPrimeMeridian();
        mandatory("GeodeticDatum: must have a prime meridian.", meridian);
        validate(meridian);

        final Ellipsoid ellipsoid = object.getEllipsoid();
        mandatory("GeodeticDatum: must have an ellipsoid.", ellipsoid);
        validate(ellipsoid);
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final VerticalDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final VerticalDatumType type = object.getVerticalDatumType();
        mandatory("VerticalDatum: must have a datum type.", type);
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final TemporalDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Date origin = object.getOrigin();
        mandatory("TemporalDatum: expected an origin.", origin);
        assertNull("TemporalDatum: should not have anchor point.", object.getAnchorPoint());
        assertNull("TemporalDatum: should not have realization epoch.", object.getRealizationEpoch());
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ImageDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final PixelInCell pc = object.getPixelInCell();
        mandatory("ImageDatum: must specify PixelInCell.", pc);
    }

    /**
     * Validates the given datum.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final EngineeringDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
    }
}
