package org.opengis.filter.expression;

import org.opengis.feature.Feature;

/**
 * Abstract super-interface for all the OGC Filter elements that compute values,
 * potentially using Feature attributes in the computation.
 */
public interface Expression {
    /**
     * Evaluates the given expression based on the content of the given
     * feature.
     */
    public Object evaluate(Feature feature);

    /**
     * Accepts a visitor.  Subclasses must implement with a method whose content
     * is the following:
     * <pre>return visitor.visit(this, extraData);</pre>
     */
    public Object accept(ExpressionVisitor visitor, Object extraData);
}
