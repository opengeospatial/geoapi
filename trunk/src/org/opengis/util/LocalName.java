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
 * Identifier within a name space for a local object. This could be the target object of the
 * {@link GenericName}, or a pointer to another name space (with a new {@link GenericName})
 * one step closer to the target of the identifier.
 *
 * @UML abstract LocalName
 * @author ISO 19103
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface LocalName extends GenericName {
    /**
     * Returns the scope (name space) of this generic name. This method returns the same
     * value than the one returned by the {@linkplain ScopedName scoped} version of this
     * name. In other words, the following relation shall be respected:
     * <blockquote><table border='0'><tr>
     *   <td nowrap>{@link ScopedName#getLocaleName}</td>
     *   <td nowrap><code>.getScope() ==</code></td>
     *   <td nowrap align="right">{@link ScopedName}</td>
     *   <td nowrap><code>.getScope()</code></td>
     * </tr><tr>
     *   <td align="center"><font size=2>(a locale name)</font></td>
     *   <td>&nbsp;</td>
     *   <td align="center"><font size=2>(a scoped name)</font></td>
     *   <td>&nbsp;</td>
     * </tr></table></blockquote>
     *
     * @UML optional scope
     */
    GenericName getScope();

    /**
     * Returns the sequence of local name for this {@linkplain GenericName generic name}.
     * Since this object is itself a locale name, this method simply returns a singleton
     * which contains only <code>this</code>.
     *
     * @return Always a singleton containing <code>this</code>.
     * @UML mandatory parsedName
     */
    List/*<LocalName>*/ getParsedNames();

    /**
     * Returns a string representation of this local name. This string do not includes
     * the scope, which is consistent with the {@linkplain #getParsedNames parsed names}
     * definition.
     *
     * @return The locale-independent string for the this name. Never <code>null</code>.
     * @UML mandatory aName
     */
    String toString();
}
