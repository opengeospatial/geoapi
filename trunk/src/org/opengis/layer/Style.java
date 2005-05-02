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

import java.util.List;

// OpenGIS direct dependencies
import org.opengis.go.display.style.GraphicStyle;
import org.opengis.sld.FeatureStyle;
import org.opengis.util.InternationalString;


/**
 * The <code>Style</code> interface encapsulates the information necessary to 
 * style the renderable data for a given {@code Layer}.
 * 
 * @author ISO_19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface Style {
    
    /**
     * Provides a unique name for identifying this {@code Style}.
     * @return this {@code Style}'s name
     */
    String getName();
    
    /**
     * Provides the human-readable string for presenting this {@code Style}.
     * @return this {@code Style}'s title
     */
    InternationalString getTitle();
    
    /**
     * Provides the narrative description of this {@code Style}.
     * @return this {@code Style}'s narrative description
     */
    InternationalString getAbstract();
    
    /**
     * Provides the linkage to an image of a map legend appropriate to this {@code Style}.
     * @return the linkage to this {@code Style}'s map legend image
     */
    List<LegendURL> getLegendURLs();
    
    /**
     * Provides the linkage to symbology information for this {@code Style}.
     * @return the linkage to this {@code Style}'s symbology information
     */
    StyleSheetURL getStyleSheetURL();
    
    /**
     * Provides the linkage to more information about the data or symbology 
     * underlying this {@code Style}.
     * @return the linkage to more info about this {@code Style}'s data or symbology
     */
    StyleURL getStyleURL();
    
    /**
     * Gets the {@code FeatureStyle}s used to style the {@code FeatureLayer}s used
     * by a {@code Layer} using this {@code Style}.  The returned {@code List} should
     * NOT be live, and modifying it should not affect this {@code Style}'s set
     * of {@code FeatureStyle}s.
     * @return this {@code Style}'s {@code FeatureStyle}s
     */
    List<FeatureStyle> getFeatureStyles();
    
    /**
     * Gets the {@code GraphicStyle}s used to style the {@code Graphic}s used by a 
     * {@code Layer} using this {@code Style}.  The returned {@code List} should NOT
     * be live, and modifying it should not affect this {@code Style}'s set of 
     * {@code GraphicStyle}s.
     * @return this {@code Style}'s {@code GraphicStyle}s
     */
    List<GraphicStyle> getGraphicStyles();
    
}
