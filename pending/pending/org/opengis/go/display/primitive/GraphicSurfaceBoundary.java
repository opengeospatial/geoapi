/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.primitive;

import org.opengis.spatialschema.coordinate.SurfaceBoundary;

/**
 * The GraphicSurfaceBoundary defines a common abstraction for implementations that 
 * drawing the ISO 19107 Geometric SurfaceBoundary.
 * <p>
 * When a Fill style is applied, any interior rings act as holes, and are not filled.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicSurfaceBoundary extends Graphic {
    
    /**
     * Sets the geometry based on ISO 19107 geometric forms.
     * @param surfaceBoundary a geometry SurfaceBoundary 
     */
    public void setSurfaceBoundary(SurfaceBoundary surfaceBoundary);
    
    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     * @return the geometry SurfaceBoundary 
     */
    public SurfaceBoundary getSurfaceBoundary();

    /**
     * Converts the exterior Ring in the underlying SurfaceBoundary 
     * to a GraphicRing, and returns that GraphicRing.
     * @return the exterior GraphicRing.
     */    
    public GraphicRing getExterior();

    /**
     * Converts the GraphicRing to a Ring and sets it as the exterior Ring 
     * in the underlying SurfaceBoundary.
     * @return the exterior GraphicRing.
     */
    public void setExterior(GraphicRing exterior);
    
    /**
     * Converts the interior Rings in the underlying SurfaceBoundary 
     * to a GraphicRing, and returns that GraphicRing.
     * @return an array of interior GraphicRings, or null if there are no interior Rings.
     */    
    public GraphicRing[] getInteriors();
                   
    public void setInteriors(GraphicRing[] interior);
}
