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
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Highlight {
    /**
     * Default blinking value.
     */
    public static final boolean DEFAULT_BLINKING = false;

    /**
     * Default blink pattern value.
     */
    public static final float[] DEFAULT_BLINK_PATTERN = {0.5f, 0.5f};
    
    /**
     * Blinking attribute name.
     */
    public static final String BLINKING = "HIGHLIGHT_BLINKING";

    /**
     * Blink pattern attribute name.
     */
    public static final String BLINK_PATTERN = "HIGHLIGHT_BLINK_PATTERN";
        
    /**
     * Returns the blinking value.
     *
     * @return the blinking value.
     */
    public boolean getBlinking();
    
    /**
     * Returns whether the blinking value has been set.
     *
     * @return <code>true</code> if the blinking value has been set,
     *         <code>false</code> otherwise.
     */    
    public boolean isBlinkingSet();
    
    /**
     * Sets the blinking value.
     *
     * @param blinking the blinking value.
     */    
    public void setBlinking(boolean blinking);
    
    /**
     * Sets the fact that the blinking value has been set.
     *
     * @param flag <code>true</code> if the blinking value has been set,
     *             <code>false</code> otherwise.
     */    
    public void setBlinkingSet(boolean flag);
   
    /**
     * Returns the blink pattern value.
     *
     * @return the blink pattern value.
     */
    public float[] getBlinkPattern();
    
    /**
     * Returns whether the blink pattern value has been set.
     *
     * @return <code>true</code> if the blink pattern value has been set,
     *         <code>false</code> otherwise.
     */    
    public boolean isBlinkPatternSet();
    
    /**
     * Sets the blink pattern value.
     *
     * @param blinkPattern the blink pattern value.
     */    
    public void setBlinkPattern(float[] blinkPattern);
    
    /**
     * Sets the fact that the blink pattern value has been set.
     *
     * @param flag <code>true</code> if the blink pattern value has been set,
     *             <code>false</code> otherwise.
     */    
    public void setBlinkPatternSet(boolean flag);
}
