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
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Creates spatial reference objects using codes
 * The codes are maintained by an external authority. A commonly used
 * authority is EPSG, which is also used in the GeoTIFF standard.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 */
public interface CS_CoordinateSystemAuthorityFactory extends Remote {
    /**
     * Returns the authority name.
     */
    String getAuthority() throws RemoteException;

    /**
     * Returns a <code>ProjectedCoordinateSystem</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_ProjectedCoordinateSystem createProjectedCoordinateSystem(String code) throws RemoteException;

    /**
     * Returns a <code>GeographicCoordinateSystem</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_GeographicCoordinateSystem createGeographicCoordinateSystem(String code) throws RemoteException;

    /**
     * Returns a <code>HorizontalDatum</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_HorizontalDatum createHorizontalDatum(String code) throws RemoteException;

    /**
     * Returns an <code>Ellipsoid</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_Ellipsoid createEllipsoid(String code) throws RemoteException;

    /**
     * Returns a <code>PrimeMeridian</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_PrimeMeridian createPrimeMeridian(String code) throws RemoteException;

    /**
     * Returns a <code>LinearUnit</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_LinearUnit createLinearUnit(String code) throws RemoteException;

    /**
     * Returns an <code>AngularUnit</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_AngularUnit createAngularUnit(String code) throws RemoteException;

    /**
     * Creates a vertical datum from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_VerticalDatum createVerticalDatum(String code) throws RemoteException;

    /**
     * Create a vertical coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_VerticalCoordinateSystem createVerticalCoordinateSystem(String code) throws RemoteException;

    /**
     * Creates a 3D coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_CompoundCoordinateSystem createCompoundCoordinateSystem(String code) throws RemoteException;

    /**
     * Creates a horizontal co-ordinate system from a code. 
     * The horizontal coordinate system could be geographic or projected.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    CS_HorizontalCoordinateSystem createHorizontalCoordinateSystem(String code) throws RemoteException;

    /**
     * Gets a description of the object corresponding to a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    String descriptionText(String code) throws RemoteException;

    /**
     * Gets the Geoid code from a WKT name. 
     * In the OGC definition of WKT horizontal datums, the geoid is
     * referenced by a quoted string, which is used as a key value.  This
     * method converts the key value string into a code recognized by this
     * authority.
     *
     * @param wkt Name of geoid defined by OGC (e.g. "European_Datum_1950").
     * @throws RemoteException if a remote method call failed.
     */
    String geoidFromWKTName(String wkt) throws RemoteException;

    /**
     * Gets the WKT name of a Geoid. 
     * In the OGC definition of WKT horizontal datums, the geoid is
     * referenced by a quoted string, which is used as a key value.
     * This method gets the OGC WKT key value from a geoid code.
     *
     * @param geoid Code value for geoid allocated by authority.
     * @throws RemoteException if a remote method call failed.
     */
    String wktGeoidName(String geoid) throws RemoteException;
}
