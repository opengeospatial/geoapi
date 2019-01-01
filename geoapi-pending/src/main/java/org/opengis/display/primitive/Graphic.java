/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
