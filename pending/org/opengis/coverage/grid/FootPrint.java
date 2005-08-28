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

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The presentation of a observed or measured space surrounding a sample point in the context of some external
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}.
 * 
 * @author Martin Schouwenburg
 * @author Wim Koolhoven
 */
@UML(identifier="CV_FootPrint", specification=ISO_19123)
public interface FootPrint {
	/**
	 * Returns the geometry that shapes the foot print. In the simplest case this
	 * can be a point, but i can also be a disc, spehere or hypersphere
	 */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
	Geometry getGeometry();
    
    /**
     * Returns the {@linkplain GridPoint grid point} to which this foor print corresponds.
     */
    @UML(identifier="SampleSpace", obligation=MANDATORY, specification=ISO_19123)
    GridPoint getSampleSpace();
}
