/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.util;

/**
 * Exception that is thrown when an invalid enumeration lookup is performed
 * in the <code>SimpleEnumerationType</code> class.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class NoSuchEnumerationException extends Exception {
    private int value;

    /**
     * Constructor taking an error (invalid) value.
     */
    public NoSuchEnumerationException(int value) {
		super("No enumeration exists for the value " + value);
		this.value = value;
    }

    /**
     * Accessor for the invalid value.
     * @return the invalid value.
     */
    public int getValue() {
		return value;
    }
}
