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

// Various JDK's classes
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * A base interface for metadata applicable to coordinate system objects.
 * The metadata items 'Abbreviation', 'Alias', 'Authority', 'AuthorityCode',
 * 'Name' and 'Remarks' were specified in the Simple Features interfaces,
 * so they have been kept here.
 *
 * This specification does not dictate what the contents of these items should
 * be.  However, the following guidelines are suggested:
 * <ul>
 *   <li>When {@link CS_CoordinateSystemAuthorityFactory} is used to create
 *       an object, the 'Authority' and 'AuthorityCode' values should be set
 *       to the authority name of the factory object, and the authority code
 *       supplied by the client, respectively.  The other values may or may
 *       not be set.  (If the authority is EPSG, the implementer may consider
 *       using the corresponding metadata values in the EPSG tables.)</li>
 *   <li>When {@link CS_CoordinateSystemFactory} creates an object, the 'Name'
 *       should be set to the value supplied by the client.  All of the other
 *       metadata items should be left empty.</li>
 * </ul>
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.referencing.IdentifiedObject} and {@link org.opengis.metadata.Identifier}.
 */
@Deprecated
public interface CS_Info extends Remote {
    /**
     * Gets the name.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.IdentifiedObject#getName}.
     */
    @Deprecated
    String getName() throws RemoteException;

    /**
     * Gets the authority name.
     * An Authority is an organization that maintains definitions of Authority
     * Codes.  For example the European Petroleum Survey Group (EPSG) maintains
     * a database of coordinate systems, and other spatial referencing objects,
     * where each object has a code number ID.  For example, the EPSG code for a
     * WGS84 Lat/Lon coordinate system is '4326'.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.Identifier#getAuthority}.
     */
    @Deprecated
    String getAuthority() throws RemoteException;

    /**
     * Gets the authority-specific identification code.
     * The AuthorityCode is a compact string defined by an Authority to reference
     * a particular spatial reference object.  For example, the European Survey
     * Group (EPSG) authority uses 32 bit integers to reference coordinate systems,
     * so all their code strings will consist of a few digits.  The EPSG code for
     * WGS84 Lat/Lon is '4326'.
     *
     * An empty string is used for no code.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.Identifier#getCode}.
     */
    @Deprecated
    String getAuthorityCode() throws RemoteException;

    /**
     * Gets the alias.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.IdentifiedObject#getIdentifiers}.
     */
    @Deprecated
    String getAlias() throws RemoteException;

    /**
     * Gets the abbreviation.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No replacement.
     */
    @Deprecated
    String getAbbreviation() throws RemoteException;

    /**
     * Gets the provider-supplied remarks.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.IdentifiedObject#getRemarks}.
     */
    @Deprecated
    String getRemarks() throws RemoteException;

    /**
     * Gets a Well-Known text representation of this object.
     *
     * @throws RemoteException if a remote method call failed.
     */
    String getWKT() throws RemoteException;

    /**
     * Gets an XML representation of this object.
     *
     * @throws RemoteException if a remote method call failed.
     */
    String getXML() throws RemoteException;
}
