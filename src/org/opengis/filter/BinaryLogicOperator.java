package org.opengis.filter;

/**
 * "Abstract" super-interface for logical operators that accept two other
 * logical values as inputs.  Currently, the only two subclasses are And and Or.
 */
public interface BinaryLogicOperator extends Filter {
    /**
     * Returns the first (left) operand of this logical operator.
     */
    public Filter getFilter1();

    /**
     * Returns the second (right) operand of this logical operator.
     */
    public Filter getFilter2();
}
