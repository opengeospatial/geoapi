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
package org.opengis.test.metadata;

import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.DateType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Validators.validate;


/**
 * Tests {@link CitationValidator}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class CitationValidatorTest {
    /**
     * Creates a new test case.
     */
    public CitationValidatorTest() {
    }

    /**
     * Tests {@link CitationValidator#validate(CitationDate...)}.
     */
    @Test
    public void testCitationDate() {
        final CitationDate creation        = new SimpleCitationDate(DateType.CREATION,         "2000-01-01");
        final CitationDate released        = new SimpleCitationDate(DateType.RELEASED,         "2001-01-01");
        final CitationDate lastUpdate      = new SimpleCitationDate(DateType.LAST_UPDATE,      "2002-01-01");
        final CitationDate nextUpdate      = new SimpleCitationDate(DateType.NEXT_UPDATE,      "2003-01-01");
        final CitationDate validityBegins  = new SimpleCitationDate(DateType.VALIDITY_BEGINS,  "2000-06-01");
        final CitationDate validityExpires = new SimpleCitationDate(DateType.VALIDITY_EXPIRES, "2004-01-01");
        validate(creation, released, lastUpdate, nextUpdate, validityBegins, validityExpires);

        AssertionError e;
        e = assertThrows(AssertionError.class,
                () -> validate(new SimpleCitationDate(DateType.CREATION, "2000-08-01"),
                               released, lastUpdate, nextUpdate, validityBegins, validityExpires),
                "Shall not accept a date before the creation time.");
        assertTrue(e.getMessage().contains("creation"));
        assertTrue(e.getMessage().contains("validityBegins"));

        e = assertThrows(AssertionError.class,
                () -> validate(creation, released, lastUpdate,
                               new SimpleCitationDate(DateType.NEXT_UPDATE, "2001-08-01"),
                               validityBegins, validityExpires),
                "Shall not accept a 'nextUpdate' date before the 'lastUpdate' one.");
        assertTrue(e.getMessage().contains("lastUpdate"));
        assertTrue(e.getMessage().contains("nextUpdate"));

        e = assertThrows(AssertionError.class,
                () -> validate(creation, released, lastUpdate, nextUpdate, validityBegins,
                               new SimpleCitationDate(DateType.VALIDITY_EXPIRES, "2000-05-01")),
                "Shall not accept a 'validityExpires' date before the 'validityBegins' one.");
        assertTrue(e.getMessage().contains("validityBegins"));
        assertTrue(e.getMessage().contains("validityExpires"));
    }
}
