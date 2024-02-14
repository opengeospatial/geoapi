/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
import org.junit.Assert;


/**
 * Verification operations that compare metadata or CRS properties against the expected values.
 * The metadata or CRS to verify (typically read from a dataset) is specified by a call to one
 * of the {@code addMetadataToVerify(…)} methods. The expected metadata values are specified by
 * calls to {@code addExpectedValues(…)} methods. After the expected and actual values have been
 * specified, they can be compared by a call to {@code assertMetadataEquals()}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ContentVerifier {
    /**
     * Path to a metadata elements. This is non-empty only while scanning a metadata object by the
     * {@link #addPropertyValue(Class, Object)} method. Values of this string builders are used as
     * keys in {@link #metadataValues} map.
     */
    private final StringBuilder path;

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
     * All expected values specified by {@link #addExpectedValue(Map)} method.
     */
    private final Map<String,Object> expectedValues;

    /**
     * Paths of properties that were expected but not found.
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
            formatValue(expected, appendTo.append("expected "));
            formatValue(actual,   appendTo.append(" but was "));
            return appendTo;
        }
    }

    /**
     * Properties to ignore. They are specified by user with calls to {@link #addPropertyToIgnore(Class, String)}.
     */
    private final Map<Class<?>, Set<String>> ignore;

    /**
     * Creates a new dataset content verifier.
     */
    public ContentVerifier() {
        path           = new StringBuilder(80);
        visited        = new HashSet<>();
        metadataValues = new TreeMap<>();
        expectedValues = new LinkedHashMap<>();
        mismatches     = new ArrayList<>();
        missings       = new ArrayList<>();
        ignore         = new HashMap<>();
    }

    /**
     * Resets this verifier to the same state as after construction.
     * This method can be invoked for reusing the same verifier with different metadata objects.
     */
    public void clear() {
        path.setLength(0);
        visited.clear();
        metadataValues.clear();
        mismatches.clear();
        missings.clear();
        ignore.clear();
    }

    /**
     * Adds a metadata property to ignore. The property is identified by a GeoAPI interface and the
     * {@link UML} identifier of a property in that interface. Properties to ignore must be declared
     * before to invoke {@code addMetadataToVerify(…)}.
     *
     * @param  type      GeoAPI interface containing the property to ignore.
     * @param  property  UML identifier of a property in the given interface.
     */
    public void addPropertyToIgnore(final Class<?> type, final String property) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(property);
        Set<String> properties = ignore.get(type);
        if (properties == null) {
            properties = new HashSet<>();
            ignore.put(type, properties);               // TODO: use Map.compureIfAbsent with JDK8.
        }
        properties.add(property);
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
     * Stores all properties of the given metadata, for later comparison against expected values.
     * If this method is invoked more than once, then the given metadata objects shall not provide values
     * for the same properties (unless the values are equal, or unless {@link #clear()} has been invoked).
     *
     * @param  actual  the metadata read from a dataset, or {@code null} if none.
     * @throws IllegalStateException if the given metadata contains a property already found in a previous
     *         call to this method, and the values found in those two invocations are not equal.
     */
    public void addMetadataToVerify(final Metadata actual) {
        explode(Metadata.class, actual);
    }

    /**
     * Stores all properties of the given CRS, for later comparison against expected values.
     * In this class, a Coordinate Reference System is considered as a kind of metadata.
     * If this method is invoked more than once, then the given CRS objects shall not provide values
     * for the same properties (unless the values are equal, or unless {@link #clear()} has been invoked).
     *
     * @param  actual  the CRS read from a dataset, or {@code null} if none.
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
            path.setLength(0);
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
     * of {@link #path}.
     *
     * @param  type  the GeoAPI interface implemented by the given object, or the standard Java class if not a metadata type.
     * @param  obj   non-null instance of {@code type} to add in the map.
     * @throws InvocationTargetException if an error occurred while invoking client code.
     * @throws IllegalAccessException if the method to invoke is not public (should never happen with interfaces).
     * @throws IllegalStateException if a different metadata value is already presents for the current {@link #path} key.
     */
    private void addPropertyValue(Class<?> type, final Object obj) throws InvocationTargetException, IllegalAccessException {
        if (InternationalString.class.isAssignableFrom(type) ||        // Most common case first.
           ControlledVocabulary.class.isAssignableFrom(type) ||
                    GenericName.class.isAssignableFrom(type) ||
                       !type.isAnnotationPresent(UML.class))
        {
            final String key = path.toString();
            final Object previous = metadataValues.put(key, obj);
            if (previous != null && !previous.equals(obj)) {
                throw new IllegalStateException(String.format("Metadata element \"%s\" is specified twice "
                        + "with two different values:%nValue 1: %s%nValue 2: %s%n", key, previous, obj));
            }
        } else {
            final Element recursivityGuard = new Element(type, obj);
            if (visited.add(recursivityGuard)) {
                final int pathElementPosition = path.length();
                type = specialized(type, obj.getClass());               // Example: Identification may actually be DataIdentification
                for (final Method getter : type.getMethods()) {
                    if (getter.getParameterTypes().length != 0) {       // TODO: use getParameterCount() with JDK8.
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
                        path.append('.');
                    }
                    path.append(spec.identifier());
                    if (values == null) {
                        addPropertyValue(valueType, value);
                    } else {
                        valueType = boundOfParameterizedProperty(getter.getGenericReturnType());
                        final int indexPosition = path.append('[').length();
                        int i = 0;
                        do {
                            path.append(i++).append(']');
                            addPropertyValue(valueType, values.next());
                            path.setLength(indexPosition);
                        } while (values.hasNext());
                    }
                    path.setLength(pathElementPosition);
                }
                if (!visited.remove(recursivityGuard)) {
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
     * Implementation of {@code compareMetadata(…)} public methods. This implementation removes properties
     * from the given map as they are found. After this method completed, the remaining entries in the given
     * map are properties not found in the metadata given to {@code addMetadataToVerify(…)} methods.
     *
     * @param  entries  the metadata properties to compare, in a modifiable map (will be modified).
     * @return {@code true} if all properties match, with no missing property and no unexpected property.
     *
     * @see #compareMetadata()
     */
    private boolean filterProperties(final Set<Map.Entry<String,Object>> entries) {
        final Iterator<Map.Entry<String,Object>> it = entries.iterator();
        while (it.hasNext()) {
            final Map.Entry<String,Object> entry = it.next();
            final String key = entry.getKey();
            final Object actual = metadataValues.remove(key);
            if (actual != null) {
                it.remove();
                final Object expected = entry.getValue();
                if (Objects.equals(expected, actual)) {
                    continue;
                } else if (expected instanceof Number && actual instanceof Number) {
                    if (expected instanceof Float) {
                        if (Float.floatToIntBits((Float) expected) ==
                            Float.floatToIntBits(((Number) actual).floatValue()))
                        {
                            continue;
                        }
                    } else if (expected instanceof Double) {
                        if (Double.doubleToLongBits((Double) expected) ==
                            Double.doubleToLongBits(((Number) actual).doubleValue()))
                        {
                            continue;
                        }
                    }
                } else if (expected instanceof CharSequence) {
                    // The main intent is to convert InternationalString.
                    if (Objects.equals(expected.toString(), actual.toString())) {
                        continue;
                    }
                }
                mismatches.add(new AbstractMap.SimpleEntry<String,Object>(key, new Mismatch(expected, actual)));
            }
        }
        missings.addAll(entries);
        return mismatches.isEmpty() && metadataValues.isEmpty() && entries.isEmpty();
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
     * The {@code expectedValue} argument is the expected values for the properties identified by the path.
     *
     * @param  path           path to the property to verify.
     * @param  expectedValue  the expected values of property identified by the path.
     * @throws IllegalArgumentException if a different value is already declared for the given path.
     */
    public void addExpectedValue(final String path, final Object expectedValue) {
        final Object previous = expectedValues.putIfAbsent(path, expectedValue);
        if (previous != null && !previous.equals(expectedValue)) {
            throw new IllegalArgumentException(String.format("Metadata element \"%s\" is specified twice:"
                    + "%nValue 1: %s%nValue 2: %s%n", path, previous, expectedValue));
        }
    }

    /**
     * Adds the expected metadata values for the given paths.
     * The {@code path} argument identifies a metadata element like the following examples
     * ({@code [0]} is the index of an element in lists or collections):
     *
     * <ul>
     *   <li>{@code "identificationInfo[0].citation.identifier[0].code"}</li>
     *   <li>{@code "spatialRepresentationInfo[0].axisDimensionProperties[0].dimensionSize"}</li>
     * </ul>
     *
     * The {@code value} argument is the expected value for the property identified by the path.
     *
     * @param  path           path of the property to compare.
     * @param  expectedValue  expected value for the property at the given path.
     * @param  others         other ({@code path}, {@code expectedValue}) pairs.
     * @throws ClassCastException if an {@code others} element for a path is not a {@link String} instance.
     * @throws IllegalArgumentException if a path is already associated to a different value.
     */
    public void addExpectedValues(final String path, final Object expectedValue, final Object... others) {
        addExpectedValue(path, expectedValue);
        for (int i=0; i<others.length; i++) {
            final Object key = others[i];
            if (!(key instanceof String)) {
                throw new ClassCastException(String.format("others[%d] shall be a String, but given value class is %s.",
                            i, (key != null) ? key.getClass() : null));
            }
            addExpectedValue((String) key, others[++i]);
        }
    }

    /**
     * Adds the expected metadata values for the given paths.
     * For each entry in the map, the key is a path to a metadata element like the following examples
     * ({@code [0]} is the index of an element in lists or collections):
     *
     * <ul>
     *   <li>{@code "identificationInfo[0].citation.identifier[0].code"}</li>
     *   <li>{@code "spatialRepresentationInfo[0].axisDimensionProperties[0].dimensionSize"}</li>
     * </ul>
     *
     * Values in the map are the expected values for the properties identified by the keys.
     *
     * @param  expected  the expected values of properties identified by the keys.
     * @throws IllegalArgumentException if a path is already associated to a different value.
     */
    public void addExpectedValues(final Map<String,?> expected) {
        for (final Map.Entry<String,?> entry : expected.entrySet()) {
            addExpectedValue(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Compares actual metadata properties against the expected values given in a map.
     * The {@code addMetadataToVerify(…)} and {@code addExpectedValues(…)} methods
     * must be invoked before this method.
     * Comparison result can be viewed after this method call with {@link #toString()}.
     *
     * @return {@code true} if all properties match, with no missing property and no unexpected property.
     */
    public boolean compareMetadata() {
        return filterProperties(expectedValues.entrySet());
    }

    /**
     * Asserts that actual metadata properties are equal to the expected values.
     * The {@code addMetadataToVerify(…)} and {@code addExpectedValues(…)} methods
     * must be invoked before this method.
     * If there is any <em>mismatched</em>, <em>missing</em> or <em>unexpected</em> value,
     * then the assertion fails with an error message listing all differences found.
     */
    public void assertMetadataEquals() {
        if (!compareMetadata()) {
            Assert.fail(toString());
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
                appendTo.append("    \"").append(key).append("\":");
                for (int i = width - key.length(); --i >= 0;) {
                    appendTo.append(' ');
                }
                final Object value = entry.getValue();
                if (value instanceof Mismatch) {
                    ((Mismatch) value).toString(appendTo);
                } else {
                    formatValue(value, appendTo);
                }
                appendTo.append(',').append(lineSeparator);
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
     * @param appendTo  where to write the value.
     */
    private static void formatValue(final Object value, final StringBuilder appendTo) {
        final boolean quote = !isPrimitive(value);
        if (quote) appendTo.append('"');
        appendTo.append(value);
        if (quote) appendTo.append('"');
    }
}
