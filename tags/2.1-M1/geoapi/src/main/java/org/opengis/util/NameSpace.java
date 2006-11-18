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
import java.util.Set;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A collection of 0 or more {@linkplain GenericName generic names}.
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="NameSpace", specification=ISO_19103)
public interface NameSpace {
    /**
     * Indicates whether this namespace is a "top level" namespace.  Global, or top-level
     * namespaces are not contained within another namespace.  There is no namespace called
     * "global" or "root" which contains all of the top-level namespaces.  Hence, this flag
     * indicates whether the namespace has a parent.
     */
    @UML(identifier="global", obligation=MANDATORY, specification=ISO_19103)
    boolean isGlobal();

    /**
     * Represents the identifier of this namespace. If the {@linkplain #isGlobal global} attribute
     * is {@code true}, indicating that this is a top level {@code NameSpace}, then the name should
     * be a {@linkplain LocalName local name}. If {@code false}, name should be a fully-qualified
     * {@linkplain ScopedName scoped name} where
     * <code>{@linkplain ScopedName#head() head()}.{@linkplain LocalName#scope
     * scope()}.{@linkplain #isGlobal} == true</code>.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19103)
    GenericName name();

    /**
     * Returns the set of {@linkplain GenericName generic names} registered with this namespace.
     * Duplicate names are forbidden. The names may be either:
     * <p>
     * <ul>
     *   <li>A {@link LocalName}.</li>
     *   <li>A {@link ScopedName} with the following constraints:
     *     <ul>
     *       <li>All elements of the {@linkplain ScopedName#getParsedNames parsed names list} except
     *           for the {@linkplain ScopedName#tail tail} must refer to a {@code NameSpace}.</li>
     *       <li>Each element of the {@linkplain ScopedName#getParsedNames parsed names list} except
     *           for the {@linkplain ScopedName#head head} must be defined in the {@code NameSpace}
     *           referred to by the previous element.</li>
     *     </ul></li>
     * </ul>
     *
     * @todo This method will put a significant burden on implementations (they will need to manage
     *       a list of names, probably through weak references, etc.). Is the ISO 19103 association
     *       really naviguable that way?
     */
    @UML(identifier="names", obligation=MANDATORY, specification=ISO_19103)
    Set<GenericName> getNames();
}
