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
import org.junit.Test;

import static org.junit.Assert.*;
import static org.opengis.test.Validators.validate;


/**
 * Tests {@link CitationValidator}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class CitationValidatorTest {
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
        try {
            validate(new SimpleCitationDate(DateType.CREATION, "2000-08-01"),
                     released, lastUpdate, nextUpdate, validityBegins, validityExpires);
            fail("Shall not accept a date before the creation time.");
        } catch (AssertionError e) {
            final String msg = e.getMessage();
            assertTrue(msg, msg.contains("creation"));
            assertTrue(msg, msg.contains("validityBegins"));
        }
        try {
            validate(creation, released, lastUpdate,
                     new SimpleCitationDate(DateType.NEXT_UPDATE, "2001-08-01"),
                     validityBegins, validityExpires);
            fail("Shall not accept a 'nextUpdate' date before the 'lastUpdate' one.");
        } catch (AssertionError e) {
            final String msg = e.getMessage();
            assertTrue(msg, msg.contains("lastUpdate"));
            assertTrue(msg, msg.contains("nextUpdate"));
        }
        try {
            validate(creation, released, lastUpdate, nextUpdate, validityBegins,
                     new SimpleCitationDate(DateType.VALIDITY_EXPIRES, "2000-05-01"));
            fail("Shall not accept a 'validityExpires' date before the 'validityBegins' one.");
        } catch (AssertionError e) {
            final String msg = e.getMessage();
            assertTrue(msg, msg.contains("validityBegins"));
            assertTrue(msg, msg.contains("validityExpires"));
        }
    }
}
