/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
     * @throws TransformException if at least one graphic primitive cannot be transformed to
     *         the specified CRS, or if the given CRS cannot be accepted for another reason.
     *
     * @see CanvasState#getObjectiveCRS
     */
    @UML(identifier="Canvas.setObjectiveCoordinateReferenceSystem", specification=OGC_03064, obligation=OPTIONAL)
    void setObjectiveCRS(CoordinateReferenceSystem crs) throws TransformException;
}
