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
 * The <code>GraphicSurfaceBoundary</code> defines a common abstraction for implementations that 
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
     * @param surfaceBoundary a geometry <code>SurfaceBoundary</code>.
     */
    public void setSurfaceBoundary(SurfaceBoundary surfaceBoundary);
    
    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     * @return the geometry <code>SurfaceBoundary</code>.
     */
    public SurfaceBoundary getSurfaceBoundary();

    /**
     * Converts the exterior <code>Ring</code> in the underlying <code>SurfaceBoundary</code> 
     * to a <code>GraphicRing</code>, and returns that <code>GraphicRing</code>.
     * @return the exterior <code>GraphicRing</code>.
     */    
    public GraphicRing getExterior();

    /**
     * Converts the <code>GraphicRing</code> to a Ring and sets it as the exterior Ring 
     * in the underlying <code>SurfaceBoundary</code>.
     * @return the exterior <code>GraphicRing</code>.
     */
    public void setExterior(GraphicRing exterior);
    
    /**
     * Converts the interior Rings in the underlying <code>SurfaceBoundary</code> 
     * to <code>GraphicRing</code>s, and returns those <code>GraphicRing</code>s.
     * @return an array of interior <code>GraphicRing</code>, or null if there are no interior Rings.
     */    
    public GraphicRing[] getInteriors();
    
    /**
     * Converts the given <code>GraphicRing</code>s to <code>Ring</code>s and sets them
     * as the interior <code>Ring</code>s in the underlying <code>SurfaceBoundary</code>.
     * @param interiors an array of interior <code>GraphicRing</code>s.
     */               
    public void setInteriors(GraphicRing[] interiors);
}
