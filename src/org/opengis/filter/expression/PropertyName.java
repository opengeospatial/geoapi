package org.opengis.filter.expression;

/**
 * Expression class whose value is computed by retrieving the value
 * of a Feature's property.
 */
public interface PropertyName extends Expression {
    /**
     * Returns the name of the property whose value will be returned by the
     * <code>evaluate</code> method.
     */
    public String getPropertyName();
}
