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

// J2SE direct dependencies
import java.awt.Color;

/**
 * Encapsulates the style data applicable to
 * {@link org.opengis.go.display.primitive.Graphic}s that are of type Line in
 * the sense of SLD (OGC 02-070).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public interface LineSymbolizer extends GraphicStyle {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************

    //**  Default LineSymbolizer property values  **

    /**  Default begin arrow style value.  */
    public static final ArrowStyle DEFAULT_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default end arrow style value.  */
    public static final ArrowStyle DEFAULT_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default stroke color value.  */
    public static final Color DEFAULT_STROKE_FILL_COLOR = Color.BLACK;

    /**  Default stroke background color value.  */
    public static final Color DEFAULT_STROKE_FILL_BACKGROUND_COLOR = Color.WHITE;

    /**  Default dash array value.  */
    public static final DashArray DEFAULT_STROKE_DASH_ARRAY = DashArray.NONE;

    /**  Default dash offset value.  */
    public static final float DEFAULT_STROKE_DASH_OFFSET = 0.f;

    /**  Default gradient point values.  */
    public static final float[] DEFAULT_STROKE_FILL_GRADIENT_POINTS = new float[2];

    /**  Default opacity value.  */
    public static final float DEFAULT_STROKE_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_STROKE_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_STROKE_FILL_STYLE = FillStyle.EMPTY;

    /**  Default line cap value.  */
    public static final LineCap DEFAULT_STROKE_LINE_CAP = LineCap.BUTT;

    /**  Default line gap value.  */
    public static final float DEFAULT_STROKE_LINE_GAP = 10.f;

    /**  Default line join value.  */
    public static final LineJoin DEFAULT_STROKE_LINE_JOIN = LineJoin.BEVEL;

    /**  Default stroke line pattern value.  */
    public static final LinePattern DEFAULT_STROKE_LINE_PATTERN = LinePattern.NONE;

    /**  Default line style value.  */
    public static final LineStyle DEFAULT_STROKE_LINE_STYLE = LineStyle.SINGLE;

    /**  Default stroke opacity value.  */
    public static final float DEFAULT_STROKE_OPACITY = 1.f;

    /**  Default stroke width value.  */
    public static final float DEFAULT_STROKE_WIDTH = 1.f;

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    public ArrowStyle getStrokeBeginArrowStyle();

    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */
    public void setStrokeBeginArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    public ArrowStyle getStrokeEndArrowStyle();

    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */
    public void setStrokeEndArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    public Color getStrokeColor();

    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */
    public void setStrokeColor(Color strokeColor);

    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    public DashArray getStrokeDashArray();

    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */
    public void setStrokeDashArray(DashArray strokeDashArray);

    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    public float getStrokeDashOffset();

    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */
    public void setStrokeDashOffset(float strokeDashOffset);

    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getStrokeFillColor();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */
    public void setStrokeFillColor(Color fillColor);

    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getStrokeFillBackgroundColor();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */
    public void setStrokeFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Returns the fill gradient points value, or null if there is no
     * fill gradient.
     * @return the fill gradient points value.
     */
    public float[] getStrokeFillGradientPoints();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value, or null
     * to specify no fill gradient.
     */
    public void setStrokeFillGradientPoints(float[] fillGradientPoints);

    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getStrokeFillOpacity();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */
    public void setStrokeFillOpacity(float fillOpacity);

    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getStrokeFillPattern();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */
    public void setStrokeFillPattern(FillPattern fillPattern);

    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getStrokeFillStyle();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */
    public void setStrokeFillStyle(FillStyle fillStyle);

    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    public LineCap getStrokeLineCap();

    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */
    public void setStrokeLineCap(LineCap strokeLineCap);

    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    public float getStrokeLineGap();

    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */
    public void setStrokeLineGap(float strokeLineGap);

    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    public LineJoin getStrokeLineJoin();

    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */
    public void setStrokeLineJoin(LineJoin strokeLineJoin);

    /**
     * Returns the stroke line pattern value.
     * @return the stroke line pattern value.
     */
    public LinePattern getStrokeLinePattern();

    /**
     * Sets the stroke line pattern value.
     * @param strokeLinePattern the stroke line pattern value.
     */
    public void setStrokeLinePattern(LinePattern strokeLinePattern);

    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    public LineStyle getStrokeLineStyle();

    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */
    public void setStrokeLineStyle(LineStyle strokeLineStyle);

    /**
     * Returns the stroke opacity value.
     * @return the stroke opacity value.
     */
    public float getStrokeOpacity();

    /**
     * Sets the stroke opacity value.
     * @param strokeOpacity the stroke opacity value.
     */
    public void setStrokeOpacity(float strokeOpacity);

    /**
     * Returns the width value.
     * @return the width value.
     */
    public float getStrokeWidth();

    /**
     * Sets the width value.
     * @param width the width value.
     */
    public void setStrokeWidth(float width);
}

