package org.opengis.feature.display.canvas;

//import java.util.Iterator;

import org.opengis.feature.FeatureType;
import org.opengis.sld.FeatureStyle;

/**
 * Abstract base class for a grouping of features drawn on a Canvas.  Every
 * <code>Layer</code> has a Z-order value that indicates the order in which
 * features are drawn on the <code>FeatureCanvas</code>.  Lower numbers draw
 * first.
 */
public interface Layer {
    /**
     * Retrieves the type of features that are drawn in this layer.
     */
    public FeatureType getFeatureType();

    /**
     * Returns the style that is applied to features in this layer when none
     * is provided by the caller of the API.
     */
    public FeatureStyle getDefaultStyle();

    /**
     * Sets the style that will be applied to features in this layer when none
     * is provided by the caller of the API.
     */
    public void setDefaultStyle(FeatureStyle style);

    /**
     * Returns a name for this layer.  This name should be human readable and
     * may be presented to the end user in a user interface.
     */
    public String getName();

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
     * Returns an iterator that ranges over all features currently in this
     * layer.  PENDING(CSD): Who would call this?  Can we remove it?
    public Iterator getFeatures();
     */
}
