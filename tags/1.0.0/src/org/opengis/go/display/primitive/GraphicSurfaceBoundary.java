/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive;

import org.opengis.spatialschema.geometry.primitive.SurfaceBoundary;

/**
 * Defines a common abstraction for implementations that drawing the
 * ISO 19107 Geometric {@link SurfaceBoundary}.
 *
 * When a Fill style is applied, any interior rings act as holes, and are not filled.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface GraphicSurfaceBoundary extends Graphic {
    
    /**
     * Sets the geometry based on ISO 19107 geometric forms.
     *
     * @param surfaceBoundary a geometry SurfaceBoundary 
     */
    public void setSurfaceBoundary(SurfaceBoundary surfaceBoundary);
    
    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     *
     * @return the geometry SurfaceBoundary 
     */
    public SurfaceBoundary getSurfaceBoundary();

    /**
     * Converts the exterior Ring in the underlying SurfaceBoundary 
     * to a GraphicRing, and returns that GraphicRing.
     *
     * @return the exterior GraphicRing.
     */    
    public GraphicRing getExterior();

    /**
     * Converts the GraphicRing to a Ring and sets it as the exterior Ring 
     * in the underlying SurfaceBoundary.
     */
    public void setExterior(GraphicRing exterior);
    
    /**
     * Converts the interior Rings in the underlying SurfaceBoundary 
     * to a GraphicRing, and returns that GraphicRing.
     *
     * @return an array of interior GraphicRings, or null if there are no interior Rings.
     */    
    public GraphicRing[] getInteriors();
    
    /**
     * Converts the given <code>GraphicRing</code>s to <code>Ring</code>s and sets them
     * as the interior <code>Ring</code>s in the underlying <code>SurfaceBoundary</code>.
     * @param interiors an array of interior <code>GraphicRing</code>s.
     */               
    public void setInteriors(GraphicRing[] interiors);
}

