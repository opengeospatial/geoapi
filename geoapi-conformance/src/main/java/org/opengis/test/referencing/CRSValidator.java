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
 * {@code org.opengis.referencing.crs} package. This class should not be used
 * directly; use the {@link org.opengis.test.Validators} convenience static methods
 * instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class CRSValidator extends ReferencingValidator {
    /**
     * {@code true} if validation of the conversion by {@link #validateGeneralDerivedCRS}
     * is under way. Used in order to avoid never-ending recursivity.
     */
    private final ThreadLocal<Boolean> VALIDATING = new ThreadLocal<Boolean>();

    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public CRSValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.crs");
    }

    /**
     * Dispatches the given object to one of {@code validate} methods.
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
            validateReferenceSystem(object);
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
        validateReferenceSystem(object);
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
            fail("GeocentricCRS: unknown CoordinateSystem of type " + cs.getClass().getCanonicalName() + '.');
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
        validateReferenceSystem(object);
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
        validateReferenceSystem(object);

        final GeographicCRS baseCRS = object.getBaseCRS();
        mandatory("ProjectedCRS: must have a base CRS.", baseCRS);
        validate(baseCRS);

        final CartesianCS cs = object.getCoordinateSystem();
        mandatory("ProjectedCRS: must have a CoordinateSystem.", cs);
        container.cs.validate(cs);

        final GeodeticDatum datum = object.getDatum();
        mandatory("ProjectedCRS: must have a Datum.", datum);
        container.datum.validate(datum);

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
        container.cs.dispatch(cs);

        final Datum datum = object.getDatum();
        mandatory("DerivedCRS: must have a Datum.", datum);
        container.datum.dispatch(datum);

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
                container.coordinateOperation.validate(conversion);
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
        validateReferenceSystem(object);
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
        validateReferenceSystem(object);
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
        validateReferenceSystem(object);
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
