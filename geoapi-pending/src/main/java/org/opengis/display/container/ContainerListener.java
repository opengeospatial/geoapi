/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.EventListener;
import org.opengis.display.primitive.Graphic;


/**
 * Listener notified when {@linkplain Graphic graphics} are added or removed from a
 * {@linkplain GraphicsContainer container}.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface ContainerListener extends EventListener {
    /**
     * Called when graphic objects are added.
     *
     * @param event The event containing the collection of graphics added.
     */
    void graphicsAdded(ContainerEvent event);

    /**
     * Called when graphics objects are removed.
     *
     * @param event The event containing the collection of graphics removed.
     */
    void graphicsRemoved(ContainerEvent event);

    /**
     * Called when graphic objects are updated.
     *
     * @param event The event containing the collection of graphics updated.
     */
    void graphicsChanged(ContainerEvent event);
    
    /**
     * Called when graphic objects need to be repainted, but no property changed.
     * Exemple : a blinking or animated graphic.
     * 
     * @param event The event containing the collection of graphics updated.
     */
    void graphicsDisplayChanged(ContainerEvent event);
    
}
