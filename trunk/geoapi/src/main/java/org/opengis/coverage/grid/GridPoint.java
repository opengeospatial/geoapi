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

import java.util.Set;
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Point located at the intersection of two or more curves in a {@linkplain Grid grid}.
 *
 * @author ISO/DIS 19123
 * @author Martin Schouwenburg
 * @author Wim Koolhoven
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_GridPoint", specification=ISO_19123)
public interface GridPoint extends DomainObject {
    /**
     * Returns the set of grid coordinates that specifies the location of the
     * grid point within the {@linkplain Grid grid}.
     */
    @UML(identifier="gridCoord", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates getGridCoordinates();

    /**
     * Returns the {@linkplain Grid grid} of which it is an element.
     *
     * @see Grid#getIntersections
     */
    @UML(identifier="framework", obligation=MANDATORY, specification=ISO_19123)
    Grid getFramework();

    /**
     * Returns the set of {@linkplain GridCell grid cells} for which this grid point is a corner.
     *
     * @see GridCell#getCorners
     */
    @UML(identifier="cell", obligation=MANDATORY, specification=ISO_19123)
    Set<GridCell> getCells();

    /**
     * Returns the representation of the grid point in an external
     * {@linkplain CoordinateReferenceSystem coordinate reference system}.
     */
    @UML(identifier="groundPoint", obligation=OPTIONAL, specification=ISO_19123)
    Point getGroundPoint();

    /**
     * Returns the {@linkplain FootPrint foot prints} that represents the sample space in an external
     * {@linkplain CoordinateReferenceSystem coordinate reference system} associated with this grid
     * point. The multiplicity of the association allows for multiple external coordinate reference
     * systems for foot print.
     *
     * @see FootPrint#getCenter
     */
    @UML(identifier="footprint", obligation=OPTIONAL, specification=ISO_19123)
    Set<FootPrint> getFootPrint();
}
