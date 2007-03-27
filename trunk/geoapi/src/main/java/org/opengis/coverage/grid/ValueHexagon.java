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

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19123;

import java.util.Set;

import org.opengis.annotation.UML;
import org.opengis.coverage.DomainObject;
import org.opengis.coverage.ValueObject;


/**
 * Basis for interpolating within a {@linkplain HexagonalGridCoverage continuous hexagonal grid
 * coverage}. A {@code ValueHexagon} is a collection of {@linkplain GridPointValuePair grid-point
 * value pairs} with a geometric structure.
 *
 * @author Alessio Fabiani
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_ValueHexagon", specification=ISO_19123)
public interface ValueHexagon extends ValueObject {
	/**
	 * Returns the geometry of the value hexagon centred on the {@linkplain GridPointValuePair
     * grid point-value pairs} identified by the {@linkplain #getControlValues control values}.
	 */
	@UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
	DomainObject getGeometry();
	
	/**
	 * Returns the <var>grid point</var>-<var>value</var> pairs at the {@code ValueHexagon} centre.
	 */
	@UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
	Set<GridPointValuePair> getControlValues();
}
