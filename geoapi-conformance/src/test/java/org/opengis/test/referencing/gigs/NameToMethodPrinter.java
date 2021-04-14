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

import java.io.PrintStream;
import org.opengis.test.TestEvent;
import org.opengis.test.TestListener;
import org.opengis.test.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * Prints the mapping between the GIGS object name and the test method name.
 * This is used for updating the {@code METHOD_NAMES} map in {@code GIGS*Generator} classes.
 *
 * <p>To use this class, just extend the GIGS test class for which the mapping is wanted.
 * The mapping will be sent to the standard output.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(JUnit4.class)
public final strictfp class NameToMethodPrinter extends GIGS3002 implements TestListener {
    static {
        TestSuite.addTestListener(new NameToMethodPrinter());
    }

    /**
     * Creates a new mapper.
     */
    public NameToMethodPrinter() {
        super(null);
    }

    /**
     * Writes the mapping of a test to the standard output stream.
     */
    @Override
    public void finished(final TestEvent event) {
        final UserObjectFactoryTestCase<?> t = (UserObjectFactoryTestCase<?>) event.getSource();
        final String method = event.getMethodName();
        printPutInstruction(t.getName(), method);
    }

    /**
     * Prints a "put" instruction for the given key-value pair.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    static void printPutInstruction(final String name, final String method) {
        final PrintStream out = System.out;
        out.print("        assertNull(m.put(\"");
        out.print(name);
        out.print("\", \"");
        out.print(method);
        out.println("\"));");
    }

    /** Ignored. */ @Override public void starting(TestEvent event) {}
    /** Ignored. */ @Override public void succeeded(TestEvent event) {}
    /** Ignored. */ @Override public void failed(TestEvent event, Throwable exception) {}
}
