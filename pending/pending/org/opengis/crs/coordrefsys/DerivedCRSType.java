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

/**
 * 
 * A <code>DerivedCRSType</code> is specifies the allowable types of <code>CoordinateReferencSystem</code>s 
 * that a <code>DerivedCRS</code> can be derived from.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class DerivedCRSType {
	
	/**
	 * Denotes a an Engineering CRS.
	 */
	public static final DerivedCRSType ENGINEERING = new DerivedCRSType("ENGINEERING");
	
	/**
	 * Denotes a an Image CRS.
	 */
	public static final DerivedCRSType IMAGE = new DerivedCRSType("IMAGE");
	
	/**
	 * Denotes a an Temporal CRS.
	 */
	public static final DerivedCRSType TEMPORAL = new DerivedCRSType("TEMPORAL");
	
	/**
	 * Denotes a an Vertical CRS.
	 */
	public static final DerivedCRSType VERTICAL = new DerivedCRSType("VERTICAL");
	
	/**
	 * Denotes all the types of CoordinateReferenceSystems.
	 */
	public static DerivedCRSType[] TYPES = {ENGINEERING, IMAGE, TEMPORAL, VERTICAL};
	
	
	private String type;
	
	/**
	 * Constructor.
	 * @param type the type of CoordinateReferenceSystem.
	 */
	protected DerivedCRSType(String type){
		this.type = type;
	}
	
	/**
	 * Returns the type of CoordinateReferenceSystem.
	 * @return the type of CoordinateReferenceSystem.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns all the types of CoordinateReferenceSystems.
	 * @return an array of the possible types of CoordinateReferenceSystems.
	 */
	public DerivedCRSType[] getTypes() {
		return TYPES;
	}

}
