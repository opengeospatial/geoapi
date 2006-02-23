/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.geometry.GenericSurface;


/**
 * Surface with a positive orientation.
 * <code>Surface</code> is a subclass of {@link Primitive} and is the basis for 2-dimensional
 * geometry. Unorientable surfaces such as the M&ouml;bius band are not allowed. The orientation of
 * a surface chooses an "up" direction through the choice of the upward normal, which, if the
 * surface is not a cycle, is the side of the surface from which the exterior boundary appears
 * counterclockwise. Reversal of the surface orientation reverses the curve orientation of each
 * boundary component, and interchanges the conceptual "up" and "down" direction of the surface.
 * If the surface is the boundary of a solid, the "up" direction is usually outward. For closed
 * surfaces, which have no boundary, the up direction is that of the surface patches, which must
 * be consistent with one another. Its included {@linkplain SurfacePatch surface patches} describe
 * the interior structure of a <code>Surface</code>.
 *
 * <blockquote><font size=2>
 * <strong>NOTE:</strong> Other than the restriction on orientability, no other "validity" condition is required for GM_Surface.
 * </font></blockquote>
 *
 * @UML type GM_Surface
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see PrimitiveFactory#createSurface(List)
 * @see PrimitiveFactory#createSurface(SurfaceBoundary)
 */
public interface Surface extends OrientableSurface, GenericSurface {
    /**
     * Relates this <code>Surface</code> to a set of {@linkplain SurfacePatch surface patches} that
     * shall be joined together to form this surface. Depending on the interpolation method, the set
     * of patches may require significant additional structure.
     *
     * If the surface {@linkplain #getCoordinateDimension coordinate dimension} is 2, then the
     * entire <code>Surface</code> is one logical patch defined by linear interpolation from the
     * boundary.
     *
     * @return The list of surface patches. Should never be <code>null</code> neither empty.
     * @UML association patch
     *
     * @see SurfacePatch#getSurface
     */
    public List/*<SurfacePatch>*/ getPatches();
}
