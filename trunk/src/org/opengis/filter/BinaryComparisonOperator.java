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


/**
 * Abstract base class for filters that compare exactly two values against each
 * other.  The nature of the comparison is dependent on the subclass.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface BinaryComparisonOperator extends Filter {
    /**
     * Returns the first of the two expressions to be compared by this operator.
     */
    Expression getExpression1();

    /**
     * Returns the second of the two expressions to be compared by this operator.
     */
    Expression getExpression2();
}
