/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature.display;

// J2SE direct dependencies
import java.awt.Container;
import java.util.Properties;

// OpenGIS direct dependencies
import org.opengis.feature.display.canvas.FeatureCanvas;


/**
 * The <code>FeatureDisplayFactory</code> class/interface...
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface FeatureDisplayFactory {
    /**
     * @revisit DOCUMENT ME.
     */
    FeatureDisplayCapabilities getCapabilities();
    
    /**
     * @revisit DOCUMENT ME.
     */
    FeatureCanvas createFeatureCanvas(Properties properties, Container container);
    
    /**
     * @revisit DOCUMENT ME.
     */
    FeatureCanvas createFeatureCanvas(Properties properties);
}
