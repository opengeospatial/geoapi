/*
 * $ Id $
 * $ Source $
 * Created on Jan 12, 2005
 */
package org.opengis.layer;

import java.net.URL;

import org.opengis.feature.display.canvas.FeatureLayer;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.util.InternationalString;


/**
 * The <code>Layer</code> interface organizes the basic GO-1 constructs that
 * may be added to <code>FeatureCanvas</code> or <code>Canvas</code>.  A GO-1
 * application may be directed to "add" a <code>Layer</code>; it should then
 * add the <code>Layer</code>'s <code>FeatureLayer</code> and 
 * <code>Graphic</code>s to the respective canvases.  
 * 
 * It is currently assumed that the <code>Layer</code> interface will prove
 * analogous to the WMS concept of Layer, and will soon be an implementation
 * of said 
 * 
 * See ISO/DIS 7.2.4.5 Layers and styles
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface Layer {
    
    // WMS properties?
    
    /**
     * Provides a unique name for identifying this <code>Layer</code>.
     * See ISO/DIS 19128 7.2.4.6.3 Name.  
     * @return the unique String identifier for this <code>Layer</code>
     */
    String getName();
    
    /**
     * Provides a URL to an Icon suitable for use in a ToC or Legend.
     * See ISO/DIS 19128 7.2.4.6.12 Attribution -> LogoURL -> OnlineResource
     * @return the URL of an Icon logo for this <code>Layer</code>
     */
    URL getIcon();
    
    /**
     * Provides the human-readable String for presenting this <code>Layer</code>.
     * See ISO/DIS 19128 7.2.4.6.2 Title
     * @return the human-readable Title for this <code>Layer</code> 
     */
    InternationalString getDisplayName();
    
    /** 
     * Provides the narrative description of this <code>Layer</code>.
     * See ISO/DIS 19128 7.2.4.6.4 Abstract and KeywordList
     * @return the narrative description of this <code>Layer</code>
     */
    InternationalString getDescription();
    
    /*List getKeywordList();
    
    CoordinateReferenceSystem getCRS();
    
    Envelope getBoundingBox();
    
    URL getAttribution_LogoURL_OnlineResource();
    
    URL getAuthorityURL();
    
    Identifier getIdentifier();
    
    URL getMetadataURL();
    
    URL getDataURL();
    
    URL getFeatureListURL();
    
    double getMinScale();
    
    double getMaxScale();*/
    
    /**
     * Gets the child <code>Layer</code>s of this <code>Layer</code>.  Typically,
     * a <code>Layer</code> will have either child <code>Layer</code>s or 
     * @return the child <code>Layer</code>s or an empty array
     */
    Layer[] getLayers();
    
    // parent access
    
    /**
     * Gets the parent <code>LayerSource</code> that produced this <code>Layer</code>.
     * This should assist in serialization.
     * @return the parent <code>LayerSource</code> that produced this <code>Layer</code>
     */
    LayerSource getLayerSource();

    // 'renderable' access
    
    /**
     * Gets the <code>FeatureLayer</code> from this <code>Layer</code> that is suitable
     * for adding to a <code>FeatureCanvas</code> in order to visually represent this
     * <code>Layer</code>.
     * @return the <code>FeatureLayer</code> to add to a <code>FeatureCanvas</code> or
     *         null
     */
    FeatureLayer getFeatureLayer();
    
    /**
     * Gets the <code>Graphic</code>s from this <code>Layer</code> that are suitable
     * for adding to a <code>Canvas</code> in order to visually represent this 
     * <code>Layer</code>.
     * @return the <code>Graphic</code>s to add to a <code>Canvas</code> or an empty array.
     */
    Graphic[] getGraphics();
    
    /**
     * Whether this <code>Layer</code>'s renderable components should be initially rendered.
     * @return true if the <code>Layer</code> should be shown initially
     */
    boolean isInitiallyVisible();
    
    // use sparingly to indicate a Layer is always on?
    // ie, for a background vectormap?
    // boolean isAlwaysVisible();
    
}
