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

import org.opengis.spatialschema.coordinate.DirectPosition;

import com.dautelle.units.Unit;

/**
 * 
 * A <code>DerivedCRS</code> is a Coordinate Reference System
 * of type Enginnering or Image, that does not have a Datum, but instead has a 
 * "conversion" to another CoordinateReferenceSystem. This conversion is defined 
 * as a spatial offset and is measured as a DirectPosition.
 * A "relative coordinate" is a coordinate in a derived Coordinate Reference System. 
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public interface DerivedCRS extends CoordinateReferenceSystem {
		
    /**
     * Gets the type of derived Coordinate Referece System, usually either 
	 * <i>Engineering</i> or <i>Image</i>.
	 * @return the CRS type.
     */
    public String getType();
		
    /**
     * Sets the type of derived Coordinate Referece System, usually either 
     * <i>Engineering</i> or <i>Image</i>.
     * @param crs the CRS type.
     */
    public void setType(String crsType);
		
    /**
	 * Gets the offset (in terms of a "reference position") beween 
	 * the origin of this Coordinate Referece System and a reference 
	 * Coordinate Referece System. The reference position value is a 
	 * DirectPosition in the reference Coordinate Reference System.
	 * @param referenceCRS the reference Coordinate Referece System.
	 * @return the reference position in the space of the reference Coordinate Referece System.
     */
    public DirectPosition getReferencePosition(CoordinateReferenceSystem referenceCRS);
		
	/**
	 * Sets the offset (in terms of a "reference position") beween 
	 * the origin of this Coordinate Referece System and a reference 
	 * Coordinate Referece System. The reference position value is a 
	 * DirectPosition in the reference Coordinate Reference System.
	 * @param referencePosition the reference position in the space of the reference Coordinate Referece System.
	 * @param referenceCRS the reference Coordinate Referece System.
	 */		
	public void setReferencePosition(DirectPosition referencePosition, CoordinateReferenceSystem referenceCRS);
    
    
    public double[] getOrientation(Unit unit);
	public void setOrientation(double[] orientation, Unit unit);
}
