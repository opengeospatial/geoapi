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
import java.util.Collections;  // For javadoc

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifier within a name space for a local object. This could be the target object of the
 * {@link GenericName}, or a pointer to another name space (with a new {@link GenericName})
 * one step closer to the target of the identifier.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see NameFactory#createLocalName
 */
@UML(identifier="LocalName", specification=ISO_19103)
public interface LocalName extends GenericName {
    /**
     * Returns the scope (name space) of this generic name. This method returns the same
     * value than the one returned by the {@linkplain ScopedName scoped} version of this
     * name. In other words, the following relation shall be respected:
     * <blockquote><table border='0'><tr>
     *   <td nowrap>{@link ScopedName#asLocalName}</td>
     *   <td nowrap>{@code .getScope() ==}</td>
     *   <td nowrap align="right">{@link ScopedName}</td>
     *   <td nowrap>{@code .getScope()}</td>
     * </tr><tr>
     *   <td align="center"><font size=2>(a locale name)</font></td>
     *   <td>&nbsp;</td>
     *   <td align="center"><font size=2>(a scoped name)</font></td>
     *   <td>&nbsp;</td>
     * </tr></table></blockquote>
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19103)
    GenericName getScope();

    /**
     * Returns the sequence of local name for this {@linkplain GenericName generic name}.
     * Since this object is itself a locale name, this method always returns a
     * {@linkplain Collections#singleton singleton} containing only {@code this}.
     */
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    List<LocalName> getParsedNames();

    /**
     * Returns a view of this object as a local name. Some implementations may
     * returns {@code this} since this object is already a local name.
     */
    @Extension
    LocalName asLocalName();

    /**
     * Returns a locale-independant string representation of this local name.
     * This string do not includes the scope, which is consistent with the
     * {@linkplain #getParsedNames parsed names} definition.
     */
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
