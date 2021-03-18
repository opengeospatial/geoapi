/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
 * Code generator for {@link GIGS2003}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS2003Generator extends TestMethodGenerator {
    /**
     * Launcher.
     *
     * @param  args  ignored.
     * @throws IOException if an error occurred while reading the test data.
     */
    public static void main(String[] args) throws IOException {
        new GIGS2003Generator().run();
    }

    /**
     * Generates the code.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    private void run() throws IOException {
        final DataParser data = new DataParser("GIGS_2003_libPrimeMeridian.csv",
                Integer.class,      // [0]: EPSG Prime Meridian Code
                Boolean.class,      // [1]: Particularly important to E&P industry?
                String .class,      // [2]: EPSG Prime Meridian Name
                String .class,      // [3]: EPSG Alias
                String .class,      // [4]: Longitude from Greenwich (sexagesimal)
                String .class,      // [5]: Unit Name
                Double .class,      // [6]: Longitude from Greenwich (decimal degrees)
                String .class);     // [7]: Remarks

         while (data.next()) {
            final int      code      = data.getInt    (0);
            final boolean  important = data.getBoolean(1);
            final String   name      = data.getString (2);
            final String[] aliases   = data.getStrings(3);
            final String   lonString = data.getString (4);
            final double   longitude = data.getDouble (6);
            final String   remarks   = data.getString (7);

            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “"); out.print(name); out.println("” prime meridian creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("EPSG prime meridian code", code,
                                  "EPSG prime meridian name", name,
                                  "Alias(es) given by EPSG", aliases,
                                  "Greenwich longitude", reformatSexagesimalAngle(lonString),
                                  "Specific usage / Remarks", remarks,
                                  "Particularly important to E&amp;P industry.", important);
            printJavadocThrows("if an error occurred while creating the prime meridian from the EPSG code.");
            printTestMethodSignature(name);
            printFieldAssignments("important",          important,
                                  "code",               code,
                                  "name",               name,
                                  "aliases",            aliases,
                                  "greenwichLongitude", longitude);
            indent(2); out.println("verifyPrimeMeridian();");
            indent(1); out.println('}');
        }
    }

    /**
     * Replaces the prime and double-prime quotes of the given angle by their Unicode symbol.
     */
    private static String reformatSexagesimalAngle(final String angle) {
        final StringBuilder buffer = new StringBuilder(angle);
        for (int i=buffer.length(); --i>=0;) {
            switch (buffer.charAt(i)) {
                case '\'': buffer.setCharAt(i, '′'); break;
                case '"' : buffer.setCharAt(i, '″'); break;
                case '\\': buffer.deleteCharAt(i); break;
            }
        }
        return buffer.toString();
    }
}
