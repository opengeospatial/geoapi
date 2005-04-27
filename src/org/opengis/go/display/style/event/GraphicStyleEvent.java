package org.opengis.go.display.style.event;

import org.opengis.go.display.style.GraphicStyle;

/**
 * Event that contains information about a modification to a
 * <code>GraphicStyle</code> object.  Each event object contains an array of
 * strings denoting which properties have changed.
 */
public interface GraphicStyleEvent {
    /**
     * Returns the style whose property or properties changed.
     */
    public GraphicStyle getGraphicStyle();

    /**
     * Returns an array naming each of the properties that changed.  This may
     * be null if an unknown number of the properties changed.
     */
    public String [] getProperties();

    /**
     * Returns an array that lists the previous values of properties that
     * changed.  This may be null if an unknown number of the properties
     * changed.  The value at a given index in the array corresponds with the
     * property named at the same index in the array returned by getProperties.
     */
    public Object [] getOldValues();

    /**
     * Returns an array that lists the new values of properties that changed.
     * This may be null if an unknown number of the properties changed.  The
     * value at a given index in the array corresponds with the property named
     * at the same index in the array returned by getProperties.
     */
    public Object [] getNewValues();
}
