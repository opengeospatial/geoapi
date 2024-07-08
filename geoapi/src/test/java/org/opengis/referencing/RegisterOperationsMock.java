/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
package org.opengis.referencing;

import java.util.Set;
import java.util.Optional;
import org.opengis.util.Factory;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;


/**
 * A dummy implementation of register operations.
 * This class keep the default implementation of all methods having such default.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class RegisterOperationsMock implements RegisterOperations {
    /**
     * Creates a new mock.
     */
    RegisterOperationsMock() {
    }

    /**
     * Returns a factory used for building <abbr>CRS</abbr> or coordinate operations.
     * For testing purposes, this method supports only {@link CRSAuthorityFactory} and
     * {@link CoordinateOperationAuthorityFactory}.
     */
    @Override
    public <T extends Factory> Optional<T> getFactory(final Class<? extends T> factoryType) {
        final Class<?> expectedType;
        final String code;
        if (factoryType == CRSAuthorityFactory.class) {
            expectedType = CoordinateReferenceSystem.class;
            code = "dummy CRS";
        } else if (factoryType == CoordinateOperationAuthorityFactory.class) {
            expectedType = CoordinateOperation.class;
            code = "dummy operation";
        } else {
            return RegisterOperations.super.getFactory(factoryType);
        }
        final class Factory extends AuthorityFactoryMock implements CRSAuthorityFactory, CoordinateOperationAuthorityFactory {
            /** Returns a dummy code if and only if the argument is the type expected by this factory. */
            @Override public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> objectType) {
                return expectedType.isAssignableFrom(objectType) ? Set.of(code) : Set.of();
            }

            /** Creates an object mock for the given code. */
            @Override public CoordinateReferenceSystem createCoordinateReferenceSystem(final String code) {
                return new IdentifiedObjectMock(code);
            }
        }
        return Optional.of(factoryType.cast(new Factory()));
    }

    /**
     * Not used for the tests.
     */
    @Override
    public Set<CoordinateOperation> findCoordinateOperations(CoordinateReferenceSystem source,
                                                             CoordinateReferenceSystem target)
            throws UnimplementedServiceException
    {
        throw new UnimplementedServiceException("Not tested");
    }

    /**
     * Not used for the tests.
     */
    @Override
    public boolean areMembersOfSameEnsemble(CoordinateReferenceSystem source,
                                            CoordinateReferenceSystem target)
            throws UnimplementedServiceException
    {
        throw new UnimplementedServiceException("Not tested");
    }
}
