/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * Common interface for {@linkplain org.opengis.spatialschema.geometry.primitive.Surface surface} and
 * {@linkplain org.opengis.spatialschema.geometry.primitive.SurfacePatch surface patch}. <code>Surface</code>
 * and <code>SurfacePatch</code> represent sections of surface geometry,
 * and therefore share a number of operation signatures.
 *
 * @UML type GM_GenericSurface
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GenericSurface {
    /**
     * Returns a vector perpendicular to the <code>GenericSurface</code> at the
     * {@linkplain DirectPosition direct position} passed, which must be on this
     * <code>GenericSurface</code>. The upward normal always points upward in a
     * manner consistent with the boundary. This means that the exterior boundary
     * of the surface is counterclockwise when viewed from the side of the surface
     * indicated by the <code>upNormal</code>. Interior boundaries are clockwise.
     * The side of the surface indicated by the <code>upNormal</code> is referred
     * to as the "top." The function "upNormal" shall be continuous and the length
     * of the normal shall always be equal to 1.0.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> The upNormal along a boundary of a solid always points away from the
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
     * </font></blockquote>
     *
     * @param point The point on this <code>GenericSurface</code> where to compute the upNormal.
     * @return The upNormal unit vector.
     * @UML operation upNormal
     */
    public double[] getUpNormal(DirectPosition point);

    /**
     * Returns the sum of the lengths of all the boundary components of this
     * <code>GenericSurface</code>. Since perimeter, like length, is an accumulation
     * (integral) of distance, its return value shall be in a reference system appropriate
     * for measuring distances.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> The perimeter is defined as the sum of the lengths of all boundary
     * components. The length of a curve or of a collection of curves is always positive and
     * non-zero (unless the curve is pathological). This means that holes in surfaces will
     * contribute positively to the total perimeter.
     * </font></blockquote>
     *
     * @return The perimeter.
     * @unitof Length
     * @UML operation perimeter
     */
    public double getPerimeter();

    /**
     * Returns the area of this <code>GenericSurface</code>. The area of a 2-dimensional geometric
     * object shall be a numeric measure of its surface area (in a square unit of distance). Since
     * area is an accumulation (integral) of the product of two distances, its return value shall
     * be in a unit of measure appropriate for measuring distances squared, such as meters squared
     * (m<sup>2</sup>).
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> Consistent with the definition of surface as a set of
     * {@linkplain DirectPosition direct positions}, holes in the surfaces will not contribute to
     * the total area. If the usual Green's Theorem (or more general Stokes' Theorem) integral is
     * used, the integral around the holes in the surface are subtracted from the integral
     * about the exterior of the surface patch.
     * </font></blockquote>
     *
     * @return The area.
     * @unitof Area
     * @UML operation area
     */
    public double getArea();
}
