/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;


/**
 * Thrown when a parameter can't be cast to the requested type. For example this exception
 * is thrown when {@link ParameterValue#doubleValue} is invoked but the value is not
 * convertible to a <code>double</code>. This exception is the only one to not extends
 * {@link IllegalArgumentException} since it is thrown when the wrong zero-argument method
 * is called rather than a wrong argument given to a method call.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see ParameterValue#intValue
 * @see ParameterValue#doubleValue
 */
public class InvalidParameterTypeException extends IllegalStateException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 2740762597003093176L;

    /**
     * The invalid parameter name.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     * @param parameterName The parameter name.
     */
    public InvalidParameterTypeException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }
}
