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
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.coverage.ValueObject;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating within a {@linkplain ContinuousQuadrilateralGridCoverage
 * continuous quadrilateral grid coverage}. A {@code GridValueCell} is a collection
 * of {@link GridPointValuePair}s with a geometric structure defined by a {@link GridCell}.
 * 
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 */
@UML(identifier="CV_GridValueCell", specification=ISO_19123)
public interface GridValueCell extends ValueObject {
    /**
     * Returns the {@linkplain GridCell grid cell} that defines the structure of the
     * {@linkplain GridPointValuePair grid point value pairs} that support the interpolation
     * of a feature attribute value at a {@linkplain DirectPosition direct position} within the
     * {@linkplain GridCell grid cell}.
     *
     * @revisit Clash in the return type!!!!
     */
//    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
//    GridCell getGeometry();

    /**
     * Returns the set of <var>grid point</var>-<var>value</var> pairs at the corners of this
     * {@code GridValueCell} 
     */
    @UML(identifier="Control", obligation=MANDATORY, specification=ISO_19123)
    Collection<GridPointValuePair> getControl();
}
