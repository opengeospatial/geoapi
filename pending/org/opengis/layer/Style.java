/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer;

import org.opengis.go.display.style.GraphicStyle;
import org.opengis.sld.FeatureStyle;
import org.opengis.util.InternationalString;


/**
 * The <code>Style</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
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
