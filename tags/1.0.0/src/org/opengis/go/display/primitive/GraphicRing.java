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

import org.opengis.spatialschema.geometry.primitive.Ring;

/**
 * Common abstraction for implementations that drawing the ISO 19107 Geometric {@link Ring}.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface GraphicRing extends GraphicCompositeCurve {
    
    /**
     * Sets the geometry based on ISO 19107 geometric forms.
     * @param ring a geometry Ring 
     */
    public void setRing(Ring ring);
    
    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     * @return the geometry Ring 
     */
    public Ring getRing();
}

