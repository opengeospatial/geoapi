/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// OpenGIS dependencies
import org.opengis.metadata.Identifier;   // For javadoc

/**
 * Thrown when a {@linkplain org.opengis.referencing.operation.MathTransform math transform}
 * as been requested with an unknow {@linkplain org.opengis.referencing.operation.OperationMethod
 * operation method} identifier.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @since 1.0
 *
 * @see org.opengis.referencing.operation.MathTransformFactory#createParameterizedTransform
 */
public class NoSuchIdentifierException extends FactoryException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = -6846799994429345902L;
    
    /**
     * The {@linkplain Identifier#getCode identifier code}.
     */
    private final String identifier;

    /**
     * Construct an exception with the specified detail message and classification name.
     *
     * @param  message The detail message. The detail message is saved
     *         for later retrieval by the {@link #getMessage()} method.
     * @param identifier {@linkplain Identifier#getCode identifier code}.
     */
    public NoSuchIdentifierException(final String message, final String identifier) {
        super(message);
        this.identifier = identifier;
    }
    
    /**
     * Returns the {@linkplain Identifier#getCode identifier code}.
     */
    public String getIdentifierCode() {
        return identifier;
    }
}
