/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.crs.operation;


/**
 * Thrown when an invalid parameter name was requested in a
 * {@linkplain OperationParameterGroup parameter group}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see OperationParameterGroup#getParameter
 * @see ParameterValueGroup#getValue
 */
public class InvalidParameterNameException extends IllegalArgumentException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = -5719686835119703061L;

    /**
     * The invalid parameter name.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     * @param parameterName The invalid parameter name.
     */
    public InvalidParameterNameException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the invalid parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }
}
