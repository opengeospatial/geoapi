package org.opengis.go.display.style.event;

/**
 * Interface to be implemented by application code that wishes to be notified
 * when a modification has been made to one of the properties of a
 * <code>GraphicStyle</code>.
 */
public interface GraphicStyleListener {
    public void styleChanged(GraphicStyleEvent event);
}
