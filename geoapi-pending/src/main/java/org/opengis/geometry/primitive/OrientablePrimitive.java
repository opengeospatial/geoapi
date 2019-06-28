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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Primitives that can be mirrored into new geometric objects in terms of their internal local
 * coordinate systems (manifold charts). For curves, the orientation reflects the direction in
 * which the curve is traversed, that is, the sense of its parameterization. When used as boundary
 * curves, the surface being bounded is to the "left" of the oriented curve. For surfaces, the
 * orientation reflects from which direction the local coordinate system can be viewed as right
 * handed, the "top" or the surface being the direction of a completing <var>z</var>-axis that
 * would form a right-handed system. When used as a boundary surface, the bounded solid is "below"
 * the surface. The orientation of points and solids has no immediate geometric interpretation in
 * 3-dimensional space.
 * <p>
 * {@code OrientablePrimitive} objects are essentially references to geometric primitives
 * that carry an "orientation" reversal flag (either "+" or "-") that determines whether this
 * primitive agrees or disagrees with the orientation of the referenced object.
 *
 * <div class="note"><b>Note:</b>
 * There are several reasons for subclassing the "positive" primitives
 * under the orientable primitives. First is a matter of the semantics of subclassing. Subclassing
 * is assumed to be a "is type of" hierarchy. In the view used, the "positive" primitive is simply
 * the orientable one with the positive orientation. If the opposite view were taken, and orientable
 * primitives were subclassed under the "positive" primitive, then by subclassing logic, the
 * "negative" primitive would have to hold the same sort of geometric description that the
 * "positive" primitive does. The only viable solution would be to separate "negative" primitives
 * under the geometric root as being some sort of reference to their opposite. This adds a great
 * deal of complexity to the subclassing tree. To minimize the number of objects and to bypass this
 * logical complexity, positively oriented primitives are self-referential (are instances of the
 * corresponding primitive subtype) while negatively oriented primitives are not.
 * </div>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_OrientablePrimitive", specification=ISO_19107)
public interface OrientablePrimitive extends Primitive {
    /**
     * Determines which of the two possible orientations this object represents.
     *
     * @return +1 for a positive orientation, or -1 for a negative orientation.
     */
    @UML(identifier="orientation", obligation=MANDATORY, specification=ISO_19107)
    int getOrientation();

    /**
     * Returns the primitive associated with this {@code OrientablePrimitive}.
     * Each {@linkplain Primitive primitive} of dimension 1 or 2 is associated
     * to two {@code OrientablePrimitive}s, one for each possible orientation.
     * For curves and surfaces, there are exactly two orientable primitives
     * for each geometric object. For the positive orientation, the orientable
     * primitive shall be the corresponding {@linkplain Curve curve} or
     * {@linkplain Surface surface}.
     * <p>
     * This method is <em>optional</em> since the association in ISO 19107 is navigable
     * only from {@code Primitive} to {@code OrientablePrimitive}, not the other way.
     *
     * @return the primitive, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see Primitive#getProxy
     */
    @UML(identifier="primitive", obligation=OPTIONAL, specification=ISO_19107)
    Primitive getPrimitive();
}
