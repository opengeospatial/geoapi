/*
 * Created on Aug 3, 2004
 */
package org.opengis.display.canvas;

import org.opengis.feature.Feature;
import org.opengis.feature.FeatureTypeFactory;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.sld.FeatureTypeStyle;
import org.opengis.sld.FeatureTypeStyleFactory;

/**
 * <p>A canvas that can consume Features and automatically turn them into Graphics.
 * <code>FeatureCanvas</code> instances are "semi-persistent" stores of <code>Feature</code>
 * instances.  What this means, for example in a web application, that features are added to
 * a feature canvas for as long as that feature canvas exists/is associated with a user session.
 * </p>
 * <p>Implementations of <code>FeatureCanvas</code> are free to optimize their temporary storage
 * of features in any way that they see fit.  This could simply mean holding feature instances in 
 * memory, or it could mean serializing them into secondary storage in order minimize their 
 * impact on main memory, or some more sophisticated caching scheme utilizing a hybrid of the
 * two techniques.</p>
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
 * canvas.
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
     * graphic.  <code>FeatureCanvas</code> are required to maintain this 
     * mapping of graphics to feature.  However, you must use the contained
     * GO-1 canvas to obtain instances of Graphic based on their position
     * on a canvas.
     */
    public Feature getFeatureFromGraphic(Graphic g);

    /**
     * Returns an array containing the Graphics that were constructed for the
     * given Feature or null if the given feature is not in this Canvas.
     *
     * @param feature A feature whose Graphic instances are desired.
     */
    public Graphic [] getGraphicsFromFeature(Feature feature);

    /**
     * Adds the given feature to this canvas by creating a Graphic for it.  If
     * this feature's type contains an attribute of type STYLE and this value is
     * not null, then this attribute will be used to style the Feature.  If the
     * feature type does not include a STYLE attribute, or if the value of the
     * STYLE attribute is null, then the default style for the feature type (as
     * returned by FeatureType.getDefaultStyle()) will be used.  If the default
     * style is not specified by the FeatureType, then the canvas will use its
     * own internal defaults.
     * <p>
     * If the Feature has child features, the Canvas is obligated to retrieve
     * them as well and add them to the Canvas.  This process should be repeated
     * recursively so that all descendants of this Feature are shown on the map
     * after this call has completed.
     *
     * @param feature The feature that is being added to the canvas.
     */
    public void addFeature(Feature feature);

    /**
     * Adds the given feature to this canvas by creating a Graphic for it using
     * the given style.  The passed style will override a STYLE attribute, if
     * the feature has it.  It will also override the FeatureType default and
     * the map's internal default.
     * 
     * PENDING(CSD): Document that if the style object changes, then next time
     * this canvas renders, it will reflect that change.
     * <p>
     * See the addFeature(Feature) method for a description of how all of the
     * addFeature(...) methods treat child Features.
     *
     * @param feature The feature (and its children) to be added.
     *
     * @param style The style that is to be specific to this individual feature.
     */
    public void addFeature(Feature f, FeatureTypeStyle style);

    /**
     * Adds the given Graphic to the map and associates it with the given
     * feature as though this map had created the Graphic itself.  This method
     * is discouraged, but may be necessary when a map supports non-standard
     * Graphics that could not be created using a Style.
     * <p>
     * See the addFeature(Feature) method for a description of how all of the
     * addFeature(...) methods treat child Features.
     */
    public void addFeature(Feature feature, Graphic graphic);

    /**
     * Removes the specified feature from this <code>FeatureCanvas</code> object.  
     * This implies that the graphics for this feature will also be removed from the
     * GO-1 canvas associated with this <code>FeatureCanvas</code>.
     */
    public void removeFeature(Feature feature);

    /**
     * Causes the given Feature's Graphics to be reconstructed based on the
     * given style.  If the style parameter is null, then the Feature is
     * rendered as though it were added with the one parameter addFeature
     * method.
     */
    public void modifyStyle(Feature feature, FeatureTypeStyle style);

    /**
     * Returns a factory with which new FeatureTypes can be registered and
     * existing feature types can be retrieved.
     */
    public FeatureTypeFactory getFeatureTypeFactory();

    /**
     * Retrieves a factory from which style objects can be built.
     */
    public FeatureTypeStyleFactory getStyleFactory();

    /**
     * This method returns a style that can be used for features of this type
     * when no other style is provided by the application.  This method may
     * return null if the FeatureType does not provide a default style.
     * If the return value changes, it is the application programmers
     * responsibility to ensure that the map behaves appropriately.  (A given
     * canvas will probably advertise how it caches styles for Features, if at
     * all.)
     *
     * @param featureTypeName The name of the feature type for which the feature
     * type is desired.
     */
    public FeatureTypeStyle getDefaultStyleForType(String featureTypeName);

    /**
     * Sets the default style for a given feature type.  This style will be used
     * when no style is provided by the caller of addFeature and when the
     * feature itself has no style attribute.  Setting the default style to null
     * clears the default for this feature type and lets the map default style
     * get used.
     *
     * @param featureTypeName The name of the feature type for which the style is to 
     * be reset to a new value.
     *
     * @param style The new style object to be used as the default for the specified
     * feature type.
     */
    public void setDefaultStyleForType(String featureTypeName, FeatureTypeStyle style);

    /**
     * Returns the Canvas onto which the Graphics will be rendered.  References to 
     * this canvas MUST not be held by the callers of the feature canvas API.  The 
     * feature canvas instance that provides the GO-1 canvas is expected to have the
     * freedom to control the life-cycle of the GO-1 canvas so that it may apply
     * a variety of potential optimizations to the management of canvas objects.
     *
     * @return A GO-1 canvas 
     */
    public Canvas getCanvas();
}
