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
package org.opengis.display.canvas;

import java.util.EventObject;
import org.opengis.referencing.operation.MathTransform;


/**
 * Event sent by a {@linkplain Canvas canvas} to it registered listeners
 * when its state changed.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public abstract class CanvasEvent extends EventObject {
    /**
     * Creates a new event having the given canvas as a source.
     *
     * @param  source The canvas on which the event initially occurred.
     * @throws IllegalArgumentException if the given source is null.
     */
    public CanvasEvent(Canvas source) {
        super(source);
    }

    /**
     * Returns the canvas on which the event occurred.
     *
     * @return the source canvas.
     */
    @Override
    public Canvas getSource() {
        return (Canvas) super.getSource();
    }

    /**
     * Returns the canvas state prior the change.
     *
     * @return the old canvas state.
     */
    public abstract CanvasState getOldState();

    /**
     * Returns the canvas state after the change.
     *
     * @return the new canvas state.
     */
    public abstract CanvasState getNewState();

    /**
     * Returns the change from the {@linkplain #getOldState old state}
     * to the {@linkplain #getNewState new state} in units of
     * {@linkplain CanvasState#getObjectiveCRS objective CRS}.
     * This change is typically (but is not restricted to) an affine transform.
     * <p>
     * <b>Example:</b> if the objective CRS uses a map projection with axis in metres,
     * and if the user pan the map, then the change is an affine transform containing
     * translation terms in metres.
     *
     * @return the change from the {@linkplain #getOldState old state} to the
     *         {@linkplain #getNewState new state}, typically as an affine transform.
     */
    public abstract MathTransform getChange();

    /**
     * Calculates to the given canvas state.
     *
     * @deprecated This method does not said is the change is computed from the old or the
     *             new state. If we need this functionality, then the method should move to
     *             {@link CanvasState}.
     */
    @Deprecated
    public abstract MathTransform getChange(CanvasState other);

    public abstract RenderingState getOldRenderingstate();

    public abstract RenderingState getNewRenderingstate();

}
