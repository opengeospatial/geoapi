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
package org.opengis.referencing;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.operation.CoordinateOperation;
import org.junit.Test;

import static org.opengis.referencing.ReferenceSystem.*;
import static org.junit.Assert.*;


/**
 * Tests the value of key constants.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 */
public final strictfp class KeyConstantsTest {
    /**
     * Ensures that the key that are expected to be the same are really the same.
     * We use {@code assertSame} instead of {@code assertEquals} because we
     * expect the JVM to have {@linkplain String#intern internalized} the strings.
     */
    @Test
    public void testSame() {
        assertSame(SCOPE_KEY,              Datum              .SCOPE_KEY);
        assertSame(SCOPE_KEY,              CoordinateOperation.SCOPE_KEY);
        assertSame(DOMAIN_OF_VALIDITY_KEY, Datum              .DOMAIN_OF_VALIDITY_KEY);
        assertSame(DOMAIN_OF_VALIDITY_KEY, CoordinateOperation.DOMAIN_OF_VALIDITY_KEY);
    }
}
