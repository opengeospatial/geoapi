package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Implementations of this interface describe the settings of a "halo" effect
 * as it is applied to text.
 */
public interface Halo {
    /**
     * Returns the object that indicates how the halo area around the text
     * should be filled.
     */
    public Fill getFill();

    /**
     * Sets the object that indicates how the halo area around the text should
     * be filled.
     */
    public void setFill(Fill f);

    /**
     * Returns the expression that will be evaluated to get the pixel radius of
     * the halo around the text.
     */
    public Expression getRadius();

    /**
     * Sets the expression that will be evaluates to get the pixel radius of the
     * halo around the text.
     */
    public void setRadius(Expression expression);
}
