/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;


/**
 * Thrown when a {@linkplain org.opengis.referencing.operation.MathTransform math transform}
 * was requested with an unknow classification name.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.0
 *
 * @see org.opengis.referencing.operation.MathTransformFactory#createParameterizedTransform
 */
public class NoSuchClassificationException extends FactoryException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 6749721413001501976L;
    
    /**
     * The classification name.
     */
    private final String classification;

    /**
     * Construct an exception with the specified detail message and classification name.
     *
     * @param  message The detail message. The detail message is saved
     *         for later retrieval by the {@link #getMessage()} method.
     * @param classification The classification name.
     */
    public NoSuchClassificationException(final String message, final String classification) {
        super(message);
        this.classification = classification;
    }
    
    /**
     * Returns the classification name.
     */
    public String getClassification() {
        return classification;
    }
}
