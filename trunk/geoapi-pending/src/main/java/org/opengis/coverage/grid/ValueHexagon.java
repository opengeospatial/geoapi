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
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.Geometry;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating within a {@linkplain HexagonalGridCoverage continuous hexagonal grid
 * coverage}. A {@code ValueHexagon} is a collection of {@linkplain GridPointValuePair grid-point
 * value pairs} with a geometric structure.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ValueHexagon", specification=ISO_19123)
public interface ValueHexagon extends ValueObject {
    /**
     * Returns the geometry of the value hexagon centred on the {@linkplain GridPointValuePair
     * grid point-value pairs} identified by the {@linkplain #getControlValues control values}.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<Geometry> getGeometry();

    /**
     * Returns the <var>grid point</var>-<var>value</var> pairs at the {@code ValueHexagon} centre.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> getControlValues();
}
