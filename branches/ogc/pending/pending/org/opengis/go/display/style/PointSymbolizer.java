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

import java.awt.Color;

/**
 * The <code>PointSymbolizer</code> interface encapsulates the point and mark attributes
 * that can be applied to any point Graphic.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface PointSymbolizer {
	
	/**
	 * Default begin arrow style value.
	 */
	public static final ArrowStyle DEFAULT_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;
    
	/**
	 * Default end arrow style value.
	 */
	public static final ArrowStyle DEFAULT_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;

	/**
	 * Default dash array value.
	 */
	public static final DashArray DEFAULT_STROKE_DASH_ARRAY = (DashArray) LinePattern.NONE;
    
	/**
	 * Default dash offset value.
	 */
	public static final float DEFAULT_STROKE_DASH_OFFSET = 0.f;

	/**
	 * Default line cap value.
	 */
	public static final LineCap DEFAULT_STROKE_LINE_CAP = LineCap.BUTT;
    
	/**
	 * Default line gap value.
	 */
	public static final float DEFAULT_STROKE_LINE_GAP = 10.f;
    
	/**
	 * Default line join value.
	 */
	public static final LineJoin DEFAULT_STROKE_LINE_JOIN = LineJoin.BEVEL;
    
	/**
	 * Default line style value.
	 */
	public static final LineStyle DEFAULT_STROKE_LINE_STYLE = LineStyle.SINGLE;
    
	/**
	 * Default stroke color value.
	 */
	public static final Color DEFAULT_STROKE_COLOR = Color.BLACK;
   
	/**
	 * Default stroke opacity value.
	 */
	public static final float DEFAULT_STROKE_OPACITY = 1.f;
   
	/**
	 * Default stroke width value.
	 */
	public static final float DEFAULT_STROKE_WIDTH = 1.f;
    
	/**
	 * Default fill color value.
	 */
	public static final Color DEFAULT_FILL_COLOR = Color.GRAY;

	/**
	 * Default fill background color value.
	 */
	public static final Color DEFAULT_FILL_BACKGROUND_COLOR = Color.BLACK;

	/**
	 * Default fill gradient points value.
	 */
	public static final float[] DEFAULT_FILL_GRADIENT_POINTS = new float[2];

	/**
	 * Default fill opacity value.
	 */
	public static final float DEFAULT_FILL_OPACITY = 1.f;

	/**
	 * Default fill pattern value.
	 */
	public static final FillPattern DEFAULT_FILL_PATTERN = FillPattern.NONE;

	/**
	 * Default fill style value.
	 */
	public static final FillStyle DEFAULT_FILL_STYLE = FillStyle.SOLID;
    

    /**
      * Default point mark value.
      */
     public static final Mark DEFAULT_MARK = Mark.CIRCLE;

     /**
      * Default point opacity value.
      */
     public static final float DEFAULT_OPACITY = 1.f;
    
    /**
      * Default point rotation value.
      */
     public static final float DEFAULT_ROTATION = 0.f;

     /**
      * Default point size value.
      */
     public static final float DEFAULT_SIZE = 16.f;
    
     /**
      * PointSymbolizer mark attribute name.
      */
     public static final String MARK = "POINTSYMBOLIZER_MARK";
    
     /**
      * PointSymbolizer mark fill attribute name.
      */
     public static final String FILL = "POINTSYMBOLIZER_FILL";
     
     /**
      * PointSymbolizer mark stroke attribute name.
      */
     public static final String STROKE = "POINTSYMBOLIZER_STROKE";
    
     /**
      * PointSymbolizer opacity attribute name.
      */
     public static final String OPACITY = "POINTSYMBOLIZER_OPACITY";

    /**
     * PointSymbolizer rotation attribute name.
     */
    public static final String ROTATION = "POINTSYMBOLIZER_ROTATION";
     
    /**
     * PointSymbolizer size attribute name.
     */
    public static final String SIZE = "POINTSYMBOLIZER_SIZE";
     
    /**
     * Returns the point mark value.
     * @return the point mark value.
     */
    public Mark getMark();
    
    /**
     * Returns whether the point mark value has been set.
     * @return true if the point mark value has been set, false otherwise.
     */    
    public boolean isMarkSet();
    
    /**
     * Sets the point mark value.
     * @param pointMark the point mark value.
     */    
    public void setMark(Mark pointMark);
    
    /**
     * Sets the fact that the point mark value has been set.
     * @param flag true if the point mark value has been set, false otherwise.
     */    
    public void setMarkSet(boolean flag);
    
    /**
     * Returns the point fill value.
     * @return the point fill value.
     */
    public Fill getFill();
    
    /**
     * Returns whether the point fill value has been set.
     * @return true if the point fill value has been set, false otherwise.
     */    
    public boolean isFillSet();
    
    /**
     * Sets the point fill value.
     * @param pointFill the point fill value.
     */    
    public void setFill(Fill pointFill);
    
    /**
     * Sets the fact that the point fill value has been set.
     * @param flag true if the point fill value has been set, false otherwise.
     */    
    public void setFillSet(boolean flag);
   
    /**
     * Returns the point stroke value.
     * @return the point stroke value.
     */
    public Stroke getStroke();
    
    /**
     * Returns whether the point stroke value has been set.
     * @return true if the point stroke value has been set, false otherwise.
     */    
    public boolean isStrokeSet();
    
    /**
     * Sets the point stroke value.
     * @param pointStroke the point stroke value.
     */    
    public void setStroke(Stroke pointStroke);
    
    /**
     * Sets the fact that the point stroke value has been set.
     * @param flag true if the point stroke value has been set, false otherwise.
     */    
    public void setStrokeSet(boolean flag);
   
    /**
     * Returns the point opacity value.
     * @return the point opacity value.
     */
    public float getOpacity();
    
    /**
     * Returns whether the point opacity value has been set.
     * @return true if the point opacity value has been set, false otherwise.
     */    
    public boolean isOpacitySet();
    
    /**
     * Sets the point opacity value.
     * @param pointOpacity the point opacity value.
     */    
    public void setOpacity(float pointOpacity);
    
    /**
     * Sets the fact that the point opacity value has been set.
     * @param flag true if the point opacity value has been set, false otherwise.
     */    
    public void setOpacitySet(boolean flag);
   
    /**
     * Returns the point rotation value.
     * @return the point rotation value.
     */
    public float getRotation();
    
    /**
     * Returns whether the point rotation value has been set.
     * @return true if the point rotation value has been set, false otherwise.
     */    
    public boolean isRotationSet();
    
    /**
     * Sets the point rotation value.
     * @param pointRotation the point rotation value.
     */    
    public void setRotation(float pointRotation);
    
    /**
     * Sets the fact that the point rotation value has been set.
     * @param flag true if the point rotation value has been set, false otherwise.
     */    
    public void setRotationSet(boolean flag);
   
    /**
     * Returns the point size value.
     * @return the point size value.
     */
    public float getSize();
    
    /**
     * Returns whether the point size value has been set.
     * @return true if the point size value has been set, false otherwise.
     */    
    public boolean isSizeSet();
    
    /**
     * Sets the point size value.
     * @param pointSize the point size value.
     */    
    public void setSize(float pointSize);
    
    /**
     * Sets the fact that the point size value has been set.
     * @param flag true if the point size value has been set, false otherwise.
     */    
    public void setSizeSet(boolean flag);
   
}
