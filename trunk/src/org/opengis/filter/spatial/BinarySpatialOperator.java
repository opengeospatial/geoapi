package org.opengis.filter.spatial;

import org.opengis.filter.expression.Expression;

/**
 * Abstract superclass for filter operators that perform some sort of spatial
 * comparison on two geometric objects.
 */
public interface BinarySpatialOperator extends SpatialOperator {
    /**
     * Returns an expression that will be evaluated to determine the first
     * operand to the spatial predicate represented by this operator.  The
     * result of evaluating this expression must be a geometry object.
     */
    public Expression getExpression1();

    /**
     * Sets the expression that will be evaluated to determine the first
     * operand to the spatial predicate represented by this operator.  The
     * result of evaluating this expression must be a geometry object.
     */
    public void setExpression1(Expression expr);

    /**
     * Returns an expression that will be evaluated to determine the second
     * operand to the spatial predicate represented by this operator.  The
     * result of evaluating this expression must be a geometry object.
     */
    public Expression getExpression2();

    /**
     * Sets the expression that will be evaluated to determine the second
     * operand to the spatial predicate represented by this operator.  The
     * result of evaluating this expression must be a geometry object.
     */
    public void setExpression2(Expression expr);
}
