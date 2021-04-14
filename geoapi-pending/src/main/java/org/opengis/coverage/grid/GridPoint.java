/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.coverage.grid;

import java.util.Set;
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Point located at the intersection of two or more curves in a {@linkplain Grid grid}.
 *
 * @version ISO 19123:2004
 * @author  Martin Schouwenburg
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridPoint", specification=ISO_19123)
public interface GridPoint extends DomainObject<Geometry> {
    /**
     * Returns the set of grid coordinates that specifies the location of the
     * grid point within the {@linkplain Grid grid}.
     *
     * @return the coordinates that specifies the location of the grid point.
     */
    @UML(identifier="gridCoord", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates getGridCoordinates();

    /**
     * Returns the {@linkplain Grid grid} of which this grid point is an element.
     *
     * @return the grid of which this grid point is an element.
     *
     * @see Grid#getIntersections
     */
    @UML(identifier="framework", obligation=MANDATORY, specification=ISO_19123)
    Grid getFramework();

    /**
     * Returns the set of {@linkplain GridCell grid cells} for which this grid point is a corner.
     *
     * @return the grid cells for which this grid point is a corner.
     *
     * @see GridCell#getCorners
     */
    @UML(identifier="cell", obligation=MANDATORY, specification=ISO_19123)
    Set<GridCell> getCells();

    /**
     * Returns the representation of the grid point in an external
     * {@linkplain CoordinateReferenceSystem coordinate reference system}.
     *
     * @return the representation of this grid point in an external CRS.
     */
    @UML(identifier="groundPoint", obligation=OPTIONAL, specification=ISO_19123)
    Point getGroundPoint();

    /**
     * Returns the {@linkplain FootPrint foot prints} that represents the sample space in an external
     * {@linkplain CoordinateReferenceSystem coordinate reference system} associated with this grid
     * point. The multiplicity of the association allows for multiple external coordinate reference
     * systems for foot print.
     *
     * @return the foot prints that represents the sample space in an external CRS.
     *
     * @see FootPrint#getCenter
     */
    @UML(identifier="footprint", obligation=OPTIONAL, specification=ISO_19123)
    Set<FootPrint> getFootPrints();
}
