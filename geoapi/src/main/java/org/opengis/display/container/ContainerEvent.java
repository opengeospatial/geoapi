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
package org.opengis.display.container;

import java.util.Collection;
import java.util.EventObject;
import org.opengis.display.primitive.Graphic;


/**
 * Event sent to {@linkplain ContainerListener container listeners} when
 * a {@linkplain Graphic graphics} changed.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public abstract class ContainerEvent extends EventObject {
    /**
     * Creates an event emitted by the given source.
     *
     * @param source The source, or {@code null} if unknown.
     */
    public ContainerEvent(GraphicsContainer source) {
        super(source);
    }

    /**
     * Returns the source of thie event.
     *
     * @return The source of this event, or {@code null} if unknown.
     */
    @Override
    public GraphicsContainer getSource() {
        return (GraphicsContainer) super.getSource();
    }

    /**
     * Returns the graphics affected by this event
     *
     * @return The graphics affected by this event.
     */
    public abstract Collection<Graphic> getGraphics();
}
