/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link Matrix}
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class MatrixTest {
    /**
     * Creates a new test case.
     */
    public MatrixTest() {
    }

    /**
     * Tests {@link Matrix#isIdentity()}.
     */
    @Test
    public void testIdentity() {
        assertFalse(new MatrixMock(2, 1, 0).isIdentity());
        assertTrue (new MatrixMock(3, 1, 0).isIdentity());
        assertFalse(new MatrixMock(3, 0, 0).isIdentity());
        assertFalse(new MatrixMock(3, 1, 1).isIdentity());
    }
}
