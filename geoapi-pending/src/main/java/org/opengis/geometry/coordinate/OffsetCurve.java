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

import java.util.List;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A curve at a constant distance from the basis curve. They can be useful as a cheap and
 * simple alternative to constructing curves that are offsets by definition.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_OffsetCurve", specification=ISO_19107)
public interface OffsetCurve extends CurveSegment {
    /**
     * Returns the base curves.
     */
    @UML(identifier="baseCurve", obligation=MANDATORY, specification=ISO_19107)
    List<CurveSegment> getBaseCurves();

    /**
     * Returns the distance at which the offset curve is generated from the basis curve.
     * In a 2D system, positive distances are to be left of the basis curve, and negative
     * distances are right of the basis curve.
     *
     * @unitof Length
     */
    @UML(identifier="distance", obligation=MANDATORY, specification=ISO_19107)
    double getDistance();

    /**
     * Defines the vector direction of the offset curve from the basis curve. It can be
     * omitted in the 2D case, where the distance can be positive or negative. In that
     * case, distance defines left side (positive distance) or right side (negative distance)
     * with respect to the tangent to the basis curve. In 3D the basis curve shall have a
     * well-defined tangent direction for every point. The offset curve at any point
     * (parameter) on the basis curve <var>c</var> is in the direction
     *
     * <P>(TODO: paste the formulae here)</P>
     *
     * <P>For the offset direction to be well-defined, <var>v</var> shall not on any point
     * of the curve be in the same, or opposite, direction as <var>t</var>.
     *
     * The default value of the reference direction shall be the local coordinate axis
     * vector for elevation, which indicates up for the curve in a geographic sense.
     * If the reference direction is the positive tangent to the local elevation axis
     * ("points upward"), then the offset vector points to the left of the curve when
     * viewed from above.
     */
    @UML(identifier="refDirection", obligation=OPTIONAL, specification=ISO_19107)
    double[] getReferenceDirection();
}
