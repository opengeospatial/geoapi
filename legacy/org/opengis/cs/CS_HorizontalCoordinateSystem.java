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
 * A 2D coordinate system suitable for positions on the Earth's surface.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 */
public interface CS_HorizontalCoordinateSystem extends CS_CoordinateSystem {
    /**
     * Returns the HorizontalDatum.
     *
     * @throws RemoteException if a remote method call failed.
     */
    CS_HorizontalDatum getHorizontalDatum() throws RemoteException;
}
