/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.display.primitive;

import org.opengis.display.container.GraphicsContainer;


/**
 * Defines the root abstraction of a graphic object taxonomy. This base interface
 * specifies the methods common to a lightweight set of graphic objects.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public interface Graphic {
    /**
     * Returns {@code true} if this graphic is visible.
     *
     * @return {@code true} if this graphic is visible.
     */
    boolean isVisible();

    /**
     * Sets whether this graphic should be visible.
     *
     * @param visible {@code true} if this graphic should be visible.
     */
    void setVisible(boolean visible);

    /**
     * Invoked by the {@linkplain GraphicsContainer container} when this graphic is no longer needed.
     * Implementations may use this method to release resources, if needed. Implementations
     * may also implement this method to return an object to an object pool. It is an error
     * to reference a {@code Graphic} in any way after its dispose method has been called.
     */
    void dispose();
}
