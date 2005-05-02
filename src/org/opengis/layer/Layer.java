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
     * Provides keywords to aid in catalogue searches.
     * 
     * @return this {@code Layer}'s KeywordList
     */
    @UML (identifier="KeywordList", specification=ISO_19128) // 7.2.4.6.4
    List<InternationalString> getKeywordList();
    
    /**
     * Provides the {@code CoordinateReferenceSystem}s available to
     * this {@code Layer}.  Implementations should inherit CRSs from
     * parent {@code Layer}s.
     * 
     * @return this {@code Layer}'s {@code CoordinateReferenceSystem}s
     */
    @UML (identifier="CRS", specification=ISO_19128) // 7.2.4.6.7
    List<CoordinateReferenceSystem> getCRSs();
    
    /**
     * Provides the BoundingBoxes that specify the coordinate ranges for
     * this {@code Layer}, as {@code Envelope}s.   Implementations should
     * inherit BoundingBoxes from parent {@code Layer}s.
     * 
     * @return this {@code Layer}'s BoundingBox {@code Envelope}s
     */
    @UML (identifier="BoundingBox", specification=ISO_19128)  // 7.2.4.6.8
    List<Envelope> getBoundingBoxes();
    
    /**
     * Provides the {@code Attribution} for this {@code Layer}, which 
     * identifies the source of the geographic information used in this
     * {@code Layer}.
     * 
     * @return this {@code Layer}'s {@code Attribution}
     */
    @UML (identifier="Attribution", specification=ISO_19128) // 7.2.4.6.12
    Attribution getAttribution();
    
    /**
     * Provides the {@code AuthorityURL}s named in this {@code Layer}'s
     * {@code Identifier}s.
     * @return this {@code Layer}'s {@code AuthorityURL}s
     */
    @UML (identifier="AuthorityURL", specification=ISO_19128) // 7.2.4.6.13
    List<AuthorityURL> getAuthorityURLs();
        
    /**
     * Provides the {@code Identifier}s containing ID numbers or labels
     * defined by a particular {@code Authority}.   
     * @return this {@code Layer}'s {@code Identifier}s
     */
    @UML (identifier="Identifier", specification=ISO_19128) // 7.2.4.6.13
    List<Identifier> getIdentifiers();
    
    /**
     * Provides the {@code MetadataURL}s that offer detailed, standardized
     * metadata about the data for this {@code Layer}.
     * 
     * @return this {@code Layer}'s {@code MetadataURL}s
     */
    @UML (identifier="MetadataURL", specification=ISO_19128) // 7.2.4.6.11
    List<MetadataURL> getMetadataURLs();
    
    DataURL[] getDataURLs();
    
    /**
     * Provides the {@code FeatureURL}s that point to a list of features 
     * represented in this {@code Layer}.
     * 
     * @return this {@code Layer}'s {@code MetadataURL}s
     */
    @UML (identifier="MetadataURL", specification=ISO_19128) // 7.2.4.6.14
    List<FeatureListURL> getFeatureListURLs();
    
    /**
     * Provides the {@code Style}s that may be requested for this {@code Layer}.
     * 
     * @return this {@code Layer}'s {@code Style}s
     */
    @UML (identifier="Style", specification=ISO_19128) // 7.2.4.6.5
    List<Style> getStyles();
        
    /**
     * @return
     */
    @UML (identifier="MinScaleDenominator", specification=ISO_19128) // 7.2.4.6.9 
    double getMinScaleDenominator();
    
    /**
     * @return
     */
    @UML (identifier="MaxScaleDenominator", specification=ISO_19128) // 7.2.4.6.9
    double getMaxScaleDenominator();

    /**
     * Gets the child {@code Layer}s of this {@code Layer}.  Typically,
     * a {@code Layer} will have either child {@code Layer}s or 
     * @return the child {@code Layer}s or an empty array
     */
    Layer[] getLayers();
    
    boolean isQueryable();
    
    /**
     * @return how many times this {@code Layer} has been cascaded
     */
    @UML (identifier="cascaded", specification=ISO_19128) // 7.2.4.7.3
    int getCascaded();
    
    /**
     * @return {@code Boolean.TRUE} if this {@code Layer}'s data is mostly opaque
     */
    @UML (identifier="opaque", specification=ISO_19128) // 7.2.4.7.4
    boolean isOpaque();
    
    /**
     * @return
     */
    @UML (identifier="noSubsets", specification=ISO_19128) // 7.2.4.7.5
    boolean isNoSubsets();
    
    /**
     * @return
     */
    @UML (identifier="fixedWidth", specification=ISO_19128) // 7.2.4.7.5
    int getFixedWidth();
    
    /**
     * @return
     */
    @UML (identifier="fixedHeight", specification=ISO_19128) // 7.2.4.7.5
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
     * {@code Layer}. The returned {@code List} should NOT be a live list of this
     * {@code Layer}'s {@code FeatureLayer}s, and modifying the list should not
     * affect this {@code Layer}'s set of {@code FeatureLayer}s.
     * 
     * @return the <code>FeatureLayer</code>s to add to a <code>FeatureCanvas</code>
     */
    List<FeatureLayer> getFeatureLayers();
    
    /**
     * Gets the <code>Graphic</code>s from this {@code Layer} that are suitable
     * for adding to a <code>Canvas</code> in order to visually represent this 
     * {@code Layer}.  The returned {@code List} should NOT be a live list of 
     * this {@code Layer}'s {@code Graphic}s, and modifying the list should not
     * affect this {@code Layer}'s set of {@code Graphic}s.
     * 
     * @return the <code>Graphic</code>s to add to a <code>Canvas</code> 
     */
    List<Graphic> getGraphics();
    
    
    
    /**
     * Whether this {@code Layer}'s renderable components should be initially rendered.
     * @return true if the {@code Layer} should be shown initially
     */
    //boolean isInitiallyVisible();
    
    // use sparingly to indicate a Layer is always on?
    // ie, for a background vectormap?
    // boolean isAlwaysVisible();
}
