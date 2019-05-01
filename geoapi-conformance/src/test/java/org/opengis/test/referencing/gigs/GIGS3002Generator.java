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
import javax.measure.Unit;
import javax.measure.quantity.Length;

import static org.junit.Assert.*;


/**
 * Code generator for {@link GIGS3002}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS3002Generator extends TestMethodGenerator {
    /**
     * The mapping from GIGS ellipsoid names to test method names.
     */
    static final Map<String,String> METHOD_NAMES;
    static {
        final Map<String,String> m = new HashMap<>();
        assertNull(m.put("GIGS ellipsoid A", "testWGS84"));
        assertNull(m.put("GIGS ellipsoid B", "testAiry"));
        assertNull(m.put("GIGS ellipsoid C", "testBessel"));
        assertNull(m.put("GIGS ellipsoid E", "testInternational1924"));
        assertNull(m.put("GIGS ellipsoid F", "testGRS1980"));
        assertNull(m.put("GIGS ellipsoid H", "testClarkeIGN"));
        assertNull(m.put("GIGS ellipsoid I", "testClarkeAuthalicSphere"));
        assertNull(m.put("GIGS ellipsoid J", "testClarke1866"));
        assertNull(m.put("GIGS ellipsoid K", "testGRS1967"));
        assertNull(m.put("GIGS ellipsoid X", "testAustralianNationalSpheroid"));
        assertNull(m.put("GIGS ellipsoid Y", "testKrassowsky"));
        /*
         * EPSG names, declared because the GIGS 3004 tests use sometime the GIGS name,
         * and sometime the EPSG name.
         */
        assertNull(m.put("WGS 84",                       "testWGS84"));
        assertNull(m.put("Airy 1830",                    "testAiry"));
        assertNull(m.put("Bessel 1841",                  "testBessel"));
        assertNull(m.put("International 1924",           "testInternational1924"));
        assertNull(m.put("GRS 1980",                     "testGRS1980"));
        assertNull(m.put("Clarke 1880 (IGN)",            "testClarkeIGN"));
        assertNull(m.put("Clarke 1866 Authalic Sphere",  "testClarkeAuthalicSphere"));
        assertNull(m.put("Clarke 1866",                  "testClarke1866"));
        assertNull(m.put("GRS 1967",                     "testGRS1967"));
        assertNull(m.put("Australian National Spheroid", "testAustralianNationalSpheroid"));
        assertNull(m.put("Krassowsky 1940",              "testKrassowsky"));
        METHOD_NAMES = Collections.unmodifiableMap(m);
    }

    /**
     * Launcher.
     *
     * @param  args  ignored.
     * @throws IOException if an error occurred while reading the test data.
     */
    public static void main(String[] args) throws IOException {
        new GIGS3002Generator().run();
    }

    /**
     * Generates the code.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    private void run() throws IOException {
        final DataParser data = new DataParser("GIGS_3002_userEllipsoid.csv",
                Integer.class,      // [ 0]: GIGS ellipsoid code
                String .class,      // [ 1]: GIGS ellipsoid name
                Double .class,      // [ 2]: Semi-major axis
                String .class,      // [ 3]: Unit
                Double .class,      // [ 4]: Inverse flattening
                Double .class,      // [ 5]: Semi-minor axis
                Boolean.class,      // [ 6]: Is sphere?
                String .class,      // [ 7]: Remarks
                Double .class,      // [ 8]: Unit Conversion Factor
                Double .class,      // [ 9]: Semi-major axis (a) in metres
                Integer.class,      // [10]: Equivalent EPSG ellipsoid code
                String .class);     // [11]: Equivalent EPSG ellipsoid name

        while (data.next()) {
            final int     code              = data.getInt    ( 0);
            final String  name              = data.getString ( 1);
            final int     codeEPSG          = data.getInt    (10);
            final String  nameEPSG          = data.getString (11);
            final String  unitName          = data.getString ( 3);
            final double  toMetres          = data.getDouble ( 8);
            final double  semiMajorInMetres = data.getDouble ( 9);
            final double  semiMajorAxis     = data.getDouble ( 2);
                  double  semiMinorAxis     = data.getDouble ( 5);
                  double  inverseFlattening = data.getDouble ( 4);
            final boolean isSphere          = data.getBoolean( 6);
            final String  remarks           = data.getString ( 7);
            final boolean isIvfDefinitive   = !Double.isNaN(inverseFlattening);

            if (Double.isNaN(semiMinorAxis)) {
                if (Double.isNaN(inverseFlattening)) {
                    semiMinorAxis     = semiMajorAxis;
                    inverseFlattening = Double.POSITIVE_INFINITY;
                } else {
                    semiMinorAxis = semiMajorAxis - semiMajorAxis/inverseFlattening;
                }
            } else if (Double.isNaN(inverseFlattening)) {
                inverseFlattening = semiMajorAxis / (semiMajorAxis - semiMinorAxis);
            }
            /*
             * Write javadoc.
             */
            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “");
            out.print(name); out.print("” ");
            out.print(isSphere ? "sphere" : isIvfDefinitive ? "flattened sphere" : "ellipsoid");
            out.println(" creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("GIGS ellipsoid code", code,
                                  "GIGS ellipsoid name", name,
                                  "EPSG equivalence", codeAndName(codeEPSG, nameEPSG),
                                  "Semi-major axis (<var>a</var>)", quantityAndAlternative(semiMajorAxis, unitName, semiMajorInMetres, "metres"),
                                  "Semi-minor axis (<var>b</var>)", quantityAndAlternative(semiMinorAxis, unitName, semiMinorAxis*toMetres, "metres"),
                                  "Inverse flattening (1/<var>f</var>)", inverseFlattening,
                                  "Specific usage / Remarks", remarks);
            printJavadocThrows("if an error occurred while creating the ellipsoid from the properties.");
            printJavadocSee("GIGS2002", METHOD_NAMES.get(name));
            /*
             * Write test method.
             */
            final Unit<Length> axisUnit = parseLinearUnit(unitName);
            printTestMethodSignature(METHOD_NAMES, name);
            printCallToSetCodeAndName(code, name);
            printFieldAssignments("semiMajorAxis",     semiMajorAxis,
                                  "semiMinorAxis",     semiMinorAxis,
                                  "axisUnit",          axisUnit,
                                  "inverseFlattening", inverseFlattening,
                                  "isIvfDefinitive",   isIvfDefinitive,
                                  "isSphere",          isSphere);

            indent(2); out.println("verifyEllipsoid();");
            indent(1); out.println('}');
        }
    }
}
