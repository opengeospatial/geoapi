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

import java.util.Collection;
import org.opengis.util.Factory;
import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.*;
import org.opengis.test.referencing.ReferencingTestCase;
import org.junit.AssumptionViolatedException;

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
strictfp abstract class GIGSTestCase extends ReferencingTestCase {
    /**
     * Relative tolerance factor from GIGS documentation.
     * This tolerance threshold is typically multiplied
     * by the magnitude of the value being compared.
     */
    static final double TOLERANCE = 1E-10;

    /**
     * Absolute angular tolerance from GIGS documentation.
     * This tolerance threshold is <strong>not</strong>
     * multiplied by the value being compared.
     */
    static final double ANGULAR_TOLERANCE = 1E-7;

    /**
     * Creates a new test which will use the given factories to execute.
     *
     * @param factories The factories to be used by the test. Those factories passed verbatim to the
     *        {@linkplain ReferencingTestCase#ReferencingTestCase(Factory[]) super-class constructor}.
     */
    protected GIGSTestCase(final Factory... factories) {
        super(factories);
    }

    /**
     * Invoked when the implementation does not support one of the code defined in the GIGS test suite.
     * This method can have two behaviors:
     *
     * <ul>
     *   <li>If the test should be ignored, then this method should invoke {@code assumeTrue(false)} or any
     *       other {@link org.junit.Assume} method which will case the test to terminate with the "ignored"
     *       status.</li>
     *   <li>Otherwise this method can return normally, in which case the caller test method will consider
     *       that we have a test failure.</li>
     * </ul>
     *
     * @param type The GeoAPI interface of the object to construct.
     * @param code The EPSG code or name of the object to create.
     */
    final void unsupportedCode(final Class<?> type, final Object code) {
        final StringBuilder buffer = new StringBuilder(50).append(type.getSimpleName()).append('[');
        final boolean quote = !(code instanceof Number);
        if (quote) buffer.append('"');
        buffer.append(code);
        if (quote) buffer.append('"');
        buffer.append("] not supported.");
        throw new AssumptionViolatedException(buffer.toString());
    }

    /**
     * Returns the name of the given object, or {@code null} if none.
     */
    static String getName(final IdentifiedObject object) {
        if (object != null) {
            final Identifier name = object.getName();
            if (name != null) {
                return name.getCode();
            }
        }
        return null;
    }

    /**
     * Asserts that the {@linkplain IdentifiedObject#getName() name} or at least one
     * {@linkplain IdentifiedObject#getAlias() alias} of the given object is equals
     * to the given string. This method ignore the case.
     *
     * <p>This method does not raise error if the object name or the alias collection
     * is null, because it is validators job to determine whether such malformed
     * objects should be accepted or not.</p>
     *
     * @param message  The message to show in case of failure.
     * @param expected The name which is expected to exist as a name or an alias.
     * @param object   The object for which to check the name or the aliases.
     */
    static void assertContainsNameOrAlias(final String message,
            final String expected, final IdentifiedObject object)
    {
        assertNotNull(message, object);
        final String name = getName(object);
        if (!expected.equalsIgnoreCase(name)) {
            final Collection<GenericName> aliases = object.getAlias();
            if (aliases != null) {
                for (GenericName alias : aliases) {
                    if (alias.tip().toString().equalsIgnoreCase(expected)) {
                        return;
                    }
                }
            }
            fail(message + ": name or alias not found: " + expected);
        }
    }

    /**
     * Compares the given generic names with the given set of expected aliases.
     * This method verifies that the given collection contains at least the expected aliases.
     * However the collection may contain additional aliases, which will be ignored.
     *
     * @param message  The prefix of the message to show in case of failure.
     * @param expected The expected aliases.
     * @param aliases  The actual aliases.
     */
    static void assertContainsAll(final String message, final String[] expected,
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
     * Ensures that the given collection contains at least one identifier having the given
     * codespace (ignoring case) and the given code value.
     *
     * @param message     The message to show in case of failure.
     * @param codespace   The code space of identifiers to search.
     * @param expected    The expected identifier code.
     * @param identifiers The actual identifiers.
     */
    static void assertContainsCode(final String message, final String codespace, final int expected,
            final Collection<? extends Identifier> identifiers)
    {
        assertNotNull(message, identifiers);
        int found = 0;
        for (final Identifier id : identifiers) {
            if (codespace.equalsIgnoreCase(id.getCodeSpace().trim())) {
                found++;
                try {
                    assertEquals(message, expected, Integer.parseInt(id.getCode()));
                } catch (NumberFormatException e) {
                    fail(message + ".getCode(): expected " + expected +
                            " but got a non-numerical value: " + e);
                }
            }
        }
        assertEquals(message + ": occurrence of " + codespace + ':' + expected, 1, found);
    }
}
