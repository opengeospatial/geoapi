/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test.dataset;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the method provided in the {@link TestData} enumeration.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   3.1
 */
public final class TestDataTest {
    /**
     * Creates a new test case.
     */
    public TestDataTest() {
    }

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
                sum += Byte.toUnsignedLong(content[i]);
            }
            assertTrue(sum >= 100000);          // Arbitrary value for testing that the file is non-empty.
        }
    }
}
