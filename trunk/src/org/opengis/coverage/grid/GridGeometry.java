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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Describes the geometry and georeferencing information of the grid coverage.
 * The grid range attribute determines the valid grid coordinates and allows
 * for calculation of grid size. A grid coverage may or may not have georeferencing.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
///@UML (identifier="CV_GridGeometry")
public interface GridGeometry {
    /**
     * The valid coordinate range of a grid coverage.
     * The lowest valid grid coordinate is often (but not always) zero.
     * A grid with 512 cells typically have a minimum coordinate of 0 and maximum of 512,
     * with 511 as the highest valid index.
     *
     * @return The valid coordinate range of a grid coverage.
     */
/// @UML (identifier="gridRange", obligation=MANDATORY)
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
     */
/// @UML (identifier="gridToCoordinateSystem", obligation=MANDATORY)
    MathTransform getGridToCoordinateSystem();
}
