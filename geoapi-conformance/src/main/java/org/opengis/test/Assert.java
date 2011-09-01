/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Collection;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystem;


/**
 * Assertion methods to be used by GeoAPI tests. This class inherits all assertion methods
 * from the JUnit {@link org.junit.Assert} class. Consequently, developers can replace the
 * following statement:
 *
 * <blockquote><pre>import static org.junit.Assert.*</pre></blockquote>
 *
 * by
 *
 * <blockquote><pre>import static org.opengis.test.Assert.*</pre></blockquote>
 *
 * if they wish to use the assertion methods defined here in addition of JUnit methods.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public strictfp class Assert extends org.junit.Assert {
    /**
     * For subclass constructors only.
     */
    protected Assert() {
    }

    /**
     * Returns the given message, or an empty string if the message is null.
     */
    private static String nonNull(final String message) {
        return (message != null) ? message.trim().concat(" ") : "";
    }

    /**
     * Asserts that the given value is an instance of the given class. No tests are performed if
     * the type is {@code null}. If the type is not-null but the value is null, this is considered
     * as a failure.
     *
     * @param message      Header of the exception message in case of failure, or {@code null} if none.
     * @param expectedType The expected parent class of the value, or {@code null}.
     * @param value        The value to test, or {@code null} (which is a failure).
     */
    public static void assertInstanceOf(final String message, final Class<?> expectedType, final Object value) {
        if (expectedType != null) {
            if (!expectedType.isInstance(value)) {
                fail(nonNull(message) + "Value \"" + value + "\" is of type " + value.getClass().getSimpleName() +
                        " while the expected type was " + expectedType.getSimpleName() + '.');
            }
        }
    }

    /**
     * Asserts that the given integer value is positive, including zero.
     *
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param value   The value to test.
     */
    public static void assertPositive(final String message, final int value) {
        if (value < 0) {
            fail(nonNull(message) + "Value is " + value + '.');
        }
    }

    /**
     * Asserts that the given integer value is strictly positive, excluding zero.
     *
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param value   The value to test.
     */
    public static void assertStrictlyPositive(final String message, final int value) {
        if (value <= 0) {
            fail(nonNull(message) + "Value is " + value + '.');
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that if both values are non-null, then the minimum value is not greater than the
     * maximum value.
     *
     * @param <T>     The type of values being compared.
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param minimum The lower bound of the range to test, or {@code null} if unbounded.
     * @param maximum The upper bound of the range to test, or {@code null} if unbounded.
     */
    @SuppressWarnings("unchecked")
    public static <T> void assertValidRange(final String message, final Comparable<T> minimum, final Comparable<T> maximum) {
        if (minimum != null && maximum != null) {
            if (minimum.compareTo((T) maximum) > 0) {
                fail(nonNull(message) + "Range found is [" + minimum + " ... " + maximum + "].");
            }
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that the minimum value is not greater than the maximum value.
     *
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param minimum The lower bound of the range to test.
     * @param maximum The upper bound of the range to test.
     */
    public static void assertValidRange(final String message, final int minimum, final int maximum) {
        if (minimum > maximum) {
            fail(nonNull(message) + "Range found is [" + minimum + " ... " + maximum + "].");
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that the minimum value is not greater than the maximum value. If one bound is or
     * both bounds are {@linkplain Double#NaN NaN}, then the test fails.
     *
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param minimum The lower bound of the range to test.
     * @param maximum The upper bound of the range to test.
     */
    public static void assertValidRange(final String message, final double minimum, final double maximum) {
        if (!(minimum <= maximum)) { // Use '!' for catching NaN.
            fail(nonNull(message) + "Range found is [" + minimum + " ... " + maximum + "].");
        }
    }

    /**
     * Asserts that the given value is between the given range. This method do <strong>not</strong>
     * tests the validity of the given range.
     *
     * @param <T>     The type of values being compared.
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param minimum The lower bound of the range (inclusive), or {@code null} if unbounded.
     * @param maximum The upper bound of the range (inclusive), or {@code null} if unbounded.
     * @param value   The value to test, or {@code null} (which is a failure).
     */
    public static <T> void assertBetween(final String message, final Comparable<T> minimum, final Comparable<T> maximum, T value) {
        if (minimum != null) {
            if (minimum.compareTo(value) > 0) {
                fail(nonNull(message) + "Value " + value + " is less than " + minimum + '.');
            }
        }
        if (maximum != null) {
            if (maximum.compareTo(value) < 0) {
                fail(nonNull(message) + "Value " + value + " is greater than " + maximum + '.');
            }
        }
    }

    /**
     * Asserts that the given value is between the given range. This method do <strong>not</strong>
     * tests the validity of the given range.
     *
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param minimum The lower bound of the range, inclusive.
     * @param maximum The upper bound of the range, inclusive.
     * @param value   The value to test.
     */
    public static void assertBetween(final String message, final int minimum, final int maximum, final int value) {
        if (value < minimum) {
            fail(nonNull(message) + "Value is " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(nonNull(message) + "Value is " + value + " is greater than " + maximum + '.');
        }
    }

    /**
     * Asserts that the given value is between the given range. If the value is
     * {@linkplain Double#NaN NaN}, then this test passes silently. This method
     * do <strong>not</strong> tests the validity of the given range.
     *
     * @param message Header of the exception message in case of failure, or {@code null} if none.
     * @param minimum The lower bound of the range, inclusive.
     * @param maximum The upper bound of the range, inclusive.
     * @param value   The value to test.
     */
    public static void assertBetween(final String message, final double minimum, final double maximum, final double value) {
        if (value < minimum) {
            fail(nonNull(message) + "Value is " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(nonNull(message) + "Value is " + value + " is greater than " + maximum + '.');
        }
    }

    /**
     * Asserts that the given value is contained in the given collection. If the given collection
     * is null, then this test passes silently (a null collection is considered as "unknown", not
     * empty). If the given value is null, then the test passes only if the given collection
     * contains the null element.
     *
     * @param message    Header of the exception message in case of failure, or {@code null} if none.
     * @param collection The collection where to look for inclusion, or {@code null}.
     * @param value      The value to test for inclusion.
     */
    public static void assertContains(final String message, final Collection<?> collection, final Object value) {
        if (collection != null) {
            if (!collection.contains(value)) {
                fail(nonNull(message) + "Looked for value \"" + value + "\" in a collection of " +
                        collection.size() + "elements.");
            }
        }
    }

    /**
     * Performs a lenient comparison of the given character sequences. First, this method locates
     * the {@linkplain Character#isUnicodeIdentifierStart(int) Unicode identifier start} in each
     * sequences, ignoring every characters before them. Then, starting from the identifier starts,
     * this method compares only the {@linkplain Character#isUnicodeIdentifierPart(int) Unicode
     * identifier parts} in a case-insensitive way.
     *
     * @param message  Header of the exception message in case of failure, or {@code null} if none.
     * @param expected The expected character sequence.
     * @param value The character sequence to compare.
     */
    public static void assertLenientEquals(final String message, final CharSequence expected, final CharSequence value) {
        final int expLength = expected.length();
        final int valLength = value.length();
        int       expOffset = 0;
        int       valOffset = 0;
        boolean   expPart   = false;
        boolean   valPart   = false;
        while (expOffset < expLength) {
            int expCode = Character.codePointAt(expected, expOffset);
            if (isUnicodeIdentifier(expCode, expPart)) {
                expPart = true;
                int valCode;
                do {
                    if (valOffset >= valLength) {
                        fail(nonNull(message) + "Expected \"" + expected + "\" but got \"" + value + "\". "
                                + "Missing part: \"" + expected.subSequence(expOffset, expLength) + "\".");
                        return;
                    }
                    valCode    = Character.codePointAt(value, valOffset);
                    valOffset += Character.charCount(valCode);
                } while (!isUnicodeIdentifier(valCode, valPart));
                valPart = true;
                expCode = Character.toLowerCase(expCode);
                valCode = Character.toLowerCase(valCode);
                if (valCode != expCode) {
                    fail(nonNull(message) + "Expected \"" + expected + "\" but got \"" + value + "\".");
                    return;
                }
            }
            expOffset += Character.charCount(expCode);
        }
        while (valOffset < valLength) {
            final int valCode = Character.codePointAt(value, valOffset);
            if (isUnicodeIdentifier(valCode, valPart)) {
                fail(nonNull(message) + "Expected \"" + expected + "\", but found it with a unexpected "
                        + "trailing string: \"" + value.subSequence(valOffset, valLength) + "\".");
            }
            valOffset += Character.charCount(valCode);
        }
    }

    /**
     * Returns {@code true} if the given codepoint is an unicode identifier start or part.
     */
    private static boolean isUnicodeIdentifier(final int codepoint, final boolean part) {
        return part ? Character.isUnicodeIdentifierPart (codepoint)
                    : Character.isUnicodeIdentifierStart(codepoint);
    }

    /**
     * Asserts that all axes in the given coordinate system are pointing toward the given
     * directions, in the same order.
     *
     * @param cs The coordinate system to test.
     * @param expected The expected axis directions.
     *
     * @since 3.1
     */
    public static void assertAxisDirectionsEqual(final CoordinateSystem cs, final AxisDirection... expected) {
        assertEquals("Wrong coordinate system dimension.", expected.length, cs.getDimension());
        for (int i=0; i<expected.length; i++) {
            assertEquals("Wrong axis direction.", expected[i], cs.getAxis(i).getDirection());
        }
    }
}
