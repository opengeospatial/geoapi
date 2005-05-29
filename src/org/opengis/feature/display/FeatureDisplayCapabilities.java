/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature.display;


/**
 * The {@code FeatureDisplayCapabilities} class/interface...
 * 
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 1.1
 */
public interface FeatureDisplayCapabilities {

    Class[] getSupportedFeatureCanvases();
    
}
