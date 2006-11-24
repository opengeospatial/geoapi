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


/**
 * Encapsulates the label placement attributes that can be applied to any text Graphic.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface LabelPlacement {
    /**
     * Label attribute name.
     */
    public static final String LABEL = "LABELPLACEMENT_LABEL";
    
    /**
     * Label rotation attribute name.
     */
    public static final String ROTATION = "LABELPLACEMENT_ROTATION";
    
    /**
     * Label X anchor attribute name.
     */
    public static final String XANCHOR = "LABELPLACEMENT_XANCHOR";
    
    /**
     * Label X displacementattribute name.
     */
    public static final String XDISPLACEMENT = "LABELPLACEMENT_XDISPLACEMENT";
    
    /**
     * Label Y anchor attribute name.
     */
    public static final String YANCHOR = "LABELPLACEMENT_YANCHOR";
 
    /**
     * Label Y displacement attribute name.
     */
    public static final String YDISPLACEMENT = "LABELPLACEMENT_YDISPLACEMENT";
    
    /**
     * Show label attribute name.
     */
    public static final String SHOW_LABEL = "LABELPLACEMENT_SHOW_LABEL";

    /**
     * Returns the label.
     * @return the label.
     */
    public String getLabel();
    
    /**
     * Returns whether the label has been set.
     * @return true if the label has been set, false otherwise.
     */    
    public boolean isLabelSet();
    
    /**
     * Sets the label.
     * @param label the label.
     */    
    public void setLabel(String label);
    
    /**
     * Sets the fact that the label has been set.
     * @param flag true if the label has been set, false otherwise.
     */    
    public void setLabelSet(boolean flag);
    
    /**
     * Returns the label rotation.
     * @return the label rotation.
     */
    public float getRotation();
    
    /**
     * Returns whether the label rotation has been set.
     * @return true if the label rotation has been set, false otherwise.
     */    
    public boolean isRotationSet();
    
    /**
     * Sets the label rotation.
     * @param labelRotation the label rotation.
     */    
    public void setRotation(float labelRotation);
    
    /**
     * Sets the fact that the label rotation has been set.
     * @param flag true if the label rotation has been set, false otherwise.
     */    
    public void setRotationSet(boolean flag);
   
    /**
     * Returns the label XAnchor.
     * @return the label XAnchor.
     */
    public XAnchor getXAnchor();
    
    /**
     * Returns whether the label XAnchor has been set.
     * @return true if the label XAnchor has been set, false otherwise.
     */    
    public boolean isXAnchorSet();
    
    /**
     * Sets the label XAnchor.
     * @param labelXAnchor the label XAnchor.
     */    
    public void setXAnchor(XAnchor labelXAnchor);
    
    /**
     * Sets the fact that the label XAnchor has been set.
     * @param flag true if the label XAnchor has been set, false otherwise.
     */    
    public void setXAnchorSet(boolean flag);
   
    /**
     * Returns the label X displacement.
     * @return the label X displacement.
     */
    public float getXDisplacement();
    
    /**
     * Returns whether the label X displacement has been set.
     * @return true if the label X displacement has been set, false otherwise.
     */    
    public boolean isXDisplacementSet();
    
    /**
     * Sets the label X displacement.
     * @param labelXDisplacement the label X displacement.
     */    
    public void setXDisplacement(float labelXDisplacement);
    
    /**
     * Sets the fact that the label X displacement has been set.
     * @param flag true if the label X displacement has been set, false otherwise.
     */    
    public void setXDisplacementSet(boolean flag);
   
    /**
     * Returns the label YAnchor.
     * @return the label YAnchor.
     */
    public YAnchor getYAnchor();
    
    /**
     * Returns whether the label YAnchor has been set.
     * @return true if the label YAnchor has been set, false otherwise.
     */    
    public boolean isYAnchorSet();
    
    /**
     * Sets the label YAnchor.
     * @param labelYAnchor the label YAnchor.
     */    
    public void setYAnchor(YAnchor labelYAnchor);
    
    /**
     * Sets the fact that the label YAnchor has been set.
     * @param flag true if the label YAnchor has been set, false otherwise.
     */    
    public void setYAnchorSet(boolean flag);
   
    /**
     * Returns the label Y displacement.
     * @return the label Y displacement.
     */
    public float getYDisplacement();
    
    /**
     * Returns whether the label Y displacement has been set.
     * @return true if the label Y displacement has been set, false otherwise.
     */    
    public boolean isYDisplacementSet();
    
    /**
     * Sets the label Y displacement.
     * @param labelYDisplacement the label Y displacement.
     */    
    public void setYDisplacement(float labelYDisplacement);
    
    /**
     * Sets the fact that the label Y displacement has been set.
     * @param flag true if the label Y displacement has been set, false otherwise.
     */    
    public void setYDisplacementSet(boolean flag);
   
    /**
     * Returns the show label.
     * @return the show label.
     */
    public boolean getShowLabel();
    
    /**
     * Returns whether the show label has been set.
     * @return true if the show label has been set, false otherwise.
     */    
    public boolean isShowLabelSet();
    
    /**
     * Sets the show label.
     * @param showLabel the show label.
     */    
    public void setShowLabel(boolean showLabel);
    
    /**
     * Sets the fact that the show label has been set.
     * @param flag true if the show label has been set, false otherwise.
     */    
    public void setShowLabelSet(boolean flag);
}
