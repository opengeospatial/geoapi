/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;
import org.opengis.spatialschema.geometry.primitive.Point;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Grid whose cells are regularly spaced in a geographic (i.e., lat / long) or map
 * coordinate system defined in the Spatial Referencing System (SRS) so that any cell
 * in the grid can be geolocated given its grid coordinate and the grid origin, cell spacing,
 * and orientation indication of whether or not geographic.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Georectified")
public interface Georectified extends GridSpatialRepresentation {
    /**
     * Indication of whether or not geographic position points are available to test the
     * accuracy of the georeferenced grid data.
     */
/// @UML (identifier="checkPointAvailability", obligation=MANDATORY)
    boolean isCheckPointAvailable();

    /**
     * Description of geographic position points used to test the accuracy of the
     * georeferenced grid data.
     */
/// @UML (identifier="checkPointDescription", obligation=OPTIONAL)
    InternationalString getCheckPointDescription();

    /**
     * Earth location in the coordinate system defined by the Spatial Reference System
     * and the grid coordinate of the cells at opposite ends of grid coverage along two
     * diagonals in the grid spatial dimensions. There are four corner points in a
     * georectified grid; at least two corner points along one diagonal are required.
     */
/// @UML (identifier="cornerPoints", obligation=MANDATORY)
    List/*<Point>*/ getCornerPoints();

    /**
     * Earth location in the coordinate system defined by the Spatial Reference System
     * and the grid coordinate of the cell halfway between opposite ends of the grid in the
     * spatial dimensions.
     */
/// @UML (identifier="centerPoint", obligation=OPTIONAL)
    Point getCenterPoint();

    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     */
/// @UML (identifier="pointInPixel", obligation=MANDATORY)
    PixelOrientation getPointInPixel();

    /**
     * Description of the information about which grid dimensions are the spatial dimensions.
     */
/// @UML (identifier="transformationDimensionDescription", obligation=OPTIONAL)
    InternationalString getTransformationDimensionDescription();

    /**
     * Information about which grid dimensions are the spatial dimensions.
     */
/// @UML (identifier="transformationDimensionMapping", obligation=OPTIONAL)
    List/*<InternationalString>*/ getTransformationDimensionMapping();
}
