/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

import java.awt.Color;


/**
 * Encapsulates the style data applicable to
 * {@link org.opengis.go.display.primitive.Graphic}s that are of type Line in
 * the sense of SLD (OGC 02-070).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface LineSymbolizer extends GraphicStyle {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************

    //**  Default LineSymbolizer property values  **

    /**  Default begin arrow style value.  */
    ArrowStyle DEFAULT_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default end arrow style value.  */
    ArrowStyle DEFAULT_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default stroke color value.  */
    Color DEFAULT_STROKE_FILL_COLOR = Color.BLACK;

    /**  Default stroke background color value.  */
    Color DEFAULT_STROKE_FILL_BACKGROUND_COLOR = Color.WHITE;

    /**  Default dash array value.  */
    DashArray DEFAULT_STROKE_DASH_ARRAY = DashArray.NO_DASH;

    /**  Default dash offset value.  */
    float DEFAULT_STROKE_DASH_OFFSET = 0.f;

    /**  Default gradient point values.  */
    float[] DEFAULT_STROKE_FILL_GRADIENT_POINTS = new float[2];

    /**  Default opacity value.  */
    float DEFAULT_STROKE_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    FillPattern DEFAULT_STROKE_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    FillStyle DEFAULT_STROKE_FILL_STYLE = FillStyle.EMPTY;

    /**  Default line cap value.  */
    LineCap DEFAULT_STROKE_LINE_CAP = LineCap.BUTT;

    /**  Default line gap value.  */
    float DEFAULT_STROKE_LINE_GAP = 10.f;

    /**  Default line join value.  */
    LineJoin DEFAULT_STROKE_LINE_JOIN = LineJoin.BEVEL;

    /**  Default stroke line pattern value.  */
    LinePattern DEFAULT_STROKE_LINE_PATTERN = LinePattern.NONE;

    /**  Default line style value.  */
    LineStyle DEFAULT_STROKE_LINE_STYLE = LineStyle.SINGLE;

    /**  Default stroke opacity value.  */
    float DEFAULT_STROKE_OPACITY = 1.f;

    /**  Default stroke width value.  */
    float DEFAULT_STROKE_WIDTH = 1.f;

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    ArrowStyle getStrokeBeginArrowStyle();

    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */
    void setStrokeBeginArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    ArrowStyle getStrokeEndArrowStyle();

    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */
    void setStrokeEndArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    Color getStrokeColor();

    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */
    void setStrokeColor(Color strokeColor);

    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    DashArray getStrokeDashArray();

    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */
    void setStrokeDashArray(DashArray strokeDashArray);

    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    float getStrokeDashOffset();

    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */
    void setStrokeDashOffset(float strokeDashOffset);

    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    Color getStrokeFillColor();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */
    void setStrokeFillColor(Color fillColor);

    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    Color getStrokeFillBackgroundColor();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */
    void setStrokeFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Returns the fill gradient points value, or null if there is no
     * fill gradient.
     * @return the fill gradient points value.
     */
    float[] getStrokeFillGradientPoints();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value, or null
     * to specify no fill gradient.
     */
    void setStrokeFillGradientPoints(float[] fillGradientPoints);

    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    float getStrokeFillOpacity();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */
    void setStrokeFillOpacity(float fillOpacity);

    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    FillPattern getStrokeFillPattern();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */
    void setStrokeFillPattern(FillPattern fillPattern);

    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    FillStyle getStrokeFillStyle();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */
    void setStrokeFillStyle(FillStyle fillStyle);

    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    LineCap getStrokeLineCap();

    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */
    void setStrokeLineCap(LineCap strokeLineCap);

    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    float getStrokeLineGap();

    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */
    void setStrokeLineGap(float strokeLineGap);

    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    LineJoin getStrokeLineJoin();

    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */
    void setStrokeLineJoin(LineJoin strokeLineJoin);

    /**
     * Returns the stroke line pattern value.
     * @return the stroke line pattern value.
     */
    LinePattern getStrokeLinePattern();

    /**
     * Sets the stroke line pattern value.
     * @param strokeLinePattern the stroke line pattern value.
     */
    void setStrokeLinePattern(LinePattern strokeLinePattern);

    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    LineStyle getStrokeLineStyle();

    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */
    void setStrokeLineStyle(LineStyle strokeLineStyle);

    /**
     * Returns the stroke opacity value.
     * @return the stroke opacity value.
     */
    float getStrokeOpacity();

    /**
     * Sets the stroke opacity value.
     * @param strokeOpacity the stroke opacity value.
     */
    void setStrokeOpacity(float strokeOpacity);

    /**
     * Returns the width value.
     * @return the width value.
     */
    float getStrokeWidth();

    /**
     * Sets the width value.
     * @param width the width value.
     */
    void setStrokeWidth(float width);
}
