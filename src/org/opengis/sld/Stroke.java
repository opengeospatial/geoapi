/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;


/**
 * Contains all the information needed to draw styled lines.  Stroke objects are contained
 * by {@link LineSymbol}s and {@link PolygonSymbol}s.  A {@code Stroke} can optionally have
 * any number of parameters that dictate the 
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface Stroke {
    /**
     * If this value is non-null, it indicates that line should be drawn by
     * tiling the (thin) area of the line with the given graphic.
     * Between getGraphicFill and getGraphicStroke, only one may return a
     * non-null value since a Stroke can have a GraphicFill or a GraphicStroke,
     * but not both.
     */
    public Graphic getGraphicFill();

    /**
     * If the parameter is non-null, then it indicates that the line should be
     * drawn by tiling the (thin) area of the line with the given graphic.
     * Between getGraphicFill and getGraphicStroke, only one may return a
     * non-null value since a Stroke can have a GraphicFill or a GraphicStroke,
     * but not both.  So if this method is called with a non-null value, the
     * value of the GraphicStroke property will be set to null.
     */
    public void setGraphicFill(Graphic graphicFill);

    /**
     * If this value is non-null, it indicates that lines should be drawn by
     * repeatedly plotting the given graphic along the path of the lines,
     * rotating it according to the orientation of the line.
     * Between getGraphicFill and getGraphicStroke, only one may return a
     * non-null value since a Stroke can have a GraphicFill or a GraphicStroke,
     * but not both.
     */
    public Graphic getGraphicStroke();

    /**
     * If the parameter is non-null, it indicates that lines should be drawn by
     * repeatedly plotting the given graphic along the path of the lines,
     * rotating it according to the orientation of the line.
     * Between getGraphicFill and getGraphicStroke, only one may return a
     * non-null value since a Stroke can have a GraphicFill or a GraphicStroke,
     * but not both.  So if this method is called with a non-null value, the
     * value of the GraphicFill property will be set to null.
     */
    public void setGraphicStroke(Graphic graphicStroke);

    /**
     * The value of this parameter indicates the color of the line if it is to
     * be solid-color filled.  The format of color values is "#rrggbb" where rr,
     * gg, and bb, are red, green, and blue intensity values, respectively,
     * represented as two digit hexadecimal integers.  If null, the default
     * color is "#000000", black.
     */
    public Expression getColor();

    /**
     * The value of this parameter indicates the color of the line if it is to
     * be solid-color filled.  The format of color values is "#rrggbb" where rr,
     * gg, and bb, are red, green, and blue intensity values, respectively,
     * represented as two digit hexadecimal integers.  If null, the default
     * color is "#000000", black.
     */
    public void setColor(Expression expression);

    /**
     * The value of this parameter must be a floating point number whose value
     * is between 0.0 and 1.0 (inclusive).  A value of zero means completely
     * transparent.  A value of 1.0 means completely opaque.  If null, the
     * default value is 1.0, totally opaque.
     */
    public Expression getOpacity();

    /**
     * The value of this parameter must be a floating point number whose value
     * is between 0.0 and 1.0 (inclusive).  A value of zero means completely
     * transparent.  A value of 1.0 means completely opaque.  If null, the
     * default value is 1.0, totally opaque.
     */
    public void setOpacity(Expression expression);

    /**
     * The value of this parameter gives the absolute width in pixels of the
     * line stroke as a floating point number.  Fractional numbers are allowed
     * (with system-dependent interpretation), but negative numbers are not.  If
     * null, the default value is 1.0.
     */
    public Expression getWidth();

    /**
     * The value of this parameter gives the absolute width in pixels of the
     * line stroke as a floating point number.  Fractional numbers are allowed
     * (with system-dependent interpretation), but negative numbers are not.  If
     * null, the default value is 1.0.
     */
    public void setWidth(Expression expression);

    /**
     * The value of this parameter indicates how the various segments of a
     * (thick) line string should be joined.  Valid values are "miter", "round",
     * and "bevel".  If null, the default value is system dependent (probably
     * whichever one is fastest to render).
     */
    public Expression getLineJoin();

    /**
     * The value of this parameter indicates how the various segments of a
     * (thick) line string should be joined.  Valid values are "miter", "round",
     * and "bevel".  If null, the default value is system dependent (probably
     * whichever one is fastest to render).
     */
    public void setLineJoin(Expression expression);

    /**
     * The value of this parameter indicates how the beginning and ending
     * segments of a line string will be terminated.  Value values are "butt",
     * "round", and "square".  If null, the default value is system dependent.
     */
    public Expression getLineCap();

    /**
     * The value of this parameter indicates how the beginning and ending
     * segments of a line string will be terminated.  Value values are "butt",
     * "round", and "square".  If null, the default value is system dependent.
     */
    public void setLineCap(Expression expression);

    /**
     * If present, the value of this parameter must be a space-separated
     * sequence of floating point numbers.  The first number represents the
     * length of the first dash to draw.  The second number represents the
     * length of space to leave.  This continues to the end of the list then
     * repeats.  If the list contains an odd number of values, then before
     * rendering the list is enlarged by repeating the last value.  If this
     * parameter is omitted, lines will be drawn as solid and unbroken.
     */
    public Expression getDashArray();

    /**
     * If present, the value of this parameter must be a space-separated
     * sequence of floating point numbers.  The first number represents the
     * length of the first dash to draw.  The second number represents the
     * length of space to leave.  This continues to the end of the list then
     * repeats.  If the list contains an odd number of values, then before
     * rendering the list is enlarged by repeating the last value.  If this
     * parameter is omitted, lines will be drawn as solid and unbroken.
     */
    public void setDashArray(Expression expression);

    /**
     * The value of this parameter indicates the distance offset into the dash
     * array to begin drawing.  If null, the default value is zero.
     */
    public Expression getDashOffset();

    /**
     * The value of this parameter indicates the distance offset into the dash
     * array to begin drawing.  If null, the default value is zero.
     */
    public void setDashOffset(Expression expression);
}
