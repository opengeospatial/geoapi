/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;


/**
 * Type of modeling element as declared in the OGC/ISO UML diagram.
 * Values of this enumeration are associated to GeoAPI interfaces by the {@link Classifier}
 * annotation.
 * <p>
 * This enumeration does not include the <cite>code list</cite> and <cite>enumeration</cite>
 * stereotypes, because instances of those types are identified by the
 * {@link org.opengis.util.CodeList} and {@link Enum} base classes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://en.wikipedia.org/wiki/Stereotype_%28UML%29">Stereotype on Wikipedia</a>
 */
public enum Stereotype {
    /**
     * Class that specifies a domain of objects together with the operations applicable to the
     * objects, without defining the physical implementation of those objects.
     * <p>
     * This is the default value for interfaces without {@link Classifier} annotation.
     */
    TYPE,

    /**
     * Encapsulation of data, as opposed to taxonomic or behavioural descriptions.
     * Data types may not have an identity of their own and are usually aggregated
     * into some sort of container such as being an attribute in another class.
     */
    DATATYPE,

    /**
     * Root class for a structural polymorphism. Abstract types are not directly instantiable.
     */
    ABSTRACT,

    /**
     * Type consisting of one and only one of several alternatives (listed as member attributes).
     */
    UNION
}
