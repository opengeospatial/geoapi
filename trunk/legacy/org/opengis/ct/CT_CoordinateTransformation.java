/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.ct;

// OpenGIS dependencies
import org.opengis.cs.CS_CoordinateSystem;

// JDK's classes
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Describes a coordinate transformation.
 * This interface only describes a coordinate transformation, it does not
 * actually perform the transform operation on points.  To transform
 * points you must use a math transform.
 *
 * The math transform will transform positions in the source coordinate
 * system into positions in the target coordinate system.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation}.
 */
public interface CT_CoordinateTransformation extends Remote {
    /**
     * Name of transformation.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation#getName}.
     */
    String getName() throws RemoteException;

    /**
     * Authority which defined transformation and parameter values.
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
    String getAuthority() throws RemoteException;

    /**
     * Code used by authority to identify transformation.
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
    String getAuthorityCode() throws RemoteException;

    /**
     * Gets the provider-supplied remarks.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation#getRemarks}.
     */
    String getRemarks() throws RemoteException;

    /**
     * Human readable description of domain in source coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation#getScope}.
     */
    String getAreaOfUse() throws RemoteException;

    /**
     * Semantic type of transform.
     * For example, a datum transformation or a coordinate conversion.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No direct replacement. Check for instance of
     *             {@link org.opengis.referencing.operation.Conversion} or
     *             {@link org.opengis.referencing.operation.Transformation} instead.
     */
    CT_TransformType getTransformType() throws RemoteException;

    /**
     * Source coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation#getSourceCRS}.
     */
    CS_CoordinateSystem getSourceCS() throws RemoteException;

    /**
     * Target coordinate system.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation#getTargetCRS}.
     */
    CS_CoordinateSystem getTargetCS() throws RemoteException;

    /**
     * Gets math transform.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.CoordinateOperation#getMathTransform}.
     */
    CT_MathTransform getMathTransform() throws RemoteException;
}
