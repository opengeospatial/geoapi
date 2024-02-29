/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The direction of positive increase in the coordinate value for a coordinate system axis.
 * This direction is exact in some cases, and is approximate in other cases.
 *
 * <p>Some coordinate systems use non-standard orientations. For example, the first axis in
 * South African grids usually points West, instead of East. This information is obviously
 * relevant for algorithms converting South African grid coordinates into Lat/Long.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CoordinateSystemAxis#getDirection()
 */
@UML(identifier="CS_AxisDirection", specification=ISO_19111, version=2007)
public final class AxisDirection extends CodeList<AxisDirection> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4405275475770755714L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<AxisDirection> VALUES = new ArrayList<>(32);

    /**
     * Unknown or unspecified axis orientation.
     *
     * @category Other
     */
    @UML(identifier="CS_AxisOrientationEnum.CS_AO_Other", specification=OGC_01009)
    public static final AxisDirection OTHER = new AxisDirection("OTHER");

    /**
     * Axis positive direction is north. In a geographic or projected CRS,
     * north is defined through the geodetic datum. In an engineering CRS,
     * north may be defined with respect to an engineering object rather
     * than a geographical direction.
     *
     * @category Rose
     */
    @UML(identifier="north", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection NORTH = new AxisDirection("NORTH");

    /**
     * Axis positive direction is approximately north-north-east.
     *
     * @category Rose
     */
    @UML(identifier="northNorthEast", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection NORTH_NORTH_EAST = new AxisDirection("NORTH_NORTH_EAST");

    /**
     * Axis positive direction is approximately north-east.
     *
     * @category Rose
     */
    @UML(identifier="northEast", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection NORTH_EAST = new AxisDirection("NORTH_EAST");

    /**
     * Axis positive direction is approximately east-north-east.
     *
     * @category Rose
     */
    @UML(identifier="eastNorthEast", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection EAST_NORTH_EAST = new AxisDirection("EAST_NORTH_EAST");

    /**
     * Axis positive direction is π/2 radians clockwise from north.
     * This is usually used for Grid X coordinates and Longitude.
     *
     * @category Rose
     */
    @UML(identifier="east", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection EAST = new AxisDirection("EAST");

    /**
     * Axis positive direction is approximately east-south-east.
     *
     * @category Rose
     */
    @UML(identifier="eastSouthEast", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection EAST_SOUTH_EAST = new AxisDirection("EAST_SOUTH_EAST");

    /**
     * Axis positive direction is approximately south-east.
     *
     * @category Rose
     */
    @UML(identifier="southEast", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection SOUTH_EAST = new AxisDirection("SOUTH_EAST");

    /**
     * Axis positive direction is approximately south-south-east.
     *
     * @category Rose
     */
    @UML(identifier="southSouthEast", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection SOUTH_SOUTH_EAST = new AxisDirection("SOUTH_SOUTH_EAST");

    /**
     * Axis positive direction is π radians clockwise from north.
     *
     * @category Rose
     */
    @UML(identifier="south", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection SOUTH = new AxisDirection("SOUTH");

    /**
     * Axis positive direction is approximately south-south-west.
     *
     * @category Rose
     */
    @UML(identifier="southSouthWest", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection SOUTH_SOUTH_WEST = new AxisDirection("SOUTH_SOUTH_WEST");

    /**
     * Axis positive direction is approximately south-west.
     *
     * @category Rose
     */
    @UML(identifier="southWest", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection SOUTH_WEST = new AxisDirection("SOUTH_WEST");

    /**
     * Axis positive direction is approximately west-south-west.
     *
     * @category Rose
     */
    @UML(identifier="westSouthWest", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection WEST_SOUTH_WEST = new AxisDirection("WEST_SOUTH_WEST");

    /**
     * Axis positive direction is 3π/2 radians clockwise from north.
     * This is usually used for Grid X coordinates and Longitude.
     *
     * @category Rose
     */
    @UML(identifier="west", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection WEST = new AxisDirection("WEST");

    /**
     * Axis positive direction is approximately west-north-west.
     *
     * @category Rose
     */
    @UML(identifier="westNorthWest", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection WEST_NORTH_WEST = new AxisDirection("WEST_NORTH_WEST");

    /**
     * Axis positive direction is approximately north-west.
     *
     * @category Rose
     */
    @UML(identifier="northWest", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection NORTH_WEST = new AxisDirection("NORTH_WEST");

    /**
     * Axis positive direction is approximately north-north-west.
     *
     * @category Rose
     */
    @UML(identifier="northNorthWest", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection NORTH_NORTH_WEST = new AxisDirection("NORTH_NORTH_WEST");

    /**
     * Axis positive direction is up relative to gravity.
     * This is used for {@linkplain VerticalCS vertical} coordinate systems.
     *
     * @category Vertical
     */
    @UML(identifier="up", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection UP = new AxisDirection("UP");

    /**
     * Axis positive direction is down relative to gravity.
     * This is used for {@linkplain VerticalCS vertical} coordinate systems.
     *
     * @category Vertical
     */
    @UML(identifier="down", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection DOWN = new AxisDirection("DOWN");

    /**
     * Axis positive direction is in the equatorial plane from the centre of the
     * modelled earth towards the intersection of the equator with the prime meridian.
     *
     * @category Geocentric
     */
    @UML(identifier="geocentricX", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection GEOCENTRIC_X = new AxisDirection("GEOCENTRIC_X");

    /**
     * Axis positive direction is in the equatorial plane from the centre of the
     * modelled earth towards the intersection of the equator and the meridian π/2
     * radians eastwards from the prime meridian.
     *
     * @category Geocentric
     */
    @UML(identifier="geocentricY", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection GEOCENTRIC_Y = new AxisDirection("GEOCENTRIC_Y");

    /**
     * Axis positive direction is from the centre of the modelled earth parallel to
     * its rotation axis and towards its north pole.
     *
     * @category Geocentric
     */
    @UML(identifier="geocentricZ", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection GEOCENTRIC_Z = new AxisDirection("GEOCENTRIC_Z");

    /**
     * Axis positive direction is towards the future.
     * This is used for {@linkplain TimeCS time} coordinate systems.
     *
     * @category Temporal
     */
    @UML(identifier="future", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection FUTURE = new AxisDirection("FUTURE");

    /**
     * Axis positive direction is towards the past.
     * This is used for {@linkplain TimeCS time} coordinate systems.
     *
     * @category Temporal
     */
    @UML(identifier="past", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection PAST = new AxisDirection("PAST");

    /**
     * Axis positive direction is towards higher pixel column.
     *
     * @category Image
     */
    @UML(identifier="columnPositive", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection COLUMN_POSITIVE = new AxisDirection("COLUMN_POSITIVE");

    /**
     * Axis positive direction is towards lower pixel column.
     *
     * @category Image
     */
    @UML(identifier="columnNegative", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection COLUMN_NEGATIVE = new AxisDirection("COLUMN_NEGATIVE");

    /**
     * Axis positive direction is towards higher pixel row.
     *
     * @category Image
     */
    @UML(identifier="rowPositive", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection ROW_POSITIVE = new AxisDirection("ROW_POSITIVE");

    /**
     * Axis positive direction is towards lower pixel row.
     *
     * @category Image
     */
    @UML(identifier="rowNegative", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection ROW_NEGATIVE = new AxisDirection("ROW_NEGATIVE");

    /**
     * Axis positive direction is right in display.
     *
     * @category Display
     */
    @UML(identifier="displayRight", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection DISPLAY_RIGHT = new AxisDirection("DISPLAY_RIGHT");

    /**
     * Axis positive direction is left in display.
     *
     * @category Display
     */
    @UML(identifier="displayLeft", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection DISPLAY_LEFT = new AxisDirection("DISPLAY_LEFT");

    /**
     * Axis positive direction is towards top of approximately vertical display surface.
     *
     * @category Display
     */
    @UML(identifier="displayUp", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection DISPLAY_UP = new AxisDirection("DISPLAY_UP");

    /**
     * Axis positive direction is towards bottom of approximately vertical display surface.
     *
     * @category Display
     */
    @UML(identifier="displayDown", obligation=CONDITIONAL, specification=ISO_19111)
    public static final AxisDirection DISPLAY_DOWN = new AxisDirection("DISPLAY_DOWN");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private AxisDirection(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code AxisDirection}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static AxisDirection[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(AxisDirection[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public AxisDirection[] family() {
        return values();
    }

    /**
     * Returns the axis direction that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static AxisDirection valueOf(String code) {
        return valueOf(AxisDirection.class, code);
    }
}
