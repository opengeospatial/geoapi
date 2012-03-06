/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2012 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import org.opengis.util.NameSpace;
import org.opengis.util.GenericName;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;


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
     * Do not allow instantiation of this class.
     */
    private IdentifiedObjects() {
    }

    /**
     * Compares the given identifier for order. This method performs
     * a comparison of identifier components in the following order:
     * {@linkplain ReferenceIdentifier#getCode() code},
     * {@linkplain ReferenceIdentifier#getCodeSpace() code space} and
     * {@linkplain ReferenceIdentifier#getVersion() version}.
     *
     * @param o1 The first operation method to compare, or {@code null}.
     * @param o2 The second operation method to compare, or {@code null}.
     * @return -1 if {@code o1} should appears before {@code o2}, -1 for the converse,
     *         or 0 if this method can not determine an ordering for the given object.
     */
    public static int compare(final ReferenceIdentifier n1, final ReferenceIdentifier n2) {
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
     * @param  s1 The first string to compare, or {@code null}.
     * @param  s2 The second string to compare, or {@code null}.
     * @return -1 if {@code s1} should appears before {@code s2}, +1 for the converse,
     *         or 0 if the two strings are equal.
     */
    private static int compare(final String s1, final String s2) {
        if (s1 == s2)   return  0;
        if (s1 == null) return +1;
        if (s2 == null) return -1;
        int c = String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
        if (c == 0) {
            c = s1.compareTo(s2);
        }
        return c;
    }

    /**
     * Replaces {@code null} value by an empty set. While implementations should always
     * return an empty collection instead than null, we nevertheless stay lenient since
     * the report generators are not validators; we just want to show what we have.
     */
    static <E> Collection<E> nullSafe(final Collection<E> c) {
        return (c != null) ? c : Collections.<E>emptySet();
    }

    /**
     * Returns the object {@linkplain IdentifiedObject#getName() name} and all its
     * {@linkplain IdentifiedObject#getAlias() aliases} for the given
     * {@linkplain ReferenceIdentifier#getCodeSpace() code space}.
     *
     * @param  info The object to get the name and aliases from, or {@code null}.
     * @param  codeSpace The code space for the name and aliases to return, or {@code null}
     *         for all code spaces.
     * @return All name and aliases for the given code space.
     */
    public static Set<String> getNameAndAliases(final IdentifiedObject info, final String codeSpace) {
        final Set<String> names = new LinkedHashSet<String>(4);
        getNameComponents(info, codeSpace, false, names);
        return names;
    }

    /**
     * Returns the code space of the given object {@linkplain IdentifiedObject#getName() name}
     * and the scope of all its {@linkplain IdentifiedObject#getAlias() aliases}.
     *
     * @param  info The object to get the code space and scopes from, or {@code null}.
     * @param  addTo An optional set where to add the code space and scopes, or {@code null}.
     * @return The set given in argument, or a new set if the {@code addTo} argument was null.
     *         This set will contain every code space and scopes found in the given object.
     */
    public static Set<String> getCodeSpaces(final IdentifiedObject info, Set<String> addTo) {
        if (addTo == null) {
            addTo = new LinkedHashSet<String>(8);
        }
        getNameComponents(info, null, true, addTo);
        return addTo;
    }

    /**
     * Implementation of {@link #getNameAndAliases(IdentifiedObject, String)}
     * and {@link #getCodeSpaces(IdentifiedObject, String)}.
     */
    private static void getNameComponents(final IdentifiedObject info, final String codeSpace,
            final boolean wantCodeSpaces, final Set<String> names)
    {
        final ReferenceIdentifier identifier = info.getName();
        if (identifier != null) { // Mandatory attribute, but be lenient.
            if (codeSpace == null || compare(codeSpace, identifier.getCodeSpace()) == 0) {
                names.add(wantCodeSpaces ? identifier.getCodeSpace() : identifier.getCode());
            }
        }
        for (GenericName alias : nullSafe(info.getAlias())) {
            if (alias == null) continue;
            alias = alias.tip();
            final NameSpace ns = alias.scope();  if (ns    == null) continue;
            final GenericName scope = ns.name(); if (scope == null) continue;
            if (codeSpace == null || compare(codeSpace, scope.toString()) == 0) {
                names.add(wantCodeSpaces ? scope.toString() : alias.toString());
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
     * @param  id The identifier, or {@code null}.
     * @return The string representation of the given identifier, or {@code null}
     *         if the given identifier was null.
     */
    public static String toString(final ReferenceIdentifier id) {
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
