/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests the {@link DataParser} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class DataParserTest {
    /**
     * Tests {@link DataParser#parseRow(String, Class[])}.
     *
     * @throws IOException if an error occurred while parsing the row.
     */
    @Test
    @SuppressWarnings("UnnecessaryBoxing")
    public void testParseRow() throws IOException {
        final Object[] values = DataParser.parseRow("8901,true,Greenwich,,\"0°\",sexagesimal degree,0",
            Integer.class, Boolean.class, String.class, String.class, String.class, String.class, Double.class);

        assertEquals("EPSG Prime Meridian Code",                Integer.valueOf(8901), values[0]);
        assertEquals("Particularly important to E&P industry?", Boolean.TRUE,          values[1]);
        assertEquals("EPSG Prime Meridian Name",                "Greenwich",           values[2]);
        assertEquals("EPSG Alias",                              null,                  values[3]);
        assertEquals("Longitude from Greenwich (sexagesimal)",  "0°",                  values[4]);
        assertEquals("Unit Name",                               "sexagesimal degree",  values[5]);
        assertEquals("Longitude from Greenwich (degrees)",      Double.valueOf(0.0),   values[6]);
    }

    /**
     * Tests loading the data from the {@code GIGS_2002_libEllipsoid.csv} file.
     * The purpose of this test is to ensure that the file is fully loaded.
     * A few sampled records are tested in this process.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    @Test
    public void testFileLoading() throws IOException {
        final int[] expectedCodes = {
            // Following records are flagged as particularly important to E&P industry.
            7001, 7002, 7003, 7004, 7046, 7007, 7008, 7009, 7011, 7012, 7015, 7044, 7016,
            7045, 7018, 7036, 7050, 7019, 7020, 7021, 7022, 7024, 7029, 7043, 7030, 7049,
            // Following records are not flagged as particularly important.
            7041, 7005, 7052, 7034, 7013, 7010, 7055, 7014, 7051, 7042, 7056, 7031, 7048,
            7053, 7058, 7057, 7025, 7032, 7033, 7027, 7059, 7054, 7028};

        // We will inspect only the first 5 columns for this test.
        final DataParser data = new DataParser("GIGS_2002_libEllipsoid.csv",
            Integer.class,      // [ 0]: EPSG Ellipsoid Code
            Boolean.class,      // [ 1]: Particularly important to E&P industry?
            String .class,      // [ 2]: EPSG Ellipsoid Name
            String .class,      // [ 3]: Alias(es) given by EPSG
            Double .class);     // [ 4]: Semi-major axis (a)

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
                    expectedImportant = false;              // Will apply also to all ellipsoids declared after 7041.
                    break;
                }
            }
            assertEquals(message, expectedImportant, isImportant);
            index++;
        }
        assertEquals("Missing records.", expectedCodes.length, index);
    }

    /**
     * Tests loading the data from the {@code GIGS_2005_libProjection.csv} file.
     * This file contains range of integer values, which is the subject of this test.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    @Test
    public void testGetInts() throws IOException {
        // We will inspect only the first column for this test.
        final DataParser data = new DataParser("GIGS_2005_libProjection.csv", String.class);
        assertTrue(data.next());
        assertEquals("16001-16060; 16101-16160", data.getString(0));
        assertArrayEquals("16001-16060; 16101-16160", new int[] {
            16001, 16002, 16003, 16004, 16005, 16006, 16007, 16008, 16009, 16010,
            16011, 16012, 16013, 16014, 16015, 16016, 16017, 16018, 16019, 16020,
            16021, 16022, 16023, 16024, 16025, 16026, 16027, 16028, 16029, 16030,
            16031, 16032, 16033, 16034, 16035, 16036, 16037, 16038, 16039, 16040,
            16041, 16042, 16043, 16044, 16045, 16046, 16047, 16048, 16049, 16050,
            16051, 16052, 16053, 16054, 16055, 16056, 16057, 16058, 16059, 16060,
            16101, 16102, 16103, 16104, 16105, 16106, 16107, 16108, 16109, 16110,
            16111, 16112, 16113, 16114, 16115, 16116, 16117, 16118, 16119, 16120,
            16121, 16122, 16123, 16124, 16125, 16126, 16127, 16128, 16129, 16130,
            16131, 16132, 16133, 16134, 16135, 16136, 16137, 16138, 16139, 16140,
            16141, 16142, 16143, 16144, 16145, 16146, 16147, 16148, 16149, 16150,
            16151, 16152, 16153, 16154, 16155, 16156, 16157, 16158, 16159, 16160
        }, data.getInts(0));

        assertTrue(data.next());
        assertEquals("16201-16260", data.getString(0));

        assertTrue(data.next());
        assertEquals("16301-16360", data.getString(0));

        assertTrue(data.next());
        assertEquals("16261-16299; 16070-16089; 16099; 16091-16094", data.getString(0));
        assertArrayEquals("16261-16299; 16070-16089; 16099; 16091-16094", new int[] {
            16261, 16262, 16263, 16264, 16265, 16266, 16267, 16268, 16269, 16270,
            16271, 16272, 16273, 16274, 16275, 16276, 16277, 16278, 16279, 16280,
            16281, 16282, 16283, 16284, 16285, 16286, 16287, 16288, 16289, 16290,
            16291, 16292, 16293, 16294, 16295, 16296, 16297, 16298, 16299, 16070,
            16071, 16072, 16073, 16074, 16075, 16076, 16077, 16078, 16079, 16080,
            16081, 16082, 16083, 16084, 16085, 16086, 16087, 16088, 16089, 16099,
            16091, 16092, 16093, 16094
        }, data.getInts(0));

        assertTrue(data.next());
        assertEquals("16362-16398 +2; 16170-16194 +2", data.getString(0));
        assertArrayEquals("16362-16398 +2; 16170-16194 +2", new int[] {
            16362, 16364, 16366, 16368, 16370, 16372, 16374, 16376, 16378, 16380,
            16382, 16384, 16386, 16388, 16390, 16392, 16394, 16396, 16398, 16170,
            16172, 16174, 16176, 16178, 16180, 16182, 16184, 16186, 16188, 16190,
            16192, 16194
        }, data.getInts(0));
    }
}
