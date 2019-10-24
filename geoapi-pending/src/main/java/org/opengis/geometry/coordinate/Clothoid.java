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
package org.opengis.geometry.coordinate;

import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The clothoid (or Cornu's spiral), a plane curve whose curvature is a fixed function of
 * its length. In suitably chosen co-ordinates it is given by Fresnel's integrals:
 *
 * @todo TODO: paste the equation here
 *
 * This geometry is mainly used as a transition curve between curves of type straight
 * line/circular arc or circular arc/circular arc. With this curve type it is possible
 * to achieve a C2-continuous transition between the above mentioned curve types. One
 * formula for the clothoid is <var>A</var>² = <var>R</var>×<var>t</var>
 * where <var>A</var> is a constant, <var>R</var> is the varying radius of curvature along
 * the curve and <var>t</var> is the length along the curve and given in the Fresnel integrals.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Clothoid", specification=ISO_19107)
public interface Clothoid extends CurveSegment {
    /**
     * Returns an affine mapping that places the curve defined by the Fresnel Integrals
     * into the coordinate reference system of this object.
     */
    @UML(identifier="refLocation", obligation=MANDATORY, specification=ISO_19107)
    AffinePlacement getReferenceLocation();

    /**
     * Gives the value for <var>A</var> in the equations above.
     */
    @UML(identifier="scaleFactor", obligation=MANDATORY, specification=ISO_19107)
    double getScaleFactor();

    /**
     * Returns the arc length distance from the inflection point that will be the
     * {@linkplain #getStartPoint start point} for this curve segment. This shall
     * be lower limit <var>t</var> used in the Fresnel integral and is the value
     * of the constructive parameter of this curve segment at its start point. The
     * start parameter can be either positive or negative. The parameter <var>t</var>
     * acts as a constructive parameter.
     *
     * <div class="note"><b>Note:</b>
     * if 0 lies between the {@linkplain #getStartConstructiveParam start constructive
     * parameter} and {@linkplain #getEndConstructiveParam end constructive parameter} of the
     * clothoid, then the curve goes through the clothoid's inflection point, and the direction
     * of its radius of curvature, given by the second derivative vector, changes sides
     * with respect to the tangent vector. The term "length" for the parameter {@code t}
     * is applicable only in the parameter space, and its relation to arc length after use of
     * the placement, and with respect to the coordinate reference system of the curve is not
     * deterministic.
     * </div>
     */
    @UML(identifier="startParameter", obligation=MANDATORY, specification=ISO_19107)
    double getStartConstructiveParam();

    /**
     * Returns the arc length distance from the inflection point that will be the
     * {@linkplain #getEndPoint end point} for this curve segment. This shall be
     * upper limit <var>t</var> used in the Fresnel integral and is the constructive
     * parameter of this curve segment at its end point. The end constructive param
     * can be either positive or negative.
     */
    @UML(identifier="endParameter", obligation=MANDATORY, specification=ISO_19107)
    double getEndConstructiveParam();
}
