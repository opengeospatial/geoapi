/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
 * <h3>Axis name</h3>
 * Usage of coordinate system axis names is constrained by geodetic custom in a number of cases,
 * depending mainly on the coordinate reference system type. These constraints are shown in the
 * table below. This constraint works in two directions; for example the names <cite>"geodetic
 * latitude"</cite> and <cite>"geodetic longitude"</cite> shall be used to designate the coordinate
 * axis names associated with a geographic coordinate reference system. Conversely, these names
 * shall not be used in any other context.
 *
 * <table class="ogc">
 * <caption>Context of coordinate system axis names usage</caption>
 * <tr><th>CS</th><th>CRS</th><th>Permitted coordinate system axis names</th></tr>
 * <tr><td>Cartesian</td><td>Geocentric</td>
 *     <td><cite>Geocentric X</cite>,
 *         <cite>Geocentric Y</cite>,
 *         <cite>Geocentric Z</cite></td></tr>
 * <tr><td>Spherical</td><td>Geocentric</td>
 *     <td><cite>Spherical Latitude</cite>,
 *         <cite>Spherical Longitude</cite>,
 *         <cite>Geocentric Radius</cite></td></tr>
 * <tr><td>Ellipsoidal</td><td>Geographic</td>
 *     <td><cite>Geodetic Latitude</cite>,
 *         <cite>Geodetic Longitude</cite>,
 *         <cite>Ellipsoidal height</cite> (if 3D)</td></tr>
 * <tr><td>Vertical</td><td>Vertical</td>
 *     <td><cite>Gravity-related height</cite> or <cite>Depth</cite></td></tr>
 * <tr><td>Cartesian</td><td>Projected</td>
 *     <td><cite>Easting</cite> or <cite>Westing</cite>,
 *         <cite>Northing</cite> or <cite>Southing</cite></td></tr>
 * </table>
 *
 * Image and engineering coordinate reference systems may make use of names specific to the
 * local context or custom and are therefore not included as constraints in the above list.
 *
 * <h3>Axis direction</h3>
 * The {@linkplain #getDirection() direction} of the coordinate axes is often only approximate;
 * two geographic coordinate reference systems will make use of the same ellipsoidal coordinate
 * system. These coordinate systems are associated with the earth through two different geodetic
 * datums, which may lead to the two systems being slightly rotated with respect to each other.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   1.0
 *
 * @see CoordinateSystem
 * @see CSAuthorityFactory#createCoordinateSystemAxis(String)
 * @see CSFactory#createCoordinateSystemAxis(Map, String, AxisDirection, Unit)
 */
@UML(identifier="CS_CoordinateSystemAxis", specification=ISO_19111)
public interface CoordinateSystemAxis extends IdentifiedObject {
    /**
     * Returns the abbreviation used for this coordinate system axes.
     * This abbreviation is also used to identify the ordinates in coordinate tuple.
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
     * The value of a ordinate in a coordinate tuple shall be recorded using this unit of measure,
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
    double getMinimumValue();

    /**
     * Returns the maximum value normally allowed for this axis,
     * in the {@linkplain #getUnit() unit of measure for the axis}.
     * If there is no maximum value, then this method returns
     * {@linkplain Double#POSITIVE_INFINITY positive infinity}.
     *
     * @return the maximum value, or {@link Double#POSITIVE_INFINITY} if none.
     */
    @UML(identifier="maximumValue", obligation=OPTIONAL, specification=ISO_19111)
    double getMaximumValue();

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
    RangeMeaning getRangeMeaning();
}
