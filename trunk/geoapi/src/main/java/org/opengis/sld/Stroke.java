/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;


/**
 * Contains all the information needed to draw styled lines.  Stroke objects are contained
 * by {@link LineSymbol}s and {@link PolygonSymbol}s. There are three basic types of strokes:
 * solid-color, {@code GraphicFill} (stipple), and repeated linear {@code GraphicStroke}. A
 * repeated linear graphic is plotted linearly and has its graphic symbol bent around the curves
 * of the line string, and a graphic fill has the pixels of the line rendered with a repeating
 * area-fill pattern. If neither a {@link #getGraphicFill GraphicFill} nor {@link #getGraphicStroke
 * GraphicStroke} element is given, then the line symbolizer will render a solid color.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Stroke")
public interface Stroke {
    /**
     * If non-null, indicates that line should be drawn by tiling the (thin) area of the line with
     * the given graphic. Between {@code getGraphicFill()} and {@link #getGraphicStroke()}, only one
     * may return a non-null value since a {@code Stroke} can have a {@code GraphicFill} or a
     * {@code GraphicStroke}, but not both.
     */
    @XmlElement("GraphicFill")
    Graphic getGraphicFill();

    /**
     * Sets the line that should be drawn by tiling the (thin) area of the line with the given graphic.
     * See {@link #getGraphicFill} for details.
     */
    @XmlElement("GraphicFill")
    void setGraphicFill(Graphic graphicFill);

    /**
     * If non-null, indicates that lines should be drawn by repeatedly plotting the given graphic
     * along the path of the lines, rotating it according to the orientation of the line.
     * Between {@link #getGraphicFill()} and {@code getGraphicStroke}, only one may return a
     * non-null value since a {@code Stroke} can have a {@code GraphicFill} or a {@code GraphicStroke},
     * but not both.
     */
    @XmlElement("GraphicStroke")
    Graphic getGraphicStroke();

    /**
     * Sets the lines that should be drawn by repeatedly plotting the given graphic along the path
     * of the lines, rotating it according to the orientation of the line.
     * See {@link #getGraphicStroke} for details.
     */
    @XmlElement("GraphicStroke")
    void setGraphicStroke(Graphic graphicStroke);

    /**
     * Indicates the color of the line if it is to be solid-color filled.  The format of color
     * values is {@code "#rrggbb"} where {@code rr}, {@code gg}, and {@code bb}, are red, green,
     * and blue intensity values, respectively, represented as two digit hexadecimal integers.
     * The hexadecimal digits between {@code A} and {@code F} may be in either uppercase or lowercase.
     * If null, the default color is {@code "#000000"}, black.
     */
    @XmlElement("stroke")  // TODO: Actually a CssParameter
    Expression getColor();

    /**
     * Sets the color of the line if it is to be solid-color filled.
     * See {@link #getColor} for details.
     */
    @XmlElement("stroke")  // TODO: Actually a CssParameter
    void setColor(Expression expression);

    /**
     * Indicates the level of translucency as a floating point number whose value is between 0.0
     * and 1.0 (inclusive).  A value of zero means completely transparent.  A value of 1.0 means
     * completely opaque.  If null, the default value is 1.0, totally opaque.
     */
    @XmlElement("stroke-opacity")  // TODO: Actually a CssParameter
    Expression getOpacity();

    /**
     * Sets the level of translucency as a floating point number whose value is between 0.0 and 1.0 (inclusive).
     * See {@link #getOpacity} for details.
     */
    @XmlElement("stroke-opacity")  // TODO: Actually a CssParameter
    void setOpacity(Expression expression);

    /**
     * Gives the absolute width in pixels of the line stroke as a floating point number.
     * Fractional numbers are allowed (with system-dependent interpretation), but negative
     * numbers are not.  If null, the default value is 1.0.
     */
    @XmlElement("stroke-width")  // TODO: Actually a CssParameter
    Expression getWidth();

    /**
     * Sets the absolute width in pixels of the line stroke as a floating point number.
     * See {@link #getWidth} for details.
     */
    @XmlElement("stroke-width")  // TODO: Actually a CssParameter
    void setWidth(Expression expression);

    /**
     * Indicates how the various segments of a (thick) line string should be joined.
     * Valid values are "miter", "round", and "bevel".  If null, the default value is
     * system dependent (probably whichever one is fastest to render).
     */
    @XmlElement("stroke-linejoin")  // TODO: Actually a CssParameter
    Expression getLineJoin();

    /**
     * Sets how the various segments of a (thick) line string should be joined.
     * Valid values are "miter", "round", and "bevel".
     * See {@link #getLineJoin} for details.
     */
    @XmlElement("stroke-linejoin")  // TODO: Actually a CssParameter
    void setLineJoin(Expression expression);

    /**
     * Indicates how the beginning and ending segments of a line string will be terminated.
     * Valid values are "butt", "round", and "square".  If null, the default value is system
     * dependent.
     */
    @XmlElement("stroke-linecap")  // TODO: Actually a CssParameter
    Expression getLineCap();

    /**
     * Sets how the beginning and ending segments of a line string will be terminated.
     * Valid values are "butt", "round", and "square".
     * See {@link #getLineCap} for details.
     */
    @XmlElement("stroke-linecap")  // TODO: Actually a CssParameter
    void setLineCap(Expression expression);

    /**
     * If present, indicates the dash pattern as a space-separated sequence of floating point numbers.
     * The first number represents the length of the first dash to draw.  The second number
     * represents the length of space to leave.  This continues to the end of the list then
     * repeats.  If the list contains an odd number of values, then before
     * rendering the list is enlarged by repeating the last value.  If this
     * parameter is omitted, lines will be drawn as solid and unbroken.
     */
    @XmlElement("stroke-dasharray")  // TODO: Actually a CssParameter
    Expression getDashArray();

    /**
     * Set the dash pattern as a space-separated sequence of floating point numbers.
     * See {@link #getDashArray} for details.
     */
    @XmlElement("stroke-dasharray")  // TODO: Actually a CssParameter
    void setDashArray(Expression expression);

    /**
     * Indicates the distance offset into the dash array to begin drawing.
     * If null, the default value is zero.
     */
    @XmlElement("stroke-dashoffset")  // TODO: Actually a CssParameter
    Expression getDashOffset();

    /**
     * Sets the distance offset into the dash array to begin drawing.
     * See {@link #getDashOffset} for details.
     */
    @XmlElement("stroke-dashoffset")  // TODO: Actually a CssParameter
    void setDashOffset(Expression expression);
}
