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

import org.opengis.spatialschema.DirectPosition;

import javax.units.Unit;

/**
 * 
 * A <code>DerivedCRS</code> is a <code>CoordinateReferenceSystem</code>
 * of type Enginnering or Image, that does not have a <code>Datum</code>, but instead has a 
 * "conversion" to another <code>CoordinateReferenceSystem</code>. This conversion is defined 
 * as a spatial offset and is measured as a <code>DirectPosition</code>.
 * A "relative coordinate" is a coordinate in a <code>DerivedCRS</code>. 
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface DerivedCRS extends CoordinateReferenceSystem {
		
    /**
     * Gets the type of this <code>DerivedCRS</code>, usually either 
	 * <i>Engineering</i> or <i>Image</i>.
	 * @return the <code>DerivedCRS</code> type.
     */
    public String getType();
		
    /**
     * Sets the type of this <code>DerivedCRS</code>, usually either 
     * <i>Engineering</i> or <i>Image</i>.
     * @param crs the new <code>DerivedCRS</code> type.
     */
    public void setType(String crsType);
		
    /**
	 * Gets the offset (in terms of a "reference position") beween 
	 * the origin of this <code>CoordinateReferenceSystem</code> and a reference 
	 * <code>CoordinateReferenceSystem</code>. The reference position value is a 
	 * <code>DirectPosition</code> in the reference <code>CoordinateReferenceSystem</code>.
	 * @param referenceCRS the reference <code>CoordinateReferenceSystem</code>.
	 * @return the reference position in the space of the reference <code>CoordinateReferenceSystem</code>m.
     */
    public DirectPosition getReferencePosition(CoordinateReferenceSystem referenceCRS);
		
	/**
	 * Sets the offset (in terms of a "reference position") beween 
	 * the origin of this <code>CoordinateReferenceSystem</code> and a reference 
	 * <code>CoordinateReferenceSystem</code>. The reference position value is a 
	 * <code>DirectPosition</code> in the reference <code>CoordinateReferenceSystem</code>.
	 * @param referencePosition the reference position in the space of the reference <code>CoordinateReferenceSystem</code>.
	 * @param referenceCRS the reference <code>CoordinateReferenceSystem</code>.
	 */		
	public void setReferencePosition(DirectPosition referencePosition, CoordinateReferenceSystem referenceCRS);
    
    /**
     * Gets the orientation vectors of this <code>DerivedCRS</code>.
     * PENDING(jdc): is this a correct javadoc?  
     * @param unit the <code>Unit</code> of measure for the returned orientation values
     * @return an array of orientation vectors
     */
    public double[] getOrientation(Unit unit);
    
    /**
     * Sets the orientation vectors of this <code>DerivedCRS</code>.
     * PENDING(jdc): is this a correct javadoc?  
     * @param orientation the new array of orientation vectors
     * @param unit the <code>Unit</code> of the orientation vectors
     */
	public void setOrientation(double[] orientation, Unit unit);
    
}
