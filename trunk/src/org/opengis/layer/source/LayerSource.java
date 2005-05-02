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
import java.io.IOException;
import java.net.URI;
import java.util.List;

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
 * A {@code LayerSource} could be capable of retrieving {@linkplain Feature features} from
 * given {@linkplain FeatureStore feature stores} using a given {@linkplain Filter filter}
 * and creating the necessary {@linkplain FeatureLayer feature layer}.  Other 
 * {@code LayerSource} implementations may connect to some other data source and produce
 * {@linkplain Graphic graphics} to be owned by the produced {@linkplain Layer layers}.
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
     */
    URI getIcon();

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
     * @throws IOException if there is a problem getting the named {@code Layer}
     */
    Layer getLayer(String name) throws IOException;
    
    /**
     * Returns a List of {@code Layer}s provided by this {@code LayerSource}.
     * This List should <b>not</b> be a live List: modifying the returned List
     * should not modify this {@code LayerSource}'s {@code Layer}s.
     *
     * @return a List of <code>Layer</code>s
     * @throws IOException if there are problems getting the {@code Layer}s
     */
    List<Layer> getLayers() throws IOException;
}
