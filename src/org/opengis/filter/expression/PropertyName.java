package org.opengis.filter.expression;

/**
 * Expression class whose value is computed by retrieving the value
 * of a Feature's property.
 */
public interface PropertyName extends Expression {
    
    /**
     *
     */
    public String getPropertyName();
    
    /**
     *
     */
    public void setPropertyName(String newValue);
}
