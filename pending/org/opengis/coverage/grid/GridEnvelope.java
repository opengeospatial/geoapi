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

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides the {@linkplain GridCoordinate grid coordinate} values for the diametrically opposed
 * corners of the (@linkplain Grid grid).
 *  
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 */
@UML(identifier="CV_GridEnvelope", specification=ISO_19123)
public interface GridEnvelope {
    /**
     * Returns the minimal coordinate values for all grid points within the (@linkplain Grid grid).
     */
    @UML(identifier="low", obligation=MANDATORY, specification=ISO_19123)
	GridCoordinates getLow();

    /**
     * Returns the maximal coordinate values for all grid points within the (@linkplain Grid grid).
     */
    @UML(identifier="high", obligation=MANDATORY, specification=ISO_19123)
	GridCoordinates getHigh();
}
