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
import javax.measure.unit.NonSI;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.cs.RangeMeaning;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystemAxis;


/**
 * An axis of a {@link PJCRS} object.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJAxis extends PJObject implements CoordinateSystemAxis {
    /**
     * Coordinate system axis names, close but not necessarily identical to the names restricted
     * by the ISO 19111 specification. Some names may be slightly different because we don't have
     * enough information for inferring the exact names.
     */
    private static final ReferenceIdentifier[] NAMES = new ReferenceIdentifier[] {
        new PJIdentifier("Easting"),
        new PJIdentifier("Westing"),
        new PJIdentifier("Northing"),
        new PJIdentifier("Southing"),
        new PJIdentifier("Geodetic longitude"),
        new PJIdentifier("Geodetic latitude"),
        new PJIdentifier("Height"),
        new PJIdentifier("Depth")
    };

    /**
     * The direction, as a 'e', 'w', 'n', 's', 'u' or 'd' letter.
     */
    private final char direction;

    /**
     * The axis unit. If angular, then the CRS is presumed geographic or geocentric and the
     * units is fixed to {@link NonSI#DEGREE_ANGLE} (no other angular unit is permitted).
     * Otherwise the CRS is presumed projected with arbitrary linear unit.
     */
    private final Unit<?> unit;

    /**
     * Creates a new axis.
     */
    PJAxis(final char direction, final Unit<?> unit) {
        super(getName(direction, unit));
        this.direction = direction;
        this.unit = unit;
    }

    /**
     * Work around for RFE #4093999 in Sun's bug database
     * ("Relax constraint on placement of this()/super() call in constructors").
     */
    private static ReferenceIdentifier getName(final char direction, final Unit<?> unit) {
        final int i;
        if (isAngular(unit)) {
            switch (direction) {
                case 'e':
                case 'w': i=4; break;
                case 'n':
                case 's': i=5; break;
                case 'u': i=6; break;
                case 'd': i=7; break;
                default: return null;
            }
        } else {
            switch (direction) {
                case 'e': i=0; break;
                case 'w': i=1; break;
                case 'n': i=2; break;
                case 's': i=3; break;
                case 'u': i=6; break;
                case 'd': i=7; break;
                default: return null;
            }
        }
        return NAMES[i];
    }

    /**
     * Returns {@code true} if the units are angular units.
     * In such case, the CRS is presumed geographic or geocentric.
     */
    private static boolean isAngular(final Unit<?> unit) {
        return NonSI.DEGREE_ANGLE.isCompatible(unit);
    }

    /**
     * Returns the abbreviation, which is inferred from the unit and direction.
     */
    @Override
    public String getAbbreviation() {
        if (isAngular(unit)) {
            switch (direction) {
                case 'e':
                case 'w': return "λ";
                case 'n':
                case 's': return "φ";
            }
        } else {
            switch (direction) {
                case 'e':
                case 'w': return "x";
                case 'n':
                case 's': return "y";
            }
        }
        switch (direction) {
            case 'u': return "h";
            case 'd': return "d";
            default:  return null;
        }
    }

    /**
     * Returns the direction.
     */
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

    /**
     * Returns the minimal value permitted by this axis.
     */
    @Override
    public double getMinimumValue() {
        return -getMaximumValue();
    }

    /**
     * Returns the minimal value permitted by this axis.
     */
    @Override
    public double getMaximumValue() {
        if (isAngular(unit)) {
            switch (direction) {
                case 'e':
                case 'w': return 180;
                case 'n':
                case 's': return 90;
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the range meaning.
     */
    @Override
    public RangeMeaning getRangeMeaning() {
        if (isAngular(unit)) {
            switch (direction) {
                case 'e':
                case 'w': return RangeMeaning.WRAPAROUND;
                case 'n':
                case 's': return RangeMeaning.EXACT;
            }
        }
        return null;
    }

    /**
     * Returns the units given at construction time.
     */
    @Override
    public Unit<?> getUnit() {
        return unit;
    }
}
