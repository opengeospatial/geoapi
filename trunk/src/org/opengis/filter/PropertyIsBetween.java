/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.XmlSchema;


/**
 * A compact way of encoding a range check. The lower and upper boundary values are inclusive.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema(URL="filter.xsd", element="PropertyIsBetween")
public interface PropertyIsBetween extends Filter {
    /**
     * Returns the expression to be compared by this operator.
     */
    Expression getExpression();

    /**
     * Returns the lower bounds (inclusive) an an expression.
     */
    Expression getLowerBoundary();

    /**
     * Returns the upper bounds (inclusive) as an expression.
     */
    Expression getUpperBoundary();
}
