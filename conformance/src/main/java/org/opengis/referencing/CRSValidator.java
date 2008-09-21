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

import java.util.Set;
import java.util.HashSet;
import org.opengis.Validator;
import org.opengis.ValidatorContainer;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;


/**
 * Validates {@link CoordinateReferenceSystem} and related objects from the
 * {@code org.opengis.referencing.crs} package. This class should not be used
 * directly; use the {@link org.opengis.Validators} convenience static methods
 * instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class CRSValidator extends Validator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public CRSValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.crs");
    }

    /**
     * Dispatchs the given object to one of {@code validate} methods.
     *
     * @param object The object to dispatch.
     */
    public void dispatch(final CoordinateReferenceSystem object) {
        if (object instanceof GeocentricCRS) {
            validate((GeocentricCRS) object);
        } else if (object instanceof GeographicCRS) {
            validate((GeographicCRS) object);
        } else if (object instanceof ProjectedCRS) {
            validate((ProjectedCRS) object);
        } else if (object instanceof DerivedCRS) {
            validate((DerivedCRS) object);
        } else if (object instanceof ImageCRS) {
            validate((ImageCRS) object);
        } else if (object instanceof EngineeringCRS) {
            validate((EngineeringCRS) object);
        } else if (object instanceof VerticalCRS) {
            validate((VerticalCRS) object);
        } else if (object instanceof TemporalCRS) {
            validate((TemporalCRS) object);
        } else if (object != null) {
            container.referencing.validate(object);
            container.cs.dispatch(object.getCoordinateSystem());
        }
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
        container.referencing.validate(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("GeocentricCRS: must have a CoordinateSystem.", cs);
        if (cs instanceof CartesianCS) {
            container.cs.validate((CartesianCS) cs);
            final Set<AxisDirection> axes = getAxisDirections(cs);
            assertTrue("GeocentricCRS: expected Geocentric X axis direction.",
                    axes.remove(AxisDirection.GEOCENTRIC_X));
            assertTrue("GeocentricCRS: expected Geocentric Y axis direction.",
                    axes.remove(AxisDirection.GEOCENTRIC_Y));
            assertTrue("GeocentricCRS: expected Geocentric Z axis direction.",
                    axes.remove(AxisDirection.GEOCENTRIC_Z));
            assertTrue("GeocentricCRS: unknown axis direction.", axes.isEmpty());
        } else if (cs instanceof SphericalCS) {
            container.cs.validate((SphericalCS) cs);
        } else if (cs != null) {
            fail("GeocentricCRS: unknown CoordinateSystem of type " + cs.getClass().getName() + '.');
        }
        final GeodeticDatum datum = object.getDatum();
        mandatory("GeocentricCRS: must have a Datum.", datum);
        container.datum.validate(datum);
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
        container.referencing.validate(object);
        final EllipsoidalCS cs = object.getCoordinateSystem();
        mandatory("GeographicCRS: must have a CoordinateSystem.", cs);
        container.cs.validate(cs);

        final GeodeticDatum datum = object.getDatum();
        mandatory("GeographicCRS: must have a Datum.", datum);
        container.datum.validate(datum);
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
        container.referencing.validate(object);
        final CartesianCS cs = object.getCoordinateSystem();
        mandatory("ProjectedCRS: must have a CoordinateSystem.", cs);
        container.cs.validate(cs);

        final GeodeticDatum datum = object.getDatum();
        mandatory("ProjectedCRS: must have a Datum.", datum);
        container.datum.validate(datum);
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
        container.referencing.validate(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("DerivedCRS: must have a CoordinateSystem.", cs);
        container.cs.dispatch(cs);

        final Datum datum = object.getDatum();
        mandatory("DerivedCRS: must have a Datum.", datum);
        container.datum.dispatch(datum);
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
        container.referencing.validate(object);
        final AffineCS cs = object.getCoordinateSystem();
        mandatory("ImageCRS: must have a CoordinateSystem.", cs);
        container.cs.dispatch(cs);

        final ImageDatum datum = object.getDatum();
        mandatory("ImageCRS: must have a Datum.", datum);
        container.datum.validate(datum);
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
        container.referencing.validate(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("EngineeringCRS: must have a CoordinateSystem.", cs);
        container.cs.dispatch(cs);

        final Datum datum = object.getDatum();
        mandatory("EngineeringCRS: must have a Datum.", datum);
        container.datum.dispatch(datum);
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
        container.referencing.validate(object);
        final VerticalCS cs = object.getCoordinateSystem();
        mandatory("VerticalCRS: must have a CoordinateSystem.", cs);
        container.cs.validate(cs);

        final VerticalDatum datum = object.getDatum();
        mandatory("VerticalCRS: must have a Datum.", datum);
        container.datum.validate(datum);
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
        container.referencing.validate(object);
        final TimeCS cs = object.getCoordinateSystem();
        mandatory("TemporalCRS: must have a CoordinateSystem.", cs);
        container.cs.validate(cs);

        final TemporalDatum datum = object.getDatum();
        mandatory("TemporalCRS: must have a Datum.", datum);
        container.datum.validate(datum);
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
        return directions;
    }
}
