package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Allows a value to either be included as a literal or computed from a
 * subexpression that may gather information from feature properties.
 */
public interface ParameterValue {
    public Expression getExpression();
    public void setExpression(Expression expr);
    
    public Object getLiteral();
    public void setLiteral(Object value);
}
