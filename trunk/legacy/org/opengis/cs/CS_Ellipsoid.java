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
 * An approximation of the Earth's surface as a squashed sphere.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.referencing.datum.Ellipsoid}.
 */
public interface CS_Ellipsoid extends CS_Info {
    /**
     * Gets the equatorial radius.
     * The returned length is expressed in this object's axis units.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.Ellipsoid#getSemiMajorAxis}.
     */
    double getSemiMajorAxis() throws RemoteException;

    /**
     * Gets the polar radius.
     * The returned length is expressed in this object's axis units.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.Ellipsoid#getSemiMinorAxis}.
     */
    double getSemiMinorAxis() throws RemoteException;

    /**
     * Returns the value of the inverse of the flattening constant. The inverse
     * flattening is related to the equatorial/polar radius by the formula
     * <code>ivf=r<sub>e</sub>/(r<sub>e</sub>-r<sub>p</sub>)</code>. For perfect
     * spheres, this formula breaks down, and a special IVF value of zero is used.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.Ellipsoid#getInverseFlattening}.
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
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.Ellipsoid#isIvfDefinitive}.
     */
    boolean isIvfDefinitive() throws RemoteException;

    /**
     * Returns the LinearUnit.
     * The units of the semi-major and semi-minor axis values.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.Ellipsoid#getAxisUnit}.
     */
    CS_LinearUnit getAxisUnit() throws RemoteException;
}
