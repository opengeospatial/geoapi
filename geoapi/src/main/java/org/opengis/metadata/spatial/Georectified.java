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
package org.opengis.metadata.spatial;

import java.util.List;
import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.geometry.primitive.Point;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid whose cells are regularly spaced in a geographic or projected coordinate reference system.
 * Any cell in the grid can be geolocated given its grid coordinate and the grid origin, cell spacing,
 * and orientation indication of whether or not geographic.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_Georectified", specification=ISO_19115)
public interface Georectified extends GridSpatialRepresentation {
    /**
     * Indication of whether or not geographic position points are available to test the
     * accuracy of the georeferenced grid data.
     *
     * @return whether or not geographic position points are available to test accuracy.
     */
    @UML(identifier="checkPointAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isCheckPointAvailable();

    /**
     * Description of geographic position points used to test the accuracy of the georeferenced grid data.
     *
     * @return description of geographic position points used to test accuracy, or {@code null}.
     *
     * @condition Mandatory if {@linkplain #isCheckPointAvailable() check point availability} equals {@code true}.
     */
    @UML(identifier="checkPointDescription", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getCheckPointDescription();

    /**
     * Earth location in the coordinate system defined by the Spatial Reference System
     * and the grid coordinate of the cells at opposite ends of grid coverage along two
     * diagonals in the grid spatial dimensions.
     *
     * <p>The {@linkplain List#size() list size} shall be 2 or 4.
     * The list shall contain at least two corner points along one diagonal.
     * or may contains the 4 corner points of the georectified grid.</p>
     *
     * <p>The first corner point corresponds to the origin of the grid.</p>
     *
     * @return the corner points.
     */
    @UML(identifier="cornerPoints", obligation=MANDATORY, specification=ISO_19115)
    List<? extends Point> getCornerPoints();

    /**
     * Earth location in the coordinate system defined by the Spatial Reference System
     * and the grid coordinate of the cell halfway between opposite ends of the grid in the
     * spatial dimensions.
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * This method will be renamed {@code getCentrePoint()} in GeoAPI 4.0
     * for compliance with ISO 19115:2014 standard.</div>
     *
     * @return the center point, or {@code null}.
     */
    @UML(identifier="centrePoint", obligation=OPTIONAL, specification=ISO_19115)
    default Point getCenterPoint() {
        return null;
    }

    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     *
     * @return earth location of the pixel.
     */
    @UML(identifier="pointInPixel", obligation=MANDATORY, specification=ISO_19115)
    PixelOrientation getPointInPixel();

    /**
     * General description of the transformation.
     *
     * @return general description of the transformation, or {@code null}.
     */
    @UML(identifier="transformationDimensionDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getTransformationDimensionDescription() {
        return null;
    }

    /**
     * Information about which grid dimensions are the spatial dimensions.
     * The list should contain at most 2 elements.
     *
     * @return information about which grid dimensions are the spatial dimensions, or {@code null}.
     */
    @UML(identifier="transformationDimensionMapping", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getTransformationDimensionMapping() {
        return Collections.emptyList();
    }

    /**
     * Geographic references used to validate georectification of the data.
     *
     * @return geographic references used to validate georectification.
     */
    @UML(identifier="checkPoint", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends GCP> getCheckPoints() {
        return Collections.emptyList();
    }
}
