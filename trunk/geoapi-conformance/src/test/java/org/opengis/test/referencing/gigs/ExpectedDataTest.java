/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests the {@link ExpectedData} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class ExpectedDataTest {
    /**
     * Tests loading the data from the
     * <a href="{@svnurl gigs}/GIGS_2002_libEllipsoid.csv">{@code GIGS_2002_libEllipsoid.csv}</a> file.
     */
    @Test
    public void testEllipsoid() {
        final int[] expectedCodes = {
            // Following records are flagged as particularly important to E&P industry.
            7001, 7002, 7003, 7004, 7046, 7007, 7008, 7009, 7011, 7012, 7015, 7044, 7016,
            7045, 7018, 7036, 7050, 7019, 7020, 7021, 7022, 7024, 7029, 7043, 7030, 7049,
            // Following records are not flagged as particularly important.
            7041, 7005, 7052, 7034, 7013, 7010, 7055, 7014, 7051, 7042, 7056, 7031, 7048,
            7053, 7058, 7057, 7025, 7032, 7033, 7027, 7059, 7054, 7028};

        // We will inspect only the first 5 columns for this test.
        final ExpectedData data = new ExpectedData("GIGS_2002_libEllipsoid.csv",
            Integer.class,  // [ 0]: EPSG Ellipsoid Code
            Boolean.class,  // [ 1]: Particularly important to E&P industry?
            String .class,  // [ 2]: EPSG Ellipsoid Name
            String .class,  // [ 3]: Alias(es) given by EPSG
            Double .class); // [ 4]: Semi-major axis (a)

        int index = 0;
        boolean expectedImportant = true;
        while (data.next()) {
            final int      code        = data.getInt    (0);
            final boolean  isImportant = data.getBoolean(1);
            final String   name        = data.getString (2);
            final String[] aliases     = data.getStrings(3);
            final double   semiMajor   = data.getDouble (4);
            final String   message     = "EPSG:" + code;
            assertEquals (message, expectedCodes[index], code);
            assertNotNull(message, name);
            assertFalse  (message, name.isEmpty());
            assertNotNull(message, aliases);
            assertTrue   (message, semiMajor > 0);
            switch (code) {
                case 7043: {
                    assertEquals(message, "WGS 72", name);
                    assertEquals(message, 6378135,  semiMajor, 0);
                    assertArrayEquals(message, new String[] {"NWL 10D"}, aliases);
                    break;
                }
                case 7030: {
                    assertEquals(message, "WGS 84", name);
                    assertEquals(message, 6378137,  semiMajor, 0);
                    assertArrayEquals(message, new String[] {"WGS84"}, aliases);
                    break;
                }
                case 7013: {
                    assertEquals(message, "Clarke 1880 (Arc)", name);
                    assertEquals(message, 6378249.145, semiMajor, 1E-4);
                    assertArrayEquals(message, new String[] {"Modified Clarke 1880 (South Africa)", "Clarke 1880 (Cape)"}, aliases);
                    break;
                }
                case 7041: {
                    expectedImportant = false; // Will apply also to all ellipsoids declared after 7041.
                    break;
                }
            }
            assertEquals(message, expectedImportant, isImportant);
            index++;
        }
        assertEquals("Missing records.", expectedCodes.length, index);
    }
}
