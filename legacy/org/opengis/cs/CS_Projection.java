/*
 * OpenGIS® Coordinate Transformation Services Implementation Specification
 * Copyright (2001) OpenGIS consortium
 *
 * THIS COPYRIGHT NOTICE IS A TEMPORARY PATCH.   Version 1.00 of official
 * OpenGIS's interface files doesn't contain a copyright notice yet. This
 * file is a slightly modified version of official OpenGIS's interface.
 * Changes have been done in order to fix RMI problems and are documented
 * on the SEAGIS web site (seagis.sourceforge.net). THIS FILE WILL LIKELY
 * BE REPLACED BY NEXT VERSION OF OPENGIS SPECIFICATIONS.
 */
package org.opengis.crs.cs;

// JDK's classes
import java.rmi.RemoteException;


/**
 * A projection from geographic coordinates to projected coordinates.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.operation.OperationMethod}.
 */
public interface CS_Projection extends CS_Info {
    /**
     * Gets number of parameters of the projection.
     *
     * @throws RemoteException if a remote method call failed.
     */
    int getNumParameters() throws RemoteException;

    /**
     * Gets an indexed parameter of the projection.
     *
     * @param index Zero based index of parameter to fetch.
     * @throws RemoteException if a remote method call failed.
     */
    CS_ProjectionParameter getParameter(int index) throws RemoteException;

    /**
     * Gets the projection classification name (e.g. 'Transverse_Mercator').
     *
     * @throws RemoteException if a remote method call failed.
     */
    String getClassName() throws RemoteException;
}
