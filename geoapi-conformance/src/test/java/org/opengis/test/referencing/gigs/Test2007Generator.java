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
 * Code generator for {@link Test2007}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class Test2007Generator extends TestMethodGenerator {
    /**
     * Launcher.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) {
        new Test2007Generator().run();
    }

    /**
     * Generates the code.
     */
    private void run() {
        final ExpectedData data = new ExpectedData("GIGS_2007_libGeodTfm.csv",
            Integer.class,  // [0]: EPSG Coordinate Operation Code
            Boolean.class,  // [1]: Particularly important to E&P industry?
            String .class,  // [2]: Transformation Name(s)
            String .class,  // [3]: Coordinate Operation Method
            String .class); // [4]: Remarks

        while (data.next()) {
            final int      code      = data.getInt    (0);
            final boolean  important = data.getBoolean(1);
            final String   name      = data.getString (2);
            final String   method    = data.getString (3);
            final String   remarks   = data.getString (4);

            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “"); out.print(name); out.println("” transformation creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("EPSG transformation code", code,
                                  "EPSG transformation name", name,
                                  "Transformation method", method,
                                  "Specific usage / Remarks", remarks,
                                  "Particularly important to E&amp;P industry.", important);
            printJavadocThrows("if an error occurred while creating the transformation from the EPSG code.");
            printTestMethodSignature(simplify(name));
            printFieldAssignments("important", important,
                                  "name",      name,
                                  "method",    method);
            indent(2); out.println("createAndVerifyTransformation();");
            out.println("    }");
        }
    }

    /**
     * Simplify the transformation name to be used for inferring a method name.
     */
    private static String simplify(String name) {
        if (!(name.contains("WGS")   && name.contains("ED50")) &&
            !(name.contains("NAD27") && name.contains("NAD83")))
        {
            final int s = name.lastIndexOf('(');
            if (s >= 0) {
                name = name.substring(0, s);
            }
        }
        name = name.replace(" to ", "_to_");
        return name;
    }
}
