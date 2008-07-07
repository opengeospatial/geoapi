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
package org.opengis.display.primitive;

import org.opengis.display.renderer.Renderer;


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
     * Sets whatever this graphic should be visible.
     *
     * @param visible {@code true} if this graphic should be visible.
     */
    void setVisible(boolean visible);

    /**
     * Invoked by the {@linkplain Renderer renderer} when this graphic is no longer needed.
     * Implementations may use this method to release resources, if needed. Implementations
     * may also implement this method to return an object to an object pool. It is an error
     * to reference a {@code Graphic} in any way after its dispose method has been called.
     */
    void dispose();
}
