/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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

import java.util.Optional;
import java.util.Set;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.RegisterOperations;
import org.opengis.util.Factory;


/**
 * Tests objects that combine all referencing sub-packages, especially {@code crs}, {@code cs} and
 * {@code datum}. The instances are created using the various factories given at construction time.
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * {@snippet lang="java" :
 * import org.opengis.test.referencing.ReferencingTest;
 *
 * public class MyTest extends ReferencingTest {
 *     public MyTest() {
 *         super(new MyCRSFactory(), new MyCSFactory(), new MyDatumFactory());
 *     }
 * }}
 *
 * @author  Cédric Briançon (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.3
 *
 * @deprecated Renamed as {@link ObjectFactoryTest}.
 */
@Deprecated
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp class ReferencingTest extends ObjectFactoryTest {
    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory    factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory     factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory  factory for creating {@link Datum} instances.
     */
    public ReferencingTest(final CRSFactory crsFactory, final CSFactory csFactory, final DatumFactory datumFactory) {
        super(new RegisterOperations() {
            @Override
            public <T extends Factory> Optional<T> getFactory(Class<? extends T> type) {
                final Factory factory;
                if (type == CRSFactory.class) factory = crsFactory;
                else if (type == CSFactory.class) factory = csFactory;
                else if (type == DatumFactory.class) factory = datumFactory;
                else return RegisterOperations.super.getFactory(type);
                return Optional.ofNullable(type.cast(factory));
            }

            @Override
            public Set<CoordinateOperation> findCoordinateOperations(CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
                return Set.of();
            }

            @Override
            public boolean areMembersOfSameEnsemble(CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
                return false;
            }
        });
    }
}
