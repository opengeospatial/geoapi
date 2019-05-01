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
 * Code generator for {@link GIGS2002}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS2002Generator extends TestMethodGenerator {
    /**
     * Launcher.
     *
     * @param  args  ignored.
     * @throws IOException if an error occurred while reading the test data.
     */
    public static void main(String[] args) throws IOException {
        new GIGS2002Generator().run();
    }

    /**
     * Generates the code.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    private void run() throws IOException {
        final DataParser data = new DataParser("GIGS_2002_libEllipsoid.csv",
                Integer.class,      // [ 0]: EPSG Ellipsoid Code
                Boolean.class,      // [ 1]: Particularly important to E&P industry?
                String .class,      // [ 2]: EPSG Ellipsoid Name
                String .class,      // [ 3]: Alias(es) given by EPSG
                Double .class,      // [ 4]: Semi-major axis (a)
                String .class,      // [ 5]: Unit Name
                Double .class,      // [ 6]: Unit Conversion Factor
                Double .class,      // [ 7]: Semi-major axis (a) in metres
                Double .class,      // [ 8]: Second defining parameter: Inverse flattening (1/f)
                Double .class,      // [ 9]: Second defining parameter: Semi-minor axis (b)
                Boolean.class);     // [10]: Sphere?

        while (data.next()) {
            final int      code              = data.getInt    ( 0);
            final boolean  important         = data.getBoolean( 1);
            final String   name              = data.getString ( 2);
            final String[] aliases           = data.getStrings( 3);
            final double   semiMajorAxis     = data.getDouble ( 4);
            final double   semiMinorAxis     = data.getDouble ( 9);
            final double   inverseFlattening = data.getDouble ( 8);
            final double   semiMajorInMetres = data.getDouble ( 7);
            final double   toMetres          = data.getDouble ( 6);
            final boolean  isSphere          = data.getBoolean(10);

            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “");
            out.print(name); out.print("” ");
            out.print(isSphere ? "spheroid" : "ellipsoid");
            out.println(" creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("EPSG ellipsoid code", code,
                                  "EPSG ellipsoid name", name,
                                  "Alias(es) given by EPSG", aliases,
                                  "Semi-major axis (<var>a</var>)", semiMajorAxis,
                                  "Semi-minor axis (<var>b</var>)", semiMinorAxis,
                                  "Inverse flattening (1/<var>f</var>)", inverseFlattening,
                                  "Particularly important to E&amp;P industry.", important);
            printJavadocThrows("if an error occurred while creating the ellipsoid from the EPSG code.");
            printTestMethodSignature(name);
            printFieldAssignments("important",         important,
                                  "code",              code,
                                  "name",              name,
                                  "aliases",           aliases,
                                  "toMetres",          Double.isNaN(toMetres) ? 1 : toMetres,
                                  "semiMajorInMetres", Double.isNaN(semiMajorInMetres) ? semiMajorAxis : semiMajorInMetres,
                                  "semiMajorAxis",     semiMajorAxis,
                                  "semiMinorAxis",     semiMinorAxis,
                                  "inverseFlattening", inverseFlattening,
                                  "isSphere",          isSphere);
            indent(2); out.println("verifyEllipsoid();");
            indent(1); out.println('}');
        }
    }
}
