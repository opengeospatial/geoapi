/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.referencing;

import java.util.Date;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.opengis.referencing.datum.*;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link Datum} and related objects from the {@code org.opengis.datum} package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class DatumValidator extends ReferencingValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public DatumValidator(ValidatorContainer container) {
        super(container, "org.opengis.referencing.datum");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final Datum object) {
        int n = 0;
        if (object != null) {
            if (object instanceof GeodeticDatum)    {validate((GeodeticDatum)    object); n++;}
            if (object instanceof VerticalDatum)    {validate((VerticalDatum)    object); n++;}
            if (object instanceof TemporalDatum)    {validate((TemporalDatum)    object); n++;}
            if (object instanceof ImageDatum)       {validate((ImageDatum)       object); n++;}
            if (object instanceof EngineeringDatum) {validate((EngineeringDatum) object); n++;}
            if (n == 0) {
                validateIdentifiedObject(object);
            }
        }
        return n;
    }

    /**
     * Validates the given prime meridian.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final PrimeMeridian object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Unit<Angle> unit = object.getAngularUnit();
        mandatory("PrimeMeridian: shall have a unit of measurement.", unit);
        double longitude = object.getGreenwichLongitude();
        if (unit != null) {
            final Unit<Angle> degree = units.degree();
            assertTrue("PrimeMeridian: unit must be compatible with degrees.", unit.isCompatible(degree));
            longitude = unit.getConverterTo(degree).convert(longitude);
        }
        assertBetween("PrimeMeridian: expected longitude in [-180 … +180]° range.", -180, +180, longitude);
    }

    /**
     * Validates the given ellipsoid.
     * This method checks the following conditions:
     *
     * <ul>
     *   <li>{@linkplain Ellipsoid#getAxisUnit() Axis unit} is defined and is linear.</li>
     *   <li>{@linkplain Ellipsoid#getSemiMinorAxis() semi-minor} &lt;= {@linkplain Ellipsoid#getSemiMajorAxis() semi-major}.</li>
     *   <li>{@linkplain Ellipsoid#getInverseFlattening() inverse flattening} &gt; 0.</li>
     *   <li>Consistency of semi-minor axis length with inverse flattening factor.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Ellipsoid object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Unit<Length> unit = object.getAxisUnit();
        mandatory("Ellipsoid: shall have a unit of measurement.", unit);
        if (unit != null) {
            assertTrue("Ellipsoid: unit must be compatible with metres.", unit.isCompatible(units.metre()));
        }
        final double semiMajor         = object.getSemiMajorAxis();
        final double semiMinor         = object.getSemiMinorAxis();
        final double inverseFlattening = object.getInverseFlattening();
        assertTrue("Ellipsoid: expected semi-major axis length > 0.", semiMajor > 0);
        assertTrue("Ellipsoid: expected semi-minor axis length > 0.", semiMinor > 0);
        assertTrue("Ellipsoid: expected semi-minor <= semi-major axis length.", semiMinor <= semiMajor);
        assertTrue("Ellipsoid: expected inverse flattening > 0.", inverseFlattening > 0);
        if (!object.isSphere()) {
            assertEquals("Ellipsoid: inconsistent semi-major axis length.",
                    semiMajor - semiMajor/inverseFlattening, semiMinor, semiMinor*DEFAULT_TOLERANCE);
            assertEquals("Ellipsoid: inconsistent inverse flattening.",
                    semiMajor / (semiMajor-semiMinor), inverseFlattening, inverseFlattening*DEFAULT_TOLERANCE);
        }
    }

    /**
     * Validates the given datum.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final GeodeticDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final PrimeMeridian meridian = object.getPrimeMeridian();
        mandatory("GeodeticDatum: shall have a prime meridian.", meridian);
        validate(meridian);

        final Ellipsoid ellipsoid = object.getEllipsoid();
        mandatory("GeodeticDatum: shall have an ellipsoid.", ellipsoid);
        validate(ellipsoid);
    }

    /**
     * Validates the given datum.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final VerticalDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final VerticalDatumType type = object.getVerticalDatumType();
        mandatory("VerticalDatum: shall have a datum type.", type);
    }

    /**
     * Validates the given datum.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final TemporalDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Date origin = object.getOrigin();
        mandatory("TemporalDatum: expected an origin.", origin);
        forbidden("TemporalDatum: should not have anchor point.", object.getAnchorPoint());
        forbidden("TemporalDatum: should not have realization epoch.", object.getRealizationEpoch());
    }

    /**
     * Validates the given datum.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final ImageDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final PixelInCell pc = object.getPixelInCell();
        mandatory("ImageDatum: shall specify PixelInCell.", pc);
    }

    /**
     * Validates the given datum.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final EngineeringDatum object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
    }
}
