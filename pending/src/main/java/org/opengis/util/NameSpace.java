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
import java.util.Set;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A collection of 0 or more {@linkplain GenericName generic names}.
 * The namespace contains utilities to look up any of the names it contains and return the
 * object associated with that name.  It contains utilities to "add" and "remove" names from
 * the namespace.  It also contains the ability to make up a name for an object when adding
 * it to the namespace.
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
     * be a {@link LocalName}. If {@code false}, name should be a fully-qualified {@link ScopedName}
     * where {@code tail().scope.global == true}.
     *
     * @todo (MD) I'm not sure that {@code tail().scope.global} should be {@code true}. I would
     *       expect something like {@code tail.tail.tail...} until there is no more parent.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19103)
    GenericName name();

    /**
     * Returns the set of {@linkplain LocalName local names} registered with this namespace.
     * Duplicate names are forbidden.
     */
    @UML(identifier="names", obligation=MANDATORY, specification=ISO_19103)
    Set<LocalName> getLocalNames();
}
