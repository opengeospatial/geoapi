/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.ct;

// OpenGIS dependencies
import org.opengis.cs.CS_CoordinateSystem;

// JDK's classes
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Creates coordinate transformations.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.operation.CoordinateOperationFactory}.
 */
public interface CT_CoordinateTransformationFactory extends Remote {
    /**
     * Creates a transformation between two coordinate systems.
     * This method will examine the coordinate systems in order to
     * construct a transformation between them. This method may fail if no
     * path between the coordinate systems is found, using the normal failing
     * behavior of the DCP (e.g. throwing an exception).
     *
     * @param sourceCS Input coordinate system.
     * @param targetCS Output coordinate system.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.crs.operation.CoordinateOperationFactory#createOperation}.
     */
    CT_CoordinateTransformation createFromCoordinateSystems(CS_CoordinateSystem sourceCS, CS_CoordinateSystem targetCS) throws RemoteException;
}
