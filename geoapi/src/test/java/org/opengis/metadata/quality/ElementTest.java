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

import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link Element}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ElementTest {
    /**
     * Creates a new test case.
     */
    public ElementTest() {
    }

    /**
     * Tests {@link Element#getDates()}.
     */
    @Test
    public void testGetDates() {
        final Instant   startTime = Instant.parse("2009-05-08T14:10:00Z");
        final Instant     endTime = Instant.parse("2009-05-12T21:45:00Z");
        final ElementImpl element = new ElementImpl(new EvaluationMethodImpl(startTime, endTime));

        @SuppressWarnings("deprecation")
        final Collection<? extends Date> dates = element.getDates();
        assertEquals(2, dates.size());
        final Iterator<? extends Date> it = dates.iterator();
        assertEquals(startTime, it.next().toInstant());
        assertEquals(endTime,   it.next().toInstant());
        assertFalse (it.hasNext());
    }
}
