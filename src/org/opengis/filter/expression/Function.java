package org.opengis.filter.expression;

/**
 *
 */
public interface Function extends Expression {
    
    /**
     *
     */
    public String getName();
    
    /**
     *
     */
    public void setName(String functionName);

    /**
     *
     */
    public Expression [] getParameters();
    
    /**
     *
     */
    public void setParameters(Expression [] exprs);
}
