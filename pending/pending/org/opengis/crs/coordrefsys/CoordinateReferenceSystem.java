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

import com.dautelle.units.Unit;

/**
 * <code>CoordinateReferenceSystem</code> defines a common abstraction 
 * for implementations that model Coordinate Reference Systems. 
 * <i> This class is currently a placeholder for a parallel effort 
 * that is developing a more detailed interface.</i>
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CoordinateReferenceSystem {
	
	/**
     * Returns the dimension of this CoordinateReferenceSystem.
	 * @return the dimension of this Coordinate Reference System.
	 */
	public int getDimension();
	
	/**
	 * Returns the coordinate system units of this CoordinateReferenceSystem.
	 * @return the coordinate system units of this Coordinate Reference System.
	 */
	public Unit[] getUnits();
    
    /**
     * Returns true if the CoordinateReferenceSystems are the same.
     * @param otherCrs the CoordinateReferenceSystem to compare this object to.
     * @return true if the two CoordinateReferenceSystems are the same.
     */
    public boolean equals(CoordinateReferenceSystem otherCrs);
}
