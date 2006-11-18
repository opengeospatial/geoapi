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
package org.opengis.coverage.grid;

// J2SE direct dependencies
import java.util.List;

// OpenGIS dependencies
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.Datum;  // For javadoc

// Annotations
import org.opengis.annotation.UML;
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
 */
@UML(identifier="CV_RectifiedGrid", specification=ISO_19123)
public interface RectifiedGrid extends Grid {
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
	 * Converts through an affine transform grid coordinates to a direct position.
	 */
	@UML(identifier="coordConv", obligation=MANDATORY, specification=ISO_19123)
	DirectPosition convertCoordinates(GridCoordinates g);

	/**
	 * Converts through an affine transform a direct position to the grid coordinates of
     * the nearest grid point.
	 * 
	 * @todo Question (Wim): GridCoordinates are always integers, how to get
	 *       the not rounded results?<br>
     *       Martin: The legacy OGC specification defined a "gridToCRS" math transform for
     *       that. We may consider to import this element in the proposed set of interfaces.
	 */
	@UML(identifier="invCoordConv", obligation=MANDATORY, specification=ISO_19123)
	GridCoordinates inverseConvertCoordinates(DirectPosition p);
}
