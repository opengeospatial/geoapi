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
 *
 * @deprecated Replaced by {@link org.opengis.referencing.crs.CRSAuthorityFactory}
 *             and {@link org.opengis.referencing.datum.DatumAuthorityFactory}.
 */
public interface CS_CoordinateSystemAuthorityFactory extends Remote {
    /**
     * Returns the authority name.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.AuthorityFactory#getAuthority}.
     */
    String getAuthority() throws RemoteException;

    /**
     * Returns a <code>ProjectedCoordinateSystem</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS}.
     */
    CS_ProjectedCoordinateSystem createProjectedCoordinateSystem(String code) throws RemoteException;

    /**
     * Returns a <code>GeographicCoordinateSystem</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS}.
     */
    CS_GeographicCoordinateSystem createGeographicCoordinateSystem(String code) throws RemoteException;

    /**
     * Returns a <code>HorizontalDatum</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum}.
     */
    CS_HorizontalDatum createHorizontalDatum(String code) throws RemoteException;

    /**
     * Returns an <code>Ellipsoid</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#createEllipsoid}.
     */
    CS_Ellipsoid createEllipsoid(String code) throws RemoteException;

    /**
     * Returns a <code>PrimeMeridian</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#createPrimeMeridian}.
     */
    CS_PrimeMeridian createPrimeMeridian(String code) throws RemoteException;

    /**
     * Returns a <code>LinearUnit</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#createUnit}.
     */
    CS_LinearUnit createLinearUnit(String code) throws RemoteException;

    /**
     * Returns an <code>AngularUnit</code> object from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#createUnit}.
     */
    CS_AngularUnit createAngularUnit(String code) throws RemoteException;

    /**
     * Creates a vertical datum from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#createVerticalDatum}.
     */
    CS_VerticalDatum createVerticalDatum(String code) throws RemoteException;

    /**
     * Create a vertical coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS}.
     */
    CS_VerticalCoordinateSystem createVerticalCoordinateSystem(String code) throws RemoteException;

    /**
     * Creates a 3D coordinate system from a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.crs.CRSAuthorityFactory#createCompoundCRS}.
     */
    CS_CompoundCoordinateSystem createCompoundCoordinateSystem(String code) throws RemoteException;

    /**
     * Creates a horizontal co-ordinate system from a code. 
     * The horizontal coordinate system could be geographic or projected.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No replacement.
     */
    CS_HorizontalCoordinateSystem createHorizontalCoordinateSystem(String code) throws RemoteException;

    /**
     * Gets a description of the object corresponding to a code.
     *
     * @param code Value allocated by authority.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.AuthorityFactory#getDescriptionText}.
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
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#geoidFromWKTName}.
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
     *
     * @deprecated Replaced by {@link org.opengis.referencing.datum.DatumAuthorityFactory#wktGeoidName}.
     */
    String wktGeoidName(String geoid) throws RemoteException;
}
