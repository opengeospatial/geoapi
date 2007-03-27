/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;

// J2SE direct dependencies
import java.util.Locale;
import java.util.Map;

// Annotations
import org.opengis.annotation.Extension;


/**
 * Factory for {@linkplain GenericName generic names} and
 * {@linkplain InternationalString international strings}.
 * 
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
@Extension
public interface NameFactory {    
    /**
     * Creates an international string from a set of strings in different locales.
     *
     * @param strings String value for each locale key.
     */
    InternationalString createInternationalString(Map<Locale,String> strings);

    /**
     * Creates a local name from a {@linkplain LocalName#scope scope} and a
     * {@linkplain LocalName#toString name}. The scope argument identifies the
     * {@linkplain NameSpace name space} in which the local name will be created.
     * The {@code name} argument is taken verbatism as the string representation
     * of the local name.
     *
     * @param scope The scope, or {@code null} for the global one.
     * @param name  The unlocalized name.
     * @param localizedName A localized version of the name, or {@code null} if none.
     */
    LocalName createLocalName(GenericName scope, String name, InternationalString localizedName);

    /**
     * Creates a scoped name from a {@linkplain ScopedName#scope scope} and a
     * {@linkplain ScopedName#toString name}. The scope argument identifies the
     * {@linkplain NameSpace name space} in which the local name will be created.
     * The {@code name} argument will be parsed in order to construct the list of
     * {@linkplain ScopedName#getParsedNames parsed names}.
     *
     * @param scope The scope, or {@code null} for the global one.
     * @param name  The unlocalized name.
     * @param localizedName A localized version of the name, or {@code null} if none.
     */
    ScopedName createScopedName(GenericName scope, String name, InternationalString localizedName);
}
