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
 * Thrown when an object references multiple CRS's (such as an aggregation of objects that each reference a single CRS) is
 * used in an operation that requires exactly one CRS. 
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class HeterogeneousCRSObjectException extends Exception {

	/**
	 * Creates an exception with the specified message.
	 *
	 * @param  message The detail message. The detail message is saved for 
	 *         later retrieval by the {@link #getMessage()} method.
	 */
	public HeterogeneousCRSObjectException(String message) {
		super(message);
	}

}
