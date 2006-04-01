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
package org.opengis.feature.type;

// J2SE direct dependencies
import java.util.Set;

// Annotations
//import org.opengis.annotation.UML;
//import org.opengis.annotation.Extension;
//import static org.opengis.annotation.Obligation.*;
//import static org.opengis.annotation.Specification.*;


/**
 * A collection of 0 or more {@linkplain Name  names), with no duplicates.
 * <p>
 * The following is handled from ISO 19103:
 * <ul>
 * <li>A Global name will return null for Namespace and NamespaceURI
 * <ul>
 * </p>
 * <p>
 * The following concessions are made to usability in the Java environment:
 * <ul>
 * <li>method compatiability with QName
 * <ul>
 * If this was not an interface only project we could make use of the javax.naming package
 * </p>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 * @since GeoAPI 2.1
 */
//@UML(identifier="NameSpace", specification=ISO_19103)
public interface Namespace extends Set<Name> {
	
    /**
     * Indicates whether this namespace is a "top level" namespace.
     * 
     * @return <code>true</code> if getName() is not contained in another namespace
     */
    //@UML(identifier="global", obligation=MANDATORY, specification=ISO_19103)
    boolean isGlobal();

    /**
     * Represents the identifier of this namespace.
     */
    //@UML(identifier="name", obligation=MANDATORY, specification=ISO_19103)
    Name getName();

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
    //@UML(identifier="names", obligation=MANDATORY, specification=ISO_19103)
    Set<Name> getNames();
}