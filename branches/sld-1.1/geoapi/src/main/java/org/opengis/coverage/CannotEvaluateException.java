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
package org.opengis.coverage;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;  // For Javadoc


/**
 * The base class for exceptions thrown when a quantity can't be evaluated.
 * This exception is usually invoked by a
 * <code>Coverage.{@linkplain Coverage#evaluate(DirectPosition, double[]) evaluate}(&hellip;)</code>
 * method, for example when a point is outside the coverage.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see Coverage#evaluate(DirectPosition, byte[])
 * @see Coverage#evaluate(DirectPosition, double[])
 *
 * @deprecated No replacement.
 */
public class CannotEvaluateException extends RuntimeException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 506793649975583062L;

    /**
     * Creates an exception with no message.
     */
    public CannotEvaluateException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public CannotEvaluateException(String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     * @param  cause The cause for this exception. The cause is saved
     *         for later retrieval by the {@link #getCause()} method.
     */
    public CannotEvaluateException(String message, Throwable cause) {
        super(message, cause);
    }
}
