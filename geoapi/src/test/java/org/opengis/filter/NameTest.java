/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests the {@link Name} default implementation.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class NameTest {
    /**
     * Verifies the name for {@code "fes:Literal"}.
     */
    @Test
    public void testLiteral() {
        final Name name = Name.LITERAL;
        assertEquals("fes",         name.head().toString());
        assertEquals(    "Literal", name.tail().toString());
        assertEquals("fes:Literal", name.toString());
        assertEquals("fes:Literal", name.toInternationalString().toString());
        assertEquals("fes",         name.tail().scope().name().toString());
        assertTrue(name.scope().isGlobal());
    }

    /**
     * Tests {@link Name#compareTo(GenericName)}.
     */
    @Test
    public void testCompareTo() {
        final Name before = new Name(Name.EXTENSION, "ZYX");
        final Name after  = Name.LITERAL;
        assertTrue(before.compareTo(after) < 0);
        assertTrue(after.compareTo(before) > 0);
    }
}
