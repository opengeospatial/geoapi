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
 * CanvasEvent send to listener when the Canvas state changes.
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class CanvasEvent extends EventObject{
    
    public CanvasEvent(Canvas source) {
        super(source);
    }

    /**
     * Returns the old state.
     * 
     * @return CanvasState
     */
    public abstract CanvasState getOldState();

    /**
     * Returns the new State.
     * 
     * @return CanvasState
     */
    public abstract CanvasState getNewState();

    /**
     * Returns the MathTransform difference between the previous state
     * and the new one.
     * 
     * @return MathTransform : difference with the previous CanvasState
     */
    public abstract MathTransform getChange();

    /**
     * Calculate a MathTransform between the Canvas State and the given CanvasState.
     * The provided CanvaState may be from another Canves.
     * 
     * @param other
     * @return MathTransform : difference between CanvasStates
     */
    public abstract MathTransform getChange(CanvasState other);

    @Override
    public Canvas getSource() {
        return (Canvas) super.getSource();
    }
    
}
