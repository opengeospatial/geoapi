package org.opengis.filter.expression;

/**
 * Instances of this class represent a function call into some implementation-
 * specific function.  This is included for completeness with respect to the
 * OGC Filter specification.  However, no functions are required to be supported
 * by that specification.
 */
public interface Function extends Expression {
    /**
     * Returns the name of the function to be called.  For example, this might
     * be "cos" or "atan2".
     */
    public String getName();

    /**
     * Returns the subexpressions that will be evaluated to provide the
     * parameters to the function.
     */
    public Expression [] getParameters();
}
