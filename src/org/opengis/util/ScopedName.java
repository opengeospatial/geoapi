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


/**
 * A <code>ScopedName</code> contains a {@link LocalName} as {@linkplain #getLocaleName head}
 * and a {@linkplain GenericName}, which may be a {@link LocalName} or a
 * <code>GenericName</code>, as {@linkplain #getScope tail}.
 *
 * @UML abstract ScopedName
 * @author ISO 19103
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit We omitted the <code>NameSpace</code> datatype. Concequently, the
 *          <code>tail</code> association become similar to the <code>scope</code>
 *          relation (if my understanding is right).
 */
public interface ScopedName extends GenericName {
    /**
     * Returns the local version of this name. This is the last element in the
     * sequence of {@linkplain #getParsedNames parsed names}. However, the local name
     * returned by this method will still have {@linkplain LocalName#getScope the same
     * scope} than this scoped name, even if the string returned by
     * {@link LocalName#toString} will not contains the scope.
     *
     * @return The locale version of this name (never <code>null</code>).
     * @UML mandatory head
     * @rename Renamed from <code>head</code> as <code>localName</code> in order to make
     *         it more obvious that this method returns a name without its scope. This is
     *         also consistent with the omission of <code>tail</code>, which is replaced
     *         by the inherited <code>getScope()</code> method.
     */
    LocalName getLocaleName();

    /**
     * Returns the scope of this name.
     *
     * @UML optional tail
     */
    GenericName getScope();

    /**
     * Returns a string representation of this name, including its scope.
     *
     * @return The locale-independent string for the this name. Never <code>null</code>.
     * @UML mandatory scopedName
     */
    String toString();
}
