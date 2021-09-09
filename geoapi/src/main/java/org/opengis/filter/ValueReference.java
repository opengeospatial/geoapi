/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.List;
import java.util.Collections;
import org.opengis.util.ScopedName;
import org.opengis.feature.Feature;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Expression whose value is computed by retrieving the value indicated by a name in a resource.
 * {@code ValueReference} can used to specify the name of any property of an object whose value
 * shall be tested by a predicate in a filter expression.
 * The most common application of this is to retrieve a {@linkplain Feature feature}'s property
 * using a property name or an XPath expression.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) used as inputs.
 * @param  <V>  the type of values fetched by the expression.
 *
 * @since 3.1
 */
@UML(identifier="ValueReference", specification=ISO_19143)
public interface ValueReference<R,V> extends Expression<R,V> {
    /**
     * Returns the name of the value reference function.
     * The default implementation returns {@code "fes:ValueReference"}.
     *
     * @return {@code "ValueReference"} in a scope at implementation choice.
     */
    @Override
    default ScopedName getFunctionName() {
        return Name.VALUE_REFERENCE;
    }

    /**
     * Returns an empty list since value references have no parameters.
     *
     * @return an empty list.
     */
    @Override
    default List<Expression<? super R, ?>> getParameters() {
        return Collections.emptyList();
    }

    /**
     * Returns the path to the property whose value will be returned by the {@code apply(R)} method.
     * If all characters are {@linkplain Character#isUnicodeIdentifierPart(int) Unicode identifier part},
     * then the XPath expression is simply a property name. Otherwise
     * (i.e. if the returned path contains characters such as {@code `:`}, {@code `/`} or {@code '['}),
     * the returned string uses a simplified form of the XPath query language described below.
     *
     * <h4>XPath syntax</h4>
     * The path is relative to the resource element (e.g. the {@link Feature}), except in the case of
     * a join operation, in which case the path is relative to the parent of the resource element.
     * The following set of XPath rules shall be supported:
     *
     * <ul class="verbose">
     *   <li>Abbreviated form of the child axis specifier (i.e. the default form with no specifier).</li>
     *   <li>Abbreviated form of the attribute axis specifier (i.e. the {@code '@'} character).</li>
     *   <li>Predicate with a positive non-zero integer to indicate which child of the context node should be selected.</li>
     *   <li>Predicate of the form {@code "child=value"} to identify a specific object property
     *       by constraining the child elements of the property.</li>
     * </ul>
     *
     * <div class="note"><b>Example:</b>
     * {@code addresses[street="Oxfordstrasse"]/number}
     * </div>
     *
     * Implementations may support a larger set of XPath rules if desired.
     *
     * @return path to the property whose value will be returned by the {@code apply(R)} method.
     *
     * @see Feature#getPropertyValue(String)
     */
    String getXPath();
}
