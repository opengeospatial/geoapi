/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
public strictfp class CitationValidatorTest {
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
