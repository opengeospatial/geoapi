package org.opengis.feature.display.canvas;

import org.opengis.feature.Feature;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.sld.FeatureStyle;

/**
 * Represents a layer containing features that were created by the user of the
 * API (as opposed to features that were retrieved from a backing store of some
 * kind).  As with <code>FilteredLayer</code>, only features of one type can be
 * stored in a <code>CustomLayer</code>.  Unlike <code>FilteredLayer</code>,
 * the caller is free to add or remove features from this layer at will, so long
 * as they conform to the type prescribed at creation time.
 * <p>
 * If the same feature (i.e. a feature with the same primary key values) is
 * added to this layer twice, the layer will recognize the feature as a
 * duplicate and behave as though the feature were first removed then re-added.
 */
public interface CustomLayer extends Layer {
    /**
     * Adds the given feature to this layer.  If this layer is currently in a
     * map, then the next time the map repaints, the given feature will appear.
	 * <p>
	 * If the same feature (i.e. a feature with the same primary key values) is
	 * added to this layer twice, the layer will recognize the feature as a
	 * duplicate and behave as though the feature were first removed then re-added.
     *
     * @throws IllegalArgumentException Throws this exception if the feature is
     *   not of the type required by this layer.
     * @throws StyleException Throws this exception if a problem occurs while
     *   styling the given feature with the default style.  The root cause of
     *   the exception can be determined by examining the return value of the
     *   <code>getCause</code> method on the thrown exception.
     */
    public void addFeature(Feature f) throws StyleException;

    /**
     * Adds the given feature to this layer, specifying a style to use for
     * drawing the feature (instead of the default).  If this layer is currently
     * in a map, then the next time the map repaints, the given feature will
     * appear.
	 * <p>
	 * If the same feature (i.e. a feature with the same primary key values) is
	 * added to this layer twice, the layer will recognize the feature as a
	 * duplicate and behave as though the feature were first removed then re-added.
     *
     * @throws IllegalArgumentException Throws this exception if the feature is
     *   not of the type required by this layer.
     * @throws StyleException Throws this exception if a problem occurs while
     *   styling the feature with the given style.  The root cause of the
     *   exception can be determined by examining the return value of the
     *   <code>getCause</code> method on the thrown exception.
     */
    public void addFeature(Feature f, FeatureStyle style) throws StyleException; /* throws UnsupportedOperationException? */

    /**
     * Adds the given feature to this layer, specifying a ready-made graphic to
     * use instead of the default.
	 * <p>
	 * If the same feature (i.e. a feature with the same primary key values) is
	 * added to this layer twice, the layer will recognize the feature as a
	 * duplicate and behave as though the feature were first removed then re-added.
     *
     * @throws IllegalArgumentException Throws this exception if the feature is
     *   not of the type required by this layer.
     * @throws StyleException Throws this exception if a problem occurs while
     *   styling the feature with the given style.  The root cause of the
     *   exception can be determined by examining the return value of the
     *   <code>getCause</code> method on the thrown exception.
     */
    public void addFeature(Feature f, Graphic graphic) throws StyleException; /* throws UnsupportedOperationException? */

    /**
     * Removes a feature that is currently in this layer.  This will cause the
     * feature not to be drawn with the canvas next updates.
     *
     * @return Returns true if the feature was actually present in this layer.
     *   Otherwise, returns false.
     */
    public boolean removeFeature(Feature f);

    /**
     * Modifies the style that is used for the given feature.  The change will
     * be reflected the next time the canvas re-draws.  If this 
     *
     * @return Returns true if the feature was actually present in this layer.
     *   Otherwise, returns false.
     *
     * @throws StyleException Throws this exception if a problem occurs while
     *   styling the feature with the given style.  The root cause of the
     *   exception can be determined by examining the return value of the
     *   <code>getCause</code> method on the thrown exception.
     */
    public boolean modifyStyle(Feature f, FeatureStyle style) throws StyleException; /* throws UnsupportedOperationException? */

    /**
     * Overrides the rendering currently being used for the given feature.
     *
     * @return Returns true if the feature was actually present in this layer.
     *   Otherwise, returns false.
     */
    public boolean setGraphic(Feature f, Graphic g) /* throws UnsupportedOperationException? */;
}
