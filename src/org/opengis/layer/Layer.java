/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.layer;

// OpenGIS direct dependencies
import org.opengis.feature.display.canvas.FeatureCanvas;
import org.opengis.feature.display.canvas.FeatureLayer;
import org.opengis.go.display.canvas.Canvas;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * The {@code Layer} interface organizes the basic GO-1 constructs that
 * may be added to {@link FeatureCanvas} or {@link Canvas}.  A GO-1
 * application may be directed to "add" a {@code Layer}; it should then
 * add the {@code Layer}'s {@link FeatureLayer} and 
 * {@link Graphic}s to the respective canvases.
 * <p>
 * It is currently assumed that the {@code Layer} interface will prove
 * analogous to the WMS concept of Layer, and will soon be an implementation
 * of said.
 * </p>
 *
 * @author ISO/DIS 7.2.4.5 Layers and styles
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
@UML (identifier="Layer", specification=ISO_19128)
public interface Layer {    
    /**
     * Provides a unique name for identifying this {@code Layer}.
     *
     * @return the unique String identifier for this {@code Layer}
     */
    @UML (identifier="Name", specification=ISO_19128) // 7.2.4.6.3
    String getName();
    
    /**
     * Provides the human-readable String for presenting this {@code Layer}.
     *
     * @return the human-readable Title for this {@code Layer} 
     */
    @UML (identifier="Title", specification=ISO_19128) // 7.2.4.6.2
    InternationalString getTitle();
    
    /** 
     * Provides the narrative description of this {@code Layer}.
     *
     * @return the narrative description of this {@code Layer}
     */
    @UML (identifier="Abstract", specification=ISO_19128) // 7.2.4.6.4
    InternationalString getAbstract();
    
    /**
     * @revisit DOCUMENT ME.
     */
    @UML (identifier="KeywordList", specification=ISO_19128) // 7.2.4.6.4
    InternationalString[] getKeywordList();
    
    /**
     * @revisit DOCUMENT ME.
     */
    CoordinateReferenceSystem[] getCRSs();
    
    Envelope[] getBoundingBoxes();
    
    Attribution getAttribution();
    
    AuthorityURL[] getAuthorityURLs();
        
    Identifier[] getIdentifiers();
    
    MetadataURL[] getMetadataURLs();
    
    DataURL[] getDataURLs();
    
    FeatureListURL[] getFeatureListURLs();
    
    Style[] getStyles();
    
    double getMinScaleDenominator();
    
    double getMaxScaleDenominator();

    /**
     * Gets the child {@code Layer}s of this {@code Layer}.  Typically,
     * a {@code Layer} will have either child {@code Layer}s or 
     * @return the child {@code Layer}s or an empty array
     */
    Layer[] getLayers();
    
    boolean isQueryable();
    
    int getCascaded();
    
    boolean isOpaque();
    
    boolean isNoSubsets();
    
    int getFixedWidth();
    
    int getFixedHeight();
    
    //*************************************************************************
    //  'work' methods?
    //*************************************************************************
    
    // parent access
    
    /**
     * Gets the parent <code>LayerSource</code> that produced this {@code Layer}.
     * This should assist in serialization.
     * @return the parent <code>LayerSource</code> that produced this {@code Layer}
     */
    //LayerSource getLayerSource();

    // 'renderable' access
    
    /**
     * Gets the <code>FeatureLayer</code> from this {@code Layer} that is suitable
     * for adding to a <code>FeatureCanvas</code> in order to visually represent this
     * {@code Layer}.
     * @return the <code>FeatureLayer</code> to add to a <code>FeatureCanvas</code> or
     *         null
     */
    FeatureLayer[] getFeatureLayers();
    
    /**
     * Gets the <code>Graphic</code>s from this {@code Layer} that are suitable
     * for adding to a <code>Canvas</code> in order to visually represent this 
     * {@code Layer}.
     * @return the <code>Graphic</code>s to add to a <code>Canvas</code> or an empty array.
     */
    Graphic[] getGraphics();
    
    
    
    /**
     * Whether this {@code Layer}'s renderable components should be initially rendered.
     * @return true if the {@code Layer} should be shown initially
     */
    //boolean isInitiallyVisible();
    
    // use sparingly to indicate a Layer is always on?
    // ie, for a background vectormap?
    // boolean isAlwaysVisible();
}
