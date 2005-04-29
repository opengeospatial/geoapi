/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.layer.source;

// J2SE direct dependencies
import java.net.URL;

// OpenGIS direct dependencies
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureStore;
import org.opengis.feature.display.canvas.FeatureLayer;
import org.opengis.filter.Filter;
import org.opengis.layer.Layer;
import org.opengis.util.InternationalString;


/**
 * Provides {@link Layer}s to a manager.
 * {@code Layer}s are the base unit in a Table of Contents, which could be used to provide
 * a human-readable visualization of the data available to add to a rendered visualization.
 * A {@code LayerSource} should be capable of retrieving {@linkplain Feature features} from
 * given {@linkplain FeatureStore feature stores} using a given {@linkplain Filter filter}
 * and creating the necessary {@linkplain FeatureLayer feature layer}.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface LayerSource {
    /**
     * Icon representing this type of LayerSource.
     *
     * @return the icon.
     *
     * @revisit Assumed to point to a 16x16 icon?
     * @revisit Should the return type be a {@link java.net.URI}?
     */
    URL getIcon();

    /**
     * Display name used to communicate this {@code LayerSource} to end users.
     */
    InternationalString getDisplayName();
    
    /** 
     * Description of this type of {@code LayerSource}.
     */
    InternationalString getDescription();
    
    /**
     * Gets the named {@code Layer}.
     *
     * @param name the id of the {@code Layer}.
     */
    Layer getLayer(String name);
    
    /**
     * Returns the array of {@code Layer}s provided by this {@code LayerSource}.
     *
     * @return an array of <code>Layer</code>s
     *
     * @revisit Should the returns type be a {@link java.util.List} instead?
     */
    Layer[] getLayers();
}
