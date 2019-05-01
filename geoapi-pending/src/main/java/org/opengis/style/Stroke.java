/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.style;

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;


/**
 * Contains all the information needed to draw styled lines.  Stroke objects are contained
 * by {@link LineSymbolizer} and {@link PolygonSymbolizer}. There are three basic types of strokes:
 * solid-color, {@code GraphicFill} (stipple), and repeated linear {@code GraphicStroke}. A
 * repeated linear graphic is plotted linearly and has its graphic symbol bent around the curves
 * of the line string, and a graphic fill has the pixels of the line rendered with a repeating
 * area-fill pattern. If neither a {@link #getGraphicFill GraphicFill} nor {@link #getGraphicStroke
 * GraphicStroke} element is given, then the line symbolizer will render a solid color.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
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
    GraphicFill getGraphicFill();

    /**
     * If non-null, indicates that lines should be drawn by repeatedly plotting the given graphic
     * along the path of the lines, rotating it according to the orientation of the line.
     * Between {@link #getGraphicFill()} and {@code getGraphicStroke}, only one may return a
     * non-null value since a {@code Stroke} can have a {@code GraphicFill} or a {@code GraphicStroke},
     * but not both.
     */
    @XmlElement("GraphicStroke")
    GraphicStroke getGraphicStroke();

    //*************************************************************
    // SVG PARAMETERS
    //*************************************************************

    /**
     * Indicates the color of the line if it is to be solid-color filled.  The format of color
     * values is {@code "#rrggbb"} where {@code rr}, {@code gg}, and {@code bb}, are red, green,
     * and blue intensity values, respectively, represented as two digit hexadecimal integers.
     * The hexadecimal digits between {@code A} and {@code F} may be in either uppercase or lowercase.
     * If null, the default color is {@code "#000000"}, black.
     */
    @XmlParameter("stroke")
    Expression getColor();

    /**
     * Indicates the level of translucency as a floating point number whose value is between 0.0
     * and 1.0 (inclusive).  A value of zero means completely transparent.  A value of 1.0 means
     * completely opaque.  If null, the default value is 1.0, totally opaque.
     */
    @XmlParameter("stroke-opacity")
    Expression getOpacity();

    /**
     * Gives the absolute width in uoms of the line stroke as a floating point number.
     * Fractional numbers are allowed (with system-dependent interpretation), but negative
     * numbers are not.  If null, the default value is 1.0.
     */
    @XmlParameter("stroke-width")
    Expression getWidth();

    /**
     * Indicates how the various segments of a (thick) line string should be joined.
     * Valid values are "miter", "round", and "bevel".  If null, the default value is
     * system dependent (probably whichever one is fastest to render).
     */
    @XmlParameter("stroke-linejoin")
    Expression getLineJoin();

    /**
     * Indicates how the beginning and ending segments of a line string will be terminated.
     * Valid values are "butt", "round", and "square".  If null, the default value is system
     * dependent.
     */
    @XmlParameter("stroke-linecap")
    Expression getLineCap();

    /**
     * If present, indicates the dash pattern as a space-separated sequence of floating point numbers.
     * The first number represents the length of the first dash to draw.  The second number
     * represents the length of space to leave.  This continues to the end of the list then
     * repeats.  If the list contains an odd number of values, then before
     * rendering the list is enlarged by repeating the last value.  If this
     * parameter is omitted, lines will be drawn as solid and unbroken.
     */
    @XmlParameter("stroke-dasharray")
    float[] getDashArray();

    /**
     * Indicates the distance offset into the dash array to begin drawing.
     * If null, the default value is zero.
     */
    @XmlParameter("stroke-dashoffset")
    Expression getDashOffset();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
