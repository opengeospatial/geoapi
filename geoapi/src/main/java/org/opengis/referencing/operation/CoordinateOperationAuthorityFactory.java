/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
