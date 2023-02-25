/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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

import javax.measure.Unit;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Definition of a coordinate system axis.
 * See <A HREF="package-summary.html#AxisNames">axis name constraints</A>.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   1.0
 *
 * @see CoordinateSystem
 * @see Unit
 *
 * @navassoc 1 - - AxisDirection
 * @navassoc 1 - - RangeMeaning
 * @navassoc 1 - - Unit
 */
@UML(identifier="CS_CoordinateSystemAxis", specification=ISO_19111)
public interface CoordinateSystemAxis extends IdentifiedObject {
    /**
     * The abbreviation used for this coordinate system axes. This abbreviation is also
     * used to identify the ordinates in coordinate tuple. Examples are "<var>X</var>"
     * and "<var>Y</var>".
     *
     * @return The coordinate system axis abbreviation.
     */
    @UML(identifier="axisAbbrev", obligation=MANDATORY, specification=ISO_19111)
    String getAbbreviation();

    /**
     * Direction of this coordinate system axis. In the case of Cartesian projected
     * coordinates, this is the direction of this coordinate system axis locally.
     * Examples:
     * {@linkplain AxisDirection#NORTH north} or {@linkplain AxisDirection#SOUTH south},
     * {@linkplain AxisDirection#EAST  east}  or {@linkplain AxisDirection#WEST  west},
     * {@linkplain AxisDirection#UP    up}    or {@linkplain AxisDirection#DOWN  down}.
     * <p>
     * Within any set of coordinate system axes, only one of each pair of terms
     * can be used. For earth-fixed coordinate reference systems, this direction is often
     * approximate and intended to provide a human interpretable meaning to the axis. When a
     * geodetic datum is used, the precise directions of the axes may therefore vary slightly
     * from this approximate direction.
     * <p>
     * Note that an {@link org.opengis.referencing.crs.EngineeringCRS} often requires
     * specific descriptions of the directions of its coordinate system axes.
     *
     * @return The coordinate system axis direction.
     */
    @UML(identifier="axisDirection", obligation=MANDATORY, specification=ISO_19111)
    AxisDirection getDirection();

    /**
     * Returns the minimum value normally allowed for this axis, in the
     * {@linkplain #getUnit unit of measure for the axis}. If there is no minimum value, then
     * this method returns {@linkplain Double#NEGATIVE_INFINITY negative infinity}.
     *
     * @return The minimum value, or {@link Double#NEGATIVE_INFINITY} if none.
     */
    @UML(identifier="minimumValue", obligation=OPTIONAL, specification=ISO_19111)
    double getMinimumValue();

    /**
     * Returns the maximum value normally allowed for this axis, in the
     * {@linkplain #getUnit unit of measure for the axis}. If there is no maximum value, then
     * this method returns {@linkplain Double#POSITIVE_INFINITY positive infinity}.
     *
     * @return The maximum value, or {@link Double#POSITIVE_INFINITY} if none.
     */
    @UML(identifier="maximumValue", obligation=OPTIONAL, specification=ISO_19111)
    double getMaximumValue();

    /**
     * Returns the meaning of axis value range specified by the {@linkplain #getMinimumValue
     * minimum} and {@linkplain #getMaximumValue maximum} values. This element shall be omitted
     * when both minimum and maximum values are omitted. It may be included when minimum and/or
     * maximum values are included. If this element is omitted when minimum or maximum values are
     * included, the meaning is unspecified.
     *
     * @return The range meaning, or {@code null} in none.
     */
    @UML(identifier="rangeMeaning", obligation=CONDITIONAL, specification=ISO_19111)
    RangeMeaning getRangeMeaning();

    /**
     * The unit of measure used for this coordinate system axis. The value of a
     * coordinate in a coordinate tuple shall be recorded using this unit of measure,
     * whenever those coordinates use a coordinate reference system that uses a
     * coordinate system that uses this axis.
     *
     * @return  The coordinate system axis unit.
     */
    @UML(identifier="axisUnitID", obligation=MANDATORY, specification=ISO_19111)
    Unit<?> getUnit();
}
