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
 * Creates coordinate transformation objects from codes.
 * The codes are maintained by an external authority.
 * A commonly used authority is EPSG, which is also used
 * in the GeoTIFF standard.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 */
public interface CT_CoordinateTransformationAuthorityFactory extends Remote {
    /**
     * The name of the authority.
     * @throws RemoteException if a remote method call failed.
     */
    String getAuthority() throws RemoteException;

    /**
     * Creates a transformation from a single transformation code. 
     * The 'Authority' and 'AuthorityCode' values of the created object will be set
     * to the authority of this object, and the code specified by the client,
     * respectively. The other metadata values may or may not be set.
     *
     * @param code Coded value for transformation.
     * @throws RemoteException if a remote method call failed.
     */
    CT_CoordinateTransformation createFromTransformationCode(String code) throws RemoteException;

    /**
     * Creates a transformation from coordinate system codes.
     *
     * @param sourceCode   Coded value of source coordinate system.
     * @param targetCode   Coded value of target coordinate system.
     * @throws RemoteException if a remote method call failed.
     */
    CT_CoordinateTransformation createFromCoordinateSystemCodes(String sourceCode, String targetCode) throws RemoteException;
}
