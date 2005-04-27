/*
 * $ Id $
 * $ Source $
 * Created on Mar 10, 2005
 */
package org.opengis.feature.display;

import java.awt.Container;
import java.util.Properties;

import org.opengis.feature.display.canvas.FeatureCanvas;


/**
 * The <code>FeatureDisplayFactory</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface FeatureDisplayFactory {

    /**
     * DOCUMENT ME.
     * @return
     */
    FeatureDisplayCapabilities getCapabilities();
    
    /**
     * DOCUMENT ME.
     * @param properties
     * @param container
     * @return
     */
    FeatureCanvas createFeatureCanvas(Properties properties, Container container);
    
    /**
     * DOCUMENT ME.
     * @param properties
     * @return
     */
    FeatureCanvas createFeatureCanvas(Properties properties);
}
