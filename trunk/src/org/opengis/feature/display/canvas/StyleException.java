package org.opengis.feature.display.canvas;

/**
 * Exceptions of this type get thrown when the canvas is unable to apply a style
 * to a feature that has been added to one of its layers.
 */
public class StyleException extends Exception {
    public StyleException() {
        super((String) null);
    }

    public StyleException(Throwable cause) {
        super(cause);
    }

    public StyleException(String message) {
        super(message);
    }

    public StyleException(String message, Throwable cause) {
        super(message, cause);
    }
}
