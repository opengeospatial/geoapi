/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.operation;

import java.util.Set;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates coordinate transformation objects from codes. The codes are maintained by an
 * external authority. A commonly used authority is <a href="http://www.epsg.org">EPSG</a>,
 * which is also used in the GeoTIFF standard.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="CT_CoordinateTransformationAuthorityFactory", specification=OGC_01009)
public interface CoordinateOperationAuthorityFactory extends AuthorityFactory {
    /**
     * Creates an operation method from a single code. The "{@linkplain Identifier#getAuthority
     * authority}" and "{@linkplain Identifier#getCode code}" values of the created object will be
     * set to the authority of this object, and the code specified by the client, respectively. The
     * other metadata values may or may not be set.
     *
     * @param  code  coded value for operation method.
     * @return the operation method for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see CoordinateOperationFactory#getOperationMethod(String)
     *
     * @departure extension
     *   This method has been added because OGC 01-009 does not define a factory
     *   method for creating such object.
     *
     * @since 2.3
     */
    OperationMethod createOperationMethod(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates an operation from a single operation code. The "{@linkplain Identifier#getAuthority
     * authority}" and "{@linkplain Identifier#getCode code}" values of the created object will be
     * set to the authority of this object, and the code specified by the client, respectively. The
     * other metadata values may or may not be set.
     *
     * @param  code  coded value for coordinate operation.
     * @return the operation for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createFromTransformationCode", specification=OGC_01009)
    CoordinateOperation createCoordinateOperation(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Creates operations from {@linkplain CoordinateReferenceSystem coordinate reference system}
     * codes. This method returns only the operations declared by the authority, with preferred
     * operations first. This method doesn't need to compute operations from {@code source} to
     * {@code target} CRS if no such operations were explicitly defined in the authority database.
     * Computation of arbitrary operations can be performed by
     * <code>{@linkplain CoordinateOperationFactory#createOperation(CoordinateReferenceSystem,
     * CoordinateReferenceSystem) CoordinateOperationFactory.createOperation}(sourceCRS, targetCRS)</code>
     * instead.
     *
     * @param  sourceCRS  coded value of source coordinate reference system.
     * @param  targetCRS  coded value of target coordinate reference system.
     * @return the operations from {@code sourceCRS} to {@code targetCRS}.
     * @throws NoSuchAuthorityCodeException if a specified code was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createFromCoordinateSystemCodes", specification=OGC_01009)
    Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS)
            throws NoSuchAuthorityCodeException, FactoryException;
}
