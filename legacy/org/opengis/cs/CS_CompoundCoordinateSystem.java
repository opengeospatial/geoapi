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
 * An aggregate of two coordinate systems (CRS).
 * One of these is usually a CRS based on a two dimensional coordinate system
 * such as a geographic or a projected coordinate system with a horizontal
 * datum.  The other is a vertical CRS which is a one-dimensional coordinate
 * system with a vertical datum.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.crs.CompoundCRS}.
 */
public interface CS_CompoundCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Gets first sub-coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_CoordinateSystem getHeadCS() throws RemoteException;

    /**
     * Gets second sub-coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_CoordinateSystem getTailCS() throws RemoteException;
}
