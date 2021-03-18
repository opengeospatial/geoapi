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
package org.opengis.filter.expression;

import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;


/**
 * Interface for all the OGC Filter elements that compute values.
 * <p>
 * The most common use is with potentially using {@linkplain Feature feature} and
 * metadata.  The ability to access "attributes" based on the provided content is
 * defined based on XPath queries currently.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @author Justin Deoliveira (The Open Planning Project)
 * @since GeoAPI 2.0
 */
@XmlElement("expression")
public interface Expression {
    /**
     * Constant expression that always evaluates to {@code null}.
     * <p>
     * This constant is a "NullObject" that can represent the absence of
     * expression in a data structures. As example it can be used to represent
     * the default stroke color in a LineSymbolizer Stroke structure.
     */
    Expression NIL = new NilExpression();

    /**
     * Evaluates the expression based on the content of the given object.
     */
    Object evaluate(Object object);

    /**
     * Evaluates the given expression based on the content of the given object
     * and the context type.
     *
     * <p>The {@code context} parameter is used to control the type of the
     * result of the expression. A particular expression may not be able to evaluate
     * to an instance of {@code context}. Therefore to be safe calling code
     * should do a null check on the return value of this method, and call {@link #evaluate(Object)}
     * if necessary. Example:</p>
     *
     * <pre>
     *  Object input = ...;
     *  String result = expression.evaluate( input, String.class );
     *  if ( result == null ) {
     *     result = expression.evalute( input ).toString();
     *  }
     *  ...
     * </pre>
     *
     * <p>Implementations that can not return a result as an instance of {@code context}
     * should return {@code null}.</p>
     *
     * @param  <T>      the type of the returned object.
     * @param  object   the object to evaluate the expression against.
     * @param  context  the type of the resulting value of the expression.
     * @return the expression based on the content of the given object an an instance of {@code context}.
     */
    <T> T evaluate( Object object, Class<T> context );

    /**
     * Accepts a visitor. Subclasses must implement with a method whose content is the following:
     * <pre>return visitor.{@linkplain ExpressionVisitor#visit visit}(this, extraData);</pre>
     */
    Object accept(ExpressionVisitor visitor, Object extraData);
}
