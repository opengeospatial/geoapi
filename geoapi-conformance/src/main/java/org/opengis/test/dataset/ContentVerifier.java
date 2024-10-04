/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test.dataset;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import org.opengis.annotation.UML;
import org.opengis.metadata.Metadata;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.util.ControlledVocabulary;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.junit.jupiter.api.Assertions;


/**
 * A comparator of metadata or <abbr>CRS</abbr> properties against expected values.
 * The methods of this class should be invoked in the following order:
 *
 * <ol>
 *   <li>{@link #addPropertyToIgnore(Class, String) addPropertyToIgnore(…)} (optional)</li>
 *   <li>{@link #addExpectedValue addExpectedValue(…)} or {@code addExpectedValues(…)}</li>
 *   <li>{@link #addMetadataToVerify(Metadata) addMetadataToVerify(…)}</li>
 *   <li>{@link #compareMetadata()} or {@link #assertMetadataEquals()}</li>
 *   <li>{@link #reset()} if this verifier will be reused for other metadata.</li>
 * </ol>
 *
 * Subclasses can also override {@link #comparePropertyValue comparePropertyValue(…)}
 * for finer grain control the the properties to be compared.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   3.1
 */
public class ContentVerifier {
    /**
     * Path to a metadata elements. This is non-empty only while scanning a metadata object by the
     * {@link #addPropertyValue(Class, Object)} method. Values of this string builders are used as
     * keys in {@link #metadataValues} map.
     */
    private final StringBuilder currentPath;

    /**
     * Instances already visited, for avoiding never-ending recursive loops. This is non-empty only
     * while scanning a metadata object by the {@link #addPropertyValue(Class, Object)} method.
     */
    private final Set<Element> visited;

    /**
     * A (class, value) pair where the value is compared by identity. This is used for detecting never-ending loops.
     * Values shall not be compared with {@link Object#equals(Object)} because we have no guarantee that users wrote
     * a safe implementation and because it would produce false positives anyway.
     *
     * <p>We take in account the type, not only the value instance, because implementations are free to implement
     * more than one interface with the same class. For example, the same {@code value} instance could implement
     * both {@code Metadata} and {@code DataIdentification} interfaces.</p>
     */
    @SuppressWarnings("doclint:missing")
    private static final class Element {
        private final Class<?> type;
        private final Object value;

        Element(final Class<?> type, final Object value) {
            this.type  = type;
            this.value = value;
        }

        @Override public int hashCode() {
            return type.hashCode() ^ System.identityHashCode(value);
        }

        @Override public boolean equals(final Object obj) {
            if (obj instanceof Element) {
                final Element other = (Element) obj;
                return type.equals(other.type) && value == other.value;
            }
            return false;
        }
    }

    /**
     * All non-null metadata values found by the {@link #addPropertyValue(Class, Object)} method.
     */
    private final Map<String,Object> metadataValues;

    /**
     * All expected values specified by {@link #addExpectedValue(String, Object)} method.
     */
    private final Map<String,Object> expectedValues;

    /**
     * Paths of properties that were expected but not found.
     * The entry values are the values that were expected.
     */
    private final List<Map.Entry<String,Object>> missings;

    /**
     * Metadata values that do not match the expected values. We use a {@code List} instead of a {@code Map}
     * because the same key may appear more than once if the user invokes {@code addMetadataToVerify(…)} and
     * {@code compareMetadata(…)} many times.
     */
    private final List<Map.Entry<String,Object>> mismatches;

    /**
     * A mismatched value.
     */
    private static final class Mismatch {
        /** The expected metadata value. */ public final Object expected;
        /** The value found in metadata. */ public final Object actual;

        /**
         * Creates a new entry for a mismatched value.
         *
         * @param expected  the expected metadata value.
         * @param actual    the value found in metadata.
         */
        Mismatch(final Object expected, final Object actual) {
            this.expected = expected;
            this.actual   = actual;
        }

        /** Returns a string representation for debugging purpose. */
        @Override public String toString() {
            return toString(new StringBuilder()).toString();
        }

        /**
         * Formats the string representation in the given buffer.
         *
         * @param  appendTo  where to append the string representation.
         * @return the given buffer for method calls chaining.
         */
        final StringBuilder toString(final StringBuilder appendTo) {
            final boolean showType = expected != null && actual != null && expected.getClass() != actual.getClass();
            formatValue(expected, showType, appendTo.append("expected "));
            formatValue(actual,   showType, appendTo.append(" but was "));
            return appendTo;
        }
    }

