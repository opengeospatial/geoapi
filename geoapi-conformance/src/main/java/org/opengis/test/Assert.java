/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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
 * @version 3.0
 * @since   2.2
 */
public class Assert extends org.junit.Assert {
    /**
     * For subclass constructors only.
     */
    protected Assert() {
    }

    /**
     * Asserts that the given value is an instance of the given class. No tests are performed if
     * the type is {@code null}. If the type is not-null but the value is null, this is considered
     * as a failure.
     *
     * @param message      The message to send in case of failure.
     * @param expectedType The expected parent class of the value, or {@code null}.
     * @param value        The value to test, or {@code null} (which is a failure).
     */
    public static void assertInstanceOf(String message, Class<?> expectedType, Object value) {
        if (expectedType != null) {
            if (!expectedType.isInstance(value)) {
                fail(message + " Value \"" + value + "\" is of type " + value.getClass().getSimpleName() +
                        " while the expected type was " + expectedType.getSimpleName() + '.');
            }
        }
    }

    /**
     * Asserts that the given integer value is positive, including zero.
     *
     * @param message The message to send in case of failure.
     * @param value   The value to test.
     */
    public static void assertPositive(String message, int value) {
        if (value < 0) {
            fail(message + " Value is " + value + '.');
        }
    }

    /**
     * Asserts that the given integer value is strictly positive, excluding zero.
     *
     * @param message The message to send in case of failure.
     * @param value   The value to test.
     */
    public static void assertStrictlyPositive(String message, int value) {
        if (value <= 0) {
            fail(message + " Value is " + value + '.');
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that if both values are non-null, then the minimum value is not greater than the
     * maximum value.
     *
     * @param <T>     The type of values being compared.
     * @param message The message to send in case of failure.
     * @param minimum The lower bound of the range to test, or {@code null} if unbounded.
     * @param maximum The upper bound of the range to test, or {@code null} if unbounded.
     */
    @SuppressWarnings("unchecked")
    public static <T> void assertValidRange(String message, Comparable<T> minimum, Comparable<T> maximum) {
        if (minimum != null && maximum != null) {
            if (minimum.compareTo((T) maximum) > 0) {
                fail(message + " Range found is [" + minimum + " ... " + maximum + "].");
            }
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that the minimum value is not greater than the maximum value.
     *
     * @param message The message to send in case of failure.
     * @param minimum The lower bound of the range to test.
     * @param maximum The upper bound of the range to test.
     */
    public static void assertValidRange(String message, int minimum, int maximum) {
        if (minimum > maximum) {
            fail(message + " Range found is [" + minimum + " ... " + maximum + "].");
        }
    }

    /**
     * Asserts that the given minimum and maximum values make a valid range. More specifically
     * asserts that the minimum value is not greater than the maximum value. If one bound is or
     * both bounds are {@linkplain Double#NaN NaN}, then the test fails.
     *
     * @param message The message to send in case of failure.
     * @param minimum The lower bound of the range to test.
     * @param maximum The upper bound of the range to test.
     */
    public static void assertValidRange(String message, double minimum, double maximum) {
        if (!(minimum <= maximum)) { // Use '!' for catching NaN.
            fail(message + " Range found is [" + minimum + " ... " + maximum + "].");
        }
    }

    /**
     * Asserts that the given value is between the given range. This method do <strong>not</strong>
     * tests the validity of the given range.
     *
     * @param <T>     The type of values being compared.
     * @param message The message to send in case of failure.
     * @param minimum The lower bound of the range (inclusive), or {@code null} if unbounded.
     * @param maximum The upper bound of the range (inclusive), or {@code null} if unbounded.
     * @param value   The value to test, or {@code null} (which is a failure).
     */
    public static <T> void assertBetween(String message, Comparable<T> minimum, Comparable<T> maximum, T value) {
        if (minimum != null) {
            if (minimum.compareTo(value) > 0) {
                fail(message + " Value " + value + " is less than " + minimum + '.');
            }
        }
        if (maximum != null) {
            if (maximum.compareTo(value) < 0) {
                fail(message + " Value " + value + " is greater than " + maximum + '.');
            }
        }
    }

    /**
     * Asserts that the given value is between the given range. This method do <strong>not</strong>
     * tests the validity of the given range.
     *
     * @param message The message to send in case of failure.
     * @param minimum The lower bound of the range, inclusive.
     * @param maximum The upper bound of the range, inclusive.
     * @param value   The value to test.
     */
    public static void assertBetween(String message, int minimum, int maximum, int value) {
        if (value < minimum) {
            fail(message + " Value is " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(message + " Value is " + value + " is greater than " + maximum + '.');
        }
    }

    /**
     * Asserts that the given value is between the given range. If the value is
     * {@linkplain Double#NaN NaN}, then this test passes silently. This method
     * do <strong>not</strong> tests the validity of the given range.
     *
     * @param message The message to send in case of failure.
     * @param minimum The lower bound of the range, inclusive.
     * @param maximum The upper bound of the range, inclusive.
     * @param value   The value to test.
     */
    public static void assertBetween(String message, double minimum, double maximum, double value) {
        if (value < minimum) {
            fail(message + " Value is " + value + " is less than " + minimum + '.');
        }
        if (value > maximum) {
            fail(message + " Value is " + value + " is greater than " + maximum + '.');
        }
    }

    /**
     * Asserts that the given value is contained in the given collection. If the given collection
     * is null, then this test passes silently (a null collection is considered as "unknown", not
     * empty). If the given value is null, then the test passes only if the given collection
     * contains the null element.
     *
     * @param message    The message to send in case of failure.
     * @param collection The collection where to look for inclusion, or {@code null}.
     * @param value      The value to test for inclusion.
     */
    public static void assertContains(String message, Collection<?> collection, Object value) {
        if (collection != null) {
            if (!collection.contains(value)) {
                fail(message + " Looked for value \"" + value + "\" in a collection of " +
                        collection.size() + "elements.");
            }
        }
    }
}
