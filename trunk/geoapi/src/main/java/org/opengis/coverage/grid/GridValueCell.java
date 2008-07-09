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
import org.opengis.coverage.ValueObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating within a {@linkplain ContinuousQuadrilateralGridCoverage
 * continuous quadrilateral grid coverage}. A {@code GridValueCell} is a collection
 * of {@linkplain GridPointValuePair grid point value pairs} with a geometric structure
 * defined by a {@linkplain GridCell grid cell}.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridValueCell", specification=ISO_19123)
public interface GridValueCell extends ValueObject {
    /**
     * Returns the {@linkplain GridCell grid cell} that defines the structure of the
     * {@linkplain GridPointValuePair grid point value pairs} that support the interpolation
     * of a feature attribute value at a {@linkplain DirectPosition direct position} within the
     * {@linkplain GridCell grid cell}.
     *
     * @departure
     *   ISO defines this method as a specialization of {@link #getGeometry}. We can
     *   not reflect this association in Java because of incompatible return type.
     *
     * @return The structure of grid points.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    GridCell getGridCell();

    /**
     * Returns the set of <var>grid point</var>-<var>value</var> pairs at the corners of this
     * {@code GridValueCell}.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> getControlValues();
}
