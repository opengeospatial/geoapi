/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
import java.util.Set;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A triangulated surface that uses the Delaunay algorithm or a similar algorithm complemented
 * with consideration for breaklines, stoplines and maximum length of triangle sides. These
 * networks satisfy the Delaunay criterion away from the modifications: For each triangle in
 * the network, the circle passing through its vertexes does not contain, in its interior, the
 * vertex of any other triangle.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see GeometryFactory#createTin
 */
@UML(identifier="GM_Tin", specification=ISO_19107)
public interface Tin extends TriangulatedSurface {
    /**
     * Stoplines are lines where the local continuity or regularity of the surface is questionable.
     * In the area of these pathologies, triangles intersecting a stopline shall be removed from
     * the TIN surface, leaving holes in the surface. If coincidence occurs on surface boundary
     * triangles, the result shall be a change of the surface boundary. The attribute
     * {@code stopLines} contains all these pathological segments as a set of line strings.
     */
    @UML(identifier="stopLines", obligation=MANDATORY, specification=ISO_19107)
    Set<LineString> getStopLines();

    /**
     * Breaklines are lines of a critical nature to the shape of the surface, representing local
     * ridges, or depressions (such as drainage lines) in the surface. As such their constituent
     * segments must be included in the TIN even if doing so violates the Delaunay criterion. The
     * attribute {@code breakLines} contains these critical segments as a set of line strings.
     */
    @UML(identifier="breakLines", obligation=MANDATORY, specification=ISO_19107)
    Set<LineString> getBreakLines();

    /**
     * Maximal length for retention.
     * Areas of the surface where the data is not sufficiently dense to assure reasonable
     * calculations shall be removed by adding a retention criterion for triangles based
     * on the length of their sides. For any triangle sides exceeding maximum length, the
     * adjacent triangles to that triangle side shall be removed from the surface.
     */
    @UML(identifier="maxLength", obligation=MANDATORY, specification=ISO_19107)
    double getMaxLength();

    /**
     * The corners of the triangles in the TIN are often referred to as posts. The attribute
     * {@code controlPoint} shall contain a set of the {@linkplain Position positions} used
     * as posts for this TIN. Since each TIN contains triangles, there must be at least 3 posts.
     * The order in which these points are given does not affect the surface that is represented.
     * Application schemas may add information based on the ordering of the control points to
     * facilitate the reconstruction of the TIN from the control points.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19107)
    List<Position> getControlPoints();
}
