/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.geometry;

import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * The <code>BoundingPolygon</code> class represents a bounds object
 * whose region is defined by a polygon with <code>DirectPosition</code>
 * vertices.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface BoundingPolygon extends Bounds {
    
    /**
     * Returns the list of vertices for this object.
     */
    public DirectPosition[] getVertices();
    
    /**
     * Sets the list of vertices for this object.
     */
    public void setVertices(DirectPosition[] vertices);
}
