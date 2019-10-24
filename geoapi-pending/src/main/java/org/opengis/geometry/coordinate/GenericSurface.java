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

import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Common interface for {@linkplain org.opengis.geometry.primitive.Surface surface} and
 * {@linkplain org.opengis.geometry.primitive.SurfacePatch surface patch}. {@code Surface}
 * and {@code SurfacePatch} represent sections of surface geometry,
 * and therefore share a number of operation signatures.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @todo Investigate why this interface doesn't extends {@link org.opengis.geometry.Geometry},
 *       since it is a cause of difficulty with {@link org.opengis.coverage.Coverage}.
 */
@UML(identifier="GM_GenericSurface", specification=ISO_19107)
public interface GenericSurface {
    /**
     * Returns a vector perpendicular to the {@code GenericSurface} at the
     * {@linkplain DirectPosition direct position} passed, which must be on this
     * {@code GenericSurface}. The upward normal always points upward in a
     * manner consistent with the boundary. This means that the exterior boundary
     * of the surface is counterclockwise when viewed from the side of the surface
     * indicated by the {@code upNormal}. Interior boundaries are clockwise.
     * The side of the surface indicated by the {@code upNormal} is referred
     * to as the "top." The function "upNormal" shall be continuous and the length
     * of the normal shall always be equal to 1.0.
     *
     * <div class="note"><b>Note:</b>
     * the upNormal along a boundary of a solid always points away from the
     * solid. This is a slight semantics problem in dealing with voids within solids, where the
     * upNormal (for sake of mathematical consistency) points into the center of the voided region,
     * which linguistically can be considered the interior of the void. What the confusion is here
     * is that the basic linguistic metaphors used in most languages for "interior of solid" and
     * for "interior of container" use "inward" in inconsistent manners from a topological point
     * of view. The void "in" rock is not inside the rock in the same manner as the solid material
     * that makes up the substance of the rock. Nor is the coffee "in" the cup the same "in" as
     * the ceramic glass "in" the cup. The use of these culturally derived metaphors may not be
     * consistent across all languages, some of which may use different prepositions for these two
     * different concepts. This specification uses the linguistically neutral concept of "interior"
     * derived from mathematics (topology).
     * </div>
     *
     * @param point The point on this {@code GenericSurface} where to compute the upNormal.
     * @return the upNormal unit vector.
     */
    @UML(identifier="upNormal", obligation=MANDATORY, specification=ISO_19107)
    double[] getUpNormal(DirectPosition point);

    /**
     * Returns the sum of the lengths of all the boundary components of this
     * {@code GenericSurface}. Since perimeter, like length, is an accumulation
     * (integral) of distance, its return value shall be in a reference system appropriate
     * for measuring distances.
     *
     * <div class="note"><b>Note:</b>
     * the perimeter is defined as the sum of the lengths of all boundary
     * components. The length of a curve or of a collection of curves is always positive and
     * non-zero (unless the curve is pathological). This means that holes in surfaces will
     * contribute positively to the total perimeter.
     * </div>
     *
     * @return the perimeter.
     * @unitof Length
     */
    @UML(identifier="perimeter", obligation=MANDATORY, specification=ISO_19107)
    double getPerimeter();

    /**
     * Returns the area of this {@code GenericSurface}. The area of a 2-dimensional geometric
     * object shall be a numeric measure of its surface area (in a square unit of distance). Since
     * area is an accumulation (integral) of the product of two distances, its return value shall
     * be in a unit of measure appropriate for measuring distances squared, such as meters squared
     * (m²).
     *
     * <div class="note"><b>Note:</b>
     * consistent with the definition of surface as a set of
     * {@linkplain DirectPosition direct positions}, holes in the surfaces will not contribute to
     * the total area. If the usual Green's Theorem (or more general Stokes' Theorem) integral is
     * used, the integral around the holes in the surface are subtracted from the integral
     * about the exterior of the surface patch.
     * </div>
     *
     * @return the area.
     * @unitof Area
     */
    @UML(identifier="area", obligation=MANDATORY, specification=ISO_19107)
    double getArea();
}
