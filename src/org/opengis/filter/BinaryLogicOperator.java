package org.opengis.filter;

import java.util.List;

/**
 * "Abstract" super-interface for logical operators that accept two or more
 * other logical values as inputs.  Currently, the only two subclasses are And
 * and Or.
 */
public interface BinaryLogicOperator extends Filter {
    /**
     * Returns a list containing all of the child filters of this object.  This
     * list will contain at least two elements, and each element will be an
     * instance of <code>Filter</code>.  Implementations of this interface are
     * encouraged to return either a copy of their internal list or an
     * immutable wrapper around their internal list.  This is because this
     * spec requires <code>Filter</code> objects to be immutable.
     */
    public List/*<Filter>*/ getChildren();
}
