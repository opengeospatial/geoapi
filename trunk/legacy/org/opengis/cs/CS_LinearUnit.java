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
 * Definition of linear units.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link javax.units.Unit}.
 */
public interface CS_LinearUnit extends CS_Unit {
    /**
     * Returns the number of meters per LinearUnit.
     *
     * @throws RemoteException if a remote method call failed.
     */
    double getMetersPerUnit() throws RemoteException;
}
