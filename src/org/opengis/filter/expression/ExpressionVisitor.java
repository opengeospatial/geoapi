/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter.expression;


/**
 * Visitor with {@code visit} methods to be called by {@link Expression#accept Expression.accept(...)}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface ExpressionVisitor {
    Object visit(Add          expression, Object extraData);
    Object visit(Divide       expression, Object extraData);
    Object visit(Function     expression, Object extraData);
    Object visit(Literal      expression, Object extraData);
    Object visit(Multiply     expression, Object extraData);
    Object visit(PropertyName expression, Object extraData);
    Object visit(Subtract     expression, Object extraData);
}
