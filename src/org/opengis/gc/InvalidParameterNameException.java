/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;


/**
 * Throws when an invalid parameter name was given to an operation.
 *
 * @UML exception GC_InvalidParameterName
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 */
public class InvalidParameterNameException extends IllegalArgumentException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 1541505337786911602L;

    /**
     * Creates an exception with no message.
     */
    public InvalidParameterNameException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public InvalidParameterNameException(String message) {
        super(message);
    }
}
