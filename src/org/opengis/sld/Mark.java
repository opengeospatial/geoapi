package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Implementations of this interface indicate that one of a few predefined
 * shapes will be drawn at the points of the geometry.
 */
public interface Mark {
    /**
     * Returns the expression whose value will indicate the symbol to draw.
     * At least the following values must be accepted: "square", "circle",
     * "triangle", "star", "cross", or "x".  If null, the default is "square".
     */
    public Expression getWellKnownName();

    /**
     * Sets the expression whose value will indicate the symbol to draw.
     * At least the following values must be accepted: "square", "circle",
     * "triangle", "star", "cross", or "x".  If null, the default is "square".
     */
    public void setWellKnownName(Expression name);

    /**
     * Returns the object that indicates how the mark should be filled.
     * Null means no fill.
     */
    public Fill getFill();

    /**
     * Sets the object that indicates how the mark should be filled.
     * Null means no fill.
     */
    public void setFill(Fill f);

    /**
     * Returns the object that indicates how the edges of the mark will be
     * drawn.  Null means that the edges will not be drawn at all.
     */
    public Stroke getStroke();

    /**
     * Sets the object that indicates how the edges of the mark will be
     * drawn.  Null means that the edges will not be drawn at all.
     */
    public void setStroke(Stroke s);
}
