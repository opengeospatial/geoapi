package org.opengis.filter;

import org.opengis.filter.expression.Expression;

/**
 * Abstract base class for filters that compare exactly two values against each
 * other.  The nature of the comparison is dependent on the subclass.  This
 * interface has no methods, but is included to line up with the types in the
 * schemas for OGC Filter 1.0.0.
 */
public interface BinaryComparisonOperator extends ComparisonOperator {
    public Expression getExpression1();
    public void setExpression1(Expression expr);

    public Expression getExpression2();
    public void setExpression2(Expression expr);
}
