/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.crs;

// OpenGIS direct dependencies
import org.opengis.crs.cs.CartesianCS;
import org.opengis.crs.cs.ObliqueCartesianCS;
import org.opengis.crs.datum.ImageDatum;
 

/**
 * An engineering coordinate reference system applied to locations in images. Image coordinate
 * reference systems are treated as a separate sub-type because a separate user community exists
 * for images with its own terms of reference.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.crs.cs.CartesianCS        Cartesian},
 *   {@link org.opengis.crs.cs.ObliqueCartesianCS ObliqueCartesian}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_ImageCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @revisit If this is an engineering CRS, why it doesn't extends {@link EngineeringCRS}?
 */
public interface ImageCRS extends CoordinateReferenceSystem {
    /**
     * Returns the cartesian coordinate system.
     *
     * @return The cartesian coordinate system.
     * @UML association usesObliqueCartesianCS
     * @UML association usesCartesianCS
     */
/// ObliqueCartesianCS getCoordinateSystem();
	 
    /**
     * Returns the datum, which must be an image one.
     *
     * @return The datum.
     * @UML association usesDatum
     */
/// ImageDatum getDatum();
}
