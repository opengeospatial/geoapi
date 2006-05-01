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

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Fully qualified identifier for an object.
 * A {@code ScopedName} contains a {@link LocalName} as head
 * and a {@linkplain GenericName}, which may be a {@link LocalName} or an other
 * {@code ScopedName}, as tail.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see NameFactory#createScopedName
 */
@UML(identifier="ScopedName", specification=ISO_19103)
public interface ScopedName extends GenericName {
    /**
     * Returns the scope of this name.
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19103)
    GenericName getScope();

    /**
     * Returns a view of this object as a local name. This is the last element in the
     * sequence of {@linkplain #getParsedNames parsed names}. The local name returned
     * by this method will still have the same {@linkplain LocalName#getScope scope}
     * than this scoped name. Note however that the string returned by
     * {@link LocalName#toString} will differs.
     */
    @Extension
    LocalName asLocalName();

    /**
     * Returns a locale-independent string representation of this name, including its scope.
     */
    @UML(identifier="scopedName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
