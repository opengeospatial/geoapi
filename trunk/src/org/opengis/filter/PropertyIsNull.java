package org.opengis.filter;

import org.opengis.filter.expression.Expression;

/**
 * Filter operator that checks if an expression's value is null.
 */
public interface PropertyIsNull extends Filter {
    /**
     * Returns the expression whose value will be checked for null.
     */
    public Expression getExpression();
}
