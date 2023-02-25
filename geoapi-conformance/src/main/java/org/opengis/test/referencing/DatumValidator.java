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

import java.util.Date;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.opengis.test.Units;
import org.opengis.referencing.datum.*;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link Datum} and related objects from the {@code org.opengis.datum} package. This
 * class should not be used directly; use the {@link org.opengis.test.Validators} convenience
 * static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0.1
 * @since   2.2
 */
public class DatumValidator extends ReferencingValidator {
    /**
     * The units of measurement to be used for the verifications.
     */
    private final Units units;

    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public DatumValidator(ValidatorContainer container) {
        super(container, "org.opengis.referencing.datum");
        this.units = Units.getDefault();
    }

    /**
     * Dispatches the given object to one of {@code validate} methods.
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
                    unit.isCompatible(units.degree()));
        }
        double longitude = object.getGreenwichLongitude();
        if (unit != null) {
            longitude = unit.getConverterTo(units.degree()).convert(longitude);
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
            assertTrue("Ellipsoid: unit must be compatible with metres.",
                    unit.isCompatible(units.metre()));
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
