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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A planar polygon defined by 3 corners. That is, a triangle would be the result of a constructor
 * of the form: {@code Polygon(LineString({P1, P2, P3, P1}))} where <var>P₁</var>,
 * <var>P₂</var>, and <var>P₃</var> are three {@linkplain Position positions}.
 * Triangles have no holes. Triangle shall be used to construct
 * {@linkplain TriangulatedSurface triangulated surfaces}.
 * <p>
 * <strong>Note:</strong> The points in a triangle can be located in terms of their corner points
 * by defining a set of barycentric coordinates, three nonnegative numbers <var>c₁</var>,
 * <var>c₂</var>, and <var>c₃</var> such that
 *
 * <var>c₁</var> + <var>c₂</var> + <var>c₃</var> = 1.0.
 *
 * Then, each point <var>P</var> in the triangle can be expressed for some set of barycentric coordinates as:
 *
 * <blockquote>
 * P = <var>c₁</var>⋅<var>P₁</var> +
 *     <var>c₂</var>⋅<var>P₂</var> +
 *     <var>c₃</var>⋅<var>P₃</var>
 * </blockquote>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Triangle", specification=ISO_19107)
public interface Triangle extends Polygon {
    /**
     * Returns the triangle corner. The list must contains exactly 3 elements.
     */
    @UML(identifier="corners", obligation=MANDATORY, specification=ISO_19107)
    List<Position> getCorners();

    /**
     * Returns the patch which own this surface patch.
     */
    @UML(identifier="surface", obligation=MANDATORY, specification=ISO_19107)
    TriangulatedSurface getSurface();
}
