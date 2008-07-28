/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.display.renderer;

import java.util.Collection;
import java.util.EventObject;
import org.opengis.display.primitive.Graphic;


/**
 * Event sent to {@linkplain RendererListener renderer listeners} when
 * a {@linkplain Graphic graphics} changed.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public abstract class RendererEvent extends EventObject {
    /**
     * Creates an event emitted by the given source.
     *
     * @param source The source, or {@code null} if unknown.
     */
    public RendererEvent(Renderer source) {
        super(source);
    }

    /**
     * Returns the source of thie event.
     *
     * @return The source of this event, or {@code null} if unknown.
     */
    @Override
    public Renderer getSource() {
        return (Renderer) super.getSource();
    }

    /**
     * Returns the graphics affected by this event
     *
     * @return The graphics affected by this event.
     */
    public abstract Collection<Graphic> getGraphics();
}
