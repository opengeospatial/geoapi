/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link ValidatorContainer}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public final class ValidatorContainerTest {
    /**
     * Creates a new test case.
     */
    public ValidatorContainerTest() {
    }

    /**
     * Ensures that {@link ValidatorContainer#all} contains no duplicated value.
     * Also ensures that the declared list size is the expected one.
     */
    @Test
    public void testAll() {
        final Set<Validator> previous = new HashSet<>();
        final ValidatorContainer container = new ValidatorContainer();
        for (final Validator validator : container.all) {
            assertTrue(previous.add(validator), "Found a duplicated value.");
        }
        assertFalse(previous.remove(null), "Found a null value.");
        assertEquals(previous.size(), container.all.size(), "Declared size is wrong.");
    }
}
