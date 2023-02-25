/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests objects that combine all referencing sub-packages, especially {@code crs}, {@code cs} and
 * {@code datum}. The instances are created using the various factories given at construction time.
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * {@snippet lang="java" :
 * import org.junit.runner.RunWith;
 * import org.junit.runners.JUnit4;
 * import org.opengis.test.referencing.ReferencingTest;
 *
 * @RunWith(JUnit4.class)
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
@RunWith(Parameterized.class)
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
        super(datumFactory, csFactory, crsFactory, null);
    }
}
