/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

// J2SE direct dependencies
import java.util.Locale;
import java.util.Map;


/**
 * Factory for {@linkplain GenericName generic names} and
 * {@linkplain InternationalString international strings}.
 * 
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface NameFactory {    
    /**
     * Creates an international string from a set of strings in different locales.
     *
     * @param strings String value for each locale key.
     */
    InternationalString createInternationalString(Map<Locale,String> strings);
    
    /**
     * Creates a local name from a scope and a name. This method creates a name similar to the one
     * created by <code>{@linkplain #createScopedName createScopedName}(</code><cite>&hellip;same
     * parameters&hellip;</cite>{@code )}, except that methods like
     * {@link LocalName#getParsedNames() getParsedNames()} and {@link LocalName#toString() toString()}
     * do not include the scope.
     *
     * @param scope The scope to be returned by {@link LocalName#getScope}, or {@code null} if none.
     * @param name  The unlocalized name.
     * @param localizedName A localized version of the name.
     */
    LocalName createLocalName(GenericName scope, String name, InternationalString localizedName);
    
    /**
     * Creates a scoped name from a scope and a name. This method creates a name similar to the one
     * created by <code>{@linkplain #createLocalName createLocalName}(</code><cite>&hellip;same
     * parameters&hellip;</cite>{@code )}, except that methods like
     * {@link ScopedName#getParsedNames() getParsedNames()} and {@link ScopedName#toString() toString()}
     * include the scope.
     *
     * @param scope The scope to be returned by {@link ScopedName#getScope}.
     * @param name  The unlocalized name.
     * @param localizedName A localized version of the name.
     */
    ScopedName createScopedName(GenericName scope, String name, InternationalString localizedName);
}
