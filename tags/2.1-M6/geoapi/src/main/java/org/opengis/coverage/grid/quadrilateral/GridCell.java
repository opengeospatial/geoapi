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

import java.util.Set;
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.Geometry;
import org.opengis.temporal.TemporalGeometricPrimitive;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A grid cell delineated by the grid lines of a {@linkplain Grid grid}. Its corners
 * are associated with the {@linkplain GridPoint grid points} at the intersections of
 * the grid lines that bound it
 * 
 * @author Martin Schouwenburg
 * @author Wim Koolhoven
 * @author Martin Desruisseaux
 * @author Alexander Petkov
 */
@UML(identifier="CV_GridCell", specification=ISO_19123)
public interface GridCell {
	/**
	 * Returns the collection of {@linkplain GridPoint grid points} at the corners of the grid cell. 
     * The size of this collection has no upper bound, to allow for grids of any dimension.
     * In a quadrilateral grid, the multiplicity of corner equals 2&times;<var>d</var>, where
     * <var>d</var> is the value of {@link Grid#getDimension}.
     *
     * @see GridPoint#getCells
	 */
    @UML(identifier="corner", obligation=MANDATORY, specification=ISO_19123)
	Set<GridPoint> getCorners();

    /**
     * Returns the {@linkplain Grid grid} of which this cell is a component.
     *
     * @see Grid#getCells
     */
    @UML(identifier="framework", obligation=MANDATORY, specification=ISO_19123)
    Grid getFramework();
    
    /**
     * This role name is inherited from DomainObject and associates the grid cell with a geometric object which encodes only the spatial components of the grid index.  
     * Spatial axes in the geometric object are specified in the same order as in the grid coordinates.  
     * The temporal axis and any categorical axes are omitted.  
     * In two spatial dimensions, the geometric object shall be a GM_PolyhedralSurface composed of a single GM_Polygon.  
     * For three spatial dimensions, the user must specify a GM_Solid object which represents the volume bounded by the eight corners.
     * 
     * Because we consider time to be orthogonal to space, these spatial elements may be factored out.  
     * The same spatial elements participate at the start time and at the end time.  
     * Therefore, they need only be represented once.
     */
    @Extension
    Set<Geometry> getSpatialElements();
    
    /**
     * This role name is inherited from DomainObject and associates the grid cell 
     * with a TM_Interval which represents the two TM_Instants which participate in the Grid Cell.  
     * Because there may be only one time axis, there can be only two relevant TM_Instants.
     * Because we consider time to be orthogonal to space, these temporal elements may be factored out and represented separately from the spatial elements.
     */
    @Extension
    Set<TemporalGeometricPrimitive> getTemporalElements();
}
