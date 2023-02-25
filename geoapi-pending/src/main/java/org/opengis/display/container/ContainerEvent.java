/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
     * @return the source of this event, or {@code null} if unknown.
     */
    @Override
    public GraphicsContainer getSource() {
        return (GraphicsContainer) super.getSource();
    }

    /**
     * Returns the graphics affected by this event
     *
     * @return the graphics affected by this event.
     */
    public abstract Collection<Graphic> getGraphics();
}
