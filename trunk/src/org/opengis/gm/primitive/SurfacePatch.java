/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.geometry.GenericSurface;


/**
 * GM_SurfacePatch (Figure 20) defines a homogeneous portion of a GM_Surface. The multiplicity
 * of the association "Segmentation" (Figure 12) specifies that each GM_SurfacePatch
 * shall be in at most one GM_Surface. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface SurfacePatch extends GenericSurface {
    /**
     * Returns the patch which own this surface patch.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> In this specification, surface patches do not appear except in the
     * context of a surface, and therefore this method should never returns <code>null</code>
     * which would preclude the use of surface patches except in this manner. While this would
     * not affect this specification, allowing <code>null</code> owner allows other standards
     * based on this one to use surface patches in a more open-ended manner.
     * </font></blockquote>
     *
     * @return The owner of this surface patch, or <code>null</code> if none.
     * @UML association surface
     *
     * @see Surface#getPatches
     */
    public Surface getSurface();

//    public GM_SurfaceInterpolation interpolation;
//    public  numDerivativesOnBoundary[0..1];
//    public GM_SurfaceBoundary boundary () { return null; }
}
