/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.crs.crs;


/**
 * Signals that the requested <code>CoordinateReferenceSystem</code> is not supported 
 * in an operation.  This may occur if, for example, if a concrete class that 
 * implements <code>DirectPosition</code> is being requested with an incompatible 
 * <code>CoordinateReferenceSystem</code>.
 *
 * @revisit This exception may need to be revisited if we provide a set of interfaces
 *          similar to OGC 2001-09 instead. Furthermore, it needs to be localizable.
 */
public class UnsupportedCRSException extends RuntimeException {

	public UnsupportedCRSException() {
	   super ("CoordinateReferenceSystem is not supported in this operation.");
	}
    
	public UnsupportedCRSException(String message) {
	   super ("CoordinateReferenceSystem is not supported in this operation " + message);
	}
}
