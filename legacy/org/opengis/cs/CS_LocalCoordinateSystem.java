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
 * A local coordinate system, with uncertain relationship to the world. 
 * In general, a local coordinate system cannot be related to other
 * coordinate systems. However, if two objects supporting this interface
 * have the same dimension, axes, units and datum then client code
 * is permitted to assume that the two coordinate systems are identical.
 * This allows several datasets from a common source (e.g. a CAD system)
 * to be overlaid.
 * In addition, some implementations of the Coordinate Transformation (CT)
 * package may have a mechanism for correlating local datums.
 * (E.g. from a database of transformations, which is created and
 * maintained from real-world measurements.)
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.crs.EngineeringCRS}.
 */
public interface CS_LocalCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Gets the local datum.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_LocalDatum getLocalDatum() throws RemoteException;
}
