/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.util.Map;
import javax.measure.Unit;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Definition of a coordinate system axis.
 *
 * <h2>Axis name</h2>
 * Usage of coordinate system axis names is constrained by geodetic custom in a number of cases,
 * depending mainly on the coordinate reference system type. These constraints are shown in the
 * table below. This constraint works in two directions; for example the names <q>geodetic
 * latitude</q> and <q>geodetic longitude</q> shall be used to designate the coordinate
 * axis names associated with a geographic coordinate reference system. Conversely, these names
 * shall not be used in any other context.
 *
 * <table class="ogc">
 * <caption>Context of coordinate system axis names usage</caption>
 * <tr><th>CS</th><th>CRS</th><th>Permitted coordinate system axis names</th></tr>
 * <tr><td>Cartesian</td><td>Geocentric</td>
 *     <td><i>Geocentric X</i>,
 *         <i>Geocentric Y</i>,
 *         <i>Geocentric Z</i></td></tr>
 * <tr><td>Spherical</td><td>Geocentric</td>
 *     <td><i>Spherical Latitude</i>,
 *         <i>Spherical Longitude</i>,
 *         <i>Geocentric Radius</i></td></tr>
 * <tr><td>Ellipsoidal</td><td>Geographic</td>
 *     <td><i>Geodetic Latitude</i>,
 *         <i>Geodetic Longitude</i>,
 *         <i>Ellipsoidal height</i> (if 3D)</td></tr>
 * <tr><td>Vertical</td><td>Vertical</td>
 *     <td><i>Gravity-related height</i> or <i>Depth</i></td></tr>
 * <tr><td>Cartesian</td><td>Projected</td>
 *     <td><i>Easting</i> or <i>Westing</i>,
 *         <i>Northing</i> or <i>Southing</i></td></tr>
 * </table>
 *
 * Image and engineering coordinate reference systems may make use of names specific to the
 * local context or custom and are therefore not included as constraints in the above list.
 *
 * <h2>Axis direction</h2>
 * The {@linkplain #getDirection() direction} of the coordinate axes is often only approximate;
 * two geographic coordinate reference systems will make use of the same ellipsoidal coordinate
 * system. These coordinate systems are associated with the earth through two different geodetic
 * datums, which may lead to the two systems being slightly rotated with respect to each other.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CoordinateSystem
 * @see CSAuthorityFactory#createCoordinateSystemAxis(String)
 * @see CSFactory#createCoordinateSystemAxis(Map, String, AxisDirection, Unit)
 */
@UML(identifier="CS_CoordinateSystemAxis", specification=ISO_19111, version=2007)
public interface CoordinateSystemAxis extends IdentifiedObject {
    /**
     * Returns the abbreviation used for this coordinate system axes.
     * This abbreviation is also used to identify the coordinates in coordinate tuple.
     * Examples are “<var>X</var>” and “<var>Y</var>”.
     *
     * @return the coordinate system axis abbreviation.
     */
    @UML(identifier="axisAbbrev", obligation=MANDATORY, specification=ISO_19111)
    String getAbbreviation();

    /**
     * Returns the direction of this coordinate system axis. In the case of Cartesian projected
     * coordinates, this is the direction of this coordinate system axis locally.
     * Examples:
     * {@linkplain AxisDirection#NORTH north} or {@linkplain AxisDirection#SOUTH south},
     * {@linkplain AxisDirection#EAST  east}  or {@linkplain AxisDirection#WEST  west},
     * {@linkplain AxisDirection#UP    up}    or {@linkplain AxisDirection#DOWN  down}.
     *
     * <p>Within any set of coordinate system axes, only one of each pair of terms can be used.
     * For earth-fixed coordinate reference systems, this direction is often approximate
     * and intended to provide a human interpretable meaning to the axis.
     * When a geodetic datum is used, the precise directions of the axes may therefore
     * vary slightly from this approximate direction.</p>
     *
     * <p>Note that an {@link org.opengis.referencing.crs.EngineeringCRS} often requires
     * specific descriptions of the directions of its coordinate system axes.</p>
     *
     * @return the coordinate system axis direction.
     */
    @UML(identifier="axisDirection", obligation=MANDATORY, specification=ISO_19111)
    AxisDirection getDirection();

    /**
     * Returns the unit of measure used for this coordinate system axis.
     * The value of a coordinate in a coordinate tuple shall be recorded using this unit of measure,
     * whenever those coordinates use a coordinate reference system that uses a coordinate system
     * that uses this axis.
     *
     * @return the coordinate system axis unit.
     */
    @UML(identifier="axisUnitID", obligation=MANDATORY, specification=ISO_19111)
    Unit<?> getUnit();

    /**
     * Returns the minimum value normally allowed for this axis,
     * in the {@linkplain #getUnit() unit of measure for the axis}.
     * If there is no minimum value, then this method returns
     * {@linkplain Double#NEGATIVE_INFINITY negative infinity}.
     *
     * @return the minimum value, or {@link Double#NEGATIVE_INFINITY} if none.
     */
    @UML(identifier="minimumValue", obligation=OPTIONAL, specification=ISO_19111)
    default double getMinimumValue() {
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * Returns the maximum value normally allowed for this axis,
     * in the {@linkplain #getUnit() unit of measure for the axis}.
     * If there is no maximum value, then this method returns
     * {@linkplain Double#POSITIVE_INFINITY positive infinity}.
     *
     * @return the maximum value, or {@link Double#POSITIVE_INFINITY} if none.
     */
    @UML(identifier="maximumValue", obligation=OPTIONAL, specification=ISO_19111)
    default double getMaximumValue() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the meaning of axis value range specified by the {@linkplain #getMinimumValue()
     * minimum} and {@linkplain #getMaximumValue() maximum} values. This element shall be omitted
     * when both minimum and maximum values are omitted. It may be included when minimum and/or
     * maximum values are included. If this element is omitted when minimum or maximum values are
     * included, the meaning is unspecified.
     *
     * @return the range meaning, or {@code null} in none.
     *
     * @see RangeMeaning#EXACT
     * @see RangeMeaning#WRAPAROUND
     */
    @UML(identifier="rangeMeaning", obligation=CONDITIONAL, specification=ISO_19111)
    default RangeMeaning getRangeMeaning() {
        return null;
    }
}
