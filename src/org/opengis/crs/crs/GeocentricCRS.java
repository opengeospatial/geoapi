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
import org.opengis.crs.cs.SphericalCS;
import org.opengis.crs.datum.GeodeticDatum;


/**
 * A 3D coordinate reference system with the origin at the approximate centre of mass of the earth.
 * A geocentric CRS deals with the earth's curvature by taking a 3D spatial view, which obviates
 * the need to model the earth's curvature.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.crs.cs.CartesianCS Cartesian},
 *   {@link org.opengis.crs.cs.SphericalCS Spherical}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_GeocentricCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @revisit This interface defines three methods for querying the coordinate system:
 *          {@link #getCoordinateSystem() getCoordinateSystem()}, {@link #getCartesianCS()}
 *          and {@link #getSphericalCS()}. What is the relationship between those three
 *          methods? Which coordinate system should <code>getCoordinateSystem()</code> returns?
 */
public interface GeocentricCRS extends CoordinateReferenceSystem {
    /**
     * Returns the cartesian coordinate system.
     *
     * @return The cartesian coordinate system, or <code>null</code> if none.
     * @UML association usesCartesianCS
     */
    CartesianCS getCartesianCS();

    /**
     * Returns the spherical coordinate system.
     *
     * @return The spherical coordinate system, or <code>null</code> if none.
     * @UML association usesSphericalCS
     */
    SphericalCS getSphericalCS();    
  	 
    /**
     * Returns the datum, which must be geodetic.
     *
     * @return The datum.
     * @UML association usesDatum
     */
/// GeodeticDatum getDatum();
}
