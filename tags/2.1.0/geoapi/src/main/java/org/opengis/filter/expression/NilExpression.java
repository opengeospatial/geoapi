package org.opengis.filter.expression;

import java.io.Serializable;

/**
 * Placeholder class used to represent a NIL expression, evaultates to <code>null</code>.
 * <p>
 * This placeholder class is allows data structures to avoid
 * the use of <code>null</code>. Please note that Expression.NIL is
 * not considered a Literal with value <code>null</code> (since the literal may have its
 * value changed).
 * </p>
 * @author Jody Garnett, Refractions Research, Inc.
 */
public final class NilExpression implements Expression, Serializable {
    private static final long serialVersionUID = 4999313240542653655L;

    /** Not extensible */
    NilExpression(){}

    public Object accept(ExpressionVisitor visitor, Object extraData) {
        return visitor.visit( this, extraData);
    }

    /** Returns <code>null</code> */
    public Object evaluate(Object object) {
        return null;
    }

    /** Returns <code>null</code> */
    public <T> T evaluate(Object object, Class<T> context) {
        return null;
    }

    /**
     * Equals is defined only against <code>Expression.NIL</code>.
     * @param other
     * @return <code>true</code> for Expression.NIL
     */
    public boolean equals(Object other) {
        return other == Expression.NIL;
    }
    /**
     * Returns <code>0</code> in order to agree with hashcode of <code>null</code>.
     */
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Expression.NIL";
    }
}
