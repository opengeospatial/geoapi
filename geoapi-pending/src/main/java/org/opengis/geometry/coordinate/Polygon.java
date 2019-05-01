/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfacePatch;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.geometry.complex.Complex;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A surface patch that is defined by a set of boundary curves and an underlying surface to
 * which these curves adhere. The default is that the curves are coplanar and the polygon
 * uses planar interpolation in its interior.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see GeometryFactory#createPolygon(SurfaceBoundary)
 * @see GeometryFactory#createPolygon(SurfaceBoundary,Surface)
 */
@UML(identifier="GM_Polygon", specification=ISO_19107)
public interface Polygon extends SurfacePatch {
    /**
     * The surface boundary that is the boundary of this polygon.
     * The boundary of a surface patch need not be in the same {@linkplain Complex complex}
     * as the containing {@linkplain Surface surface}. The curves that are contained in the
     * interior of the {@linkplain Surface surface} (act as common boundary to 2 surface patches)
     * are not part of any {@linkplain Complex complex} in which the {@linkplain Surface surface}
     * is contained. They are purely constructive and would not play in any topological relation
     * between {@linkplain Surface surface} and {@linkplain Curve curve} that defines the connectivity
     * of the {@linkplain Complex complex}.
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    SurfaceBoundary getBoundary();

    /**
     * Provides a mechanism for spanning the interior of the polygon.
     * The spanning surface should have no boundary components that intersect the boundary of the
     * polygon, and there should be no ambiguity as to which portion of the surface is described by
     * the bounding curves for the polygon. The most common spanning surface is an elevation model,
     * which is not directly described in this standard, although {@linkplain Tin tins} and
     * {@linkplain GriddedSurface gridded surfaces} are often used in this role.
     */
    @UML(identifier="spanningSurface", obligation=OPTIONAL, specification=ISO_19107)
    List<Surface> getSpanningSurface();

    /**
     * Returns the patch which own this surface patch.
     */
    @UML(identifier="surface", obligation=MANDATORY, specification=ISO_19107)
    PolyhedralSurface getSurface();
}
