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

import java.util.Map;
import java.util.HashMap;
import java.io.PrintStream;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import static org.junit.Assert.*;


/**
 * Base class of test code generators. Those generators need to be executed only if the GIGS data changed.
 * The code is sent to the standard output; maintainer need to copy-and-paste the relevant methods to the
 * test class, but be aware that the original code may contain manual changes that need to be preserved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract class TestMethodGenerator {
    /**
     * The programmatic names of unit.
     */
    private static final Map<Unit<?>,String> UNIT_NAMES;
    static {
        final Map<Unit<?>,String> m = new HashMap<Unit<?>,String>();
        assertNull(m.put(Unit.ONE,  "Unit.ONE"));
        assertNull(m.put(SI.METRE,  "SI.METRE"));
        assertNull(m.put(SI.RADIAN, "SI.RADIAN"));
        UNIT_NAMES = m;
    }

    /**
     * Where to write the generated code.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    final PrintStream out = System.out;

    /**
     * For subclasses constructor only.
     */
    TestMethodGenerator() {
    }

    /**
     * Prints the margin at the beginning of a new line.
     */
    final void indent(int n) {
        do out.print("    ");
        while (--n != 0);
    }

    /**
     * Returns {@code true} if the given value should be skipped from javadoc.
     */
    private static boolean omitFromJavadoc(final Object value) {
        if (value instanceof Double)   return ((Double) value).isNaN();
        if (value instanceof Object[]) return ((Object[]) value).length == 0;
        return false;
    }

    /**
     * Prints a sequence of key-values as a list in javadoc.
     * Boolean value are treated especially: the key is printed only if the value is {@code true}.
     *
     * @param pairs Key-value pairs.
     */
    final void printJavadocKeyValues(final Object... pairs) {
        assertTrue("Array length shall be even", (pairs.length & 1) == 0);
        indent(1); out.println(" * <ul>");
        for (int i=0; i<pairs.length; i += 2) {
            final Object value = pairs[i+1];
            if (value != null && !omitFromJavadoc(value)) {
                if (value instanceof Boolean) {
                    if ((Boolean) value) {
                        indent(1);
                        out.print(" *   <li>");
                        out.print(pairs[i]);
                        out.println("</li>");
                    }
                } else {
                    indent(1);
                    out.print(" *   <li>");
                    out.print(pairs[i]);
                    out.print(": <b>");
                    if (value instanceof Object[]) {
                        String separator = "";
                        for (final Object e : (Object[]) value) {
                            out.print(separator);
                            out.print(e);
                            separator = "</b>, <b>";
                        }
                    } else if (value instanceof int[]) {
                        String separator = "";
                        final int length = ((int[]) value).length;
                        final int stopAt = Math.min(length, 10);
                        for (int j=0; j<stopAt; j++) {
                            out.print(separator);
                            out.print(((int[]) value)[j]);
                            separator = "</b>, <b>";
                        }
                        if (stopAt != length) {
                            out.print("</b>, <i>…");
                            out.print(length - stopAt);
                            out.println(" more</i></li>");
                            continue; // Because we already wrote the closing </li>.
                        }
                    } else if (value instanceof Double) {
                        final double asDouble = (Double) value;
                        final int asInteger = (int) asDouble;
                        if (asDouble == asInteger) {
                            out.print(asInteger);
                        } else {
                            out.print(asDouble);
                        }
                    } else {
                        out.print(value);
                    }
                    out.println("</b></li>");
                }
            }
        }
        indent(1); out.println(" * </ul>");
    }

    /**
     * Prints the javadoc {@code throws FactoryException} followed by the given explanatory text.
     * Then close the javadoc comment block.
     */
    final void printJavadocThrows(final String condition) {
        indent(1); out.println(" *");
        indent(1); out.print  (" * @throws FactoryException "); out.println(condition);
        indent(1); out.println(" */");
    }

    /**
     * Prints the test method signature, including the {@code throws FactoryException} declaration.
     *
     * @param name The name to use for generating a method name. This name may contain illegal characters like spaces;
     *        they will be trimmed in an implementation-dependant way.
     */
    final void printTestMethodSignature(final String name) {
        indent(1); out.println("@Test");
        indent(1); out.print  ("public void test");
        boolean toUpperCase = true;
        for (int i=0; i<name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isJavaIdentifierPart(c)) {
                if (toUpperCase) {
                    toUpperCase = false;
                    c = Character.toUpperCase(c);
                }
                out.print(c);
            } else {
                if (c == '(' || c == ')') {
                    if (i+1 < name.length()) {
                        out.print('_');
                        toUpperCase = false;
                    }
                } else {
                    toUpperCase = true;
                }
                /*
                 * For name like “Clarke's foot”, skip also the "s" after the single quote.
                 * The result will be “ClarkeFoot”.
                 */
                if (c == '\'' && i+1 < name.length() && name.charAt(i+1) == 's') {
                    i++;
                }
            }
        }
        out.println("() throws FactoryException {");
    }

    /**
     * Prints a sequence of key-values as assignments in the method body.
     * Boolean value are treated especially: the assignment is printed only if the value is {@code true}.
     *
     * @param pairs Key-value pairs.
     */
    final void printFieldAssignments(final Object... pairs) {
        assertTrue("Array length shall be even", (pairs.length & 1) == 0);
        int length = 0;
        for (int i=0; i<pairs.length; i += 2) {
            length = Math.max(length, ((String) pairs[i]).length());
        }
        for (int i=0; i<pairs.length; i += 2) {
            final String name  = (String) pairs[i];
            final Object value = pairs[i+1];
            if (!(value instanceof Boolean) || (Boolean) value) {
                indent(2);
                out.print(name);
                for (int j = length - name.length(); --j >= 0;) {
                    out.print(' ');
                }
                out.print(" = ");
                if (value instanceof String[]) {
                    if (((String[]) value).length == 0) {
                        out.print("NONE");
                    } else {
                        String separator = "new String[] {\"";
                        for (final String e : (String[]) value) {
                            out.print(separator);
                            out.print(e);
                            separator = "\", \"";
                        }
                        out.print("\"}");
                    }
                } else if (value instanceof Double && ((Double) value).isNaN()) {
                    out.print("Double.NaN");
                } else {
                    final boolean quote = (value instanceof CharSequence);
                    if (quote) {
                        out.print('"');
                    }
                    out.print(value);
                    if (quote) {
                        out.print('"');
                    }
                }
                out.println(';');
            }
        }
    }

    /**
     * Prints the programmatic name of the given unit.
     *
     * @param unit The unit for which to print the programmatic name.
     */
    final void printProgrammaticName(final Unit<?> unit) {
        final String name = UNIT_NAMES.get(unit);
        assertNotNull(unit.toString(), name);
        out.print(name);
    }
}
