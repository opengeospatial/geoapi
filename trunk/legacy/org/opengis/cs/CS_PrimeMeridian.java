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
 * A meridian used to take longitude measurements from.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.crs.datum.PrimeMeridian}.
 */
public interface CS_PrimeMeridian extends CS_Info {
    /**
     * Returns the longitude value relative to the Greenwich Meridian.
     * The longitude is expressed in this objects angular units.
     *
     * @throws RemoteException if a remote method call failed.
     */
    double getLongitude() throws RemoteException;

    /**
     * Returns the AngularUnits.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_AngularUnit getAngularUnit() throws RemoteException;
}
