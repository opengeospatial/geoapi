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

import java.util.List;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface for {@linkplain ScopedName scoped} and {@linkplain LocalName local name}
 * structure for type and attribute name in the context of name spaces. All generic names:
 * <p>
 * <ul>
 *   <li>have the ability to provide a "{@linkplain #getParsedNames parsed}" version of
 *       themselves;</li>
 *   <li>carry an indication of their "{@linkplain #depth depth}" (e.g., the number of
 *       hierarchical levels specified by the name);</li>
 *   <li>carry an association with a specific {@linkplain NameSpace name space}, or {@linkplain
 *       #scope scope}, in which they are considered "local".</li>
 * </ul>
 * <p>
 * The same name object may not be registered in different {@linkplain NameSpace name space}.
 * If the same character string is registered in two name spaces, two different name objects
 * will result.
 * <p>
 * The {@linkplain Comparable natural ordering} for generic names is implementation dependent.
 * A recommended practice is to {@linkplain String#compareTo compare lexicographically} each
 * element in the {@linkplain #getParsedNames list of parsed names}. Specific attributes of
 * the name, such as how it treats case, may affect the ordering. In general, two names of
 * different classes may not be compared.
 *
 * @author Martin Desruisseaux (IRD)
 * @author Bryce Nordgren (USDA)
 * @since GeoAPI 1.0
 *
 * @see javax.naming.Name
 */
@UML(identifier="GenericName", specification=ISO_19103)
public interface GenericName extends Comparable {
    /**
     * Returns the scope (name space) in which this name is local. The scope is set on creation
     * and is not modifiable. The scope of a name determines where a name "starts". For instance,
     * if a name has a {@linkplain #depth depth} of two (for example {@code "util.GenericName"})
     * and is associated with a {@linkplain NameSpace name space} having the name
     * {@code "org.opengis"}, then the fully qualified name would be
     * {@code "org.opengis.util.GenericName"}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19103)
    NameSpace scope();

    /**
     * Returns the scope (name space) of this generic name. If this name has no scope
     * (e.g. is the root), then this method returns {@code null}.
     *
     * @deprecated Replaced by {@link #scope}.
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19103)
    GenericName getScope();

    /**
     * Returns the depth of this name within the namespace hierarchy.  This indicates the number
     * of levels specified by this name.  For any {@link LocalName}, it is always one.  For a
     * {@link ScopedName} it is some number greater than or equal to 2.
     * <p>
     * The depth is the length of the list returned by the {@link #getParsedNames} method.
     * As such it is a derived parameter.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="depth", obligation=MANDATORY, specification=ISO_19103)
    int depth();

    /**
     * Returns the sequence of {@linkplain LocalName local names} making this generic name. All
     * elements of the list except for the last one refer to {@linkplain NameSpace name spaces}.
     * The length of this sequence is the {@linkplain #depth depth}.
     *
     * @todo What means the "All elements of the list except for the last one refer to name
     *       spaces"? Does it means that the last element refer to the global name space?
     */
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    List<LocalName> getParsedNames();

    /**
     * Returns a view of this object as a local name. This is always the last element in the
     * sequence of {@linkplain #getParsedNames parsed names}.
     *
     * @deprecated Renamed as {@link #name()}.
     */
    @Extension
    LocalName asLocalName();

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames parsed names}.
     * For any {@link LocalName}, this is always {@code this}.
     *
     * @see LocalName#name
     * @see ScopedName#name
     *
     * @since GeoAPI 2.1
     */
    @Extension
    LocalName name();

    /**
     * Returns a view of this name as a fully-qualified name, or {@code null} if none.
     * The {@linkplain #scope scope} of a fully qualified name must be
     * {@linkplain NameSpace#isGlobal global}.
     * <p>
     * If this name is a {@linkplain LocalName local name} and the {@linkplain #scope scope}
     * is already {@linkplain NameSpace#isGlobal global}, returns {@code null} since it is not
     * possible to derive a scoped name.
     *
     * @deprecated Replaced by {@link #toFullyQualifiedName}.
     */
    @Extension
    ScopedName asScopedName();

    /**
     * Returns a view of this name as a fully-qualified name. The {@linkplain #scope scope}
     * of a fully qualified name must be {@linkplain NameSpace#isGlobal global}. This method
     * never returns {@code null}.
     *
     * @since GeoAPI 2.1
     */
    @Extension
    GenericName toFullyQualifiedName();

    /**
     * Returns this name expanded with the specified scope. One may represent this operation
     * as a concatenation of the specified {@code name} with {@code this}. In pseudo-code,
     * the following relationships must hold (the last one is specific to {@link ScopedName}):
     * <p>
     * <ul>
     *   <li><code>push(<var>name</var>).getParsedList() ==
     *       <var>name</var>.getParsedList().addAll({@linkplain #getParsedNames()})</code></li>
     *   <li><code>push(<var>name</var>).scope() == <var>name</var>.{@linkplain #scope()}</code></li>
     *   <li><code>push({@linkplain ScopedName#head head()}).{@linkplain ScopedName#tail tail()} == this</code></li>
     * </ul>
     * <p>
     * <strong>Note:</strong> Those conditions can be understood in terms of the Java
     * {@link Object#equals equals} method instead of the Java identity comparator {@code ==}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="push", obligation=MANDATORY, specification=ISO_19103)
    ScopedName push(GenericName name);

    /**
     * Returns a string representation of this generic name. This string representation
     * is local-independant. It contains all elements listed by {@link #getParsedNames}
     * separated by an arbitrary character (usually {@code :} or {@code /}).
     */
    @Extension
    String toString();

    /**
     * Returns a local-dependent string representation of this generic name. This string
     * is similar to the one returned by {@link #toString} except that each element has
     * been localized in the {@linkplain InternationalString#toString(java.util.Locale)
     * specified locale}. If no international string is available, then this method should
     * returns an implementation mapping to {@link #toString} for all locales.
     */
    @Extension
    InternationalString toInternationalString();
}
