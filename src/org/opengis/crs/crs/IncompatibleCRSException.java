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

/**
 * Thrown when an object that references a CRS is used in a manner inconsistent with that particular CRS. 
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class IncompatibleCRSException extends Exception {
	/**
	 * The invalid CRS name.
	 */
	private final String crsName;

	/**
	 * Creates an exception with the specified message and CRS name.
	 *
	 * @param  message The detail message. The detail message is saved for 
	 *         later retrieval by the {@link #getMessage()} method.
	 * @param crsName The invalid CRS name.
	 */
	public IncompatibleCRSException(String message, String crsName) {
		super(message);
		this.crsName = crsName;
	}

	/**
	 * Returns the invalid CRS name.
	 */
	public String getCRSName() {
		return crsName;
	}
}
