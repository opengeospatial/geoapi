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
package org.opengis.feature.display.canvas;

// J2SE direct dependencies
import java.util.EventListener;


/**
 * Methods on this interface are invoked when a {@link FeatureLayer}
 * has been modified.
 * 
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface FeatureLayerListener extends EventListener {
    /**
     * Invoked when the style has changed.
     */
    void styleChanged(FeatureLayerEvent event);

    /**
     * Invoked when the Z-order value has changed.
     */
    void levelChanged(FeatureLayerEvent event);
}
