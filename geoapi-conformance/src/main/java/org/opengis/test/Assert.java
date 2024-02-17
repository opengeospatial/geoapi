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


/**
 * Extension to JUnit assertion methods.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 *
 * @deprecated Replaced by {@link Assertion} for consistency with JUnit 5 conventions.
 */
@Deprecated(since="3.1", forRemoval=true)
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public final strictfp class Assert {
    /**
     * Do not allow instantiation of this class.
     */
    private Assert() {
    }

    /**
     * Asserts that the given value is an instance of the given class. No tests are performed if
     * the type is {@code null}. If the type is not-null but the value is null, this is considered
     * as a failure.
     *
     * @param message       header of the exception message in case of failure, or {@code null} if none.
     * @param expectedType  the expected parent class of the value, or {@code null} if unrestricted.
     * @param value         the value to test, or {@code null} (which is a failure).
     *
     * @deprecated Replaced by {@code org.junit.jupiter.api.Assertions.assertInstanceOf(…)}.
     */
    @Deprecated
    public static void assertInstanceOf(final String message, final Class<?> expectedType, final Object value) {
        org.junit.jupiter.api.Assertions.assertInstanceOf(expectedType, value, message);
    }

    /**
     * Asserts that the given integer value is positive, including zero.
     *
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param value    the value to test.
     */
    public static void assertPositive(final String message, final int value) {
        Assertions.assertPositive(value, message);
    }

    /**
     * Asserts that the given integer value is strictly positive, excluding zero.
     *
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param value    the value to test.
     */
    public static void assertStrictlyPositive(final String message, final int value) {
        Assertions.assertStrictlyPositive(value, message);
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that if both values are non-null, then the minimum value is not greater than the
     * maximum value.
     *
     * @param <T>      the type of values being compared.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param minimum  the lower bound of the range to test, or {@code null} if unbounded.
     * @param maximum  the upper bound of the range to test, or {@code null} if unbounded.
     */
    @SuppressWarnings("unchecked")
    public static <T> void assertValidRange(final String message, final Comparable<T> minimum, final Comparable<T> maximum) {
        Assertions.assertValidRange(minimum, maximum, message);
    }

    /**
     * Asserts that the given minimum is smaller or equals to the given maximum.
     *
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param minimum  the lower bound of the range to test.
     * @param maximum  the upper bound of the range to test.
     */
    public static void assertValidRange(final String message, final int minimum, final int maximum) {
        Assertions.assertValidRange(minimum, maximum, message);
    }

    /**
     * Asserts that the given minimum is smaller or equals to the given maximum.
     * If one bound is or both bounds are {@linkplain Double#NaN NaN}, then the test fails.
     *
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param minimum  the lower bound of the range to test.
     * @param maximum  the upper bound of the range to test.
     */
    public static void assertValidRange(final String message, final double minimum, final double maximum) {
        Assertions.assertValidRange(minimum, maximum, message);
    }

    /**
     * Asserts that the given value is inside the given range. This method does <strong>not</strong>
     * test the validity of the given [{@code minimum} … {@code maximum}] range.
     *
     * @param <T>      the type of values being compared.
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param minimum  the lower bound of the range (inclusive), or {@code null} if unbounded.
     * @param maximum  the upper bound of the range (inclusive), or {@code null} if unbounded.
     * @param value    the value to test, or {@code null} (which is a failure).
     */
    public static <T> void assertBetween(final String message, final Comparable<T> minimum, final Comparable<T> maximum, T value) {
        Assertions.assertBetween(minimum, maximum, value, message);
    }

    /**
     * Asserts that the given value is inside the given range. This method does <strong>not</strong>
     * test the validity of the given [{@code minimum} … {@code maximum}] range.
     *
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param minimum  the lower bound of the range, inclusive.
     * @param maximum  the upper bound of the range, inclusive.
     * @param value    the value to test.
     */
    public static void assertBetween(final String message, final int minimum, final int maximum, final int value) {
        Assertions.assertBetween(minimum, maximum, value, message);
    }

    /**
     * Asserts that the given value is inside the given range. If the given {@code value} is
     * {@linkplain Double#NaN NaN}, then this test passes silently. This method does <strong>not</strong>
     * test the validity of the given [{@code minimum} … {@code maximum}] range.
     *
     * @param message  header of the exception message in case of failure, or {@code null} if none.
     * @param minimum  the lower bound of the range, inclusive.
     * @param maximum  the upper bound of the range, inclusive.
     * @param value    the value to test.
     */
    public static void assertBetween(final String message, final double minimum, final double maximum, final double value) {
        Assertions.assertBetween(minimum, maximum, value, message);
    }

    /**
     * Asserts that the given value is contained in the given collection. If the given collection
     * is null, then this test passes silently (a null collection is considered as "unknown", not
     * empty). If the given value is null, then the test passes only if the given collection
     * contains the null element.
     *
     * @param message     header of the exception message in case of failure, or {@code null} if none.
     * @param collection  the collection where to look for inclusion, or {@code null} if unrestricted.
     * @param value       the value to test for inclusion.
     */
    public static void assertContains(final String message, final Collection<?> collection, final Object value) {
        Assertions.assertContains(collection, value, message);
    }

    /**
     * @deprecated Renamed {@link Assertions#assertUnicodeIdentifierEquals(String, CharSequence, CharSequence, boolean)}
     * for avoiding confusion with the {@code Identifier} interface.
     *
     * @param message   header of the exception message in case of failure, or {@code null} if none.
     * @param expected  the expected character sequence.
     * @param value     the character sequence to compare.
     */
    @Deprecated
    public static void assertIdentifierEquals(final String message, final CharSequence expected, final CharSequence value) {
        Assertions.assertUnicodeIdentifierEquals(expected, value, true, message);
    }
}
