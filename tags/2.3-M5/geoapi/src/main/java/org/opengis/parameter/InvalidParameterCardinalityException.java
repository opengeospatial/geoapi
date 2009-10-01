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

import java.util.List;


/**
 * Throws if adding a {@linkplain ParameterValue parameter value} to a
 * {@linkplain ParameterValueGroup group} would result in more parameters
 * than the {@linkplain ParameterDescriptor#getMaximumOccurs maximum occurence}
 * allowed. This operation may be throws during {@linkplain List#add} or
 * {@linkplain List#remove} operation on the list returned by
 * {@link ParameterValueGroup#values}.
 *
 * {@note This exception is of kind <code>IllegalStateException</code> instead than
 *        <code>IllegalArgumentException</code> because it is not caused by a bad argument.
 *        It is rather a consequence of an <code>ParameterValueGroup</code> being "full".}
 *
 * @departure extension
 *   This exception is not part of the OGC specification.
 *
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.0
 *
 * @see ParameterValueGroup#values
 * @see ParameterDescriptor#getMinimumOccurs
 * @see ParameterDescriptor#getMaximumOccurs
 */
public class InvalidParameterCardinalityException extends IllegalStateException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 4030549323541812311L;

    /**
     * The name of the parameter with invalid cardinality.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message The detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param parameterName The name of the parameter with invalid cardinality.
     */
    public InvalidParameterCardinalityException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the parameter with invalid cardinality.
     *
     * @return The name of the parameter with invalid cardinality.
     */
    public String getParameterName() {
        return parameterName;
    }
}
