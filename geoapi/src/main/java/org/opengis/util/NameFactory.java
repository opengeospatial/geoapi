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

import java.util.Map;
import java.util.Locale;
import org.opengis.annotation.Extension;


/**
 * Factory for {@linkplain GenericName generic names} and
 * {@linkplain InternationalString international strings}.
 *
 * @author Jesse Crossley (SYS Technologies)
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.0
 */
@Extension
public interface NameFactory {
    /**
     * Creates an international string from a set of strings in different locales.
     *
     * @param strings String value for each locale key.
     * @return The international string.
     */
    InternationalString createInternationalString(Map<Locale,String> strings);

    /**
     * Creates a namespace having the given name. Despite the "create" name, implementations are
     * free to return an existing instance.
     *
     * @param  name The name of the namespace to be returned. This argument can be created using
     *         <code>{@linkplain #createGenericName createGenericName}(null, parsedNames)</code>.
     * @param  separator The separator to insert between {@linkplain GenericName#getParsedNames
     *         parsed names} in that namespace. For HTTP namespace, it is {@code "/"}. For URN
     *         namespace, it is typically {@code ":"}.
     * @return A namespace having the given name and separator.
     *
     * @since GeoAPI 2.2
     */
    NameSpace createNameSpace(GenericName name, String separator);

    /**
     * Creates a local or scoped name from an array of parsed names. The array elements can be either
     * {@link String} or {@link InternationalString} instances. In the later case, implementations
     * can use an arbitrary {@linkplain Locale locale} (typically {@link Locale#ENGLISH ENGLISH},
     * but not necessarly) for the unlocalized string to be returned by {@link GenericName#toString}.
     * <p>
     * If the length of the {@code parsedNames} array is 1, then this method returns an instance
     * of {@link LocalName}. If the length is 2 or more, then this method returns an instance of
     * {@link ScopedName}.
     *
     * @param  scope The {@linkplain GenericName#scope scope} of the generic name to be created,
     *         or {@code null} for a global namespace.
     * @param  parsedNames The local names as an array of strings or international strings.
     *         This array must contains at least one element.
     * @return The generic name for the given parsed names.
     *
     * @since GeoAPI 2.2
     */
    GenericName createGenericName(NameSpace scope, CharSequence[] parsedNames);

    /**
     * Creates a local name from a {@linkplain LocalName#scope scope} and a
     * {@linkplain LocalName#toString name}. The {@code scope} argument identifies the
     * {@linkplain NameSpace name space} in which the local name will be created.
     * The {@code name} argument is taken verbatism as the string representation
     * of the local name.
     * <p>
     * This method
     *
     * @param scope The scope, or {@code null} for the global one.
     * @param name  The unlocalized name.
     * @param localizedName A localized version of the name, or {@code null} if none.
     * @return The local name.
     *
     * @deprecated Replaced by {@link #createNameSpace createNameSpace} for the scope argument,
     *             and {@link #createGenericName createGenericName} for the name and localized
     *             name arguments.
     */
    @Deprecated
    LocalName createLocalName(GenericName scope, String name, InternationalString localizedName);

    /**
     * Creates a scoped name from a {@linkplain ScopedName#scope scope} and a
     * {@linkplain ScopedName#toString name}. The {@code scope} argument identifies the
     * {@linkplain NameSpace name space} in which the scoped name will be created.
     * The {@code name} argument will be parsed in order to construct the list of
     * {@linkplain ScopedName#getParsedNames parsed names}.
     *
     * @param scope The scope, or {@code null} for the global one.
     * @param name  The unlocalized name.
     * @param localizedName A localized version of the name, or {@code null} if none.
     * @return The scoped name.
     *
     * @deprecated Replaced by {@link #createNameSpace createNameSpace} for the scope argument,
     *             and {@link #createGenericName createGenericName} for the name and localized
     *             name arguments.
     */
    @Deprecated
    ScopedName createScopedName(GenericName scope, String name, InternationalString localizedName);
}
