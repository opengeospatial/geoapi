package org.opengis.filter;

/**
 * "Abstract" super-interface for logical operators that accept two or more
 * other logical values as inputs.  Currently, the only two subclasses are And
 * and Or.
 */
public interface BinaryLogicOperator extends Filter {
    public int getChildCount();

    public Filter getChild(int i) throws IndexOutOfBoundsException;
}
