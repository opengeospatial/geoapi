/*
 * $ Id $
 * $ Source $
 * Created on Jan 10, 2005
 */
package org.opengis.go.display.primitive.store;

import java.net.URL;

import org.opengis.go.display.DisplayFactory;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.util.InternationalString;


/**
 * The <code>GraphicStore</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface GraphicStore {
    
    /**
     * Icon representing this GraphicStore.
     * <p>
     * Assumed 16x16 in size.
     * </p>
     * @return URL to a icon (GIF or PNG) representing this GraphicStore.
     */
    URL getIcon();

    /** Display name for this Datastore. */
    InternationalString getDisplayName();

    /**
     * Description of this GraphicStore.
     */
    InternationalString getDescription();

    /**
     * Access to metadata about this GraphicStore.
     * <p>
     * Note this is an example of the bridge pattern, client code should not
     * cache this metadata object to ensure that they are never out of date.
     * </p>
     * <p>
     * It is too much overhead to indicate metadata changing with an extra set of
     * events, GraphicStoreEvents will be used to indicate new content is available. 
     */
    // Metadata getMetadata();

    
    Graphic[] getGraphics(DisplayFactory factory);
    
}
