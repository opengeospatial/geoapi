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
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Fully qualified identifier for an object.
 * A <code>ScopedName</code> contains a {@link LocalName} as {@linkplain #asLocalName head}
 * and a {@linkplain GenericName}, which may be a {@link LocalName} or an other
 * <code>ScopedName</code>, as {@linkplain #getScope tail}.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.1
 *
 * @see NameFactory#createScopedName
 */
@UML(identifier="ScopedName", specification=ISO_19103)
public interface ScopedName extends GenericName {
    /**
     * Returns the scope of this name.
     */
    @UML(identifier="tail", obligation=OPTIONAL, specification=ISO_19103)
    GenericName getScope();

    /**
     * Returns a view of this object as a local name. This is the last element in the
     * sequence of {@linkplain #getParsedNames parsed names}. The local name returned
     * by this method will still have the same {@linkplain LocalName#getScope scope}
     * than this scoped name. Note however that the string returned by
     * {@link LocalName#toString} will differs.
     *
     * @rename Renamed from <code>head</code> as <code>localName</code> in order to make
     *         it more obvious that this method returns a name without its scope. This is
     *         also consistent with the omission of <code>tail</code>, which is replaced
     *         by the inherited <code>getScope()</code> method.
     */
    @UML(identifier="head", obligation=MANDATORY, specification=ISO_19103)
    LocalName asLocalName();

    /**
     * Returns a locale-independent string representation of this name, including its scope.
     */
    @UML(identifier="scopedName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
