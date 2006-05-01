/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter.expression;

// Annotation
import org.opengis.annotation.Extension;


/**
 * Visitor with {@code visit} methods to be called by {@link Expression#accept Expression.accept(...)}.
 * <p>
 * Please note that a generic visit( Expression ) entry point has not been provided, although Expression
 * forms a heirarchy for your convience it is not an open heirarchy. If you need to extend this system
 * please make use of {code Function}, this will allow extention while remaining standards complient.
 * </p>
 * <p>
 * It is very common for a single instnace to implement both ExpressionVisitor and FilterVisitor.
 * </p>
 * 
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@Extension
public interface ExpressionVisitor {
	// Object visit(Expression   expression, Object extraData); // bad idea
    Object visit(Add          expression, Object extraData);
    Object visit(Divide       expression, Object extraData);
    Object visit(Function     expression, Object extraData);
    Object visit(Literal      expression, Object extraData);
    Object visit(Multiply     expression, Object extraData);
    Object visit(PropertyName expression, Object extraData);
    Object visit(Subtract     expression, Object extraData);
}
