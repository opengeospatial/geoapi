/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;


/**
 * Exception that is thrown when an invalid enumeration lookup is performed
 * in the {@link SimpleEnumerationType} class.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @since GeoAPI 1.0
 */
public class NoSuchEnumerationException extends Exception {
    /**
     * The invalid value.
     */
    private final int value;
    
    /**
     * Constructs an exception with the given invalid value.
     *
     * @param value The invalid value.
     * @revisit Localize the error message.
     */
    public NoSuchEnumerationException(final int value) {
        super("No enumeration exists for the value " + value);
        this.value = value;
    }
    
    /**
     * Returns the invalid value.
     */
    public int getValue() {
        return value;
    }
}

