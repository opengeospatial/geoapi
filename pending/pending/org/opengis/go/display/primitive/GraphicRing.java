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

import org.opengis.spatialschema.primitive.Ring;
/**
 * The GraphicRing defines a common abstraction for implementations that 
 * drawing the ISO 19107 Geometric Ring.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
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
