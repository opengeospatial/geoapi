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
import org.opengis.go.display.style.GraphicStyle;
import org.opengis.sld.FeatureStyle;
import org.opengis.util.InternationalString;


/**
 * The <code>Style</code> class/interface...
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface Style {
    
    /*public static final Param NAME_PARAM = 
        new Param( "Name", String.class, "Unique name for identifying a Layer", false);
    
    public static final Param TITLE_PARAM = 
        new Param("Title", InternationalString.class, "Human-readable title for presenting a Layer", true);
    
    public static final Param ABSTRACT_PARAM = 
        new Param("Abstract", InternationalString.class, "", false);
    
    public static final Param LEGEND_URLS_PARAM = 
        new Param( "LegendURLs", LegendURL[].class, "", false);
    
    public static final Param STYLESHEET_URL_PARAM = 
        new Param("StyleSheetURL", StyleSheetURL.class, "", false);
    
    public static final Param STYLE_URL_PARAM = 
        new Param("StyleURL", StyleURL.class, "", false);
*/
    String getName();
    
    InternationalString getTitle();
    
    InternationalString getAbstract();
    
    LegendURL[] getLegendURLs();
    
    StyleSheetURL getStyleSheetURL();
    
    StyleURL getStyleURL();
    
    FeatureStyle[] getFeatureStyles();
    
    GraphicStyle[] getGraphicStyles();
    
}
