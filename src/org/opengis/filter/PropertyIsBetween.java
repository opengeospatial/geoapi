package org.opengis.filter;

import org.opengis.filter.expression.Expression;

public interface PropertyIsBetween extends Filter {
    public Expression getExpression();
    public Expression getLowerBoundary();
    public Expression getUpperBoundary();
}
