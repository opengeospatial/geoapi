/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;

import java.util.Collection;
import java.util.List;
import org.opengis.util.InternationalString;
import org.opengis.geometry.primitive.Point;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid whose cells are regularly spaced in a geographic (i.e., lat / long) or map
 * coordinate system defined in the Spatial Referencing System (SRS) so that any cell
 * in the grid can be geolocated given its grid coordinate and the grid origin, cell spacing,
 * and orientation indication of whether or not geographic.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - Point
 * @navassoc 1 - - PixelOrientation
 * @navassoc - - - GCP
 */
@UML(identifier="MD_Georectified", specification=ISO_19115)
public interface Georectified extends GridSpatialRepresentation {
    /**
     * Indication of whether or not geographic position points are available to test the
     * accuracy of the georeferenced grid data.
     *
     * @return Whether or not geographic position points are available to test accuracy.
     */
    @UML(identifier="checkPointAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isCheckPointAvailable();

    /**
     * Description of geographic position points used to test the accuracy of the
     * georeferenced grid data.
     *
     * @return Description of geographic position points used to test accuracy, or {@code null}.
     *
     * @condition {@linkplain #isCheckPointAvailable() Check point availability} equals yes.
     */
    @UML(identifier="checkPointDescription", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getCheckPointDescription();

    /**
     * Earth location in the coordinate system defined by the Spatial Reference System
     * and the grid coordinate of the cells at opposite ends of grid coverage along two
     * diagonals in the grid spatial dimensions. There are four corner points in a
     * georectified grid; at least two corner points along one diagonal are required.
     *
     * @return The corner points.
     */
    @UML(identifier="cornerPoints", obligation=MANDATORY, specification=ISO_19115)
    List<? extends Point> getCornerPoints();

    /**
     * Earth location in the coordinate system defined by the Spatial Reference System
     * and the grid coordinate of the cell halfway between opposite ends of the grid in the
     * spatial dimensions.
     *
     * @return The center point, or {@code null}.
     */
    @UML(identifier="centerPoint", obligation=OPTIONAL, specification=ISO_19115)
    Point getCenterPoint();

    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     *
     * @return Earth location of the pixel.
     */
    @UML(identifier="pointInPixel", obligation=MANDATORY, specification=ISO_19115)
    PixelOrientation getPointInPixel();

    /**
     * Description of the information about which grid dimensions are the spatial dimensions.
     *
     * @return Description of the information about grid dimensions, or {@code null}.
     */
    @UML(identifier="transformationDimensionDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getTransformationDimensionDescription();

    /**
     * Information about which grid dimensions are the spatial dimensions.
     *
     * @return Information about which grid dimensions are the spatial dimensions, or {@code null}.
     */
    @UML(identifier="transformationDimensionMapping", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getTransformationDimensionMapping();

    /**
     * Geographic references used to validate georectification of the data.
     *
     * @return Geographic references used to validate georectification.
     *
     * @since 2.3
     */
    @UML(identifier="checkPoint", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends GCP> getCheckPoints();
}
