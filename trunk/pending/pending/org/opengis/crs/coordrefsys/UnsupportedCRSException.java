
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
 * Signals that the requested <code>CoordinateReferenceSystem</code> is not supported 
 * in an operation.  This may occur if, for example, if a concrete class that 
 * implements <code>DirectPosition</code> is being requested with an incompatible 
 * <code>CoordinateReferenceSystem</code>.
 */
public class UnsupportedCRSException extends RuntimeException {

	public UnsupportedCRSException() {
	   super ("CoordinateReferenceSystem is not supported in this operation.");
	}
    
	public UnsupportedCRSException(String message) {
	   super ("CoordinateReferenceSystem is not supported in this operation " + message);
	}
}