    /**
     * Properties to ignore, identified by their <abbr>UML</abbr> identifier and enclosing interface.
     *
     * @see #addPropertyToIgnore(Class, String)
     */
    private final Map<Class<?>, Set<String>> ignore;

    /**
     * Properties to ignore, identified by their paths.
     *
     * @see #addPropertyToIgnore(Predicate)
     */
    private Predicate<String> pathsToIgnore;

    /**
     * Creates a new dataset content verifier.
     */
    public ContentVerifier() {
        currentPath    = new StringBuilder(80);
        visited        = new HashSet<>();
        metadataValues = new TreeMap<>();
        expectedValues = new LinkedHashMap<>();
        mismatches     = new ArrayList<>();
        missings       = new ArrayList<>();
        ignore         = new HashMap<>();
    }

    /**
     * Resets this verifier for comparison of another metadata object with the same expected values.
     * This method clears the values given to {@link #addMetadataToVerify(Metadata) addMetadataToVerify(…)},
     * but preserves the values given to {@link #addPropertyToIgnore(Class, String) addPropertyToIgnore(…)}
     * and {@link #addExpectedValue addExpectedValue(…)} or {@code addExpectedValues(…)}.
     */
    public void reset() {
        currentPath.setLength(0);
        visited.clear();
        metadataValues.clear();
        mismatches.clear();
        missings.clear();
    }

