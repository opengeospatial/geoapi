/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Set;
import java.util.HashSet;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests {@link ValidatorContainer}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class ValidatorContainerTest {
    /**
     * Ensures that {@link ValidatorContainer#all} contains no duplicated value.
     * Also ensures that the declared list size is the expected one.
     */
    @Test
    public void testAll() {
        final Set<Validator> previous = new HashSet<>();
        final ValidatorContainer container = new ValidatorContainer();
        for (final Validator validator : container.all) {
            assertTrue("Found a duplicated value.", previous.add(validator));
        }
        assertFalse("Found a null value.", previous.remove(null));
        assertEquals("Declared size is wrong.", previous.size(), container.all.size());
    }
}
