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

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A grid whose relation with an external {@linkplain CoordinateReferenceSystem coordinate reference
 * system} is specified in another way than in terms of origin, orientation and spacing in that
 * coordinate system. The transformation between grid and external coordinate system can be some
 * analytical or non-analytical form.
 *
 * @author ISO/DIS 19123
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 * @author Martin Desruisseaux
 *
 * @todo Comment (Wim): there seems to be no way to check whether two ReferenceableGrids are equal,
 *       i.e. exactly fitting on all GridPoints.<br>
 *       Martin: a possible approach is to import the "gridToCRS" attribute from the legacy OGC
 *       specification, exactly as proposed for {@link RectifiedGrid}. Two ReferenceableGrids with
 *       the same grid geometry and the same "gridToCRS" math transform exactly fitting on all
 *       GridPoints.
 */
@UML(identifier="CV_ReferenceableGrid", specification=ISO_19123)
public interface ReferenceableGrid extends Grid {
    /**
     * Returns the coordinate reference system to which this grid is referenceable.
     */
    @UML(identifier="CoordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19123)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Transforms a grid coordinates to a direct position.
     */
    @UML(identifier="coordTransform", obligation=MANDATORY, specification=ISO_19123)
    DirectPosition transformCoordinates(GridCoordinates g);

    /**
     * Transforms from a direct position to the grid coordinates of the nearest grid point.
     *
     * @todo Question (Wim): GridCoordinates are always integers, how to get
     *       the not rounded results?<br>
     *       Martin: The legacy OGC specification defined a "gridToCRS" math transform for
     *       that. We may consider to import this element in the proposed set of interfaces.
     */
    @UML(identifier="invCoordTransform", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates inverseTransformCoordinates(DirectPosition p);
}
