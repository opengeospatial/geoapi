/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.canvas;

/**
 * Marker interface for all classes that access <code>Canvas</code> parameters, 
 * and abstracts the shape of the Canvas display.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CanvasParameterAccessor {
    
    /**
     * Returns a copy of this state object.
     */
    public Object clone() throws CloneNotSupportedException;

    /**
     * Determines if the given object is the same type of canvas state object
     * and has values equal to this one.
     */
    public boolean equals(Object object);

}
