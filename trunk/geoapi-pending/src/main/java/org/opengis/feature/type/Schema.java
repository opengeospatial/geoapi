/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2011 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.Set;

/**
 * A collection of AttributeType.
 * <p>
 * A schema is organized as a map of {@link Name} to {@link AttributeType}. In
 * each name,type tuple, the name matches the name of the type.
 * <pre>
 *   //create some attribute types
 *   AttributeType pointType =
 *     new AttributeTypeImpl( new NameImpl( "http://www.opengis.net/gml", "PointType" ), ... );
 *   AttributeType lineStringType =
 *     new AttributeTypeImpl( new NameImpl( "http://www.opengis.net/gml", "LineStringType" ), ... );
 *   AttributeType polygonType =
 *     new AttributeTypeImpl( new NameImpl( "http://www.opengis.net/gml", "PolygonType" ), ... );
 *
 *   //create a schema
 *   Schema schema = new SchemaImpl( "http://www.opengis.net/gml" );
 *
 *   //add types to the schema
 *   schema.add( pointType );
 *   schema.add( lineStringType );
 *   schema.add( polygonType );
 * </pre>
 * </p>
 * <p>
 * The intention of a schema is to provide a resuable set of attribute types.
 * These types are used when building attribute instances.
 * </p>
 *
 * @author Jody Garnett, Refractions Research, Inc.
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface Schema extends Map<Name, AttributeType> {

    /**
     * The uri of the schema.
     * <p>
     * This method is a convenience for <code>keySet().getURI()</code>.
     * </p>
     *
     * @return The uri of the schema.
     */
    String getURI();

    /**
     * Adds a type to the schema.
     * <p>
     * This method is a convenience for <code>put(type.getName(),type)</code>.
     * </p>
     * @param type The type to add.
     */
    void add( AttributeType type );

    /**
     * Profiles the schema, creating a new schema in the process.
     * <p>
     * A profile of a schema is a subset of the schema, and it also a schema
     * itself.
     * </p>
     * <p>
     * Used to select a subset of types for a specific application. Profiles
     * often are used to express limitiations of a source of data.
     * </p>
     * @param profile The set of names which corresond to entries that will make
     * up the profile.
     *
     * @return The profile of the original schema.
     */
    Schema profile( Set<Name> profile );
}
