/*
 * $ Id $
 * $ Source $
 * Created on Jan 12, 2005
 */
package org.opengis.layer;

import java.net.URL;

import org.opengis.util.InternationalString;


/**
 * The <code>LayerSource</code> interface provides <code>Layer</code>s to a manager.
 * <code>Layer</code>s are the base unit in a Table of Contents, which could be used to provide
 * a human-readable visualization of the data available to add to a rendered visualization.
 * A <code>LayerSource</code> should be capable of retrieving <code>Feature</code>s from given
 * <code>FeatureStore</code>s using a given <code>Filter</code> and creating the 
 * necessary <code>FeatureLayer</code> using a given <code></code>
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface LayerSource {

    /**
     * Icon representing this type of LayerSource.
     * <p>
     * Assumed to point to a 16x16 icon?
     * </p>
     * @return the icon.
     */
    URL getIcon();

    /**
     *  Display name used to communicate this LayerSource to end users.
     * @return
     */
    InternationalString getDisplayName();
    
    /** 
     * Description of this type of LayerSource.
     * @return
     */
    InternationalString getDescription();
    
    /**
     * Gets the named <code>Layer</code>.
     * @param name the id of the <code>Layer</code>.
     * @return
     */
    Layer getLayer(String name);
    
    /**
     * Returns the array of <code>Layer</code>s provided by this <code>LayerSource</code>.
     * <p/>
     * <code>Layer</code>s 
     * @return an array of <code>Layer</code>s
     */
    Layer[] getLayers();
    
}
