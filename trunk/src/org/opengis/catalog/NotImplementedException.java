/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.catalog;

/**
 * This Exception is thrown if a method was not implemented intentionally
 * because it does not make sense in a particular context.
 */
public class NotImplementedException extends Exception {
	/**
	 * 
	 */
	public NotImplementedException() {
		super();
	}
	/**
	 * @param message
	 */
	public NotImplementedException(String message) {
		super(message);
	}
	/**
	 * @param cause
	 */
	public NotImplementedException(Throwable cause) {
		super(cause);
	}
	/**
	 * @param message
	 * @param cause
	 */
	public NotImplementedException(String message, Throwable cause) {
		super(message, cause);
	}
}
