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
package org.opengis.geometry.primitive;

import javax.measure.Unit;
import javax.measure.quantity.Angle;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents direction in the coordinate reference system. In a 2D coordinate reference
 * system, this can be accomplished using a "angle measured from true north" or a 2D vector
 * point in that direction. In a 3D coordinate reference system, two angles or any 3D vector
 * is possible. If both a set of angles and a vector are given, then they shall be consistent
 * with one another.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="Bearing", specification=ISO_19107)
public interface Bearing {
    /**
     * Returns the unit of values returned by the {@link #getAngles()} method.
     *
     * @return the unit of angles.
     *
     * @since GeoAPI 2.3
     */
    Unit<Angle> getAngleUnit();

    /**
     * Returns the azimuth and (optionally) the altitude.
     * In this variant of bearing usually used for 2D coordinate systems, the first angle (azimuth)
     * is measured from the first coordinate axis (usually north) in a counterclockwise fashion
     * parallel to the reference surface tangent plane. If two angles are given, the second angle
     * (altitude) usually represents the angle above (for positive angles) or below (for negative
     * angles) a local plane parallel to the tangent plane of the reference surface.
     *
     * @return an array of length 0, 1 or 2 containing the azimuth and altitude angles,
     *         in units of {@link #getAngleUnit()}.
     */
    @UML(identifier="angle", obligation=MANDATORY, specification=ISO_19107)
    double[] getAngles();

    /**
     * Returns the direction as a vector.
     * In this variant of bearing usually used for 3D coordinate systems, the direction is
     * express as an arbitrary vector, in the coordinate system.
     *
     * @return the direction.
     */
    @UML(identifier="direction", obligation=MANDATORY, specification=ISO_19107)
    double[] getDirection();
}
