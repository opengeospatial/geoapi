/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;


/**
 * Thrown when an invalid parameter value was given to an operation.
 *
 * @UML exception GC_InvalidParameterValue
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @revisit Should we move this exception into the <code>org.opengis.crs.operation/<code> package?
 */
public class InvalidParameterValueException extends IllegalArgumentException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 3929162159301445901L;

    /**
     * Creates an exception with no message.
     */
    public InvalidParameterValueException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public InvalidParameterValueException(String message) {
        super(message);
    }
}
