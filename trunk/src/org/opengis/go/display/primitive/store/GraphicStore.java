/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive.store;

// J2SE direct dependencies
import java.net.URI;
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.go.display.DisplayFactory;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.util.InternationalString;


/**
 * The <code>GraphicStore</code> class/interface...
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface GraphicStore {    
    /**
     * Icon representing this {@code GraphicStore}.
     *
     * @return URI to a icon (GIF or PNG) representing this {@code GraphicStore}.
     *
     * @todo Assumed 16x16 in size.
     */
    URI getIcon();

    /**
     * Display name for this {@code GraphicStore}.
     */
    InternationalString getDisplayName();

    /**
     * Description of this {@code GraphicStore}.
     */
    InternationalString getDescription();

    /**
     * Access to metadata about this {@code GraphicStore}.
     * <p>
     * Note this is an example of the bridge pattern, client code should not
     * cache this metadata object to ensure that they are never out of date.
     * <p>
     * It is too much overhead to indicate metadata changing with an extra set of
     * events, GraphicStoreEvents will be used to indicate new content is available. 
     */
    // Metadata getMetadata();

    /**
     * Gets a List of {@code Graphic}s produced by this {@code GraphicStore}.  This
     * List should not be live:  modifying the List should not modify the 
     * {@code GraphicStore}'s internal {@code Graphic}s.
     * @param factory the {@code DisplayFactory} to use to create the {@code Graphic}s
     * @return a List of {@code Graphic}s
     */
    List<Graphic> getGraphics(DisplayFactory factory);
}
