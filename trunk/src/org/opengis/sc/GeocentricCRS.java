/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.cs.CartesianCS;
import org.opengis.cs.SphericalCS;
import org.opengis.cd.GeodeticDatum;


/**
 * A 3D coordinate reference system with the origin at the approximate centre of mass of the earth.
 * A geocentric CRS deals with the earth's curvature by taking a 3D spatial view, which obviates
 * the need to model the earth's curvature.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.cs.CartesianCS Cartesian},
 *   {@link org.opengis.cs.SphericalCS Spherical}
 * </TD></TR></TABLE>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GeocentricCRS extends CoordinateReferenceSystem {
    /**
     * Returns the cartesian coordinate system.
     *
     * @return The cartesian coordinate system, or <code>null</code> if none.
     * @association usesCartesianCS
     */
    public CartesianCS getCartesianCS();

    /**
     * Returns the spherical coordinate system.
     *
     * @return The spherical coordinate system, or <code>null</code> if none.
     * @association usesSphericalCS
     */
    public SphericalCS getSphericalCS();

    /**
     * Returns the datum, which must be geodetic.
     *
     * @return The datum.
     * @association usesDatum
     *
     * @revisit Change the return type from <code>Datum</code> to
     *          {@link GeodeticDatum} when the J2SE 1.5 compiler will be available.
     */
    public /*GeodeticDatum*/ org.opengis.cd.Datum getDatum();
}
