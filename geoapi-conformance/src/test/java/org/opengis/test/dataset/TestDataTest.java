/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.dataset;

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests the method provided in the {@link TestData} enumeration.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class TestDataTest {
    /**
     * Tests {@link TestData#content()} on all enumeration values.
     * If a file does not have the expected length, an exception will be thrown here.
     *
     * @throws IOException if an error occurred while reading the test file.
     */
    @Test
    public void testAllContent() throws IOException {
        for (final TestData td : TestData.values()) {
            final byte[] content = td.content();
            long sum = 0;
            for (int i=0; i<content.length; i++) {
                sum += content[i] & 0xFF;               // TODO: Byte.toUnsignedLong(content[i]) on JDK8.
            }
            assertTrue(sum >= 100000);          // Arbitrary value for testing that the file is non-empty.
        }
    }
}
