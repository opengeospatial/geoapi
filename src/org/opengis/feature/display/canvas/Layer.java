package org.opengis.feature.display.canvas;

import java.util.List;

import org.opengis.feature.FeatureCollection;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.sld.FeatureStyle;
import org.opengis.spatialschema.geometry.Envelope;

/**
 * Abstract base class for a grouping of features drawn on a Canvas.  The
 * members of this interface are derived from the WMS specification, version
 * 1.1.1.  This interface differs slightly from the WMS Layer element in that
 * it assumes that the data is a FeatureCollection.  Also, in this spec, each
 * <code>Layer</code> has a Z-order value that indicates the order in which
 * features are drawn on the <code>FeatureCanvas</code>.  Lower numbers draw
 * first.
 * <p>
 * The majority of the properties of a layer are not required to be mutable.
 * The only exceptions are the current style and the Z-order, which must be
 * changeable at runtime.  The list of child layers can be modified, but
 * implementations may require that the list of children be fixed once the layer
 * is displayed.
 */
public interface Layer {
    /**
     * Returns the name of this Layer.  This is a human-readable name that
     * may be presented to the user.  This may be null.
     * QUESTION: Should this become InternationalString?
     */
    public String getName();

    /**
     * Returns a terse, unique name for this layer.
     */
    public String getTitle();

    /**
     * Returns a longer description of this Layer.  This is human-readable text
     * that may, for example, be presented to the user in a panel that gives
     * details about a layer.
     * QUESTION: Should this become InternationalString?
     */
    public String getAbstract();

    /**
     * Returns a list of keywords that apply to this layer.  These may be used,
     * for example, to allow the user to find which layers in the system apply
     * a topic of interest.
     * QUESTION: Should this become List<InternationalString>?
     */
    public List/*<String>*/ getKeywordList();

    /**
     * This indicates the coordinate reference system of the geometry of the
     * features in this layer.  This can be set if either the geometry does not
     * specify a coordinate reference system or the user wishes to override the
     * coordinate reference system and treat the coordinates as though they were
     * in this one.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Returns a bounding rectangle that indcates, in latitude and longitude
     * coordinates, the extent of the features in this layer.  This can be null
     * if this in unknown or hard to calculate.
     */
    public Envelope getLatLonBoundingBox();

    /**
     * Returns a bounding rectangle that indcates, in the feature's native
     * coordinate system, the extent of the data.  This can be null if this is
     * unknown or hard to calculate.
     */
    public Envelope getBoundingBox();

    /**
     * Similar to getBoundingBox(), this method returns the extents of the data,
     * but includes vertical, spatial, and temporal extents as well.  This may
     * be null if this is unknown or hard to calculate.
     */
    public Extent getExtent();

    /**
     * Returns an identifier for this layer.
     */
    public Identifier getIdentifer();

    /**
     * Returns a list (which may be modified) of subordinate layers.
     */
    public List/*<Layer>*/ getChildLayers();

//// Elements NOT in the WMS spec follow

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
    public void addLayerListener(LayerListener ll);

    /**
     * Removes a listener that was previously added with the addLayerListener
     * method.
     */
    public void removeLayerListener(LayerListener ll);
}
