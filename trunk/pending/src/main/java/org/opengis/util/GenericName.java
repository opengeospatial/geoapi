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
import java.util.List;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface for {@linkplain ScopedName generic scoped} and
 * {@linkplain LocalName local name} structure for type and attribute
 * name in the context of name spaces. All generic names:
 * <p>
 * <ul>
 *   <li>have the ability to provide a "{@linkplain #getParsedNames parsed}" version of
 *       themselves;</li>
 *   <li>carry an indication of their "{@linkplain #depth depth}" (e.g., the number of
 *       hierarchical levels specified by the name);</li>
 *   <li>carry an association with a specific {@linkplain NameSpace namespace}, or {@linkplain
 *       #scope scope}, in which they are considered "{@linkplain LocalName local}".</li>
 * </ul>
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
     * Returns the scope (name space) of this generic name. The scope is set on creation and is
     * not modifiable.
     * 
     * @return The scope of this generic name. If this name has no scope
     *         (e.g. is the root), then this method returns {@code null}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19103)
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
     * Returns the sequence of {@linkplain LocalName local names} making this generic name.
     * Each element in this list is like a directory name in a file path name.
     * The length of this sequence is the generic name {@linkplain #depth depth}.
     */
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    List<LocalName> getParsedNames();

    /**
     * Returns a view of this object as a scoped name,
     * or {@code null} if this name has no scope.
     */
    @Extension
    ScopedName asScopedName();

    /**
     * Returns a view of this object as a local name. Special cases:
     * <p>
     * <ul>
     *   <li>For a {@linkplain LocalName local name}, returns {@code this}.</li>
     *   <li>For a {@linkplain ScopedName scoped name}, return the {@linkplain ScopedName#head head}.</li>
     * </ul>
     * <p>
     * In all cases, the local name returned by this method will have the same
     * {@linkplain LocalName#scope scope} than this generic name.
     */
    @Extension
    LocalName asLocalName();

    /**
     * Returns a string representation of this generic name. This string representation
     * is local-independant. It contains all elements listed by {@link #getParsedNames}
     * separated by an arbitrary character (usually {@code :} or {@code /}).
     * This rule implies that the {@code toString()} method for a
     * {@linkplain ScopedName scoped name} will contains the scope, while the
     * {@code toString()} method for the {@linkplain LocalName local version} of
     * the same name will not contains the scope.
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
