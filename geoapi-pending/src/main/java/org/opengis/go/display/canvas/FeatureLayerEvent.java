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
package org.opengis.go.display.canvas;

// J2SE direct dependencies
import java.util.EventObject;


/**
 * Instance of this event are passed to {@link FeatureLayerListener}s
 * when a property of the canvas has changed.
 *
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public class FeatureLayerEvent extends EventObject {
    /**
     * Serial version UID allowing cross compiler use of {@code FeatureLayerEvent}.
     */
    private static final long serialVersionUID = 2455929532717150118L;

    /**
     * The old value, or {@code null} in none.
     */
    private final Object oldValue;

    /**
     * The new value, or {@code null} in none.
     */
    private final Object newValue;

    /**
     * Constructs an event for a feature layer's property initialized to a value.
     *
     * @param layer The modified layer.
     * @param newValue The new value.
     */
    public FeatureLayerEvent(final FeatureLayer layer, final Object newValue) {
        this(layer, null, newValue);
    }

    /**
     * Constructs an event for a feature layer's property set to a new value.
     *
     * @param layer The modified layer.
     * @param oldValue The old value, or {@code null} in none.
     * @param newValue The new value, or {@code null} in none.
     */
    public FeatureLayerEvent(final FeatureLayer layer, final Object oldValue, final Object newValue) {
        super(layer);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * Returns the old value, or {@code null} in none.
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Returns the new value, or {@code null} in none.
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Returns the modified layer.
     */
    @Override
    public FeatureLayer getSource() {
        return (FeatureLayer) super.getSource();
    }

    /**
     * Synonym for {@link #getSource}.
     *
     * @deprecated To be replaced by {@link #getSource} in a J2SE 1.5 profile.
     */
    @Deprecated
    public FeatureLayer getLayer() {
        return (FeatureLayer) super.getSource();
    }
}
