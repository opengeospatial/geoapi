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
 * A 2D coordinate system suitable for positions on the Earth's surface.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 */
public interface CS_HorizontalCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Returns the HorizontalDatum.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_HorizontalDatum getHorizontalDatum() throws RemoteException;
}
