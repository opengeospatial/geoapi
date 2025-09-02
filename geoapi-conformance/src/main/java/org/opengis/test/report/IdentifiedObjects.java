/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.report;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.LinkedHashMap;
import org.opengis.util.NameSpace;
import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.IdentifiedObject;


/**
 * Static utility methods for the handling of {@link IdentifiedObject} instances.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @since 3.1
 */
final class IdentifiedObjects {
    /**
     * The default separator between code spaces (or scopes) and codes.
     */
    static final String SEPARATOR = ":";

    /**
     * Do not allow instantiation of this class.
     */
    private IdentifiedObjects() {
    }

    /**
     * Compares the given identifier for order. This method performs
     * a comparison of identifier components in the following order:
     * {@linkplain Identifier#getCode() code},
     * {@linkplain Identifier#getCodeSpace() code space} and
     * {@linkplain Identifier#getVersion() version}.
     *
     * @param  s1  the first operation method to compare, or {@code null}.
     * @param  s2  the second operation method to compare, or {@code null}.
     * @return -1 if {@code o1} should appears before {@code o2}, -1 for the converse,
     *         or 0 if this method cannot determine an ordering for the given object.
     */
    public static int compare(final Identifier s1, final Identifier s2) {
        if (s1 == s2)   return  0;
        if (s1 == null) return +1;
        if (s2 == null) return -1;
        int c = compare(s1.getCode(), s2.getCode());
        if (c == 0) {
            c = compare(s1.getCodeSpace(), s2.getCodeSpace());
            if (c == 0) {
                c = compare(s1.getVersion(), s2.getVersion());
            }
        }
        return c;
    }

    /**
     * Compares the given string for ordering. First, this method performs a case-insensitive
     * comparison. If the given string are equals, then this method performs a case-sensitive
     * comparison (since we still want a determinist order). {@code null} values (if any) are
     * ordered last.
     *
     * @param  s1  the first string to compare, or {@code null}.
     * @param  s2  the second string to compare, or {@code null}.
     * @return -1 if {@code s1} should appears before {@code s2}, +1 for the converse,
     *         or 0 if the two strings are equal.
     */
    @SuppressWarnings("StringEquality")
    static int compare(String s1, String s2) {
        if (s1 == s2)   return  0;  // Identity comparison ok here, since this is only an optimization for a common case.
        if (s1 == null) return +1;
        if (s2 == null) return -1;
        /*
         * The ASCII value of the underscore character is greater than 'Z' but lower than 'a',
         * which sometimes produce unexpected sort results. For example, "Foo_bar" is sorted
         * between "FooBar" and "Foobar". The space character produces more consistent sort
         * results because its ASCII value is less than any printable character.
         */
        s1 = s1.replace('_', ' ').trim();
        s2 = s2.replace('_', ' ').trim();

        // Empty strings before non-empty ones.
        if (s1.isEmpty()) return s2.isEmpty() ? 0 : -1;
        if (s2.isEmpty()) return +1;

        // Any numbers before any alphabetic strings.
        if (isNumeric(s1)) return isNumeric(s2) ? Integer.parseInt(s1) - Integer.parseInt(s2) : -1;
        if (isNumeric(s2)) return +1;

        int c = String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
        if (c == 0) {
            c = s1.compareTo(s2);
        }
        return c;
    }

    /**
     * Compares the elements in the given arrays for order. Elements are compared as numerical
     * values if possible, otherwise as strings using a case-insensitive comparator.
     *
     * @param  s1  the first array of strings to compare, or {@code null}.
     * @param  s2  the second array of strings to compare, or {@code null}.
     * @return negative if {@code s1} is before {@code s2}, positive if after, 0 if equal.
     */
    static int compare(final String[] s1, final String[] s2) {
        final int length = Math.min(s1.length, s2.length);
        for (int i=0; i<length; i++) {
            final int c = compare(s1[i], s2[i]);
            if (c != 0) {
                return c;
            }
        }
        return s1.length - s2.length;
    }

