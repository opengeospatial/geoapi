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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A constant value that can be used in expressions.
 * The {@link #apply(R)} method of this class must return the same value as {@link #getValue()}.
 *
 * <div class="note"><b>Note:</b>
 * the content of {@link #getValue()} may be persisted with XML based technologies.
 * As an example a {@link org.opengis.geometry.Geometry} may be written out using GML.
 * Consequently the value should be data objects such as strings, numbers, dates or geometries.
 * It should not be state of operations.</div>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @param  <R>  the type of resources used as inputs. This is ignored by literals.
 * @param  <V>  the type of the value given by the literal.
 *
 * @since 3.1
 */
@UML(identifier="Literal", specification=ISO_19143)
public interface Literal<R,V> extends Expression<R,V> {
    /**
     * Returns the name of the literal function.
     * The default implementation returns {@code "fes:Literal"}.
     *
     * @return {@code "Literal"} in a scope at implementation choice.
     */
    @Override
    default ScopedName getFunctionName() {
        return Name.LITERAL;
    }

    /**
     * Returns an empty list since literals have no parameters.
     *
     * @return an empty list.
     */
    @Override
    default List<Expression<? super R, ?>> getParameters() {
        return Collections.emptyList();
    }

    /**
     * Returns the constant value held by this object.
     * It should be a data object such as a string, number, date or geometry.
     *
     * @return the literal value. May be {@code null}.
     */
    @UML(identifier="value", specification=ISO_19143)       // Conceptually mandatory even if we allow null.
    V getValue();

    /**
     * Evaluates the expression value.
     * Shall be the same value as {@link #getValue()}.
     *
     * @param  input  ignored (may be {@code null}).
     * @return the literal value.
     */
    @Override
    default V apply(R input) {
        return getValue();
    }

    /*
     * NOTE: it would be possible to implement a default `toValueType(Class)` method if we were sure that this
     * literal has no method expecting a <V> argument (e.g. setter method). This `Literal` interface  does not
     * define such method, but implementation could. If such methods exist, then the <R,N> parameterized types
     * would be incorrect and would need to be <R, ? extends N>. Only implementer knows if it is safe to cast,
     * so we do not provide default implementation for that reason.
     */
}
