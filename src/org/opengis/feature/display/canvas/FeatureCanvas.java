package org.opengis.feature.display.canvas;

import java.util.Iterator;

import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.feature.FeatureTypeFactory;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.go.display.canvas.Canvas;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.sld.FeatureStyleFactory;

/**
 * <p>A canvas that can consume Features and automatically turn them into Graphics.
 * <code>FeatureCanvas</code> instances are "semi-persistent" stores of <code>Feature</code>
 * instances.  What this means, for example in a web application, that features are added to
 * a feature canvas for as long as that feature canvas exists/is associated with a user session.
 * </p>
 *
 * <p>Implementations of <code>FeatureCanvas</code> are free to optimize their temporary storage
 * of features in any way that they see fit.  This could simply mean holding feature instances in 
 * memory, or it could mean serializing them into secondary storage in order minimize their 
 * impact on main memory, or some more sophisticated caching scheme utilizing a hybrid of the
 * two techniques.</p>
 *
 * <p>Another place that a <code>FeatureCanvas</code> is free to optimize is in its managment 
 * of the GO-1 canvas to which it delegates and provides as an immutable property.  Users of
 * the <code>FeatureCanvas</code> interface should assume that the relationship to a GO-1 canvas
 * from a <code>FeatureCanvas</code> instance is 1-to-1, even though this may not be strictly true
 * in the implementation.  Implementations are free to use a pool of canvas objects, tie 
 * themselves to a single instance of a GO-1 canvas, or take any other approach, so long as they
 * can maintain the appearance of 1-to-1 mapping.  The consequence of this for application 
 * programmers is that they should always obtain a GO-1 canvas as it is needed, and never assign it
 * to an instance member.  Where it is convenient to pass some canvas as a parameter to another
 * method the <code>FeatureCanvas</code> should always be used in favor of passing the actual GO-1 
 * canvas.</p>
 *
 * <p>Data is added to a canvas by creating a subclass of <code>Layer</code>.  This is done with
 * either the <code>createCustomLayer</code> or <code>createFilteredLayer</code> method.
 * If a <code>CustomLayer</code> is created, then the caller is responsible for populating the
 * features in this layer.  If a <code>FilteredLayer</code> is created, then the canvas will
 * automatically retrieve all of the "known" features of the given type that satisfy the filter that
 * was used to create the layer.</p>
 *
 * <p>Some feature types provided by a canvas may be "read-only", i.e. the user of the API cannot
 * create new <code>Feature</code> instances of that type.  Such layers may represent, for example,
 * connections to read only data stores.</p>
 *
 * @author Chris Dillard, Jake Fear
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

    /*
     * Returns a Graphic corresponding to the given Feature or null if the
     * given feature is not in this Canvas.  Note that the returned graphic
     * may be an aggregate.
     * PENDING(CSD): This is problematic now since a feature could be in more
     * than one layer.  Who would call this, any way?  Can we remove it?
     *
     * @param feature A feature whose Graphic instances are desired.
     *
    public Graphic getGraphicFromFeature(Feature feature);
     */

    /**
     * Creates a new instance of <code>CustomLayer</code> and immediately
     * attaches it to this canvas.  The returned <code>CustomLayer</code> can be
     * used to add features of the given type to the canvas.
     *
     * @param name A human-friendly name to give to this layer.
     * @param featureType The type of features that can be added to this layer.
     */
    public CustomLayer createCustomLayer(String name, FeatureType featureType);

    /**
     * Creates a new <code>FilteredLayer</code> that retrieves features of the
     * given type.
     *
     * @param name A human-friendly name to give to this layer.
     * @param featureType The type of features that will be in this layer.
     * @param filter A filter limiting which features will be in this layer.
     *   This parameter may be null if all features are desired.
     */
    public FilteredLayer createFilteredLayer(String name, FeatureType featureType, Filter filter);

    /**
     * Removes the given layer from being displayed on this map.  After calling
     * this method, the layer object is no longer valid and any calls to its
     * methods may produce unexpected results.
     */
    public void deleteLayer(Layer layer);

    /**
     * Returns an array containing all of the <code>Layer</code>s currently
     * known to this canvas.
     */
    public Layer [] getLayers();

    /**
     * Returns a factory with which new FeatureTypes can be registered and
     * existing feature types can be retrieved.
     */
    public FeatureTypeFactory getFeatureTypeFactory();

    /**
     * Retrieves a factory from which style objects can be built.
     */
    public FeatureStyleFactory getStyleFactory();

    /**
     * Retrieves a factory from which Filter and Expression objects can be
     * built.
     */
    public FilterFactory getFilterFactory();

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
