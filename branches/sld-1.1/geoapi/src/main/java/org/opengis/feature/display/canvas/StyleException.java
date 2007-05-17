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
package org.opengis.feature.display.canvas;


/**
 * Thrown when the canvas is unable to apply a style
 * to a feature that has been added to one of its layers.
 * 
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public class StyleException extends Exception {
    /**
     * Serial version UID allowing cross compiler use of {@code StyleException}.
     */
    private static final long serialVersionUID = 5298957346728112618L;

    /**
     * Constructs a new exception with no detail message.
     */
    public StyleException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message, saved for later retrieval by the {@link #getMessage} method.
     */
    public StyleException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause.
     *
     * @param cause the cause, saved for later retrieval by the {@link Throwable#getCause} method.
     */
    public StyleException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message, saved for later retrieval by the {@link #getMessage} method.
     * @param cause the cause, saved for later retrieval by the {@link Throwable#getCause} method.
     */
    public StyleException(String message, Throwable cause) {
        super(message, cause);
    }
}
