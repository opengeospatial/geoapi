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
package org.opengis.feature.type;

// J2SE direct dependencies
import java.util.Set;

// Annotations
//import org.opengis.annotation.UML;
//import org.opengis.annotation.Extension;
//import static org.opengis.annotation.Obligation.*;
//import static org.opengis.annotation.Specification.*;
/**
 * A collection of 0 or more {@linkplain Name names}, with no duplicates.
 * <p>
 * If this was not an interface only project we could make use of the
 * javax.naming package As such we recommend storing your
 * Namespace instances in a JNDI according to their URI.
 * </p>
 * <p>
 * The ISO 19103 specification asks that we have:
 * <ul>
 * <li>isGlobal()
 * <li>name() - inidicating the name of this namespace
 * <li>getNames() - set of names
 * </ul>
 * We have combined these concerns by making this a Set of Names,
 * and we remember the URI of this namespace.
 * <p>
 * One allowance ISO_19103 allows for is having a Namespace located inside another
 * namespace. You may certaintly do this by constructing a facility similar to Schema
 * in which namespaces may be looked up via a Name with the same URI as the one
 * used here.
 * <p>
 * We are simply not dictating the lookup mechanism, or a backpointer to
 * a containing namespace (note the two solutions are in conflict and we would like
 * to offer application the freedom to back this interface onto a facility such as
 * JNDI used in their own application).
 * </p>
 * @author Jody Garnett, Refractions Research, Inc.
 * @since GeoAPI 2.1
 */
//@UML(identifier="NameSpace", specification=ISO_19103)
public interface Namespace extends Set<Name> {
    /** Indicates the URI of this namespace. */
    String getURI();

    /** Allows lookup via just the localPart of the name */
    Name lookup(String name);
}
