package org.opengis.feature.display.canvas;

import java.util.Iterator;
import java.util.List;

import org.opengis.feature.Feature;
import org.opengis.go.display.canvas.Canvas;
import org.opengis.go.display.primitive.Graphic;

/**
 * This class represents a canvas that consumes features (i.e. objects that
 * implement the <code>Feature</code> interface) and automatically turns them
 * into Graphics.  A FeatureCanvas should be able to consume Feature objects
 * from any DataStore that the user may provide.
 * <p>
 * Implementations of FeatureCanvas are free to optimize their temporary storage
 * of features in any way that they see fit.  This could simply mean holding
 * feature instances in memory, or it could mean serializing them into secondary
 * storage in order minimize their impact on main memory, or some more
 * sophisticated caching scheme.
 * <p>
 * Conceptually, a FeatureCanvas turns every Feature it draws into one or more
 * Graphic objects and adds them to a GO-1 Graphic Canvas.  However, an
 * implementation is free to optimize this by not creating actual Graphic
 * objects until they are requested.
 * <p>
 * Data is added to a canvas by creating Layers.  A layer consists of a
 * FeatureCollection, a style to use for drawing those features, and perhaps
 * some child Layers.  In order to keep its rendering up-to-date, an
 * implementation of FeatureCanvas may add feature listeners to the feature
 * collection in the layer.
 * <p>
 * In many ways, this class looks like an in-process WMS that draws Features.
 * Similar to a WMS, it has concepts of layers with styles.  Moreover, the state
 * information maintained by a FeatureCanvas is similar to the Map Context
 * information as defined in the corresponding OGC spec.  After further
 * reflection, the FeatureCanvas abstraction may need to be refactored in light
 * of these other two specs.
 */
public interface FeatureCanvas {
    /**
     * Given a Graphic that is on this canvas, returns the Feature from which
     * it was built, or null if the graphic is not on this canvas or was not
     * created as a result of an addFeature() call.
     *
     * @param graphic Find the feature instances associated with the given 
     * graphic.  <code>FeatureCanvas</code>es are required to maintain this
     * mapping of graphics to feature.  However, you must use the contained
     * GO-1 canvas to obtain instances of Graphic based on their position
     * on a canvas.
     */
    public Feature getFeatureFromGraphic(Graphic graphic);

    /**
     * Returns the feature whose graphic is located at the given location in the
     * Canvas window.
     * <p>
     * A naive implementation could implement this method by getting the
     * Graphics at that location (from the Canvas) and then call
     * getFeatureFromGraphic.  However, a more sophisticated implementation may
     * not even create Graphic instances when a Feature is added to this Canvas.
     * Instead it may defer that until absolutely necessary.
     */
    public Feature getFeatureAtPoint(int x, int y);

    /**
     * Returns the features whose graphics are located at the given location in
     * the Canvas window.
     * <p>
     * A naive implementation could implement this method by getting the
     * Graphics at that location (from the Canvas) and then repeatedly call
     * getFeatureFromGraphic.  However, a more sophisticated implementation may
     * not even create Graphic instances when a Feature is added to this Canvas.
     * Instead it may defer that until absolutely necessary.
     */
    public Iterator getFeaturesAtPoint(int x, int y);

    /**
     * Adds the given layer to the map.
     */
    public void addFeatureLayer(FeatureLayer layer);

    /**
     * Removes the given layer from being displayed on this map.
     */
    public void deleteFeatureLayer(FeatureLayer layer);

    /**
     * Returns a list containing all of the <code>FeatureLayer</code>s currently
     * known to this canvas.  Callers should not attempt to modify this list.
     * Instead, they should call <code>addLayer</code> or
     * <code>deleteLayer</code>.
     */
    public List/*<FeatureLayer>*/ getLayers();

    /**
     * Returns the Canvas onto which the Graphics will be rendered.  References to 
     * this canvas MUST NOT be held by the callers of the feature canvas API.  The 
     * feature canvas instance that provides the GO-1 canvas is expected to have the
     * freedom to control the life-cycle of the GO-1 canvas so that it may apply
     * a variety of potential optimizations to the management of canvas objects.
     *
     * @return A GO-1 canvas 
     */
    public Canvas getCanvas();
}
