/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// OpenGIS direct dependencies
import org.opengis.referencing.operation.MathTransform;


/**
 * Describes the geometry and georeferencing information of the grid coverage.
 * The grid range attribute determines the valid grid coordinates and allows
 * for calculation of grid size. A grid coverage may or may not have georeferencing.
 *
 * @UML datatype CV_GridGeometry
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
public interface GridGeometry {
    /**
     * The valid coordinate range of a grid coverage.
     * The lowest valid grid coordinate is zero.
     * A grid with 512 cells can have a minimum coordinate of 0 and maximum of 512,
     * with 511 as the highest valid index.
     *
     * @return The valid coordinate range of a grid coverage.
     * @UML mandatory gridRange
     *
     * @revisit Should we relax the constraint that the lowest valid grid coordinate is zero?
     *          {@link java.awt.image.RenderedImage} allow arbitrary integer coordinates for
     *          the upper left corner. If we force it to zero, the mapping from Java2D to
     *          GridCoverage may no longer be direct. (Note: if we relax this specification,
     *          then the package description need to be updated as well, and
     *          {@link GridRange#getLower} too).
     */
    GridRange getGridRange();

    /**
     * The conversion allows for the transformations from grid coordinates to real
     * world earth coordinates. The transform is often an affine transformation. The
     * coordinate reference system of the real world coordinates is given by the
     * {@link org.opengis.coverage.Coverage#getCoordinateReferenceSystem} method.
     * If no conversion is given, this attribute will be <code>null</code>.
     *
     * @return The conversion from grid coordinates to
     *         {@linkplain org.opengis.coverage.Coverage#getCoordinateReferenceSystem
     *         real world earth coordinates}.
     * @UML mandatory gridToCoordinateSystem
     */
    MathTransform getGridToCoordinateSystem();
}
