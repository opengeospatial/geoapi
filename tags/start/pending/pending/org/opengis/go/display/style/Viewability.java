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
 * The <code>Viewability</code> interface encapsulates the viewability attributes
 * that can be applied to any Graphic.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface Viewability {
 
    /**
     * Default max scale value.
     */
    public static final int DEFAULT_MAX_SCALE = Integer.MAX_VALUE;

    /**
     * Default min scale value.
     */
    public static final int DEFAULT_MIN_SCALE = 1;
    
    /**
     * Default visible value.
     */
    public static final boolean DEFAULT_VISIBLE = true;

    /**
     * Default z order value.
     */
    public static final double DEFAULT_Z_ORDER = 0.0;
    
    /**
     * Max scale attribute name.
     */
    public static final String MAX_SCALE = "VIEWABILITY_MAX_SCALE";

    /**
     * Min scale attribute name.
     */
    public static final String MIN_SCALE = "VIEWABILITY_MIN_SCALE";
    
    /**
     * Visible attribute name.
     */
    public static final String VISIBLE = "VIEWABILITY_VISIBLE";

    /**
     * Z Order attribute name.
     */
    public static final String Z_ORDER = "VIEWABILITY_Z_ORDER";
    
    /**
     * Returns the max scale value.
     * @return the max scale value.
     */
    public double getMaxScale();
    
    /**
     * Returns whether the max scale value has been set.
     * @return true if the max scale value has been set, false otherwise.
     */    
    public boolean isMaxScaleSet();
    
    /**
     * Sets the max scale value.
     * @param maxScale the max scale value.
     */    
    public void setMaxScale(double maxScale);
    
    /**
     * Sets the fact that the max scale value has been set.
     * @param flag true if the max scale value has been set, false otherwise.
     */    
    public void setMaxScaleSet(boolean flag);
  
    /**
     * Returns the min scale value.
     * @return the min scale value.
     */
    public double getMinScale();
    
    /**
     * Returns whether the min scale value has been set.
     * @return true if the min scale value has been set, false otherwise.
     */    
    public boolean isMinScaleSet();
    
    /**
     * Sets the min scale value.
     * @param minScale the min scale value.
     */    
    public void setMinScale(double minScale);
    
    /**
     * Sets the fact that the min scale value has been set.
     * @param flag true if the min scale value has been set, false otherwise.
     */    
    public void setMinScaleSet(boolean flag);
    
    /**
     * Returns the z order hint value.
     * @return the z order hint value.
     */
    public double getZOrderHint();
    
    /**
     * Returns whether the z order hint value has been set.
     * @return true if the z order hint value has been set, false otherwise.
     */    
    public boolean isZOrderHintSet();
    
    /**
     * Sets the z order hint value.
     * @param zOrderHint the z order hint value.
     */    
    public void setZOrderHint(double zOrderHint);
    
    /**
     * Sets the fact that the z order hint value has been set.
     * @param flag true if the z order hint value has been set, false otherwise.
     */    
    public void setZOrderHintSet(boolean flag);
    
    /**
     * Returns the visible value.
     * @return the visible value.
     */
    public boolean getVisible();
    
    /**
     * Returns whether the visible value has been set.
     * @return true if the visible value has been set, false otherwise.
     */    
    public boolean isVisibleSet();
    
    /**
     * Sets the visible value.
     * @param visible the visible value.
     */    
    public void setVisible(boolean visible);
    
    /**
     * Sets the fact that the visible value has been set.
     * @param flag true if the visible value has been set, false otherwise.
     */    
    public void setVisibleSet(boolean flag);
    
}
