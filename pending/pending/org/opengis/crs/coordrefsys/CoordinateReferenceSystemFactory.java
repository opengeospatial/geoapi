/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.crs.coordrefsys;

import java.util.Properties;

import org.opengis.crs.coordops.CoordinateTransformation;

/**
 * <code>CoordinateReferenceSystemFactory</code> defines a common 
 * abstraction for implementations
 * that create <code>CoordinateReferenceSystem</code>s.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CoordinateReferenceSystemFactory {
	
	public static String COORDINATE_REFERECE_SYSTEM_URL = "COORDINATE_REFERECE_SYSTEM_URL";
	
    /**
     * Gets a <code>CoordinateReferenceSystem</code> by a criteria set in the form of a Properties object. 
     * The criteria are implementation-specific, and the implementation choses which <code>CoordinateReferenceSystem</code> 
     * to return for a given set of criteria.
     * @param criteria the criteria corresponding to a Coordinate Reference System.
     * @return the Coordinate Reference System.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem(Properties criteria) throws UnsupportedCRSException;
    
    /**
     * Returns the coordinate transformation object for this
     * <code>CoordinateReferenceSystem</code>. This allows the 
     * <code>CoordinateReferenceSystem</code> to resolve 
     * conversions of coordinates between different Coordinate Reference Systems.
     * @return the coordinate transformation object
     */    
    public CoordinateTransformation getCoordinateTransformation();

}
