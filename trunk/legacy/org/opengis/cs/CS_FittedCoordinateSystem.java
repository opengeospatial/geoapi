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
 * A coordinate system which sits inside another coordinate system. 
 * The fitted coordinate system can be rotated and shifted, or use
 * any other math transform to inject itself into the base coordinate
 * system.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.crs.DerivedCRS}.
 */
public interface CS_FittedCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Gets underlying coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_CoordinateSystem getBaseCoordinateSystem() throws RemoteException;

    /**
     * Gets Well-Known Text of a math transform to the base coordinate system.
     * The dimension of this fitted coordinate system is
     * determined by the source dimension of the math transform.  
     * The transform should be one-to-one within this coordinate
     * system's domain, and the base coordinate system dimension must be
     * at least as big as the dimension of this coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     */
    String getToBase() throws RemoteException;
}
