package org.opengis.filter.expression;

/**
 *
 */
public interface Literal extends Expression {
    
    /**
     *
     */
    public Object getValue();
    
    /**
     *
     */
    public void setValue(Object obj);
}
