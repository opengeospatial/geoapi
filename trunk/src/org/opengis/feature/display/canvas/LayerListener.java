/*
 * Created on Nov 24, 2004
 */
package org.opengis.feature.display.canvas;

import java.util.EventListener;

/**
 * Methods on this interface are invoked when a Layer has been modified.
 */
public interface LayerListener extends EventListener {
    /**
     * Invoked when the style has changed.
     */
    public void styleChanged(LayerEvent event);

    /**
     * Invoked when the Z-order value has changed.
     */
    public void levelChanged(LayerEvent event);

    /**
     * Invoked when a new child Layer has been added.  The events "getNewValue"
     * method will return the new child (and "getOldValue" will return null).
     */
    public void childLayerAdded(LayerEvent event);
}
