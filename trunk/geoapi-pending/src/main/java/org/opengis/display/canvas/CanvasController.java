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

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Controls the state of a {@linkplain Canvas canvas}, including its position, scale and title.
 *
 * @author Open GIS Consortium, Inc.
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public interface CanvasController {
    /**
     * Sets the title of the {@linkplain Canvas canvas}. The title of a canvas
     * may or may not be displayed in the titlebar of an application's window.
     *
     * @param title The new title for the canvas.
     *
     * @see CanvasState#getTitle
     */
    @UML(identifier="Canvas.setTitle", specification=OGC_03064, obligation=OPTIONAL)
    void setTitle(InternationalString title);

    /**
     * Sets the position of the center point of the {@linkplain Canvas canvas}.
     * The coordinate shall be in {@linkplain CanvasState#getObjectiveCRS objective CRS}.
     *
     * @param center The new center position.
     *
     * @see CanvasState#getCenter
     */
    @UML(identifier="setCenter", specification=OGC_03064, obligation=OPTIONAL)
    void setCenter(DirectPosition center);

    /**
     * Sets the "real world" Coordinate Reference System. This is typically
     * a {@linkplain ProjectedCRS projected CRS} using linear units like metre.
     * Graphic data will be projected to this CRS before to be display.
     *
     * @param  crs The new objective Coordinate Reference System.
     * @throws TransformException If at least one graphic primivite can not be transformed to
     *         the specified CRS, or if the given CRS can not be accepted for an other reason.
     *
     * @see CanvasState#getObjectiveCRS
     */
    @UML(identifier="Canvas.setObjectiveCoordinateReferenceSystem", specification=OGC_03064, obligation=OPTIONAL)
    void setObjectiveCRS(CoordinateReferenceSystem crs) throws TransformException;
}
