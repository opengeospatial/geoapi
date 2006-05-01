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
import java.awt.Font;

/**
 * Encapsulates the style data applicable to
 * {@link org.opengis.go.display.primitive.Graphic}s
 * that are of type Text in the sense of SLD (OGC 02-070).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface TextSymbolizer {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    //**  TextSymbolizer properties  **
    
    public static final String TEXT_FILL_BACKGROUND_COLOR = "TEXT_FILL_BACKGROUND_COLOR";
    public static final String TEXT_FILL_COLOR = "TEXT_FILL_COLOR";
    public static final String TEXT_FILL_GRADIENT_POINTS = "TEXT_FILL_GRADIENT_POINTS"; 
    public static final String TEXT_FILL_OPACITY = "TEXT_FILL_OPACITY";
    public static final String TEXT_FILL_PATTERN = "TEXT_FILL_PATTERN";
    public static final String TEXT_FILL_STYLE = "TEXT_FILL_STYLE";
    public static final String TEXT_FONT = "TEXT_FONT";
    public static final String TEXT_HALO_RADIUS = "TEXT_HALO_RADIUS";
    public static final String TEXT_LABEL = "TEXT_LABEL"; 
    public static final String TEXT_LABEL_ROTATION = "TEXT_ROTATION";
    public static final String TEXT_LABEL_SHOW_LABEL = "TEXT_SHOW_LABEL";
    public static final String TEXT_LABEL_X_ANCHOR = "TEXT_X_ANCHOR";
    public static final String TEXT_LABEL_Y_ANCHOR = "TEXT_Y_ANCHOR";
    public static final String TEXT_LABEL_X_DISPLACEMENT = "TEXT_X_DISPLACEMENT";
    public static final String TEXT_LABEL_Y_DISPLACEMENT = "TEXT_Y_DISPLACEMENT";
    
    
    //**  Default values  **
    
    /**  Default fill color value.  */
    public static final Color DEFAULT_TEXT_FILL_COLOR = Color.BLACK;

    /**  Default fill background color value.  */
    public static final Color DEFAULT_TEXT_FILL_BACKGROUND_COLOR = Color.WHITE;

    /**  Default fill gradient points value.  */
    public static final float[] DEFAULT_TEXT_FILL_GRADIENT_POINTS = new float[2];

    /**  Default fill opacity value.  */
    public static final float DEFAULT_TEXT_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_TEXT_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_TEXT_FILL_STYLE = FillStyle.SOLID;
    
    // PENDING(jdc):  need a default font!!
    public static final Font DEFAULT_TEXT_FONT = null;
    
    /**  Default halo radius value.  */
    public static final float DEFAULT_TEXT_HALO_RADIUS = 1.f;
    
    /**  Default label.  */
    public static final String DEFAULT_TEXT_LABEL = "Label";
    
    /**  Default rotation.  */
    public static final float DEFAULT_TEXT_LABEL_ROTATION = 0f;
        
    /**  Default show label.  */
    public static final boolean DEFAULT_TEXT_LABEL_SHOW_LABEL = false;
    
    /**  Default xAnchor.  */
    public static final XAnchor DEFAULT_TEXT_LABEL_X_ANCHOR = XAnchor.CENTER;
    
    /**  Default xDisplacement.  */
    public static final float DEFAULT_TEXT_LABEL_X_DISPLACEMENT = 0f;
    
    /**  Default yAnchor.  */
    public static final YAnchor DEFAULT_TEXT_LABEL_Y_ANCHOR = YAnchor.MIDDLE;
    
    /**  Default yDisplacement.  */
    public static final float DEFAULT_TEXT_LABEL_Y_DISPLACEMENT = 0f;
    
    //*************************************************************************
    //  Methods
    //*************************************************************************
    
    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    public Color getTextFillColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */    
    public boolean isTextFillColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */    
    public void setTextFillColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */    
    public void setTextFillColorSet(boolean flag);
   
    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getTextFillBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */    
    public boolean isTextFillBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */    
    public void setTextFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */    
    public void setTextFillBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getTextFillGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */    
    public boolean isTextFillGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */    
    public void setTextFillGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */    
    public void setTextFillGradientPointsSet(boolean flag);
   
    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getTextFillOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */    
    public boolean isTextFillOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */    
    public void setTextFillOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */    
    public void setTextFillOpacitySet(boolean flag);
   
    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getTextFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */    
    public boolean isTextFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */    
    public void setTextFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */    
    public void setTextFillPatternSet(boolean flag);
   
    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getTextFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */    
    public boolean isTextFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */    
    public void setTextFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */    
    public void setTextFillStyleSet(boolean flag);
        
    //**  FONT  **
    
	/**
	 * Returns the Font object.
	 * @return the Font object.
	 */
	public Font getTextFont();

	/**
	 * Returns whether the Font object has been set.
	 * @return true if the Font object has been set, false otherwise.
	 */
	public boolean isTextFontSet();

	/**
	 * Sets the Font object.
	 * @param object the Font object.
	 * 
	 */
	public void setTextFont(Font object);

	/**
	 * Sets the fact that the Font object has been set.
	 * @param flag true if the Font object has been set, false otherwise.
	 * 
	 */
	public void setTextFontSet(boolean flag);
	
    //**  HALO  **

    /**
     * Returns the halo radius value.
     * @return the value of the halo radius.
     */
    public float getTextHaloRadius();
    
    /**
     * Returns whether the halo radius value has been set.
     * @return true if the halo radius value has been set, false otherwise.
     */    
    public boolean isTextHaloRadiusSet();
    
    /**
     * Sets the halo radius value.
     * @param haloRadius the value of the halo radius.
     */    
    public void setTextHaloRadius(float haloRadius);
    
    /**
     * Sets the fact that the halo radius value has been set.
     * @param flag true if the halo radius value has been set, false otherwise.
     */    
    public void setTextHaloRadiusSet(boolean flag);
    
    /**
     * Returns the label.
     * @return the label.
     */
    public String getTextLabel();
    
    /**
     * Returns whether the label has been set.
     * @return true if the label has been set, false otherwise.
     */    
    public boolean isTextLabelSet();
    
    /**
     * Sets the label.
     * @param label the label.
     */    
    public void setTextLabel(String label);
    
    /**
     * Sets the fact that the label has been set.
     * @param flag true if the label has been set, false otherwise.
     */    
    public void setTextLabelSet(boolean flag);
    
    /**
     * Returns the label rotation.
     * @return the label rotation.
     */
    public float getTextLabelRotation();
    
    /**
     * Returns whether the label rotation has been set.
     * @return true if the label rotation has been set, false otherwise.
     */    
    public boolean isTextLabelRotationSet();
    
    /**
     * Sets the label rotation.
     * @param labelRotation the label rotation.
     */    
    public void setTextLabelRotation(float labelRotation);
    
    /**
     * Sets the fact that the label rotation has been set.
     * @param flag true if the label rotation has been set, false otherwise.
     */    
    public void setTextLabelRotationSet(boolean flag);
   
    /**
     * Returns the show label.
     * @return the show label.
     */
    public boolean getTextLabelShowLabel();
    
    /**
     * Returns whether the show label has been set.
     * @return true if the show label has been set, false otherwise.
     */    
    public boolean isTextLabelShowLabelSet();
    
    /**
     * Sets the show label.
     * @param showLabel the show label.
     */    
    public void setTextLabelShowLabel(boolean showLabel);
    
    /**
     * Sets the fact that the show label has been set.
     * @param flag true if the show label has been set, false otherwise.
     */    
    public void setTextLabelShowLabelSet(boolean flag);

    /**
     * Returns the label XAnchor.
     * @return the label XAnchor.
     */
    public XAnchor getTextLabelXAnchor();
    
    /**
     * Returns whether the label XAnchor has been set.
     * @return true if the label XAnchor has been set, false otherwise.
     */    
    public boolean isTextLabelXAnchorSet();
    
    /**
     * Sets the label XAnchor.
     * @param labelXAnchor the label XAnchor.
     */    
    public void setTextLabelXAnchor(XAnchor labelXAnchor);
    
    /**
     * Sets the fact that the label XAnchor has been set.
     * @param flag true if the label XAnchor has been set, false otherwise.
     */    
    public void setTextLabelXAnchorSet(boolean flag);
   
    /**
     * Returns the label X displacement.
     * @return the label X displacement.
     */
    public float getTextLabelXDisplacement();
    
    /**
     * Returns whether the label X displacement has been set.
     * @return true if the label X displacement has been set, false otherwise.
     */    
    public boolean isTextLabelXDisplacementSet();
    
    /**
     * Sets the label X displacement.
     * @param labelXDisplacement the label X displacement.
     */    
    public void setTextLabelXDisplacement(float labelXDisplacement);
    
    /**
     * Sets the fact that the label X displacement has been set.
     * @param flag true if the label X displacement has been set, false otherwise.
     */    
    public void setTextLabelXDisplacementSet(boolean flag);
   
    /**
     * Returns the label YAnchor.
     * @return the label YAnchor.
     */
    public YAnchor getTextLabelYAnchor();
    
    /**
     * Returns whether the label YAnchor has been set.
     * @return true if the label YAnchor has been set, false otherwise.
     */    
    public boolean isTextLabelYAnchorSet();
    
    /**
     * Sets the label YAnchor.
     * @param labelYAnchor the label YAnchor.
     */    
    public void setTextLabelYAnchor(YAnchor labelYAnchor);
    
    /**
     * Sets the fact that the label YAnchor has been set.
     * @param flag true if the label YAnchor has been set, false otherwise.
     */    
    public void setTextLabelYAnchorSet(boolean flag);
   
    /**
     * Returns the label Y displacement.
     * @return the label Y displacement.
     */
    public float getTextLabelYDisplacement();
    
    /**
     * Returns whether the label Y displacement has been set.
     * @return true if the label Y displacement has been set, false otherwise.
     */    
    public boolean isTextLabelYDisplacementSet();
    
    /**
     * Sets the label Y displacement.
     * @param labelYDisplacement the label Y displacement.
     */    
    public void setTextLabelYDisplacement(float labelYDisplacement);
    
    /**
     * Sets the fact that the label Y displacement has been set.
     * @param flag true if the label Y displacement has been set, false otherwise.
     */    
    public void setTextLabelYDisplacementSet(boolean flag);
   
    
	
}

