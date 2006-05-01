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
package org.opengis.feature;

// J2SE direct dependencies
import java.io.IOException;    // For javadoc
import java.sql.SQLException;  // For javadoc


/**
 * Thrown to indicate that a {@link FeatureStore} operation could not complete because of a
 * failure in the backing store, or a failure to contact the backing store.
 *
 * @todo Since this is a {@link FeatureStore} exception should we have a backpointer
 *       to the {@code FeatureStore}? Or at least an indication of which data store
 *       caused the problem?
 * 
 * @since GeoAPI 2.0
 */
public class FeatureStoreException extends IOException {
    /**
     * Serial version UID allowing cross compiler use of {@code FeatureStoreException}.
     */
    private static final long serialVersionUID = -7266283714520657425L;

    /**
     * Constructs a new exception with no detail message.
     */
    public FeatureStoreException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message, saved for later retrieval by the {@link #getMessage} method.
     */
    public FeatureStoreException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause.
     *
     * @param cause the cause, saved for later retrieval by the {@link Throwable#getCause} method.
     */
    public FeatureStoreException(final Throwable cause) {
        super(cause==null ? null : cause.toString());
        initCause(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message, saved for later retrieval by the {@link #getMessage} method.
     * @param cause the cause, saved for later retrieval by the {@link Throwable#getCause} method.
     */
    public FeatureStoreException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}
