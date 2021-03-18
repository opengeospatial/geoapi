/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

/**
 * Supported arithmetic operators in a filter capabilities document.
 *
 * <pre>
 * &lt;xsd:complexType name="ArithmeticOperatorsType"&lt;
 *     &lt;xsd:choice maxOccurs="unbounded"&lt;
 *        &lt;xsd:element ref="ogc:SimpleArithmetic"/&lt;
 *        &lt;xsd:element name="Functions" type="ogc:FunctionsType"/&lt;
 *     &lt;/xsd:choice&lt;
 *  &lt;/xsd:complexType&lt;
 * </pre>
 *
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface ArithmeticOperators {

    /**
     * Indicates if simple arithmetic is provided.
     *
     * <pre>
     * &lt;xsd:element ref="ogc:SimpleArithmetic"/&lt;
     * </pre>
     */
    boolean hasSimpleArithmetic();

    /**
     * Provided functions.
     *
     * <pre>
     * &lt;xsd:element name="Functions" type="ogc:FunctionsType"/&lt;
     * </pre>
     */
    Functions getFunctions();
}
