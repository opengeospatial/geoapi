package org.opengis.feature.display.canvas;

import java.util.EventObject;

/**
 * Instance of this event are passed to LayerListners when a property of the
 * canvas has changed.
 */
public class FeatureLayerEvent extends EventObject {
    private Object oldValue;
    private Object newValue;

    public FeatureLayerEvent(FeatureLayer layer, Object newValue) {
        this(layer, null, newValue);
    }

    public FeatureLayerEvent(FeatureLayer layer, Object oldValue, Object newValue) {
        super(layer);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public FeatureLayer getLayer() {
        return (FeatureLayer) getSource();
    }
}
