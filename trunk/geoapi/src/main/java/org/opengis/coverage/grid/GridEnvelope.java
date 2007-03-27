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

import org.opengis.annotation.UML;
import org.opengis.geometry.coordinate.Position;
import org.opengis.util.Cloneable;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides the {@linkplain GridCoordinates grid coordinate} values for the diametrically opposed
 * corners of the {@linkplain Grid grid}.
 * 
 * Remark that both corners are inclusive. 
 * Thus the number of elements in the direction of the first axis is
 * {@code getHigh().getCoordinateValue(0) - getLow().getCoordinateValue(0) + 1}  
 *  
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 */
@UML(identifier="CV_GridEnvelope", specification=ISO_19123)
public interface GridEnvelope extends Cloneable {
    /**
     * Returns the minimal coordinate values for all grid points within the {@linkplain Grid grid}.
     */
    @UML(identifier="low", obligation=MANDATORY, specification=ISO_19123)
	GridCoordinates getLow();

    /**
     * Returns the maximal coordinate values for all grid points within the {@linkplain Grid grid}.
     */
    @UML(identifier="high", obligation=MANDATORY, specification=ISO_19123)
	GridCoordinates getHigh();
}
