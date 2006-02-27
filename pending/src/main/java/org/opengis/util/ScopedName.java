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

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Fully qualified identifier for an object.
 * A {@code ScopedName} contains a {@linkplain LocalName local name} as {@linkplain #asLocalName head}
 * and a {@linkplain GenericName generic name}, which may be a {@linkplain LocalName local name} or an
 * other {@code ScopedName}, as {@linkplain #scope tail}.
 *
 * @author Martin Desruisseaux (IRD)
 * @author Bryce Nordgren (USDA)
 * @since GeoAPI 2.0
 *
 * @see NameFactory#createScopedName
 */
@UML(identifier="ScopedName", specification=ISO_19103)
public interface ScopedName extends GenericName {
    /**
     * Returns the scope of this name.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19103)
    NameSpace scope();

    /**
     * Returns the scope of this name.
     *
     * @deprecated Replaced by {@link #scope}.
     */
    @UML(identifier="tail", obligation=OPTIONAL, specification=ISO_19103)
    GenericName getScope();

    /**
     * Returns the tail of this scoped name. This is the first elements in the sequence of
     * {@linkplain #getParsedNames parsed names}, minus the {@linkplain #head head}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="tail", obligation=OPTIONAL, specification=ISO_19103)
    GenericName tail();

    /**
     * Returns the head of this scoped name. This is the last element in the sequence of
     * {@linkplain #getParsedNames parsed names}. The local name returned by this method
     * will still have the same {@linkplain LocalName#scope scope} than this scoped name.
     * Note however that the string returned by {@link LocalName#toString} will differs.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="head", obligation=MANDATORY, specification=ISO_19103)
    LocalName head();

    /**
     * Returns a view of this object as a local name. For a scoped name, this method is a
     * synonymous for {@link #head}.
     */
    @Extension
    LocalName asLocalName();

    /**
     * Returns a locale-independent string representation of this name, including its scope.
     */
    @UML(identifier="scopedName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
