/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the {@link Version} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class VersionTest {
    /**
     * Creates a new test case.
     */
    public VersionTest() {
    }

    /**
     * Tests the version numbers having the documented pattern.
     * This is by contrast with informal pattern that we could
     * eventually support in a future version.
     */
    @Test
    public void testDocumentedVersions() {
        final Version v301 = new Version("3.0.1");
        assertEquals(3, v301.major);
        assertEquals(0, v301.minor);
        assertEquals(1, v301.third);
        assertFalse (v301.isMilestone());
        assertEquals("3.0.1", v301.toString());

        final Version v310 = new Version("3.1.0");
        assertEquals(3, v310.major);
        assertEquals(1, v310.minor);
        assertEquals(0, v310.third);
        assertFalse (v310.isMilestone());
        assertEquals("3.1.0", v310.toString());

        final Version v312 = new Version("3.1.2");
        assertEquals(3, v312.major);
        assertEquals(1, v312.minor);
        assertEquals(2, v312.third);
        assertFalse (v312.isMilestone());
        assertEquals("3.1.2", v312.toString());

        final Version v31M = new Version("3.1-M07");
        assertEquals(3, v31M.major);
        assertEquals(1, v31M.minor);
        assertEquals(0, v31M.third);
        assertTrue  (v31M.isMilestone());
        assertEquals("3.1-M07", v31M.toString());

        assertTrue(v301.compareTo(v301) == 0);
        assertTrue(v301.compareTo(v310) <  0);
        assertTrue(v301.compareTo(v312) <  0);
        assertTrue(v301.compareTo(v31M) <  0);
        assertTrue(v310.compareTo(v301) >  0);
        assertTrue(v310.compareTo(v310) == 0);
        assertTrue(v310.compareTo(v312) <  0);
        assertTrue(v310.compareTo(v31M) >  0);
        assertTrue(v312.compareTo(v301) >  0);
        assertTrue(v312.compareTo(v310) >  0);
        assertTrue(v312.compareTo(v312) == 0);
        assertTrue(v312.compareTo(v31M) >  0);
        assertTrue(v31M.compareTo(v301) >  0);
        assertTrue(v31M.compareTo(v310) <  0);
        assertTrue(v31M.compareTo(v312) <  0);
        assertTrue(v31M.compareTo(v31M) == 0);
    }
}
