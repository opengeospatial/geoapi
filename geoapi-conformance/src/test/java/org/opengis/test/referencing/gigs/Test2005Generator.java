/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2015 Open Geospatial Consortium, Inc.
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


/**
 * Code generator for {@link Test2005}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class Test2005Generator extends TestMethodGenerator {
    /**
     * Launcher.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) {
        new Test2005Generator().run();
    }

    /**
     * Generates the code.
     */
    private void run() {
        final ExpectedData data = new ExpectedData("GIGS_2005_libProjection.csv",
            String .class,  // [0]: EPSG Coordinate Operation Code(s)
            Boolean.class,  // [1]: Particularly important to E&P industry?
            String .class,  // [2]: Map Projection Name(s)
            String .class,  // [3]: Coordinate Operation Method
            String .class); // [4]: Remarks

        while (data.next()) {
            final int[]    codes      = data.getInts   (0);
            final boolean  important  = data.getBoolean(1);
            final String   name       = data.getString (2);
            final String   method     = data.getString (3);
            final String   remarks    = data.getString (4);

            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “"); out.print(name); out.println("” coordinate operation creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("EPSG coordinate operation codes", codes,
                                  "EPSG coordinate operation name", name,
                                  "Coordinate operation method", method,
                                  "Specific usage / Remarks", remarks,
                                  "Particularly important to E&amp;P industry.", important);
            printJavadocThrows("if an error occurred while creating the coordinate operation from the EPSG code.");
            printTestMethodSignature(name);
            printFieldAssignments("important", important,
                                  "name",      name,
                                  "method",    method);
            for (int i=0; i<codes.length; i++) {
                /*
                 * Verify if we have a sequence of consecutive codes. If we find a sequence of at least 4
                 * consecutive codes, we will write a for loop. Otherwise we will enumerate the codes.
                 */
                if (i+3 < codes.length) {
                    final int delta = codes[i+1] - codes[i];
                    if (delta >= 1) {
                        int upper = i + 2;  // Exclusive
                        while (upper < codes.length) {
                            if (codes[upper] - codes[upper - 1] != delta) {
                                break;
                            }
                            upper++;
                        }
                        if (upper - i >= 4) {   // Threshold for formatting a 'for' loop.
                            indent(2);
                            out.print("for (int code = ");
                            out.print(codes[i]);
                            out.print("; code <= ");
                            out.print(codes[upper - 1]);
                            out.print("; ");
                            if (delta == 1) {
                                out.print("code++");
                            } else {
                                out.print("code += ");
                                out.print(delta);
                            }
                            out.print(") {    // Loop over ");
                            out.print(upper - i);
                            out.println(" codes");
                            indent(3);
                            out.println("createAndVerifyCoordinateOperation(code);");
                            indent(2);
                            out.println("}");
                        }
                        i = upper - 1;  // Skip the sequence.
                        continue;
                    }
                }
                indent(2);
                out.print("createAndVerifyCoordinateOperation(");
                out.print(codes[i]);
                out.println(");");
            }
            out.println("    }");
        }
    }
}