    /**
     * Returns {@code true} if the given string seems to be a number.
     * The string must be non-empty (it must be verified by the caller).
     *
     * @param  s  the string to test.
     * @return whether the given string seems a number.
     */
    private static boolean isNumeric(final String s) {
        for (int i=s.length(); --i>=0;) {
            final char c = s.charAt(i);
            if (c < '0' || c > '9') {
                if (i != 0 || (c != '+' && c != '-')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Replaces {@code null} value by an empty set. While implementations should always
     * return an empty collection instead of null, we nevertheless stay lenient since
     * the report generators are not validators; we just want to show what we have.
     *
     * @param  <E>  type of elements in the collection.
     * @param  c    the potentially null collection.
     * @return the non-null collection.
     */
    static <E> Collection<E> nullSafe(final Collection<E> c) {
        return (c != null) ? c : Set.<E>of();
    }

    /**
     * Returns the object {@linkplain IdentifiedObject#getName() name} and all its
     * {@linkplain IdentifiedObject#getAlias() aliases} for the given
     * {@linkplain Identifier#getCodeSpace() code space}.
     *
     * <p>The values in the returned map are {@link Boolean#TRUE} for the primary name
     * (at most one entry) and {@link Boolean#FALSE} for aliases (all other entries).</p>
     *
     * @param  info       the object to get the name and aliases from, or {@code null}.
     * @param  codeSpace  the code space for the name and aliases to return, or {@code null} for all code spaces.
     * @return all name and aliases for the given code space.
     */
    public static Map<String,Boolean> getNameAndAliases(final IdentifiedObject info, final String codeSpace) {
        final Map<String,Boolean> names = new LinkedHashMap<>(4);
        getNameComponents(info, codeSpace, false, names);
        return names;
    }

    /**
     * Collects the code space of the given object {@linkplain IdentifiedObject#getName() name}
     * and the scope of all its {@linkplain IdentifiedObject#getAlias() aliases}.
     *
     * <p>The values in the given map will be {@link Boolean#TRUE} for the code space of the primary name
     * (at most one entry) and {@link Boolean#FALSE} for the scope of aliases (all other entries).</p>
     *
     * @param  info   the object to get the code space and scopes from, or {@code null}.
     * @param  addTo  a map where to add the code space and scopes.
     */
    public static void getCodeSpaces(final IdentifiedObject info, Map<String, ? super Boolean> addTo) {
        getNameComponents(info, null, true, addTo);
    }

    /**
     * Implementation of {@link #getNameAndAliases(IdentifiedObject, String)}
     * and {@link #getCodeSpaces(IdentifiedObject, String)}.
     *
     * @param  info            the object to get the name and aliases from, or {@code null}.
     * @param  codeSpace       the code space for the name and aliases to return, or {@code null} for all code spaces.
     * @param  wantCodeSpaces  whether to include code spaces in the given map.
     * @param  names           a map where to add the code spaces and scopes.
     */
    private static void getNameComponents(final IdentifiedObject info, final String codeSpace,
            final boolean wantCodeSpaces, final Map<String, ? super Boolean> names)
    {
        final Identifier identifier = info.getName();
        if (identifier != null) {                                           // Mandatory attribute, but be lenient.
            if (codeSpace == null || compare(codeSpace, identifier.getCodeSpace()) == 0) {
                names.put(wantCodeSpaces ? identifier.getCodeSpace() : identifier.getCode(), Boolean.TRUE);
            }
        }
        for (GenericName alias : nullSafe(info.getAlias())) {
            if (alias == null) continue;
            final GenericName tip = alias.tip();
            if (tip != null) {          // Should never be null, but protect ourself against broken implementations.
                alias = tip;
            }
            final NameSpace ns = alias.scope();  if (ns    == null) continue;
            final GenericName scope = ns.name(); if (scope == null) continue;
            if (codeSpace == null || compare(codeSpace, scope.toString()) == 0) {
                final String key = wantCodeSpaces ? scope.toString() : alias.toString();
                if (Boolean.TRUE.equals(names.put(key, Boolean.FALSE))) {
                    names.put(key, Boolean.TRUE);       // Value TRUE has precedence.
                }
            }
        }
        names.remove(null);
    }

    /**
     * Returns a string representation of the given identifier in the form
     * {@code codespace:code:version}. The {@code codespace} and {@code version}
     * may be omitted, but the {@code code} is mandatory: if absent this method
     * will write "{@code null}".
     *
     * @param  id  the identifier, or {@code null}.
     * @return the string representation of the given identifier, or {@code null} if the given identifier was null.
     */
    public static String toString(final Identifier id) {
        if (id == null) {
            return null;
        }
        final String code      = id.getCode();
        final String codeSpace = id.getCodeSpace();
        final String version   = id.getVersion();
        if (codeSpace == null && version == null) {
            return code;
        }
        final StringBuilder buffer = new StringBuilder();
        if (codeSpace != null) {
            buffer.append(codeSpace).append(':');
        }
        buffer.append(code);
        if (version != null) {
            buffer.append(':').append(version);
        }
        return buffer.toString();
    }
}
