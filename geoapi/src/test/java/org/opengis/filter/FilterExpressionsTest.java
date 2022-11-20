/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests {@link FilterExpressions} bridge between {@link Filter} and {@link Expression} lists.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class FilterExpressionsTest {
    /**
     * Tests with {@link FilterLiteral} values.
     */
    @Test
    public void testFilterLiterals() {
        final FilterExpressions<Filter<?>> exp = new FilterExpressions<>(Arrays.asList(Filter.include(), Filter.exclude()));
        assertFalse(exp.isEmpty());
        assertEquals(2, exp.size());
        assertEquals(Boolean.TRUE,  exp.get(0).apply(null));
        assertEquals(Boolean.FALSE, exp.get(1).apply(null));
        assertEquals("[Expression[Filter.INCLUDE], Expression[Filter.EXCLUDE]]", exp.toString());
        assertTrue(exp.get(0).getParameters().isEmpty());
        assertTrue(exp.get(1).getParameters().isEmpty());
    }
}
