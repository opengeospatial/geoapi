package org.opengis.feature.display.canvas;

import org.opengis.feature.FeatureCollection;
import org.opengis.sld.FeatureStyle;

/**
 * Abstract base class for a grouping of features drawn on a Canvas.  Each
 * instance of this interface has a reference to a collection of features to
 * draw and a style to draw those features with.  Also, each
 * <code>FeatureLayer</code> has a Z-order value that indicates the order in
 * which features are drawn on the <code>FeatureCanvas</code>.  Lower numbers
 * draw first.
 * <p>
 * The current style and the Z-order are changeable at runtime, so consumers of
 * <code>FeatureLayer</code> objects must add listeners to know if they have
 * been changed.
 */
public interface FeatureLayer {
    /**
     * Returns the collection of features that will be portrayed in this layer.
     * A null value is allowed and indicates that nothing will be drawn.
     */
    public FeatureCollection getFeatureCollection();

    /**
     * Returns the style to apply to features in this layer.  A null value is
     * allowed, and indicates that the features should not be drawn.
     */
    public FeatureStyle getStyle();

    /**
     * Sets the style that will be applied to features in this layer.
     */
    public void setStyle(FeatureStyle style);

    /**
     * Returns the minimum z-order level that this layer can be moved to.
     */
    public double getMinimumLevel();

    /**
     * Returns the maximum z-order level that this layer can be moved to.
     */
    public double getMaximumLevel();

    /**
     * Sets the current z-order level of this layer.
     *
     * @throws IllegalArgumentException If the level is outside the allowable
     *   range.
     */
    public void setLevel(double level) throws IllegalArgumentException;

    /**
     * Returns the current z-order level of this layer.
     */
    public double getLevel();

    /**
     * Allows an object to register for events when one of the mutable
     * properties of this layer has changed.  A FeatureCanvas may, for example,
     * use this to receive notification when the style has changed.
     */
    public void addFeatureLayerListener(FeatureLayerListener ll);

    /**
     * Removes a listener that was previously added with the addLayerListener
     * method.
     */
    public void removeFeatureLayerListener(FeatureLayerListener ll);
}
