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

import javax.measure.unit.Unit;
import javax.measure.unit.NonSI;
import javax.measure.quantity.Angle;

import static org.junit.Assert.*;


/**
 * Code generator for {@link GIGS3003}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS3003Generator extends TestMethodGenerator {
    /**
     * Launcher.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) {
        new GIGS3003Generator().run();
    }

    /**
     * Generates the code.
     */
    private void run() {
        final ExpectedData data = new ExpectedData("GIGS_3003_userPrimeMeridian.csv",
                Integer.class,      // [0]: GIGS prime meridian code
                String.class,       // [1]: GIGS prime meridian name
                String.class,       // [2]: Longitude from greenwich
                String.class,       // [3]: EPSG unit name
                Double.class,       // [4]: Longitude from Greenwich in decimal degrees
                String.class,       // [5]: Remarks
                Integer.class);     // [6]: Equivalent EPSG Prime Meridian code

        while (data.next()) {
            final int     code               = data.getInt    ( 0);
            final String  name               = data.getString ( 1);
            final String  unitName           = data.getString ( 3);
            final String  greenwichLongitude = data.getString ( 2);
            final double  longitudeInDegrees = data.getDouble ( 4);
            final String  remarks            = data.getString ( 5);
            final int     codeEPSG           = data.getInt    ( 6);
            final double longitude;
            final Unit<Angle> unit;
            if (unitName.equalsIgnoreCase("sexagesimal degree")) {
                /*
                 * Sexagesimal degrees are written in a String not directly convertible to 'double'
                 * values. Even if we performed the conversion, we do not expect implementations to
                 * support sexagesimal units since the conversion to degrees is non-linear.
                 * Consequently, fallback on the decimal degrees instead.
                 */
                unit = NonSI.DEGREE_ANGLE;
                longitude = longitudeInDegrees;
            } else {
                unit = parseAngularUnit(unitName);
                assertNotNull(unitName, unit); // Failure here would be a geoapi-conformance bug.
                longitude = Double.parseDouble(data.getString(2));
            }
            /*
             * Write javadoc.
             */
            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “");
            out.print(name); out.println("” prime meridian creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("GIGS prime meridian code", code,
                                  "GIGS prime meridian name", name,
                                  "EPSG equivalence", codeEPSG,
                                  "Greenwich longitude", quantityAndAlternative(greenwichLongitude, unitName, longitudeInDegrees, "°"),
                                  "Specific usage / Remarks", remarks);
            printJavadocThrows("if an error occurred while creating the prime meridian from the properties.");
            /*
             * Write test method.
             */
            printTestMethodSignature(name);
            printCallToSetCodeAndName(code, name);
            printFieldAssignments("greenwichLongitude", longitude,
                                  "angularUnit",        unit);

            indent(2); out.println("createAndTestPrimeMeridian();");
            indent(1); out.println('}');
        }
    }
}
