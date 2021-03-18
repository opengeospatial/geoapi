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
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import javax.measure.Unit;

import static org.junit.Assert.*;


/**
 * Code generator for {@link GIGS3005}. This generator needs to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class GIGS3005Generator extends TestMethodGenerator {
    /**
     * The mapping from GIGS ellipsoid names to test method names.
     */
    static final Map<String,String> METHOD_NAMES;
    static {
        final Map<String,String> m = new HashMap<>();
        assertNull(m.put("GIGS projection 1",       "testUTM_zone31N"));
        assertNull(m.put("GIGS projection 2",       "testBritishNationalGrid"));
        assertNull(m.put("GIGS projection 2 alt A", "testBritishNationalGrid_altA"));
        assertNull(m.put("GIGS projection 2 alt B", "testBritishNationalGrid_altB"));
        assertNull(m.put("GIGS projection 4",       "testRDNew"));
        assertNull(m.put("GIGS projection 6",       "testBelgianLambert72"));
        assertNull(m.put("GIGS projection 7",       "testAustralianMapGridZone54"));
        assertNull(m.put("GIGS projection 8",       "testAustralianMapGridZone55"));
        assertNull(m.put("GIGS projection 9",       "testAustralianAlbers"));
        assertNull(m.put("GIGS projection 10",      "testSouthAfricanSurveyGridZone21"));
        assertNull(m.put("GIGS projection 11",      "testArgentinaZone5"));
        assertNull(m.put("GIGS projection 12",      "testBrazilPolyconic"));
        assertNull(m.put("GIGS projection 14",      "testEastMalaysia"));
        assertNull(m.put("GIGS projection 15",      "testJohorGrid"));
        assertNull(m.put("GIGS projection 16",      "testEuropeEqualArea"));
        assertNull(m.put("GIGS projection 17",      "testUtahNorth"));
        assertNull(m.put("GIGS projection 18",      "testUtahNorth_ftUS"));
        assertNull(m.put("GIGS projection 19",      "testLambertZoneII"));
        assertNull(m.put("GIGS projection 24",      "testCaspianSeaMercator"));
        assertNull(m.put("GIGS projection 25",      "testFranceEuroLambert"));
        assertNull(m.put("GIGS projection 26",      "testEOV"));
        assertNull(m.put("GIGS projection 27",      "testNEIEZ"));
        assertNull(m.put("GIGS projection 28",      "testUTM_zone8N"));
        METHOD_NAMES = Collections.unmodifiableMap(m);
    }

    /**
     * Launcher.
     *
     * @param  args  ignored.
     * @throws IOException if an error occurred while reading the test data.
     */
    public static void main(String[] args) throws IOException {
        new GIGS3005Generator().run();
    }

    /**
     * Maximum number of parameters in the CSV file.
     */
    private static final int MAX_NUM_PARAMETERS = 7;

    /**
     * Returns the first column for the given parameter index. The column returned by this method
     * contains the parameter name, and the 2 or 3 following columns contain the parameter values
     * and unit of measurement.
     */
    private static int getFirstParameterColumn(final int paramNum) {
        return 3 + Math.min(paramNum,   4) * 4   // Parameter 1 to 4 use 4 columns.
                 + Math.max(paramNum-4, 0) * 3;  // Parameter 5 to 7 use 3 columns.
    }

    /**
     * Generates the code.
     *
     * @throws IOException if an error occurred while reading the test data.
     */
    private void run() throws IOException {
        final DataParser data = new DataParser("GIGS_3005_userProjection.csv",
                Integer.class,     // [ 0]: GIGS projection code
                String.class,      // [ 1]: GIGS projection name
                String.class,      // [ 2]: EPSG Conversion method name
                String.class,      // [ 3]: Parameter 1 name
                String.class,      // [ 4]: Parameter 1 value
                String.class,      // [ 5]: Parameter 1 unit
                Double.class,      // [ 6]: Parameter 1 value in decimal degrees
                String.class,      // [ 7]: Parameter 2 name
                String.class,      // [ 8]: Parameter 2 value
                String.class,      // [ 9]: Parameter 2 unit
                Double.class,      // [10]: Parameter 2 value in decimal degrees
                String.class,      // [11]: Parameter 3 name
                String.class,      // [12]: Parameter 3 value
                String.class,      // [13]: Parameter 3 unit
                Double.class,      // [14]: Parameter 3 value in decimal degrees
                String.class,      // [15]: Parameter 4 name
                String.class,      // [16]: Parameter 4 value
                String.class,      // [17]: Parameter 4 unit
                Double.class,      // [18]: Parameter 4 value in decimal degrees
                String.class,      // [19]: Parameter 5 name
                String.class,      // [20]: Parameter 5 value
                String.class,      // [21]: Parameter 5 unit
                String.class,      // [22]: Parameter 6 name
                String.class,      // [23]: Parameter 6 value
                String.class,      // [24]: Parameter 6 unit
                String.class,      // [25]: Parameter 7 name
                String.class,      // [26]: Parameter 7 value
                String.class,      // [27]: Parameter 7 unit
                String.class,      // [28]: Remarks
                Integer.class,     // [29]: Equivalent EPSG code
                String.class);     // [30]: Equivalent EPSG name

        while (data.next()) {
            final int     code       = data.getInt        ( 0);
            final String  name       = data.getString     ( 1);
            final String  methodName = data.getString     ( 2);
            final String  remarks    = data.getString     (27);
            final Integer codeEPSG   = data.getIntOptional(29);
            final String  nameEPSG   = data.getString     (30);
            /*
             * Write javadoc.
             */
            out.println();
            indent(1); out.println("/**");
            indent(1); out.print(" * Tests “");
            out.print(name); out.println("” conversion creation from the factory.");
            indent(1); out.println(" *");
            printJavadocKeyValues("GIGS conversion code", code,
                                  "GIGS conversion name", name,
                                  "EPSG operation method", methodName,
                                  "EPSG equivalence", (codeEPSG != null) ? codeAndName(codeEPSG, nameEPSG) : nameEPSG,
                                  "Specific usage / Remarks", remarks);

            printParameterTableHeader("Conversion parameters");
            for (int paramNum = 0; paramNum < MAX_NUM_PARAMETERS; paramNum++) {
                final int columnOffset = getFirstParameterColumn(paramNum);
                final String paramName = data.getString(columnOffset);
                if (paramName != null) {
                    printParameterTableRow(paramName,
                            data.getString(columnOffset + 1),
                            data.getString(columnOffset + 2));
                }
            }
            printParameterTableFooter();
            printJavadocThrows("if an error occurred while creating the conversion from the properties.");
            /*
             * Write test method.
             */
            printTestMethodSignature(METHOD_NAMES, name);
            printCallToSetCodeAndName(code, name);
            printFieldAssignments("methodName", methodName);
            indent(2);
            out.println("createDefaultParameters();");
            for (int paramNum = 0; paramNum < MAX_NUM_PARAMETERS; paramNum++) {
                final int columnOffset = getFirstParameterColumn(paramNum);
                final String paramName = data.getString(columnOffset);
                if (paramName != null) {
                    final double value;
                    final Unit<?> unit;
                    final String unitName = data.getString(columnOffset + 2);
                    if (unitName.equalsIgnoreCase(GIGS3003Generator.SEXAGESIMAL_DEGREE)) {
                        value = data.getDouble(columnOffset + 3);
                        unit = units.degree();
                    } else {
                        unit = parseUnit(unitName);
                        assertNotNull(unitName, unit);          // Failure here would be a geoapi-conformance bug.
                        value = Double.valueOf(data.getString(columnOffset + 1));
                    }
                    indent(2);
                    out.print("definition.parameter(\"");
                    out.print(paramName);
                    out.print("\").setValue(");
                    out.print(value);
                    if (unit != null) {
                        out.print(", ");
                        printProgrammaticName(unit);
                    }
                    out.println(");");
                }
            }
            indent(2); out.println("verifyConversion();");
            indent(1); out.println('}');
        }
    }
}
