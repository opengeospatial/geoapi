/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.report;

import java.util.Map;
import java.util.Collection;
import java.util.Collections;
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
     * @param  o1  the first operation method to compare, or {@code null}.
     * @param  o2  the second operation method to compare, or {@code null}.
     * @return -1 if {@code o1} should appears before {@code o2}, -1 for the converse,
     *         or 0 if this method cannot determine an ordering for the given object.
     */
    public static int compare(final Identifier n1, final Identifier n2) {
        if (n1 == n2)   return  0;
        if (n1 == null) return +1;
        if (n2 == null) return -1;
        int c = compare(n1.getCode(), n2.getCode());
        if (c == 0) {
            c = compare(n1.getCodeSpace(), n2.getCodeSpace());
            if (c == 0) {
                c = compare(n1.getVersion(), n2.getVersion());
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
    static int compare(String s1, String s2) {
        if (s1 == s2)   return  0;  // Identity comparison ok here, since this is only an optimization for a common case.
        if (s1 == null) return +1;
        if (s2 == null) return -1;
        /*
         * The ASCII value of the underscore character is greater than 'Z' but lower than 'a',
         * which sometimes produce unexpected sort results. For example "Foo_bar" is sorted
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
     */
    static <E> Collection<E> nullSafe(final Collection<E> c) {
        return (c != null) ? c : Collections.<E>emptySet();
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
