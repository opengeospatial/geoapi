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
 * Encapsulates the viewability attributes that can be applied to any
 * {@link org.opengis.go.display.primitive.Graphic}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface Viewability {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
 
    //**  Viewability property names  **
    
    public static final String VIEWABILITY_MAX_SCALE = "VIEWABILITY_MAX_SCALE";    
    public static final String VIEWABILITY_MIN_SCALE = "VIEWABILITY_MIN_SCALE";    
    public static final String VIEWABILITY_VISIBLE = "VIEWABILITY_VISIBLE";    
    public static final String VIEWABILITY_Z_ORDER = "VIEWABILITY_Z_ORDER";

    //**  Default Viewability property values  **
    
    /**  Default max scale value.  */
    public static final int DEFAULT_VIEWABILITY_MAX_SCALE = Integer.MAX_VALUE;

    /**  Default min scale value.  */
    public static final int DEFAULT_VIEWABILITY_MIN_SCALE = 1;
    
    /**  Default visible value.  */
    public static final boolean DEFAULT_VIEWABILITY_VISIBLE = true;

    /**  Default z order value.  */
    public static final double DEFAULT_VIEWABILITY_Z_ORDER = 0.0;
    
    //*************************************************************************
    //  Methods
    //*************************************************************************
    
    /**
     * Returns the max scale value.
     * @return the max scale value.
     */
    public double getViewabilityMaxScale();
    
    /**
     * Returns whether the max scale value has been set.
     * @return true if the max scale value has been set, false otherwise.
     */    
    public boolean isViewabilityMaxScaleSet();
    
    /**
     * Sets the max scale value.
     * @param maxScale the max scale value.
     */    
    public void setViewabilityMaxScale(double maxScale);
    
    /**
     * Sets the fact that the max scale value has been set.
     * @param flag true if the max scale value has been set, false otherwise.
     */    
    public void setViewabilityMaxScaleSet(boolean flag);
  
    /**
     * Returns the min scale value.
     * @return the min scale value.
     */
    public double getViewabilityMinScale();
    
    /**
     * Returns whether the min scale value has been set.
     * @return true if the min scale value has been set, false otherwise.
     */    
    public boolean isViewabilityMinScaleSet();
    
    /**
     * Sets the min scale value.
     * @param minScale the min scale value.
     */    
    public void setViewabilityMinScale(double minScale);
    
    /**
     * Sets the fact that the min scale value has been set.
     * @param flag true if the min scale value has been set, false otherwise.
     */    
    public void setViewabilityMinScaleSet(boolean flag);
    
    /**
     * Returns the z order hint value.
     * @return the z order hint value.
     */
    public double getViewabilityZOrderHint();
    
    /**
     * Returns whether the z order hint value has been set.
     * @return true if the z order hint value has been set, false otherwise.
     */    
    public boolean isViewabilityZOrderHintSet();
    
    /**
     * Sets the z order hint value.
     * @param zOrderHint the z order hint value.
     */    
    public void setViewabilityZOrderHint(double zOrderHint);
    
    /**
     * Sets the fact that the z order hint value has been set.
     * @param flag true if the z order hint value has been set, false otherwise.
     */    
    public void setViewabilityZOrderHintSet(boolean flag);
    
    /**
     * Returns the visible value.
     * @return the visible value.
     */
    public boolean getViewabilityVisible();
    
    /**
     * Returns whether the visible value has been set.
     * @return true if the visible value has been set, false otherwise.
     */    
    public boolean isViewabilityVisibleSet();
    
    /**
     * Sets the visible value.
     * @param visible the visible value.
     */    
    public void setViewabilityVisible(boolean visible);
    
    /**
     * Sets the fact that the visible value has been set.
     * @param flag true if the visible value has been set, false otherwise.
     */    
    public void setViewabilityVisibleSet(boolean flag);
    
}

