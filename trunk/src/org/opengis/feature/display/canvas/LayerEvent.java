package org.opengis.feature.display.canvas;

import java.util.EventObject;

/**
 * Instance of this event are passed to LayerListners when a property of the
 * canvas has changed.
 */
public class LayerEvent extends EventObject {
    private Object oldValue;
    private Object newValue;

    public LayerEvent(Layer layer, Object newValue) {
        this(layer, null, newValue);
    }

    public LayerEvent(Layer layer, Object oldValue, Object newValue) {
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

    public Layer getLayer() {
        return (Layer) getSource();
    }
}
