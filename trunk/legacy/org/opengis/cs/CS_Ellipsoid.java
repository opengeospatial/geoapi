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
 * An approximation of the Earth's surface as a squashed sphere.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 */
public interface CS_Ellipsoid extends CS_Info {
    /**
     * Gets the equatorial radius.
     * The returned length is expressed in this object's axis units.
     *
     * @throws RemoteException if a remote method call failed.
     */
    double getSemiMajorAxis() throws RemoteException;

    /**
     * Gets the polar radius.
     * The returned length is expressed in this object's axis units.
     *
     * @throws RemoteException if a remote method call failed.
     */
    double getSemiMinorAxis() throws RemoteException;

    /**
     * Returns the value of the inverse of the flattening constant. The inverse
     * flattening is related to the equatorial/polar radius by the formula
     * <code>ivf=r<sub>e</sub>/(r<sub>e</sub>-r<sub>p</sub>)</code>. For perfect
     * spheres, this formula breaks down, and a special IVF value of zero is used.
     *
     * @throws RemoteException if a remote method call failed.
     */
    double getInverseFlattening() throws RemoteException;

    /**
     * Is the Inverse Flattening definitive for this ellipsoid?
     * Some ellipsoids use the IVF as the defining value, and calculate the
     * polar radius whenever asked. Other ellipsoids use the polar radius to
     * calculate the IVF whenever asked. This distinction can be important to
     * avoid floating-point rounding errors.
     *
     * @throws RemoteException if a remote method call failed.
     */
    boolean isIvfDefinitive() throws RemoteException;

    /**
     * Returns the LinearUnit.
     * The units of the semi-major and semi-minor axis values.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_LinearUnit getAxisUnit() throws RemoteException;
}
