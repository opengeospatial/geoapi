/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.layer;

// J2SE direct dependencies
import java.io.IOException;


/**
 * Throws when a {@linkplain org.opengis.layer.source.LayerSource layer source}
 * failed to create a {@linkplain Layer layer}.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public class LayerException extends IOException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = -1234809939315146143L;
    
    /**
     * Creates an exception with no message.
     */
    public LayerException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public LayerException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified cause.
     *
     * @param  cause The cause for this exception. The cause is saved
     *         for later retrieval by the {@link #getCause()} method.
     */
    public LayerException(final Throwable cause) {
        super();
        initCause(cause);
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     * @param  cause The cause for this exception. The cause is saved
     *         for later retrieval by the {@link #getCause()} method.
     */
    public LayerException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}
