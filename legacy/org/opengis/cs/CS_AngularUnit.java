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
 * Definition of angular units.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link javax.units.Unit}.
 */
@Deprecated
public interface CS_AngularUnit extends CS_Unit {
    /**
     * Returns the number of radians per AngularUnit.
     *
     * @throws RemoteException if a remote method call failed.
     */
    double getRadiansPerUnit() throws RemoteException;
}
