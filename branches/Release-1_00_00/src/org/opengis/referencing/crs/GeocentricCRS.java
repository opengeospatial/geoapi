/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.GeodeticDatum;


/**
 * A 3D coordinate reference system with the origin at the approximate centre of mass of the earth.
 * A geocentric CRS deals with the earth's curvature by taking a 3D spatial view, which obviates
 * the need to model the earth's curvature.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.CartesianCS Cartesian},
 *   {@link org.opengis.referencing.cs.SphericalCS Spherical}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_GeocentricCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface GeocentricCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system, which must be {@linkplain CartesianCS cartesian}
     * or {@linkplain SphericalCS spherical}.
     *
     * @return The cartesian or spherical coordinate system.
     * @UML association usesCartesianCS
     * @UML association usesSphericalCS
     */
    CoordinateSystem getCoordinateSystem();
  	 
    /**
     * Returns the datum, which must be geodetic.
     *
     * @return The datum.
     * @UML association usesDatum
     */
/// GeodeticDatum getDatum();
}
