/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.Conversion;
import org.opengis.test.ValidatorContainer;

import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.referencing.Utilities.getName;
import static org.opengis.test.referencing.Utilities.getAxisDirections;


/**
 * Validates {@link CoordinateReferenceSystem} and related objects from the
 * {@code org.opengis.referencing.crs} package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class CRSValidator extends ReferencingValidator {
    /**
     * The axis names mandated by ISO 19111 for some particular kind of CRS.
     * See ISO 19111:2019 table 16 at page 27.
     */
    private static final String[]
            GEOCENTRIC_AXIS_NAME = {"geocentric X", "geocentric Y", "geocentric Z"},
            GEOGRAPHIC_AXIS_NAME = {"geodetic latitude", "geodetic longitude", "ellipsoidal height"},
            PROJECTED_AXIS_NAME  = {"northing", "southing", "easting", "westing", "ellipsoidal height"},
            SPHERICAL_AXIS_NAME  = {"spherical latitude", "geocentric latitude", "geocentric co-latitude",
                                    "spherical longitude", "geodetic longitude", "geocentric radius"},
            VERTICAL_AXIS_NAME   = {"depth", "gravity-related height", "gravity-related depth"};
    /*
     * Note: the ISO table does not mention "gravity-related depth" as a standard name.
     * However, this name is used in the EPSG database and seems a natural complement
     * to the "gravity-related height" standard name.
     */

    /**
     * {@code true} if validation of the conversion by {@link #validateGeneralDerivedCRS} is under way.
     * Used in order to avoid never-ending recursivity.
     *
     * @todo Replace by a more general mechanism in {@link ValidatorContainer}.
     */
    private final ThreadLocal<Boolean> VALIDATING = new ThreadLocal<>();

    /**
     * {@code true} if standard names shall be enforced when such names are defined by an OGC/ISO
     * standard. For example, the ISO 19111 standard constraints the {@link GeographicCRS} axis names
     * to <q>geodetic latitude</q>, <q>geodetic longitude</q> and <q>ellipsoidal height</q> (if 3D) names.
     * Those axis names will be verified by this validator, unless this fields is explicitly set to {@code false}.
     *
     * @see #validate(GeocentricCRS)
     * @see #validate(GeographicCRS)
     * @see #validate(ProjectedCRS)
     * @see #validate(VerticalCRS)
     *
     * @since 3.1
     */
    public boolean enforceStandardNames = true;

    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public CRSValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.crs");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    @SuppressWarnings("deprecation")
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
            if (object instanceof CompoundCRS)    {validate((CompoundCRS)    object); n++;}
            if (n == 0) {
                if (object instanceof GeodeticCRS) {
                    validate((GeodeticCRS) object, false, false);
                    n++;
                } else {
                    validateIdentifiedObject(object);
                    container.validate(object.getCoordinateSystem());
                }
            }
        }
        return n;
    }

    /**
     * Validates the given coordinate reference system. If the {@link #enforceStandardNames}
     * field is set to {@code true} (which is the default), then this method expects the axes
     * to have the following names:
     *
     * <ul>
     *   <li>For Cartesian coordinate system, <q>geocentric X</q>,
     *       <q>geocentric Y</q> and <q>geocentric Z</q>.</li>
     *   <li>For spherical coordinate system, <q>spherical latitude</q>,
     *       <q>spherical longitude</q> and <q>geocentric radius</q>.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @deprecated ISO 19111 does not have a specific type for geocentric CRS. Use geodetic CRS instead.
     */
    @Deprecated(since="3.1")
    public void validate(final GeocentricCRS object) {
        validate(object, true, false);
    }

    /**
     * Validates the given coordinate reference system. If the {@link #enforceStandardNames}
     * field is set to {@code true} (which is the default), then this method expects the axes
     * to have the following names:
     *
     * <ul>
     *   <li><q>geodetic latitude</q>, <q>geodetic longitude</q> and <q>ellipsoidal height</q> (if 3D).</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final GeographicCRS object) {
        validate(object, false, true);
    }

    /**
     * Implementation of {@link #validate(GeocentricCRS)} and {@link #validate(GeographicCRS)}.
     *
     * @param object          the object to validate, or {@code null}.
     * @param skipGeographic  whether to skip validation of geographic CRS.
     * @param skipGeocentric  whether to skip validation of geocentric CRS.
     */
    private void validate(final GeodeticCRS object, final boolean skipGeographic, final boolean skipGeocentric) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("GeodeticCRS: shall have a CoordinateSystem.", cs);
        if (!skipGeographic && cs instanceof EllipsoidalCS) {
            container.validate((EllipsoidalCS) cs);
            if (enforceStandardNames) {
                assertStandardNames("GeographicCRS", cs, GEOGRAPHIC_AXIS_NAME);
            }
        } else if (!skipGeocentric && cs instanceof CartesianCS) {
            container.validate((CartesianCS) cs);
            final Set<AxisDirection> axes = getAxisDirections(cs);
            validate(axes);
            assertTrue(axes.remove(AxisDirection.GEOCENTRIC_X), "GeocentricCRS: expected Geocentric X axis direction.");
            assertTrue(axes.remove(AxisDirection.GEOCENTRIC_Y), "GeocentricCRS: expected Geocentric Y axis direction.");
            assertTrue(axes.remove(AxisDirection.GEOCENTRIC_Z), "GeocentricCRS: expected Geocentric Z axis direction.");
            assertTrue(axes.isEmpty(), "GeocentricCRS: unknown axis direction.");
            if (enforceStandardNames) {
                assertStandardNames("GeocentricCRS", cs, GEOCENTRIC_AXIS_NAME);
            }
        } else if (!skipGeocentric && cs instanceof SphericalCS) {
            container.validate((SphericalCS) cs);
            if (enforceStandardNames) {
                assertStandardNames("GeocentricCRS", cs, SPHERICAL_AXIS_NAME);
            }
        } else if (cs != null) {
            fail("GeodeticCRS: unknown CoordinateSystem of type " + cs.getClass().getCanonicalName() + '.');
        }
        final GeodeticDatum datum = object.getDatum();
        mandatory("GeodeticCRS: shall have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system. If the {@link #enforceStandardNames}
     * field is set to {@code true} (which is the default), then this method expects the axes
     * to have the following names:
     *
     * <ul>
     *   <li><q>northing</q> or <q>southing</q>, <q>easting</q> or <q>westing</q>.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final ProjectedCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);

        final GeographicCRS baseCRS = object.getBaseCRS();
        mandatory("ProjectedCRS: shall have a base CRS.", baseCRS);
        validate(baseCRS);

        final CartesianCS cs = object.getCoordinateSystem();
        mandatory("ProjectedCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);
        if (enforceStandardNames) {
            assertStandardNames("ProjectedCRS", cs, PROJECTED_AXIS_NAME);
        }
        final GeodeticDatum datum = object.getDatum();
        mandatory("ProjectedCRS: shall have a Datum.", datum);
        container.validate(datum);

        validateGeneralDerivedCRS(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final DerivedCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);

        final CoordinateReferenceSystem baseCRS = object.getBaseCRS();
        mandatory("DerivedCRS: shall have a base CRS.", baseCRS);
        dispatch(baseCRS);

        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("DerivedCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);

        final Datum datum = object.getDatum();
        mandatory("DerivedCRS: shall have a Datum.", datum);
        container.validate(datum);

        validateGeneralDerivedCRS(object);
    }

    /**
     * Validates the conversion in the given derived CRS. This method is private because
     * it doesn't perform a full validation; only the one not already done by the public
     * {@link #validate(ProjectedCRS)} and {@link #validate(DerivedCRS)} methods.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    private void validateGeneralDerivedCRS(final DerivedCRS object) {
        if (!Boolean.TRUE.equals(VALIDATING.get())) try {
            VALIDATING.set(Boolean.TRUE);
            final Conversion conversion = object.getConversionFromBase();
            if (conversion != null) {
                container.validate(conversion);
                final CoordinateReferenceSystem   baseCRS = object.getBaseCRS();
                final CoordinateReferenceSystem sourceCRS = conversion.getSourceCRS();
                final CoordinateReferenceSystem targetCRS = conversion.getTargetCRS();
                if (baseCRS != null && sourceCRS != null) {
                    assertSame(baseCRS, sourceCRS,
                            "DerivedCRS: The base CRS should be the source CRS of the conversion.");
                }
                if (targetCRS != null) {
                    assertSame(object, targetCRS,
                            "DerivedCRS: The derived CRS should be the target CRS of the conversion.");
                }
            }
        } finally {
            VALIDATING.set(Boolean.FALSE);
        }
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @deprecated {@code ImageCRS} is replaced by {@link EngineeringCRS} as of ISO 19111:2019.
     */
    @SuppressWarnings("removal")
    @Deprecated(since="3.1", forRemoval=true)
    public void validate(final ImageCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final AffineCS cs = object.getCoordinateSystem();
        mandatory("ImageCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);

        final ImageDatum datum = object.getDatum();
        mandatory("ImageCRS: shall have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    @SuppressWarnings("deprecation")
    public void validate(final EngineeringCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("EngineeringCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);
        String message = "EngineeringCRS: illegal coordinate system type. Shall be one of"
                       + " affine, Cartesian, cylindrical, linear, polar, or spherical.";
        assertTrue(cs instanceof AffineCS      ||      // Include the CartesianCS case.
                   cs instanceof CylindricalCS ||
                   cs instanceof LinearCS      ||
                   cs instanceof PolarCS       ||
                   cs instanceof SphericalCS   ||
                   cs instanceof UserDefinedCS,
                message);

        assertFalse(cs instanceof EllipsoidalCS ||
                    cs instanceof VerticalCS    ||
                    cs instanceof ParametricCS  ||
                    cs instanceof TimeCS,
                message);

        final Datum datum = object.getDatum();
        mandatory("EngineeringCRS: shall have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system. If the {@link #enforceStandardNames}
     * field is set to {@code true} (which is the default), then this method expects the axes
     * to have the following names:
     *
     * <ul>
     *   <li><q>depth</q> or <q>gravity-related height</q>.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final VerticalCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final VerticalCS cs = object.getCoordinateSystem();
        mandatory("VerticalCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);
        if (enforceStandardNames) {
            assertStandardNames("VerticalCRS", cs, VERTICAL_AXIS_NAME);
        }
        final VerticalDatum datum = object.getDatum();
        mandatory("VerticalCRS: shall have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final TemporalCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final TimeCS cs = object.getCoordinateSystem();
        mandatory("TemporalCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);

        final TemporalDatum datum = object.getDatum();
        mandatory("TemporalCRS: shall have a Datum.", datum);
        container.validate(datum);
    }

    /**
     * Validates the given coordinate reference system.
     * This method will validate every individual components in the given compound CRS.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final CompoundCRS object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("CompoundCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);
        AxisDirection[] directions = null;
        if (cs != null) {
            directions = new AxisDirection[cs.getDimension()];
            for (int i=0; i<directions.length; i++) {
                CoordinateSystemAxis axis = cs.getAxis(i);
                if (axis != null) {
                    directions[i] = axis.getDirection();
                }
            }
        }
        /*
         * Verify the components, potentially with nested compound CRS.
         */
        final List<CoordinateReferenceSystem> components = object.getComponents();
        mandatory("CompoundCRS: shall have components.", components);
        if (components != null) {
            int dimension = 0;
            // If the above 'mandatory(…)' call accepted an empty list, we accept it too.
            assertNotEquals(1, components.size(), "CompoundCRS: shall have at least 2 components.");
            for (final CoordinateReferenceSystem component : components) {
                dispatch(component);
                dimension = compareAxes(component.getCoordinateSystem(), directions, dimension);
            }
            if (directions != null) {
                assertEquals(directions.length, dimension, "CompoundCRS: unexpected sum of the dimension of components.");
            }
        }
        /*
         * Verify the components again, but without nested compound CRS.
         */
        final List<SingleCRS> singles = object.getSingleComponents();
        mandatory("CompoundCRS: shall have components.", singles);
        if (singles != null) {
            int dimension = 0;
            assertNotEquals(1, singles.size(), "CompoundCRS: shall have at least 2 components.");
            for (final SingleCRS component : singles) {
                dispatch(component);
                dimension = compareAxes(component.getCoordinateSystem(), directions, dimension);
            }
            if (directions != null) {
                assertEquals(directions.length, dimension, "CompoundCRS: unexpected sum of the dimension of components.");
            }
        }
    }

    /**
     * Checks if the axis directions of the given coordinate system are the expected one.
     *
     * @param  cs          the coordinate system to validate, or {@code null} if none.
     * @param  directions  the expected directions, or {@code null} if unknown.
     * @param  index       index of the first element in the {@code directions} array.
     * @return index after the last element in the {@code directions} array.
     */
    private static int compareAxes(final CoordinateSystem cs, final AxisDirection[] directions, int index) {
        if (directions != null) {
            assertNotNull("CompoundCRS: missing coordinate system for component.");
            final int dimension = cs.getDimension();
            assertTrue(index + dimension < directions.length, "CompoundCRS: components have too many dimensions.");
            for (int i=0; i<dimension; i++) {
                final AxisDirection expected = directions[index++];
                if (expected != null) {
                    final int d = i;        // Because lambda functions require final values.
                    final Supplier<String> message = () -> "CompoundCRS: inconsistent axis at dimension " + d + ".";
                    final CoordinateSystemAxis axis = cs.getAxis(d);
                    assertNotNull(axis, message);
                    assertEquals(expected, axis.getDirection(), message);
                }
            }
        }
        return index;
    }

    /**
     * Verifies that the given coordinate system uses the given standard axis names.
     *
     * @param type           type of coordinate system to report in error messages.
     * @param cs             the coordinate system to validate.
     * @param standardNames  the expected standard axis names.
     */
    private static void assertStandardNames(final String type, final CoordinateSystem cs, final String[] standardNames) {
        final int dimension = cs.getDimension();
        final Set<String> names = new LinkedHashSet<>(dimension * 4/3 + 1);
        for (int i=0; i<dimension; i++) {
            final String name = getName(cs.getAxis(i));
            if (name != null && !names.add(toLowerCase(name.trim()))) {
                fail(type + ": duplicated axis name: " + name);
            }
        }
        final List<String> notFound = new ArrayList<>(names.size());
        for (final String name : standardNames) {
            if (!names.remove(name)) {
                notFound.add(name);
            }
        }
        if (!names.isEmpty()) {
            fail(type + ": Non-standard axis names: " + names + ". Expected some of " + notFound + '.');
        }
    }

    /**
     * Returns the given string in lower cases, except for the last letter if it is single.
     * The intent is to leave the trailing X, Y or Z case unchanged in "geocentric X",
     * "geocentric Y" and "geocentric Z" axis names.
     *
     * @param  name  the name in mixed case.
     * @return the given string in lower case, except last letter if single.
     */
    static String toLowerCase(final String name) {
        int s = name.length();
        if (s >= 3) {
            s -= Character.charCount(name.codePointBefore(s));
            final int c = name.codePointBefore(s);
            if (Character.isSpaceChar(c)) {
                s -= Character.charCount(c);
                return name.substring(0, s).toLowerCase().concat(name.substring(s));
            }
        }
        return name.toLowerCase();
    }
}
