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
