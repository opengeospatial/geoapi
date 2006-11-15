/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid.quadrilateral;

// J2SE direct dependencies
import java.util.List;

// OpenGIS dependencies
import org.opengis.referencing.operation.Conversion;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.Datum;  // For javadoc

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid for which there is an affine transformation between the grid coordinates and the coordinates of
 * an external {@linkplain CoordinateReferenceSystem coordinate reference system}. A rectified grid is
 * defined by an origin in an external {@linkplain CoordinateReferenceSystem coordinate reference system},
 * and a set of offset vectors that specify the direction and distance between grid lines within
 * that external CRS.
 * <p>
 * <b>NOTE:</b> If the coordinate reference system is related to the earth by a
 * {@linkplain Datum datum}, the grid is a georectified grid.
 * <p>
 * <b>Constraints:</b>
 * <ul>
 *   <li>The {@linkplain Grid#getDimension dimension of the grid} shall be less than or equal to the
 *       dimension of the {@linkplain DirectPosition#getCoordinateReferenceSystem coordinate
 *       reference system of the point} that is the {@linkplain #getOrigin origin}.</li>
 *   <li>The number of {@linkplain #getOffsetVectors offset vectors} shall equal the
 *       {@linkplain Grid#getDimension dimension of the grid}.</li>
 *   <li>The dimension of all offset vectors shall equal the dimension of the coordinate reference
 *       system, even if an offset vector is aligned with an axis of the external coordinate system.</li>
 * </ul>
 * 
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 * @author Martin Desruisseaux
 * @author Alexander Petkov
 */
@UML(identifier="CV_RectifiedGrid", specification=ISO_19123)
public interface RectifiedGrid extends RectifiableGrid {
    /**
     * Returns the origin of the rectified grid in an external coordinate reference system. 
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19123)
    DirectPosition getOrigin();

    /**
     * Returns the offset vectors that determine the grid spacing in each direction. The vectors
     * are defined in terms of the external coordinate reference system.
     */
    @UML(identifier="offsetVectors", obligation=MANDATORY, specification=ISO_19123)
    List<double[]> getOffsetVectors();

    /**
     * {@inheritDoc}
     * In the context of the {@code RectifiedGrid} type, the {@linkplain CoordinateReferenceSystem
     * coordinate reference system} attribute inherited from {@linkplain GridPositioning grid positioning}
     * shall be derived from the Coordinate Reference System association of the origin.
     */
    @Extension
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The conversion defined by this object is an affine transformation 
     * defined by the origin and offset vectors attributes.
     */
/// @Extension
/// Conversion getConversion();

     /**
      * The inverseConversion defined by this object is an affine transformation 
      * defined by the origin and offset vectors attributes.
      */
///  @Extension
///  Conversion getInverseConversion();
}
