/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer;

import org.opengis.util.InternationalString;


/**
 * The <code>Style</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface Style {

    String getName();
    
    InternationalString getTitle();
    
    InternationalString getAbstract();
    
    LegendURL[] getLegendURLs();
    
    StyleSheetURL getStyleSheetURL();
    
    StyleURL getStyleURL();
    
}
