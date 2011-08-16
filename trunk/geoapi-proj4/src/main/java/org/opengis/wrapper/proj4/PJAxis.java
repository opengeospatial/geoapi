/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import javax.measure.unit.Unit;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.RangeMeaning;


/**
 * An axis of a {@link PJCRS} object.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJAxis extends PJObject implements CoordinateSystemAxis {
    /**
     * The direction, as a 'e', 'w', 'n', 's', 'u' or 'd' letter.
     */
    private final char direction;

    /**
     * Creates a new axis.
     */
    PJAxis(final char direction) {
        super(null);
        this.direction = direction;
    }

    @Override
    public String getAbbreviation() {
        return null;
    }

    @Override
    public AxisDirection getDirection() {
        final AxisDirection dir;
        switch (direction) {
            case 'e': dir = AxisDirection.EAST;  break;
            case 'w': dir = AxisDirection.WEST;  break;
            case 'n': dir = AxisDirection.NORTH; break;
            case 's': dir = AxisDirection.SOUTH; break;
            case 'u': dir = AxisDirection.UP;    break;
            case 'd': dir = AxisDirection.DOWN;  break;
            default:  dir = null; break;
        }
        return dir;
    }

    @Override
    public double getMinimumValue() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double getMaximumValue() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public RangeMeaning getRangeMeaning() {
        return null;
    }

    @Override
    public Unit<?> getUnit() {
        return null;
    }
}
