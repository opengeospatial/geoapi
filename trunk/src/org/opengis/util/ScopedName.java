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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A <code>ScopedName</code> contains a {@link LocalName} as {@linkplain #getLocaleName head}
 * and a {@linkplain GenericName}, which may be a {@link LocalName} or a
 * <code>GenericName</code>, as {@linkplain #getScope tail}.
 *
 * @author ISO 19103
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit We omitted the <code>NameSpace</code> datatype. Concequently, the
 *          <code>tail</code> association become similar to the <code>scope</code>
 *          relation (if my understanding is right).
 */
///@UML (identifier="ScopedName")
public interface ScopedName extends GenericName {
    /**
     * Returns the local version of this name. This is the last element in the
     * sequence of {@linkplain #getParsedNames parsed names}. However, the local name
     * returned by this method will still have {@linkplain LocalName#getScope the same
     * scope} than this scoped name, even if the string returned by
     * {@link LocalName#toString} will not contains the scope.
     *
     * @rename Renamed from <code>head</code> as <code>localName</code> in order to make
     *         it more obvious that this method returns a name without its scope. This is
     *         also consistent with the omission of <code>tail</code>, which is replaced
     *         by the inherited <code>getScope()</code> method.
     */
/// @UML (identifier="head", obligation=MANDATORY)
    LocalName getLocaleName();

    /**
     * Returns the scope of this name.
     */
/// @UML (identifier="tail", obligation=OPTIONAL)
    GenericName getScope();

    /**
     * Returns a locale-independent string representation of this name, including its scope.
     */
/// @UML (identifier="scopedName", obligation=MANDATORY)
    String toString();
}
