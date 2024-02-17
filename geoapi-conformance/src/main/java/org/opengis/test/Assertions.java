/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test;

import java.util.Collection;
import java.util.function.Supplier;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;

import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.test.coverage.image.PixelIterator;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Extension to JUnit assertion methods.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public final strictfp class Assertions {
    /**
     * The keyword for unrestricted value in {@link String} arguments.
     */
    private static final String UNRESTRICTED = "##unrestricted";

    /**
     * Do not allow instantiation of this class.
     */
    private Assertions() {
    }

    /**
     * Returns the given message, or an empty string if the message is null.
     *
     * @param  message  the message, possibly null.
     * @return the given message or an empty string, never null.
     */
    private static String nonNull(final String message) {
        return (message != null) ? message.trim().concat(" ") : "";
    }

    /**
     * Returns the concatenation of the given message with the given extension.
     * This method returns the given extension if the message is null or empty.
     *
     * <p>Invoking this method is equivalent to invoking {@code nonNull(message) + ext},
     * but avoid the creation of temporary objects in the common case where the message
     * is null.</p>
     *
     * @param  message  the message, or {@code null}.
     * @param  ext      the extension to append after the message.
     * @return the concatenated string.
     */
    private static String concat(String message, final String ext) {
        if (message == null || (message = message.trim()).isEmpty()) {
            return ext;
        }
        return message + ' ' + ext;
    }

    /**
     * Verifies if we expected a null value, then returns {@code true} if the value is null as expected.
     *
     * @param  expected  the expected value (only its existence is checked).
     * @param  actual    the actual value (only its existence is checked).
     * @param  message   the message to show in case of test failure, or {@code null}.
     * @return whether the actual value is null.
     */
    private static boolean isNull(final Object expected, final Object actual, final String message) {
        final boolean isNull = (actual == null);
        if (isNull != (expected == null)) {
            fail(concat(message, isNull ? "Value is null." : "Expected null."));
        }
        return isNull;
    }

    /**
     * Asserts that the given integer value is positive, including zero.
     *
     * @param value    the value to test.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertPositive(final int value, final String message) {
        if (value < 0) {
            fail(nonNull(message) + "Value is " + value + '.');
        }
    }

    /**
     * Asserts that the given integer value is strictly positive, excluding zero.
     *
     * @param value    the value to test.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertStrictlyPositive(final int value, final String message) {
        if (value <= 0) {
            fail(nonNull(message) + "Value is " + value + '.');
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that if both values are non-null, then the minimum value is not greater than the
     * maximum value.
     *
     * @param <T>      the type of values being compared.
     * @param minimum  the lower bound of the range to test, or {@code null} if unbounded.
     * @param maximum  the upper bound of the range to test, or {@code null} if unbounded.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    @SuppressWarnings("unchecked")
    public static <T> void assertValidRange(final Comparable<T> minimum, final Comparable<T> maximum, final String message) {
        if (minimum != null && maximum != null) {
            if (minimum.compareTo((T) maximum) > 0) {
                fail(nonNull(message) + "Range found is [" + minimum + " ... " + maximum + "].");
            }
        }
    }

    /**
     * Asserts that the given minimum is smaller or equals to the given maximum.
     *
     * @param minimum  the lower bound of the range to test.
     * @param maximum  the upper bound of the range to test.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertValidRange(final int minimum, final int maximum, final String message) {
        if (minimum > maximum) {
            fail(nonNull(message) + "Range found is [" + minimum + " ... " + maximum + "].");
        }
    }

    /**
     * Asserts that the given minimum is smaller or equals to the given maximum.
     * If one bound is or both bounds are {@linkplain Double#NaN NaN}, then the test fails.
     *
     * @param minimum  the lower bound of the range to test.
     * @param maximum  the upper bound of the range to test.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertValidRange(final double minimum, final double maximum, final String message) {
        if (!(minimum <= maximum)) {            // Use `!` for catching NaN.
            fail(nonNull(message) + "Range found is [" + minimum + " ... " + maximum + "].");
        }
    }

    /**
     * Asserts that the given value is inside the given range. This method does <strong>not</strong>
     * test the validity of the given [{@code minimum} … {@code maximum}] range.
     *
     * @param <T>      the type of values being compared.
     * @param minimum  the lower bound of the range (inclusive), or {@code null} if unbounded.
     * @param maximum  the upper bound of the range (inclusive), or {@code null} if unbounded.
     * @param value    the value to test, or {@code null} (which is a failure).
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static <T> void assertBetween(final Comparable<T> minimum, final Comparable<T> maximum, T value, final String message) {
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
     * Asserts that the given value is inside the given range. This method does <strong>not</strong>
     * test the validity of the given [{@code minimum} … {@code maximum}] range.
     *
     * @param minimum  the lower bound of the range, inclusive.
     * @param maximum  the upper bound of the range, inclusive.
     * @param value    the value to test.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertBetween(final int minimum, final int maximum, final int value, final String message) {
        if (value < minimum) {
            fail(nonNull(message) + "Value " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(nonNull(message) + "Value " + value + " is greater than " + maximum + '.');
        }
    }

    /**
     * Asserts that the given value is inside the given range. If the given {@code value} is
     * {@linkplain Double#NaN NaN}, then this test passes silently. This method does <strong>not</strong>
     * test the validity of the given [{@code minimum} … {@code maximum}] range.
     *
     * @param minimum  the lower bound of the range, inclusive.
     * @param maximum  the upper bound of the range, inclusive.
     * @param value    the value to test.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertBetween(final double minimum, final double maximum, final double value, final String message) {
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
     * @param collection  the collection where to look for inclusion, or {@code null} if unrestricted.
     * @param value       the value to test for inclusion.
     * @param message     header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertContains(final Collection<?> collection, final Object value, final String message) {
        if (collection != null) {
            if (!collection.contains(value)) {
                fail(nonNull(message) + "Looked for value \"" + value + "\" in a collection of " +
                        collection.size() + "elements.");
            }
        }
    }

    /**
     * Asserts that the title or an alternate title of the given citation is equal to the given string.
     * This method is typically used for testing if a citation stands for the OGC, OGP or EPSG authority
     * for instance. Such abbreviations are often declared as {@linkplain Citation#getAlternateTitles()
     * alternate titles} rather than the main {@linkplain Citation#getTitle() title}, but this method
     * tests both for safety.
     *
     * @param expected  the expected title or alternate title.
     * @param actual    the citation to test.
     * @param message   header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertAnyTitleEquals(final String expected, final Citation actual, final String message) {
        if (isNull(expected, actual, message)) {
            return;
        }
        InternationalString title = actual.getTitle();
        if (title != null && expected.equals(title.toString())) {
            return;
        }
        for (final InternationalString t : actual.getAlternateTitles()) {
            if (expected.equals(t.toString())) {
                return;
            }
        }
        fail(concat(message, '"' + expected + "\" not found in title or alternate titles."));
    }

    /**
     * Asserts that the given identifier is equal to the given authority, code space, version and code.
     * If any of the above-cited properties is {@code ""##unrestricted"}, then it will not be verified.
     * This flexibility is useful in the common case where a test accepts any {@code version} value.
     *
     * @param authority  the expected authority title or alternate title (may be {@code null}), or {@code "##unrestricted"}.
     * @param codeSpace  the expected code space (may be {@code null}), or {@code "##unrestricted"}.
     * @param version    the expected version    (may be {@code null}), or {@code "##unrestricted"}.
     * @param code       the expected code value (may be {@code null}), or {@code "##unrestricted"}.
     * @param actual     the identifier to test.
     * @param message    header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertIdentifierEquals(final String authority, final String codeSpace, final String version,
            final String code, final Identifier actual, final String message)
    {
        if (actual == null) {
            fail(concat(message, "Identifier is null"));
        } else {
            if (!UNRESTRICTED.equals(authority)) assertAnyTitleEquals(authority, actual.getAuthority(), message);
            if (!UNRESTRICTED.equals(codeSpace)) assertEquals(codeSpace, actual.getCodeSpace(), () -> concat(message, "Wrong code space"));
            if (!UNRESTRICTED.equals(version))   assertEquals(version,   actual.getVersion(),   () -> concat(message, "Wrong version"));
            if (!UNRESTRICTED.equals(code))      assertEquals(code,      actual.getCode(),      () -> concat(message, "Wrong code"));
        }
    }

    /**
     * Asserts that the character sequences are equal, ignoring any characters that are not valid for Unicode identifiers.
     * First, this method locates the {@linkplain Character#isUnicodeIdentifierStart(int) Unicode identifier start}
     * in each sequences, ignoring any other characters before them. Then, starting from the identifier starts, this
     * method compares only the {@linkplain Character#isUnicodeIdentifierPart(int) Unicode identifier parts} until
     * the end of character sequences.
     *
     * <p><b>Examples:</b> {@code "WGS 84"} and {@code "WGS84"} as equal according this method.</p>
     *
     * @param expected    the expected character sequence (may be {@code null}), or {@code "##unrestricted"}.
     * @param actual      the character sequence to compare, or {@code null}.
     * @param ignoreCase  {@code true} for ignoring case.
     * @param message     header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertUnicodeIdentifierEquals(final CharSequence expected, final CharSequence actual,
            final boolean ignoreCase, final String message)
    {
        if (UNRESTRICTED.equals(expected) || isNull(expected, actual, message)) {
            return;
        }
        final int expLength = expected.length();
        final int valLength = actual.length();
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
                        fail(nonNull(message) + "Expected \"" + expected + "\" but got \"" + actual + "\". "
                                + "Missing part: \"" + expected.subSequence(expOffset, expLength) + "\".");
                        return;
                    }
                    valCode    = Character.codePointAt(actual, valOffset);
                    valOffset += Character.charCount(valCode);
                } while (!isUnicodeIdentifier(valCode, valPart));
                valPart = true;
                if (ignoreCase) {
                    expCode = Character.toLowerCase(expCode);
                    valCode = Character.toLowerCase(valCode);
                }
                if (valCode != expCode) {
                    fail(nonNull(message) + "Expected \"" + expected + "\" but got \"" + actual + "\".");
                    return;
                }
            }
            expOffset += Character.charCount(expCode);
        }
        while (valOffset < valLength) {
            final int valCode = Character.codePointAt(actual, valOffset);
            if (isUnicodeIdentifier(valCode, valPart)) {
                fail(nonNull(message) + "Expected \"" + expected + "\", but found it with a unexpected "
                        + "trailing string: \"" + actual.subSequence(valOffset, valLength) + "\".");
            }
            valOffset += Character.charCount(valCode);
        }
    }

    /**
     * Returns {@code true} if the given codepoint is an unicode identifier start or part.
     *
     * @param  codepoint  the code point to test.
     * @param  part       {@code false} for identifier start, or {@code true} for identifier part.
     * @return whether the given code point is a Unicode identifier start or part.
     */
    private static boolean isUnicodeIdentifier(final int codepoint, final boolean part) {
        return part ? Character.isUnicodeIdentifierPart (codepoint)
                    : Character.isUnicodeIdentifierStart(codepoint);
    }

    /**
     * Asserts that all axes in the given coordinate system are pointing toward the given directions,
     * in the same order.
     *
     * @param cs        the coordinate system to test.
     * @param expected  the expected axis directions.
     */
    public static void assertAxisDirectionsEqual(final CoordinateSystem cs, final AxisDirection... expected) {
        assertAxisDirectionsEqual(cs, expected, null);
    }

    /**
     * Asserts that all axes in the given coordinate system are pointing toward the given directions,
     * in the same order.
     *
     * @param cs        the coordinate system to test.
     * @param expected  the expected axis directions.
     * @param message   header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertAxisDirectionsEqual(final CoordinateSystem cs, final AxisDirection[] expected, final String message) {
        assertEquals(expected.length, cs.getDimension(), () -> concat(message, "Wrong coordinate system dimension."));
        for (int i=0; i<expected.length; i++) {
            final int ci = i;   // Because lambda expressions require final values.
            assertEquals(expected[i], cs.getAxis(i).getDirection(),
                    () -> concat(message, "Wrong axis direction at index" + ci + '.'));
        }
    }

    /**
     * Asserts that the given matrix is equal to the expected one, up to the given tolerance value.
     *
     * @param expected   the expected matrix, which may be {@code null}.
     * @param actual     the matrix to compare, or {@code null}.
     * @param tolerance  the tolerance threshold.
     * @param message    header of the exception message in case of failure, or {@code null} if none.
     *
     * @see org.opengis.test.referencing.TransformTestCase#assertMatrixEquals(String, Matrix, Matrix, Matrix)
     */
    public static void assertMatrixEquals(final Matrix expected, final Matrix actual, final double tolerance, final String message) {
        if (isNull(expected, actual, message)) {
            return;
        }
        final int numRow = actual.getNumRow();
        final int numCol = actual.getNumCol();
        assertEquals(expected.getNumRow(), numRow, "numRow");
        assertEquals(expected.getNumCol(), numCol, "numCol");
        for (int j=0; j<numRow; j++) {
            for (int i=0; i<numCol; i++) {
                final double e = expected.getElement(j,i);
                final double a = actual.getElement(j,i);
                if (!(StrictMath.abs(e - a) <= tolerance) && Double.doubleToLongBits(a) != Double.doubleToLongBits(e)) {
                    fail(nonNull(message) + "Matrix.getElement(" + j + ", " + i + "): expected " + e + " but got " + a);
                }
            }
        }
    }

    /**
     * Asserts that all control points of two shapes are equal.
     * This method performs the following checks:
     *
     * <ol>
     *   <li>Ensures that the {@linkplain Shape#getBounds2D() shape bounds} are equal,
     *       up to the given tolerance thresholds.</li>
     *   <li>{@linkplain Shape#getPathIterator(AffineTransform) Gets the path iterator} of each shape.</li>
     *   <li>Ensures that the {@linkplain PathIterator#getWindingRule() winding rules} are equal.</li>
     *   <li>Iterates over all path segments until the iteration {@linkplain PathIterator#isDone() is done}.
     *       For each iteration step:<ol>
     *       <li>Invokes {@link PathIterator#currentSegment(double[])}.</li>
     *       <li>Ensures that the segment type (one of the {@code SEG_*} constants) is the same.</li>
     *       <li>Ensures that the coordinate values are equal, up to the given tolerance thresholds.</li>
     *   </ol></li>
     * </ol>
     *
     * @param expected    the expected shape, which may be {@code null}.
     * @param actual      the actual shape, or {@code null}.
     * @param toleranceX  the tolerance threshold for <var>x</var> coordinate values.
     * @param toleranceY  the tolerance threshold for <var>y</var> coordinate values.
     * @param message     header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertShapeEquals(final Shape expected, final Shape actual,
            final double toleranceX, final double toleranceY, final String message)
    {
        if (isNull(expected, actual, message)) {
            return;
        }
        final Rectangle2D b0 = expected.getBounds2D();
        final Rectangle2D b1 = actual  .getBounds2D();
        final Supplier<String> mismatch = () -> concat(message, "Mismatched bounds.");
        assertEquals(b0.getMinX(), b1.getMinX(), toleranceX, mismatch);
        assertEquals(b0.getMaxX(), b1.getMaxX(), toleranceX, mismatch);
        assertEquals(b0.getMinY(), b1.getMinY(), toleranceY, mismatch);
        assertEquals(b0.getMaxY(), b1.getMaxY(), toleranceY, mismatch);
        assertPathEquals(expected.getPathIterator(null), actual.getPathIterator(null), toleranceX, toleranceY, message);
    }

    /**
     * Asserts that all control points in two geometric paths are equal.
     * This method performs the following checks:
     *
     * <ol>
     *   <li>Ensures that the {@linkplain PathIterator#getWindingRule() winding rules} are equal.</li>
     *   <li>Iterates over all path segments until the iteration {@linkplain PathIterator#isDone() is done}.
     *       For each iteration step:<ol>
     *       <li>Invokes {@link PathIterator#currentSegment(double[])}.</li>
     *       <li>Ensures that the segment type (one of the {@code SEG_*} constants) is the same.</li>
     *       <li>Ensures that the coordinate values are equal, up to the given tolerance thresholds.</li>
     *   </ol></li>
     * </ol>
     *
     * This method can be used instead of {@link #assertShapeEquals(String, Shape, Shape, double, double)}
     * when the tester needs to compare the shapes with a non-null affine transform or a flatness factor.
     * in such case, the tester needs to invoke the {@link Shape#getPathIterator(AffineTransform, double)}
     * method himself.
     *
     * @param expected    the expected path, which may be {@code null}.
     * @param actual      the actual path, or {@code null}.
     * @param toleranceX  the tolerance threshold for <var>x</var> coordinate values.
     * @param toleranceY  the tolerance threshold for <var>y</var> coordinate values.
     * @param message     header of the exception message in case of failure, or {@code null} if none.
     */
    public static void assertPathEquals(final PathIterator expected, final PathIterator actual,
            final double toleranceX, final double toleranceY, final String message)
    {
        if (isNull(expected, actual, message)) {
            return;
        }
        assertEquals(expected.getWindingRule(), actual.getWindingRule(),
                     () -> concat(message, "Mismatched winding rule."));
        final Supplier<String> mismatchedType = () -> concat(message, "Mismatched path segment type.");
        final Supplier<String> mismatchedX    = () -> concat(message, "Mismatched X coordinate value.");
        final Supplier<String> mismatchedY    = () -> concat(message, "Mismatched Y coordinate value.");
        final Supplier<String> endOfPath      = () -> concat(message, "Premature end of path.");
        final double[] expectedCoords = new double[6];
        final double[] actualCoords   = new double[6];
        while (!expected.isDone()) {
            assertFalse(actual.isDone(), endOfPath);
            final int type = expected.currentSegment(expectedCoords);
            assertEquals(type, actual.currentSegment(actualCoords), mismatchedType);
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
                assertEquals(expectedCoords[i], actualCoords[i++], toleranceX, mismatchedX);
                assertEquals(expectedCoords[i], actualCoords[i++], toleranceY, mismatchedY);
            }
            actual.next();
            expected.next();
        }
        assertTrue(actual.isDone(), () -> concat(message, "Expected end of path."));
    }

    /**
     * Asserts that all sample values in the given images are equal. This method requires the images
     * {@linkplain RenderedImage#getWidth() width}, {@linkplain RenderedImage#getHeight() height}
     * and the {@linkplain java.awt.image.SampleModel#getNumBands() number of bands} to be equal,
     * but does <em>not</em> require the {@linkplain RenderedImage#getTile(int, int) tiling},
     * {@linkplain java.awt.image.ColorModel color model} or
     * {@linkplain java.awt.image.SampleModel#getDataType() datatype} to be equal.
     *
     * @param expected   an image containing the expected values, which may be {@code null}.
     * @param actual     the actual image containing the sample values to compare, or {@code null}.
     * @param tolerance  tolerance threshold for floating point comparisons.
     *                   This threshold is ignored if both images use integer datatype.
     * @param message    header of the exception message in case of failure, or {@code null} if none.
     *
     * @see PixelIterator#assertSampleValuesEqual(PixelIterator, double)
     */
    public static void assertSampleValuesEqual(final RenderedImage expected, final RenderedImage actual,
            final double tolerance, final String message)
    {
        if (isNull(expected, actual, message)) {
            return;
        }
        assertEquals(expected.getWidth(),  actual.getWidth(),  () -> concat(message, "Mismatched image width."));
        assertEquals(expected.getHeight(), actual.getHeight(), () -> concat(message, "Mismatched image height."));
        assertEquals(expected.getSampleModel().getNumBands(), actual.getSampleModel().getNumBands(),
                     () -> concat(message, "Mismatched number of bands."));
        final var iterator = new PixelIterator(expected);
        iterator.assertSampleValuesEqual(new PixelIterator(actual), tolerance);
    }
}
