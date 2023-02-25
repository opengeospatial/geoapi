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

import java.util.List;

import org.opengis.referencing.crs.*;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests {@link CoordinateReferenceSystem} and related objects
 * from the {@code org.opengis.referencing.crs} package.
 * CRS instances are created using the authority factory given at construction time.
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * {@snippet lang="java" :
 * import org.junit.runner.RunWith;
 * import org.junit.runners.JUnit4;
 * import org.opengis.test.referencing.CRSTest;
 *
 * @RunWith(JUnit4.class)
 * public class MyTest extends CRSTest {
 *     public MyTest() {
 *         super(new MyCRSAuthorityFactory());
 *     }
 * }}
 *
 * @author  Cédric Briançon (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.3
 *
 * @deprecated Renamed as {@link AuthorityFactoryTest}.
 */
@Deprecated
@RunWith(Parameterized.class)
public strictfp class CRSTest extends ReferencingTestCase {
    /**
     * The authority factory for creating a {@link CoordinateReferenceSystem} from a code,
     * or {@code null} if none.
     */
    protected final CRSAuthorityFactory factory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead of
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code CRSTest} constructor.
     *
     * @since 3.1
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(CRSAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory  factory for creating {@link CoordinateReferenceSystem} instances.
     */
    public CRSTest(final CRSAuthorityFactory factory) {
        super(factory);
        this.factory = factory;
    }

    /**
     * Tests the creation of the WGS84 {@linkplain CoordinateReferenceSystem crs} from the
     * EPSG code.
     *
     * @throws NoSuchAuthorityCodeException if the specified code is not found among the ones present in the database.
     * @throws FactoryException if the creation of the {@link CoordinateReferenceSystem} failed for another reason.
     */
    @Test
    public void testCRSAuthorityCreation() throws NoSuchAuthorityCodeException, FactoryException {
        new AuthorityFactoryTest(factory, null, null).testWGS84();
    }
}
