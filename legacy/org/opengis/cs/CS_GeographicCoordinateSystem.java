/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.cs;

// JDK's classes
import java.rmi.RemoteException;


/**
 * A coordinate system based on latitude and longitude.
 * Some geographic coordinate systems are Lat/Lon, and some are Lon/Lat.
 * You can find out which this is by examining the axes.  You should also
 * check the angular units, since not all geographic coordinate systems use
 * degrees.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.crs.GeographicCRS}.
 */
public interface CS_GeographicCoordinateSystem extends CS_HorizontalCoordinateSystem { 
    /**
     * Returns the AngularUnit.
     * The angular unit must be the same as the {@link CS_CoordinateSystem} units.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.crs.cs.CoordinateSystemAxis#getUnit}.
     */
    CS_AngularUnit getAngularUnit() throws RemoteException;

    /**
     * Returns the PrimeMeridian.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.crs.datum.GeodeticDatum#getPrimeMeridian}.
     */
    CS_PrimeMeridian getPrimeMeridian() throws RemoteException;

    /**
     * Gets the number of available conversions to WGS84 coordinates.
     *
     * @throws RemoteException if a remote method call failed.
     */
    int getNumConversionToWGS84() throws RemoteException;

    /**
     * Gets details on a conversion to WGS84.
     * Some geographic coordinate systems provide several transformations
     * into WGS84, which are designed to provide good accuracy in different
     * areas of interest.  The first conversion (with index=0) should
     * provide acceptable accuracy over the largest possible area of
     * interest.
     *
     * @param index Zero based index of conversion to fetch.
     * @throws RemoteException if a remote method call failed.
     */
    CS_WGS84ConversionInfo getWGS84ConversionInfo(int index) throws RemoteException;
}
