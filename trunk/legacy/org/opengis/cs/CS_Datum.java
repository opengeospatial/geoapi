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
 * A set of quantities from which other quantities are calculated.
 * For the OGC abstract model, it can be defined as a set of real points on
 * the earth that have coordinates. EG. A datum can be thought of as a set of
 * parameters defining completely the origin and orientation of a coordinate
 * system with respect to the earth.
 * <br><br>
 * A textual description and/or a set of parameters describing the
 * relationship of a coordinate system to some predefined physical locations
 * (such as center of mass) and physical directions (such as axis of spin).
 * The definition of the datum may also include the temporal behavior (such
 * as the rate of change of the orientation of the coordinate axes).
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated Replaced by {@link org.opengis.crs.datum.Datum}.
 */
public interface CS_Datum extends CS_Info {
    /**
     * Gets the type of the datum as an enumerated code.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_DatumType getDatumType() throws RemoteException;
}
