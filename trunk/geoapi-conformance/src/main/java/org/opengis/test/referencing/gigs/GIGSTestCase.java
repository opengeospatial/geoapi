/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2013 Open Geospatial Consortium, Inc.
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

import java.util.Collection;

import org.opengis.util.Factory;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.util.GenericName;
import org.opengis.referencing.*;
import org.opengis.test.TestCase;

import static org.opengis.test.Assert.*;


/**
 * Base class of all GIGS tests.
 *
 * @see org.opengis.test.referencing.AuthorityFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
strictfp abstract class GIGSTestCase extends TestCase {
    /**
     * Relative tolerance factor from GIGS documentation.
     */
    static final double TOLERANCE = 1E-10;

    /**
     * Angular tolerance from GIGS documentation.
     */
    static final double ANGULAR_TOLERANCE = 1E-7;

    /**
     * Creates a new test which will use the given factories to execute.
     *
     * @param factories The factories to be used by the test. Those factories passed verbatim
     *        to the {@linkplain TestCase#TestCase(Factory[]) super-class constructor}.
     */
    protected GIGSTestCase(final Factory... factories) {
        super(factories);
    }

    /**
     * Invoked when the implementation does not support one of the code defined in
     * the GIGS test suite.
     *
     * @param type      The GeoAPI interface of the object to construct.
     * @param code      The EPSG code of the object to create.
     * @param e         The exception we got while trying to instantiate the object.
     * @param important Was the object particularly important to E&P industry?
     */
    static void unsupportedCode(final Class<?> type, final int code,
            final NoSuchIdentifierException e, final boolean important)
    {
        // TODO
    }

    /**
     * Returns the name of the given object, or {@code null} if none.
     */
    static String getName(final IdentifiedObject object) {
        if (object != null) {
            final ReferenceIdentifier name = object.getName();
            if (name != null) {
                return name.getCode();
            }
        }
        return null;
    }

    /**
     * Compares the given generic names with the given set of expected aliases. This method
     * verifies that the given collection contains at least the expected aliases. However
     * the collection may contain additional aliases, which will be ignored.
     *
     * @param message  The message to show in case of failure.
     * @param expected The expected aliases.
     * @param aliases  The actual aliases.
     */
    static void testAliases(final String message, final String[] expected,
            final Collection<GenericName> aliases)
    {
        assertNotNull(message, aliases);
next:   for (final String search : expected) {
            for (final GenericName alias : aliases) {
                final String tip = alias.tip().toString();
                if (search.equalsIgnoreCase(tip)) {
                    continue next;
                }
            }
            fail(message + ": alias not found: " + search);
        }
    }

    /**
     * Ensures that the given collection contains at least one identifier having the EPSG
     * codespace (ignoring case) and the given code.
     *
     * @param message     The message to show in case of failure.
     * @param expected    The expected identifier code.
     * @param identifiers The actual identifiers.
     */
    static void testIdentifier(final String message, final int expected,
            final Collection<? extends ReferenceIdentifier> identifiers)
    {
        assertNotNull(message, identifiers);
        int found = 0;
        for (final ReferenceIdentifier id : identifiers) {
            if (id.getCodeSpace().trim().equalsIgnoreCase("EPSG")) {
                found++;
                try {
                    assertEquals(message, expected, Integer.parseInt(id.getCode()));
                } catch (NumberFormatException e) {
                    fail(message + ".getCode(): expected " + expected +
                            " but got a non-numerical value: " + e);
                }
            }
        }
        assertEquals(message + ": occurrence of EPSG:" + expected, 1, found);
    }

    /**
     * Returns the concatenation of the given prefix and suffix.
     * This is used for building messages in JUnit assert statements.
     */
    static String message(final StringBuilder prefix, final String suffix) {
        final int length = prefix.length();
        final String message = prefix.append(suffix).toString();
        prefix.setLength(length);
        return message;
    }
}
