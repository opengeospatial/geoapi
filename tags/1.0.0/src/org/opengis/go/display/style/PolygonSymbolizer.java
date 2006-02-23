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
 * {@link org.opengis.go.display.primitive.Graphic}s that are of type Polygon
 * in the sense of SLD (OGC 02-070).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface PolygonSymbolizer {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
	
    //**  PolygonSymbolizer properties  **
    public static final String POLYGON_FILL_BACKGROUND_COLOR = "POLYGON_FILL_BACKGROUND_COLOR";
    public static final String POLYGON_FILL_COLOR = "POLYGON_FILL_COLOR";
    public static final String POLYGON_FILL_GRADIENT_POINTS = "POLYGON_FILL_GRADIENT_POINTS"; 
    public static final String POLYGON_FILL_OPACITY = "POLYGON_FILL_OPACITY";
    public static final String POLYGON_FILL_PATTERN = "POLYGON_FILL_PATTERN";
    public static final String POLYGON_FILL_STYLE = "POLYGON_FILL_STYLE";
    public static final String POLYGON_STROKE_BEGIN_ARROW_STYLE = "POLYGON_STROKE_BEGIN_ARROW_STYLE";
    public static final String POLYGON_STROKE_END_ARROW_STYLE = "POLYGON_STROKE_END_ARROW_STYLE";
    public static final String POLYGON_STROKE_COLOR = "POLYGON_STROKE_COLOR"; 
    public static final String POLYGON_STROKE_DASH_ARRAY = "POLYGON_STROKE_DASH_ARRAY";
    public static final String POLYGON_STROKE_DASH_OFFSET = "POLYGON_STROKE_DASH_OFFSET";
    public static final String POLYGON_STROKE_FILL_BACKGROUND_COLOR = "POLYGON_STROKE_FILL_BACKGROUND_COLOR";
    public static final String POLYGON_STROKE_FILL_COLOR = "POLYGON_STROKE_FILL_COLOR";
    public static final String POLYGON_STROKE_FILL_GRADIENT_POINTS = "POLYGON_STROKE_FILL_GRADIENT_POINTS"; 
    public static final String POLYGON_STROKE_FILL_OPACITY = "POLYGON_STROKE_FILL_OPACITY";
    public static final String POLYGON_STROKE_FILL_PATTERN = "POLYGON_STROKE_FILL_PATTERN";
    public static final String POLYGON_STROKE_FILL_STYLE = "POLYGON_STROKE_FILL_STYLE";
    public static final String POLYGON_STROKE_LINE_CAP = "POLYGON_STROKE_LINE_CAP";
    public static final String POLYGON_STROKE_LINE_GAP = "POLYGON_STROKE_LINE_GAP";
    public static final String POLYGON_STROKE_LINE_JOIN = "POLYGON_STROKE_LINE_JOIN";
    public static final String POLYGON_STROKE_LINE_PATTERN = "POLYGON_STROKE_LINE_PATTERN";
    public static final String POLYGON_STROKE_LINE_STYLE = "POLYGON_STROKE_LINE_STYLE";
    public static final String POLYGON_STROKE_OPACITY = "POLYGON_STROKE_OPACITY";
    public static final String POLYGON_STROKE_WIDTH = "POLYGON_STROKE_WIDTH";
    public static final String POLYGON_STROKE_PATTERN = "POLYGON_STROKE_PATTERN";

    //**  Default PolygonSymbolizer property values  **
    
	/**  Default fill background color value.  */
    public static final Color DEFAULT_POLYGON_FILL_BACKGROUND_COLOR = Color.BLACK;

    /**  Default fill color value.  */
    public static final Color DEFAULT_POLYGON_FILL_COLOR = Color.GRAY;

    /**  Default fill gradient points value.  */
    public static final float[] DEFAULT_POLYGON_FILL_GRADIENT_POINTS = new float[2];

    /**  Default fill opacity value.  */
    public static final float DEFAULT_POLYGON_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_POLYGON_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_POLYGON_FILL_STYLE = FillStyle.SOLID;
    
    public static final ArrowStyle DEFAULT_POLYGON_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;
    
    public static final ArrowStyle DEFAULT_POLYGON_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;
    
    /**  Default stroke color value.  */
    public static final Color DEFAULT_POLYGON_STROKE_COLOR = Color.BLACK;
   
    /**  Default dash array value.  */
	public static final DashArray DEFAULT_POLYGON_STROKE_DASH_ARRAY = DashArray.NONE;
    
	/**  Default dash offset value.  */
	public static final float DEFAULT_POLYGON_STROKE_DASH_OFFSET = 0.f;

	/**  Default fill background color value.  */
    public static final Color DEFAULT_POLYGON_STROKE_FILL_BACKGROUND_COLOR = Color.BLACK;

    /**  Default fill color value.  */
    public static final Color DEFAULT_POLYGON_STROKE_FILL_COLOR = Color.GRAY;

    /**  Default fill gradient points value.  */
    public static final float[] DEFAULT_POLYGON_STROKE_FILL_GRADIENT_POINTS = new float[2];

    /**  Default fill opacity value.  */
    public static final float DEFAULT_POLYGON_STROKE_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_POLYGON_STROKE_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_POLYGON_STROKE_FILL_STYLE = FillStyle.SOLID;
    
    /**  Default line cap value.  */
	public static final LineCap DEFAULT_POLYGON_STROKE_LINE_CAP = LineCap.BUTT;
    
	/**  Default line gap value.  */
	public static final float DEFAULT_POLYGON_STROKE_LINE_GAP = 10.f;

	/**  Default line join value.  */
	public static final LineJoin DEFAULT_POLYGON_STROKE_LINE_JOIN = LineJoin.BEVEL;
    
    public static final LinePattern DEFAULT_POLYGON_STROKE_LINE_PATTERN = LinePattern.NONE;
    
	/**  Default line style value.  */
	public static final LineStyle DEFAULT_POLYGON_STROKE_LINE_STYLE = LineStyle.SINGLE;
    
	/**  Default stroke opacity value.  */
	public static final float DEFAULT_POLYGON_STROKE_OPACITY = 1.f;
   
	/**  Default stroke width value.  */
	public static final float DEFAULT_POLYGON_STROKE_WIDTH = 1.f;
    
	//*************************************************************************
    //  Methods
    //*************************************************************************
    
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getPolygonFillColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isPolygonFillColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setPolygonFillColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setPolygonFillColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getPolygonFillBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isPolygonFillBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setPolygonFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setPolygonFillBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getPolygonFillGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isPolygonFillGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setPolygonFillGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setPolygonFillGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getPolygonFillOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isPolygonFillOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setPolygonFillOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setPolygonFillOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getPolygonFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isPolygonFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setPolygonFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setPolygonFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getPolygonFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isPolygonFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setPolygonFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setPolygonFillStyleSet(boolean flag);
    
    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    public ArrowStyle getPolygonStrokeBeginArrowStyle();
    
    /**
     * Returns whether the stroke begin arrow style value has been set.
     * @return true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeBeginArrowStyleSet();
    
    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */    
    public void setPolygonStrokeBeginArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke begin arrow style value has been set.
     * @param flag true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public void setPolygonStrokeBeginArrowStyleSet(boolean flag);
    
    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    public ArrowStyle getPolygonStrokeEndArrowStyle();
    
    /**
     * Returns whether the stroke end arrow style value has been set.
     * @return true if the stroke end arrow style value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeEndArrowStyleSet();
    
    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */    
    public void setPolygonStrokeEndArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke end arrow style value has been set.
     * @param flag true if the stroke end arrow style value has been set, false otherwise.
     */    
    public void setPolygonStrokeEndArrowStyleSet(boolean flag);
      
    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    public Color getPolygonStrokeColor();
    
    /**
     * Returns whether the stroke color value has been set.
     * @return true if the stroke color value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeColorSet();
    
    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */    
    public void setPolygonStrokeColor(Color strokeColor);
    
    /**
     * Sets the fact that the stroke color value has been set.
     * @param flag true if the stroke color value has been set, false otherwise.
     */    
    public void setPolygonStrokeColorSet(boolean flag);
   
    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    public DashArray getPolygonStrokeDashArray();
    
    /**
     * Returns whether the stroke dash array value has been set.
     * @return true if the stroke dash array value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeDashArraySet();
    
    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */    
    public void setPolygonStrokeDashArray(DashArray strokeDashArray);
    
    /**
     * Sets the fact that the stroke dash array value has been set.
     * @param flag true if the stroke dash array value has been set, false otherwise.
     */    
    public void setPolygonStrokeDashArraySet(boolean flag);
   
    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    public float getPolygonStrokeDashOffset();
    
    /**
     * Returns whether the stroke dash offset value has been set.
     * @return true if the stroke dash offset value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeDashOffsetSet();
    
    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */    
    public void setPolygonStrokeDashOffset(float strokeDashOffset);
    
    /**
     * Sets the fact that the stroke dash offset value has been set.
     * @param flag true if the stroke dash offset value has been set, false otherwise.
     */    
    public void setPolygonStrokeDashOffsetSet(boolean flag);
   
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getPolygonStrokeFillColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeFillColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setPolygonStrokeFillColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setPolygonStrokeFillColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getPolygonStrokeFillBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeFillBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setPolygonStrokeFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setPolygonStrokeFillBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getPolygonStrokeFillGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeFillGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setPolygonStrokeFillGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setPolygonStrokeFillGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getPolygonStrokeFillOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeFillOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setPolygonStrokeFillOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setPolygonStrokeFillOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getPolygonStrokeFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setPolygonStrokeFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setPolygonStrokeFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getPolygonStrokeFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setPolygonStrokeFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setPolygonStrokeFillStyleSet(boolean flag);
      
    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    public LineCap getPolygonStrokeLineCap();
    
    /**
     * Returns whether the stroke line cap value has been set.
     * @return true if the stroke line cap value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeLineCapSet();
    
    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */    
    public void setPolygonStrokeLineCap(LineCap strokeLineCap);
    
    /**
     * Sets the fact that the stroke line cap value has been set.
     * @param flag true if the stroke line cap value has been set, false otherwise.
     */    
    public void setPolygonStrokeLineCapSet(boolean flag);
   
    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    public float getPolygonStrokeLineGap();
    
    /**
     * Returns whether the stroke line gap value has been set.
     * @return true if the stroke line gap value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeLineGapSet();
    
    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */    
    public void setPolygonStrokeLineGap(float strokeLineGap);
    
    /**
     * Sets the fact that the stroke line gap value has been set.
     * @param flag true if the stroke line gap value has been set, false otherwise.
     */    
    public void setPolygonStrokeLineGapSet(boolean flag);
     
    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    public LineJoin getPolygonStrokeLineJoin();
    
    /**
     * Returns whether the stroke line join value has been set.
     * @return true if the stroke line join value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeLineJoinSet();
    
    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */    
    public void setPolygonStrokeLineJoin(LineJoin strokeLineJoin);
    
    /**
     * Sets the fact that the stroke line join value has been set.
     * @param flag true if the stroke line join value has been set, false otherwise.
     */    
    public void setPolygonStrokeLineJoinSet(boolean flag);
     
    /**
     * Returns the stroke line pattern value.
     * @return the stroke line pattern value.
     */
    public LinePattern getPolygonStrokeLinePattern();

    /**
     * Returns whether the stroke line pattern value has been set.
     * @return true if the stroke line pattern value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeLinePatternSet();

    /**
     * Sets the stroke line pattern value.
     * @param strokeLinePattern the stroke line pattern value.
     */    
    public void setPolygonStrokeLinePattern(LinePattern strokeLinePattern);

    /**
     * Sets the fact that the stroke line pattern value has been set.
     * @param flag true if the stroke line pattern value has been set, false otherwise.
     */    
    public void setPolygonStrokeLinePatternSet(boolean flag);
      

    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    public LineStyle getPolygonStrokeLineStyle();
    
    /**
     * Returns whether the stroke line style value has been set.
     * @return true if the stroke line style value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeLineStyleSet();
    
    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */    
    public void setPolygonStrokeLineStyle(LineStyle strokeLineStyle);
    
    /**
     * Sets the fact that the stroke line style value has been set.
     * @param flag true if the stroke line style value has been set, false otherwise.
     */    
    public void setPolygonStrokeLineStyleSet(boolean flag);
     
    /**
     * Returns the opacity value.
     * @return the opacity value.
     */
    public float getPolygonStrokeOpacity();
    
    /**
     * Returns whether the opacity value has been set.
     * @return true if the opacity value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeOpacitySet();
    
    /**
     * Sets the opacity value.
     * @param opacity the opacity value.
     */    
    public void setPolygonStrokeOpacity(float opacity);
    
    /**
     * Sets the fact that the opacity value has been set.
     * @param flag true if the opacity value has been set, false otherwise.
     */    
    public void setPolygonStrokeOpacitySet(boolean flag);
   
    /**
     * Returns the width value.
     * @return the width value.
     */
    public float getPolygonStrokeWidth();
    
    /**
     * Returns whether the width value has been set.
     * @return true if the width value has been set, false otherwise.
     */    
    public boolean isPolygonStrokeWidthSet();
    
    /**
     * Sets the width value.
     * @param width the width value.
     */    
    public void setPolygonStrokeWidth(float width);
    
    /**
     * Sets the fact that the width value has been set.
     * @param flag true if the width value has been set, false otherwise.
     */    
    public void setPolygonStrokeWidthSet(boolean flag);
    
}

