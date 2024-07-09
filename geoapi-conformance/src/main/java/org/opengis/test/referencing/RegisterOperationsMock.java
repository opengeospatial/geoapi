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
package org.opengis.test.referencing;

import java.util.Map;
import java.util.Set;
import java.util.Optional;
import org.opengis.util.Factory;
import org.opengis.referencing.RegisterOperations;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransformFactory;


/**
 * Mock class for register operations.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
final strictfp class RegisterOperationsMock implements RegisterOperations {
    /**
     * The factories to test.
     */
    private final Map<Class<?>, Factory> factories;

    /**
     * Creates a new register operations mock.
     *
     * @param factories the factories to test.
     */
    RegisterOperationsMock(final MathTransformFactory mtFactory) {
        factories = Map.of(MathTransformFactory.class, mtFactory);
    }

    /**
     * Should never been invoked for the tests.
     */
    @Override
    public Set<CoordinateOperation> findCoordinateOperations(CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
        throw new UnsupportedOperationException();
    }

    /**
     * Should never been invoked for the tests.
     */
    @Override
    public boolean areMembersOfSameEnsemble(CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the factory of the specified type.
     */
    @Override
    public <T extends Factory> Optional<T> getFactory(Class<? extends T> type) {
        return Optional.ofNullable(type.cast(factories.get(type)));
    }
}
