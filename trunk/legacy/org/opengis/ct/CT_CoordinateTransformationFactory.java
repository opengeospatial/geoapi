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
     */
    CT_CoordinateTransformation createFromCoordinateSystems(CS_CoordinateSystem sourceCS, CS_CoordinateSystem targetCS) throws RemoteException;
}
