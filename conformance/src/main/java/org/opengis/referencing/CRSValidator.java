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
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;


/**
 * Validates {@linkplain CoordinateReferenceSystem}s. This class should not be used directly;
 * use the {@link org.opengis.Validators} convenience static methods instead.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class CRSValidator extends Validator {
    /**
     * The system wide instance used by {@link org.opengis.Validators}. Vendor can replace
     * this instance by some {@code Validator} subclass if some tests need to be overrided.
     */
    public static CRSValidator instance = new CRSValidator();

    /**
     * Creates a new validator.
     */
    protected CRSValidator() {
        super("org.opengis.referencing.crs");
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
        } else {
            ReferencingValidator.instance.validate(object);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GeocentricCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            CoordinateSystem cs = object.getCoordinateSystem();
            assertNotNull("GeocentricCRS: must have a CoordinateSystem.", cs);
            if (cs instanceof CartesianCS) {
                CSValidator.instance.validate((CartesianCS) cs);
                final Set<AxisDirection> axes = getAxisDirections(cs);
                assertTrue("GeocentricCRS: expected Geocentric X axis direction.",
                        axes.remove(AxisDirection.GEOCENTRIC_X));
                assertTrue("GeocentricCRS: expected Geocentric Y axis direction.",
                        axes.remove(AxisDirection.GEOCENTRIC_Y));
                assertTrue("GeocentricCRS: expected Geocentric Z axis direction.",
                        axes.remove(AxisDirection.GEOCENTRIC_Z));
                assertTrue("GeocentricCRS: unknown axis direction.", axes.isEmpty());
            } else if (cs instanceof SphericalCS) {
                CSValidator.instance.validate((SphericalCS) cs);
            } else {
                fail("GeocentricCRS: unknown CoordinateSystem.");
            }
            GeodeticDatum datum = object.getDatum();
            assertNotNull("GeocentricCRS: must have a Datum.", datum);
            DatumValidator.instance.validate(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final GeographicCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            EllipsoidalCS cs = object.getCoordinateSystem();
            assertNotNull("GeographicCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.validate(cs);

            GeodeticDatum datum = object.getDatum();
            assertNotNull("GeographicCRS: must have a Datum.", datum);
            DatumValidator.instance.validate(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ProjectedCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            CartesianCS cs = object.getCoordinateSystem();
            assertNotNull("ProjectedCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.validate(cs);

            GeodeticDatum datum = object.getDatum();
            assertNotNull("ProjectedCRS: must have a Datum.", datum);
            DatumValidator.instance.validate(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final DerivedCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            CoordinateSystem cs = object.getCoordinateSystem();
            assertNotNull("DerivedCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.dispatch(cs);

            Datum datum = object.getDatum();
            assertNotNull("DerivedCRS: must have a Datum.", datum);
            DatumValidator.instance.dispatch(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ImageCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            AffineCS cs = object.getCoordinateSystem();
            assertNotNull("ImageCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.dispatch(cs);

            ImageDatum datum = object.getDatum();
            assertNotNull("ImageCRS: must have a Datum.", datum);
            DatumValidator.instance.validate(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final EngineeringCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            CoordinateSystem cs = object.getCoordinateSystem();
            assertNotNull("EngineeringCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.dispatch(cs);

            Datum datum = object.getDatum();
            assertNotNull("EngineeringCRS: must have a Datum.", datum);
            DatumValidator.instance.dispatch(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final VerticalCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            VerticalCS cs = object.getCoordinateSystem();
            assertNotNull("VerticalCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.validate(cs);

            VerticalDatum datum = object.getDatum();
            assertNotNull("VerticalCRS: must have a Datum.", datum);
            DatumValidator.instance.validate(datum);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final TemporalCRS object) {
        ReferencingValidator.instance.validate(object);
        if (object != null) {
            TimeCS cs = object.getCoordinateSystem();
            assertNotNull("TemporalCRS: must have a CoordinateSystem.", cs);
            CSValidator.instance.validate(cs);

            TemporalDatum datum = object.getDatum();
            assertNotNull("TemporalCRS: must have a Datum.", datum);
            DatumValidator.instance.validate(datum);
        }
    }

    /**
     * Returns the axis directions from the given coordinate system.
     *
     * @param cs The coordinate system from which to get axis directions.
     * @return The axis directions.
     */
    private static Set<AxisDirection> getAxisDirections(final CoordinateSystem cs) {
        final int dimension = cs.getDimension();
        final Set<AxisDirection> directions = new HashSet<AxisDirection>(dimension * 4/3 + 1);
        for (int i=0; i<dimension; i++) {
            final CoordinateSystemAxis axis = cs.getAxis(i);
            if (axis != null) {
                assertTrue("CoordinateSystem: duplicated axis direction.",
                        directions.add(axis.getDirection()));
            }
        }
        return directions;
    }
}
