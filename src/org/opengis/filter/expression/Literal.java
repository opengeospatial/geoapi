package org.opengis.filter.expression;

/**
 * Instances of this interface provide a constant, literal value that can be
 * used in expressions.  The <code>evaluate</code> method of this class must
 * return the same value as <code>getValue()</code>.
 */
public interface Literal extends Expression {
    /**
     * Returns the constant value held by this object.
     */
    public Object getValue();
}
