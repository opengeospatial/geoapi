/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.geometry.GenericSurface;


/**
 * GM_Surface (Figure 12) a subclass of GM_Primitive and is the basis for 2-dimensional
 * geometry. Unorientable surfaces such as the Mobius band are not allowed. The orientation
 * of a surface chooses an "up" direction through the choice of the upward normal, which,
 * if the surface is not a cycle, is the side of the surface from which the exterior
 * boundary appears counterclockwise. Reversal of the surface orientation reverses the
 * curve orientation of each boundary component, and interchanges the conceptual "up"
 * and "down" direction of the surface. If the surface is the boundary of a solid, the
 * "up" direction is usually outward. For closed surfaces, which have no boundary, the
 * up direction is that of the surface patches, which must be consistent with one another.
 * Its included GM_SurfacePatches describe the interior structure of a GM_Surface. NOTE
 * Other than the restriction on orientability, no other "validity" condition is required
 * for GM_Surface. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Surface extends OrientableSurface, GenericSurface {
//    public GM_SurfacePatch patch[];
//    public void setPatch(GM_SurfacePatch patch[]) {  }
//    public GM_SurfacePatch[] getPatch() { return null; }
}
