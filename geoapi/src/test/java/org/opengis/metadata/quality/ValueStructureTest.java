/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2022-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.util.Set;
import java.util.List;
import java.util.SortedMap;
import java.util.ArrayList;
import java.util.Collection;
import org.opengis.referencing.operation.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link ValueStructure}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ValueStructureTest {
    /**
     * Creates a new test case.
     */
    public ValueStructureTest() {
    }

    /**
     * Tests {@link ValueStructure#valueOf(Class)}.
     */
    @Test
    public void testValueOfClass() {
        assertEquals(ValueStructure.BAG,      ValueStructure.valueOf(Collection.class).get());
        assertEquals(ValueStructure.SET,      ValueStructure.valueOf(Set.class).get());
        assertEquals(ValueStructure.TABLE,    ValueStructure.valueOf(SortedMap.class).get());
        assertEquals(ValueStructure.SEQUENCE, ValueStructure.valueOf(List.class).get());
        assertEquals(ValueStructure.SEQUENCE, ValueStructure.valueOf(ArrayList.class).get());
        assertEquals(ValueStructure.MATRIX,   ValueStructure.valueOf(Matrix.class).get());
        assertFalse(ValueStructure.valueOf(Integer.class).isPresent());
        assertFalse(ValueStructure.valueOf(Object.class).isPresent());
    }
}
