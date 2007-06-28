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

import org.opengis.geometry.Geometry;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The presentation of an observed or measured space surrounding a sample point in the context
 * of some external {@linkplain CoordinateReferenceSystem coordinate reference system}.
 * 
 * @author Martin Schouwenburg
 * @author Wim Koolhoven
 */
@UML(identifier="CV_FootPrint", specification=ISO_19123)
public interface FootPrint {
	/**
	 * Returns the geometry that shapes the foot print. In the simplest case this
	 * can be a point, but it can also be a disc, sphere or hypersphere
	 */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
	Geometry getGeometry();

    /**
     * Returns the {@linkplain GridPoint grid point} to which this foor print corresponds.
     *
     * @see GridPoint#getFootPrint
     */
    @UML(identifier="center", obligation=MANDATORY, specification=ISO_19123)
    GridPoint getCenter();
}
