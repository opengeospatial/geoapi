/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.crs.crs;

import java.util.Properties;

import org.opengis.crs.operation.CoordinateTransformation;

/**
 * <code>CoordinateReferenceSystemFactory</code> defines a common 
 * abstraction for implementations
 * that create <code>CoordinateReferenceSystem</code>s.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CoordinateReferenceSystemFactory {
    /**
     * @revisit What is it for?
     */
    public static String COORDINATE_REFERECE_SYSTEM_URL = "COORDINATE_REFERECE_SYSTEM_URL";

    /**
     * Gets a <code>CoordinateReferenceSystem</code> by a criteria set in the form of a <code>Properties</code> object. 
     * The criteria are implementation-specific, and the implementation choses which <code>CoordinateReferenceSystem</code> 
     * to return for a given set of criteria.
     * @param criteria the criteria corresponding to a Coordinate Reference System.
     * @return the <code>CoordinateReferenceSystem</code>.
     * @throws UnsupportedCRSException if the criteria cannot be met
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem(Properties criteria) throws UnsupportedCRSException;

    /**
     * Returns the <code>CoordinateTransformation</code> object for this
     * <code>CoordinateReferenceSystem</code>. This allows the 
     * <code>CoordinateReferenceSystem</code> to resolve 
     * conversions of coordinates between different Coordinate Reference Systems.
     * PENDING(jdc): this definition doesn't make sense...this is a Factory, not a CRS.  
     * is this method misplaced?
     * @return the coordinate transformation object
     *
     * @revisit This method should be defined in a coordinate transformation factory.
     */    
    public CoordinateTransformation getCoordinateTransformation();
}
