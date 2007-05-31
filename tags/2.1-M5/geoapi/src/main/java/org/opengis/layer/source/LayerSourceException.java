/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.layer.source;

// J2SE dependencies
import java.io.IOException;


/**
 * Throws when a {@linkplain LayerSourceFactory layer source factory} failed to create a
 * {@linkplain LayerSource layer source}.
 * 
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
public class LayerSourceException extends IOException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 2279619485423536666L;

    /**
     * Creates an exception with no message.
     */
    public LayerSourceException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public LayerSourceException(final String message) {
        super(message);
    }

    /**
     * Creates an exception with the specified cause.
     *
     * @param  cause The cause for this exception. The cause is saved
     *         for later retrieval by the {@link #getCause()} method.
     */
    public LayerSourceException(final Throwable cause) {
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
    public LayerSourceException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}
