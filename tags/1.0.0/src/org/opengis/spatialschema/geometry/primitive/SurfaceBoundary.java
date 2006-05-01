/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;


/**
 * The boundary of {@linkplain Surface surfaces}. A <code>SurfaceBoundary</code> consists of some number
 * of {@linkplain Ring rings}, corresponding to the various components of its boundary. In the normal 2D
 * case, one of these rings is distinguished as being the exterior boundary. In a general manifold this
 * is not always possible, in which case all boundaries shall be listed as interior boundaries,
 * and the exterior will be empty.
 *
 * <blockquote><font size=2>
 * <strong>NOTE:</strong> The use of exterior and interior here is not intended to invoke the
 * definitions of "interior" and "exterior" of geometric objects. The terms are in common usage,
 * and reflect a linguistic metaphor that uses the same linguistic constructs for the concept of
 * being inside an object to being inside a container. In normal mathematical terms, the exterior
 * boundary is the one that appears in the Jordan Separation Theorem (Jordan Curve Theorem extended
 * beyond 2D). The exterior boundary is the one that separates the surface (or solid in 3D) from
 * infinite space. The interior boundaries separate the object at hand from other bounded objects.
 * The uniqueness of the exterior comes from the uniqueness of unbounded space. Essentially, the
 * Jordan Separation Theorem shows that normal 2D or 3D space separates into bounded and unbounded
 * pieces by the insertion of a ring or shell, respectively. It goes beyond that, but this
 * specification is restricted to at most 3 dimensions.
 * <br><br>
 * <strong>EXAMPLE 1:</strong> If the underlying manifold is an infinite cylinder, then two
 * transverse cuts of the cylinder define a compact surface between the cuts, and two separate
 * unbounded portions of the cylinders. In this case, either cut could reasonably be called
 * exterior. In cases of such ambiguity, the standard chooses to list all boundaries in the
 * "interior" set. The only guarantee of an exterior boundary being unique is in the 2-dimensional
 * plane, E<sup>2</sup>.
 * <br><br>
 * <strong>EXAMPLE 2:</strong> Taking the equator of a sphere, and generating a 1 meter buffer,
 * we have a surface with two isomorphic boundary components. There is no unbiased manner to
 * distinguish one of these as an exterior.
 * </font></blockquote>
 *
 * @UML type GM_SurfaceBoundary
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see SolidBoundary
 */
public interface SurfaceBoundary extends PrimitiveBoundary {
    /**
     * Returns the exterior ring, or <code>null</code> if none.
     *
     * @return The exterior ring, or <code>null</code>.
     * @UML operation exterior
     */
    public Ring getExterior();

    /**
     * Returns the interior rings.
     *
     * @return The interior rings. Never <code>null</code>, but may be an empty array.
     * @UML operation interior
     */
    public Ring[] getInteriors();
}
