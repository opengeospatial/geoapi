package org.opengis.filter;

import org.opengis.filter.expression.Expression;

public interface PropertyIsBetween extends ComparisonOperator {

    public Expression getLowerBoundary();
    public void setLowerBoundary(Expression expr);

    public Expression getUpperBoundary();
    public void setUpperBoundary(Expression expr);
}
