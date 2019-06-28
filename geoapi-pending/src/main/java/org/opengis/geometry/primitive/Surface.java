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

import java.util.List;
import org.opengis.geometry.coordinate.GenericSurface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Surface with a positive orientation.
 * {@code Surface} is a subclass of {@link Primitive} and is the basis for 2-dimensional
 * geometry. Unorientable surfaces such as the M&ouml;bius band are not allowed. The orientation of
 * a surface chooses an "up" direction through the choice of the upward normal, which, if the
 * surface is not a cycle, is the side of the surface from which the exterior boundary appears
 * counterclockwise. Reversal of the surface orientation reverses the curve orientation of each
 * boundary component, and interchanges the conceptual "up" and "down" direction of the surface.
 * If the surface is the boundary of a solid, the "up" direction is usually outward. For closed
 * surfaces, which have no boundary, the up direction is that of the surface patches, which must
 * be consistent with one another. Its included {@linkplain SurfacePatch surface patches} describe
 * the interior structure of a {@code Surface}.
 *
 * <div class="note"><b>Note:</b>
 * Other than the restriction on orientability, no other "validity" condition is required for GM_Surface.
 * </div>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see PrimitiveFactory#createSurface(List)
 * @see PrimitiveFactory#createSurface(SurfaceBoundary)
 */
@UML(identifier="GM_Surface", specification=ISO_19107)
public interface Surface extends OrientableSurface, GenericSurface {
    /**
     * Relates this {@code Surface} to a set of {@linkplain SurfacePatch surface patches} that
     * shall be joined together to form this surface. Depending on the interpolation method, the set
     * of patches may require significant additional structure.
     *
     * If the surface {@linkplain #getCoordinateDimension coordinate dimension} is 2, then the
     * entire {@code Surface} is one logical patch defined by linear interpolation from the
     * boundary.
     *
     * @return the list of surface patches. Should never be {@code null} neither empty.
     *
     * @see SurfacePatch#getSurface
     * @see Curve#getSegments
     */
    @UML(identifier="patch", obligation=MANDATORY, specification=ISO_19107)
    List<? extends SurfacePatch> getPatches();

    /**
     * Returns the orientable surfaces associated with this surface.
     *
     * @return the orientable surfaces as an array of length 2.
     *
     * @see OrientableSurface#getPrimitive
     */
    @UML(identifier="proxy", obligation=MANDATORY, specification=ISO_19107)
    OrientableSurface[] getProxy();
}
