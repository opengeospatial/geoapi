/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
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
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@Extension
public interface ExpressionVisitor {
    Object visit(Add          expression, Object extraData);
    Object visit(Divide       expression, Object extraData);
    Object visit(Function     expression, Object extraData);
    Object visit(Literal      expression, Object extraData);
    Object visit(Multiply     expression, Object extraData);
    Object visit(PropertyName expression, Object extraData);
    Object visit(Subtract     expression, Object extraData);
}
