/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.crs.coordops;

import org.opengis.crs.coordrefsys.*;
import org.opengis.spatialschema.coordinate.DirectPosition;
/**
 * <code>CoordinateTransformation</code> defines a common abstraction for classes
 * that convert from one Coordinate Reference System to another.
 *
 * @see DirectPosition
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CoordinateTransformation {
    /**
     * Converts the <code>DirectPosition</code> passed in as <code>fromCoordinate</code>
     * to a different type.
     * @param fromCoordinate the DirectPosition to convert.
     * @param toCoordinateInterface the type of <code>DirectPosition</code> to convert to.
     * @return Returns the <code>DirectPosition</code> that results from the
     *   conversion.
     */
    public DirectPosition convertDirectPosition(DirectPosition fromCoordinate, Class toCoordinateInterface, 
    	CoordinateReferenceSystem toCRS);
        
}

