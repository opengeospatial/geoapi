/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// J2SE dependencies
import java.util.Set;

// OpenGIS direct dependencies and extensions
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;  // For javadoc
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Creates coordinate transformation objects from codes.
 * The codes are maintained by an external authority.
 * A commonly used authority is EPSG, which is also used
 * in the GeoTIFF standard.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
///@UML (identifier="CT_CoordinateTransformationAuthorityFactory")
public interface CoordinateOperationAuthorityFactory extends AuthorityFactory {
    /**
     * Creates an operation from a single operation code. 
     * The "Authority" and "Code" values of the created object will be set
     * to the authority of this object, and the code specified by the client,
     * respectively. The other metadata values may or may not be set.
     *
     * @param code Coded value for transformation.
     *
     * @throws NoSuchAuthorityCodeException if the specified <code>code</code> was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
/// @UML (identifier="createFromTransformationCode")
    CoordinateOperation createCoordinateOperation(String code) throws FactoryException;

    /**
     * Creates an operation from coordinate reference system codes.
     *
     * @param  sourceCode   Coded value of source coordinate reference system.
     * @param  targetCode   Coded value of target coordinate reference system.
     *
     * @throws NoSuchAuthorityCodeException if a specified code was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
/// @UML (identifier="createFromCoordinateSystemCodes")
    Set/*<CoordinateOperation>*/ createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException;
}
