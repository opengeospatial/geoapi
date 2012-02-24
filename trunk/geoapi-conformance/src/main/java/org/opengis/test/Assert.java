/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2012 Open Geospatial Consortium, Inc.
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
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;

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
     * Returns the concatenation of the given message with the given extension.
     * This method returns the given extension if the message is null or empty.
     * <p>
     * Invoking this method is equivalent to invoking {@code nonNull(message) + ext},
     * but avoid the creation of temporary objects in the common case where the message
     * is null.
     *
     * @param  message The message, or {@code null}.
     * @param  ext The extension to append after the message.
     * @return The concatenated string.
     */
    private static String concat(String message, final String ext) {
        if (message == null || (message = message.trim()).isEmpty()) {
            return ext;
        }
        return message + ' ' + ext;
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
            fail(nonNull(message) + "Value " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(nonNull(message) + "Value " + value + " is greater than " + maximum + '.');
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
            fail(nonNull(message) + "Value " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(nonNull(message) + "Value " + value + " is greater than " + maximum + '.');
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
     * Asserts that the identifiers in the given character sequences are equal,
     * ignoring non-identifier characters. First, this method locates
     * the {@linkplain Character#isUnicodeIdentifierStart(int) Unicode identifier start} in each
     * sequences, ignoring every characters before them. Then, starting from the identifier starts,
     * this method compares only the {@linkplain Character#isUnicodeIdentifierPart(int) Unicode
     * identifier parts} in a case-insensitive way until the end of character sequences.
     *
     * @param message  Header of the exception message in case of failure, or {@code null} if none.
     * @param expected The expected character sequence.
     * @param value The character sequence to compare.
     */
    public static void assertIdentifierEquals(final String message, final CharSequence expected, final CharSequence value) {
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
     * @param message  Header of the exception message in case of failure, or {@code null} if none.
     * @param cs       The coordinate system to test.
     * @param expected The expected axis directions.
     *
     * @since 3.1
     */
    public static void assertAxisDirectionsEqual(String message,
            final CoordinateSystem cs, final AxisDirection... expected)
    {
        assertEquals(concat(message, "Wrong coordinate system dimension."), expected.length, cs.getDimension());
        message = concat(message, "Wrong axis direction.");
        for (int i=0; i<expected.length; i++) {
            assertEquals(message, expected[i], cs.getAxis(i).getDirection());
        }
    }

    /**
     * Asserts that all control points of two shapes are equal.
     * This method performs the following checks:
     * <p>
     * <ol>
     *   <li>Ensures that the {@linkplain Shape#getBounds2D() shape bounds} are equal,
     *       up to the given tolerance thresholds.</li>
     *   <li>{@linkplain Shape#getPathIterator(AffineTransform) Gets the path iterator} of each shape.</li>
     *   <li>Ensures that the {@linkplain PathIterator#getWindingRule() winding rules} are equal.</li>
     *   <li>Iterates over all path segments until the iteration {@linkplain PathIterator#isDone() is done}.
     *       For each iteration step:<ol>
     *       <li>Invokes {@link PathIterator#currentSegment(double[])}.</li>
     *       <li>Ensures that the segment type (one of the {@code SEG_*} constants) is the same.</li>
     *       <li>Ensures that the ordinate values are equal, up to the given tolerance thresholds.</li>
     *   </ol></li>
     * </ol>
     *
     * @param message    Header of the exception message in case of failure, or {@code null} if none.
     * @param expected   The expected shape.
     * @param actual     The actual shape.
     * @param toleranceX The tolerance threshold for <var>x</var> ordinate values.
     * @param toleranceY The tolerance threshold for <var>y</var> ordinate values.
     *
     * @since 3.1
     */
    public static void assertShapeEquals(String message, final Shape expected,
            final Shape actual, final double toleranceX, final double toleranceY)
    {
        final Rectangle2D b0 = expected.getBounds2D();
        final Rectangle2D b1 = actual  .getBounds2D();
        final String mismatch = concat(message, "Mismatched bounds.");
        assertEquals(mismatch, b0.getMinX(), b1.getMinX(), toleranceX);
        assertEquals(mismatch, b0.getMaxX(), b1.getMaxX(), toleranceX);
        assertEquals(mismatch, b0.getMinY(), b1.getMinY(), toleranceY);
        assertEquals(mismatch, b0.getMaxY(), b1.getMaxY(), toleranceY);
        assertPathEquals(message, expected.getPathIterator(null), actual.getPathIterator(null), toleranceX, toleranceY);
    }

    /**
     * Asserts that all control points in two geometric paths are equal.
     * This method performs the following checks:
     * <p>
     * <ol>
     *   <li>Ensures that the {@linkplain PathIterator#getWindingRule() winding rules} are equal.</li>
     *   <li>Iterates over all path segments until the iteration {@linkplain PathIterator#isDone() is done}.
     *       For each iteration step:<ol>
     *       <li>Invokes {@link PathIterator#currentSegment(double[])}.</li>
     *       <li>Ensures that the segment type (one of the {@code SEG_*} constants) is the same.</li>
     *       <li>Ensures that the ordinate values are equal, up to the given tolerance thresholds.</li>
     *   </ol></li>
     * </ol>
     * <p>
     * This method can be used instead of {@link #assertShapeEquals(String, Shape, Shape, double, double)}
     * when the tester needs to compare the shapes with a non-null affine transform or a flatness factor.
     * in such case, the tester needs to invoke the {@link Shape#getPathIterator(AffineTransform, double)}
     * method himself.
     *
     * @param message    Header of the exception message in case of failure, or {@code null} if none.
     * @param expected   The expected path.
     * @param actual     The actual path.
     * @param toleranceX The tolerance threshold for <var>x</var> ordinate values.
     * @param toleranceY The tolerance threshold for <var>y</var> ordinate values.
     *
     * @since 3.1
     */
    public static void assertPathEquals(final String message, final PathIterator expected,
            final PathIterator actual, final double toleranceX, final double toleranceY)
    {
        assertEquals(concat(message, "Mismatched winding rule."), expected.getWindingRule(), actual.getWindingRule());
        final String   mismatchedType = concat(message, "Mismatched path segment type.");
        final String   mismatchedX    = concat(message, "Mismatched X ordinate value.");
        final String   mismatchedY    = concat(message, "Mismatched Y ordinate value.");
        final String   endOfPath      = concat(message, "Premature end of path.");
        final double[] expectedCoords = new double[6];
        final double[] actualCoords   = new double[6];
        while (!expected.isDone()) {
            assertFalse(endOfPath, actual.isDone());
            final int type = expected.currentSegment(expectedCoords);
            assertEquals(mismatchedType, type, actual.currentSegment(actualCoords));
            final int length;
            switch (type) {
                case PathIterator.SEG_CLOSE:   length = 0; break;
                case PathIterator.SEG_MOVETO:  // Fallthrough
                case PathIterator.SEG_LINETO:  length = 2; break;
                case PathIterator.SEG_QUADTO:  length = 4; break;
                case PathIterator.SEG_CUBICTO: length = 6; break;
                default: throw new AssertionError(nonNull(message) + "Unknown segment type: " + type);
            }
            for (int i=0; i<length;) {
                assertEquals(mismatchedX, expectedCoords[i], actualCoords[i++], toleranceX);
                assertEquals(mismatchedY, expectedCoords[i], actualCoords[i++], toleranceY);
            }
            actual.next();
            expected.next();
        }
        assertTrue(concat(message, "Expected end of path."), actual.isDone());
    }
}
