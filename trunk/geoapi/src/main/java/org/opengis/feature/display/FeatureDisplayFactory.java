/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature.display;

// J2SE direct dependencies
import java.awt.Container;
import java.util.Properties;

// OpenGIS direct dependencies
import org.opengis.feature.display.canvas.FeatureCanvas;


/**
 * The {@code FeatureDisplayFactory} class/interface...
 * 
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface FeatureDisplayFactory {
    /**
     * @todo DOCUMENT ME.
     */
    FeatureDisplayCapabilities getCapabilities();
    
    /**
     * @todo DOCUMENT ME.
     */
    FeatureCanvas createFeatureCanvas(Properties properties, Container container);
    
    /**
     * @todo DOCUMENT ME.
     */
    FeatureCanvas createFeatureCanvas(Properties properties);
}
