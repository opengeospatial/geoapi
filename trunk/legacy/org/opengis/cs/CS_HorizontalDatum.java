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
 * Procedure used to measure positions on the surface of the Earth.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 */
public interface CS_HorizontalDatum extends CS_Datum {
    /**
     * Returns the Ellipsoid.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_Ellipsoid getEllipsoid() throws RemoteException;

    /**
     * Gets preferred parameters for a Bursa Wolf transformation into WGS84. 
     * The 7 returned values correspond to (dx,dy,dz) in meters, (ex,ey,ez)
     * in arc-seconds, and scaling in parts-per-million.
     * This method will always fail for horizontal datums with type CS_HD_Other.
     * This method may also fail if no suitable transformation is available.
     * Failures are indicated using the normal failing behavior of the DCP
     * (e.g. throwing an exception).
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_WGS84ConversionInfo getWGS84Parameters() throws RemoteException;
}
