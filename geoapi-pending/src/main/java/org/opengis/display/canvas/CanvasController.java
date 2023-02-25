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
