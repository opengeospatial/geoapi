/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.awt.Color;


/**
 * The <code>Fill</code> interface encapsulates the drawing attributes
 * that can be applied to fills on line Graphics and closed Graphics.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Fill {
    /**
     * Fill color attribute name.
     */
    public static final String COLOR = "FILL_COLOR";

    /**
     * Fill background color attribute name.
     */
    public static final String BACKGROUND_COLOR = "FILL_BACKGROUND_COLOR";
    
    /**
     * Halo fill gradient attribute name.
     */
    public static final String GRADIENT_POINTS = "FILL_GRADIENT_POINTS";

    /**
     * Fill opacity attribute name.
     */
    public static final String OPACITY = "FILL_OPACITY";
    
    /**
     * Fill pattern attribute name.
     */
    public static final String FILL_PATTERN = "FILL_PATTERN";

    /**
     * Fill style attribute name.
     */
    public static final String FILL_STYLE = "FILL_STYLE";
    
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getColor();
    
    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isColorSet();
    
    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setColor(Color fillColor);
    
    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getBackgroundColor();
    
    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isBackgroundColorSet();
    
    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setBackgroundColor(Color fillBackgroundColor);
    
    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setBackgroundColorSet(boolean flag);
    
    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getGradientPoints();
    
    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isGradientPointsSet();
    
    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setGradientPoints(float[] fillGradientPoints);
    
    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getOpacity();
    
    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isOpacitySet();
    
    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setOpacity(float fillOpacity);
    
    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getFillPattern();
    
    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isFillPatternSet();
    
    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setFillPattern(FillPattern fillPattern);
    
    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getFillStyle();
    
    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isFillStyleSet();
    
    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setFillStyle(FillStyle fillStyle);
    
    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setFillStyleSet(boolean flag);
}
