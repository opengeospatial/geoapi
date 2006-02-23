/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 *
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 11:54:42  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.1  2005/01/21 19:42:48  stephanef
 * RangePosition related change
 *
 */
package com.owsx.coverage;

import java.util.Map;

/**
 * This class is used to represent a position in the range space.
 * A position is defined by the measure name (to select the measure data space)
 * and its axis values (if any)
 * 
 * @author Stephane Fellah
 * @since Jan 14, 2005
 * @version $Revision$
 */
public interface RangePosition {
    /**
     * Get the Measure name in the range space
     * 
     * @return Measure name
     */
    public String getMeasureName();
    
    /**
     * Get the Axis values constraints if any
     * 
     * @return Map of axis name/lexical value pair
     */
    public Map<String,String> getAxisValues();
    
    /**
     * Get the lexical value of the axis with the given name
     * 
     * @param name
     * @return lexical value
     */
    public String getAxisLexicalValue(String name);
    
    public int getAxisInt(String name);
    
    public float getAxisFloat(String name);
    
    public double getAxisDouble(String name);
    
    public short getAxisShort(String name);
    
}
