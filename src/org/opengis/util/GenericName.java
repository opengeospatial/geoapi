/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.util;

// J2SE direct dependencies
import java.util.List;


/**
 * Base interface for {@linkplain ScopedName generic scoped} and
 * {@linkplain LocalName local name} structure for type and attribute
 * name in the context of name spaces.
 *
 * @UML abstract GenericName
 * @author ISO 19103
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GenericName {
    /**
     * Returns the scope (name space) of this generic name. If this name has no scope
     * (e.g. is the root), then this method returns <code>null</code>.
     *
     * @UML optional scope
     */
    GenericName getScope();

    /**
     * Returns the sequence of {@linkplain LocalName local names} making this generic name.
     * Each element in this list is like a directory name in a file path name.
     * The length of this sequence is the generic name depth.
     *
     * @return The sequence of {@linkplain LocalName local names} (never <code>null</code>).
     * @UML mandatory parsedName
     */
    List/*<LocalName>*/ getParsedNames();

    /**
     * Returns a string representation of this generic name. This string representation
     * is local-independant. It contains all elements listed by {@link #getParsedNames}
     * separated by an arbitrary character (usually <code>:</code> or <code>/</code>).
     * This rule implies that the <code>toString()</code> method for a
     * {@linkplain ScopedName scoped name} will contains the scope, while the
     * <code>toString()</code> method for the {@linkplain LocalName local version} of
     * the same name will not contains the scope.
     *
     * @return The locale-independent string for the full name. Never <code>null</code>.
     */
    String toString();

    /**
     * Returns a local-dependent string representation of this generic name. This string
     * is similar to the one returned by {@link #toString} except that each element has
     * been localized in the {@linkplain InternationalString#toString(java.util.Locale)
     * specified locale}.
     *
     * @return The locale-dependent string for the full name. Never <code>null</code>:
     *         if no international string is available, then this method should returns
     *         an implementation mapping to {@link #toString} for all locales.
     */
    InternationalString toInternationalString();
}
