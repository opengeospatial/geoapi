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
 * A 3D coordinate system, with its origin at the center of the Earth. 
 * The <var>X</var> axis points towards the prime meridian.
 * The <var>Y</var> axis points East or West.
 * The <var>Z</var> axis points North or South. By default the
 * <var>Z</var> axis will point North, and the <var>Y</var> axis
 * will point East (e.g. a right handed system), but you should
 * check the axes for non-default values.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.sc.GeocentricCRS}.
 */
public interface CS_GeocentricCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Returns the HorizontalDatum.
     * The horizontal datum is used to determine where the center of the Earth
     * is considered to be. All coordinate points will be measured from the
     * center of the Earth, and not the surface.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_HorizontalDatum getHorizontalDatum() throws RemoteException;

    /**
     * Gets the units used along all the axes.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_LinearUnit getLinearUnit() throws RemoteException;

    /**
     * Returns the PrimeMeridian.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_PrimeMeridian getPrimeMeridian() throws RemoteException;
}
