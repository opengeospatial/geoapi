/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.identity;

/**
 * An object identifier.
 * This class is an abstract base for identifiers. Some known identifiers are:
 * <ul>
 *   <li>FeatureId</li>
 *   <li>GMLObjectId</li>
 *   <li>RecordId</li>
 * </ul>
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Jody Garnett (Refractions Research)
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface Identifier {
    /**
     * Returns the identifier itself.
     */
    Object getID();

    /**
     * Determines if the id of an object matches the value of the identifier.
     *
     * @param  object  the object to perform the test against.
     * @return {@code true} if a match, otherwise {@code false}.
     */
    boolean matches(Object object);

    /**
     * Identifier is a data object, equals is based just on getID()
     *
     * @return true if obj is an Identifier with the same getID()
     */
    @Override
    boolean equals(Object obj);

    /**
     * Identifier is a data object, hashCode is based just on getID()
     *
     * @return hashCode based on getID()
     */
    @Override
    int hashCode();

    /**
     * Returns a string representation of the identifier.
     *
     * @return getID().toString()
     */
    @Override
    String toString();
}
