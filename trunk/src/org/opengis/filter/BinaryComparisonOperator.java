package org.opengis.filter;

import org.opengis.filter.expression.Expression;

/**
 * Abstract base class for filters that compare exactly two values against each
 * other.  The nature of the comparison is dependent on the subclass.
 */
public interface BinaryComparisonOperator extends Filter {
    /**
     * Returns the first of the two expressions to be compared by this operator.
     */
    public Expression getExpression1();

    /**
     * Returns the second of the two expressions to be compared by this
     * operator.
     */
    public Expression getExpression2();
}
