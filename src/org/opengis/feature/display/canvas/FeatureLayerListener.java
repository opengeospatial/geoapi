package org.opengis.feature.display.canvas;

import java.util.EventListener;

/**
 * Methods on this interface are invoked when a FeatureLayer has been modified.
 */
public interface FeatureLayerListener extends EventListener {
    /**
     * Invoked when the style has changed.
     */
    public void styleChanged(FeatureLayerEvent event);

    /**
     * Invoked when the Z-order value has changed.
     */
    public void levelChanged(FeatureLayerEvent event);
}
