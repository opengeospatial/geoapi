/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
 * of the {@code addMetadataToVerify(…)} methods. After the actual values have been specified,
 * they can be compared against the expected value by a call to {@code assertMetadataEquals(…)}.
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
     * more than one interface with the same class. For example the same {@code value} instance could implement
     * both {@code Metadata} and {@code DataIdentification} interfaces.</p>
     */
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
     * Paths of properties that were expected but not found.
     */
    private final List<Map.Entry<String,Object>> missings;

    /**
     * Metadata values that do not match the expected values. We use a {@code List} instead than a {@code Map}
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

        /** Creates a new entry for a mismatched value. */
        Mismatch(final Object expected, final Object actual) {
            this.expected = expected;
            this.actual   = actual;
        }

        /** Returns a string representation for debugging purpose. */
        @Override public String toString() {
            return toString(new StringBuilder()).toString();
        }

        /** Formats the string representation in the given buffer. */
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
        mismatches     = new ArrayList<>();
        missings       = new ArrayList<>();
        ignore         = new HashMap<>();
    }

    /**
     * Resets this verifier to the same state than after construction.
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
        ignore.computeIfAbsent(type, (k) -> new HashSet<>()).add(property);
    }

    /**
     * Returns {@code true} if the given property shall be ignored.
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
     * Returns the sub-interfaces implemented by the given implementation class. For example is a property type
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
                        values = ((Collection<?>) value).iterator();
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
        throw new IllegalArgumentException("Can not find the parameterized type of " + type);
    }

    /**
     * Returns {@code true} if the given value should be considered as a "primitive" for formatting purpose.
     * Primitive are null, numbers or booleans, but we extend this definition to enumerations and code lists.
     */
    private static boolean isPrimitive(final Object value) {
        return (value == null) || (value instanceof ControlledVocabulary)
                || (value instanceof Number) || (value instanceof Boolean);
    }

    /**
     * Implementation of {@code compareMetadata(…)} public methods. This implementation removes properties
     * from the given map as they are found. After this method completed, the remaining entries in the given
     * map are properties not found in the metadata given to {@code addMetadataToVerify(…)} methods.
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
                mismatches.add(new AbstractMap.SimpleEntry<>(key, new Mismatch(expected, actual)));
            }
        }
        missings.addAll(entries);
        return mismatches.isEmpty() && metadataValues.isEmpty() && entries.isEmpty();
    }

    /**
     * Compares actual metadata properties against the expected values given in a map.
     * For each entry in the map, the key is a path to a metadata element like the following examples
     * ({@code [0]} is the index of an element in lists or collections):
     *
     * <ul>
     *   <li>{@code "identificationInfo[0].citation.identifier[0].code"}</li>
     *   <li>{@code "spatialRepresentationInfo[0].axisDimensionProperties[0].dimensionSize"}</li>
     * </ul>
     *
     * Values in the map are the expected values for the properties identified by the keys.
     * Comparison result can be viewed after this method call with {@link #toString()}.
     *
     * @param  expected  the expected values of properties identified by the keys.
     * @return {@code true} if all properties match, with no missing property and no unexpected property.
     */
    public boolean compareMetadata(final Map<String,Object> expected) {
        return filterProperties(new LinkedHashMap<>(expected).entrySet());
    }

    /**
     * Compares actual metadata properties against the expected values.
     * The {@code path} argument identifies a metadata element like the following examples
     * ({@code [0]} is the index of an element in lists or collections):
     *
     * <ul>
     *   <li>{@code "identificationInfo[0].citation.identifier[0].code"}</li>
     *   <li>{@code "spatialRepresentationInfo[0].axisDimensionProperties[0].dimensionSize"}</li>
     * </ul>
     *
     * The {@code value} argument is the expected value for the property identified by the path.
     * Comparison result can be viewed after this method call with {@link #toString()}.
     *
     * @param  path           path of the property to compare.
     * @param  expectedValue  expected value for the property at the given path.
     * @param  others         other ({@code path}, {@code expectedValue}) pairs.
     * @return {@code true} if all properties match, with no missing property and no unexpected property.
     */
    public boolean compareMetadata(final String path, final Object expectedValue, final Object... others) {
        final int length = others.length;
        final Map<String,Object> m = new LinkedHashMap<>((length - length/2) / 2);
        m.put(path, expectedValue);
        for (int i=0; i<length; i++) {
            final Object key = others[i];
            if (!(key instanceof String)) {
                throw new ClassCastException(String.format("others[%d] shall be a String, but given value class is %s.",
                            i, (key != null) ? key.getClass() : null));
            }
            final Object value = others[++i];
            final Object previous = m.put((String) key, value);
            if (previous != null) {
                throw new IllegalStateException(String.format("Metadata element \"%s\" is specified twice:"
                        + "%nValue 1: %s%nValue 2: %s%n", key, previous, value));
            }
        }
        return filterProperties(m.entrySet());
    }

    /**
     * Asserts that actual metadata properties are equal to the expected values.
     * The {@code path} argument identifies a metadata element like the following examples
     * ({@code [0]} is the index of an element in lists or collections):
     *
     * <ul>
     *   <li>{@code "identificationInfo[0].citation.identifier[0].code"}</li>
     *   <li>{@code "spatialRepresentationInfo[0].axisDimensionProperties[0].dimensionSize"}</li>
     * </ul>
     *
     * The {@code value} argument is the expected value for the property identified by the path.
     * If there is any <em>mismatched</em>, <em>missing</em> or <em>unexpected</em> value, then
     * the assertion fails with an error message listing all differences found.
     *
     * @param  path           path of the property to compare.
     * @param  expectedValue  expected value for the property at the given path.
     * @param  others         other ({@code path}, {@code expectedValue}) pairs, in any order.
     */
    public void assertMetadataEquals(final String path, final Object expectedValue, final Object... others) {
        if (!compareMetadata(path, expectedValue, others)) {
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
     */
    private static void formatValue(final Object value, final StringBuilder appendTo) {
        final boolean quote = !isPrimitive(value);
        if (quote) appendTo.append('"');
        appendTo.append(value);
        if (quote) appendTo.append('"');
    }
}
