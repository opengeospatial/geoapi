/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2015-2024 Open Geospatial Consortium, Inc.
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
import javax.measure.UnitConverter;
import javax.measure.IncommensurableException;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicDescription;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.extent.TemporalExtent;
import org.opengis.metadata.extent.VerticalExtent;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.ObjectDomain;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.temporal.Instant;
import org.opengis.temporal.Period;
import org.opengis.temporal.TemporalPrimitive;
import org.opengis.test.TestCase;
import org.opentest4j.AssertionFailedError;

import static java.lang.Double.isNaN;
import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Assertions.assertUnicodeIdentifierEquals;


/**
 * Base class of {@link CoordinateReferenceSystem} implementation tests.
 * This base class provides {@code verify(…)} methods that subclasses can override if they need to alter
 * the object verifications.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp abstract class ReferencingTestCase extends TestCase {
    /**
     * Creates a test case initialized to default values.
     */
    protected ReferencingTestCase() {
    }

    /**
     * Returns the given wrapper as a primitive value, or NaN if null.
     *
     * @param  value  the value to unwrap.
     * @return the unwrapped value.
     */
    private static double toPrimitive(final Double value) {
        return (value != null) ? value : Double.NaN;
    }

    /**
     * Converts the given date to Julian days.
     *
     * @param  time  the date to convert.
     * @return the Julian days for the given date.
     */
    private static double julian(final Date time) {
        return (time.getTime() - (-2440588 * (24*60*60*1000L) + (12*60*60*1000L))) / (24*60*60*1000.0);
    }

    /**
     * Infers a value from the extent as a {@link Date} object and computes the union with a lower or upper bounds.
     *
     * @param  bound  the current lower ({@code begin == true}) or upper ({@code begin == false}) bound.
     * @param  extent the extent from which to read a bound.
     * @param  begin  {@code true} for the start time, or {@code false} for the end time.
     * @return the new bound value.
     */
    private static Date union(final Date bound, final TemporalPrimitive extent, final boolean begin) {
        final Instant instant;
        if (extent instanceof Instant) {
            instant = (Instant) extent;
        } else if (extent instanceof Period) {
            instant = begin ? ((Period) extent).getBeginning() : ((Period) extent).getEnding();
        } else {
            return bound;
        }
        final Date t = instant.getDate();
        if (t != null && (bound == null || (begin ? t.before(bound) : t.after(bound)))) {
            return t;
        }
        return bound;
    }

    /**
     * Compares the name and identifier of the given {@code object} against the expected values.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>For {@link IdentifiedObject#getName()}:
     *     <ul>
     *       <li>Only the value returned by {@link Identifier#getCode()} is verified.
     *           The code space, authority and version are ignored.</li>
     *       <li>Only the characters that are valid for Unicode identifiers are compared (ignoring case), as documented in
     *           {@link org.opengis.test.Assertions#assertUnicodeIdentifierEquals Assertions.assertUnicodeIdentifierEquals(…)}.</li>
     *     </ul>
     *   </li>
     *   <li>For {@link IdentifiedObject#getIdentifiers()}:
     *     <ul>
     *       <li>Only the value returned by {@link Identifier#getCode()} is verified.
     *           The code space, authority and version are ignored.</li>
     *       <li>The identifiers collection can contain more identifiers than the expected one,
     *           and the expected identifier does not need to be first.</li>
     *       <li>The comparison is case-insensitive.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * If the given {@code object} is {@code null}, then this method does nothing.
     * Deciding if {@code null} objects are allowed or not is {@link org.opengis.test.Validator}'s job.
     *
     * @param object      the object to verify, or {@code null} if none.
     * @param name        the expected name (ignoring code space), or {@code null} if unrestricted.
     * @param identifier  the expected identifier code (ignoring code space), or {@code null} if unrestricted.
     */
    protected void verifyIdentification(final IdentifiedObject object, final String name, final String identifier) {
        if (object != null) {
            if (name != null) {
                assertUnicodeIdentifierEquals(name, Utilities.getName(object), true, "getName().getCode()");
            }
            if (identifier != null) {
                for (final Identifier id : object.getIdentifiers()) {
                    assertNotNull(id, "getName().getIdentifiers()");
                    if (identifier.equalsIgnoreCase(id.getCode())) {
                        return;
                    }
                }
                fail("getName().getIdentifiers(): element “" + identifier + "” not found.");
            }
        }
    }

    /**
     * Compares the name, axis lengths and inverse flattening factor of the given ellipsoid against the expected values.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>{@link Ellipsoid#getName()} allows for the same flexibilities as the one documented in
     *       {@link #verifyIdentification verifyIdentification(…)}.</li>
     *   <li>{@link Ellipsoid#getSemiMajorAxis()} does not need to use the unit of measurement given
     *       by the {@code axisUnit} argument. Unit conversion will be applied as needed.</li>
     * </ul>
     *
     * The tolerance thresholds are 0.5 unit of the last digits of the values found in the EPSG database:
     * <ul>
     *   <li>3 decimal digits for {@code semiMajor} values in metres.</li>
     *   <li>9 decimal digits for {@code inverseFlattening} values.</li>
     * </ul>
     *
     * If the given {@code ellipsoid} is {@code null}, then this method does nothing.
     * Deciding if {@code null} datum are allowed or not is {@link org.opengis.test.Validator}'s job.
     *
     * @param ellipsoid          the ellipsoid to verify, or {@code null} if none.
     * @param name               the expected name (ignoring code space), or {@code null} if unrestricted.
     * @param semiMajor          the expected semi-major axis length, in units given by the {@code axisUnit} argument.
     * @param inverseFlattening  the expected inverse flattening factor.
     * @param axisUnit           the unit of the {@code semiMajor} argument (not necessarily the actual unit of the ellipsoid).
     *
     * @see GeodeticDatum#getEllipsoid()
     */
    protected void verifyFlattenedSphere(final Ellipsoid ellipsoid, final String name,
            final double semiMajor, final double inverseFlattening, final Unit<Length> axisUnit)
    {
        if (ellipsoid != null) {
            if (name != null) {
                assertUnicodeIdentifierEquals(name, Utilities.getName(ellipsoid), true,
                        "Ellipsoid.getName().getCode()");
            }
            final Unit<Length> actualUnit = ellipsoid.getAxisUnit();
            assertNotNull(actualUnit, "Ellipsoid.getAxisUnit()");
            assertEquals(semiMajor,
                    actualUnit.getConverterTo(axisUnit).convert(ellipsoid.getSemiMajorAxis()),
                    units.metre().getConverterTo(axisUnit).convert(5E-4),
                    "Ellipsoid.getSemiMajorAxis()");
            assertEquals(inverseFlattening, ellipsoid.getInverseFlattening(), 5E-10,
                    "Ellipsoid.getInverseFlattening()");
        }
    }

    /**
     * Compares the name and Greenwich longitude of the given prime meridian against the expected values.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>{@link PrimeMeridian#getName()} allows for the same flexibilities as the one documented in
     *       {@link #verifyIdentification verifyIdentification(…)}.</li>
     *   <li>{@link PrimeMeridian#getGreenwichLongitude()} does not need to use the unit of measurement given
     *       by the {@code angularUnit} argument. Unit conversion will be applied as needed.</li>
     * </ul>
     *
     * The tolerance threshold is 0.5 unit of the last digit of the values found in the EPSG database:
     * <ul>
     *   <li>7 decimal digits for {@code greenwichLongitude} values in degrees.</li>
     * </ul>
     *
     * If the given {@code primeMeridian} is {@code null}, then this method does nothing.
     * Deciding if {@code null} prime meridians are allowed or not is {@link org.opengis.test.Validator}'s job.
     *
     * @param primeMeridian       the prime meridian to verify, or {@code null} if none.
     * @param name                the expected name (ignoring code space), or {@code null} if unrestricted.
     * @param greenwichLongitude  the expected Greenwich longitude, in units given by the {@code angularUnit} argument.
     * @param angularUnit         the unit of the {@code greenwichLongitude} argument (not necessarily the actual unit of the prime meridian).
     *
     * @see GeodeticDatum#getPrimeMeridian()
     */
    protected void verifyPrimeMeridian(final PrimeMeridian primeMeridian, final String name,
            final double greenwichLongitude, final Unit<Angle> angularUnit)
    {
        if (primeMeridian != null) {
            if (name != null) {
                assertUnicodeIdentifierEquals(name, Utilities.getName(primeMeridian), true,
                        "PrimeMeridian.getName().getCode()");
            }
            final Unit<Angle> actualUnit = primeMeridian.getAngularUnit();
            assertNotNull(actualUnit, "PrimeMeridian.getAngularUnit()");
            assertEquals(greenwichLongitude,
                    actualUnit.getConverterTo(angularUnit).convert(primeMeridian.getGreenwichLongitude()),
                    units.degree().getConverterTo(angularUnit).convert(5E-8),
                    "PrimeMeridian.getGreenwichLongitude()");
        }
    }

    /**
     * Compares the type, axis units and directions of the given coordinate system against the expected values.
     * This method does not verify the coordinate system name because it is usually not significant.
     * This method does not verify axis names neither because the names specified by ISO 19111 and ISO 19162 differ.
     *
     * <p>If the given {@code cs} is {@code null}, then this method does nothing.
     * Deciding if {@code null} coordinate systems are allowed or not is {@link org.opengis.test.Validator}'s job.</p>
     *
     * @param  cs          the coordinate system to verify, or {@code null} if none.
     * @param  type        the expected coordinate system type.
     * @param  directions  the expected axis directions. The length of this array determines the expected {@code cs} dimension.
     * @param  axisUnits   the expected axis units. If the array length is less than the {@code cs} dimension,
     *                     then the last unit is repeated for all remaining dimensions.
     *                     If the array length is greater, than extra units are ignored.
     *
     * @see CoordinateReferenceSystem#getCoordinateSystem()
     */
    protected void verifyCoordinateSystem(final CoordinateSystem cs, final Class<? extends CoordinateSystem> type,
            final AxisDirection[] directions, final Unit<?>... axisUnits)
    {
        if (cs != null) {
            assertEquals(directions.length, cs.getDimension(), "CoordinateSystem.getDimension()");
            for (int i=0; i<directions.length; i++) {
                final CoordinateSystemAxis axis = cs.getAxis(i);
                assertNotNull(axis, "CoordinateSystem.getAxis(*)");
                assertEquals(directions[i], axis.getDirection(), "CoordinateSystem.getAxis(*).getDirection()");
                assertEquals(axisUnits[Math.min(i, axisUnits.length-1)], axis.getUnit(), "CoordinateSystem.getAxis(*).getUnit()");
            }
        }
    }

    /**
     * Compares an operation parameter against the expected value.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>The parameter does not need to use the unit of measurement given by the {@code unit} argument.
     *       Unit conversion should be applied as needed by the {@link ParameterValue#doubleValue(Unit)} method.</li>
     * </ul>
     *
     * If the given {@code group} is {@code null}, then this method does nothing.
     * Deciding if {@code null} parameters are allowed or not is {@link org.opengis.test.Validator}'s job.
     *
     * @param group  the parameter group containing the parameter to test.
     * @param name   the name of the parameter to test.
     * @param value  the expected parameter value when expressed in units given by the {@code unit} argument.
     * @param unit   the units of measurement of the {@code value} argument
     *               (not necessarily the unit actually used by the implementation).
     */
    protected void verifyParameter(final ParameterValueGroup group, final String name, final double value, final Unit<?> unit) {
        if (group != null) {
            final ParameterValue<?> param = group.parameter(name);
            assertNotNull(param, name);
            assertEquals(param.doubleValue(unit), value, StrictMath.abs(value * 1E-10), name);
        }
    }

    /**
     * Compares the geographic description and bounding box of the given extent against the expected values.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>For {@link GeographicDescription} elements:
     *     <ul>
     *       <li>Descriptions are considered optional. If the given {@code extent} does not contain any
     *           {@code GeographicDescription} element, then the given {@code description} argument is ignored.</li>
     *       <li>If the given {@code extent} contains more than one {@code GeographicDescription} element, then only
     *           one of them (not necessarily the first one) needs to have the given {@code description} value.
     *           Other elements are ignored.</li>
     *     </ul>
     *   </li>
     *   <li>For {@link GeographicBoundingBox} elements:
     *     <ul>
     *       <li>Bounding boxes are considered optional. If the given {@code extent} does not contain any
     *           {@code GeographicBoundingBox} element, then all given bound arguments are ignored.</li>
     *       <li>If the given {@code extent} contains more than one {@code GeographicBoundingBox} element,
     *           then the union of them is compared against the given bound arguments.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * The tolerance threshold is 0.005° since geographic bounding box are only approximate information.
     *
     * <p>If the given {@code extent} is {@code null}, then this method does nothing.
     * Deciding if {@code null} extents are allowed or not is {@link org.opengis.test.Validator}'s job.</p>
     *
     * @param extent              the extent to verify, or {@code null} if none.
     * @param description         the expected area, or {@code null} if unrestricted.
     * @param southBoundLatitude  the expected minimum latitude,  or NaN if unrestricted.
     * @param westBoundLongitude  the expected minimum longitude, or NaN if unrestricted.
     * @param northBoundLatitude  the expected maximum latitude,  or NaN if unrestricted.
     * @param eastBoundLongitude  the expected maximum longitude, or NaN if unrestricted.
     *
     * @see ObjectDomain#getDomainOfValidity()
     */
    protected void verifyGeographicExtent(final Extent extent, final String description,
            final double southBoundLatitude, final double westBoundLongitude,
            final double northBoundLatitude, final double eastBoundLongitude)
    {
        if (extent != null) {
            double ymin = Double.POSITIVE_INFINITY;
            double xmin = Double.POSITIVE_INFINITY;
            double ymax = Double.NEGATIVE_INFINITY;
            double xmax = Double.NEGATIVE_INFINITY;
            String unknownArea = null;
            for (final GeographicExtent e : extent.getGeographicElements()) {
                if (e instanceof GeographicBoundingBox) {
                    final GeographicBoundingBox bbox = (GeographicBoundingBox) e;
                    double t;
                    if ((t = bbox.getSouthBoundLatitude()) < ymin) ymin = t;
                    if ((t = bbox.getWestBoundLongitude()) < xmin) xmin = t;
                    if ((t = bbox.getNorthBoundLatitude()) > ymax) ymax = t;
                    if ((t = bbox.getEastBoundLongitude()) > xmax) xmax = t;
                }
                /*
                 * Description: optional, but if present we allow any number of identifiers
                 * provided that at least one contain the expected string.
                 */
                if (description != null && e instanceof GeographicDescription) {
                    final String area = ((GeographicDescription) e).getGeographicIdentifier().getCode();
                    if (description.equals(area)) {
                        unknownArea = null;
                        break;
                    }
                    if (unknownArea == null) {
                        unknownArea = area;         // For reporting an error message if we do not find the expected area.
                    }
                }
            }
            if (unknownArea != null) {
                assertEquals(description, unknownArea, "GeographicDescription");
            }
            /*
             * WKT 2 specification said that BBOX precision should be about 0.01°.
             */
            if (!isNaN(southBoundLatitude) && ymin != Double.POSITIVE_INFINITY) assertEquals(southBoundLatitude, ymin, 0.005, "getSouthBoundLatitude()");
            if (!isNaN(westBoundLongitude) && xmin != Double.POSITIVE_INFINITY) assertEquals(westBoundLongitude, xmin, 0.005, "getWestBoundLongitude()");
            if (!isNaN(northBoundLatitude) && ymax != Double.NEGATIVE_INFINITY) assertEquals(northBoundLatitude, ymax, 0.005, "getNorthBoundLatitude()");
            if (!isNaN(eastBoundLongitude) && xmax != Double.NEGATIVE_INFINITY) assertEquals(eastBoundLongitude, xmax, 0.005, "getEastBoundLongitude()");
        }
    }

    /**
     * Compares the vertical elements of the given extent against the expected values.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>Vertical extents are considered optional. If the given {@code extent} does not contain any
     *       {@code VerticalExtent} element, then this method does nothing.</li>
     *   <li>{@link VerticalExtent#getMinimumValue()} and {@link VerticalExtent#getMaximumValue() getMaximumValue()}
     *       do not need to use the unit of measurement given by the {@code unit} argument. Unit conversions will be
     *       applied as needed if {@link VerticalExtent#getVerticalCRS()} returns a non-null value.</li>
     *   <li>If the given {@code extent} contains more than one {@code VerticalExtent} element,
     *       then the union of them is compared against the given bound arguments.</li>
     * </ul>
     *
     * <p>If the given {@code extent} is {@code null}, then this method does nothing.
     * Deciding if {@code null} extents are allowed or not is {@link org.opengis.test.Validator}'s job.</p>
     *
     * @param extent        the extent to verify, or {@code null} if none.
     * @param minimumValue  the expected minimal vertical value, or NaN if unrestricted.
     * @param maximumValue  the expected maximal vertical value, or NaN if unrestricted.
     * @param tolerance     the tolerance threshold to use for comparison.
     * @param unit          the unit of {@code minimumValue}, {@code maximumValue} and {@code tolerance} arguments,
     *                      or {@code null} for skipping the unit conversion.
     *
     * @see ObjectDomain#getDomainOfValidity()
     */
    protected void verifyVerticalExtent(final Extent extent,
            final double minimumValue, final double maximumValue, final double tolerance, final Unit<?> unit)
    {
        if (extent != null) {
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;
            for (final VerticalExtent e : extent.getVerticalElements()) {
                double minValue = toPrimitive(e.getMinimumValue());
                double maxValue = toPrimitive(e.getMaximumValue());
                if (unit != null) {
                    final VerticalCRS crs = e.getVerticalCRS();
                    if (crs != null) {
                        final VerticalCS cs = crs.getCoordinateSystem();
                        if (cs != null) {
                            assertEquals(1, cs.getDimension(),
                                    "VerticalExtent.getVerticalCRS().getCoordinateSystem().getDimension()");
                            final CoordinateSystemAxis axis = cs.getAxis(0);
                            if (axis != null) {
                                final Unit<?> u = axis.getUnit();
                                if (u != null) {
                                    final UnitConverter c;
                                    try {
                                        c = u.getConverterToAny(unit);
                                    } catch (IncommensurableException ex) {
                                        throw new AssertionFailedError("Expected VerticalExtent in units of “"
                                                + unit + "” but got units of “" + u + "”.", ex);
                                    }
                                    minValue = c.convert(minValue);
                                    maxValue = c.convert(maxValue);
                                }
                            }
                        }
                    }
                }
                if (minValue < min) min = minValue;
                if (maxValue > max) max = maxValue;
            }
            if (!isNaN(minimumValue) && min != Double.POSITIVE_INFINITY) assertEquals(minimumValue, min, tolerance, "VerticalExtent.getMinimumValue()");
            if (!isNaN(maximumValue) && max != Double.NEGATIVE_INFINITY) assertEquals(maximumValue, max, tolerance, "VerticalExtent.getMaximumValue()");
        }
    }

    /**
     * Compares the temporal elements of the given extent against the expected values.
     * This method allows for some flexibilities:
     *
     * <ul>
     *   <li>Temporal extents are considered optional. If the given {@code extent} does not contain any
     *       {@code TemporalExtent} element, then this method does nothing.</li>
     *   <li>If the given {@code extent} contains more than one {@code TemporalExtent} element,
     *       then the union of them is compared against the given bound arguments.</li>
     * </ul>
     *
     * <p>If the given {@code extent} is {@code null}, then this method does nothing.
     * Deciding if {@code null} extents are allowed or not is {@link org.opengis.test.Validator}'s job.</p>
     *
     * @param extent     the extent to verify, or {@code null} if none.
     * @param startTime  the expected start time, or {@code null} if unrestricted.
     * @param endTime    the expected end time, or {@code null} if unrestricted.
     * @param tolerance  the tolerance threshold to use for comparison, in unit of days.
     *
     * @see ObjectDomain#getDomainOfValidity()
     */
    protected void verifyTimeExtent(final Extent extent, final Date startTime, final Date endTime, final double tolerance) {
        if (extent != null) {
            Date min = null;
            Date max = null;
            for (final TemporalExtent e : extent.getTemporalElements()) {
                final TemporalPrimitive p = e.getExtent();
                min = union(min, p, true);
                max = union(max, p, false);
            }
            if (startTime != null && min != null) {
                assertEquals(julian(startTime), julian(min), tolerance, "TemporalExtent start time (julian days)");
            }
            if (endTime != null && max != null) {
                assertEquals(julian(endTime), julian(max), tolerance, "TemporalExtent end time (julian days)");
            }
        }
    }
}
