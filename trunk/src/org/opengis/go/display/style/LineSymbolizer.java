/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.awt.Color;

/**
 * Encapsulates the style data applicable to
 * {@link org.opengis.go.display.primitive.Graphic}s
 * that are of type Line in the sense of SLD (OGC 02-070).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface LineSymbolizer {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    //**  LineSymbolizer properties  **
    
    public static final String LINE_STROKE_BEGIN_ARROW_STYLE = "LINE_STROKE_BEGIN_ARROW_STYLE";
    public static final String LINE_STROKE_END_ARROW_STYLE = "LINE_STROKE_END_ARROW_STYLE";
    public static final String LINE_STROKE_COLOR = "LINE_STROKE_COLOR";    
    public static final String LINE_STROKE_DASH_ARRAY = "LINE_STROKE_DASH_ARRAY";
    public static final String LINE_STROKE_DASH_OFFSET = "LINE_STROKE_DASH_OFFSET";
    public static final String LINE_STROKE_FILL_BACKGROUND_COLOR = "LINE_STROKE_FILL_BACKGROUND_COLOR";
    public static final String LINE_STROKE_FILL_COLOR = "LINE_STROKE_FILL_COLOR";
    public static final String LINE_STROKE_FILL_GRADIENT_POINTS = "LINE_STROKE_FILL_GRADIENT_POINTS"; 
    public static final String LINE_STROKE_FILL_OPACITY = "LINE_STROKE_FILL_OPACITY";
    public static final String LINE_STROKE_FILL_PATTERN = "LINE_STROKE_FILL_PATTERN";
    public static final String LINE_STROKE_FILL_STYLE = "LINE_STROKE_FILL_STYLE";
    public static final String LINE_STROKE_LINE_CAP = "LINE_STROKE_LINE_CAP";
    public static final String LINE_STROKE_LINE_GAP = "LINE_STROKE_LINE_GAP";
    public static final String LINE_STROKE_LINE_JOIN = "LINE_STROKE_LINE_JOIN";
    public static final String LINE_STROKE_LINE_STYLE = "LINE_STROKE_LINE_STYLE";
    public static final String LINE_STROKE_OPACITY = "LINE_STROKE_OPACITY";
    public static final String LINE_STROKE_PATTERN = "LINE_STROKE_PATTERN";
    public static final String LINE_STROKE_WIDTH = "LINE_STROKE_WIDTH";
    
    //**  Default LineSymbolizer property values  **
    
    /**  Default begin arrow style value.  */
    public static final ArrowStyle DEFAULT_LINE_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default end arrow style value.  */
    public static final ArrowStyle DEFAULT_LINE_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default stroke color value.  */
    public static final Color DEFAULT_LINE_STROKE_COLOR = Color.BLACK;

    /**  Default dash array value.  */
    public static final DashArray DEFAULT_LINE_STROKE_DASH_ARRAY = (DashArray)LinePattern.NONE;

    /**  Default dash offset value.  */
    public static final float DEFAULT_LINE_STROKE_DASH_OFFSET = 0.f;

    public static final Color DEFAULT_LINE_STROKE_FILL_BACKGROUND_COLOR = Color.WHITE;
    
    public static final Color DEFAULT_LINE_STROKE_FILL_COLOR = Color.BLACK;
    
    public static final float[] DEFAULT_LINE_STROKE_FILL_GRADIENT_POINTS = new float[0];
    
    public static final float DEFAULT_LINE_STROKE_FILL_OPACITY = 1.f;
    
    public static final FillPattern DEFAULT_LINE_STROKE_FILL_PATTERN = FillPattern.NONE;
    
    public static final FillStyle DEFAULT_LINE_STROKE_FILL_STYLE = FillStyle.SOLID;

    /**  Default line cap value.  */
    public static final LineCap DEFAULT_LINE_STROKE_LINE_CAP = LineCap.BUTT;

    /**  Default line gap value.  */
    public static final float DEFAULT_LINE_STROKE_LINE_GAP = 10.f;

    /**  Default line join value.  */
    public static final LineJoin DEFAULT_LINE_STROKE_LINE_JOIN = LineJoin.BEVEL;

    public static final LinePattern DEFAULT_LINE_STROKE_LINE_PATTERN = LinePattern.NONE;

    /**  Default line style value.  */
    public static final LineStyle DEFAULT_LINE_STROKE_LINE_STYLE = LineStyle.SINGLE;

    /**  Default stroke opacity value.  */
    public static final float DEFAULT_LINE_STROKE_OPACITY = 1.f;

    /**  Default stroke width value.  */
    public static final float DEFAULT_LINE_STROKE_WIDTH = 1.f;

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    public ArrowStyle getLineStrokeBeginArrowStyle();
    
    /**
     * Returns whether the stroke begin arrow style value has been set.
     * @return true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public boolean isLineStrokeBeginArrowStyleSet();
    
    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */    
    public void setLineStrokeBeginArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke begin arrow style value has been set.
     * @param flag true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public void setLineStrokeBeginArrowStyleSet(boolean flag);
    
    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    public ArrowStyle getLineStrokeEndArrowStyle();
    
    /**
     * Returns whether the stroke end arrow style value has been set.
     * @return true if the stroke end arrow style value has been set, false otherwise.
     */    
    public boolean isLineStrokeEndArrowStyleSet();
    
    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */    
    public void setLineStrokeEndArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke end arrow style value has been set.
     * @param flag true if the stroke end arrow style value has been set, false otherwise.
     */    
    public void setLineStrokeEndArrowStyleSet(boolean flag);
      
    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    public Color getLineStrokeColor();
    
    /**
     * Returns whether the stroke color value has been set.
     * @return true if the stroke color value has been set, false otherwise.
     */    
    public boolean isLineStrokeColorSet();
    
    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */    
    public void setLineStrokeColor(Color strokeColor);
    
    /**
     * Sets the fact that the stroke color value has been set.
     * @param flag true if the stroke color value has been set, false otherwise.
     */    
    public void setLineStrokeColorSet(boolean flag);
   
    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    public DashArray getLineStrokeDashArray();
    
    /**
     * Returns whether the stroke dash array value has been set.
     * @return true if the stroke dash array value has been set, false otherwise.
     */    
    public boolean isLineStrokeDashArraySet();
    
    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */    
    public void setLineStrokeDashArray(DashArray strokeDashArray);
    
    /**
     * Sets the fact that the stroke dash array value has been set.
     * @param flag true if the stroke dash array value has been set, false otherwise.
     */    
    public void setLineStrokeDashArraySet(boolean flag);
   
    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    public float getLineStrokeDashOffset();
    
    /**
     * Returns whether the stroke dash offset value has been set.
     * @return true if the stroke dash offset value has been set, false otherwise.
     */    
    public boolean isLineStrokeDashOffsetSet();
    
    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */    
    public void setLineStrokeDashOffset(float strokeDashOffset);
    
    /**
     * Sets the fact that the stroke dash offset value has been set.
     * @param flag true if the stroke dash offset value has been set, false otherwise.
     */    
    public void setLineStrokeDashOffsetSet(boolean flag);
   
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getLineStrokeFillColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isLineStrokeFillColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setLineStrokeFillColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setLineStrokeFillColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getLineStrokeFillBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isLineStrokeFillBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setLineStrokeFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setLineStrokeFillBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getLineStrokeFillGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isLineStrokeFillGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setLineStrokeFillGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setLineStrokeFillGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getLineStrokeFillOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isLineStrokeFillOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setLineStrokeFillOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setLineStrokeFillOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getLineStrokeFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isLineStrokeFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setLineStrokeFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setLineStrokeFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getLineStrokeFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isLineStrokeFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setLineStrokeFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setLineStrokeFillStyleSet(boolean flag);
      
    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    public LineCap getLineStrokeLineCap();
    
    /**
     * Returns whether the stroke line cap value has been set.
     * @return true if the stroke line cap value has been set, false otherwise.
     */    
    public boolean isLineStrokeLineCapSet();
    
    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */    
    public void setLineStrokeLineCap(LineCap strokeLineCap);
    
    /**
     * Sets the fact that the stroke line cap value has been set.
     * @param flag true if the stroke line cap value has been set, false otherwise.
     */    
    public void setLineStrokeLineCapSet(boolean flag);
   
    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    public float getLineStrokeLineGap();
    
    /**
     * Returns whether the stroke line gap value has been set.
     * @return true if the stroke line gap value has been set, false otherwise.
     */    
    public boolean isLineStrokeLineGapSet();
    
    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */    
    public void setLineStrokeLineGap(float strokeLineGap);
    
    /**
     * Sets the fact that the stroke line gap value has been set.
     * @param flag true if the stroke line gap value has been set, false otherwise.
     */    
    public void setLineStrokeLineGapSet(boolean flag);
     
    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    public LineJoin getLineStrokeLineJoin();
    
    /**
     * Returns whether the stroke line join value has been set.
     * @return true if the stroke line join value has been set, false otherwise.
     */    
    public boolean isLineStrokeLineJoinSet();
    
    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */    
    public void setLineStrokeLineJoin(LineJoin strokeLineJoin);
    
    /**
     * Sets the fact that the stroke line join value has been set.
     * @param flag true if the stroke line join value has been set, false otherwise.
     */    
    public void setLineStrokeLineJoinSet(boolean flag);
     
    /**
     * Returns the stroke line pattern value.
     * @return the stroke line pattern value.
     */
    public LinePattern getLineStrokeLinePattern();
    
    /**
     * Returns whether the stroke line pattern value has been set.
     * @return true if the stroke line pattern value has been set, false otherwise.
     */    
    public boolean isLineStrokeLinePatternSet();
    
    /**
     * Sets the stroke line pattern value.
     * @param strokeLinePattern the stroke line pattern value.
     */    
    public void setLineStrokeLinePattern(LinePattern strokeLinePattern);
    
    /**
     * Sets the fact that the stroke line pattern value has been set.
     * @param flag true if the stroke line pattern value has been set, false otherwise.
     */    
    public void setLineStrokeLinePatternSet(boolean flag);
     
    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    public LineStyle getLineStrokeLineStyle();
    
    /**
     * Returns whether the stroke line style value has been set.
     * @return true if the stroke line style value has been set, false otherwise.
     */    
    public boolean isLineStrokeLineStyleSet();
    
    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */    
    public void setLineStrokeLineStyle(LineStyle strokeLineStyle);
    
    /**
     * Sets the fact that the stroke line style value has been set.
     * @param flag true if the stroke line style value has been set, false otherwise.
     */    
    public void setLineStrokeLineStyleSet(boolean flag);
     
    /**
     * Returns the opacity value.
     * @return the opacity value.
     */
    public float getLineStrokeOpacity();
    
    /**
     * Returns whether the opacity value has been set.
     * @return true if the opacity value has been set, false otherwise.
     */    
    public boolean isLineStrokeOpacitySet();
    
    /**
     * Sets the opacity value.
     * @param opacity the opacity value.
     */    
    public void setLineStrokeOpacity(float opacity);
    
    /**
     * Sets the fact that the opacity value has been set.
     * @param flag true if the opacity value has been set, false otherwise.
     */    
    public void setLineStrokeOpacitySet(boolean flag);
   
    /**
     * Returns the width value.
     * @return the width value.
     */
    public float getLineStrokeWidth();
    
    /**
     * Returns whether the width value has been set.
     * @return true if the width value has been set, false otherwise.
     */    
    public boolean isLineStrokeWidthSet();
    
    /**
     * Sets the width value.
     * @param width the width value.
     */    
    public void setLineStrokeWidth(float width);
    
    /**
     * Sets the fact that the width value has been set.
     * @param flag true if the width value has been set, false otherwise.
     */    
    public void setLineStrokeWidthSet(boolean flag);
}

