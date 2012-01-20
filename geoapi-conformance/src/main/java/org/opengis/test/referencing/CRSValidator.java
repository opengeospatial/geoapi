/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.HashSet;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.Conversion;

import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link CoordinateReferenceSystem} and related objects from the
 * {@code org.opengis.referencing.crs} package.
 * <p>
 * This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class CRSValidator extends ReferencingValidator {
    /**
     * {@code true} if validation of the conversion by {@link #validateGeneralDerivedCRS}
     * is under way. Used in order to avoid never-ending recursivity.
     *
     * @todo Replace by a more general mechanism straight in {@link ValidatorContainer}.
     */
    private final ThreadLocal<Boolean> VALIDATING = new ThreadLocal<Boolean>();

    /**
     * Creates a new validator instance.
     *
     * @param container The set of validators to use for validating other kinds of objects
     *                  (see {@linkplain #container field javadoc}).
     */
    public CRSValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.crs");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(...)} method defined in this class (if any).
     *
     * @param  object The object to dispatch to {@code validate(...)} methods, or {@code null}.
     * @return Number of {@code validate(...)} methods invoked in this class for the given object.
     */
    public int dispatch(final CoordinateReferenceSystem object) {
        int n = 0;
        if (object != null) {
            if (object instanceof GeocentricCRS)  {validate((GeocentricCRS)  object); n++;}
            if (object instanceof GeographicCRS)  {validate((GeographicCRS)  object); n++;}
            if (object instanceof ProjectedCRS)   {validate((ProjectedCRS)   object); n++;}
            if (object instanceof DerivedCRS)     {validate((DerivedCRS)     object); n++;}
            if (object instanceof ImageCRS)       {validate((ImageCRS)       object); n++;}
            if (object instanceof EngineeringCRS) {validate((EngineeringCRS) object); n++;}
            if (object instanceof VerticalCRS)    {validate((VerticalCRS)    object); n++;}
            if (object instanceof TemporalCRS)    {validate((TemporalCRS)    object); n++;}
            if (n == 0) {
                validateReferenceSystem(object);
                container.validate(object.getCoordinateSystem());
            }
        }
        return n;
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GeocentricCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("GeocentricCRS: must have a CoordinateSystem.", cs);
        if (cs instanceof CartesianCS) {
            container.validate((CartesianCS) cs);
            final Set<AxisDirection> axes = getAxisDirections(cs);
            assertTrue("GeocentricCRS: expected Geocentric X axis direction.", axes.remove(AxisDirection.GEOCENTRIC_X));
            assertTrue("GeocentricCRS: expected Geocentric Y axis direction.", axes.remove(AxisDirection.GEOCENTRIC_Y));
            assertTrue("GeocentricCRS: expected Geocentric Z axis direction.", axes.remove(AxisDirection.GEOCENTRIC_Z));
            assertTrue("GeocentricCRS: unknown axis direction.",               axes.isEmpty());
        } else if (cs instanceof SphericalCS) {
            container.validate((SphericalCS) cs);
        } else if (cs != null) {
            fail("GeocentricCRS: unknown CoordinateSystem of type " + cs.getClass().getCanonicalName() + '.');
        }
        final GeodeticDatum datum = object.getDatum();
        mandatory("GeocentricCRS: must have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GeographicCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final EllipsoidalCS cs = object.getCoordinateSystem();
        mandatory("GeographicCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final GeodeticDatum datum = object.getDatum();
        mandatory("GeographicCRS: must have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ProjectedCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);

        final GeographicCRS baseCRS = object.getBaseCRS();
        mandatory("ProjectedCRS: must have a base CRS.", baseCRS);
        validate(baseCRS);

        final CartesianCS cs = object.getCoordinateSystem();
        mandatory("ProjectedCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final GeodeticDatum datum = object.getDatum();
        mandatory("ProjectedCRS: must have a Datum.", datum);
        container.validate(datum);

        validateGeneralDerivedCRS(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final DerivedCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);

        final CoordinateReferenceSystem baseCRS = object.getBaseCRS();
        mandatory("DerivedCRS: must have a base CRS.", baseCRS);
        dispatch(baseCRS);

        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("DerivedCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final Datum datum = object.getDatum();
        mandatory("DerivedCRS: must have a Datum.", datum);
        container.validate(datum);

        validateGeneralDerivedCRS(object);
    }

    /**
     * Validates the conversion in the given derived CRS. This method is private because
     * it doesn't perform a full validation; only the one not already done by the public
     * {@link #validate(ProjectedCRS)} and {@link #validate(DerivedCRS)} methods.
     *
     * @param object The object to validate, or {@code null}.
     */
    private void validateGeneralDerivedCRS(final GeneralDerivedCRS object) {
        if (!Boolean.TRUE.equals(VALIDATING.get())) try {
            VALIDATING.set(Boolean.TRUE);
            final Conversion conversion = object.getConversionFromBase();
            if (conversion != null) {
                container.validate(conversion);
                final CoordinateReferenceSystem   baseCRS = object.getBaseCRS();
                final CoordinateReferenceSystem sourceCRS = conversion.getSourceCRS();
                final CoordinateReferenceSystem targetCRS = conversion.getTargetCRS();
                if (baseCRS != null && sourceCRS != null) {
                    assertSame("GeneralDerivedCRS: The base CRS should be " +
                            "the source CRS of the conversion.", baseCRS, sourceCRS);
                }
                if (targetCRS != null) {
                    assertSame("GeneralDerivedCRS: The derived CRS should be " +
                            "the target CRS of the conversion.", object, targetCRS);
                }
            }
        } finally {
            VALIDATING.set(Boolean.FALSE);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ImageCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final AffineCS cs = object.getCoordinateSystem();
        mandatory("ImageCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final ImageDatum datum = object.getDatum();
        mandatory("ImageCRS: must have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final EngineeringCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("EngineeringCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final Datum datum = object.getDatum();
        mandatory("EngineeringCRS: must have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final VerticalCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final VerticalCS cs = object.getCoordinateSystem();
        mandatory("VerticalCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final VerticalDatum datum = object.getDatum();
        mandatory("VerticalCRS: must have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final TemporalCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final TimeCS cs = object.getCoordinateSystem();
        mandatory("TemporalCRS: must have a CoordinateSystem.", cs);
        container.validate(cs);

        final TemporalDatum datum = object.getDatum();
        mandatory("TemporalCRS: must have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Returns the axis directions from the given coordinate system.
     *
     * @param cs The coordinate system from which to get axis directions.
     * @return The axis directions.
     */
    private Set<AxisDirection> getAxisDirections(final CoordinateSystem cs) {
        final int dimension = cs.getDimension();
        final Set<AxisDirection> directions = new HashSet<AxisDirection>(dimension * 4/3 + 1);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = cs.getAxis(i);
            if (axis != null) {
                final AxisDirection direction = axis.getDirection();
                mandatory("Axis must have a direction.", direction);
                if (direction != null) {
                    if (!directions.add(direction)) {
                        fail("CoordinateSystem: duplicated axis direction for " + direction);
                    }
                }
            }
        }
        validate(directions);
        return directions;
    }
}
