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

/**
 * Encapsulates the highlight attributes that can be applied to any
 * {@link org.opengis.go.display.primitive.Graphic}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface Highlight {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    //**  Highlight property names  **
    
    public static final String HIGHLIGHT_BLINK_PATTERN = "HIGHLIGHT_BLINK_PATTERN";    
    public static final String HIGHLIGHT_BLINKING = "HIGHLIGHT_BLINKING";
    
    //**  Default Highlight property values  **    
    
    /**  Default blinking value.  */
    public static final boolean DEFAULT_HIGHLIGHT_BLINKING = false;

    /**  Default blink pattern value.  */
    public static final float[] DEFAULT_HIGHLIGHT_BLINK_PATTERN = {0.5f, 0.5f};
    
    //*************************************************************************
    //  Methods
    //*************************************************************************
    
    /**
     * Returns the blinking value.
     * @return the blinking value.
     */
    public boolean getHighlightBlinking();
    
    /**
     * Returns whether the blinking value has been set.
     * @return true if the blinking value has been set, false otherwise.
     */    
    public boolean isHighlightBlinkingSet();
    
    /**
     * Sets the blinking value.
     * @param blinking the blinking value.
     */    
    public void setHighlightBlinking(boolean blinking);
    
    /**
     * Sets the fact that the blinking value has been set.
     * @param flag true if the blinking value has been set, false otherwise.
     */    
    public void setHighlightBlinkingSet(boolean flag);
   
    /**
     * Returns the blink pattern value.
     * @return the blink pattern value.
     */
    public float[] getHighlightBlinkPattern();
    
    /**
     * Returns whether the blink pattern value has been set.
     * @return true if the blink pattern value has been set, false otherwise.
     */    
    public boolean isHighlightBlinkPatternSet();
    
    /**
     * Sets the blink pattern value.
     * @param blinkPattern the blink pattern value.
     */    
    public void setHighlightBlinkPattern(float[] blinkPattern);
    
    /**
     * Sets the fact that the blink pattern value has been set.
     * @param flag true if the blink pattern value has been set, false otherwise.
     */    
    public void setHighlightBlinkPatternSet(boolean flag);
   
}

