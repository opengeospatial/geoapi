/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2022 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests {@link Element}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ElementTest {
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
