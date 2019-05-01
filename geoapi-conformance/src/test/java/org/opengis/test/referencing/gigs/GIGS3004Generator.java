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
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import static org.junit.Assert.*;


/**
 * Code generator for {@link GIGS3004}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS3004Generator extends TestMethodGenerator {
    /**
     * The mapping from GIGS ellipsoid names to test method names.
     */
    static final Map<String,String> METHOD_NAMES;
    static {
        final Map<String,String> m = new HashMap<>();
        assertNull(m.put("GIGS geodetic datum A",  "testWGS84"));
        assertNull(m.put("GIGS geodetic datum AA", "testWGS84_bis"));
        assertNull(m.put("GIGS geodetic datum B",  "testOSGB1936"));
        assertNull(m.put("GIGS geodetic datum BB", "testOSGB1936_bis"));
        assertNull(m.put("GIGS geodetic datum B′", "testOSGB1936_p1"));
        assertNull(m.put("GIGS geodetic datum C",  "testAmersfoort"));
        assertNull(m.put("GIGS geodetic datum CC", "testAmersfoort_bis"));
        assertNull(m.put("GIGS geodetic datum C′", "testAmersfoort_p1"));
        assertNull(m.put("GIGS geodetic datum D",  "testBatavia_Jakarta"));
        assertNull(m.put("GIGS geodetic datum DD", "testBatavia_Jakarta_bis"));
        assertNull(m.put("GIGS geodetic datum E",  "testBelge1972"));
        assertNull(m.put("GIGS geodetic datum EE", "testBelge1972_bis"));
        assertNull(m.put("GIGS geodetic datum E′", "testBelge1972_p1"));
        assertNull(m.put("GIGS geodetic datum F",  "testGDA94"));
        assertNull(m.put("GIGS geodetic datum FF", "testGDA94_bis"));
        assertNull(m.put("GIGS geodetic datum G",  "testETRS89"));
        assertNull(m.put("GIGS geodetic datum H",  "testNTF_Paris"));
        assertNull(m.put("GIGS geodetic datum HH", "testNTF_Paris_bis"));
        assertNull(m.put("GIGS geodetic datum J",  "testNAD27"));
        assertNull(m.put("GIGS geodetic datum J′", "testNAD27_p1"));
        assertNull(m.put("GIGS geodetic datum J″", "testNAD27_p2"));
        assertNull(m.put("GIGS geodetic datum J‴", "testNAD27_p3"));
        assertNull(m.put("GIGS geodetic datum K",  "testHD72"));
        assertNull(m.put("GIGS geodetic datum L",  "testBatavia"));
        assertNull(m.put("GIGS geodetic datum M",  "testED50"));
        assertNull(m.put("GIGS geodetic datum T",  "testNTF"));
        assertNull(m.put("GIGS geodetic datum X",  "testAGD66"));
        assertNull(m.put("GIGS geodetic datum X′", "testAGD66_p1"));
        assertNull(m.put("GIGS geodetic datum Y",  "testPulkovo1942"));
        assertNull(m.put("GIGS geodetic datum Z",  "testNAD83"));
        assertNull(m.put("GIGS geodetic datum ZZ", "testNAD83_bis"));
        METHOD_NAMES = Collections.unmodifiableMap(m);
    }

    /**
     * Launcher.
     *
     * @param  args  ignored.
     * @throws IOException if an error occurred while reading the test data.
     */
    public static void main(String[] args) throws IOException {
        new GIGS3004Generator().run();
    }

    /**
     * Generates the code.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    private void run() throws IOException {
        final DataParser data = new DataParser("GIGS_3004_userGeodeticDatumCRS.csv",
                Integer.class,    // [ 0]: User Datum code
                String.class,     // [ 1]: User Datum code name
                String.class,     // [ 2]: GIGS User-defined Ellipsoid Name (see test 3002)
                String.class,     // [ 3]: GIGS User-defined Prime Meridian Name (see test 3003)
                String.class,     // [ 4]: Datum origin
                Integer.class,    // [ 5]: GIGS User-defined CRS Code
                String.class,     // [ 6]: GIGS CRS Name
                String.class,     // [ 7]: EPSG CRS Type
                Integer.class,    // [ 8]: GIGS datum code (see column 0)
                Integer.class,    // [ 9]: EPSG coordinate system code
                String.class,     // [10]: Remarks regarding CRS definition
                String.class,     // [11]: Equivalent EPSG CRS codes (may be a list)
                String.class,     // [12]: Equivalent EPSG CRS names (may be a list)
                Integer.class);   // [13]: Early binding transformation code (see test 3007)
        /*
         * Each line define both a datum and a CRS. However some lines omit the datum
         * definition, in which case the datum from the previous line shall be reused.
         * Note: strictly speaking, the previous datum to use is specified in column 8.
         * But this is always the previous column (this is verified in this method).
         */
        int    datumCode     = 0;
        String datumName     = null;
        String ellipsoidName = null;
        String meridianName  = null;
        String anchorPoint   = null;
        while (data.next()) {
            final Integer newDatumCode = data.getIntOptional(0);
            if (newDatumCode != null) {
                datumCode     = newDatumCode;
                datumName     = data.getString(1);
                ellipsoidName = data.getString(2);
                meridianName  = data.getString(3);
                anchorPoint   = data.getString(4);
                datumName = replaceAsciiPrimeByUnicode(datumName);
            }
            assertEquals("GIGS code in column 8 shall be equal to the code in column 0"
                    + " of either the same row if this row contains such value,"
                    + " or of the previous row otherwise.", data.getInt(8), datumCode);     // See the comment before the loop.

            final int      crsCode  = data.getInt    ( 5);
            final String   crsName  = replaceAsciiPrimeByUnicode(data.getString(6));
            final String   crsType  = data.getString ( 7);
            final int      csCode   = data.getInt    ( 9);
            final String   remarks  = data.getString (10);
            final int[]    codeEPSG = data.getInts   (11);
            final String[] nameEPSG = data.getStrings(12);
            /*
             * If the current line needs a new datum, start a new method since each method
             * is for exactly one datum. Otherwise continue writing code in the same method.
             */
            if (newDatumCode != null) {
                indent(1); out.println('}');    // Close previous method.
                out.println();
                indent(1); out.println("/**");
                indent(1); out.print(" * Tests “");
                out.print(datumName); out.println("” geodetic datum creation from the factory.");
                indent(1); out.println(" *");
                printJavadocKeyValues("GIGS datum code", datumCode,
                                      "GIGS datum name", datumName,
                                      "GIGS datum anchor point", anchorPoint,
                                      "Ellipsoid name", ellipsoidName,
                                      "Prime meridian name", meridianName,
                                      "GIGS coordinate reference system using the datum", codeAndName(crsCode, crsName),
                                      "EPSG equivalence", codeAndName(codeEPSG, nameEPSG),
                                      "Specific usage / Remarks", remarks);
                printJavadocThrows("if an error occurred while creating the datum or a CRS from the properties.");
                /*
                 * Begin writing the test method. Stop after datum creation.
                 */
                printTestMethodSignature(METHOD_NAMES, datumName);
                printCallToSetCodeAndName(datumCode, datumName);
                if (anchorPoint != null) {
                    indent(2);
                    out.print("assertNull(GeodeticDatum.ANCHOR_POINT_KEY, properties.put(GeodeticDatum.ANCHOR_POINT_KEY, \"");
                    out.print(anchorPoint);
                    out.println("\"));");
                }
                indent(2);
                out.print("ellipsoidData.");
                out.print(GIGS3002Generator.METHOD_NAMES.get(ellipsoidName));
                out.println("();");
                indent(2);
                out.print("primeMeridianData.");
                out.print(GIGS3003Generator.METHOD_NAMES.get(meridianName));
                out.println("();");
                indent(2);
                out.println("verifyDatum();");
            }
            indent(2);
            if (crsType.startsWith("Geocentric")) {
                out.print("createAndVerifyGeocentricCRS");
            } else if (crsType.startsWith("Geographic")) {
                out.print("createAndVerifyGeographicCRS");
            } else {
                // Should never happen, unless we have a bug in our test suite
                throw new AssertionError(crsType);
            }
            out.print('(');
            out.print(crsCode);
            out.print(", \"");
            out.print(crsName);
            out.print("\", ");
            out.print(csCode);
            out.println(");");
        }
    }
}
