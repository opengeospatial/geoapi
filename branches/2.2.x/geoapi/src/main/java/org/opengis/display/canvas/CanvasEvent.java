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
     * @throws IllegalArgumentException If the given source is null.
     */
    public CanvasEvent(Canvas source) {
        super(source);
    }

    /**
     * Returns the canvas on which the event occured.
     *
     * @return The source canvas.
     */
    @Override
    public Canvas getSource() {
        return (Canvas) super.getSource();
    }

    /**
     * Returns the canvas state prior the change.
     *
     * @return The old canvas state.
     */
    public abstract CanvasState getOldState();

    /**
     * Returns the canvas state after the change.
     *
     * @return The new canvas state.
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
     * @return The change from the {@linkplain #getOldState old state} to the
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
}
