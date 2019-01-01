/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.Conversion;
import org.opengis.test.ValidatorContainer;

import static org.opengis.test.Assert.*;
import static org.opengis.test.referencing.Utilities.*;


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
     * See ISO 19111:2007 table 16 at page 25.
     */
    private static final String[]
            GEOCENTRIC_AXIS_NAME = {"geocentric X", "geocentric Y", "geocentric Z"},
            GEOGRAPHIC_AXIS_NAME = {"geodetic latitude", "geodetic longitude", "ellipsoidal height"},
            PROJECTED_AXIS_NAME  = {"northing", "southing", "easting", "westing"},
            SPHERICAL_AXIS_NAME  = {"spherical latitude", "spherical longitude", "geocentric radius"},
            VERTICAL_AXIS_NAME   = {"depth", "gravity-related height", "gravity-related depth"};
    /*
     * Note: the ISO table does not mention "gravity-related depth" as a standard name.
     * However this name is used in the EPSG database and seems a natural complement to
     * the "gravity-related height" standard name.
     */

    /**
     * {@code true} if validation of the conversion by {@link #validateGeneralDerivedCRS}
     * is under way. Used in order to avoid never-ending recursivity.
     *
     * @todo Replace by a more general mechanism straight in {@link ValidatorContainer}.
     */
    private final ThreadLocal<Boolean> VALIDATING = new ThreadLocal<>();

    /**
     * {@code true} if standard names shall be enforced when such names are defined by an OGC/ISO
     * standard. For example the ISO 19111 standard constraints the {@link GeographicCRS} axis names
     * to <cite>"geodetic latitude"</cite>, <cite>"geodetic longitude"</cite> and <cite>"ellipsoidal
     * height"</cite> (if 3D) names. Those axis names will be verified by this validator, unless
     * this fields is explicitely set to {@code false}.
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
                } else {
                    validateReferenceSystem(object);
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
     *   <li>For Cartesian coordinate system, <cite>"geocentric X"</cite>,
     *       <cite>"geocentric Y"</cite> and <cite>"geocentric Z"</cite>.</li>
     *   <li>For spherical coordinate system, <cite>"spherical latitude"</cite>,
     *       <cite>"spherical longitude"</cite> and <cite>"geocentric radius"</cite>.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final GeocentricCRS object) {
        validate(object, true, false);
    }

    /**
     * Validates the given coordinate reference system. If the {@link #enforceStandardNames}
     * field is set to {@code true} (which is the default), then this method expects the axes
     * to have the following names:
     *
     * <ul>
     *   <li><cite>"geodetic latitude"</cite>, <cite>"geodetic longitude"</cite> and
     *       <cite>"ellipsoidal height"</cite> (if 3D).</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final GeographicCRS object) {
        validate(object, false, true);
    }

    /**
     * Implementation of {@link #validate(GeocentricCRS)} and {@link #validate(GeographicCRS)}.
     */
    private void validate(final GeodeticCRS object, final boolean skipGeographic, final boolean skipGeocentric) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
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
            assertTrue("GeocentricCRS: expected Geocentric X axis direction.", axes.remove(AxisDirection.GEOCENTRIC_X));
            assertTrue("GeocentricCRS: expected Geocentric Y axis direction.", axes.remove(AxisDirection.GEOCENTRIC_Y));
            assertTrue("GeocentricCRS: expected Geocentric Z axis direction.", axes.remove(AxisDirection.GEOCENTRIC_Z));
            assertTrue("GeocentricCRS: unknown axis direction.",               axes.isEmpty());
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
     *   <li><cite>"northing"</cite> or <cite>"southing"</cite>, <cite>"easting"</cite> or
     *       <cite>"westing"</cite>.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final ProjectedCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);

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
        validateReferenceSystem(object);

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
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final ImageCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
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
    public void validate(final EngineeringCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("EngineeringCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);
        assertTrue("EngineeringCRS: illegal coordinate system type. Shall be one of affine, "
                + "Cartesian, cylindrical, linear, polar, spherical or user defined.",
                cs instanceof AffineCS      || // Include the CartesianCS case.
                cs instanceof CylindricalCS ||
                cs instanceof LinearCS      ||
                cs instanceof PolarCS       ||
                cs instanceof SphericalCS   ||
                cs instanceof UserDefinedCS);

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
     *   <li><cite>"depth"</cite> or <cite>"gravity-related height"</cite>.</li>
     * </ul>
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final VerticalCRS object) {
        if (object == null) {
            return;
        }
        validateReferenceSystem(object);
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
        validateReferenceSystem(object);
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
        validateReferenceSystem(object);
        final CoordinateSystem cs = object.getCoordinateSystem();
        mandatory("CompoundCRS: shall have a CoordinateSystem.", cs);
        container.validate(cs);

        final List<CoordinateReferenceSystem> components = object.getComponents();
        mandatory("CompoundCRS: shall have components.", components);
        if (components != null) {
            // If the above 'mandatory(…)' call accepted an empty list, we accept it too.
            assertTrue("CompoundCRS: shall have at least 2 components.", components.size() != 1);
            for (final CoordinateReferenceSystem component : components) {
                dispatch(component);
            }
        }
    }

    /**
     * Verifies that the given coordinate system uses the given standard names.
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
