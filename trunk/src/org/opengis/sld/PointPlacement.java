package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Implementations of this interface are used in the TextSymbol class to
 * indicate that text should be drawn starting at a particular point and
 * extending in a straight line.
 */
public interface PointPlacement {
    /**
     * Returns an expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     * This expression must evaluate to a floating point number between zero
     * and one with zero indicating the far left of the bounding box and one
     * indicating the far right of the bounding box.  If the expression is
     * null, the default value is zero.
     */
    public Expression getAnchorX();

    /**
     * Sets the expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     * This expression must evaluate to a floating point number between zero
     * and one with zero indicating the far left of the bounding box and one
     * indicating the far right of the bounding box.  If the expression is
     * null, the default value is zero.
     */
    public void setAnchorX(Expression e);

    /**
     * Returns an expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     * This expression must evaluate to a floating point number between zero
     * and one with zero indicating the bottom of the bounding box and one
     * indicating the top of the bounding box.  If the expression is null, the
     * default value is zero.
     */
    public Expression getAnchorY();

    /**
     * Returns an expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     * This expression must evaluate to a floating point number between zero
     * and one with zero indicating the bottom of the bounding box and one
     * indicating the top of the bounding box.  If the expression is null, the
     * default value is zero.
     */
    public void setAnchorY(Expression e);

    /**
     * Returns an expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets located.
     * If this expression is null, the default offset of zero is used.
     */
    public Expression getDisplacementX();

    /**
     * Sets the expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets located.
     * If this expression is null, the default offset of zero is used.
     */
    public void setDisplacementX(Expression e);

    /**
     * Returns an expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets located.
     * If this expression is null, the default offset of zero is used.
     */
    public Expression getDisplacementY();

    /**
     * Sets the expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets located.
     * If this expression is null, the default offset of zero is used.
     */
    public void setDisplacementY(Expression e);

    /**
     * Returns the expression that will be evaluated to determine the rotation
     * of the text about its anchor point.  The rotation must evaluate to a
     * number that is interpreted as rotation clockwise in decimal degrees from
     * the normal orientation of the text.  If the expression is null, then the
     * default value of zero is used.
     */
    public Expression getRotation();

    /**
     * Sets the expression that will be evaluated to determine the rotation
     * of the text about its anchor point.  The rotation must evaluate to a
     * number that is interpreted as rotation clockwise in decimal degrees from
     * the normal orientation of the text.  If the expression is null, then the
     * default value of zero is used.
     */
    public void setRotation(Expression e);
}
