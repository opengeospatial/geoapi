package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Implementations of this interface indicate how text will be drawn.
 */
public interface TextSymbol extends Symbol {
    /**
     * Returns the expression that will be evaluated to determine what text is
     * displayed.
     */
    public Expression getLabel();

    /**
     * Sets the expression that will be evaluated to determine what text is
     * displayed.
     */
    public void setLabel(Expression label);

    /**
     * Indicates the name of the font or font family to use.  Any number of
     * comma-separated values may be provided and they are assumed to be in
     * preferred order.  The list of available font families is system
     * dependent.  If null, a system dependent default will be used.
     */
    public Expression getFontFamily();

    /**
     * Indicates the name of the font or font family to use.  Any number of
     * comma-separated values may be provided and they are assumed to be in
     * preferred order.  The list of available font families is system
     * dependent.  If null, a system dependent default will be used.
     */
    public void setFontFamily(Expression expression);

    /**
     * Expression that indicates the style of the font.  Allowed values are
     * "normal", "italic", and "oblique".  If null, the default is "normal".
     */
    public Expression getFontStyle();

    /**
     * Expression that indicates the style of the font.  Allowed values are
     * "normal", "italic", and "oblique".  If null, the default is "normal".
     */
    public void setFontStyle(Expression expression);

    /**
     * Expression that indicates the weight of the font.  Allowed values are
     * "normal" and "bold".  If null, the default is "normal".
     */
    public Expression getFontWeight();

    /**
     * Expression that indicates the weight of the font.  Allowed values are
     * "normal" and "bold".  If null, the default is "normal".
     */
    public void setFontWeight(Expression expression);

    /**
     * Expression that indicates the pixel size of the font.  If null, the
     * default value is 10.
     */
    public Expression getFontSize();

    /**
     * Expression that indicates the pixel size of the font.  If null, the
     * default value is 10.
     */
    public void setFontSize(Expression expression);

    /**
     * Returns the object that indicates that the text should be drawn relative
     * to the points in the geometry.
     * Between the PointPlacement and LinePlacement property, exactly one must
     * be non-null since a TextSymbol has either a PointPlacement or a
     * LinePlacement, but not both.
     */
    public PointPlacement getPointPlacement();

    /**
     * Sets the object that indicates that the text should be drawn relative
     * to the points in the geometry.
     * Between the PointPlacement and LinePlacement property, exactly one must
     * be non-null since a TextSymbol has either a PointPlacement or a
     * LinePlacement, but not both.
     * Setting this property to a non-null value will immediately set the
     * LinePlacement property to null.
     */
    public void setPointPlacement(PointPlacement pointPlacement);

    /**
     * Returns the object that indicates that the text should be drawn bending
     * around the lines of the geometry.
     * Between the PointPlacement and LinePlacement property, exactly one must
     * be non-null since a TextSymbol has either a PointPlacement or a
     * LinePlacement, but not both.
     */
    public LinePlacement getLinePlacement();

    /**
     * Returns the object that indicates that the text should be drawn bending
     * around the lines of the geometry.
     * Between the PointPlacement and LinePlacement property, exactly one must
     * be non-null since a TextSymbol has either a PointPlacement or a
     * LinePlacement, but not both.
     * Setting this property to a non-null value will immediately set the
     * PointPlacement property to null.
     */
    public void setLinePlacement(LinePlacement linePlacement);
}
