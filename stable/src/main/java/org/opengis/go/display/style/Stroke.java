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
 * Encapsulates the stroke attributes that can be applied to any line Graphic.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Stroke {
    /**
     *  arrow direction attribute name.
     */
    public static final String ARROW_DIRECTION = "STROKE_ARROW_DIRECTION";
      
    /**
     *  arrow style attribute name.
     */
    public static final String ARROW_STYLE = "STROKE_ARROW_STYLE";
   
    /**
     *  color attribute name.
     */
    public static final String COLOR = "STROKE_COLOR";
  
    /**
     *  dash array attribute name.
     */
    public static final String DASH_ARRAY = "STROKE_DASH_ARRAY";
   
    /**
     *  dash offset attribute name.
     */
    public static final String DASH_OFFSET = "STROKE_DASH_OFFSET";
   
    /**
     *  fill attribute name.
     */
    public static final String FILL = "STROKE_FILL";
   
    /**
     *  line cap attribute name.
     */
    public static final String LINE_CAP = "STROKE_LINE_CAP";
   
    /**
     *  line gap attribute name.
     */
    public static final String LINE_GAP = "STROKE_LINE_GAP";
   
    /**
     *  line join attribute name.
     */
    public static final String LINE_JOIN = "STROKE_LINE_JOIN";

    /**
     *  line style attribute name.
     */
    public static final String LINE_STYLE = "STROKE_LINE_STYLE";
    
    /**
     *  opacity attribute name.
     */
    public static final String OPACITY = "STROKE_OPACITY";
    
    /**
     *  width attribute name.
     */
    public static final String WIDTH = "STROKE_WIDTH";
    
    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    public ArrowStyle getBeginArrowStyle();
    
    /**
     * Returns whether the stroke begin arrow style value has been set.
     * @return true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public boolean isBeginArrowStyleSet();
    
    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */    
    public void setBeginArrowStyle(ArrowStyle strokeArrowStyle);
    
    /**
     * Sets the fact that the stroke begin arrow style value has been set.
     * @param flag true if the stroke begin arrow style value has been set, false otherwise.
     */    
    public void setBeginArrowStyleSet(boolean flag);
    
    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    public ArrowStyle getEndArrowStyle();

    /**
     * Returns whether the stroke end arrow style value has been set.
     * @return true if the stroke end arrow style value has been set, false otherwise.
     */    
    public boolean isEndArrowStyleSet();

    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */    
    public void setEndArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Sets the fact that the stroke end arrow style value has been set.
     * @param flag true if the stroke end arrow style value has been set, false otherwise.
     */    
    public void setEndArrowStyleSet(boolean flag);
      
    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    public Color getColor();
    
    /**
     * Returns whether the stroke color value has been set.
     * @return true if the stroke color value has been set, false otherwise.
     */    
    public boolean isColorSet();
    
    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */    
    public void setColor(Color strokeColor);
    
    /**
     * Sets the fact that the stroke color value has been set.
     * @param flag true if the stroke color value has been set, false otherwise.
     */    
    public void setColorSet(boolean flag);
   
    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    public DashArray getDashArray();
    
    /**
     * Returns whether the stroke dash array value has been set.
     * @return true if the stroke dash array value has been set, false otherwise.
     */    
    public boolean isDashArraySet();
    
    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */    
    public void setDashArray(DashArray strokeDashArray);
    
    /**
     * Sets the fact that the stroke dash array value has been set.
     * @param flag true if the stroke dash array value has been set, false otherwise.
     */    
    public void setDashArraySet(boolean flag);
   
    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    public float getDashOffset();
    
    /**
     * Returns whether the stroke dash offset value has been set.
     * @return true if the stroke dash offset value has been set, false otherwise.
     */    
    public boolean isDashOffsetSet();
    
    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */    
    public void setDashOffset(float strokeDashOffset);
    
    /**
     * Sets the fact that the stroke dash offset value has been set.
     * @param flag true if the stroke dash offset value has been set, false otherwise.
     */    
    public void setDashOffsetSet(boolean flag);
   
    /**
     * Returns the stroke fill value.
     * @return the stroke fill value.
     */
    public Fill getFill();
    
    /**
     * Returns whether the stroke fill value has been set.
     * @return true if the stroke fill value has been set, false otherwise.
     */    
    public boolean isFillSet();
    
    /**
     * Sets the stroke fill value.
     * @param strokeFill the stroke fill value.
     */    
    public void setFill(Fill strokeFill);
    
    /**
     * Sets the fact that the stroke fill value has been set.
     * @param flag true if the stroke fill value has been set, false otherwise.
     */    
    public void setFillSet(boolean flag);
   
    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    public LineCap getLineCap();
    
    /**
     * Returns whether the stroke line cap value has been set.
     * @return true if the stroke line cap value has been set, false otherwise.
     */    
    public boolean isLineCapSet();
    
    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */    
    public void setLineCap(LineCap strokeLineCap);
    
    /**
     * Sets the fact that the stroke line cap value has been set.
     * @param flag true if the stroke line cap value has been set, false otherwise.
     */    
    public void setLineCapSet(boolean flag);
   
    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    public float getLineGap();
    
    /**
     * Returns whether the stroke line gap value has been set.
     * @return true if the stroke line gap value has been set, false otherwise.
     */    
    public boolean isLineGapSet();
    
    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */    
    public void setLineGap(float strokeLineGap);
    
    /**
     * Sets the fact that the stroke line gap value has been set.
     * @param flag true if the stroke line gap value has been set, false otherwise.
     */    
    public void setLineGapSet(boolean flag);
     
    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    public LineJoin getLineJoin();
    
    /**
     * Returns whether the stroke line join value has been set.
     * @return true if the stroke line join value has been set, false otherwise.
     */    
    public boolean isLineJoinSet();
    
    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */    
    public void setLineJoin(LineJoin strokeLineJoin);
    
    /**
     * Sets the fact that the stroke line join value has been set.
     * @param flag true if the stroke line join value has been set, false otherwise.
     */    
    public void setLineJoinSet(boolean flag);
     
    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    public LineStyle getLineStyle();
    
    /**
     * Returns whether the stroke line style value has been set.
     * @return true if the stroke line style value has been set, false otherwise.
     */    
    public boolean isLineStyleSet();
    
    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */    
    public void setLineStyle(LineStyle strokeLineStyle);
    
    /**
     * Sets the fact that the stroke line style value has been set.
     * @param flag true if the stroke line style value has been set, false otherwise.
     */    
    public void setLineStyleSet(boolean flag);
     
    /**
     * Returns the opacity value.
     * @return the opacity value.
     */
    public float getOpacity();
    
    /**
     * Returns whether the opacity value has been set.
     * @return true if the opacity value has been set, false otherwise.
     */    
    public boolean isOpacitySet();
    
    /**
     * Sets the opacity value.
     * @param opacity the opacity value.
     */    
    public void setOpacity(float opacity);
    
    /**
     * Sets the fact that the opacity value has been set.
     * @param flag true if the opacity value has been set, false otherwise.
     */    
    public void setOpacitySet(boolean flag);
   
    /**
     * Returns the width value.
     * @return the width value.
     */
    public float getWidth();
    
    /**
     * Returns whether the width value has been set.
     * @return true if the width value has been set, false otherwise.
     */    
    public boolean isWidthSet();
    
    /**
     * Sets the width value.
     * @param width the width value.
     */    
    public void setWidth(float width);
    
    /**
     * Sets the fact that the width value has been set.
     * @param flag true if the width value has been set, false otherwise.
     */    
    public void setWidthSet(boolean flag);
}
