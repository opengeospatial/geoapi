/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2013 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.feature.type;

import java.util.Set;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A set of 0 or more names, with no duplicates.
 * <p>
 * A namespace contains {@link Name} objects. Each name usually corresponds to
 * the name of a type. The namespace uri of each name ({@link Namespace#getURI()}
 * is the same as the uri of the Namespace object containing it ({@link #getURI()}.
 * </p>
 * <pre>
 *  //create namespace for gml
 *  Namespace namespace = new NamespaceImpl( "http://www.opengis.net/gml" );
 *
 *  //add some names
 *  namespace.add( new NameImpl( "http://www.opengis.net/gml", "PointType" ) );
 *  namespace.add( new NameImpl( "http://www.opengis.net/gml", "LineStringType" ) );
 *  namespace.add( new NameImpl( "http://www.opengis.net/gml", "PolygonType" ) );
 *  namespace.add( new NameImpl( "http://www.opengis.net/gml", "AbstractFeatureType" );
 * </pre>
 * </p>
 * <p>
 * <h3>ISO 19103</h3>
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
 * @author Justin Deoliveira, The Open Planning Project
 *
 * @since GeoAPI 2.1
 */
@UML(identifier="NameSpace", specification=ISO_19103)
public interface Namespace extends Set<Name> {

    /**
     * The namespace uri of this namespace.
     * <p>
     * This value can never be <code>null</code>.
     * </p>
     */
    String getURI();

    /**
     * Looks up a name in the namespace.
     * <p>
     * Since all Name objects in the namespace share the same uri as the
     * namespace itself, only the local part of the name is specified.
     * </p>
     * <p>
     * This method returns <code>null</code> if no such name exists.
     * </p>
     * @param name The local part of the name to look up.
     *
     * @return The name, or <code>null</code>.
     */
    Name lookup( String name );
}
