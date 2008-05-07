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

import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A composite of a {@linkplain LocalName local name} (as {@linkplain #head head}) for locating
 * another {@linkplain NameSpace name space}, and a {@linkplain GenericName generic name} (as
 * {@linkplain #tail tail}) valid in that name space. This definition allows for iteration. The
 * {@linkplain #tail tail} may be either a {@linkplain LocalName local name} or a scoped name.
 * If it is a scoped name, then another another step towards a remote {@linkplain LocalName local
 * name} is taken. In this way, a scoped name may represent an arbitrarily distant
 * {@linkplain LocalName local name} simply by the number of times the {@link #tail()} method
 * evaluates to a {@code ScopedName} before finally terminating on a {@link LocalName}.
 * <p>
 * It may be seen that {@code ScopedName} is the means by which fully-qualified names are expressed.
 * However, a {@code ScopedName} is not, in itself, what is commonly thought of as a <cite>fully
 * qualified</cite> name. The {@code ScopedName} type is one link in the chain, not the entire chain.
 * A scoped name is a fully qualified name only if its {@linkplain #scope scope} is
 * {@linkplain NameSpace#isGlobal global}.
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
     * Returns the head of this scoped name. This is the first elements in the sequence of
     * {@linkplain #getParsedNames parsed names}. The head element must exists in the same
     * {@linkplain NameSpace name space} than this scoped name. In other words, the following
     * relationship must holds:
     * <p>
     * <ul>
     *   <li><code>head().scope() == this.{@linkplain #scope scope()}</code></li>
     * </ul>
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="head", obligation=MANDATORY, specification=ISO_19103)
    LocalName head();

    /**
     * Returns the tail of this scoped name. The returned name contains every elements of the
     * {@linkplain #getParsedNames parsed names list} except for the first one, which is the
     * {@linkplain #head head}. In other words, the following relationship must holds:
     * <p>
     * <ul>
     *   <li><code>tail().getParsedNames() == this.{@linkplain #getParsedNames getParsedNames()}.sublist(1,end)</code></li>
     * </ul>
     * <p>
     * <strong>Note:</strong> This condition can be understood in terms of the Java
     * {@link java.util.List#equals equals} method instead of the Java identity
     * comparator {@code ==}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="tail", obligation=MANDATORY, specification=ISO_19103)
    GenericName tail();

    /**
     * Returns a name which contains every element of the
     * {@linkplain #getParsedNames parsed names list} except for the last element.
     *
     * @see java.io.File#getPath
     *
     * @since GeoAPI 2.1
     */
    @Extension
    GenericName path();

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames parsed names}.
     *
     * @see java.io.File#getName
     *
     * @since GeoAPI 2.1
     */
    @Extension
    LocalName name();

    /**
     * Returns a locale-independent string representation of this scoped name.
     * This method encapsulates the domain logic which formats the {@linkplain #getParsedNames
     * parsed names} into a legal string representation of the name. There will be variants on
     * this theme. XML aficionados may require URIs. For java classes, a dotted notation is more
     * appropriate, for C++, a double-colon, for directories, a forward or reverse slash,
     * and for {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem CRS}, it
     * will depend on the mode of expression: URN or {@code Authority:Identifier} notation.
     */
    @UML(identifier="scopedName", obligation=MANDATORY, specification=ISO_19103)
    ///@Override    
    String toString();
}
