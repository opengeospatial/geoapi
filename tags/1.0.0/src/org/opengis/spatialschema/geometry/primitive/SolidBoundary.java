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
 * The boundary of {@linkplain Solid solids}. Solid boundaries are similar to
 * {@linkplain SurfaceBoundary surface boundaries}. In normal 3-dimensional Euclidean
 * space, one {@linkplain Shell shell} is distinguished as the exterior. In the more
 * general case, this is not always possible.
 *
 * <blockquote><font size=2>
 * <strong>NOTE:</strong> An alternative use of solids with no external shell would be to define
 * "complements" of finite solids. These infinite solids would have only interior boundaries. If
 * this specification is extended to 4D Euclidean space, or if 3D compact manifolds are used
 * (probably not in geographic information), then other examples of bounded solids without exterior
 * boundaries are possible.
 * </font></blockquote>
 *
 * @UML type GM_SolidBoundary
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see SurfaceBoundary
 */
public interface SolidBoundary extends PrimitiveBoundary {
    /**
     * Returns the exterior shell, or <code>null</code> if none.
     *
     * @return The exterior shell, or <code>null</code>.
     * @UML operation exterior
     */
    public Shell getExterior();

    /**
     * Returns the interior shells.
     *
     * @return The interior shells. Never <code>null</code>, but may be an empty array.
     * @UML operation interior
     */
    public Shell[] getInteriors();
}
