package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Implementations of this interface are held by a TextSymbol to indicate that
 * text should be drawn at some distance from a line.
 */
public interface LinePlacement extends TextPlacement {
    /**
     * Returns the expression that is used to compute how far from the lines
     * the text will be drawn.  The distance must evaluate to a non-negative
     * number.
     */
    public Expression getPerpindicularOffset();

    /**
     * Sets the expression that is used to compute how far from the lines
     * the text will be drawn.  The distance must evaluate to a non-negative
     * number.
     */
    public void setPerpindicularOffset(Expression e);
}
