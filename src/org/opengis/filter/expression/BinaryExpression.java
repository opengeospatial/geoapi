package org.opengis.filter.expression;

/**
 * "Abstract" base class for the various filter expressions that compute some
 * value from two input values.
 */
public interface BinaryExpression extends Expression {
    /**
     * Returns the expression that represents the first (left) value that will
     * be used in the computation of another value.
     */
    public Expression getExpression1();

    /**
     * Returns the expression that represents the second (right) value that will
     * be used in the computation of another value.
     */
    public Expression getExpression2();
}
