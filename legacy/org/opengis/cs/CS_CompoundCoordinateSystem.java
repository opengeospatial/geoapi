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
 * An aggregate of two coordinate systems (CRS).
 * One of these is usually a CRS based on a two dimensional coordinate system
 * such as a geographic or a projected coordinate system with a horizontal
 * datum.  The other is a vertical CRS which is a one-dimensional coordinate
 * system with a vertical datum.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.crs.CompoundCRS}.
 */
public interface CS_CompoundCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Gets first sub-coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.crs.crs.CompoundCRS#getCoordinateReferenceSystems}.
     */
    CS_CoordinateSystem getHeadCS() throws RemoteException;

    /**
     * Gets second sub-coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.crs.crs.CompoundCRS#getCoordinateReferenceSystems}.
     */
    CS_CoordinateSystem getTailCS() throws RemoteException;
}
