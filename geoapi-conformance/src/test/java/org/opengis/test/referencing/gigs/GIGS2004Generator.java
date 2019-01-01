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


/**
 * Code generator for {@link GIGS2004}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS2004Generator extends TestMethodGenerator {
    /**
     * Launcher.
     *
     * @param  args  ignored.
     * @throws IOException if an error occurred while reading the test data.
     */
    public static void main(String[] args) throws IOException {
        new GIGS2004Generator().run();
    }

    /**
     * Generates the code.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    private void run() throws IOException {
        final DataParser data = new DataParser("GIGS_2004_libGeodeticDatumCRS.csv",
                Integer.class,      // [0]: EPSG Datum Code
                String .class,      // [1]: Datum Name
                Integer.class,      // [2]: EPSG geocen CRS Code
                Integer.class,      // [3]: EPSG geog3D CRS Code
                Integer.class,      // [4]: EPSG geog2D CRS Code
                String .class,      // [5]: CRS Name
                Boolean.class,      // [6]: Particularly important to E&P industry?
                String .class,      // [7]: Ellipsoid Name
                String .class,      // [8]: Prime Meridian Name
                String .class);     // [9]: Remarks

        while (data.next()) {
            final int     code              = data.getInt    (0);
            final String  name              = data.getString (1);
            final String  crsName           = data.getString (5);
            final boolean important         = data.getBoolean(6);
            final String  ellipsoidName     = data.getString (7);
            final String  primeMeridianName = data.getString (8);
            final String  remarks           = data.getString (9);

            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “"); out.print(name); out.println("” geodetic datum creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("EPSG datum code", code,
                                  "EPSG datum name", name,
                                  "Ellipsoid name", ellipsoidName,
                                  "Prime meridian name", primeMeridianName,
                                  "CRS using the datum", crsName,
                                  "Specific usage / Remarks", remarks,
                                  "Particularly important to E&amp;P industry.", important);
            printJavadocThrows("if an error occurred while creating the datum or a CRS from the EPSG code.");
            printTestMethodSignature(crsName);  // CRS name is simpler than datum name.
            printFieldAssignments("important",         important,
                                  "code",              code,
                                  "name",              name,
                                  "crsName",           crsName,
                                  "ellipsoidName",     ellipsoidName,
                                  "primeMeridianName", primeMeridianName);
            indent(2); out.println("verifyDatum();");
            for (int column=2; column<=4; column++) {
                final Integer crsCode = data.getIntOptional(column);
                if (crsCode != null) {
                    indent(2);
                    out.print((column == 2) ? "createAndVerifyGeocentricCRS" : "createAndVerifyGeographicCRS");
                    out.print('(');
                    out.print(crsCode);
                    if (column != 2) {
                        out.print(", ");
                        out.print(column == 3 ? "GEOGRAPHIC_3D" : "GEOGRAPHIC_2D");
                    }
                    out.println(");");
                }
            }
            indent(1); out.println('}');
        }
    }
}
