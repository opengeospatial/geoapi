package org.opengis.filter.expression;

import org.opengis.feature.Feature;

/**
 * Abstract super-interface for all the OGC Filter elements that compute values
 * from Feature attributes.
 */
public interface Expression {
    
    /**
     * Evaluates the given expression based on the content of the given
     * feature.
     */
    public Object evaluate(Feature feature);
}
