/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;

/**
 * Thrown when an operation is applied in a manner inconsistent with one or both of two particular CRS objects. 
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class IncompatibleOperationException extends Exception {

	/**
	 * The invalid Operation name.
	 */
	private final String operationName;
	
	/**
	 * Creates an exception with the specified message and Operation name.
	 *
	 * @param  message The detail message. The detail message is saved for 
	 *         later retrieval by the {@link #getMessage()} method.
	 * @param crsName The invalid CRS name.
	 */
	public IncompatibleOperationException(String message, String operationName) {
		super(message);
		this.operationName = operationName;
	}

	/**
	 * Returns the invalid Operation name.
	 */
	public String getOperationName() {
		return operationName;
	}
}