    /**
     * Adds a metadata property to ignore in all occurrences of a metadata of the given type.
     * The property is identified by a GeoAPI interface and the {@link UML} identifier of a property in that interface.
     * For example, the following code instructs this comparator to ignore the {@code onlineResource} property of all
     * {@link Citation} instances, no matter the path to that citation (<i>i.e.</i>, the parent metadata):
     *
     * {@snippet lang="java" :
     * addPropertyToIgnore(Citation.class, "onlineResource");
     * }
     *
     * Note that properties ignored by this method are not read at all,
     * <i>i.e.</i> their getter method on metadata objects is not invoked.
     *
     * <h4>Method calls ordering</h4>
     * If an {@code addMetadataToVerify(…)} method was invoked before this call to {@code addPropertyToIgnore(…)},
     * the previously added metadata properties are unaffected. This method modifies only the behavior of calls to
     * {@code addMetadataToVerify(…)} done <em>after</em> this method call.
     *
     * @param  type      GeoAPI interface containing the property to ignore.
     * @param  property  UML identifier of a property in the given interface.
     */
    public void addPropertyToIgnore(final Class<?> type, final String property) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(property);
        ignore.computeIfAbsent(type, (k) -> new HashSet<>()).add(property);
    }

    /**
     * Returns {@code true} if the given property shall be ignored.
     *
     * @param  type      the type containing the property to filter.
     * @param  property  the property to filter.
     * @return whether to ignore the given property.
     */
    private boolean isIgnored(final Class<?> type, final UML property) {
        final Set<String> properties = ignore.get(type);
        return (properties != null) && properties.contains(property.identifier());
    }

    /**
     * Adds a metadata property to ignore, using the path as a criterion.
     * For each property of a metadata object to verify, the property path as documented
     * in {@link #addExpectedValue(String, Object)} is given to the {@code pathFilter} predicate.
     * If the predicate returns {@code true}, then the property is ignored.
     *
     * <h4>Method calls ordering</h4>
     * If an {@code addMetadataToVerify(…)} method was invoked before this call to {@code addPropertyToIgnore(…)},
     * the previously added metadata properties are unaffected. This method modifies only the behavior of calls to
     * {@code addMetadataToVerify(…)} done <em>after</em> this method call.
     *
     * @param  pathFilter  a filter returning {@code true} for the path of properties to ignore.
     */
    public void addPropertyToIgnore(final Predicate<String> pathFilter) {
        if (pathsToIgnore == null) {
            pathsToIgnore = Objects.requireNonNull(pathFilter);
        } else {
            pathsToIgnore = pathsToIgnore.or(pathFilter);
        }
    }

    /**
     * Returns {@code true} if the given path shall be ignored.
     *
     * @param  propertyPath  path to the property to filter.
     * @return whether to ignore the specified property.
     */
    private boolean isIgnored(final String propertyPath) {
        return pathsToIgnore != null && pathsToIgnore.test(propertyPath);
    }

    /**
     * Adds the expected metadata value for the given path.
     * The path is a string like the following examples
     * ({@code [0]} is the index of an element in lists or collections):
     *
     * <ul>
     *   <li>{@code "identificationInfo[0].citation.identifier[0].code"}</li>
     *   <li>{@code "spatialRepresentationInfo[0].axisDimensionProperties[0].dimensionSize"}</li>
     * </ul>
     *
     * The {@code expectedValue} argument is the expected value for the property identified by the path.
     * Values are typically instances of {@link String}, {@link Number}, {@link java.time.temporal.Temporal},
     * {@link java.util.CodeList}, <i>etc.</i>
     *
     * @param  propertyPath   path to the property to verify.
     * @param  expectedValue  the expected values of the property identified by the path.
     * @throws IllegalArgumentException if a different value is already declared for the given path,
     *         or if the given property would be {@linkplain #addPropertyToIgnore(Predicate) ignored}.
     */
    public void addExpectedValue(final String propertyPath, final Object expectedValue) {
        if (isIgnored(propertyPath)) {
            throw new IllegalArgumentException("The \"" + propertyPath + "\" property path is ignored.");
        }
        final Object previous = expectedValues.putIfAbsent(propertyPath, expectedValue);
        if (previous != null && !previous.equals(expectedValue)) {
            throw new IllegalArgumentException(String.format("Metadata element \"%s\" is specified twice:"
                    + "%nValue 1: %s%nValue 2: %s%n", propertyPath, previous, expectedValue));
        }
    }

    /**
     * Adds the expected metadata values for the given paths.
     * Values in entries are the expected values for the properties identified by the keys.
     * This is a convenience method for multiple calls to {@link #addExpectedValue(String, Object)}.
     *
     * @param  expected  the expected values of properties identified by the keys.
     * @throws ClassCastException if an {@code others} element for a path is not a {@link String} instance.
     * @throws IllegalArgumentException if a path is already associated to a different value.
     *
     * @see #addExpectedValue(String, Object)
     */
    @SafeVarargs
    public final void addExpectedValues(final Map.Entry<String,?>... expected) {
        for (Map.Entry<String,?> entry : expected) {
            addExpectedValue(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Adds the expected metadata values for the given paths.
     * Values in the map are the expected values for the properties identified by the keys.
     * This is a convenience method for multiple calls to {@link #addExpectedValue(String, Object)}.
     *
     * @param  expected  the expected values of properties identified by the keys.
     * @throws IllegalArgumentException if a path is already associated to a different value.
     *
     * @see #addExpectedValue(String, Object)
     */
    public void addExpectedValues(final Map<String,?> expected) {
        for (Map.Entry<String,?> entry : expected.entrySet()) {
            addExpectedValue(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Stores properties of the given metadata, for later comparison against expected values.
     * If one or many {@link #addPropertyToIgnore(Class, String) addPropertyToIgnore(…)} methods have been invoked
     * before this method call, the properties that match an {@code addPropertyToIgnore(…)} criterion are excluded.
     * If this method is invoked more than once since construction or since the last call to {@link #reset()},
     * then all properties at the same paths shall either be excluded by an {@code addPropertyToIgnore(…)},
     * or have the same value.
     *
     * @param  actual  the metadata to compare against expected values, or {@code null} if none.
     * @throws IllegalStateException if the given metadata contains a property already found in a previous
     *         call to this method, and the values found in those two invocations are not equal.
     */
    public void addMetadataToVerify(final Metadata actual) {
        explode(Metadata.class, actual);
    }

    /**
     * Stores properties of the given <abbr>CRS</abbr>, for later comparison against expected values.
     * In this class, a Coordinate Reference System is considered as a kind of metadata.
     * See {@link #addMetadataToVerify(Metadata)} for more information.
     *
     * @param  actual  the <abbr>CRS</abbr> to compare against expected values, or {@code null} if none.
     * @throws IllegalStateException if the given CRS contains a property already found in a previous
     *         call to this method, and the values found in those two invocations are not equal.
     */
    public void addMetadataToVerify(final CoordinateReferenceSystem actual) {
        explode(CoordinateReferenceSystem.class, actual);
    }

    /**
     * Adds a snapshot of the given object for later comparison against expected values.
     *
     * @param  <T>     compile time value of {@code type}.
     * @param  type    the GeoAPI interface implemented by the given object.
     * @param  actual  the metadata or CRS read from a dataset, or {@code null} if none.
     * @throws IllegalStateException if the given object contains a property already found in a previous
     *         call to this method, and the values found in those two invocations are not equal.
     */
    private <T> void explode(final Class<T> type, final T actual) {
        if (actual != null) try {
            addPropertyValue(type, actual);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getTargetException();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Error) {
                throw (Error) cause;
            } else {
                throw new RuntimeException(cause);
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);                    // Should never happen since we invoked only public methods.
        } finally {
            currentPath.setLength(0);
            visited.clear();
        }
    }

    /**
     * Returns the sub-interfaces implemented by the given implementation class. For example if a property type
     * is {@code CoordinateReferenceSystem}, a given instance could implement the {@code GeographicCRS} subtype.
     *
     * @param  baseType        the property type.
     * @param  implementation  the class which may implement a specialized type.
     * @return the given type or one of its subtypes implemented by the given class.
     */
    private static Class<?> specialized(final Class<?> baseType, Class<?> implementation) {
        do {
            for (final Class<?> s : implementation.getInterfaces()) {
                if (baseType.isAssignableFrom(s) && s.isAnnotationPresent(UML.class)) {
                    return s;
                }
            }
            implementation = implementation.getSuperclass();
        } while (implementation != null);
        return baseType;
    }

    /**
     * Adds the given value in the {@link #metadataValues} map. If the given value is another metadata object,
     * then this method iterates recursively over all elements in that metadata. The key is the current value
     * of {@link #currentPath}.
     *
     * @param  type  the GeoAPI interface implemented by the given object, or the standard Java class if not a metadata type.
     * @param  obj   non-null instance of {@code type} to add in the map.
     * @throws InvocationTargetException if an error occurred while invoking client code.
     * @throws IllegalAccessException if the method to invoke is not public (should never happen with interfaces).
     * @throws IllegalStateException if a different metadata value is already presents for the current {@link #currentPath} key.
     */
    private void addPropertyValue(Class<?> type, final Object obj) throws InvocationTargetException, IllegalAccessException {
        if (InternationalString.class.isAssignableFrom(type) ||        // Most common case first.
           ControlledVocabulary.class.isAssignableFrom(type) ||
                    GenericName.class.isAssignableFrom(type) ||
                       !type.isAnnotationPresent(UML.class))
        {
            final String key = currentPath.toString();
            if (!isIgnored(key)) {
                final Object previous = metadataValues.putIfAbsent(key, obj);
                if (previous != null && !previous.equals(obj)) {
                    throw new IllegalStateException(String.format("Metadata element \"%s\" is specified twice "
                            + "with two different values:%nValue 1: %s%nValue 2: %s%n", key, previous, obj));
                }
            }
        } else if (pathsToIgnore == null || !pathsToIgnore.test(currentPath.toString())) {
            // Note: we didn't used `isIgnored(String)` in order to invoke `toString()` only if necessary.
            final Element recursionGuard = new Element(type, obj);
            if (visited.add(recursionGuard)) {
                final int pathElementPosition = currentPath.length();
                type = specialized(type, obj.getClass());               // Example: Identification may actually be DataIdentification
                for (final Method getter : type.getMethods()) {
                    if (getter.getParameterCount() != 0) {
                        continue;
                    }
                    if (getter.isAnnotationPresent(Deprecated.class)) {
                        continue;
                    }
                    final UML spec = getter.getAnnotation(UML.class);
                    if (spec == null || isIgnored(type, spec)) {
                        continue;
                    }
                    Class<?> valueType = getter.getReturnType();
                    if (Void.TYPE.equals(valueType)) {
                        continue;
                    }
                    final Object value = getter.invoke(obj, (Object[]) null);
                    if (value == null) {
                        continue;
                    }
                    final Iterator<?> values;
                    if (Map.class.isAssignableFrom(valueType)) {
                        values = ((Map<?,?>) value).keySet().iterator();
                        if (!values.hasNext()) continue;
                    } else if (Iterable.class.isAssignableFrom(valueType)) {
                        values = ((Iterable<?>) value).iterator();
                        if (!values.hasNext()) continue;
                    } else {
                        values = null;
                    }
                    if (pathElementPosition != 0) {
                        currentPath.append('.');
                    }
                    currentPath.append(spec.identifier());
                    if (values == null) {
                        addPropertyValue(valueType, value);
                    } else {
                        valueType = boundOfParameterizedProperty(getter.getGenericReturnType());
                        final int indexPosition = currentPath.append('[').length();
                        int i = 0;
                        do {
                            currentPath.append(i++).append(']');
                            addPropertyValue(valueType, values.next());
                            currentPath.setLength(indexPosition);
                        } while (values.hasNext());
                    }
                    currentPath.setLength(pathElementPosition);
                }
                if (!visited.remove(recursionGuard)) {
                    // Should never happen unless the map is modified concurrently in another thread.
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     * Returns the upper bounds of the parameterized type. For example if a method returns {@code Collection<String>},
     * then {@code boundOfParameterizedProperty(method.getGenericReturnType())} should return {@code String.class}.
     *
     * @param  type  the type for which to get parameterized bound type.
     * @return the parameterized bound type.
     */
    private static Class<?> boundOfParameterizedProperty(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) type).getActualTypeArguments();
            if (p != null && p.length == 2) {
                final Type raw = ((ParameterizedType) type).getRawType();
                if (raw instanceof Class<?> && Map.class.isAssignableFrom((Class<?>) raw)) {
                    /*
                     * If the type is a map, keep only the first type parameter (for keys type).
                     * The type that we retain here must be consistent with the choice of iterator
                     * (keys or values) done in above addPropertyValue(…) method.
                     */
                    p = Arrays.copyOf(p, 1);
                }
            }
            while (p != null && p.length == 1) {
                type = p[0];
                if (type instanceof WildcardType) {
                    p = ((WildcardType) type).getUpperBounds();
                } else {
                    if (type instanceof ParameterizedType) {
                        type = ((ParameterizedType) type).getRawType();
                    }
                    if (type instanceof Class<?>) {
                        return (Class<?>) type;
                    }
                    break;                              // Unknown type.
                }
            }
        }
        throw new IllegalArgumentException("Cannot find the parameterized type of " + type);
    }

    /**
     * Returns {@code true} if the given value should be considered as a "primitive" for formatting purpose.
     * Primitive are null, numbers or booleans, but we extend this definition to enumerations and code lists.
     *
     * @param  value  the value to test.
     * @return whether the specified value is a primitive for formatting purpose.
     */
    private static boolean isPrimitive(final Object value) {
        return (value == null) || (value instanceof ControlledVocabulary)
                || (value instanceof Number) || (value instanceof Boolean);
    }

    /**
     * Compares a metadata property value against the expected value.
     * The {@code expected} argument is a value given to a call to {@code addExpectedValue(…)},
     * and the {@code actual} argument is a value found in a metadata given to {@code addMetadataToVerify(…)}.
     * The default implementation compares the actual and expected values with {@link Object#equals(Object)},
     * with the following special cases:
     *
     * <ul>
     *   <li>If the expected value is a {@link Float}, then the two values
     *       are compared as {@linkplain Float#floatToIntBits(float) bit patterns}.</li>
     *   <li>If the expected value is a {@link Double}, then the two values
     *       are compared as {@linkplain Double#doubleToLongBits(double) bit patterns}.</li>
     *   <li>If the expected value is a {@link CharSequence}, then the two values
     *       are compared as {@linkplain CharSequence#toString() strings}.</li>
     * </ul>
     *
     * Subclasses can override if they want fine grain control on how values are compared.
     *
     * @param  propertyPath  path to the property value as documented in {@link #addExpectedValue addExpectedValue(…)}.
     * @param  expected      the value that was expected for the property path, or {@code null} if that property was unexpected.
     * @param  actual        the actual value found in the metadata to verify, or {@code null} if no value was found at that path.
     * @return whether the actual value can be considered equal to the expected value.
     *
     * @see #addPropertyToIgnore(Class, String)
     * @see #addPropertyToIgnore(Predicate)
     */
    protected boolean comparePropertyValue(final String propertyPath, final Object expected, final Object actual) {
        if (Objects.equals(expected, actual)) {
            return true;
        } else if (expected instanceof Number && actual instanceof Number) {
            if (expected instanceof Float) {
                if (Float.floatToIntBits((Float) expected) ==
                    Float.floatToIntBits(((Number) actual).floatValue()))
                {
                    return true;
                }
            } else if (expected instanceof Double) {
                if (Double.doubleToLongBits((Double) expected) ==
                    Double.doubleToLongBits(((Number) actual).doubleValue()))
                {
                    return true;
                }
            }
        } else if (expected instanceof CharSequence && actual != null) {
            // The main intent is to convert `InternationalString`.
            if (Objects.equals(expected.toString(), actual.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares actual metadata properties against the expected values.
     * The expected and actual values are specified by {@code addExpectedValues(…)} and {@code addMetadataToVerify(…)}
     * methods respectively, which must be invoked in that order before this {@code compareMetadata()} method.
     * In case of mismatch, this method does not cause test failure. Instead, the result is returned as a Boolean.
     * Comparison result can be viewed after this method call with {@link #toString()}.
     *
     * @return {@code true} if all properties match, with no missing property and no unexpected property.
     */
    public boolean compareMetadata() {
        Iterator<Map.Entry<String,Object>> it = expectedValues.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<String,Object> entry = it.next();
            final String propertyPath = entry.getKey();
            final Object expected = entry.getValue();
            final Object actual = metadataValues.remove(propertyPath);
            if (!comparePropertyValue(propertyPath, expected, actual)) {
                if (actual != null) {
                    mismatches.add(new AbstractMap.SimpleEntry<>(propertyPath, new Mismatch(expected, actual)));
                } else {
                    missings.add(entry);
                }
            }
        }
        /*
         * If user overrides `comparePropertyValue(…)`, check if values
         * unexpected by this class were actually expected by the user.
         */
        it = metadataValues.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<String,Object> entry = it.next();
            if (comparePropertyValue(entry.getKey(), null, entry.getValue())) {
                it.remove();
            }
        }
        return mismatches.isEmpty() && metadataValues.isEmpty() && missings.isEmpty();
    }

    /**
     * Asserts that actual metadata properties are equal to the expected values.
     * This method invokes {@link #compareMetadata()} and causes a test failure if the latter
     * method found any <em>mismatched</em>, <em>missing</em> or <em>unexpected</em> value.
     *
     * @throws AssertionError if {@link #compareMetadata()} returned {@code false}.
     */
    public void assertMetadataEquals() {
        if (!compareMetadata()) {
            Assertions.fail(toString());
        }
    }

    /**
     * Returns a string representation of the comparison results.
     * This method formats up to three blocks in a JSON-like format:
     *
     * <ul>
     *   <li>List of actual values that do no match the expected values.</li>
     *   <li>List of expected values that are missing in the actual values.</li>
     *   <li>List of actual values that were unexpected.</li>
     * </ul>
     *
     * @return a string representation of the comparison results.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        final String lineSeparator = System.lineSeparator();
        formatTable("mismatches", mismatches,                buffer, lineSeparator);
        formatTable("missings",   missings,                  buffer, lineSeparator);
        formatTable("unexpected", metadataValues.entrySet(), buffer, lineSeparator);
        return buffer.length() != 0 ? buffer.toString() : "No difference found.";
    }

    /**
     * Formats the given entry as a table in the given {@link StringBuilder}.
     *
     * @param label          table label.
     * @param values         values to format.
     * @param appendTo       where to write the value.
     * @param lineSeparator  value of {@link System#lineSeparator()}.
     */
    private static void formatTable(final String label, final Collection<Map.Entry<String,Object>> values,
            final StringBuilder appendTo, final String lineSeparator)
    {
        if (!values.isEmpty()) {
            appendTo.append(label).append(" = {").append(lineSeparator);
            int width = -1;
            for (final Map.Entry<String,Object> entry : values) {
                final int length = entry.getKey().length();
                if (length > width) width = length;
            }
            width++;
            for (final Map.Entry<String,Object> entry : values) {
                final String key = entry.getKey();
                appendTo.append("    entry(\"").append(key).append("\",");
                for (int i = width - key.length(); --i >= 0;) {
                    appendTo.append(' ');
                }
                final Object value = entry.getValue();
                if (value instanceof Mismatch) {
                    ((Mismatch) value).toString(appendTo);
                } else {
                    formatValue(value, false, appendTo);
                }
                appendTo.append("),").append(lineSeparator);
            }
            if (width > 0) {                                                                // Paranoiac check.
                appendTo.deleteCharAt(appendTo.length() - lineSeparator.length() - 1);      // Remove last comma.
            }
            appendTo.append('}').append(lineSeparator);
        }
    }

    /**
     * Formats the given value in the given buffer, eventually between quotes.
     *
     * @param value     value to format.
     * @param showType  whether to format the value type.
     * @param appendTo  where to write the value.
     */
    private static void formatValue(final Object value, final boolean showType, final StringBuilder appendTo) {
        final boolean quote = !isPrimitive(value);
        if (quote) appendTo.append('"');
        appendTo.append(value);
        if (quote) appendTo.append('"');
        if (showType) {
            appendTo.append(" (an instance of ").append(value.getClass().getSimpleName()).append(')');
        }
    }
}
