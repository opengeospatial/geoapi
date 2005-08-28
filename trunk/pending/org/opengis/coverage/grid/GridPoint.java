/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.coverage.DomainObject;
import org.opengis.spatialschema.geometry.primitive.Point;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents the intersections of the grid lines.
 * 
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
     */
    @UML(identifier="Organization", obligation=MANDATORY, specification=ISO_19123)
    Grid getOrganization();

    /**
     * Returns the set of {@linkplain GridCell grid cells} for which this grid point is a corner.
     */
    @UML(identifier="Location", obligation=MANDATORY, specification=ISO_19123)
    Set<GridCell> getLocation();

	/**
	 * Returns the representation of the grid point in an external
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}.
	 */
    @UML(identifier="Reference", obligation=OPTIONAL, specification=ISO_19123)
	Point getReference();

	/**
	 * Returns the {@linkplain FootPrint foot prints} that represents the sample space in an external
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}
     * associated with this grid point. The multiplicity of the association allows for multiple external
     * coordinate reference systems for foot print.
     */
    @UML(identifier="SampleSpace", obligation=OPTIONAL, specification=ISO_19123)
	Set<FootPrint> getSampleSpace();
}
