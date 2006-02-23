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
 * Encapsulates the point and mark attributes that can be applied to any point
 * {@link org.opengis.go.display.primitive.Graphic}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface PointSymbolizer {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    //**  PointSymbolizer properties  **
    
    public static final String POINT_FILL_BACKGROUND_COLOR = "POINT_FILL_BACKGROUND_COLOR";
    public static final String POINT_FILL_COLOR = "POINT_FILL_COLOR";
    public static final String POINT_FILL_GRADIENT_POINTS = "POINT_FILL_GRADIENT_POINTS"; 
    public static final String POINT_FILL_OPACITY = "POINT_FILL_OPACITY";
    public static final String POINT_FILL_PATTERN = "POINT_FILL_PATTERN";
    public static final String POINT_FILL_STYLE = "POINT_FILL_STYLE";
    public static final String POINT_MARK = "POINT_MARK";
    public static final String POINT_OPACITY = "POINT_OPACITY";
    public static final String POINT_ROTATION = "POINT_ROTATION";
    public static final String POINT_SIZE = "POINT_SIZE";
    public static final String POINT_STROKE_BEGIN_ARROW_STYLE = "POINT_STROKE_BEGIN_ARROW_STYLE";
    public static final String POINT_STROKE_END_ARROW_STYLE = "POINT_STROKE_END_ARROW_STYLE";
    public static final String POINT_STROKE_COLOR = "POINT_STROKE_COLOR"; 
    public static final String POINT_STROKE_DASH_ARRAY = "POINT_STROKE_DASH_ARRAY";
    public static final String POINT_STROKE_DASH_OFFSET = "POINT_STROKE_DASH_OFFSET";
    public static final String POINT_STROKE_FILL_BACKGROUND_COLOR = "POINT_STROKE_FILL_BACKGROUND_COLOR";
    public static final String POINT_STROKE_FILL_COLOR = "POINT_STROKE_FILL_COLOR";
    public static final String POINT_STROKE_FILL_GRADIENT_POINTS = "POINT_STROKE_FILL_GRADIENT_POINTS"; 
    public static final String POINT_STROKE_FILL_OPACITY = "POINT_STROKE_FILL_OPACITY";
    public static final String POINT_STROKE_FILL_PATTERN = "POINT_STROKE_FILL_PATTERN";
    public static final String POINT_STROKE_FILL_STYLE = "POINT_STROKE_FILL_STYLE";
    public static final String POINT_STROKE_LINE_CAP = "POINT_STROKE_LINE_CAP";
    public static final String POINT_STROKE_LINE_GAP = "POINT_STROKE_LINE_GAP";
    public static final String POINT_STROKE_LINE_JOIN = "POINT_STROKE_LINE_JOIN";
    public static final String POINT_STROKE_LINE_PATTERN = "POINT_STROKE_LINE_PATTERN";
    public static final String POINT_STROKE_LINE_STYLE = "POINT_STROKE_LINE_STYLE";
    public static final String POINT_STROKE_OPACITY = "POINT_STROKE_OPACITY";
    public static final String POINT_STROKE_WIDTH = "POINT_STROKE_WIDTH";
    public static final String POINT_STROKE_PATTERN = "POINT_STROKE_PATTERN";
    
	//**  Default PointSymbolizer property values
    
    /**  Default fill background color value.  */
    public static final Color DEFAULT_POINT_FILL_BACKGROUND_COLOR = Color.BLACK;

	/**  Default fill color value.  */
    public static final Color DEFAULT_POINT_FILL_COLOR = Color.GRAY;

    /**  Default fill gradient points value.  */
    public static final float[] DEFAULT_POINT_FILL_GRADIENT_POINTS = new float[2];

    /**  Default fill opacity value.  */
    public static final float DEFAULT_POINT_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_POINT_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_POINT_FILL_STYLE = FillStyle.SOLID;
    
    /**  Default point mark value.  */
    public static final Mark DEFAULT_POINT_MARK = Mark.CIRCLE;

    /**  Default point opacity value.  */
    public static final float DEFAULT_POINT_OPACITY = 1.f;
    
    /**  Default point rotation value.  */
    public static final float DEFAULT_POINT_ROTATION = 0.f;

    /**  Default point size value.  */
    public static final float DEFAULT_POINT_SIZE = 16.f;
    
    /**  Default begin arrow style value.  */
	public static final ArrowStyle DEFAULT_POINT_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;
    
	/**  Default end arrow style value.  */
	public static final ArrowStyle DEFAULT_POINT_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;

    /**  Default stroke color value.  */
    public static final Color DEFAULT_POINT_STROKE_COLOR = Color.BLACK;
   
	/**  Default dash array value.  */
	public static final DashArray DEFAULT_POINT_STROKE_DASH_ARRAY = (DashArray) DashArray.NONE;
    
	/**  Default dash offset value.  */
	public static final float DEFAULT_POINT_STROKE_DASH_OFFSET = 0.f;

    /**  Default fill color value.  */
    public static final Color DEFAULT_POINT_STROKE_FILL_COLOR = Color.GRAY;

    /**  Default fill background color value.  */
    public static final Color DEFAULT_POINT_STROKE_FILL_BACKGROUND_COLOR = Color.BLACK;

    /**  Default fill gradient points value.  */
    public static final float[] DEFAULT_POINT_STROKE_FILL_GRADIENT_POINTS = new float[2];

    /**  Default fill opacity value.  */
    public static final float DEFAULT_POINT_STROKE_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_POINT_STROKE_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_POINT_STROKE_FILL_STYLE = FillStyle.SOLID;
    
	/**  Default line cap value.  */
	public static final LineCap DEFAULT_POINT_STROKE_LINE_CAP = LineCap.BUTT;
    
	/**  Default line gap value.  */
	public static final float DEFAULT_POINT_STROKE_LINE_GAP = 10.f;
    
	/**  Default line join value.  */
	public static final LineJoin DEFAULT_POINT_STROKE_LINE_JOIN = LineJoin.BEVEL;
    
    public static final LinePattern DEFAULT_POINT_STROKE_LINE_PATTERN = LinePattern.NONE;
    
	/**  Default line style value.  */
	public static final LineStyle DEFAULT_POINT_STROKE_LINE_STYLE = LineStyle.SINGLE;
    
	/**  Default stroke opacity value.  */
	public static final float DEFAULT_POINT_STROKE_OPACITY = 1.f;
   
	/**  Default stroke width value.  */
	public static final float DEFAULT_POINT_STROKE_WIDTH = 1.f;
    
    //*************************************************************************
    //  Methods
    //*************************************************************************
    
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getPointFillColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isPointFillColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setPointFillColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setPointFillColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getPointFillBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isPointFillBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setPointFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setPointFillBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getPointFillGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isPointFillGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setPointFillGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setPointFillGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getPointFillOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isPointFillOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setPointFillOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setPointFillOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getPointFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isPointFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setPointFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setPointFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getPointFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isPointFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setPointFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setPointFillStyleSet(boolean flag);
    
    
    /**
     * Returns the point mark value.
     * @return the point mark value.
     */
    public Mark getPointMark();
    
    /**
     * Returns whether the point mark value has been set.
     * @return true if the point mark value has been set, false otherwise.
     */    
    public boolean isPointMarkSet();
    
    /**
     * Sets the point mark value.
     * @param pointMark the point mark value.
     */    
    public void setPointMark(Mark pointMark);
    
    /**
     * Sets the fact that the point mark value has been set.
     * @param flag true if the point mark value has been set, false otherwise.
     */    
    public void setPointMarkSet(boolean flag);
    
    /**
     * Returns the point opacity value.
     * @return the point opacity value.
     */
    public float getPointOpacity();
    
    /**
     * Returns whether the point opacity value has been set.
     * @return true if the point opacity value has been set, false otherwise.
     */    
    public boolean isPointOpacitySet();
    
    /**
     * Sets the point opacity value.
     * @param pointOpacity the point opacity value.
     */    
    public void setPointOpacity(float pointOpacity);
    
    /**
     * Sets the fact that the point opacity value has been set.
     * @param flag true if the point opacity value has been set, false otherwise.
     */    
    public void setPointOpacitySet(boolean flag);
   
    /**
     * Returns the point rotation value.
     * @return the point rotation value.
     */
    public float getPointRotation();
    
    /**
     * Returns whether the point rotation value has been set.
     * @return true if the point rotation value has been set, false otherwise.
     */    
    public boolean isPointRotationSet();
    
    /**
     * Sets the point rotation value.
     * @param pointRotation the point rotation value.
     */    
    public void setPointRotation(float pointRotation);
    
    /**
     * Sets the fact that the point rotation value has been set.
     * @param flag true if the point rotation value has been set, false otherwise.
     */    
    public void setPointRotationSet(boolean flag);
   
    /**
     * Returns the point size value.
     * @return the point size value.
     */
    public float getPointSize();
    
    /**
     * Returns whether the point size value has been set.
     * @return true if the point size value has been set, false otherwise.
     */    
    public boolean isPointSizeSet();
    
    /**
     * Sets the point size value.
     * @param pointSize the point size value.
     */    
    public void setPointSize(float pointSize);
    
    /**
     * Sets the fact that the point size value has been set.
     * @param flag true if the point size value has been set, false otherwise.
     */    
    public void setPointSizeSet(boolean flag);
   
    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    public ArrowStyle getPointStrokeBeginArrowStyle();
    
    /**
     * Returns whether the stroke begin arrow style value has been set.
     * @return true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public boolean isPointStrokeBeginArrowStyleSet();
    
    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */    
    public void setPointStrokeBeginArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke begin arrow style value has been set.
     * @param flag true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public void setPointStrokeBeginArrowStyleSet(boolean flag);
    
    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    public ArrowStyle getPointStrokeEndArrowStyle();
    
    /**
     * Returns whether the stroke end arrow style value has been set.
     * @return true if the stroke end arrow style value has been set, false otherwise.
     */    
    public boolean isPointStrokeEndArrowStyleSet();
    
    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */    
    public void setPointStrokeEndArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke end arrow style value has been set.
     * @param flag true if the stroke end arrow style value has been set, false otherwise.
     */    
    public void setPointStrokeEndArrowStyleSet(boolean flag);
      
    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    public Color getPointStrokeColor();
    
    /**
     * Returns whether the stroke color value has been set.
     * @return true if the stroke color value has been set, false otherwise.
     */    
    public boolean isPointStrokeColorSet();
    
    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */    
    public void setPointStrokeColor(Color strokeColor);
    
    /**
     * Sets the fact that the stroke color value has been set.
     * @param flag true if the stroke color value has been set, false otherwise.
     */    
    public void setPointStrokeColorSet(boolean flag);
   
    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    public DashArray getPointStrokeDashArray();
    
    /**
     * Returns whether the stroke dash array value has been set.
     * @return true if the stroke dash array value has been set, false otherwise.
     */    
    public boolean isPointStrokeDashArraySet();
    
    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */    
    public void setPointStrokeDashArray(DashArray strokeDashArray);
    
    /**
     * Sets the fact that the stroke dash array value has been set.
     * @param flag true if the stroke dash array value has been set, false otherwise.
     */    
    public void setPointStrokeDashArraySet(boolean flag);
   
    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    public float getPointStrokeDashOffset();
    
    /**
     * Returns whether the stroke dash offset value has been set.
     * @return true if the stroke dash offset value has been set, false otherwise.
     */    
    public boolean isPointStrokeDashOffsetSet();
    
    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */    
    public void setPointStrokeDashOffset(float strokeDashOffset);
    
    /**
     * Sets the fact that the stroke dash offset value has been set.
     * @param flag true if the stroke dash offset value has been set, false otherwise.
     */    
    public void setPointStrokeDashOffsetSet(boolean flag);
   
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getPointStrokeFillColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isPointStrokeFillColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setPointStrokeFillColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setPointStrokeFillColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getPointStrokeFillBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isPointStrokeFillBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setPointStrokeFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setPointStrokeFillBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getPointStrokeFillGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isPointStrokeFillGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setPointStrokeFillGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setPointStrokeFillGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getPointStrokeFillOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isPointStrokeFillOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setPointStrokeFillOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setPointStrokeFillOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getPointStrokeFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isPointStrokeFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setPointStrokeFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setPointStrokeFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getPointStrokeFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isPointStrokeFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setPointStrokeFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setPointStrokeFillStyleSet(boolean flag);
      
    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    public LineCap getPointStrokeLineCap();
    
    /**
     * Returns whether the stroke line cap value has been set.
     * @return true if the stroke line cap value has been set, false otherwise.
     */    
    public boolean isPointStrokeLineCapSet();
    
    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */    
    public void setPointStrokeLineCap(LineCap strokeLineCap);
    
    /**
     * Sets the fact that the stroke line cap value has been set.
     * @param flag true if the stroke line cap value has been set, false otherwise.
     */    
    public void setPointStrokeLineCapSet(boolean flag);
   
    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    public float getPointStrokeLineGap();
    
    /**
     * Returns whether the stroke line gap value has been set.
     * @return true if the stroke line gap value has been set, false otherwise.
     */    
    public boolean isPointStrokeLineGapSet();
    
    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */    
    public void setPointStrokeLineGap(float strokeLineGap);
    
    /**
     * Sets the fact that the stroke line gap value has been set.
     * @param flag true if the stroke line gap value has been set, false otherwise.
     */    
    public void setPointStrokeLineGapSet(boolean flag);
     
    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    public LineJoin getPointStrokeLineJoin();
    
    /**
     * Returns whether the stroke line join value has been set.
     * @return true if the stroke line join value has been set, false otherwise.
     */    
    public boolean isPointStrokeLineJoinSet();
    
    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */    
    public void setPointStrokeLineJoin(LineJoin strokeLineJoin);
    
    /**
     * Sets the fact that the stroke line join value has been set.
     * @param flag true if the stroke line join value has been set, false otherwise.
     */    
    public void setPointStrokeLineJoinSet(boolean flag);
    
    /**
     * Returns the stroke line pattern value.
     * @return the stroke line pattern value.
     */
    public LinePattern getPointStrokeLinePattern();

    /**
     * Returns whether the stroke line pattern value has been set.
     * @return true if the stroke line pattern value has been set, false otherwise.
     */    
    public boolean isPointStrokeLinePatternSet();

    /**
     * Sets the stroke line pattern value.
     * @param strokeLinePattern the stroke line pattern value.
     */    
    public void setPointStrokeLinePattern(LinePattern strokeLinePattern);

    /**
     * Sets the fact that the stroke line pattern value has been set.
     * @param flag true if the stroke line pattern value has been set, false otherwise.
     */    
    public void setPointStrokeLinePatternSet(boolean flag);
      
    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    public LineStyle getPointStrokeLineStyle();
    
    /**
     * Returns whether the stroke line style value has been set.
     * @return true if the stroke line style value has been set, false otherwise.
     */    
    public boolean isPointStrokeLineStyleSet();
    
    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */    
    public void setPointStrokeLineStyle(LineStyle strokeLineStyle);
    
    /**
     * Sets the fact that the stroke line style value has been set.
     * @param flag true if the stroke line style value has been set, false otherwise.
     */    
    public void setPointStrokeLineStyleSet(boolean flag);
     
    /**
     * Returns the opacity value.
     * @return the opacity value.
     */
    public float getPointStrokeOpacity();
    
    /**
     * Returns whether the opacity value has been set.
     * @return true if the opacity value has been set, false otherwise.
     */    
    public boolean isPointStrokeOpacitySet();
    
    /**
     * Sets the opacity value.
     * @param opacity the opacity value.
     */    
    public void setPointStrokeOpacity(float opacity);
    
    /**
     * Sets the fact that the opacity value has been set.
     * @param flag true if the opacity value has been set, false otherwise.
     */    
    public void setPointStrokeOpacitySet(boolean flag);
   
    /**
     * Returns the width value.
     * @return the width value.
     */
    public float getPointStrokeWidth();
    
    /**
     * Returns whether the width value has been set.
     * @return true if the width value has been set, false otherwise.
     */    
    public boolean isPointStrokeWidthSet();
    
    /**
     * Sets the width value.
     * @param width the width value.
     */    
    public void setPointStrokeWidth(float width);
    
    /**
     * Sets the fact that the width value has been set.
     * @param flag true if the width value has been set, false otherwise.
     */    
    public void setPointStrokeWidthSet(boolean flag);

}

