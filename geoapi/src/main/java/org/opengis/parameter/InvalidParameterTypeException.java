/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;


/**
 * Thrown by {@link ParameterValue} getter methods when a value can not be casted to the
 * requested type. For example this exception is thrown when {@link ParameterValue#doubleValue()}
 * is invoked but the value is not convertible to a {@code double}.
 *
 * {@note This exception is of kind <code>IllegalStateException</code> instead than
 *        <code>IllegalArgumentException</code> because it is not caused by a bad argument.
 *        It is rather a consequence of a zero-argument method invoked in a context where
 *        is it not allowed.}
 *
 * @departure extension
 *   This exception is not part of the OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @see ParameterValue#intValue()
 * @see ParameterValue#doubleValue()
 * @see ParameterValue#booleanValue()
 * @see ParameterValue#stringValue()
 * @see ParameterValue#valueFile()
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
     * @param message The detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param parameterName The parameter name.
     */
    public InvalidParameterTypeException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the parameter name.
     *
     * @return The parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }
}
